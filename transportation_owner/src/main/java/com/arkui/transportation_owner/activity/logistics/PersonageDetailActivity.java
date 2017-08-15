package com.arkui.transportation_owner.activity.logistics;

import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.arkui.fz_tools.ui.BaseActivity;
import com.arkui.transportation_owner.R;
import com.arkui.transportation_owner.entity.LogisticalListEntity;
import com.arkui.transportation_owner.mvp.LogisticsPresenter;
import com.arkui.transportation_owner.view.LogisticsView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class PersonageDetailActivity extends BaseActivity implements LogisticsView {

    @BindView(R.id.tv_name)
    TextView mTvName;
    @BindView(R.id.tv_address)
    TextView mTvAddress;
    @BindView(R.id.tv_tel)
    TextView mTvTel;
    @BindView(R.id.tv_registerYear)
    TextView mTvRegisterYear;
    @BindView(R.id.tv_volume)
    TextView mTvVolume;
    @BindView(R.id.rating)
    RatingBar mRating;
    @BindView(R.id.iv_collect)
    ImageView mIvCollect;
    @BindView(R.id.tv_collect)
    TextView mTvCollect;
    private LogisticsPresenter mLogisticsPresenter;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_personage_detail);
        setTitle(getIntent().getStringExtra("title"));
    }

    @Override
    public void initView() {
        super.initView();
        ButterKnife.bind(this);
    }

    @Override
    public void initData() {
        super.initData();
        mLogisticsPresenter = new LogisticsPresenter(this, this);

        String id = getIntent().getStringExtra("id");

        mLogisticsPresenter.postLogisticsDetail(id);
    }

    @Override
    public void onSucceed(List<LogisticalListEntity> logisticalList) {

    }

    @Override
    public void onError() {

    }

    @Override
    public void onSucceed(LogisticalListEntity logisticalDetails) {

    }
}
