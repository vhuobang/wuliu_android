package com.arkui.transportation_owner.activity.waybill;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.arkui.fz_net.entity.BaseHttpResult;
import com.arkui.fz_net.http.ApiException;
import com.arkui.fz_net.http.HttpMethod;
import com.arkui.fz_net.http.RetrofitFactory;
import com.arkui.fz_net.subscribers.ProgressSubscriber;
import com.arkui.fz_tools.dialog.CommonDialog;
import com.arkui.fz_tools.listener.OnConfirmClick;
import com.arkui.fz_tools.ui.BaseActivity;
import com.arkui.fz_tools.utils.DividerItemDecoration2;
import com.arkui.fz_tools.utils.StrUtil;
import com.arkui.fz_tools.view.PullRefreshRecyclerView;
import com.arkui.transportation_owner.R;
import com.arkui.transportation_owner.adapter.PublishDetailsAdapter;
import com.arkui.transportation_owner.api.LogisticalApi;
import com.arkui.transportation_owner.entity.PublishDetailEntity;
import com.arkui.transportation_owner.mvp.PublishDetailPresenter;
import com.arkui.transportation_owner.view.PublishDetailView;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

import static com.arkui.transportation_owner.R.id.tv_state;

/**
 * 承运详情
 */
public class CarriageDetailActivity extends BaseActivity implements OnRefreshListener, PublishDetailView, OnConfirmClick {

    @BindView(R.id.rl_list)
    PullRefreshRecyclerView mRlList;

    @BindView(R.id.tv_state)
    TextView tvState;
    //private CommonAdapter<String> mCarriageDetailAdapter;
    private CommonDialog mCommonDialog;
    private PublishDetailsAdapter mPublishDetailsAdapter;
    private PublishDetailPresenter mPublishDetailPresenter;
    private ViewHolder mHeadHolder;
    private String mId;
    private String cStatus;
    LogisticalApi logisticalApi;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_carriage_detail);
        setTitle("承运详情");
        setRight("再来一单");
    }

    @Override
    public void initView() {
        super.initView();
        ButterKnife.bind(this);
        mId = getIntent().getStringExtra("id");
        cStatus = getIntent().getStringExtra("cStatus");
        tvState.setText(StrUtil.formatCStatus(cStatus));
        statusClickable();
        mRlList.setLinearLayoutManager();
        mRlList.addItemDecoration(new DividerItemDecoration2(mActivity, DividerItemDecoration2.VERTICAL_LIST));

        mPublishDetailsAdapter = new PublishDetailsAdapter();

        mRlList.setOnRefreshListener(this);
        mRlList.setAdapter(mPublishDetailsAdapter);

        View mCarriageHeaderView = getLayoutInflater().inflate(R.layout.layout_carriage_header, mRlList, false);
        //初始化头
        initHeadView(mCarriageHeaderView);
        mPublishDetailsAdapter.setHeaderView(mCarriageHeaderView);
        mCommonDialog = new CommonDialog();
        mCommonDialog.setTitle("停止抢单").setContent("确定要停止发布吗？");
        mCommonDialog.setConfirmClick(this);
        logisticalApi = RetrofitFactory.createRetrofit(LogisticalApi.class);
    }

    private void initHeadView(View mCarriageHeaderView) {
        mHeadHolder = new ViewHolder(mCarriageHeaderView);
    }

    @Override
    protected void onRightClick() {
        super.onRightClick();
        //showActivity(EditPlanPublishDetailActivity.class);

        EditPlanPublishDetailActivity.showActivity(mActivity,mId,true);
    }

    @Override
    public void initData() {
        super.initData();
        mPublishDetailPresenter = new PublishDetailPresenter(this, this);
        mPublishDetailPresenter.postPublishDetail(mId);
    }

    private void statusClickable() {
        switch (cStatus) {
            case "1": //发布中
                tvState.setClickable(true);
                break;
            case "2":// 已抢完
                tvState.setClickable(false);
                break;
            case "3"://已停止
                tvState.setClickable(false);
                break;
        }
    }

    @OnClick(tv_state)
    public void onClick() {
        mCommonDialog.show(getSupportFragmentManager(), "state");
    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        mPublishDetailPresenter.postPublishDetail(mId);
    }

    public static void showActivity(Context context, String cargo_id, String cStatus) {
        Intent intent = new Intent(context, CarriageDetailActivity.class);
        intent.putExtra("id", cargo_id);
        intent.putExtra("cStatus", cStatus);
        context.startActivity(intent);
    }

    @Override
    public void onSucceed(PublishDetailEntity publishDetailEntity) {
        String[] loadingAddress = publishDetailEntity.getLoadingAddress().split(" ");
        String[] unloadingAddress = publishDetailEntity.getUnloadingAddress().split(" ");
        mHeadHolder.mTvLoadingAddress.setText(loadingAddress.length >= 0 ? loadingAddress[0] : "");
        mHeadHolder.mTvLoadingDetailAddress.setText(loadingAddress.length >= 2 ? loadingAddress[1] : "");
        mHeadHolder.mTvUnloadingAddress.setText(unloadingAddress.length >= 0 ? unloadingAddress[0] : "");
        mHeadHolder.mTvUnloadingDetailAddress.setText(unloadingAddress.length >= 2 ? unloadingAddress[1] : "");

        mHeadHolder.mTvCargoName.setText(publishDetailEntity.getCargoName()+" "+ publishDetailEntity.getCargoNum()+"吨");
        mHeadHolder.mTvSurplusNum.setText(String.format("剩余%s",publishDetailEntity.getSurplusNum()+"吨"));

        mPublishDetailsAdapter.setNewData(publishDetailEntity.getCarrierInfo());

        mRlList.refreshComplete();
    }

    @Override
    public void onError() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPublishDetailPresenter.onDestroy();
    }

    // 点击发布停止
    @Override
    public void onConfirmClick() {
        Observable<BaseHttpResult> observable = logisticalApi.upCargoStatus(mId);
        HttpMethod.getInstance().getNetData(observable, new ProgressSubscriber<BaseHttpResult>(mActivity) {
            @Override
            protected void getDisposable(Disposable d) {
                mDisposables.add(d);
            }

            @Override
            public void onNext(BaseHttpResult value) {
                tvState.setText("已停止");
                tvState.setClickable(false);
            }

            @Override
            public void onApiError(ApiException e) {
                // super.onApiError(e);
            }
        });
    }

    static class ViewHolder {
        @BindView(R.id.tv_loading_address)
        TextView mTvLoadingAddress;
        @BindView(R.id.tv_loading_detail_address)
        TextView mTvLoadingDetailAddress;
        @BindView(R.id.tv_unloading_address)
        TextView mTvUnloadingAddress;
        @BindView(R.id.tv_unloading_detail_address)
        TextView mTvUnloadingDetailAddress;
        @BindView(R.id.tv_cargo_name)
        TextView mTvCargoName;
        @BindView(R.id.tv_surplus_num)
        TextView mTvSurplusNum;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
