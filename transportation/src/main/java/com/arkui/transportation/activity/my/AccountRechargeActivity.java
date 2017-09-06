package com.arkui.transportation.activity.my;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.arkui.fz_tools.ui.BaseActivity;
import com.arkui.fz_tools.view.ShapeButton;
import com.arkui.transportation.R;
import com.arkui.transportation.pay.alipay.PayResult;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class AccountRechargeActivity extends BaseActivity {

    @BindView(R.id.rb_zfb)
    RadioButton mRbZfb;
    @BindView(R.id.rb_wx)
    RadioButton mRbWx;
    @BindView(R.id.rb_yl)
    RadioButton mRbYl;
    @BindView(R.id.rg_root)
    RadioGroup mRgRoot;
    @BindView(R.id.bt_start)
    ShapeButton mBtStart;
    String payType ;
    private static final int SDK_PAY_FLAG = 1;
    @Override
    public void setRootView() {
        setContentView(R.layout.activity_account_recharge);
        setTitle("账户充值");
    }

    @Override
    public void initView() {
        super.initView();
        ButterKnife.bind(this);

        mRgRoot.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rb_zfb: // 支付宝支付
                        payType="zfb";

                        break;
                    case  R.id.rb_wx:  // 微信支付
                        payType="wx";
                        break;
                    case  R.id.rb_yl: // 银联支付
                        payType = "yl";
                        break;
                }
            }
        });
    }

  // 支付
    @OnClick(R.id.bt_start)
    public void onClick() {
//        Alipay alipay = new Alipay(ChargeMoneyActivity.this);
//        alipay.pay(payInfo);
//        alipay.setHander(mHandler);
        showActivity(RechargeSucceedActivity.class);
    }


    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {

                    PayResult payResult = new PayResult((String) msg.obj);
                    /**
                     * 同步返回的结果必须放置到服务端进行验证（验证的规则请看https://doc.open.alipay.com/doc2/
                     * detail.htm?spm=0.0.0.0.xdvAU6&treeId=59&articleId=103665&
                     * docType=1) 建议商户依赖异步通知
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息

                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为“9000”则代表支付成功，具体状态码代表含义可参考接口文档
                    if (TextUtils.equals(resultStatus, "9000")) {
                        Toast.makeText(AccountRechargeActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                    } else {
                        // 判断resultStatus 为非"9000"则代表可能支付失败
                        // "8000"代表支付结果因为支付渠道原因或者系统原因还在等待支付结果确认，最终交易是否成功以服务端异步通知为准（小概率状态）
                        if (TextUtils.equals(resultStatus, "8000")) {
                            Toast.makeText(AccountRechargeActivity.this, "支付结果确认中", Toast.LENGTH_SHORT).show();

                        } else {
                            // 其他值就可以判断为支付失败，包括用户主动取消支付，或者系统返回的错误
                            Toast.makeText(AccountRechargeActivity.this, "支付失败", Toast.LENGTH_SHORT).show();
                        }
                    }
                    break;
                }
                default:
                    break;
            }
        }
    };

}
