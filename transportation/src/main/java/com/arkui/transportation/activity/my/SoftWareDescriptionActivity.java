package com.arkui.transportation.activity.my;

import android.webkit.WebView;

import com.arkui.fz_tools.model.Constants;
import com.arkui.fz_tools.ui.BaseActivity;
import com.arkui.transportation.R;


public class SoftWareDescriptionActivity extends BaseActivity {

    private WebView web;



    @Override
    public void setRootView() {
        setContentView(R.layout.activity_soft_ware_description);
        web = (WebView) findViewById(R.id.webView);
        web.loadUrl(Constants.LOG_SOFTWARE_DETAILS);
        setTitle("软件说明");
    }
}
