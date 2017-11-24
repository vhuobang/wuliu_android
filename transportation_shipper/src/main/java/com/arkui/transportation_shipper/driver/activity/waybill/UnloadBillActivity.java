package com.arkui.transportation_shipper.driver.activity.waybill;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.arkui.fz_net.entity.BaseHttpResult;
import com.arkui.fz_net.http.ApiException;
import com.arkui.fz_net.http.HttpMethod;
import com.arkui.fz_net.http.RetrofitFactory;
import com.arkui.fz_net.subscribers.ProgressSubscriber;
import com.arkui.fz_tools._interface.UploadingPictureInterface;
import com.arkui.fz_tools.entity.UpLoadEntity;
import com.arkui.fz_tools.mvp.UploadingPicturePresenter;
import com.arkui.fz_tools.ui.BasePhotoActivity;
import com.arkui.fz_tools.utils.AppManager;
import com.arkui.fz_tools.utils.GlideUtils;
import com.arkui.fz_tools.view.ShapeButton;
import com.arkui.transportation_shipper.R;
import com.arkui.transportation_shipper.common.api.DriverApi;
import com.arkui.transportation_shipper.driver.event.LoadEvent;
import com.arkui.transportation_shipper.owner.activity.waybill.WaybillListDetailActivity;

import org.greenrobot.eventbus.EventBus;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;


public class UnloadBillActivity extends BasePhotoActivity implements UploadingPictureInterface {

    @BindView(R.id.tv_time)
    TextView mTvTime;
    @BindView(R.id.unloading_weight)
    EditText mUnloadingWeight;
    @BindView(R.id.iv_upload_pound_list)
    ImageView mIvUploadPoundList;
    @BindView(R.id.submit)
    ShapeButton mSubmit;
    private String orderId;
    private UpLoadEntity mUpLoadEntityf;
    private DriverApi driverApi;
    private UploadingPicturePresenter uploadingPicturePresenter;
    private String carryNumber;

    public static void openActivity(Context context, String orderId,String carryNumber) {
        Intent intent = new Intent(context, UnloadBillActivity.class);
        intent.putExtra("orderId", orderId);
        intent.putExtra("carryNumber",carryNumber);
        context.startActivity(intent);
    }

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_unload_bill);
        setTitle("卸货磅单");
    }

    @Override
    public void initView() {
        super.initView();
        ButterKnife.bind(this);
        orderId = getIntent().getStringExtra("orderId");
        carryNumber = getIntent().getStringExtra("carryNumber");
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = simpleDateFormat.format(date);
        mTvTime.setText(dateString);
        uploadingPicturePresenter = new UploadingPicturePresenter(this,this);
        driverApi = RetrofitFactory.createRetrofit(DriverApi.class);

    }

    @Override
    protected void onCrop(String path) {
        uploadingPicturePresenter.upPicture(path, "Avatar");
    }


    @OnClick({R.id.iv_upload_pound_list, R.id.submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_upload_pound_list:
                showPicturePicker(true);
                break;
            case R.id.submit:
                String loadingWeight = mUnloadingWeight.getText().toString().trim();
                if (TextUtils.isEmpty(loadingWeight)){
                    Toast.makeText(UnloadBillActivity.this,"卸货吨数不能为空",Toast.LENGTH_SHORT).show();
                    return;
                }

               if ( Double.parseDouble(loadingWeight) > Double.parseDouble(carryNumber)+1){
                   Toast.makeText(UnloadBillActivity.this,"卸货吨数不能大于预装吨数1吨",Toast.LENGTH_SHORT).show();
                   return;
               }
                if (mUpLoadEntityf == null){
                    Toast.makeText(UnloadBillActivity.this,"上传磅单未成功",Toast.LENGTH_SHORT).show();
                    return;
                }
                String oriImg = mUpLoadEntityf.getOriImg();

                String upTime = (String) mTvTime.getText();

                upPoundList(loadingWeight,oriImg,upTime);
                break;
        }
    }

    private void upPoundList(String loadingWeight, String oriImg, String upTime) {
        HashMap<String,Object> map = new HashMap<>();
        map.put("order_id",orderId);
        map.put("unloading_time",upTime);
        map.put("unloading_weight",loadingWeight);
        map.put("unloading_photo",oriImg);
        Observable<BaseHttpResult> observable = driverApi.unloadingSubmit(map);
        HttpMethod.getInstance().getNetData(observable, new ProgressSubscriber<BaseHttpResult>(this) {
            @Override
            protected void getDisposable(Disposable d) {
                mDisposables.add(d);
            }

            @Override
            public void onNext(BaseHttpResult value) {
                Toast.makeText(UnloadBillActivity.this,value.getMessage(),Toast.LENGTH_SHORT).show();
                finish();
                AppManager.getAppManager().finishActivity(WaybillListDetailActivity.class);
                AppManager.getAppManager().finishActivity(DriverWaybillDetailActivity.class);
                //发送数据
                EventBus.getDefault().post(new LoadEvent());
            }

            @Override
            public void onApiError(ApiException e) {
              //  super.onApiError(e);
            }
        });

    }

    @Override
    public void onUploadingSuccess(UpLoadEntity upLoadEntity) {
        mUpLoadEntityf = upLoadEntity;
        GlideUtils.getInstance().load(UnloadBillActivity.this,upLoadEntity.getOriImg(),mIvUploadPoundList);
    }
}
