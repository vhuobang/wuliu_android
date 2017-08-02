package com.arkui.fz_tools.ui;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;

import com.arkui.fz_tools.entity.SelectPicEntity;
import com.arkui.fz_tools.permission.PermissionListener;
import com.arkui.fz_tools.permission.SuperPermission;
import com.arkui.fz_tools.utils.LogUtil;
import com.arkui.fz_tools.utils.TimeUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Administrator on 2015/12/9 0009.
 */
public abstract class BasePhotoActivity extends BaseActivity implements PermissionListener {

    protected final int REQUEST_CAMERA = 1121;
    protected final int REQUEST_PHOTO = 1122;
    protected final int REQUEST_CROP = 1123;
    private File directory;
    private Uri uri;
    private boolean isCrop;
    private List<File> temps;
    public List<SelectPicEntity> picEntityList;

    private final int REQUEST_CAMERA_BIG = 233;
    private final int REQUEST_PHOTO_BIG = 234;
    private final int REQUEST_CROP_BIG = 235;
    private Uri uri_big;
    private File externalFilesDir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // directory = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/DCIM/Camera");
        externalFilesDir = getApplicationContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        directory=externalFilesDir;
        if (!directory.exists()) {
            directory.mkdirs();
        }
        temps = new ArrayList<>();
    }

    @Override
    public void initView() {
        super.initView();
        picEntityList = new ArrayList<>();
    }

    protected void useCamera(boolean isCrop) {
        this.isCrop = isCrop;
        File file = new File(directory, "IMG_" + TimeUtil.getCurTime("yyyyMMdd_HHmmss") + ".jpg");
        uri = Uri.fromFile(file);
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        startActivityForResult(intent, REQUEST_CAMERA);
    }


    protected void useCameraBig(boolean isCrop) {
        this.isCrop = isCrop;
        File file = new File(externalFilesDir, "IMG_" + TimeUtil.getCurTime("yyyyMMdd_HHmmss") + ".jpg");
        uri_big = Uri.fromFile(file);
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri_big);
        startActivityForResult(intent, REQUEST_CAMERA_BIG);
    }

    protected void selectPhoto(boolean isCrop) {
        this.isCrop = isCrop;
        Intent intent = new Intent(Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, REQUEST_PHOTO);
    }

    protected void selectPhotoBig(boolean isCrop) {
        this.isCrop = isCrop;
        Intent intent = new Intent(Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, REQUEST_PHOTO_BIG);
    }

    private void crop(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        // crop为true是设置在开启的intent中设置显示的view可以剪裁
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX,outputY 是剪裁图片的宽高
        intent.putExtra("outputX", 300);
        intent.putExtra("outputY", 300);
        intent.putExtra("return-data", true);
        intent.putExtra("noFaceDetection", true);
        startActivityForResult(intent, REQUEST_CROP);
    }


    private void cropBig(Uri uri) {

        File file = new File(externalFilesDir, "IMG_" + TimeUtil.getCurTime("yyyyMMdd_HHmmss") + ".jpg");
        uri_big = Uri.fromFile(file);

        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        // crop为true是设置在开启的intent中设置显示的view可以剪裁
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX,outputY 是剪裁图片的宽高
        intent.putExtra("outputX", 500);
        intent.putExtra("outputY", 500);
        intent.putExtra("return-data", false);
        intent.putExtra("noFaceDetection", true);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri_big);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        startActivityForResult(intent, REQUEST_CROP_BIG);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case REQUEST_CAMERA:
                    if (isCrop) {
                        try {
                            temps.add(new File(new URI(uri.toString())));
                        } catch (URISyntaxException e) {
                            e.printStackTrace();
                        }
                        crop(uri);
                    } else {
                        onSelectPic(uri, getUriPath(uri));
                    }
                    break;
                case REQUEST_PHOTO:
                    uri = data.getData();
                    if (isCrop) {
                        crop(uri);
                    } else {
                        onSelectPic(uri, getUriPath(uri));
                    }
                    break;
                case REQUEST_CROP:
                    Bitmap bitmap = data.getParcelableExtra("data");
                    File cropFile = new File(directory, "temp" + TimeUtil.getCurTime("yyyyMMdd_HHmmss") + ".jpg");
                    saveBitmap(bitmap, cropFile);
                    String path = cropFile.getAbsolutePath();
                    temps.add(cropFile);
                    onCrop(bitmap, path);
                    break;
                case REQUEST_PHOTO_BIG:
                    uri = data.getData();
                    if (isCrop) {
                        cropBig(uri);
                    } else {
                        onSelectPic(uri, getUriPath(uri));
                    }

                    break;
                case REQUEST_CROP_BIG:
                    if (uri_big != null) {
                        Bitmap bitmap2 = decodeUriAsBitmap(uri_big);//decode bitmap
                        onCrop(bitmap2, getUriPath(uri_big));
                        try {
                            temps.add(new File(new URI(uri_big.toString())));
                        } catch (URISyntaxException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case REQUEST_CAMERA_BIG:
                    if (isCrop) {
                        try {
                            temps.add(new File(new URI(uri_big.toString())));
                            cropBig(uri_big);
                        } catch (URISyntaxException e) {
                            e.printStackTrace();
                        }
                    } else {
                        onSelectPic(uri_big, getUriPath(uri_big));
                    }
                    break;
            }
        }
    }


    private Bitmap decodeUriAsBitmap(Uri uri) {
        Bitmap bitmap = null;
        try {
            bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(uri));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
        return bitmap;
    }

    protected void onSelectPic(Uri uri, String filePath) {

    }

    protected void onCrop(Bitmap bitmap, String path) {

    }

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

    public void showPic(Uri uri) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(uri, "image/*");
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        for (File file : temps) {
            file.delete();
        }
    }


    // 显示缺失权限提示
    public void showMissingPermissionDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final AlertDialog alertDialog = builder.create();

        builder.setMessage("当前应用缺少必要权限。\n\n请点击\"设置\"-\"权限\"-打开所需权限。\n\n最后点击两次后退按钮，即可返回。");
        // 拒绝, 退出应用
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                alertDialog.dismiss();
            }
        });

        builder.setPositiveButton("设置", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startAppSettings();
            }
        });

        builder.show();
    }

    // 启动应用的设置
    public void startAppSettings() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.parse("package:" + this.getPackageName()));
        startActivity(intent);
    }

    public void getPermission() {
        SuperPermission.with(this)
                .requestCode(101)
                .permission(Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.CAMERA)
                .send();
    }

    /*@Override
    public void onSucceed(int requestCode) {
    }
*/
    @Override
    public void onFailed(int requestCode) {
        ShowToast("获取权限失败");
        new AlertDialog.Builder(this)
                .setTitle("友好提醒")
                .setMessage("没有拍照权限将不能为您拍照上传头像，请把拍照权限赐给我吧！")
                .setPositiveButton("好，给你", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        // 用户同意继续申请。
                        startAppSettings();
                    }
                })
                .setNegativeButton("我拒绝", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        // 用户拒绝申请。
                    }
                }).show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        SuperPermission.onRequestPermissionsResult(this, requestCode, permissions, grantResults, this);
    }
}