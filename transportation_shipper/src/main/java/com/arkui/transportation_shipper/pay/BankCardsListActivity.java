package com.arkui.transportation_shipper.pay;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.arkui.fz_tools.entity.BinkCardInfo;
import com.arkui.fz_tools.model.NetConstants;
import com.arkui.fz_tools.ui.BaseActivity;
import com.arkui.fz_tools.utils.AppManager;
import com.arkui.fz_tools.view.PullRefreshRecyclerView;
import com.arkui.transportation_shipper.R;
import com.arkui.transportation_shipper.common.base.App;
import com.arkui.transportation_shipper.owner.activity.my.AccountRechargeActivity;
import com.arkui.transportation_shipper.owner.adapter.BankCardListAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chanjet.yqpay.Constants$CallBackConstants;
import com.chanjet.yqpay.IYQPayCallback;
import com.chanjet.yqpay.YQPayApi;
import com.chanjet.yqpay.util.MsgUtil;
import com.chanjet.yqpay.util.StringUtils;
import com.google.gson.Gson;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BankCardsListActivity extends BaseActivity implements OnRefreshListener {

    @BindView(R.id.rl_bank_list)
    PullRefreshRecyclerView mRlBankList;
    private BankCardListAdapter bankCardListAdapter;
    private ProgressDialog dialog;
    private String orderId;
    private static final int REQUEST_CODE = 8888;
    private String money;

    public static void openActivity(Context context,String orderId,String money){
        Intent intent = new Intent(context,BankCardsListActivity.class);
        intent.putExtra("orderId",orderId);
        intent.putExtra("money",money);
        context.startActivity(intent);
    }

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_bank_cards_list);
        setTitle("选择银行卡");
        setRight("绑定银行卡");
        ButterKnife.bind(this);
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppManager.getAppManager().finishActivity(BankCardsListActivity.class);
                AppManager.getAppManager().finishActivity(AccountRechargeActivity.class);
                finish();
            }
        });
    }

    @Override
    public void initData() {

        orderId = getIntent().getStringExtra("orderId");
        money = getIntent().getStringExtra("money");
        bankCardListAdapter = new BankCardListAdapter(R.layout.layout_bank_card);
        mRlBankList.setLinearLayoutManager();
        mRlBankList.setAdapter(bankCardListAdapter);
        mRlBankList.setOnRefreshListener(this);
        // 点击 进入支付页面
        bankCardListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                BinkCardInfo.BindingCardsBean item = (BinkCardInfo.BindingCardsBean) adapter.getItem(position);
                do_request_pay(item);
            }
        });

        d0_queryAuthentication();

    }

    /**
     * 支付
     */
    private void do_request_pay(BinkCardInfo.BindingCardsBean info) {
        dialog = ProgressDialog.show(this, "提示", "处理中，请稍后...");
        dialog.setCancelable(true);
        YQPayApi.doPay(mActivity, getRequestPayParam(info), new IYQPayCallback() {
            @Override
            public void payResult(String status, String message) {
                dialog.dismiss();
                MsgUtil.showDebugLog("WJDemo", "bind card result : " + message);
                if (status.equals(Constants$CallBackConstants.CALLBACK_FAILD)) {
                    Toast.makeText(mActivity, message, Toast.LENGTH_LONG).show();
                } else {
                    WebViewActivity.startActionForResult(mActivity, message, REQUEST_CODE);
                }
            }
        });
    }

    private Map<String, String> getRequestPayParam(BinkCardInfo.BindingCardsBean info) {
        HashMap<String, String> reqMap = new HashMap<>();
        reqMap.put("Version", "1.0");//接口版本，目前只有固定值1.0
        reqMap.put("TradeDate", StringUtils.getCurrentTime("yyyyMMdd"));// 交易日期yyyyMMdd
        reqMap.put("TradeTime", StringUtils.getCurrentTime("HHmmss"));// 交易时间HHmmss
        reqMap.put("PartnerId", Constants.MERID); // 签约合作方的唯一用户号
        reqMap.put("InputCharset", "utf-8");// 商户网站使用的编码格式，如utf-8、gbk、gb2312等
        reqMap.put("MchId", Constants.MERID);// 商户标示ID
        reqMap.put("PAY_KEY", Constants.PAY_KEY);// 支付密钥
        reqMap.put("TrxId", orderId);
        reqMap.put("OrdrName", "充值");
        reqMap.put("MerUserId", App.getUserId());
        reqMap.put("SellerId",Constants.MERID );
        reqMap.put("TradeType", "11");
        reqMap.put("BkAcctTp", info.getBkAcctTp());//卡类型 00-贷记卡  01-借记卡
        reqMap.put("CardBegin", info.getCardBegin());
        reqMap.put("CardEnd", info.getCardEnd());
        reqMap.put("TrxAmt", money); // 交易金额
        reqMap.put("BankCode", "FrontEndRequestPay");
        reqMap.put("ExpiredTime", "30m");
        reqMap.put("AccessChannel", "wap");
//        if (!StringUtils.isEmpty(etNotifyUrl.getText().toString().trim()))
//            reqMap.put("ReturnUrl", etReturnUrl.getText().toString().trim());
        reqMap.put("NotifyUrl", NetConstants.NOTIFY_URL);

        return reqMap;
    }

    private void d0_queryAuthentication() {
        dialog = ProgressDialog.show(this, "提示", "处理中，请稍后...");
        dialog.setCancelable(true);
        bankCardListAdapter.setNewData(null);
        YQPayApi.doPay(mActivity, getBaseParam(), new IYQPayCallback() {
            @Override
            public void payResult(String status, String message) {
                dialog.dismiss();
                mRlBankList.refreshComplete();
                MsgUtil.showDebugLog("WJDemo", "Query bind card info result : " + message);
                if (status.equals(Constants$CallBackConstants.CALLBACK_FAILD))
                    Toast.makeText(mActivity, message, Toast.LENGTH_LONG).show();
                else {
                    Gson gson = new Gson();
                   BinkCardInfo binkCardInfo = gson.fromJson(message, BinkCardInfo.class);
                    List<BinkCardInfo.BindingCardsBean> bindingCards = binkCardInfo.getBindingCards();
//                    Log.e("fz", "payResult: "+ bindingCards );
//                    ArrayList<BankCardInfo> cardList = gson.fromJson(bindingCards, new TypeToken<ArrayList<BankCardInfo>>() {
//                    }.getType());
                    Toast.makeText(mActivity, binkCardInfo.getRetMsg(), Toast.LENGTH_LONG).show();
                    if (bindingCards != null && bindingCards.size() > 0) {
                       bankCardListAdapter.setNewData(bindingCards);
                    }else {
                        mRlBankList.loadFail("暂时没有绑卡");
                    }
                }
            }
        });
    }
    @Override
    protected void onRightClick() {
        super.onRightClick();
        //直接调用畅捷的界面  需要的参数： 订单号 商户编号  订单有效期  异步通知地址 用户标识  同步回掉地址
        do_bindCard();
    }
    private void do_bindCard() {
//        getBindCardPara();
        dialog = ProgressDialog.show(this, "提示", "处理中，请稍后...");
        dialog.setCancelable(true);
        YQPayApi.doPay(mActivity, getBindCardPara(), new IYQPayCallback() {
            @Override
            public void payResult(String status, String message) {
                dialog.dismiss();
                Log.e("fz", "payResult: "+ message );
                MsgUtil.showDebugLog("WJDemo", "bind card result : " + message);
                if (status.equals(Constants$CallBackConstants.CALLBACK_FAILD)) {
                    Toast.makeText(mActivity, message, Toast.LENGTH_LONG).show();
                } else {
                    WebViewActivity.startActionForResult(mActivity, message, REQUEST_CODE);
                }
            }
        });
    }

    private HashMap<String, String> getBindCardPara() {
        HashMap<String, String> maps = new HashMap<>();
        maps.put("Version", "1.0");//接口版本，目前只有固定值1.0
        maps.put("TradeDate", StringUtils.getCurrentTime("yyyyMMdd"));// 交易日期yyyyMMdd
        maps.put("TradeTime", StringUtils.getCurrentTime("HHmmss"));// 交易时间HHmmss
        maps.put("PartnerId", Constants.MERID); // 签约合作方的唯一用户号
        maps.put("InputCharset", "utf-8");// 商户网站使用的编码格式，如utf-8、gbk、gb2312等
        maps.put("MchId", Constants.MERID);// 商户标示ID
        maps.put("PAY_KEY", Constants.PAY_KEY);// 支付密钥

        maps.put("TrxId", StringUtils.getOrderid());// 订单号

        maps.put("ExpiredTime", "30m");// 订单有效期
        maps.put("BankCode", "FrontEndBindCard");
        maps.put("NotifyUrl", "http://cadmin.chanpay.com/tpu/mag/asynNotify.do");
        maps.put("ReturnUrl", "");
        maps.put("MerUserId", App.getUserId());

        return maps;
    }
    /**
     * 下拉刷新
     * @param refreshlayout
     */
    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        d0_queryAuthentication();
    }

    /**
     * 畅捷接口用到的参数
     * @return
     */
    private Map<String, String> getBaseParam() {
        Map<String, String> reqMaps = new HashMap<String, String>();
        // 基本参数
        reqMaps.put("Version", "1.0");//接口版本，目前只有固定值1.0
        reqMaps.put("TradeDate", StringUtils.getCurrentTime("yyyyMMdd"));// 交易日期yyyyMMdd
        reqMaps.put("TradeTime", StringUtils.getCurrentTime("HHmmss"));// 交易时间HHmmss
        reqMaps.put("PartnerId", Constants.MERID); // 签约合作方的唯一用户号
        reqMaps.put("InputCharset", "utf-8");// 商户网站使用的编码格式，如utf-8、gbk、gb2312等
        reqMaps.put("MchId", Constants.MERID);// 商户标示ID
        reqMaps.put("PAY_KEY", Constants.PAY_KEY);// 支付密钥
     //   reqMaps.put("AppId", "2016081101731631");// 微信/支付宝给商户开通的appid

        reqMaps.put("BankCode", "BindCardQuery");
        reqMaps.put("TrxId", StringUtils.getOrderid());// 订单号
        reqMaps.put("MerUserId", App.getUserId());//App.getUserId()
//        reqMaps.put("CardBegin", "");
//        reqMaps.put("CardEnd", "");
//        reqMaps.put("BkAcctTp", "");//卡类型 00-贷记卡  01-借记卡

        return reqMaps;
    }
}
