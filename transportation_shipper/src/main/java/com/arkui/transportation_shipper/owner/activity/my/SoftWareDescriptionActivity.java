package com.arkui.transportation_shipper.owner.activity.my;

import android.webkit.WebView;

import com.arkui.fz_tools.model.Constants;
import com.arkui.fz_tools.ui.BaseActivity;
import com.arkui.fz_tools.utils.SPUtil;
import com.arkui.transportation_shipper.R;

public class SoftWareDescriptionActivity extends BaseActivity {

    private WebView web;



    @Override
    public void setRootView() {
        setContentView(R.layout.activity_soft_ware_description);
        web = (WebView) findViewById(R.id.webView);
        int type = SPUtil.getInstance(this).read("type", -1);
        if(type== Constants.DRIVER){
            web.loadUrl(Constants.DRIVER_SOFTWARE_DETAILS);//司机         finish();
        }else if(type==Constants.CAR_OWNER){
            web.loadUrl(Constants.CAR_SOFTWARE_DETAILS);//车主
        }
        setTitle("软件说明");
    }
}
