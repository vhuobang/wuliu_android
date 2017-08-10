package com.arkui.transportation_owner.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.arkui.fz_tools._interface.PublicInterface;
import com.arkui.fz_tools._interface.UploadingPictureInterface;
import com.arkui.fz_tools.dialog.CommonDialog;
import com.arkui.fz_tools.entity.UpLoadEntity;
import com.arkui.fz_tools.mvp.AuthenticationPresenter;
import com.arkui.fz_tools.mvp.BaseMvpPhotoFragment;
import com.arkui.fz_tools.mvp.UploadingPicturePresenter;
import com.arkui.fz_tools.utils.GlideUtils;
import com.arkui.transportation_owner.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 基于基类的Fragment
 */
public class PersonalAuthFragment extends BaseMvpPhotoFragment implements UploadingPictureInterface, PublicInterface {
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
    int mType;
    private AuthenticationPresenter mAuthenticationPresenter;

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

        mUploadingPicturePresenter = new UploadingPicturePresenter(this, getActivity());
        setAspectX(3);
        setAspectY(2);
    }

    @Override
    public void initPresenter() {
        mAuthenticationPresenter = new AuthenticationPresenter(getActivity(),this);
    }

    @Override
    protected void onCrop(String path) {
        Log.e("TAG", path);
        mUploadingPicturePresenter.upPicture(path, "Avatar");
    }

    @OnClick({R.id.iv_pic1, R.id.iv_pic2, R.id.iv_pic3, R.id.bt_submit, R.id.iv_name_clear, R.id.iv_detail_address, R.id.iv_number_clear})
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
                //mCommonDialog.show(getFragmentManager(), "auth");
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
        }
    }

    /**
     * 提交个人认证
     */
    private void submit() {

    }


    @Override
    public void onUploadingSuccess(UpLoadEntity upLoadEntity) {
        switch (mType) {
            case 1:
                GlideUtils.getInstance().load(this, upLoadEntity.getOriImg(), mIvPic1);
                break;
            case 2:
                GlideUtils.getInstance().load(this, upLoadEntity.getOriImg(), mIvPic2);
                break;
            case 3:
                GlideUtils.getInstance().load(this, upLoadEntity.getOriImg(), mIvPic3);
                break;
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if(mAuthenticationPresenter!=null){
            mAuthenticationPresenter.onDestroy();
        }
    }

    @Override
    public void onSuccess() {

    }

    @Override
    public void onFail(String message) {

    }
}
