package com.arkui.transportation.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.arkui.fz_tools.ui.BaseActivity;
import com.arkui.fz_tools.ui.BaseFragment;
import com.arkui.transportation.R;
import com.arkui.transportation.activity.publish.MyDeliverActivity;
import com.arkui.transportation.fragment.HomeFragment;
import com.arkui.transportation.fragment.MessageFragment;
import com.arkui.transportation.fragment.MyFragment;
import com.arkui.transportation.fragment.WaybillFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author nmliz 主页
 * @time 2017/6/19 10:08
 */
public class MainActivity extends BaseActivity {
    @BindView(R.id.rg_root)
    RadioGroup mRgRoot;
    private long mPressedTime = 0;
    protected BaseFragment currentSupportFragment;
    private HomeFragment mHomeFragment;
    private MessageFragment mMessageFragment;
    private WaybillFragment mWaybillFragment;
    private MyFragment mMyFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentViewNoTitle(R.layout.activity_main);
        ButterKnife.bind(this);
        mHomeFragment = new HomeFragment();
        mMessageFragment = new MessageFragment();
        mWaybillFragment = new WaybillFragment();
        mMyFragment = new MyFragment();
        changeFragment(R.id.fl_content,mHomeFragment);
    }

    @Override
    public void setRootView() {

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

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        int type = intent.getIntExtra("type", -1);
        switch (type) {
            case 3:
                mRgRoot.check(R.id.rb_waybill);
                mWaybillFragment.setFragmentPosition(1);
                changeFragment(R.id.fl_content, mWaybillFragment);
                break;
        }
    }

}
