package com.arkui.transportation_owner.activity.waybill;

import android.os.Handler;
import android.widget.RatingBar;
import android.widget.Toast;

import com.arkui.fz_tools._interface.PublicInterface;
import com.arkui.fz_tools.dialog.SuccessFullyDialog;
import com.arkui.fz_tools.entity.EvaluateEvent;
import com.arkui.fz_tools.mvp.EvaluatePresenter;
import com.arkui.fz_tools.ui.BaseActivity;
import com.arkui.fz_tools.view.ShapeButton;
import com.arkui.transportation_owner.R;
import com.arkui.transportation_owner.base.App;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 货主端 评价
 */

public class PublishEvaluateActivity extends BaseActivity implements PublicInterface {

    @BindView(R.id.car_starts)
    RatingBar mCarStarts;
    @BindView(R.id.lg_starts)
    RatingBar mLgStarts;
    @BindView(R.id.bt_publish)
    ShapeButton mBtPublish;
    private SuccessFullyDialog mSuccessFullyDialog;
    private EvaluatePresenter evaluatePresenter;
    private String orderId;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_publish_evaluate);
        setTitle("发布评价");

        mSuccessFullyDialog = new SuccessFullyDialog();
        mSuccessFullyDialog.setTitle("发布成功");
    }

    @Override
    public void initView() {
        super.initView();
        ButterKnife.bind(this);
        orderId = getIntent().getStringExtra("wayBillId");
        evaluatePresenter = new EvaluatePresenter(this, this);
    }

    @OnClick(R.id.bt_publish)
    public void onClick() {
        String carStarts = String.valueOf(mCarStarts.getRating());
        String lgStarts = String.valueOf(mLgStarts.getRating());
        evaluatePresenter.evaluate(null,lgStarts,carStarts, App.getUserId(),"1",orderId);
    }



    @Override
    public void onSuccess() {
        mSuccessFullyDialog.show(getSupportFragmentManager(), "publish");

        new Handler().postDelayed(new Runnable() {
            public void run() {
                mSuccessFullyDialog.dismiss();
                EventBus.getDefault().post(new EvaluateEvent());
                finish();
            }
        }, 1000);
    }

    @Override
    public void onFail(String message) {
        Toast.makeText(mActivity,message,Toast.LENGTH_SHORT).show();
    }
}
