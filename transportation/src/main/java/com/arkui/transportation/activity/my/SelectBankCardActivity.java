package com.arkui.transportation.activity.my;

import android.view.View;
import android.widget.Toast;

import com.arkui.fz_tools._interface.BinkListInterface;
import com.arkui.fz_tools._interface.PublicInterface;
import com.arkui.fz_tools.adapter.CommonAdapter;
import com.arkui.fz_tools.entity.BankCarEntity;
import com.arkui.fz_tools.listener.OnBindViewHolderListener;
import com.arkui.fz_tools.mvp.BankCarListPresenter;
import com.arkui.fz_tools.mvp.BankPresenter;
import com.arkui.fz_tools.ui.BaseActivity;
import com.arkui.fz_tools.utils.DividerItemDecoration;
import com.arkui.fz_tools.view.PullRefreshRecyclerView;
import com.arkui.transportation.R;
import com.arkui.transportation.base.App;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class SelectBankCardActivity extends BaseActivity implements  OnBindViewHolderListener<BankCarEntity>,OnRefreshListener, BinkListInterface, PublicInterface {

    @BindView(R.id.rl_bank)
    PullRefreshRecyclerView mRlBank;
    private CommonAdapter<BankCarEntity> mSelectBankCardAdapter;
    private BankCarListPresenter bankCarListPresenter;
    private BankPresenter bankPresenter;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_select_bank_card);
        setTitle("选择银行卡");
    }

    @Override
    public void initView() {
        super.initView();
        ButterKnife.bind(this);
        bankCarListPresenter = new BankCarListPresenter(this,this);
        bankPresenter = new BankPresenter(this, this);
        mRlBank.setLinearLayoutManager();
        mRlBank.addItemDecoration(new DividerItemDecoration(mActivity,DividerItemDecoration.VERTICAL_LIST));
        mSelectBankCardAdapter = CommonAdapter.getInstance(R.layout.item_select_bank,this);
        mRlBank.setAdapter(mSelectBankCardAdapter);
        mRlBank.setOnRefreshListener(this);
        onRefreshing();

        mSelectBankCardAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                   switch (view.getId()){
                       case R.id.iv_del:
                           BankCarEntity item = (BankCarEntity) adapter.getItem(position);
                           bankPresenter.delBank(item.getId());
                           break;
                   }
            }
        });

    }

    public void onRefreshing() {

        bankCarListPresenter.getBankCarList(App.getUserId());
    }

    @Override
    public void convert(BaseViewHolder helper, BankCarEntity item) {
          helper.setText(R.id.tv_name,item.getNote());
          helper.setText(R.id.tv_content,item.getNumber());
          helper.addOnClickListener(R.id.iv_del);
    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        onRefreshing();
    }

    @Override
    public void onSuccess(List<BankCarEntity> BankCarList) {
        mRlBank.refreshComplete();
        mSelectBankCardAdapter.setNewData(BankCarList);

    }

    @Override
    public void onSuccess() {
        Toast.makeText(SelectBankCardActivity.this,"删除成功",Toast.LENGTH_SHORT).show();
        onRefreshing();
    }

    @Override
    public void onFail(String errorMessage) {
        Toast.makeText(SelectBankCardActivity.this,errorMessage,Toast.LENGTH_SHORT).show();
        mRlBank.refreshComplete();
        mRlBank.loadFail();
        mSelectBankCardAdapter.setNewData(null);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bankCarListPresenter.onDestroy();
        bankPresenter.onDestroy();
    }
}
