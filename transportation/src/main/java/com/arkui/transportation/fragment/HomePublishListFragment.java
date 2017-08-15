package com.arkui.transportation.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.arkui.fz_net.http.ApiException;
import com.arkui.fz_net.http.HttpMethod;
import com.arkui.fz_net.http.HttpResultFunc;
import com.arkui.fz_net.http.RetrofitFactory;
import com.arkui.fz_net.subscribers.ProgressSubscriber;
import com.arkui.fz_tools.adapter.CommonAdapter;
import com.arkui.fz_tools.ui.BaseListLazyFragment;
import com.arkui.fz_tools.utils.DividerItemDecoration2;
import com.arkui.fz_tools.view.PullRefreshRecyclerView;
import com.arkui.transportation.R;
import com.arkui.transportation.activity.home.SupplyDetailActivity;
import com.arkui.transportation.api.LogisticalApi;
import com.arkui.transportation.base.App;
import com.arkui.transportation.entity.LogisticalListEntity;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.scwang.smartrefresh.layout.api.RefreshLayout;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

/**
 * 基于基类的Fragment
 */
public class HomePublishListFragment extends BaseListLazyFragment<LogisticalListEntity> implements BaseQuickAdapter.RequestLoadMoreListener {

    @BindView(R.id.rl_list)
    PullRefreshRecyclerView mRlList;
    private CommonAdapter<LogisticalListEntity> mCommonAdapter;
    private int mType;
    private LogisticalApi mLogisticalApi;
    public  int page =1;
    public  int pageSize =10;

    /**
     *创建 HomePublishListFragment 实例
     * @param type
     * @return
     */
    public static HomePublishListFragment getInstance(int type) {
        Bundle bundle = new Bundle();
        bundle.putInt("type", type);
        HomePublishListFragment homePublishListFragment = new HomePublishListFragment();
        homePublishListFragment.setArguments(bundle);
        return homePublishListFragment;
    }
    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        return inflater.inflate(R.layout.fragment_home_publish_list, container, false);
    }

    @Override
    protected void initView(View parentView) {
        super.initView(parentView);
        ButterKnife.bind(this, parentView);
        mType = getArguments().getInt("type");
        mLogisticalApi = RetrofitFactory.createRetrofit(LogisticalApi.class);
        mCommonAdapter = initAdapter(mRlList, R.layout.item_logistics);
        mRlList.addItemDecoration(new DividerItemDecoration2(mActivity, DividerItemDecoration2.VERTICAL_LIST));
        mCommonAdapter.setOnLoadMoreListener(this,mRlList.getRecyclerView());
        mCommonAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent=new Intent(mContext,SupplyDetailActivity.class);
                intent.putExtra("type", mType);
                startActivity(intent);
            }
        });
    }
  // 懒加载数据
    @Override
    protected void lazyLoadData() {
       onRefreshing();
    }

    @Override
    public void convert(BaseViewHolder helper, LogisticalListEntity item) {
        //helper.addOnClickListener(R.id.iv_had);
        if(mType==0){ //待发布
            ImageView  header = helper.getView(R.id.iv_head);
          //  GlideUtils.getInstance().loadRound(mActivity,"",header);
             helper.setText(R.id.tv_start_address,item.getLoadingAddress());
            helper.setText(R.id.tv_destination,item.getUnloadingAddress());
            helper.setText(R.id.tv_info,item.getCargoName()+"/"+ item.getCargoNum());
        }else if (mType==1) { // 已经发布
            helper.setText(R.id.tv_start_address,item.getLoadingAddress());
            helper.setText(R.id.tv_destination,item.getUnloadingAddress());
            helper.setText(R.id.tv_info,item.getCargoName()+"/"+ item.getCargoNum());
        }

    }

    /**
     * 请求数据
     */
    public void onRefreshing() {

//        new Handler().postDelayed(new Runnable(){
//            public void run() {
//                mCommonAdapter.addData(ListData.getTestData(""));
//                mRlList.refreshComplete);
//            }
//        }, 300);
        HashMap<String,Object>  hashMap = new HashMap<>();
        hashMap.put("user_id", App.getUserId());
        hashMap.put("log_status",mType);
        hashMap.put("page",page);
        hashMap.put("pageSize",pageSize);
        Observable<List<LogisticalListEntity>> observable = mLogisticalApi.getLogisticalList(hashMap).
                map(new HttpResultFunc<List<LogisticalListEntity>>());
        HttpMethod.getInstance().getNetData(observable, new ProgressSubscriber<List<LogisticalListEntity>>(mActivity,false) {
            @Override
            protected void getDisposable(Disposable d) {
              mDisposables.add(d);
            }

            @Override
            public void onNext(List<LogisticalListEntity> logisticalListEntityList) {
               if (page==1){
                   mCommonAdapter.setNewData(logisticalListEntityList);
                   mRlList.refreshComplete();
                   if(mCommonAdapter.getItemCount()<pageSize){
                       mCommonAdapter.loadMoreEnd(false);
                   }else{
                       mCommonAdapter.loadMoreEnd(true);
                   }
               }else {
                   mCommonAdapter.addData(logisticalListEntityList);
                   mCommonAdapter.loadMoreComplete();
                   if (logisticalListEntityList.size()<pageSize){
                       mCommonAdapter.loadMoreEnd(false);
                   }else {
                       mCommonAdapter.loadMoreEnd(true);
                   }
               }
            }

            @Override
            public void onApiError(ApiException e) {
                super.onApiError(e);
                mCommonAdapter.loadMoreEnd();
                mRlList.refreshComplete();
                mRlList.loadFail();
            }
        });
    }
  //  下拉刷新数据
    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        page=1;
        onRefreshing();
    }
   // 上拉加载更多数据
    @Override
    public void onLoadMoreRequested() {
         page++;
        onRefreshing();
    }
}
