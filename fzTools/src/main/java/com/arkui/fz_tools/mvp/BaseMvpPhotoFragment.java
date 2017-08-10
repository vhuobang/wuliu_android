package com.arkui.fz_tools.mvp;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.arkui.fz_tools.dialog.CommonDialog;
import com.arkui.fz_tools.dialog.SelectPicturePicker;
import com.arkui.fz_tools.listener.OnConfirmClick;
import com.arkui.fz_tools.listener.OnPictureClickListener;
import com.arkui.fz_tools.utils.LogUtil;
import com.arkui.fz_tools.utils.TimeUtil;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.io.File;

import io.reactivex.functions.Consumer;

/**
 * Created by nmliz on 2017/8/9.
 */

public abstract class BaseMvpPhotoFragment extends BaseMvpFragment implements OnPictureClickListener, OnConfirmClick {


    private SelectPicturePicker mSelectPicturePicker;
    private RxPermissions mRxPermissions;
    private CommonDialog mCommonDialog;
    private File mExternalFilesDir;
    private final int REQUEST_CAMERA = 233;
    private final int REQUEST_PHOTO = 234;
    private final int REQUEST_CROP = 235;
    private Uri mPhotoUri;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRxPermissions = new RxPermissions(getActivity());
        mExternalFilesDir = getActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        if(mExternalFilesDir.exists()){
            mExternalFilesDir.mkdir();
        }
        initDialog();
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    //初始化选择图片的选择器
    private void initDialog() {
        mSelectPicturePicker = new SelectPicturePicker();
        mSelectPicturePicker.setOnPictureClickListener(this);
        mCommonDialog = new CommonDialog();
        mCommonDialog.setTitle("提示").setContent("为了上传图片需要存储权限和拍照权限，按确定继续！").setNoCancel().setConfirmClick(this);
    }

    @Override
    public void onClick(int position) {
        if (position == 0) {
            //选择图片
            selectPhoto();
        } else {
            //拍照
            selectCamera();
        }
    }

    protected void selectCamera() {
        File file = new File(mExternalFilesDir, "IMG_" + TimeUtil.getCurTime("yyyyMMdd_HHmmss") + ".jpg");
        mPhotoUri = Uri.fromFile(file);
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, mPhotoUri);
        startActivityForResult(intent, REQUEST_CAMERA);
    }

    public void showPicturePicker() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if(ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED &&ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA)
                    == PackageManager.PERMISSION_GRANTED ){
                mSelectPicturePicker.show(getChildFragmentManager(), "picture");
            }else{
                mCommonDialog.show(getChildFragmentManager(), "dialog");
            }
        } else {
            mSelectPicturePicker.show(getChildFragmentManager(), "picture");
        }
    }

    protected void selectPhoto() {
        Intent intent = new Intent(Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, REQUEST_PHOTO);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        File[] filesList = mExternalFilesDir.listFiles();
        for (File file : filesList) {
            file.delete();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case REQUEST_PHOTO:
                    cropPhoto(data.getData());
                    break;
                case REQUEST_CROP:
                    if (mPhotoUri != null) {
                        onCrop(getUriPath(mPhotoUri));
                    }
                    break;
                case REQUEST_CAMERA:
                    cropPhoto(mPhotoUri);
                    break;
            }
        }
    }

    /**
     * 裁切图片
     *
     * @param uri
     */
    private void cropPhoto(Uri uri) {
        File file = new File(mExternalFilesDir, "IMG_" + TimeUtil.getCurTime("yyyyMMdd_HHmmss") + ".jpg");
        mPhotoUri = Uri.fromFile(file);
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        // crop为true是设置在开启的intent中设置显示的view可以剪裁
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX,outputY 是剪裁图片的宽高
       /* intent.putExtra("outputX", 500);
        intent.putExtra("outputY", 500);*/
        intent.putExtra("return-data", false);
        intent.putExtra("noFaceDetection", true);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, mPhotoUri);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        startActivityForResult(intent, REQUEST_CROP);
    }

    protected String getUriPath(Uri uri) {
        Cursor cursor = getActivity().getContentResolver().query(uri, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
            String path = cursor.getString(cursor.getColumnIndex("_data"));
            cursor.close();
            return path;
        }

        LogUtil.e(uri.getPath());
        return uri.getPath();
    }

    protected abstract void onCrop(String path);

    @Override
    public void onConfirmClick() {
        mRxPermissions.request(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA).subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean aBoolean) throws Exception {
                if (!aBoolean) {
                    Toast.makeText(mContext, "没有权限，无法正常上传图片哦，建议你允许！", Toast.LENGTH_SHORT).show();
                } else {
                    mSelectPicturePicker.showDialog(getActivity(), "picture");
                }
            }
        });
    }

}
