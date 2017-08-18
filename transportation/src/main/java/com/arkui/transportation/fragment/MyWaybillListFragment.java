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
import com.arkui.transportation.activity.waybill.DriverLocationActivity;
import com.arkui.transportation.activity.waybill.PlanPublishDetailActivity;
import com.arkui.transportation.activity.waybill.WaybillDetailActivity;
import com.arkui.transportation.adapter.CarGoListAdapter;
import com.arkui.transportation.base.App;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 基于基类的Fragment
 */
public class MyWaybillListFragment extends BaseLazyFragment implements  OnRefreshListener, CarGoListInterface {

    @BindView(R.id.rl_list)
    PullRefreshRecyclerView mRlList;
    private BaseQuickAdapter mListAdapter;
    private int mType;
    private CarGoListAdapter mCarGoListAdapter;
    private CarGoListPresenter carGoListPresenter;
    int page =1;
    int pageSize =2;

    // 提供一个实例
    public static MyWaybillListFragment getInstance(int type) {
        Bundle bundle = new Bundle();
        bundle.putInt("type", type);
        MyWaybillListFragment waybillListFragment = new MyWaybillListFragment();
        waybillListFragment.setArguments(bundle);
        return waybillListFragment;
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
       switch (mType){
           case 0:
           case 1:
               mListAdapter = new CarGoListAdapter(R.layout.item_my_waybill_list);
               break;
           case 2:
           case 3:
           case 4:
           case 5:

               break;
        }

        mRlList.setAdapter(mListAdapter);
        mRlList.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL_LIST));

        mListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

                switch (mType) {
                    case 0:
                        CarGoListEntity item = (CarGoListEntity) adapter.getItem(position);
                        Bundle bundle = new Bundle();
                        bundle.putString("carGoId",item.getId());
                        showActivity(PlanPublishDetailActivity.class,bundle);
                        break;
                    case 1:
                        showActivity(CarriageDetailActivity.class);
                        break;
                    case 2:
                    case 3:
                        WaybillDetailActivity.openActivity(mContext, 3, true, "");
                        //showActivity(CarriageDetailActivity.class);
                        break;
                    case 4:
                        WaybillDetailActivity.openActivity(mContext, 2, true, "");
                        break;
                    case 5:
                        WaybillDetailActivity.openActivity(mContext, 4, true, "");
                        break;
                }
            }
        });

        mListAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                showActivity(DriverLocationActivity.class);
            }
        });
    }

    @Override
    protected void lazyLoadData() {
        switch (mType) {
            case 0:
            case 1:
                //已发布 预发布接口
                loadCarGoListData();
                break;
            case 2:
            case 3:
            case 4:
            case 5:// 运单列表
                loadWayBillListData();
                break;

        }
    }

    /**
     * 运单列表
     */
    private void loadWayBillListData() {

    }

    /**
     * 预发布 已发布
     */
    private void loadCarGoListData() {
        switch (mType){
            case 0:
                carGoListPresenter.getCarGoList(App.getUserId(),"0",page,pageSize);
                break;
            case 1:
                carGoListPresenter.getCarGoList(App.getUserId(),"1",page,pageSize);
                break;
        }

    }



//
//    public void convert(BaseViewHolder helper, String item) {
//        switch (mType) {
//            case 0:
//                helper.setVisible(R.id.tv_company, true);
//                helper.setText(R.id.tv_company, "500元/吨 7日内结算");
//                break;
//            case 1:
//                helper.setVisible(R.id.tv_company, true);
//                helper.setVisible(R.id.tv_state, true);
//                helper.setText(R.id.tv_company, "500元/吨 7日内结算");
//                break;
//            case 2:
//            case 3:
//                helper.setVisible(R.id.ll_location, true);
//                helper.setVisible(R.id.tv_state, false);
//                break;
//            case 4:
//                helper.setVisible(R.id.ll_location, false);
//                helper.setVisible(R.id.tv_cost, true);
//                helper.setText(R.id.tv_company, "北京美华国际物流有限公司");
//                break;
//            case 5:
//                break;
//          /*  case 6:
//                helper.setVisible(R.id.ll_location, false);
//                helper.setVisible(R.id.tv_state, false);
//                break;*/
//        }
//
     //  helper.addOnClickListener(R.id.ll_location);
//    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        page=1;
        loadCarGoListData();
    }

    /**
     * 预发布 已发布 数据成功回调
     * @param carGoListEntityList
     */
    @Override
    public void onCarGoListSuccess(List<CarGoListEntity> carGoListEntityList) {
        if (page==1){
            mListAdapter.setNewData(carGoListEntityList);
            mRlList.refreshComplete();
            if(mListAdapter.getItemCount()<10){
                mListAdapter.loadMoreEnd(false);
            }else{
                mListAdapter.loadMoreEnd(true);
            }
        }else {
            mListAdapter.addData(carGoListEntityList);
            mListAdapter.loadMoreComplete();
            if (carGoListEntityList.size()<pageSize){
                mListAdapter.loadMoreEnd(false);
            }else {
                mListAdapter.loadMoreEnd(true);
            }

        }

    }

    /**
     * 已发布 预发布 数据失败回调
     * @param errorMessage
     */
    @Override
    public void onCarGoListFail(String errorMessage) {
        mListAdapter.loadMoreEnd();
        mRlList.refreshComplete();
        mRlList.loadFail();
    }
}
