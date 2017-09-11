package com.arkui.transportation.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arkui.fz_tools._interface.CarGoListInterface;
import com.arkui.fz_tools.entity.CarGoListEntity;
import com.arkui.fz_tools.mvp.CarGoListPresenter;
import com.arkui.fz_tools.ui.BaseLazyFragment;
import com.arkui.fz_tools.utils.DividerItemDecoration;
import com.arkui.fz_tools.view.PullRefreshRecyclerView;
import com.arkui.transportation.R;
import com.arkui.transportation.activity.waybill.CarriageDetailActivity;
import com.arkui.transportation.activity.waybill.PlanPublishDetailActivity;
import com.arkui.transportation.adapter.CarGoListAdapter;
import com.arkui.transportation.base.App;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 84658 on 2017/8/21.
 *
 * 预发布和已发布 fragment
 */

public class PublishFragment extends BaseLazyFragment implements OnRefreshListener, CarGoListInterface {

    @BindView(R.id.rl_list)
    PullRefreshRecyclerView mRlList;
    private int mType;
    private CarGoListAdapter mCarGoListAdapter;
    private CarGoListPresenter carGoListPresenter;
    int page = 1;
    int pageSize = 10;

    // 提供一个实例
    public static PublishFragment getInstance(int type) {
        Bundle bundle = new Bundle();
        bundle.putInt("type", type);
        PublishFragment publishFragment = new PublishFragment();
        publishFragment.setArguments(bundle);
        return publishFragment;
    }

    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        return inflater.inflate(R.layout.fragment_waybill_list, container, false);
    }

    @Override
    protected void initView(View parentView) {
        super.initView(parentView);
        ButterKnife.bind(this, parentView);
        mRlList.setLinearLayoutManager();
        mRlList.setOnRefreshListener(this);
        mType = getArguments().getInt("type");
        //请求已发布预发布的p层
        carGoListPresenter = new CarGoListPresenter(this, getActivity());
        // 根据不同的type设置不同的adapter
        mCarGoListAdapter = new CarGoListAdapter(R.layout.item_my_waybill_list);
        initReleaseAdapter();
    }

    private void initReleaseAdapter() {
        mRlList.setAdapter(mCarGoListAdapter);
        mRlList.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL_LIST));

        mCarGoListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                CarGoListEntity item = (CarGoListEntity) adapter.getItem(position);
                switch (mType) {
                    case 1:
                        Bundle bundle = new Bundle();
                        bundle.putString("carGoId", item.getId());
                        showActivity(PlanPublishDetailActivity.class, bundle);
                        break;
                    case 2:
                        Bundle bundle2 = new Bundle();
                        bundle2.putString("carGoId", item.getId());
                        bundle2.putString("c_status",item.getCStatus());
                        showActivity(CarriageDetailActivity.class,bundle2);
                        break;
                }
            }
        });


    }

    @Override
    protected void lazyLoadData() {
        //已发布 预发布接口
        loadCarGoListData();
    }

    /**
     * 预发布 已发布
     */
    private void loadCarGoListData() {
        switch (mType) {
            case 1:
                carGoListPresenter.getCarGoList(App.getUserId(), "0", page, pageSize);
                break;
            case 2:
                carGoListPresenter.getCarGoList(App.getUserId(), "1", page, pageSize);
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
       loadCarGoListData();
    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        page = 1;
        loadCarGoListData();
    }

    /**
     * 预发布 已发布 数据成功回调
     *
     * @param carGoListEntityList
     */

    @Override
    public void onCarGoListSuccess(List<CarGoListEntity> carGoListEntityList) {
        if (page == 1) {
            mCarGoListAdapter.setNewData(carGoListEntityList);
            mRlList.refreshComplete();
            if (mCarGoListAdapter.getItemCount() < pageSize) {
                mCarGoListAdapter.loadMoreEnd(false);
            } else {
                mCarGoListAdapter.loadMoreEnd(true);
            }
        } else {
            mCarGoListAdapter.addData(carGoListEntityList);
            mCarGoListAdapter.loadMoreComplete();
            if (carGoListEntityList.size() < pageSize) {
                mCarGoListAdapter.loadMoreEnd(false);
            } else {
                mCarGoListAdapter.loadMoreEnd(true);
            }
        }

    }

    /**
     * 已发布 预发布 数据失败回调
     *
     * @param errorMessage
     */
    @Override
    public void onCarGoListFail(String errorMessage) {
        mCarGoListAdapter.loadMoreEnd();
        mRlList.refreshComplete();
        mRlList.loadFail();
    }
}
