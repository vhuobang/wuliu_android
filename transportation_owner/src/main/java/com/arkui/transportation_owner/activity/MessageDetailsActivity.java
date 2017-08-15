package com.arkui.transportation_owner.activity;

import android.widget.TextView;
import android.widget.Toast;

import com.arkui.fz_tools._interface.SystemMessageInterface;
import com.arkui.fz_tools.entity.SystemDetialEntity;
import com.arkui.fz_tools.mvp.BaseMvpActivity;
import com.arkui.fz_tools.mvp.SystemMessagePresenter;
import com.arkui.transportation_owner.R;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MessageDetailsActivity extends BaseMvpActivity<SystemMessagePresenter> implements SystemMessageInterface {

    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.tv_content)
    TextView tvContent;
    private String id;

    @Override
    public void setRootView() {

        setContentView(R.layout.activity_message_details);
        ButterKnife.bind(this);
        setTitle(getIntent().getStringExtra("title"));

    }

    @Override
    public void initView() {
        mPresenter = new SystemMessagePresenter(this,this);
        id = getIntent().getStringExtra("id");
    }

    @Override
    public void initData() {
        mPresenter.getSystemMessageDetail(id); // 请求数据
    }


    @Override
    public void initPresenter() {
        mPresenter.setSystemMessageInterface(this);
    }
    // 请求成功的回调
    @Override
    public void onSuccess(SystemDetialEntity systemDetialEntity) {
        tvTime.setText(systemDetialEntity.getCreatedAt());
        tvContent.setText(systemDetialEntity.getContent());
    }

    @Override
    public void onFail(String errorMessage) {
        Toast.makeText(MessageDetailsActivity.this,errorMessage,Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        mPresenter.onDestroy();
        super.onDestroy();

    }
}

