package com.arkui.transportation.activity.home;

import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;
import android.widget.Toast;

import com.arkui.fz_tools._interface.HistoricalSearchInterface;
import com.arkui.fz_tools.adapter.CommonAdapter;
import com.arkui.fz_tools.entity.HistocialSearchEntity;
import com.arkui.fz_tools.listener.OnBindViewHolderListener;
import com.arkui.fz_tools.mvp.HistoricalSearchPresenter;
import com.arkui.fz_tools.ui.BaseActivity;
import com.arkui.fz_tools.utils.DividerItemDecoration;
import com.arkui.fz_tools.utils.HistorySearchDividerItem;
import com.arkui.fz_tools.view.PullRefreshRecyclerView;
import com.arkui.fz_tools.view.ShapeEditText;
import com.arkui.transportation.R;
import com.arkui.transportation.adapter.SearchLogisticaAdapter;
import com.arkui.transportation.base.App;
import com.arkui.transportation.entity.CargoSearchListEntity;
import com.arkui.transportation.presenter.CargoSearchPresenter;
import com.arkui.transportation.view.CargoSearchView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class SearchLogisticsActivity extends BaseActivity implements  OnRefreshListener, View.OnClickListener, CargoSearchView, HistoricalSearchInterface {
    @BindView(R.id.rl_list)
    PullRefreshRecyclerView mRlList;
    @BindView(R.id.rl_search)
    PullRefreshRecyclerView mRlSearch;
    @BindView(R.id.tv_next)
    TextView mTvNext;
    @BindView(R.id.et_search)
    ShapeEditText etSearch;

    private SearchLogisticaAdapter mLogisticsAdapter;
    private boolean mIsSelect;
    private List<String> searchHistoryList;
    private CargoSearchPresenter cargoSearchPresenter;
    private CommonAdapter<HistocialSearchEntity> mHistorySearchAdapter;
    private HistoricalSearchPresenter mHistoricalSearchPresenter;

    @Override
    public void setRootView() {
        setContentViewNoTitle(R.layout.activity_search_logistics);
    }

    @Override
    public void initView() {
        super.initView();
        ButterKnife.bind(this);
        mIsSelect = getIntent().getBooleanExtra("isSelect", false);
        cargoSearchPresenter = new CargoSearchPresenter(this, this);
        mHistoricalSearchPresenter = new HistoricalSearchPresenter(this,this);
        mRlList.setLinearLayoutManager();
        mHistorySearchAdapter = new CommonAdapter<HistocialSearchEntity>(R.layout.item_history_search,
                new OnBindViewHolderListener<HistocialSearchEntity>() {
            @Override
            public void convert(BaseViewHolder helper, HistocialSearchEntity item) {
                    helper.setText(R.id.tv_name,item.getContent());
            }
        });
        mRlList.setAdapter(mHistorySearchAdapter);
        getSearchHistoryData();
        mRlList.setEnablePullToRefresh(false);
        mRlList.addItemDecoration(new HistorySearchDividerItem(mActivity, HistorySearchDividerItem.VERTICAL_LIST));
        mRlList.setRecyclerBackgroundColor(ContextCompat.getColor(mActivity, R.color.white));

        View mViewSearchFooter = getLayoutInflater().inflate(R.layout.layout_search_footer, mRlList, false);
        TextView clear = (TextView) mViewSearchFooter.findViewById(R.id.tv_clear);
        clear.setOnClickListener(this);
        mHistorySearchAdapter.setFooterView(mViewSearchFooter);

        mHistorySearchAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                mRlList.setVisibility(View.GONE);
                mRlSearch.setVisibility(View.VISIBLE);
                HistocialSearchEntity item = (HistocialSearchEntity) adapter.getItem(position);
                cargoSearchPresenter.getCargoSearchList(App.getUserId(),item.getContent());
            }
        });
        // 搜索结果的 adapter
        mLogisticsAdapter = new SearchLogisticaAdapter(R.layout.item_logistics);
        mRlSearch.setLinearLayoutManager();
        mRlSearch.setAdapter(mLogisticsAdapter);
        mRlSearch.setOnRefreshListener(this);
        mRlSearch.addItemDecoration(new DividerItemDecoration(mActivity, DividerItemDecoration.VERTICAL_LIST));

        mLogisticsAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                CargoSearchListEntity  item = (CargoSearchListEntity) adapter.getItem(position);
                SupplyDetailActivity.openActivity(mActivity, Integer.parseInt(item.getLogisticalStatus()),item.getId());
            }
        });
        initSearch();
    }

    private void getSearchHistoryData() {
        /************************************************************/
        // 从sp中拿历史搜索数据
//        String searchHistory = SPUtil.getInstance(this).read("searchHistory", "");
//        String[] split = searchHistory.split(",");
//        if (split!=null){
//            searchHistoryList = new ArrayList<>();
//            for (int i=0;i<split.length;i++){
//                searchHistoryList.add(split[i]);
//            }
//        }
        /***********************************************************/
        mHistoricalSearchPresenter.getSearchList(App.getUserId(),"logistical");


    }

    /**
     * 初始化搜索 点击搜索按钮进行搜索
     */
    private void initSearch() {
        etSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {

                    String keyWords = etSearch.getText().toString().trim();
                    if (TextUtils.isEmpty(keyWords)){
                        Toast.makeText(mActivity,"请输入关键字体",Toast.LENGTH_SHORT).show();
                        return false;
                    }
                    mRlList.setVisibility(View.GONE);
                    mRlSearch.setVisibility(View.VISIBLE);
                    cargoSearchPresenter.getCargoSearchList(App.getUserId(),keyWords);
                    return true;
                }

                return  false;
            }
        });
    }


    @OnClick({R.id.tv_cancel, R.id.tv_next})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_cancel:
                finish();
                break;
            case R.id.tv_next:
                //showActivity(PublishDeclareActivity.class);
                break;
            case R.id.tv_clear: // 清空所有记录
              //  SPUtil.getInstance(mActivity).remove("searchHistory");
             //   getSearchHistoryData();
                // 清除数据
                mHistoricalSearchPresenter.delSearchList(App.getUserId(),"logistical");
                break;
        }
    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        mRlSearch.refreshComplete();
    }
    // 搜索成功
    @Override
    public void onSuccess(List<CargoSearchListEntity> cargoSearchListEntities) {
         mRlSearch.refreshComplete();
         mLogisticsAdapter.setNewData(cargoSearchListEntities);

    }
   // 搜索失败
    @Override
    public void onFail(String errorMessage) {
        Toast.makeText(mActivity,errorMessage,Toast.LENGTH_SHORT).show();
        mLogisticsAdapter.setNewData(null);
        mRlSearch.loadFail();

    }
   // 删除成功的回掉
    @Override
    public void onSuccess(String successMessage) {
         getSearchHistoryData();
    }
  // 搜索失败 或者删除失败
    @Override
    public void onSearchFail(String errorMessage) {
        mHistorySearchAdapter.setNewData(null);
    }
  // 历史搜索记录
    @Override
    public void onSearchListSuccess(List<HistocialSearchEntity> histocialSearchEntity) {
        mHistorySearchAdapter.setNewData(histocialSearchEntity);
    }
}
