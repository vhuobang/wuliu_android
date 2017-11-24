package com.arkui.transportation_shipper.owner.activity.asset;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.arkui.fz_net.entity.BaseHttpResult;
import com.arkui.fz_net.http.HttpMethod;
import com.arkui.fz_net.http.HttpResultFunc;
import com.arkui.fz_net.http.RetrofitFactory;
import com.arkui.fz_net.subscribers.ProgressSubscriber;
import com.arkui.fz_tools.dialog.CommonDialog;
import com.arkui.fz_tools.listener.OnConfirmClick;
import com.arkui.fz_tools.ui.BaseActivity;
import com.arkui.fz_tools.utils.AssetDecoration;
import com.arkui.fz_tools.utils.GlideUtils;
import com.arkui.fz_tools.view.PullRefreshRecyclerView;
import com.arkui.transportation_shipper.R;
import com.arkui.transportation_shipper.common.api.AssetApi;
import com.arkui.transportation_shipper.common.base.App;
import com.arkui.transportation_shipper.common.entity.DriverListDetailEntity;
import com.arkui.transportation_shipper.common.entity.RefreshAssetListEntity;
import com.arkui.transportation_shipper.owner.adapter.DriverDetailAdapter;
import com.arkui.transportation_shipper.owner.dialog.ViewVehicleLargeMapDialog;
import com.arkui.transportation_shipper.owner.listener.OnBindViewHolderListener;
import com.chad.library.adapter.base.BaseViewHolder;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;


/**
 * 司机详情
 * TODO 2017年8月31日 这里因为缺少数据司机状态没有写
 */
public class DriverDetailActivity extends BaseActivity implements OnBindViewHolderListener<String>, OnConfirmClick {

    @BindView(R.id.rl_driver)
    PullRefreshRecyclerView mRlDriver;
    //private CommonAdapter<String> mRlVehicleDerailsAdapter;
    private ViewVehicleLargeMapDialog mViewVehicleLargeMapDialog;
    private CommonDialog mCommonDialog;
    private AssetApi mAssetApi;
    private String mId;
    private ViewHolder mViewHolder;
    private DriverListDetailEntity mDriverDetail;
    private DriverDetailAdapter mDriverDetailAdapter;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_driver_detail);
        setTitle("司机详情");
    }

    @Override
    public void initView() {
        super.initView();
        ButterKnife.bind(this);
        mRlDriver.setLinearLayoutManager();
        //mRlVehicleDerailsAdapter = CommonAdapter.getInstance(R.layout.item_vehicle_details, this);
        mDriverDetailAdapter=new DriverDetailAdapter();
        mRlDriver.setAdapter(mDriverDetailAdapter);
        mRlDriver.addItemDecoration(new AssetDecoration(mActivity, AssetDecoration.VERTICAL_LIST));

      /*  new Handler().postDelayed(new Runnable() {
            public void run() {
                mRlVehicleDerailsAdapter.addData("车辆已装货");
                mRlVehicleDerailsAdapter.addData("车辆已装货");

            }
        }, 2000);*/
        mRlDriver.setEnablePullToRefresh(false);

        View vehicle_details_head = getLayoutInflater().inflate(R.layout.layout_driver_details_head, mRlDriver, false);
        mDriverDetailAdapter.addHeaderView(vehicle_details_head);
        mViewHolder = new ViewHolder(vehicle_details_head);
        vehicle_details_head.findViewById(R.id.iv_front);
        //  ImageView IvFront = ButterKnife.findById(vehicle_details_head, R.id.iv_front);
        //IvFront.setOnClickListener(this);
        initDialog();
    }

    private void initDialog() {
        mViewVehicleLargeMapDialog = new ViewVehicleLargeMapDialog();
        mCommonDialog = new CommonDialog();
        mCommonDialog.setTitle("删除司机").setContent("删除司机后，对应的手机号将不能登录司机端，确定要删除吗？").setConfirmClick(this);
    }

    @Override
    public void initData() {
        super.initData();
        //获取司机详情
        mId = getIntent().getStringExtra("id");
        mAssetApi = RetrofitFactory.createRetrofit(AssetApi.class);
        getNetData();
    }

    private void getNetData() {
        Observable<DriverListDetailEntity> observable = mAssetApi.postDriverDetail(mId).map(new HttpResultFunc<DriverListDetailEntity>());

        HttpMethod.getInstance().getNetData(observable, new ProgressSubscriber<DriverListDetailEntity>(mActivity) {
            @Override
            protected void getDisposable(Disposable d) {
                mDisposables.add(d);
            }

            @Override
            public void onNext(DriverListDetailEntity value) {
                setUiData(value);
            }
        });
    }

    private void setUiData(DriverListDetailEntity value) {
        mDriverDetail = value;
        mViewHolder.mTvName.setText(value.getName());
        mViewHolder.mTvMobile.setText(value.getMobile());
        mViewHolder.mTvPassword.setText(value.getOrigin_password());
        mViewHolder.mTvOutNum.setText(value.getOut_num());

        if (value.getId_photo_front() != null) {
            GlideUtils.getInstance().load(mActivity, value.getId_photo_front(), mViewHolder.mIvFront);
        }

        if (value.getId_photo_back() != null) {
            GlideUtils.getInstance().load(mActivity, value.getId_photo_back(), mViewHolder.mIvReverse);
        }

        if (value.getId_photo_hold() != null) {
            GlideUtils.getInstance().load(mActivity, value.getId_photo_back(), mViewHolder.mIvHold);
        }

        if(value.getDrive_photo()!=null){
            GlideUtils.getInstance().load(mActivity,value.getDrive_photo(),mViewHolder.mIvDrive);
        }

        mRlDriver.refreshComplete();
        mDriverDetailAdapter.setNewData(value.getDriver_status());
    }

    //适配器回调
    @Override
    public void convert(BaseViewHolder helper, String item) {

    }

    /**
     * 删除回调
     */
    @Override
    public void onConfirmClick() {
        deleteDriver();
    }

    private void deleteDriver() {
        Observable<BaseHttpResult> observable = mAssetApi.postDeleteDriver(mId, App.getUserId());

        HttpMethod.getInstance().getNetData(observable, new ProgressSubscriber<BaseHttpResult>(mActivity) {
            @Override
            protected void getDisposable(Disposable d) {
                mDisposables.add(d);
            }

            @Override
            public void onNext(BaseHttpResult value) {
                ShowToast(value.getMessage());
                EventBus.getDefault().post(new RefreshAssetListEntity(2));
                finish();
            }
        });
    }

    @OnClick({R.id.tv_edit, R.id.tv_del})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_edit:
                //showActivity(DriverEditActivity.class);
                Intent intent=new Intent(mActivity,DriverEditActivity.class);
                intent.putExtra("data",mDriverDetail);
                intent.putExtra("id",mId);
                startActivity(intent);
                break;
            case R.id.tv_del:
                mCommonDialog.show(getSupportFragmentManager(), "del");
                break;
            case R.id.iv_front:
                //showActivity(ViewVehicleLargeMapActivity.class);
                mViewVehicleLargeMapDialog.show(getSupportFragmentManager(), "dialog");
                break;
        }
    }

    public static void openActivity(Context context, String id) {
        Intent intent = new Intent(context, DriverDetailActivity.class);
        intent.putExtra("id", id);
        context.startActivity(intent);
    }

    static class ViewHolder {
        @BindView(R.id.tv_name)
        TextView mTvName;
        @BindView(R.id.tv_mobile)
        TextView mTvMobile;
        @BindView(R.id.tv_password)
        TextView mTvPassword;
        @BindView(R.id.tv_out_num)
        TextView mTvOutNum;
        @BindView(R.id.iv_front)
        ImageView mIvFront;
        @BindView(R.id.iv_reverse)
        ImageView mIvReverse;
        @BindView(R.id.iv_hold)
        ImageView mIvHold;
        @BindView(R.id.iv_drive)
        ImageView mIvDrive;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
