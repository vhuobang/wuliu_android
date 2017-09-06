package com.arkui.fz_tools.view;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewStub;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.arkui.fz_tools.R;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.w3c.dom.Text;

/**
 * Created by nmliz on 2017/6/20.
 */

public class PullRefreshRecyclerView extends FrameLayout {

    private ViewStub mProgress;
    private SmartRefreshLayout mPtrLayout;
    private ViewStub mEmpty;
    private RecyclerView mRecycler;
    private ImageView mIvProgress;
    private AnimationDrawable mAnimationDrawable;
    private TextView mTvFailName;

    public PullRefreshRecyclerView(@NonNull Context context) {
        super(context);
        initView();
    }

    public PullRefreshRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public PullRefreshRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        View v = LayoutInflater.from(getContext()).inflate(R.layout.view_progress_recycler, this);
        mPtrLayout = (SmartRefreshLayout) v.findViewById(R.id.ptr_layout);
        mPtrLayout.setRefreshHeader(new RefreshHeaderView(getContext()));
        mPtrLayout.setHeaderHeight(70);

        mRecycler = (RecyclerView) v.findViewById(R.id.recycler);

        mProgress = (ViewStub) v.findViewById(android.R.id.progress);
        mProgress.setLayoutResource(R.layout.view_progress);
        View inflate = mProgress.inflate();
        mIvProgress = (ImageView) inflate.findViewById(R.id.iv_progress);
        mIvProgress.setImageResource(R.drawable.refresh_loading);
        mAnimationDrawable = (AnimationDrawable) mIvProgress.getDrawable();
        mAnimationDrawable.start();

        mEmpty = (ViewStub) v.findViewById(R.id.empty);
        mEmpty.setLayoutResource(R.layout.view_empty);
        View empty_view = mEmpty.inflate();
        mTvFailName = (TextView) empty_view.findViewById(R.id.tv_name);
        mEmpty.setVisibility(View.GONE);
    }

    public void setAdapter(RecyclerView.Adapter adapter) {
        if (adapter != null) {
            mRecycler.setAdapter(adapter);
            RecyclerView.AdapterDataObserver observer = new RecyclerView.AdapterDataObserver() {
                @Override
                public void onChanged() {
                    super.onChanged();
                    if (mRecycler.getAdapter().getItemCount() > 0) {
                        mRecycler.setVisibility(View.VISIBLE);
                        mProgress.setVisibility(View.GONE);
                        mEmpty.setVisibility(View.GONE);
                        mAnimationDrawable.stop();
                    } else {
                        mAnimationDrawable.start();
                        mRecycler.setVisibility(View.GONE);
                        mEmpty.setVisibility(View.GONE);
                        mProgress.setVisibility(View.VISIBLE);
                    }
                }
            };
            adapter.registerAdapterDataObserver(observer);
            observer.onChanged();
        } else {
            throw new RuntimeException("This RecyclerView has no adapter, you must call setAdapter first!");
        }
    }

    public void setLayoutManager(LinearLayoutManager linearLayoutManager) {
        mRecycler.setLayoutManager(linearLayoutManager);
    }

    public void setLinearLayoutManager() {
        mRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    public void addItemDecoration(RecyclerView.ItemDecoration decor) {
        mRecycler.addItemDecoration(decor);
    }

    public void setOnRefreshListener(OnRefreshListener onRefreshListener) {
        mPtrLayout.setOnRefreshListener(onRefreshListener);
    }

    public void refreshComplete() {
        mPtrLayout.finishRefresh();
        if(mRecycler.getAdapter().getItemCount()==0){
            mProgress.setVisibility(View.GONE);
            mRecycler.setVisibility(View.GONE);
            mAnimationDrawable.stop();
            mEmpty.setVisibility(View.VISIBLE);
        }else{
            mProgress.setVisibility(View.GONE);
            mRecycler.setVisibility(View.VISIBLE);
            mAnimationDrawable.stop();
            mEmpty.setVisibility(View.GONE);
        }
    }

    public void autoRefresh() {
        mPtrLayout.autoRefresh();
    }

    //开启或关闭下拉刷新
    public void setEnablePullToRefresh(boolean enable) {
        mPtrLayout.setEnableRefresh(enable);
    }

    public void setRecyclerBackgroundColor(int color){
        mRecycler.setBackgroundColor(color);
    }

    public RecyclerView getRecyclerView() {
        return mRecycler;
    }

    public void loadFail(){
     //   mRecycler.setVisibility(View.VISIBLE);
        mProgress.setVisibility(View.GONE);
        mRecycler.setVisibility(View.GONE);
        mAnimationDrawable.stop();
        mEmpty.setVisibility(View.VISIBLE);
    }

    public void loadFail(String name){
        loadFail();
        mTvFailName.setText(name);
    }

    //开始动画
    public void starLoad(){
        mAnimationDrawable.start();
        mRecycler.setVisibility(View.GONE);
        mEmpty.setVisibility(View.GONE);
        mProgress.setVisibility(View.VISIBLE);
    }
}
