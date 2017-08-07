package com.arkui.transportation.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.arkui.fz_tools.ui.BaseFragment;
import com.arkui.transportation.R;
import com.arkui.transportation.activity.publish.MyDeliverActivity;
import com.arkui.transportation.fragment.HomeFragment;
import com.arkui.transportation.fragment.MessageFragment;
import com.arkui.transportation.fragment.MyFragment;
import com.arkui.transportation.fragment.WaybillFragment;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author nmliz 主页
 * @time 2017/6/19 10:08
 */
public class MainActivity extends AppCompatActivity {

    private long mPressedTime = 0;
    protected BaseFragment currentSupportFragment;
    private HomeFragment mHomeFragment;
    private MessageFragment mMessageFragment;
    private WaybillFragment mWaybillFragment;
    private MyFragment mMyFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mHomeFragment = new HomeFragment();

        mMessageFragment = new MessageFragment();
        mWaybillFragment = new WaybillFragment();
        mMyFragment = new MyFragment();
        changeFragment(R.id.fl_content,mHomeFragment);
    }


    @OnClick({R.id.rb_home, R.id.rb_message, R.id.rb_waybill, R.id.rb_my,R.id.iv_publish})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rb_home:
                changeFragment(R.id.fl_content,mHomeFragment);
                break;
            case R.id.rb_message:
                changeFragment(R.id.fl_content,mMessageFragment);
                break;
            case R.id.rb_waybill:
                changeFragment(R.id.fl_content,mWaybillFragment);
                break;
            case R.id.rb_my:
                changeFragment(R.id.fl_content,mMyFragment);
                break;
            case R.id.iv_publish:
                Intent intent=new Intent(this, MyDeliverActivity.class);
                startActivity(intent);
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
