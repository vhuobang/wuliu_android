package com.arkui.transportation_shipper.owner.activity.asset;

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
import com.arkui.transportation_shipper.common.entity.RefreshAssetListEntity;
import com.arkui.transportation_shipper.common.entity.VehicleDetailEntity;
import com.arkui.transportation_shipper.owner.adapter.VehicleDetailsAdapter;
import com.arkui.transportation_shipper.owner.dialog.ViewVehicleLargeMapDialog;
import com.arkui.transportation_shipper.owner.listener.OnBindViewHolderListener;
import com.chad.library.adapter.base.BaseViewHolder;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

/*
车辆详情
 */
public class VehicleDetailsActivity extends BaseActivity implements OnBindViewHolderListener<String>, View.OnClickListener, OnConfirmClick {

    @BindView(R.id.rl_vehicle_derails)
    PullRefreshRecyclerView mVehicleDetails;
    private VehicleDetailsAdapter mVehicleDetailsAdapter;
    private ViewVehicleLargeMapDialog mViewVehicleLargeMapDialog;
    private CommonDialog mCommonDialog;
    private AssetApi mAssetApi;
    private ViewHolder mViewHolder;
    private VehicleDetailEntity.TruckDetailBean mTruckDetail;
    private String mId;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_vehicle_details);
        setTitle("车辆详情");
    }

    @Override
    public void initView() {
        super.initView();
        ButterKnife.bind(this);

        mVehicleDetails.setLinearLayoutManager();
        //CommonAdapter<String> mRlVehicleDerailsAdapter=new CommonAdapter<>(R.layout.item_vehicle_details,this);
        //mRlVehicleDerailsAdapter = CommonAdapter.getInstance(R.layout.item_vehicle_details, this);
        mVehicleDetailsAdapter=new VehicleDetailsAdapter();
        mVehicleDetails.setAdapter(mVehicleDetailsAdapter);
        mVehicleDetails.addItemDecoration(new AssetDecoration(mActivity, AssetDecoration.VERTICAL_LIST));
        mVehicleDetails.setEnablePullToRefresh(false);

        View vehicle_details_head = getLayoutInflater().inflate(R.layout.layout_vehicle_details_head, mVehicleDetails, false);
        mVehicleDetailsAdapter.addHeaderView(vehicle_details_head);
        initHeadView(vehicle_details_head);
        initDialog();
    }

    private void initHeadView(View vehicle_details_head) {
        mViewHolder = new ViewHolder(vehicle_details_head);
        mViewHolder.mIvFront.setOnClickListener(this);
        mViewHolder.mIvDrivingLicense.setOnClickListener(this);
    }

    @Override
    public void initData() {
        super.initData();
        mId = getIntent().getStringExtra("id");
        mAssetApi = RetrofitFactory.createRetrofit(AssetApi.class);
        //获取车辆详情
        getNetData(mId);
    }

    private void getNetData(String id) {
        Observable<VehicleDetailEntity> observable = mAssetApi.postVehicleDetail(id).map(new HttpResultFunc<VehicleDetailEntity>());

        HttpMethod.getInstance().getNetData(observable, new ProgressSubscriber<VehicleDetailEntity>(mActivity) {
            @Override
            protected void getDisposable(Disposable d) {
                mDisposables.add(d);
            }

            @Override
            public void onNext(VehicleDetailEntity value) {
                setUiData(value);
            }
        });
    }

    private void setUiData(VehicleDetailEntity value) {
        mTruckDetail = value.getTruck_detail();
        mViewHolder.mTvLicensePlate.setText(mTruckDetail.getLicense_plate());
        mViewHolder.mTvSingularNum.setText(mTruckDetail.getSingular_num());
        mViewHolder.mTvType.setText(mTruckDetail.getType());
        GlideUtils.getInstance().load(mActivity, mTruckDetail.getTruck_poto(), mViewHolder.mIvFront);
        GlideUtils.getInstance().load(mActivity, mTruckDetail.getDriving_license_photo(), mViewHolder.mIvDrivingLicense);

        //TODO 2017年8月30日 暂时性留个问题 因为这里数据未知 没数据
        //TODO 2017年9月5日 问题解决 但是数据会夹带Null 很奇怪 待解决
        mVehicleDetailsAdapter.setNewData(value.getTruck_status());
    }

    private void initDialog() {
        mViewVehicleLargeMapDialog = new ViewVehicleLargeMapDialog();
        mCommonDialog = new CommonDialog();
        mCommonDialog.setTitle("删除车辆").setContent("确定删除该条车辆信息吗？").setConfirmClick(this);
    }

    //适配器回调
    @Override
    public void convert(BaseViewHolder helper, String item) {

    }

    @OnClick({R.id.tv_edit, R.id.tv_del})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_edit:
                if (mTruckDetail == null)
                    return;
                //showActivity(VehicleEditedActivity.class);
                Intent intent=new Intent(mActivity,VehicleEditedActivity.class);
                intent.putExtra("data",mTruckDetail);
                startActivity(intent);
                break;
            case R.id.tv_del:
                mCommonDialog.show(getSupportFragmentManager(), "del");
                break;
            case R.id.iv_front:
                if (mTruckDetail == null)
                    return;
                //showActivity(ViewVehicleLargeMapActivity.class);
                mViewVehicleLargeMapDialog.setImgUrl(mTruckDetail.getTruck_poto()).showDialog(this, "dialog");
                break;
            case R.id.iv_driving_license:
                if (mTruckDetail == null)
                    return;
                //showActivity(ViewVehicleLargeMapActivity.class);
                mViewVehicleLargeMapDialog.setImgUrl(mTruckDetail.getDriving_license_photo()).showDialog(this, "dialog");
                break;
        }
    }

    //删除回调
    @Override
    public void onConfirmClick() {
        //删掉车辆信息
        deleteVehicle();
    }

    private void deleteVehicle() {
        Observable<BaseHttpResult> observable = mAssetApi.postDeleteVehicle(mId);

        HttpMethod.getInstance().getNetData(observable, new ProgressSubscriber<BaseHttpResult>(mActivity) {
            @Override
            protected void getDisposable(Disposable d) {
                mDisposables.add(d);
            }

            @Override
            public void onNext(BaseHttpResult value) {
                //通知刷新车辆列表
                EventBus.getDefault().post(new RefreshAssetListEntity(1));
                ShowToast(value.getMessage());
                finish();
            }
        });
    }

    static class ViewHolder {
        @BindView(R.id.tv_license_plate)
        TextView mTvLicensePlate;
        @BindView(R.id.tv_type)
        TextView mTvType;
        @BindView(R.id.tv_singular_num)
        TextView mTvSingularNum;
        @BindView(R.id.iv_front)
        ImageView mIvFront;
        @BindView(R.id.iv_driving_license)
        ImageView mIvDrivingLicense;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
