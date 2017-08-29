package com.arkui.transportation.activity.my;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.arkui.fz_tools._interface.InformationFeeInterface;
import com.arkui.fz_tools.adapter.CommonAdapter;
import com.arkui.fz_tools.entity.InformationDetailEntity;
import com.arkui.fz_tools.mvp.InformationFeePresenter;
import com.arkui.fz_tools.ui.BaseListActivity;
import com.arkui.fz_tools.utils.DividerItemDecoration2;
import com.arkui.fz_tools.view.PullRefreshRecyclerView;
import com.arkui.transportation.R;
import com.arkui.transportation.base.App;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.scwang.smartrefresh.layout.api.RefreshLayout;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MyInfoFeeActivity extends BaseListActivity<InformationDetailEntity> implements InformationFeeInterface, BaseQuickAdapter.RequestLoadMoreListener {

    @BindView(R.id.rl_list)
    PullRefreshRecyclerView mRlList;
    private CommonAdapter<InformationDetailEntity> mCommonAdapter;
    private ImageView mIvHintBg;
    private InformationFeePresenter informationFeePresenter;
    int page = 1;
    int pageSize = 10;
    private String informationFee;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_my_info_fee);
        setTitle("我的信息费");
    }

    @Override
    public void initView() {
        super.initView();
        ButterKnife.bind(this);
        informationFee = getIntent().getStringExtra("informationFee");

        informationFeePresenter = new InformationFeePresenter(this, this);
        mCommonAdapter = initAdapter(mRlList, R.layout.item_my_info_fee);
        mRlList.addItemDecoration(new DividerItemDecoration2(mActivity, DividerItemDecoration2.VERTICAL_LIST));
        mCommonAdapter.setOnLoadMoreListener(this, mRlList.getRecyclerView());
        View headView = getLayoutInflater().inflate(R.layout.layout_my_info_fee_head, mRlList, false);
        mIvHintBg = (ImageView) headView.findViewById(R.id.iv_hint_bg);
        TextView information = (TextView) headView.findViewById(R.id.information_fee);
        information.setText(informationFee);
        headView.findViewById(R.id.iv_hint).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mIvHintBg.getVisibility() == View.VISIBLE) {
                    mIvHintBg.setVisibility(View.GONE);
                } else {
                    mIvHintBg.setVisibility(View.VISIBLE);
                }

            }
        });

        mCommonAdapter.addHeaderView(headView);

        onRefreshing();
    }

    @Override
    public void convert(BaseViewHolder helper, InformationDetailEntity item) {
        helper.setText(R.id.tv_create_time, item.getCreatedAt());
        helper.setText(R.id.tv_way_bill_number, item.getOrderNumber());
        helper.setText(R.id.information_fee, item.getInfomationFee());
    }

    public void onRefreshing() {
        informationFeePresenter.getInformationFeeList(App.getUserId(), page, pageSize);
    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        page = 1;
        onRefreshing();
    }

    /**
     * 数据请求成功
     *
     * @param informationDetailEntities
     */
    @Override
    public void onSuccess(List<InformationDetailEntity> informationDetailEntities) {
        mRlList.refreshComplete();
        if (page == 1) {
            mCommonAdapter.setNewData(informationDetailEntities);
        } else {
            mCommonAdapter.addData(informationDetailEntities);
        }
        if (informationDetailEntities.size() <= pageSize) {
            mCommonAdapter.loadMoreEnd(false);
        }
    }

    /**
     * 数据请求失败
     *
     * @param errorMessage
     */
    @Override
    public void onFail(String errorMessage) {
        mRlList.refreshComplete();
        mRlList.loadFail();
        mCommonAdapter.setNewData(null);
    }

    // 上拉加载更多
    @Override
    public void onLoadMoreRequested() {
        page++;
        onRefreshing();
    }
}
