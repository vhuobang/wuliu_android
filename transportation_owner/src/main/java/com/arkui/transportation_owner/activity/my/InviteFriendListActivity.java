package com.arkui.transportation_owner.activity.my;

import android.widget.ImageView;
import android.widget.TextView;

import com.arkui.fz_tools._interface.InviteFriendListInterface;
import com.arkui.fz_tools.adapter.CommonAdapter;
import com.arkui.fz_tools.entity.InviteFriendEntity;
import com.arkui.fz_tools.mvp.InviteFriendListPresenter;
import com.arkui.fz_tools.ui.BaseListActivity;
import com.arkui.fz_tools.utils.DividerItemDecoration;
import com.arkui.fz_tools.utils.GlideUtils;
import com.arkui.fz_tools.view.PullRefreshRecyclerView;
import com.arkui.transportation_owner.R;
import com.arkui.transportation_owner.base.App;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.scwang.smartrefresh.layout.api.RefreshLayout;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class InviteFriendListActivity extends BaseListActivity<InviteFriendEntity> implements InviteFriendListInterface, BaseQuickAdapter.RequestLoadMoreListener {
    @BindView(R.id.rl_list)
    PullRefreshRecyclerView mRlList;
    private CommonAdapter<InviteFriendEntity> mCommonAdapter;
    private InviteFriendListPresenter mInviteFriendListPresenter;
    int page = 1;
    int pageSize = 10;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_invite_friend_list);
        setTitle("受邀好友列表");

    }

    @Override
    public void initView() {
        super.initView();
        ButterKnife.bind(this);
        mInviteFriendListPresenter = new InviteFriendListPresenter(this, this);
        mCommonAdapter = initAdapter(mRlList, R.layout.item_invite_friend);
        mRlList.addItemDecoration(new DividerItemDecoration(mActivity, DividerItemDecoration.VERTICAL_LIST));
        mCommonAdapter.setOnLoadMoreListener(this, mRlList.getRecyclerView());

    }

    @Override
    public void initData() {
        loadNetData();
    }

    private void loadNetData() {
        mInviteFriendListPresenter.getInviteFriendList(App.getUserId(), page, pageSize);
    }

    // 下拉刷新
    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        page = 1;
        loadNetData();
    }

    @Override
    public void convert(BaseViewHolder helper, InviteFriendEntity item) {
        ImageView avatar = helper.getView(R.id.iv_avatar);
        GlideUtils.getInstance().loadCircle(InviteFriendListActivity.this,item.getAvatar(),avatar);
        helper.setText(R.id.tv_name,item.getNickname());
        TextView type = helper.getView(R.id.tv_type);
        // type 1.  货主 2.物流 3.车主 4.司机
        switch (item.getType()){
            case "1":
                type.setText("货主");
                type.setTextColor(getResources().getColor(R.color.huozhu_color));
                break;
            case "2":
                type.setText("物流");
                type.setTextColor(getResources().getColor(R.color.wuliu_color));
                break;
            case "3":
                type.setText("车主");
                type.setTextColor(getResources().getColor(R.color.chezhu_color));
                break;
            case "4":
                type.setText("司机");
                type.setTextColor(getResources().getColor(R.color.siji_color));
                break;
        }
    }

    /**
     * 请求数据成功
     *
     * @param inviteFriendEntities
     */
    @Override
    public void onSuccess(List<InviteFriendEntity> inviteFriendEntities) {
        if (page == 1) {
            mCommonAdapter.setNewData(inviteFriendEntities);
            mRlList.refreshComplete();
            if (mCommonAdapter.getItemCount() < 10) {
                mCommonAdapter.loadMoreEnd(true);
            } else {
                mCommonAdapter.loadMoreEnd(false);
            }
        } else {
            mCommonAdapter.addData(inviteFriendEntities);
            mCommonAdapter.loadMoreComplete();
            mRlList.refreshComplete();
        }
    }

    /**
     * 请求数据失败
     *
     * @param message
     */
    @Override
    public void onFail(String message) {
        mCommonAdapter.loadMoreEnd();
        mRlList.refreshComplete();
        mRlList.loadFail();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mInviteFriendListPresenter.onDestroy();
    }

    /**
     * 加载更多
     */
    @Override
    public void onLoadMoreRequested() {
        page++;
        loadNetData();
    }
}
