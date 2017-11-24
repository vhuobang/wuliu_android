package com.arkui.transportation_shipper.owner.activity.waybill;

import android.os.Handler;
import android.widget.RatingBar;
import android.widget.Toast;

import com.arkui.fz_tools._interface.PublicInterface;
import com.arkui.fz_tools.dialog.SuccessFullyDialog;
import com.arkui.fz_tools.entity.EvaluateEvent;
import com.arkui.fz_tools.mvp.EvaluatePresenter;
import com.arkui.fz_tools.ui.BaseActivity;
import com.arkui.fz_tools.view.ShapeButton;
import com.arkui.transportation_shipper.R;
import com.arkui.transportation_shipper.common.base.App;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class PublishEvaluateActivity extends BaseActivity implements PublicInterface {

    @BindView(R.id.cargo_starts)
    RatingBar mCargoStarts;
    @BindView(R.id.lg_starts)
    RatingBar mLgStarts;
    @BindView(R.id.publish)
    ShapeButton mPublish;
    SuccessFullyDialog  mSuccessFullyDialog;
    private EvaluatePresenter evaluatePresenter;
    private String waybill_id;

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
        waybill_id = getIntent().getStringExtra("waybill_id");
        evaluatePresenter = new EvaluatePresenter(this, this);
    }

    @OnClick(R.id.publish)
    public void onViewClicked() {
        String cargoStarts = String.valueOf(mCargoStarts.getRating());
        String lgStarts = String.valueOf(mLgStarts.getRating());
        evaluatePresenter.evaluate(cargoStarts,lgStarts,null, App.getUserId(),"3",waybill_id);
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
