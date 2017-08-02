package com.arkui.transportation_shipper.driver.activity;

import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Toast;

import com.arkui.fz_tools.ui.BaseActivity;
import com.arkui.fz_tools.ui.BaseFragment;
import com.arkui.transportation_shipper.R;
import com.arkui.transportation_shipper.driver.fragment.DriverMyFragment;
import com.arkui.transportation_shipper.driver.fragment.DriverWaybillFragment;
import com.arkui.transportation_shipper.owner.fragment.MessageFragment;

import butterknife.ButterKnife;
import butterknife.OnClick;


public class DriverMainActivity extends BaseActivity {

    private long mPressedTime = 0;
    private MessageFragment mMessageFragment;
    protected BaseFragment currentSupportFragment;
    private DriverWaybillFragment mDriverOrderFragment;
    private DriverMyFragment mDriverMyFragment;


    @Override
    public void setRootView() {
        setContentViewNoTitle(R.layout.activity_driver_main);
    }

    @Override
    public void initView() {
        super.initView();
        ButterKnife.bind(this);
        mMessageFragment = new MessageFragment();
        mDriverOrderFragment = new DriverWaybillFragment();
        mDriverMyFragment = new DriverMyFragment();
        changeFragment(R.id.fl_content, mMessageFragment);
    }

    @OnClick({ R.id.rb_msg, R.id.rb_order, R.id.rb_my})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rb_msg:
                changeFragment(R.id.fl_content, mMessageFragment);
                break;
            case R.id.rb_order:
                changeFragment(R.id.fl_content, mDriverOrderFragment);
                break;
            case R.id.rb_my:
                changeFragment(R.id.fl_content, mDriverMyFragment);
                break;
        }
    }

    public void changeFragment(int resView, BaseFragment targetFragment) {
        if (targetFragment.equals(currentSupportFragment)) {
            return;
        }
        FragmentTransaction transaction = getSupportFragmentManager()
                .beginTransaction();
        if (!targetFragment.isAdded()) {
            transaction.add(resView, targetFragment, targetFragment.getClass()
                    .getName());
        }
        if (targetFragment.isHidden()) {
            transaction.show(targetFragment);
            targetFragment.onChange();
        }
        if (currentSupportFragment != null
                && currentSupportFragment.isVisible()) {
            transaction.hide(currentSupportFragment);
        }
        currentSupportFragment = targetFragment;
        transaction.commit();
    }

    @Override
    public void onBackPressed() {
        long mNowTime = System.currentTimeMillis();
        //获取第一次按键时间
        if ((mNowTime - mPressedTime) > 2000) {//比较两次按键时间差
            Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
            mPressedTime = mNowTime;
        } else {//退出程序
            this.finish();
        }
    }
}
