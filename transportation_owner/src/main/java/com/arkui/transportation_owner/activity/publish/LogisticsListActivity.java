package com.arkui.transportation_owner.activity.publish;

import android.content.Intent;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import com.ajguan.library.EasyRefreshLayout;
import com.arkui.fz_tools.adapter.CommonAdapter;
import com.arkui.fz_tools.listener.OnBindViewHolderListener;
import com.arkui.fz_tools.ui.BaseActivity;
import com.arkui.fz_tools.utils.DividerItemDecoration;
import com.arkui.fz_tools.view.PullRefreshRecyclerView;
import com.arkui.transportation_owner.R;
import com.arkui.transportation_owner.activity.logistics.CityPickerActivity;
import com.arkui.transportation_owner.activity.logistics.CompanyDetailActivity;
import com.arkui.transportation_owner.activity.logistics.PersonageDetailActivity;
import com.arkui.transportation_owner.activity.logistics.SearchLogisticsActivity;
import com.arkui.transportation_owner.utils.ListData;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class LogisticsListActivity extends BaseActivity implements OnBindViewHolderListener<String>, EasyRefreshLayout.EasyEvent {

    @BindView(R.id.tv_city)
    TextView mTvCity;
    @BindView(R.id.rl_list)
    PullRefreshRecyclerView mRlList;
    private CommonAdapter<String> mLogisticsAdapter;

    @Override
    public void setRootView() {
        setContentViewNoTitle(R.layout.activity_logistics_list);
        //setTitle("物流公司列表页");
    }

    @Override
    public void initView() {
        super.initView();
        ButterKnife.bind(this);
        mLogisticsAdapter = new CommonAdapter<>(R.layout.item_logistics, this);
        mRlList.setLinearLayoutManager();
        mRlList.setAdapter(mLogisticsAdapter);
        mRlList.addEasyEvent(this);
        mRlList.addItemDecoration(new DividerItemDecoration(mActivity, DividerItemDecoration.VERTICAL_LIST));

        mLogisticsAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                showActivity(PersonageDetailActivity.class);
            }
        });

        mLogisticsAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                showActivity(CompanyDetailActivity.class);
            }
        });
        onRefreshing();
    }

    @OnClick({R.id.tv_city, R.id.tv_search})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_city:
                showActivity(CityPickerActivity.class);
                break;
            case R.id.tv_search:
               // showActivity(SearchLogisticsActivity.class);
                Intent intent=new Intent(mActivity,SearchLogisticsActivity.class);
                intent.putExtra("isSelect",true);
                startActivity(intent);
                break;
        }

    }

    @Override
    public void convert(BaseViewHolder helper, String item) {
        helper.addOnClickListener(R.id.iv_head);
    }

    @Override
    public void onRefreshing() {
        new Handler().postDelayed(new Runnable() {
            public void run() {
                mLogisticsAdapter.addData(ListData.getTestData(""));
                mRlList.refreshComplete();
            }
        }, 1000);

    }

    @OnClick(R.id.tv_next)
    public void onClick() {
        showActivity(PublishDeclareActivity.class);
    }
}
