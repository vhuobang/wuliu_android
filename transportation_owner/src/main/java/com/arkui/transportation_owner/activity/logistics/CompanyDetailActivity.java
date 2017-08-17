package com.arkui.transportation_owner.activity.logistics;

import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.arkui.fz_net.utils.RxBus;
import com.arkui.fz_tools.entity.LogisticsBusEntity;
import com.arkui.fz_tools.ui.BaseActivity;
import com.arkui.fz_tools.utils.SkipUtils;
import com.arkui.transportation_owner.R;
import com.arkui.transportation_owner.entity.LogisticalListEntity;
import com.arkui.transportation_owner.mvp.LogisticsPresenter;
import com.arkui.transportation_owner.view.LogisticsView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class CompanyDetailActivity extends BaseActivity implements LogisticsView {

    @BindView(R.id.tv_name)
    TextView mTvName;
    @BindView(R.id.tv_address)
    TextView mTvAddress;
    @BindView(R.id.tv_tel)
    TextView mTvTel;
    @BindView(R.id.tv_handler_name)
    TextView mTvHandlerName;
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
    LogisticalListEntity mLogisticalDetails;
    private int mPosition;
    private int mType;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_company_detail);
        setTitle(getIntent().getStringExtra("title"));
    }

    @Override
    public void initView() {
        super.initView();
        ButterKnife.bind(this);
        mType = getIntent().getIntExtra("type", -1);
    }

    @Override
    public void initData() {
        super.initData();
        mLogisticsPresenter = new LogisticsPresenter(this, this);
        String id = getIntent().getStringExtra("id");
        mLogisticsPresenter.postLogisticsDetail(id);
        mPosition = getIntent().getIntExtra("position", -1);
    }

    @Override
    public void onSucceed(List<LogisticalListEntity> logisticalList) {

    }

    @Override
    public void onError() {

    }

    @Override
    public void onSucceed(LogisticalListEntity logisticalDetails) {
        setUiData(logisticalDetails);
    }

    @Override
    public void onSucceed(int position) {
        //通知上一页改变状态
        mLogisticalDetails.setStatus("1".equals(mLogisticalDetails.getStatus())?"0":"1");
        mTvCollect.setText("1".equals(mLogisticalDetails.getStatus()) ? "已收藏" : "收藏");
        mIvCollect.setImageResource("1".equals(mLogisticalDetails.getStatus()) ? R.mipmap.sc_1 : R.mipmap.sc_2);
        RxBus.getDefault().post(new LogisticsBusEntity(mType,position,mLogisticalDetails.getStatus()));
    }

    private void setUiData(LogisticalListEntity logisticalDetails) {
        mLogisticalDetails=logisticalDetails;
        mTvName.setText(logisticalDetails.getName());
        mTvAddress.setText(logisticalDetails.getAddress());
        mTvRegisterYear.setText(String.format("%s年", logisticalDetails.getRegisterYear()));
        mTvTel.setText(logisticalDetails.getTel());
        mTvVolume.setText(logisticalDetails.getVolume());
        mRating.setRating(Float.parseFloat(logisticalDetails.getStarRating()));
        mTvCollect.setText("1".equals(logisticalDetails.getStatus()) ? "已收藏" : "收藏");
        mIvCollect.setImageResource("1".equals(logisticalDetails.getStatus()) ? R.mipmap.sc_1 : R.mipmap.sc_2);
        mTvHandlerName.setText(logisticalDetails.getHandlerName());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mLogisticsPresenter != null) {
            mLogisticsPresenter.onDestroy();
        }
    }

    @OnClick({R.id.iv_tel, R.id.ll_collect})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_tel:
                SkipUtils.skipPhone(this,mLogisticalDetails.getTel());
                break;
            case R.id.ll_collect:
                mLogisticsPresenter.postCollectionLogistical(mLogisticalDetails.getId(),mPosition);
                break;
        }
    }
}
