package com.arkui.transportation_shipper.owner.activity.my;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import butterknife.ButterKnife;

import com.arkui.fz_tools.dialog.SelectPicturePicker;
import com.arkui.fz_tools.dialog.old.SelectPicDialog;
import com.arkui.fz_tools.ui.BaseActivity;
import com.arkui.transportation_shipper.R;


public class MyAvatarActivity extends BaseActivity {

    private SelectPicturePicker mSelectPicturePicker;

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

        mSelectPicturePicker = new SelectPicturePicker();
    }

    @Override
    protected void onRightClick() {
        super.onRightClick();
        mSelectPicturePicker.show(getSupportFragmentManager(),"avatar");
    }
}
