package com.arkui.transportation_shipper.owner.activity.my;

import android.webkit.WebView;

import com.arkui.fz_tools.model.NetConstants;
import com.arkui.fz_tools.ui.BaseActivity;
import com.arkui.transportation_shipper.R;

import butterknife.BindView;
import butterknife.ButterKnife;


public class AboutActivity extends BaseActivity {

    @BindView(R.id.webview)
    WebView mWebview;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_about);
        setTitle("关于我们");
    }

    @Override
    public void initView() {
        super.initView();
        ButterKnife.bind(this);
        mWebview.loadUrl(NetConstants.ABOUT_US);
    }


}
