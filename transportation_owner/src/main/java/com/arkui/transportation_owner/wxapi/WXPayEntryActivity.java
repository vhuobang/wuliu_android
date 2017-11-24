package com.arkui.transportation_owner.wxapi;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.arkui.fz_tools.utils.AppManager;
import com.arkui.transportation_owner.activity.my.AccountRechargeActivity;
import com.arkui.transportation_owner.pay.Constants;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;


public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler {
	
	private static final String TAG = "MicroMsg.SDKSample.WXPayEntryActivity";
	
    private IWXAPI api;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.pay_result);
    	api = WXAPIFactory.createWXAPI(this, Constants.WX_APP_ID);
        api.handleIntent(getIntent(), this);
    }

	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
		setIntent(intent);
        api.handleIntent(intent, this);
	}

	@Override
	public void onReq(BaseReq req) {
	}

	@Override
	public void onResp(BaseResp resp) {
		//LogUtils.i("onPayFinish" + resp.errCode);
		if (resp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
			switch (resp.errCode){
				case 0:
					AlertDialog.Builder builder = new AlertDialog.Builder(this);
					//builder.setTitle("支付成功");
					builder.setMessage("支付成功");
					builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialogInterface, int i) {
							WXPayEntryActivity.this.finish();
							AppManager.getAppManager().finishActivity(AccountRechargeActivity.class);
						}
					});
					builder.show();
					break;

				default:
					AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
					//builder.setTitle("支付成功");
					if (resp.errCode== -2){
						builder1.setMessage("取消支付");
					}else{
						builder1.setMessage("支付失败"+resp.errStr);
					}

					builder1.setNegativeButton("确定", new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialogInterface, int i) {
							WXPayEntryActivity.this.finish();
						}
					});
					builder1.show();
					break;
			}
		}
	}
}