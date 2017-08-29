package com.arkui.transportation_shipper.owner.activity.my;

import com.arkui.fz_tools._interface.BillingDetailsInterface;
import com.arkui.fz_tools.adapter.CommonAdapter;
import com.arkui.fz_tools.entity.BillingDetailsEntity;
import com.arkui.fz_tools.mvp.BillingDetailsPresenter;
import com.arkui.fz_tools.ui.BaseActivity;
import com.arkui.fz_tools.utils.DividerItemDecoration;
import com.arkui.fz_tools.view.PullRefreshRecyclerView;
import com.arkui.transportation_shipper.R;
import com.arkui.transportation_shipper.common.base.App;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class DetailBillActivity extends BaseActivity implements OnRefreshListener, BillingDetailsInterface, BaseQuickAdapter.RequestLoadMoreListener, com.arkui.fz_tools.listener.OnBindViewHolderListener<BillingDetailsEntity> {

    @BindView(R.id.rl_bill)
    PullRefreshRecyclerView mRlBill;
    private com.arkui.fz_tools.adapter.CommonAdapter<BillingDetailsEntity> mDetailBillAdapter;
    private BillingDetailsPresenter billingDetailsPresenter;
    private int page = 1;
    private int pageSize = 10;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_detail_bill);
        setTitle("明细账单");
    }
    @Override
    public void initView() {
        super.initView();
        ButterKnife.bind(this);
        billingDetailsPresenter = new BillingDetailsPresenter(this, this);
        mRlBill.setLinearLayoutManager();
        mRlBill.addItemDecoration(new DividerItemDecoration(mActivity, DividerItemDecoration.VERTICAL_LIST));
        mRlBill.setOnRefreshListener(this);

        mDetailBillAdapter = CommonAdapter.getInstance(R.layout.item_detail_bill, this);
        mRlBill.setAdapter(mDetailBillAdapter);
        mDetailBillAdapter.setOnLoadMoreListener(this, mRlBill.getRecyclerView());
        onRefreshing();

    }

    public void onRefreshing() {

        billingDetailsPresenter.getBillingDetails(App.getUserId(), page, pageSize);

    }

    @Override
    public void convert(BaseViewHolder helper, BillingDetailsEntity item) {
        String type =null;
        // withdraw=提现  pay_message_free=息支付信费 send_balance=赠送余额  recharge=充值
//        switch (item.getDetailType()){
//            case "withdraw":
//                type= "提现";
//                break;
//            case "pay_message_free":
//                type= "支付信息费";
//                break;
//            case "send_balance":
//                type="赠送余额";
//                break;
//            case "recharge" :
//                type = "充值";
//                break;
//        }
        helper.setText(R.id.tv_name,item.getCostName());
        helper.setText(R.id.tv_content,item.getCreatedAt());
        helper.setText(R.id.iv_arrow,item.getUnit()+item
                .getAmountPaid());
    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        page = 1;
        onRefreshing();
    }

    //数据请求成功
    @Override
    public void onSuccess(List<BillingDetailsEntity> billingDetailsEntityList) {
        mRlBill.refreshComplete();
        if (page == 1) {
            mDetailBillAdapter.setNewData(billingDetailsEntityList);
        } else {
            mDetailBillAdapter.addData(billingDetailsEntityList);
        }
        if (billingDetailsEntityList.size() <= pageSize) {
            mDetailBillAdapter.loadMoreEnd(false);
        }

    }

    // 数据请求失败
    @Override
    public void onFail(String errorMessage) {
        mRlBill.refreshComplete();
        mRlBill.loadFail();
        mDetailBillAdapter.setNewData(null);
    }

    /**
     * 上拉加载更多
     */
    @Override
    public void onLoadMoreRequested() {
        page++;
        onRefreshing();
    }
}
