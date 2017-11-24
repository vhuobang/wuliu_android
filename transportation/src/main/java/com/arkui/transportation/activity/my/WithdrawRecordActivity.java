package com.arkui.transportation.activity.my;

import com.arkui.fz_tools._interface.IntegralDetailsInterface;
import com.arkui.fz_tools.adapter.CommonAdapter;
import com.arkui.fz_tools.entity.IntegralDetailsEntity;
import com.arkui.fz_tools.listener.OnBindViewHolderListener;
import com.arkui.fz_tools.mvp.IntegralDetailsPresenter;
import com.arkui.fz_tools.ui.BaseActivity;
import com.arkui.fz_tools.utils.DividerItemDecoration;
import com.arkui.fz_tools.utils.DividerItemDecoration2;
import com.arkui.fz_tools.view.PullRefreshRecyclerView;
import com.arkui.transportation.R;
import com.arkui.transportation.base.App;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class WithdrawRecordActivity extends BaseActivity implements OnBindViewHolderListener<IntegralDetailsEntity>,OnRefreshListener, IntegralDetailsInterface, BaseQuickAdapter.RequestLoadMoreListener {

    @BindView(R.id.rl_record)
    PullRefreshRecyclerView mRlRecord;
    private CommonAdapter<IntegralDetailsEntity> mWithdrawRecord;
    int page =1;
    int pageSize=10;
    private IntegralDetailsPresenter integralDetailsPresenter;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_withdraw_record);
        setTitle("提现记录");
    }

    @Override
    public void initView() {
        super.initView();
        ButterKnife.bind(this);
        integralDetailsPresenter = new IntegralDetailsPresenter(this, this);
        mRlRecord.setLinearLayoutManager();
        mWithdrawRecord = CommonAdapter.getInstance(R.layout.item_withdraw_record,this);
        mRlRecord.setAdapter(mWithdrawRecord);
        mRlRecord.addItemDecoration(new DividerItemDecoration2(mActivity,DividerItemDecoration.VERTICAL_LIST));
        mRlRecord.setOnRefreshListener(this);
        mRlRecord.setEnablePullToRefresh(false);
        mWithdrawRecord.setOnLoadMoreListener(this,mRlRecord.getRecyclerView());
        onRefreshing();
    }

    public void onRefreshing() {
        integralDetailsPresenter.getIntegralDetails(App.getUserId(),page,pageSize);
    }

    @Override
    public void convert(BaseViewHolder helper, IntegralDetailsEntity item) {
       helper.setText(R.id.tv_create_time,item.getCreatedAt());
        helper.setText(R.id.point_number,item.getIntegral());
         String  status = item.getStatus().equals("1") ? "审核中": "已成功";
         helper.setText(R.id.tv_status,status);
    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        page++;
        onRefreshing();
    }

    /**
     * 积分提现详情
     * @param integralDetailsEntityList
     */
    @Override
    public void onSuccess(List<IntegralDetailsEntity> integralDetailsEntityList) {
        mRlRecord.refreshComplete();
        if (page == 1) {
            mWithdrawRecord.setNewData(integralDetailsEntityList);
        } else {
            mWithdrawRecord.addData(integralDetailsEntityList);
        }
        if (integralDetailsEntityList.size() <= pageSize) {
            mWithdrawRecord.loadMoreEnd(false);
        }
    }

    /**
     * 请求接口失败
     * @param errorMessage
     */
    @Override
    public void onFail(String errorMessage) {
        if (page==1){
            mWithdrawRecord.setNewData(null);
            mRlRecord.loadFail();
        }else {
            mRlRecord.refreshComplete();
            mWithdrawRecord.loadMoreEnd();
        }



    }

    /**
     * 加载更多
     */
    @Override
    public void onLoadMoreRequested() {
        page++;
        onRefreshing();
    }
}
