package com.arkui.transportation.fragment;

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
import com.arkui.fz_tools.ui.BaseFragment;
import com.arkui.fz_tools.utils.GlideUtils;
import com.arkui.transportation.R;
import com.arkui.transportation.base.App;
import com.arkui.transportation.utils.LoadCityData;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.functions.Consumer;

/**
 * 基于基类的Fragment
 */
public class PersonalAuthFragment extends BaseMvpPhotoFragment implements UploadingPictureInterface, PublicInterface, AddressPicker.OnEnsureClickListener  {

    @BindView(R.id.iv_pic1)
    ImageView mIvPic1;
    @BindView(R.id.iv_pic2)
    ImageView mIvPic2;
    @BindView(R.id.iv_pic3)
    ImageView mIvPic3;
    @BindView(R.id.tv_name)
    EditText mTvName;
    @BindView(R.id.tv_address)
    TextView mTvAddress;
    @BindView(R.id.tv_detail_address)
    EditText mTvDetailAddress;
    @BindView(R.id.et_number)
    EditText mEtNumber;
    private CommonDialog mCommonDialog;
    private UploadingPicturePresenter mUploadingPicturePresenter;
    private AuthenticationPresenter mAuthenticationPresenter;
    private String mPath0=null;
    private String mPath1=null;
    private String mPath2=null;
    private AddressPicker mAddressPicker;
    private List<City> mCityList;
    private int mType;
    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        return inflater.inflate(R.layout.fragment_personal_auth, container, false);
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

        setAspectX(3);
        setAspectY(2);
        mAddressPicker = new AddressPicker();
        //初始化其数据
        LoadCityData.initData(mContext,new Consumer<List<City>>() {
            @Override
            public void accept(List<City> cityList) throws Exception {
                mCityList = cityList;
                mAddressPicker.setCities(mCityList);
            }
        });
        mAddressPicker.setOnEnsureClickListener(this);
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

    @OnClick({R.id.iv_pic1, R.id.iv_pic2, R.id.iv_pic3, R.id.bt_submit, R.id.iv_name_clear, R.id.iv_detail_address, R.id.iv_number_clear,R.id.ll_address})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_pic1:
                mType = 1;
                showPicturePicker();
                break;
            case R.id.iv_pic2:
                mType = 2;
                showPicturePicker();
                break;
            case R.id.iv_pic3:
                mType = 3;
                showPicturePicker();
                break;
            case R.id.bt_submit:
                submit();
                break;
            case R.id.iv_name_clear:
                mTvName.setText("");
                break;
            case R.id.iv_detail_address:
                mTvDetailAddress.setText("");
                break;
            case R.id.iv_number_clear:
                mEtNumber.setText("");
                break;
            case R.id.ll_address:
                if(mCityList==null)
                    return;
                mAddressPicker.show(getFragmentManager(),"city");
                break;
        }
    }

    /**
     * 提交个人认证
     */
    private void submit() {
        String name = mTvName.getText().toString().trim();
        String address = mTvAddress.getText().toString().trim();
        String detailAddress = mTvDetailAddress.getText().toString().trim();
        String number= mEtNumber.getText().toString().trim();
        mAuthenticationPresenter.postPersonalAuth(App.getUserId(),name,address,detailAddress,number,mPath0,mPath1,mPath2,null);
    }


    @Override
    public void onUploadingSuccess(UpLoadEntity upLoadEntity) {
        switch (mType) {
            case 1:
                mPath0= upLoadEntity.getOriImg();
                GlideUtils.getInstance().load(this, upLoadEntity.getOriImg(), mIvPic1);
                break;
            case 2:
                mPath1= upLoadEntity.getOriImg();
                GlideUtils.getInstance().load(this, upLoadEntity.getOriImg(), mIvPic2);
                break;
            case 3:
                mPath2= upLoadEntity.getOriImg();
                GlideUtils.getInstance().load(this, upLoadEntity.getOriImg(), mIvPic3);
                break;
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mAuthenticationPresenter != null) {
            mAuthenticationPresenter.onDestroy();
        }
    }

    @Override
    public void onSuccess() {
        mCommonDialog.show(getFragmentManager(),"onSuccess");
    }

    @Override
    public void onFail(String message) {

    }

    @Override
    public void onCityClick(String city) {
        mTvAddress.setText(city);
    }
}
