package com.arkui.transportation_owner.activity.logistics;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;
import android.widget.Toast;

import com.arkui.fz_net.utils.RxBus;
import com.arkui.fz_tools.adapter.CommonAdapter;
import com.arkui.fz_tools.entity.LogisticsBusEntity;
import com.arkui.fz_tools.listener.OnBindViewHolderListener;
import com.arkui.fz_tools.ui.BaseActivity;
import com.arkui.fz_tools.utils.AppManager;
import com.arkui.fz_tools.utils.DividerItemDecoration;
import com.arkui.fz_tools.utils.HistorySearchDividerItem;
import com.arkui.fz_tools.view.PullRefreshRecyclerView;
import com.arkui.fz_tools.view.ShapeEditText;
import com.arkui.transportation_owner.R;
import com.arkui.transportation_owner.activity.publish.LogisticsListActivity;
import com.arkui.transportation_owner.activity.publish.PublishDeclareActivity;
import com.arkui.transportation_owner.adapter.LogisticsAdapter;
import com.arkui.transportation_owner.entity.LogisticalListEntity;
import com.arkui.transportation_owner.entity.RefreshLogistics;
import com.arkui.transportation_owner.entity.RefreshWaybill;
import com.arkui.transportation_owner.mvp.LogisticsPresenter;
import com.arkui.transportation_owner.utils.ListData;
import com.arkui.transportation_owner.view.LogisticsView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;


public class SearchLogisticsActivity extends BaseActivity<String> implements OnBindViewHolderListener<String>, OnRefreshListener, BaseQuickAdapter.OnItemChildClickListener, LogisticsView, TextView.OnEditorActionListener, BaseQuickAdapter.OnItemClickListener {

    @BindView(R.id.rl_list)
    PullRefreshRecyclerView mRlList;
    @BindView(R.id.rl_search)
    PullRefreshRecyclerView mRlSearch;
    @BindView(R.id.tv_next)
    TextView mTvNext;
    @BindView(R.id.et_search)
    ShapeEditText mEtSearch;
    private LogisticsAdapter mLogisticsAdapter;
    private boolean mIsSelect;
    private LogisticsPresenter mLogisticsPresenter;
    private int mPage = 1;
    private boolean mIsChange=false;

    @Override
    public void setRootView() {
        setContentViewNoTitle(R.layout.activity_search_logistics);
    }

    @Override
    public void initView() {
        super.initView();
        ButterKnife.bind(this);
        mIsSelect = getIntent().getBooleanExtra("isSelect", false);
        mRlList.setLinearLayoutManager();
        CommonAdapter<String> mHistorySearchAdapter = new CommonAdapter<String>(R.layout.item_history_search, this);
        mRlList.setAdapter(mHistorySearchAdapter);
        mHistorySearchAdapter.addData(ListData.getTestData(""));
        mRlList.setEnablePullToRefresh(false);
        mRlList.addItemDecoration(new HistorySearchDividerItem(mActivity, HistorySearchDividerItem.VERTICAL_LIST));
        mRlList.setRecyclerBackgroundColor(ContextCompat.getColor(mActivity, R.color.white));

        View mViewSearchFooter = getLayoutInflater().inflate(R.layout.layout_search_footer, mRlList, false);
        mHistorySearchAdapter.setFooterView(mViewSearchFooter);

        mHistorySearchAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                hideSearch();
            }
        });
        initAdapter();
        mEtSearch.setOnEditorActionListener(this);
        //回调收藏状态 改变监听
        Disposable subscribe = RxBus.getDefault().toObservable(LogisticsBusEntity.class).subscribe(new Consumer<LogisticsBusEntity>() {
            @Override
            public void accept(LogisticsBusEntity logisticsBusEntity) throws Exception {
                if(LogisticsBusEntity.SEARCH_LOGISTICS==logisticsBusEntity.getType()){
                    mLogisticsAdapter.getItem(logisticsBusEntity.getPosition()).setStatus(logisticsBusEntity.getStatus());
                    mLogisticsAdapter.notifyItemChanged(logisticsBusEntity.getPosition());
                }
            }
        });

        mDisposables.add(subscribe);
    }

    private void hideSearch() {
        mRlList.setVisibility(View.GONE);
        mRlSearch.setVisibility(View.VISIBLE);
        if (mIsSelect) {
            mTvNext.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void initData() {
        super.initData();
        mLogisticsPresenter = new LogisticsPresenter(this, mActivity);
    }

    private void initAdapter() {
        mRlSearch.setLinearLayoutManager();
        mRlSearch.setOnRefreshListener(this);
        mRlSearch.addItemDecoration(new DividerItemDecoration(mActivity, DividerItemDecoration.VERTICAL_LIST));
        mRlSearch.setEnablePullToRefresh(false);
        mLogisticsAdapter = new LogisticsAdapter();
        mRlSearch.setAdapter(mLogisticsAdapter);
        mLogisticsAdapter.setOnItemClickListener(this);
        mLogisticsAdapter.setOnItemChildClickListener(this);
    }


    @OnClick({R.id.tv_cancel, R.id.tv_next})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_cancel:
                finish();
                break;
            case R.id.tv_next:
                //showActivity(PublishDeclareActivity.class);
                AppManager.getAppManager().finishActivity(LogisticsListActivity.class);
                if(mIsChange){
                    //发送给下一页 让他执行刷新
                    RxBus.getDefault().post(new RefreshLogistics(101));
                }
                finish();
                break;
        }
    }


    @Override
    public void convert(BaseViewHolder helper, String item) {

    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {

    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        String id = mLogisticsAdapter.getItem(position).getId();
        mLogisticsPresenter.postCollectionLogistical(id,position);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        String id = mLogisticsAdapter.getItem(position).getId();
        String name = mLogisticsAdapter.getItem(position).getName();
        String authTatus = mLogisticsAdapter.getItem(position).getAuthTatus();
        Intent intent = new Intent();
        intent.putExtra("id", id);
        intent.putExtra("title", name);
        intent.putExtra("position", position);
        intent.putExtra("type", LogisticsBusEntity.SEARCH_LOGISTICS);

        if ("1".equals(authTatus)) {
            //个人
            intent.setClass(mActivity, PersonageDetailActivity.class);
            startActivity(intent);
        } else if ("2".equals(authTatus)) {
            //公司
            intent.setClass(mActivity, CompanyDetailActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(mActivity, "这是一条不规范的数据", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onSucceed(List<LogisticalListEntity> logisticalList) {
        if (mPage == 1) {
            mLogisticsAdapter.setNewData(logisticalList);
            mRlSearch.refreshComplete();
            mLogisticsAdapter.setEnableLoadMore(logisticalList.size() == 20);
        } else {
            mLogisticsAdapter.addData(logisticalList);
            mLogisticsAdapter.loadMoreComplete();
        }
        mPage += 1;
    }

    @Override
    public void onError() {
        if (mPage == 1) {
            mRlSearch.loadFail();
        } else {
            mLogisticsAdapter.loadMoreEnd();
        }
        mRlSearch.refreshComplete();
    }

    @Override
    public void onSucceed(LogisticalListEntity logisticalDetails) {

    }

    @Override
    public void onSucceed(int position) {
        mIsChange=true;
        mLogisticsAdapter.getItem(position).setStatus("1".equals(mLogisticsAdapter.getItem(position).getStatus())?"0":"1");
        mLogisticsAdapter.notifyItemChanged(position);
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if(actionId == EditorInfo.IME_ACTION_SEARCH){
            String searchStr = mEtSearch.getText().toString().trim();
            if(TextUtils.isEmpty(searchStr)){
                ShowToast("请输入搜索文字");
            }else{
                hideSearch();
                mRlSearch.starLoad();
                mPage=1;
                mLogisticsPresenter.postLogisticsList("",searchStr,mPage);
            }
        }
            return false;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mLogisticsPresenter != null) {
            mLogisticsPresenter.onDestroy();
        }
    }

}
