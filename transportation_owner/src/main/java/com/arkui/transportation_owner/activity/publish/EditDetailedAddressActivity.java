package com.arkui.transportation_owner.activity.publish;

import android.graphics.Color;
import android.os.Handler;
import android.support.v4.content.ContextCompat;

import com.arkui.fz_tools.adapter.CommonAdapter;
import com.arkui.fz_tools.ui.BaseActivity;
import com.arkui.fz_tools.ui.BaseListActivity;
import com.arkui.fz_tools.utils.HistorySearchDividerItem;
import com.arkui.fz_tools.view.PullRefreshRecyclerView;
import com.arkui.transportation_owner.R;
import com.arkui.transportation_owner.utils.ListData;
import com.chad.library.adapter.base.BaseViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class EditDetailedAddressActivity extends BaseListActivity<String> {

    @BindView(R.id.rl_list)
    PullRefreshRecyclerView mRlList;
    private CommonAdapter<String> mCommonAdapter;

    @Override
    public void setRootView() {
        setContentViewNoTitle(R.layout.activity_edit_detailed_address);
    }

    @Override
    public void initView() {
        super.initView();
        ButterKnife.bind(this);

        mCommonAdapter = initAdapter(mRlList, R.layout.item_history_search);
        mRlList.getRecyclerView().addItemDecoration(new HistorySearchDividerItem(mActivity, HistorySearchDividerItem.VERTICAL_LIST));
        mRlList.setRecyclerBackgroundColor(Color.WHITE);

        onRefreshing();
    }


    @OnClick(R.id.tv_cancel)
    public void onClick() {
        finish();
    }

    @Override
    public void onRefreshing() {
        new Handler().postDelayed(new Runnable(){
            public void run() {
                mCommonAdapter.addData(ListData.getTestData(""));
            }
        }, 300);

        mRlList.refreshComplete();
    }

    @Override
    public void convert(BaseViewHolder helper, String item) {
        super.convert(helper, item);
        helper.setText(R.id.tv_name,"金域国际中心A座");
        helper.setText(R.id.tv_name2,"金域国际中心A座");
        helper.setVisible(R.id.tv_name2,true);
    }
}
