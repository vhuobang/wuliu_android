package com.arkui.transportation_owner.activity.user;

import android.webkit.WebView;

import com.arkui.fz_tools.model.NetConstants;
import com.arkui.fz_tools.ui.BaseActivity;
import com.arkui.transportation_owner.R;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ClauseActivity extends BaseActivity {

    @BindView(R.id.webView)
    WebView mWebView;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_clause);
        setTitle("免责声明");
    }

    @Override
    public void initView() {
        super.initView();
        ButterKnife.bind(this);
        mWebView.loadUrl(NetConstants.IMPUNITY);
    }


}
