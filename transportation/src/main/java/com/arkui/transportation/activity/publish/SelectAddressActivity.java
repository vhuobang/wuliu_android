package com.arkui.transportation.activity.publish;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.arkui.fz_tools.dialog.AddressPicker;
import com.arkui.fz_tools.entity.City;
import com.arkui.fz_tools.ui.BaseActivity;
import com.arkui.transportation.R;
import com.arkui.transportation.utils.LoadCityData;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;


public class SelectAddressActivity extends BaseActivity {

    private AddressPicker mAddressPicker;
    List<City> mCityList;
    private Disposable mDisposable;

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
        /*  @Override
          public void call(List<City> cityList) {
              mCityList=cityList;
              mAddressPicker.setCities(mCityList);
          }*/
        mDisposable = LoadCityData.initData(mActivity, new Consumer<List<City>>() {
            @Override
            public void accept(List<City> cityList) throws Exception {
                mCityList = cityList;
                mAddressPicker.setCities(mCityList);
            }

          /*  @Override
            public void call(List<City> cityList) {
                mCityList=cityList;
                mAddressPicker.setCities(mCityList);
            }*/
        });

    }

    @Override
    public void initView() {
        super.initView();
        ButterKnife.bind(this);
    }

    public static void openActivity(Context context, int type) {
        Intent intent = new Intent(context, SelectAddressActivity.class);
        intent.putExtra("type", type);
        context.startActivity(intent);
    }

    @OnClick({R.id.tl_city, R.id.tl_detail})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tl_city:
                if(mCityList==null)
                    return;
                mAddressPicker.show(getSupportFragmentManager(),"city");
                break;
            case R.id.tl_detail:
                showActivity(EditDetailedAddressActivity.class);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mDisposable.isDisposed()){
            mDisposable.dispose();
        }
    }
}
