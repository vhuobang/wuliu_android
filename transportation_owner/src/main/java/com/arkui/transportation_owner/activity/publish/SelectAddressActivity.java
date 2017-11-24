package com.arkui.transportation_owner.activity.publish;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.arkui.fz_tools.dialog.AddressPicker;
import com.arkui.fz_tools.entity.City;
import com.arkui.fz_tools.ui.BaseActivity;
import com.arkui.transportation_owner.R;
import com.arkui.transportation_owner.utils.LoadCityData;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.functions.Consumer;


public class SelectAddressActivity extends BaseActivity implements AddressPicker.OnEnsureClickListener {

    @BindView(R.id.tv_address)
    TextView mTvAddress;
    @BindView(R.id.tv_detail_address)
    TextView mTvDetailAddress;
    private AddressPicker mAddressPicker;
    List<City> mCityList;
    private String mCity;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_select_address);
        int type = getIntent().getIntExtra("type", -1);

        if (type == 1) {
            setTitle("装货地址");
        } else {
            setTitle("卸货地址");
        }

        mAddressPicker = new AddressPicker();

        //初始化其数据
        LoadCityData.initData(mActivity, new Consumer<List<City>>() {
            @Override
            public void accept(List<City> cityList) throws Exception {
                mCityList = cityList;
                mAddressPicker.setCities(mCityList);
            }
        });
        mAddressPicker.setOnEnsureClickListener(this);
    }

    @Override
    public void initView() {
        super.initView();
        ButterKnife.bind(this);
    }

    public static void openActivity(Activity context, int type) {
        Intent intent = new Intent(context, SelectAddressActivity.class);
        intent.putExtra("type", type);
        context.startActivityForResult(intent, type);
    }

    @OnClick({R.id.tl_city, R.id.tl_detail,R.id.bt_save})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tl_city:
                if (mCityList == null)
                    return;
                mAddressPicker.show(getSupportFragmentManager(), "city");
                break;
            case R.id.tl_detail:
                if(mCity==null){
                    ShowToast("请选择地址！");
                    return;
                }
               // showActivity(EditDetailedAddressActivity.class);
                Intent intent=new Intent(mActivity,EditDetailedAddressActivity.class);
                intent.putExtra("city",mCity);
                startActivityForResult(intent,101);
                break;
            case R.id.bt_save:
                String address = mTvAddress.getText().toString().trim();
                String detailAddress = mTvDetailAddress.getText().toString().trim();
                if(TextUtils.isEmpty(address)){
                    ShowToast("请选择地址");
                    return;
                }
                if(TextUtils.isEmpty(detailAddress)){
                    ShowToast("请选择详细地址");
                    return;
                }

                Intent intent1=new Intent();
                intent1.putExtra("address",address+" "+detailAddress);
                setResult(Activity.RESULT_OK,intent1);
                finish();
                break;
        }
    }

    @Override
    public void onCityClick(String city) {
        String[] split = city.split("-");
        if(split.length<2){
            ShowToast("请选择");
            return;
        }

        if("北京".equals( split[0])||"上海".equals( split[0])||"天津".equals( split[0])||"重庆".equals( split[0])){
            mCity = split[0];
        }else{
            mCity = split[1];
        }
        mTvAddress.setText(city);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(101==requestCode&&resultCode==Activity.RESULT_OK){
            mTvDetailAddress.setText(data.getStringExtra("address"));
        }
    }
}
