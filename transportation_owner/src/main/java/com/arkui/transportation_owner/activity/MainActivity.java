package com.arkui.transportation_owner.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.arkui.fz_tools.ui.BaseFragment;
import com.arkui.transportation_owner.R;
import com.arkui.transportation_owner.activity.publish.MyDeliverActivity;
import com.arkui.transportation_owner.fragment.LogisticsFragment;
import com.arkui.transportation_owner.fragment.MessageFragment;
import com.arkui.transportation_owner.fragment.MyFragment;
import com.arkui.transportation_owner.fragment.WaybillFragment;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    private long mPressedTime = 0;
    protected BaseFragment currentSupportFragment;
    private LogisticsFragment mLogisticsFragment;
    private MessageFragment mMessageFragment;
    private WaybillFragment mWaybillFragment;
    private MyFragment mMyFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mLogisticsFragment = new LogisticsFragment();
        mMessageFragment = new MessageFragment();
        mWaybillFragment = new WaybillFragment();
        mMyFragment = new MyFragment();
        changeFragment(R.id.fl_content, mLogisticsFragment);

        ButterKnife.bind(this);
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

    @OnClick({R.id.rb_msg, R.id.rb_asset, R.id.iv_publish, R.id.rb_order, R.id.rb_my})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rb_msg:
                changeFragment(R.id.fl_content, mLogisticsFragment);
                break;
            case R.id.rb_asset:
                changeFragment(R.id.fl_content, mMessageFragment);
                break;
            case R.id.iv_publish:
                Intent intent=new Intent(this,MyDeliverActivity.class);
                startActivity(intent);
                break;
            case R.id.rb_order:
                changeFragment(R.id.fl_content,mWaybillFragment);
                break;
            case R.id.rb_my:
                changeFragment(R.id.fl_content,mMyFragment);
                break;
        }
    }
}
