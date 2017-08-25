package com.arkui.transportation_owner.activity.my;

import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.arkui.fz_tools._interface.UploadingPictureInterface;
import com.arkui.fz_tools._interface.UserEditInterface;
import com.arkui.fz_tools.entity.UpLoadEntity;
import com.arkui.fz_tools.entity.UserEntity;
import com.arkui.fz_tools.mvp.UploadingPicturePresenter;
import com.arkui.fz_tools.mvp.UserEditPresenter;
import com.arkui.fz_tools.ui.BasePhotoActivity;
import com.arkui.fz_tools.utils.GlideUtils;
import com.arkui.transportation_owner.R;
import com.arkui.transportation_owner.base.App;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MyAvatarActivity extends BasePhotoActivity implements UploadingPictureInterface, UserEditInterface {


    @BindView(R.id.iv_avatar)
    ImageView ivAvatar;
    private UploadingPicturePresenter uploadingPicturePresenter;
    UserEditPresenter  mUserEditPresenter;
    @Override
    public void setRootView() {
        setContentView(R.layout.activity_my_avatar);
        setTitle("修改头像");
        setRightIcon(R.mipmap.xiugai);
    }

    @Override
    public void initView() {
        super.initView();
        ButterKnife.bind(this);
        uploadingPicturePresenter = new UploadingPicturePresenter(this, this);
        mUserEditPresenter = new UserEditPresenter(this,this);
        GlideUtils.getInstance().load(this,App.getUserEntity().getAvatar(),ivAvatar);
    }


    @Override
    protected void onCrop(String path) {
        Log.e("haha", "onCrop: " + path);
        uploadingPicturePresenter.upPicture(path, "Avatar");

    }

    @Override
    protected void onRightClick() {
        super.onRightClick();
        showPicturePicker(true);
    }

    /**
     * 上传成功的回调
     *
     * @param upLoadEntity
     */
    @Override
    public void onUploadingSuccess(UpLoadEntity upLoadEntity) {
        UserEntity userEntity = App.getUserEntity();
        userEntity.setAvatar(upLoadEntity.getOriImg());
        mUserEditPresenter.getUserEdit(userEntity.getId(),userEntity.getNickname(),upLoadEntity.getOriImg(),userEntity.getQq());
        GlideUtils.getInstance().load(MyAvatarActivity.this,upLoadEntity.getOriImg(),ivAvatar);
        App.setUserEntity(userEntity);
    }

    @Override
    public void onSuccess(UserEntity userEntity) {
        Toast.makeText(mActivity,"修改头像成功",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFail(String message) {

    }
}