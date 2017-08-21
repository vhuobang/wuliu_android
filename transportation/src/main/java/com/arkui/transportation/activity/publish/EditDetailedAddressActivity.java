package com.arkui.transportation.activity.publish;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;

import com.amap.api.services.core.AMapException;
import com.amap.api.services.help.Inputtips;
import com.amap.api.services.help.InputtipsQuery;
import com.amap.api.services.help.Tip;
import com.arkui.fz_tools.ui.BaseListActivity;
import com.arkui.fz_tools.utils.HistorySearchDividerItem;
import com.arkui.fz_tools.view.PullRefreshRecyclerView;
import com.arkui.fz_tools.view.ShapeEditText;
import com.arkui.transportation.R;
import com.arkui.transportation.adapter.SearchAddressAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class EditDetailedAddressActivity extends BaseListActivity<String> implements Inputtips.InputtipsListener, BaseQuickAdapter.OnItemClickListener {

    @BindView(R.id.rl_list)
    PullRefreshRecyclerView mRlList;
    @BindView(R.id.et_search)
    ShapeEditText mEtSearch;
    private SearchAddressAdapter mSearchAddressAdapter;
    private InputtipsQuery mInputQuery;
    private Inputtips mInputTips;
    private String mCity;

    @Override
    public void setRootView() {
        setContentViewNoTitle(R.layout.activity_edit_detailed_address);
    }

    @Override
    public void initView() {
        super.initView();
        ButterKnife.bind(this);
        mSearchAddressAdapter = new SearchAddressAdapter();
        mRlList.getRecyclerView().addItemDecoration(new HistorySearchDividerItem(mActivity, HistorySearchDividerItem.VERTICAL_LIST));
        mRlList.setRecyclerBackgroundColor(Color.WHITE);
        mEtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String searchStr = mEtSearch.getText().toString().trim();
                if (TextUtils.isEmpty(searchStr)) {
                    mRlList.loadFail("请输入搜索地址");
                    return;
                }
                mInputQuery = new InputtipsQuery(searchStr, mCity);
                mInputQuery.setCityLimit(true);//限制在当前城市
                mInputTips = new Inputtips(mActivity, mInputQuery);
                mInputTips.setInputtipsListener(EditDetailedAddressActivity.this);
                mInputTips.requestInputtipsAsyn();
            }
        });
        mRlList.setLinearLayoutManager();
        mRlList.setAdapter(mSearchAddressAdapter);
        mRlList.loadFail("请输入搜索地址");
        mRlList.setEnablePullToRefresh(false);
        mSearchAddressAdapter.setOnItemClickListener(this);
    }

    @Override
    public void initData() {
        super.initData();
        mCity = getIntent().getStringExtra("city");
    }

    @OnClick(R.id.tv_cancel)
    public void onClick() {
        finish();
    }


    @Override
    public void onGetInputtips(List<Tip> list, int rCode) {
        if (rCode == AMapException.CODE_AMAP_SUCCESS) {
            if(list.isEmpty()){
                mRlList.loadFail();
            }else{
                mSearchAddressAdapter.setNewData(list);
            }

        }

    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        //回调给下一页
        String name = mSearchAddressAdapter.getItem(position).getName();
        String address = mSearchAddressAdapter.getItem(position).getAddress();

        Intent intent=new Intent();
        intent.putExtra("address",name+address);
        setResult(Activity.RESULT_OK,intent);
        finish();
    }
}
