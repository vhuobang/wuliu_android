package com.arkui.transportation.activity.publish;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.arkui.fz_tools.dialog.SelectTypePicker;
import com.arkui.fz_tools.entity.ReleaseDetailsEntity;
import com.arkui.fz_tools.listener.OnVehicleTypeClickListener;
import com.arkui.fz_tools.ui.BaseActivity;
import com.arkui.fz_tools.utils.SPUtil;
import com.arkui.fz_tools.utils.StrUtil;
import com.arkui.fz_tools.view.ShapeButton;
import com.arkui.transportation.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.arkui.transportation.R.id.tv_time;


public class PublishCompleteInfoActivity extends BaseActivity implements OnVehicleTypeClickListener {

    @BindView(R.id.et_host_name)
    EditText etHostName;
    @BindView(R.id.et_host_phone)
    EditText etHostPhone;
    @BindView(R.id.contact_name)
    EditText contactName;
    @BindView(R.id.et_contact_phone)
    EditText etContactPhone;
    @BindView(tv_time)
    TextView tvTime;
    @BindView(R.id.info_fee)
    EditText infoFee;
    @BindView(R.id.bt_confirm)
    ShapeButton btConfirm;
    private SelectTypePicker mSelectTypePicker;

    String mSettleMentTime ;
    private ReleaseDetailsEntity releaseDetails;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_publish_complete_info);
        setTitle("完善信息");
    }

    @Override
    public void initView() {
        super.initView();
        ButterKnife.bind(this);
        releaseDetails = (ReleaseDetailsEntity) getIntent().getSerializableExtra("releaseDetails");
        List<String> mEndTimeList = new ArrayList<>();
        mEndTimeList.add("立即结算");
        mEndTimeList.add("7天内结算");
        mEndTimeList.add("15天内结算");
        mEndTimeList.add("30天内结算");

        String logName = SPUtil.getInstance(mActivity).read("logName", "");
        String logPhoneNumber = SPUtil.getInstance(mActivity).read("logPhoneNumber", "");
        if (!TextUtils.isEmpty(logName)){
            contactName.setText(logName);
        }
        if (!TextUtils.isEmpty(logPhoneNumber)){
            etContactPhone.setText(logPhoneNumber);
        }
        mSelectTypePicker = new SelectTypePicker();
        mSelectTypePicker.setTitle("结算时间").setData(mEndTimeList);
        mSelectTypePicker.setOnTypeClickListener(this);
    }

    @OnClick({R.id.bt_confirm, tv_time})
    public void onClick(View view) {
        switch (view.getId()) {
            case tv_time:
                mSelectTypePicker.show(getSupportFragmentManager(), "time");
                break;
            case R.id.bt_confirm:
                String hostName = etHostName.getText().toString().trim(); //货主名字
                String hostPhone = etHostPhone.getText().toString().trim();//货主的手机号
                String contact = contactName.getText().toString().trim(); //物流联系人
                String contactPhone = etContactPhone.getText().toString().trim();// 物流电话
                String infoFee = this.infoFee.getText().toString().trim();//信息费用
                if (TextUtils.isEmpty(infoFee)){
                    infoFee="0";
                }
//                if (TextUtils.isEmpty(hostName)){
//                    Toast.makeText(PublishCompleteInfoActivity.this,"请输入货主名",Toast.LENGTH_SHORT).show();
//                    return;
//                }
//
//                if (!StrUtil.isMobileNO(hostPhone)){
//                    Toast.makeText(PublishCompleteInfoActivity.this,"请输入货主电话",Toast.LENGTH_SHORT).show();
//                    return;
//                }
                if (TextUtils.isEmpty(contact)){
                    Toast.makeText(PublishCompleteInfoActivity.this,"请输入物流联系人",Toast.LENGTH_SHORT).show();
                    return;
                }
                SPUtil.getInstance(mActivity).save("logName",contact);
                if (!StrUtil.isMobileNO(contactPhone)){
                    Toast.makeText(PublishCompleteInfoActivity.this,"请输入物流联系人电话",Toast.LENGTH_SHORT).show();
                    return;
                }
                SPUtil.getInstance(mActivity).save("logPhoneNumber",contactPhone);
//                if (TextUtils.isEmpty(infoFee)){
//                    Toast.makeText(PublishCompleteInfoActivity.this,"输入信息费",Toast.LENGTH_SHORT).show();
//                    return;
//                }
                releaseDetails.setOwner_name(hostName);
                releaseDetails.setOwner_tel(hostPhone);
                releaseDetails.setLog_contact_name(contact);
                releaseDetails.setLog_contact_tel(contactPhone);
                releaseDetails.setInfomation_fee(infoFee);
                String settlementTime = releaseDetails.getSettlementTime();

                releaseDetails.setLog_settlement_time(settlementTime);
                Bundle bundle = new Bundle();
                bundle.putSerializable("releaseInfo",releaseDetails);
                showActivity(PublishDeclareActivity.class,bundle);
                break;

        }
    }

    @Override
    public void OnVehicleTypeClick(String item, int pos) {
        mSettleMentTime = pos+1+"";
        tvTime.setText(item);
    }


}
