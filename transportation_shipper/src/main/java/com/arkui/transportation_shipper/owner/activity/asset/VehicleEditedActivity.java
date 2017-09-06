package com.arkui.transportation_shipper.owner.activity.asset;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.arkui.fz_net.entity.BaseHttpResult;
import com.arkui.fz_net.http.HttpMethod;
import com.arkui.fz_net.http.HttpResultFunc;
import com.arkui.fz_net.http.RetrofitFactory;
import com.arkui.fz_net.subscribers.ProgressSubscriber;
import com.arkui.fz_tools._interface.UploadingPictureInterface;
import com.arkui.fz_tools.dialog.SelectPicturePicker;
import com.arkui.fz_tools.dialog.SelectTypePicker;
import com.arkui.fz_tools.entity.UpLoadEntity;
import com.arkui.fz_tools.listener.OnVehicleTypeClickListener;
import com.arkui.fz_tools.mvp.UploadingPicturePresenter;
import com.arkui.fz_tools.ui.BaseActivity;
import com.arkui.fz_tools.ui.BasePhotoActivity;
import com.arkui.fz_tools.utils.AppManager;
import com.arkui.fz_tools.utils.GlideUtils;
import com.arkui.transportation_shipper.R;
import com.arkui.transportation_shipper.common.api.AssetApi;
import com.arkui.transportation_shipper.common.base.App;
import com.arkui.transportation_shipper.common.dialog.SelectVehicleModelDialog;
import com.arkui.transportation_shipper.common.entity.RefreshAssetListEntity;
import com.arkui.transportation_shipper.common.entity.VehicleDetailEntity;
import com.arkui.transportation_shipper.common.entity.VehicleModelEntity;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;


public class VehicleEditedActivity extends BasePhotoActivity implements OnVehicleTypeClickListener, UploadingPictureInterface, SelectVehicleModelDialog.OnSelectVehicleModelListener {

    @BindView(R.id.tv_vehicle_model)
    TextView mTvVehicleModel;
    @BindView(R.id.iv_front)
    ImageView mIvFront;
    @BindView(R.id.iv_driving_license)
    ImageView mIvDrivingLicense;
    @BindView(R.id.et_license_plate)
    EditText mEtLicensePlate;
    //private SelectPicturePicker mSelectPicturePicker;
    //private SelectTypePicker mSelectVehicleTypePicker;
    private VehicleDetailEntity.TruckDetailBean mTruckDetail;
    private String mPath1 = null;
    private String mPath2 = null;
    private String mItemId = null;
    private UploadingPicturePresenter mUploadingPicturePresenter;
    private AssetApi mAssetApi;
    private int mType = -1;
    private SelectVehicleModelDialog mSelectVehicleModelDialog;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_vehicle_edited);
        setTitle("车辆编辑");
    }

    @Override
    public void initView() {
        super.initView();
        ButterKnife.bind(this);
        initDialog();
    }

    @Override
    public void initData() {
        super.initData();
        setUiData();
        mUploadingPicturePresenter = new UploadingPicturePresenter(this, this);
        mAssetApi = RetrofitFactory.createRetrofit(AssetApi.class);
        //获取车型
        Observable<List<VehicleModelEntity>> observable = mAssetApi.postCarType().map(new HttpResultFunc<List<VehicleModelEntity>>());

        HttpMethod.getInstance().getNetData(observable, new ProgressSubscriber<List<VehicleModelEntity>>(mActivity, false) {
            @Override
            protected void getDisposable(Disposable d) {
                mDisposables.add(d);
            }

            @Override
            public void onNext(List<VehicleModelEntity> value) {
                mSelectVehicleModelDialog.setVehicleModelList(value);
            }
        });
    }

    private void setUiData() {
        mTruckDetail = getIntent().getParcelableExtra("data");
        mEtLicensePlate.setText(mTruckDetail.getLicense_plate());
        mTvVehicleModel.setText(mTruckDetail.getType());
        mPath1 = mTruckDetail.getTruck_poto();
        mPath2 = mTruckDetail.getDriving_license_photo();
        GlideUtils.getInstance().load(mActivity, mTruckDetail.getTruck_poto(), mIvFront);
        GlideUtils.getInstance().load(mActivity, mTruckDetail.getDriving_license_photo(), mIvDrivingLicense);
    }

    private void initDialog() {
        // mSelectPicturePicker = new SelectPicturePicker();
        /*mSelectVehicleTypePicker = new SelectTypePicker();
        List<String> list = new ArrayList<>();
        list.add("车型一");
        list.add("车型二");
        list.add("车型三");
        list.add("车型四");
        list.add("车型五");
        mSelectVehicleTypePicker.setData(list).setTitle("车辆选择");
        mSelectVehicleTypePicker.setOnTypeClickListener(this);*/
        mSelectVehicleModelDialog = new SelectVehicleModelDialog();
        mSelectVehicleModelDialog.setOnSelectVehicleModelListener(this);
    }

    @OnClick({R.id.tv_vehicle_model, R.id.iv_front, R.id.iv_driving_license, R.id.tv_complete})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_vehicle_model:
                mSelectVehicleModelDialog.showDialog(this, "model");
                break;
            case R.id.iv_front:
                mType = 1;
                showPicturePicker(true);
                // mSelectPicturePicker.show(getSupportFragmentManager(), "front");
                break;
            case R.id.iv_driving_license:
                mType = 2;
                showPicturePicker(true);
                // mSelectPicturePicker.show(getSupportFragmentManager(), "license");
                break;
            case R.id.tv_complete:
                editVehicle();
                break;
        }
    }

    private void editVehicle() {
        String licensePlate = mEtLicensePlate.getText().toString();
        if (TextUtils.isEmpty(licensePlate)) {
            ShowToast("请输入车牌号");
            return;
        }

        String type = mTvVehicleModel.getText().toString();
        if (mItemId == null) {
            ShowToast("请选择车型");
            return;
        }

        if (mPath1 == null) {
            ShowToast("请上传车辆正面照");
            return;
        }

        if (mPath2 == null) {
            ShowToast("请上传行驶证照");
            return;
        }

        Map<String, Object> parameter = new HashMap<>();

        parameter.put("user_id", App.getUserId());
        parameter.put("license_plate", licensePlate);
        parameter.put("type", type);
        parameter.put("truck_poto", mPath1);
        parameter.put("driving_license_photo", mPath2);
        parameter.put("truck_id", mTruckDetail.getId());

        Observable<BaseHttpResult> observable = mAssetApi.postEditVehicle(parameter);
        HttpMethod.getInstance().getNetData(observable, new ProgressSubscriber<BaseHttpResult>(mActivity) {
            @Override
            protected void getDisposable(Disposable d) {
                mDisposables.add(d);
            }

            @Override
            public void onNext(BaseHttpResult value) {
                //通知列表刷新数据
                ShowToast(value.getMessage());
                EventBus.getDefault().post(new RefreshAssetListEntity(1));
                AppManager.getAppManager().finishActivity(VehicleDetailsActivity.class);
                finish();
            }
        });
    }

    @Override
    protected void onCrop(String path) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mUploadingPicturePresenter != null) {
            mUploadingPicturePresenter.onDestroy();
        }
    }

    /**
     * 车型选择回调
     *
     * @param item
     */
    @Override
    public void OnVehicleTypeClick(String item, int pos) {
        mTvVehicleModel.setText(item);
    }

    @Override
    public void onUploadingSuccess(UpLoadEntity upLoadEntity) {
        GlideUtils.getInstance().load(mActivity, upLoadEntity.getOriImg(), mType == 1 ? mIvFront : mIvDrivingLicense);
        if (mType == 1) {
            mPath1 = upLoadEntity.getOriImg();
        } else {
            mPath2 = upLoadEntity.getOriImg();
        }
    }

    @Override
    public void selectVehicleModel(String itemText, String itemId) {
        mTvVehicleModel.setText(itemText);
        mItemId = itemId;
    }
}
