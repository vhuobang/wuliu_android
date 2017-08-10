package com.arkui.transportation.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arkui.fz_tools._interface.NoticeInterface;
import com.arkui.fz_tools.adapter.CommonAdapter;
import com.arkui.fz_tools.entity.NoticeEntity;
import com.arkui.fz_tools.listener.OnBindViewHolderListener;
import com.arkui.fz_tools.mvp.BaseMvpFragment;
import com.arkui.fz_tools.mvp.NoticePresenter;
import com.arkui.fz_tools.utils.DividerItemDecoration;
import com.arkui.fz_tools.view.PullRefreshRecyclerView;
import com.arkui.transportation.R;
import com.arkui.transportation.activity.MessageDetailsActivity;
import com.arkui.transportation.base.App;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 系统消息
 */
public class SystemFragment extends BaseMvpFragment<NoticePresenter> implements OnBindViewHolderListener<NoticeEntity>,OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener,NoticeInterface {

    @BindView(R.id.rl_system)
    PullRefreshRecyclerView mRlSystem;
   /* @BindView(R.id.refresh)
    EasyRefreshLayout mRefresh;*/
    private CommonAdapter<NoticeEntity> mAdapter;

    private  int page=1;
    private int pageSize =10;
    private  String SYSTEM_TYPE="1";

    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        return inflater.inflate(R.layout.fragment_system, container, false);
    }

    @Override
    protected void initView(View parentView) {
        super.initView(parentView);
        ButterKnife.bind(this, parentView);
        mRlSystem.setOnRefreshListener(this);
        mAdapter = new CommonAdapter<NoticeEntity>(R.layout.item_system_message,this);
        mRlSystem.setLayoutManager(new LinearLayoutManager(mContext));
        mRlSystem.setAdapter(mAdapter);
        mRlSystem.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL_LIST));
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent=new Intent(mContext, MessageDetailsActivity.class);
                intent.putExtra("title","系统消息");
                startActivity(intent);
            }
        });
        mAdapter.setOnLoadMoreListener(this,mRlSystem.getRecyclerView());


    }

    @Override
    protected void initData() {

        getLoadData();
    }
    public void onRefreshing() {
        page=1;
        getLoadData();
    }
    private void getLoadData() {
        mPresenter.getNoticeList(App.getUser_id(),SYSTEM_TYPE,page,pageSize);
    }

    @Override
    public void convert(BaseViewHolder helper, NoticeEntity item) {
        helper.setText(R.id.tv_name,item.getTitle());
        helper.setText(R.id.tv_time,item.getCreated_at());
        helper.setText(R.id.tv_content,item.getContent());
        String status = item.getStatus();
        if ("1".equals(status)){
            helper.getView(R.id.red_point).setVisibility(View.VISIBLE);
        }else {
            helper.getView(R.id.red_point).setVisibility(View.GONE);
        }
    }
   // 下拉刷新
    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        onRefreshing();
    }
     // 加载更多
    @Override
    public void onLoadMoreRequested() {
        page++;
        getLoadData();
    }
     // 加载数据成功
    @Override
    public void onSuccess(List<NoticeEntity> noticeEntityList) {
        if (page==1){
            mAdapter.setNewData(noticeEntityList);
            mRlSystem.refreshComplete();
            if(mAdapter.getItemCount()<10){
                mAdapter.loadMoreEnd(true);
            }else{
                mAdapter.loadMoreEnd(false);
            }
        }else {
            mAdapter.addData(noticeEntityList);
            mAdapter.loadMoreComplete();
            mRlSystem.refreshComplete();
        }
    }
   // 加载数据失败
    @Override
    public void onFail(String message) {
        mAdapter.loadMoreEnd();
        mRlSystem.refreshComplete();
        mRlSystem.loadFail();
    }

    @Override
    public void initPresenter() {
            mPresenter.setNoticeInterface(this);
    }
}
