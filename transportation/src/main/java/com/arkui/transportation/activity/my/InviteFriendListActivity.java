package com.arkui.transportation.activity.my;

import android.os.Handler;

import com.arkui.fz_tools.adapter.CommonAdapter;
import com.arkui.fz_tools.ui.BaseListActivity;
import com.arkui.fz_tools.utils.DividerItemDecoration;
import com.arkui.fz_tools.view.PullRefreshRecyclerView;
import com.arkui.transportation.R;
import com.arkui.transportation.utils.ListData;
import com.scwang.smartrefresh.layout.api.RefreshLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

public class InviteFriendListActivity extends BaseListActivity<String> {
    @BindView(R.id.rl_list)
    PullRefreshRecyclerView mRlList;
    private CommonAdapter<String> mCommonAdapter;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_invite_friend_list);
        setTitle("受邀好友列表");

    }

    @Override
    public void initView() {
        super.initView();
        ButterKnife.bind(this);
        mCommonAdapter = initAdapter(mRlList, R.layout.item_invite_friend);
        mRlList.addItemDecoration(new DividerItemDecoration(mActivity, DividerItemDecoration.VERTICAL_LIST));
        onRefresh(null);

    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        new Handler().postDelayed(new Runnable(){
            public void run() {
                mCommonAdapter.addData(ListData.getTestData(""));
                mRlList.refreshComplete();
            }
        }, 300);
    }
}
