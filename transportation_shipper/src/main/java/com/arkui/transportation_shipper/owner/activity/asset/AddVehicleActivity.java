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
import com.arkui.fz_tools.entity.UpLoadEntity;
import com.arkui.fz_tools.listener.OnVehicleTypeClickListener;
import com.arkui.fz_tools.mvp.UploadingPicturePresenter;
import com.arkui.fz_tools.ui.BasePhotoActivity;
import com.arkui.fz_tools.utils.GlideUtils;
import com.arkui.transportation_shipper.R;
import com.arkui.transportation_shipper.common.api.AssetApi;
import com.arkui.transportation_shipper.common.base.App;
import com.arkui.transportation_shipper.common.dialog.SelectVehicleModelDialog;
import com.arkui.transportation_shipper.common.entity.RefreshAssetListEntity;
import com.arkui.transportation_shipper.common.entity.VehicleModelEntity;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;


public class AddVehicleActivity extends BasePhotoActivity implements OnVehicleTypeClickListener, UploadingPictureInterface, SelectVehicleModelDialog.OnSelectVehicleModelListener {

    @BindView(R.id.et_license_plate)
    EditText mEtLicensePlate;
    @BindView(R.id.tv_vehicle_model)
    TextView mTvVehicleModel;
    @BindView(R.id.iv_pic)
    ImageView mIvPic;
    @BindView(R.id.iv_pic2)
    ImageView mIvPic2;
    @BindView(R.id.et_glicense_plate)
    EditText mEtGlicensePlate;
    @BindView(R.id.tv_complete)
    TextView mTvComplete;
    // private SelectPicturePicker mSelectPicturePicker;
    private SelectVehicleModelDialog mSelectVehicleModelDialog;
    private int mType = -1;
    private UploadingPicturePresenter mUploadingPicturePresenter;
    private String mPath1 = null;
    private String mPath2 = null;
    private String mItemId = null;
    private AssetApi mAssetApi;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_add_vehicle);
        setTitle("添加车辆");
    }

    @Override
    public void initView() {
        super.initView();
        ButterKnife.bind(this);
        initDialog();
        mUploadingPicturePresenter = new UploadingPicturePresenter(this, this);
//        setAspectY(100);
//        setAspectX(78);
    }

    @Override
    public void initData() {
        super.initData();
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

    private void initDialog() {
        mSelectVehicleModelDialog = new SelectVehicleModelDialog();
        mSelectVehicleModelDialog.setOnSelectVehicleModelListener(this);
    }

    @OnClick({R.id.tv_vehicle_model, R.id.tv_complete, R.id.iv_pic, R.id.iv_pic2, R.id.iv_clean, R.id.iv_delete})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_vehicle_model:
                if (mSelectVehicleModelDialog.getVehicleModelList().isEmpty())
                    return;
                mSelectVehicleModelDialog.showDialog(this, "model");
                break;
            case R.id.tv_complete:
                //添加车辆
                addVehicle();
                break;
            case R.id.iv_pic:
                mType = 1;
                showPicturePicker(true);
                break;
            case R.id.iv_pic2:
                mType = 2;
                showPicturePicker(true);
                // mSelectPicturePicker.show(getSupportFragmentManager(), "vehicle");
                break;
            case R.id.iv_clean:
                mEtLicensePlate.setText("");
                break;
            case R.id.iv_delete:
                mEtGlicensePlate.setText("");
                break;

        }
    }

    private void addVehicle() {
        String licensePlate = mEtLicensePlate.getText().toString();
        if (TextUtils.isEmpty(licensePlate)) {
            ShowToast("请输入车牌号");
            return;
        }
        String hand_car = mEtGlicensePlate.getText().toString().trim();


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
        parameter.put("hand_car",hand_car);
        parameter.put("truck_poto", mPath1);
        parameter.put("driving_license_photo", mPath2);

        Observable<BaseHttpResult> observable = mAssetApi.postTruckAdd(parameter);
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
                finish();
            }
        });
    }

    @Override
    protected void onCrop(String path) {
        mUploadingPicturePresenter.upPicture(path, "Avatar");
    }

    @Deprecated
    @Override
    public void OnVehicleTypeClick(String item, int pos) {
        mTvVehicleModel.setText(item);
    }

    @Override
    public void onUploadingSuccess(UpLoadEntity upLoadEntity) {
        GlideUtils.getInstance().load(mActivity, upLoadEntity.getOriImg(), mType == 1 ? mIvPic : mIvPic2);
        if (mType == 1) {
            mPath1 = upLoadEntity.getOriImg();
        } else {
            mPath2 = upLoadEntity.getOriImg();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mUploadingPicturePresenter != null) {
            mUploadingPicturePresenter.onDestroy();
        }
    }

    @Override
    public void selectVehicleModel(String itemText, String itemId) {
        mTvVehicleModel.setText(itemText);
        mItemId = itemId;
    }


}
