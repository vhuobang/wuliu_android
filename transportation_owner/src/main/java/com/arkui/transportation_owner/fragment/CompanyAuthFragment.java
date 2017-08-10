package com.arkui.transportation_owner.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.arkui.fz_tools._interface.PublicInterface;
import com.arkui.fz_tools._interface.UploadingPictureInterface;
import com.arkui.fz_tools.dialog.AddressPicker;
import com.arkui.fz_tools.dialog.CommonDialog;
import com.arkui.fz_tools.entity.City;
import com.arkui.fz_tools.entity.UpLoadEntity;
import com.arkui.fz_tools.listener.OnConfirmClick;
import com.arkui.fz_tools.mvp.AuthenticationPresenter;
import com.arkui.fz_tools.mvp.BaseMvpPhotoFragment;
import com.arkui.fz_tools.mvp.UploadingPicturePresenter;
import com.arkui.fz_tools.utils.GlideUtils;
import com.arkui.transportation_owner.R;
import com.arkui.transportation_owner.base.App;
import com.arkui.transportation_owner.utils.LoadCityData;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.functions.Consumer;

/**
 * 基于基类的Fragment
 */
public class CompanyAuthFragment extends BaseMvpPhotoFragment implements UploadingPictureInterface, PublicInterface, AddressPicker.OnEnsureClickListener {

    @BindView(R.id.et_name)
    EditText mEtName;
    @BindView(R.id.tv_address)
    TextView mTvAddress;
    @BindView(R.id.tv_detail_address)
    EditText mTvDetailAddress;
    @BindView(R.id.et_business_license)
    EditText mEtBusinessLicense;
    @BindView(R.id.et_license_name)
    EditText mEtLicenseName;
    @BindView(R.id.et_license_number)
    EditText mEtLicenseNumber;
    @BindView(R.id.iv_license_pic)
    ImageView mIvLicensePic;
    @BindView(R.id.iv_license_pic2)
    ImageView mIvLicensePic2;
    private CommonDialog mCommonDialog;
    private UploadingPicturePresenter mUploadingPicturePresenter;
    private AuthenticationPresenter mAuthenticationPresenter;
    private AddressPicker mAddressPicker;
    private List<City> mCityList;
    private int mType;
    private String mPath0 = null;
    private String mPath1 = null;

    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        return inflater.inflate(R.layout.fragment_company_auth, container, false);
    }

    @Override
    protected void initView(View parentView) {
        super.initView(parentView);
        ButterKnife.bind(this, parentView);
        mCommonDialog = new CommonDialog();
        mCommonDialog.setTitle("个人认证").setContent("个人认证信息已提交到后台进行审核，请耐心等待！").setNoCancel();
        mCommonDialog.setConfirmClick(new OnConfirmClick() {
            @Override
            public void onConfirmClick() {
                getActivity().finish();
            }
        });
        mAddressPicker = new AddressPicker();
        //初始化其数据
        LoadCityData.initData(mContext, new Consumer<List<City>>() {
            @Override
            public void accept(List<City> cityList) throws Exception {
                mCityList = cityList;
                mAddressPicker.setCities(mCityList);
            }
        });
        mAddressPicker.setOnEnsureClickListener(this);
    }

    @OnClick({R.id.iv_name_clear, R.id.ll_address, R.id.iv_detail_address_clean, R.id.iv_business_license_clean, R.id.iv_license_name_clean, R.id.iv_license_number_clean, R.id.iv_license_pic, R.id.iv_license_pic2, R.id.bt_submit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_name_clear:
                mEtName.setText("");
                break;
            case R.id.ll_address:
                mAddressPicker.show(getFragmentManager(), "address");
                break;
            case R.id.iv_detail_address_clean:
                mTvDetailAddress.setText("");
                break;
            case R.id.iv_business_license_clean:
                mEtBusinessLicense.setText("");
                break;
            case R.id.iv_license_name_clean:
                mEtLicenseName.setText("");
                break;
            case R.id.iv_license_number_clean:
                mEtLicenseNumber.setText("");
                break;
            case R.id.iv_license_pic:
                mType = 0;
                showPicturePicker();
                break;
            case R.id.iv_license_pic2:
                mType = 1;
                showPicturePicker();
                break;
            case R.id.bt_submit:
                submit();
                break;
        }
    }

    //提交认证
    private void submit() {
        String name = mEtName.getText().toString().trim();
        String address = mTvAddress.getText().toString().trim();
        String detailAddress = mTvDetailAddress.getText().toString().trim();
        String businessLicense = mEtBusinessLicense.getText().toString().trim();
        String licenseName = mEtLicenseName.getText().toString().trim();
        String licenseNumber = mEtLicenseNumber.getText().toString().trim();
        mAuthenticationPresenter.postCompanyAuth(App.getUserId(), name, address, detailAddress, businessLicense, licenseName, licenseNumber, mPath0, mPath1);
    }

    @Override
    public void initPresenter() {
        mUploadingPicturePresenter = new UploadingPicturePresenter(this, getActivity());
        mAuthenticationPresenter = new AuthenticationPresenter(getActivity(), this);
    }

    @Override
    protected void onCrop(String path) {
        mUploadingPicturePresenter.upPicture(path, "Avatar");
    }

    @Override
    public void onUploadingSuccess(UpLoadEntity upLoadEntity) {
        switch (mType) {
            case 0:
                mPath0 = upLoadEntity.getOriImg();
                GlideUtils.getInstance().load(this, upLoadEntity.getOriImg(), mIvLicensePic);
                break;
            case 1:
                mPath1 = upLoadEntity.getOriImg();
                GlideUtils.getInstance().load(this, upLoadEntity.getOriImg(), mIvLicensePic2);
                break;
        }
    }

    //认证操作成功
    @Override
    public void onSuccess() {
        mCommonDialog.show(getFragmentManager(), "auth");
    }

    //认证操作失败
    @Override
    public void onFail(String message) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mUploadingPicturePresenter != null && mAuthenticationPresenter != null) {
            mUploadingPicturePresenter.onDestroy();
            mAuthenticationPresenter.onDestroy();
        }

    }

    @Override
    public void onCityClick(String city) {
        mTvAddress.setText(city);
    }
}
