package com.arkui.transportation_shipper.owner.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.arkui.fz_tools._interface.NoticeInterface;
import com.arkui.fz_tools._interface.PublicInterface;
import com.arkui.fz_tools.adapter.CommonAdapter;
import com.arkui.fz_tools.entity.NoticeEntity;
import com.arkui.fz_tools.listener.OnBindViewHolderListener;
import com.arkui.fz_tools.mvp.BaseMvpFragment;
import com.arkui.fz_tools.mvp.NoticePresenter;
import com.arkui.fz_tools.mvp.PublicPresenter;
import com.arkui.fz_tools.utils.DividerItemDecoration;
import com.arkui.fz_tools.view.PullRefreshRecyclerView;
import com.arkui.transportation_shipper.R;
import com.arkui.transportation_shipper.common.base.App;
import com.arkui.transportation_shipper.owner.activity.supply.WaybillDetailActivity;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 基于基类的Fragment
 */
public class OrderFragment extends BaseMvpFragment<NoticePresenter> implements OnBindViewHolderListener<NoticeEntity>,OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener ,NoticeInterface, PublicInterface {

    @BindView(R.id.rl_order)
    PullRefreshRecyclerView mRlOrder;
    // @BindView(R.id.refresh)
    // EasyRefreshLayout mRefresh;
    private CommonAdapter<NoticeEntity> mOrderMessageAdapter;
    private  int page=1;
    private int pageSize =10;
    private  String ORDER_TYPE="1";

    private PublicPresenter publicPresenter;

    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        return inflater.inflate(R.layout.fragment_order, container, false);
    }

    @Override
    protected void initView(View parentView) {
        super.initView(parentView);
        ButterKnife.bind(this, parentView);
        publicPresenter = new PublicPresenter(this, getActivity());
        mRlOrder.setLayoutManager(new LinearLayoutManager(mContext));
        mOrderMessageAdapter = new CommonAdapter(R.layout.item_system_message,this);
        mRlOrder.setAdapter(mOrderMessageAdapter);
        mRlOrder.setOnRefreshListener(this);
        mOrderMessageAdapter.setOnLoadMoreListener(this,mRlOrder.getRecyclerView());

        //mRlOrder.autoRefresh();
        mRlOrder.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL_LIST));

        mOrderMessageAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

                NoticeEntity item = (NoticeEntity) adapter.getItem(position);
                publicPresenter.getReadMessage(item.getId());

                ImageView readPoint = (ImageView) view.findViewById(R.id.red_point);
                readPoint.setVisibility(View.GONE);
                showActivity(WaybillDetailActivity.class);

            }
        });

    }

    @Override
    protected void initData() {
        getLoadData();
    }

    private void getLoadData() {
        mPresenter.getNoticeList(App.getUserId(),ORDER_TYPE,page,pageSize);
    }


    public void onRefreshing() {
        page=1;
        getLoadData();
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

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        onRefreshing();
    }

    /**
     * 上拉加载更多
     */
    @Override
    public void onLoadMoreRequested() {
        page++;
        getLoadData();
    }

    /**
     * 数据请求
     * @param noticeEntityList
     */
    @Override
    public void onSuccess(List<NoticeEntity> noticeEntityList) {
        if (page==1){
            mOrderMessageAdapter.setNewData(noticeEntityList);
            mRlOrder.refreshComplete();
            if(mOrderMessageAdapter.getItemCount()<10){
                mOrderMessageAdapter.loadMoreEnd(true);
            }else{
                mOrderMessageAdapter.loadMoreEnd(false);
            }
        }else {
            mOrderMessageAdapter.addData(noticeEntityList);
            mOrderMessageAdapter.loadMoreComplete();
            mRlOrder.refreshComplete();
        }

    }

    @Override
    public void onSuccess() {

    }

    /*
        请求数据失败
       */
    @Override
    public void onFail(String message) {
        mOrderMessageAdapter.loadMoreEnd();
        mRlOrder.refreshComplete();
        mRlOrder.loadFail();
    }

    @Override
    public void initPresenter() {
        mPresenter.setNoticeInterface(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.onDestroy();
        publicPresenter.onDestroy();
    }
}
