package com.arkui.transportation_owner.activity.waybill;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.arkui.fz_tools.dialog.CommonDialog;
import com.arkui.fz_tools.listener.OnConfirmClick;
import com.arkui.fz_tools.ui.BaseActivity;
import com.arkui.fz_tools.utils.DividerItemDecoration2;
import com.arkui.fz_tools.utils.StrUtil;
import com.arkui.fz_tools.view.PullRefreshRecyclerView;
import com.arkui.transportation_owner.R;
import com.arkui.transportation_owner.adapter.PublishDetailsAdapter;
import com.arkui.transportation_owner.entity.PublishDetailEntity;
import com.arkui.transportation_owner.mvp.PublishDetailPresenter;
import com.arkui.transportation_owner.view.PublishDetailView;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class CarriageDetailActivity extends BaseActivity implements OnRefreshListener, PublishDetailView, OnConfirmClick {

    @BindView(R.id.rl_list)
    PullRefreshRecyclerView mRlList;
    //private CommonAdapter<String> mCarriageDetailAdapter;
    private CommonDialog mCommonDialog;
    private PublishDetailsAdapter mPublishDetailsAdapter;
    private PublishDetailPresenter mPublishDetailPresenter;
    private ViewHolder mHeadHolder;
    private String mId;

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

        mRlList.setLinearLayoutManager();
        mRlList.addItemDecoration(new DividerItemDecoration2(mActivity, DividerItemDecoration2.VERTICAL_LIST));

        // mCarriageDetailAdapter = new CommonAdapter<>(R.layout.item_carriage_detail, this);
        mPublishDetailsAdapter = new PublishDetailsAdapter();

        mRlList.setOnRefreshListener(this);
        mRlList.setAdapter(mPublishDetailsAdapter);

        View mCarriageHeaderView = getLayoutInflater().inflate(R.layout.layout_carriage_header, mRlList, false);
        //初始化头
        initHeadView(mCarriageHeaderView);
        mPublishDetailsAdapter.setHeaderView(mCarriageHeaderView);
        mCommonDialog = new CommonDialog();
        mCommonDialog.setTitle("停止抢单").setContent("该运单还剩20吨没有被抢，确定要停止发布吗？");
        mCommonDialog.setConfirmClick(this);
    }

    private void initHeadView(View mCarriageHeaderView) {
        mHeadHolder = new ViewHolder(mCarriageHeaderView);
    }

    @Override
    protected void onRightClick() {
        super.onRightClick();
        showActivity(EditPlanPublishDetailActivity.class);
    }

    @Override
    public void initData() {
        super.initData();
        mId = getIntent().getStringExtra("id");
        mPublishDetailPresenter = new PublishDetailPresenter(this, this);
        mPublishDetailPresenter.postPublishDetail(mId);
    }

    @OnClick(R.id.tv_state)
    public void onClick() {
        mCommonDialog.show(getSupportFragmentManager(), "state");
    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        mPublishDetailPresenter.postPublishDetail(mId);
    }

    public static void showActivity(Context context, String cargo_id) {
        Intent intent = new Intent(context, CarriageDetailActivity.class);
        intent.putExtra("id", cargo_id);
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
        mHeadHolder.mTvSurplusNum.setText(String.format("剩余%s",publishDetailEntity.getCargoNum()+"吨"));

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

    @Override
    public void onConfirmClick() {

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
