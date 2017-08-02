package com.arkui.transportation.activity.my;

import com.arkui.fz_tools.dialog.SelectPicturePicker;
import com.arkui.fz_tools.ui.BaseActivity;
import com.arkui.transportation.R;

import butterknife.ButterKnife;


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
