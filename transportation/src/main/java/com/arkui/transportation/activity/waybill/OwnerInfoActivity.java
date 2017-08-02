package com.arkui.transportation.activity.waybill;

import android.view.View;
import android.widget.TableRow;

import com.arkui.fz_tools.ui.BaseActivity;
import com.arkui.transportation.R;

import butterknife.BindView;
import butterknife.ButterKnife;


public class OwnerInfoActivity extends BaseActivity {

    @BindView(R.id.tl_owner_phone)
    TableRow mTlOwnerPhone;
    @BindView(R.id.tl_owner_time)
    TableRow mTlOwnerTime;
    @BindView(R.id.tl_owner_number)
    TableRow mTlOwnerNumber;
    @BindView(R.id.tl_ratingBar)
    TableRow mTlRatingBar;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_owner_info);
        setTitle("货主信息");
    }

    @Override
    public void initView() {
        super.initView();
        ButterKnife.bind(this);
        boolean isMy = getIntent().getBooleanExtra("isMy", false);
        if (isMy) {
            mTlOwnerTime.setVisibility(View.GONE);
            mTlOwnerNumber.setVisibility(View.GONE);
            mTlRatingBar.setVisibility(View.GONE);
        }
    }

}
