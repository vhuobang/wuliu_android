package com.arkui.fz_tools.ui;

import android.Manifest;
import android.content.ContentValues;
import android.content.Context;
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
import android.support.v4.content.FileProvider;
import android.widget.Toast;

import com.arkui.fz_tools.dialog.CommonDialog;
import com.arkui.fz_tools.dialog.SelectPicturePicker;
import com.arkui.fz_tools.entity.SelectPicEntity;
import com.arkui.fz_tools.listener.OnConfirmClick;
import com.arkui.fz_tools.listener.OnPictureClickListener;
import com.arkui.fz_tools.utils.LogUtil;
import com.arkui.fz_tools.utils.TimeUtil;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.functions.Consumer;


/**
 * Created by Administrator on 2015/12/9 0009.
 */
public abstract class BasePhotoActivity extends BaseActivity implements OnPictureClickListener, OnConfirmClick {

    //private Uri uri;
    private boolean isCrop;
    public List<SelectPicEntity> picEntityList;

    private final int REQUEST_CAMERA_BIG = 233;
    private final int REQUEST_PHOTO_BIG = 234;
    private final int REQUEST_CROP_BIG = 235;
    private Uri mUri;
    private File mExternalFilesDir;
    private SelectPicturePicker mSelectPicturePicker;
    private RxPermissions mRxPermissions;
    private CommonDialog mCommonDialog;
    private int mAspectX = 0;
    private int mAspectY = 0;
    // private File file;
    private File mImageFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRxPermissions = new RxPermissions(this);
        mExternalFilesDir = getApplicationContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES);

        if (!mExternalFilesDir.exists()) {
            mExternalFilesDir.mkdirs();
        }
        initDialog();
    }

    public void showPicturePicker(boolean isCrop) {
        this.isCrop = isCrop;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                    == PackageManager.PERMISSION_GRANTED) {
                mSelectPicturePicker.show(getSupportFragmentManager(), "picture");
            } else {
                mCommonDialog.show(getSupportFragmentManager(), "dialog");
            }
        } else {
            mSelectPicturePicker.show(getSupportFragmentManager(), "picture");
        }
    }

    //初始化选择图片的选择器
    private void initDialog() {
        mSelectPicturePicker = new SelectPicturePicker();
        mSelectPicturePicker.setOnPictureClickListener(this);
        mCommonDialog = new CommonDialog();
        mCommonDialog.setTitle("提示").setContent("为了上传图片需要存储权限和拍照权限，按确定继续！").setNoCancel().setConfirmClick(this);
    }

    @Override
    public void initView() {
        super.initView();
        picEntityList = new ArrayList<>();
    }

    protected void useCamera(boolean isCrop) {
        this.isCrop = isCrop;
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        mImageFile = new File(mExternalFilesDir, "IMG_" + TimeUtil.getCurTime("yyyyMMdd_HHmmss") + ".jpg");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            intent.addFlags(Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION);
            String authority = mActivity.getApplicationInfo().packageName + ".provider";
            mUri = FileProvider.getUriForFile(mActivity, authority, mImageFile);
        } else {
            mUri = Uri.fromFile(mImageFile);
        }
        intent.putExtra(MediaStore.EXTRA_OUTPUT, mUri);
        startActivityForResult(intent, REQUEST_CAMERA_BIG);
    }

    protected void selectPhoto(boolean isCrop) {
        this.isCrop = isCrop;
        Intent intent = new Intent(Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, REQUEST_PHOTO_BIG);
    }


    private void cropBig(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        File file = new File(mExternalFilesDir, "IMG_" + TimeUtil.getCurTime("yyyyMMdd_HHmmss") + ".jpg");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            intent.addFlags(Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION | Intent.FLAG_GRANT_READ_URI_PERMISSION);
            //String authority=mActivity.getApplicationInfo().packageName+".provider";
            // imageUri = FileProvider.getUriForFile(mActivity,authority,mImageFile);
        } else {
            // imageUri = Uri.fromFile(file);
        }
        mUri = Uri.fromFile(file);
        intent.setDataAndType(uri, "image/*");
        // crop为true是设置在开启的intent中设置显示的view可以剪裁
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", mAspectX);
        intent.putExtra("aspectY", mAspectY);

        //intent.putExtra("outputX", 500);
        //intent.putExtra("outputY", 500);

        intent.putExtra("return-data", false);
        intent.putExtra("noFaceDetection", true);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, mUri);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        startActivityForResult(intent, REQUEST_CROP_BIG);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case REQUEST_PHOTO_BIG:
                    mUri = data.getData();
                    if (isCrop) {
                        cropBig(mUri);
                    } else {
                        onCrop(getUriPath(mUri));
                    }
                    break;
                case REQUEST_CROP_BIG:
                    if (mUri != null) {
                        onCrop(getUriPath(mUri));
                    }
                    break;
                case REQUEST_CAMERA_BIG:
                    if (isCrop) {
                        cropBig(mUri);
                    } else {
                        onCrop(getUriPath(mUri));
                    }
                    break;
            }
        }
    }


    protected abstract void onCrop(String path);

    private void saveBitmap(Bitmap bitmap, File file) {
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    protected String getUriPath(Uri uri) {
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
            String path = cursor.getString(cursor.getColumnIndex("_data"));

            cursor.close();
            return path;
        }

        LogUtil.e(uri.getPath());
        return uri.getPath();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        File[] filesList = mExternalFilesDir.listFiles();
        for (File file : filesList) {
            file.delete();
        }
    }

    @Override
    public void onClick(int position) {
        switch (position) {
            case 0: //
                selectPhoto(isCrop);
                break;
            case 1://选图
                useCamera(isCrop);
                break;
        }
    }

    @Override
    public void onConfirmClick() {
        mRxPermissions.request(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA).subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean aBoolean) throws Exception {
                if (!aBoolean) {
                    Toast.makeText(BasePhotoActivity.this, "没有权限，无法正常上传图片哦，建议你允许！", Toast.LENGTH_SHORT).show();
                } else {
                    mSelectPicturePicker.showDialog(BasePhotoActivity.this, "picture");
                }
            }
        });
    }

    public void setAspectX(int mAspectX) {
        this.mAspectX = mAspectX;
    }

    public void setAspectY(int mAspectY) {
        this.mAspectY = mAspectY;
    }

    public static Uri getImageContentUri(Context context, File imageFile) {
        String filePath = imageFile.getAbsolutePath();
        Cursor cursor = context.getContentResolver().query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                new String[]{MediaStore.Images.Media._ID},
                MediaStore.Images.Media.DATA + "=? ",
                new String[]{filePath}, null);

        if (cursor != null && cursor.moveToFirst()) {
            int id = cursor.getInt(cursor
                    .getColumnIndex(MediaStore.MediaColumns._ID));
            Uri baseUri = Uri.parse("content://media/external/images/media");
            return Uri.withAppendedPath(baseUri, "" + id);
        } else {
            if (imageFile.exists()) {
                ContentValues values = new ContentValues();
                values.put(MediaStore.Images.Media.DATA, filePath);
                return context.getContentResolver().insert(
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
            } else {
                return null;
            }
        }
    }

}