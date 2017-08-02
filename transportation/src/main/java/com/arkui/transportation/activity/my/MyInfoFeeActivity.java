package com.arkui.transportation.activity.my;

import android.os.Handler;
import android.view.View;
import android.widget.ImageView;

import com.arkui.fz_tools.adapter.CommonAdapter;
import com.arkui.fz_tools.ui.BaseActivity;
import com.arkui.fz_tools.ui.BaseListActivity;
import com.arkui.fz_tools.utils.DividerItemDecoration2;
import com.arkui.fz_tools.view.PullRefreshRecyclerView;
import com.arkui.transportation.R;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MyInfoFeeActivity extends BaseListActivity<String> {

    @BindView(R.id.rl_list)
    PullRefreshRecyclerView mRlList;
    private CommonAdapter<String> mCommonAdapter;
    private ImageView mIvHintBg;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_my_info_fee);
        setTitle("我的信息费");
    }

    @Override
    public void initView() {
        super.initView();
        ButterKnife.bind(this);

        mCommonAdapter = initAdapter(mRlList, R.layout.item_my_info_fee);
        mRlList.addItemDecoration(new DividerItemDecoration2(mActivity,DividerItemDecoration2.VERTICAL_LIST));
        View headView = getLayoutInflater().inflate(R.layout.layout_my_info_fee_head, mRlList, false);
        mIvHintBg = (ImageView) headView.findViewById(R.id.iv_hint_bg);
        headView.findViewById(R.id.iv_hint).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mIvHintBg.getVisibility()==View.VISIBLE){
                    mIvHintBg.setVisibility(View.GONE);
                }else{
                    mIvHintBg.setVisibility(View.VISIBLE);
                }

            }
        });

        mCommonAdapter.addHeaderView(headView);

        onRefreshing();
    }

    @Override
    public void onRefreshing() {
        super.onRefreshing();

        new Handler().postDelayed(new Runnable() {
            public void run() {
                mCommonAdapter.addData("积分更新啦！");
                mCommonAdapter.addData("积分更新啦！");
                mCommonAdapter.addData("积分更新啦！");
                mCommonAdapter.addData("积分更新啦！");
                mRlList.refreshComplete();
            }
        },500);
    }
}
