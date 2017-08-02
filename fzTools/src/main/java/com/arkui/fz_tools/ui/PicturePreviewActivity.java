package com.arkui.fz_tools.ui;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;


import com.arkui.fz_tools.R;
import com.arkui.fz_tools.view.AlbumViewPager;
import com.arkui.fz_tools.view.MatrixImageView;

import java.util.ArrayList;
import java.util.List;



public class PicturePreviewActivity extends BaseActivity implements MatrixImageView.OnSingleTapListener, View.OnClickListener {


    AlbumViewPager mAlbumviewpager;
    TextView mTvCount;
    private ArrayList<String> data;

    @Override
    public void setRootView() {
        setContentViewNoTitle(R.layout.activity_picture_preview);
    }

    @Override
    public void initView() {
        super.initView();
        mAlbumviewpager = (AlbumViewPager) findViewById(R.id.albumviewpager);
        mTvCount = (TextView) findViewById(R.id.tv_count);
        findViewById(R.id.activity_picture_preview).setOnClickListener(this);
    }

    @Override
    public void initData() {
        super.initData();
        data = getIntent().getStringArrayListExtra("data");
        int index = getIntent().getIntExtra("index", 0);
        showViewPager(index);
    }

    //显示大图pager
    private void showViewPager(int index) {
        mAlbumviewpager.setAdapter(mAlbumviewpager.new LocalViewPagerAdapter(data));
        mAlbumviewpager.setCurrentItem(index);
        mTvCount.setText((index + 1) + "/" + data.size());
        mAlbumviewpager.setOnPageChangeListener(pageChangeListener);
        mAlbumviewpager.setOnSingleTapListener(this);
    }

    private ViewPager.OnPageChangeListener pageChangeListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageSelected(int position) {
            if (mAlbumviewpager.getAdapter() != null) {
                String text = (position + 1) + "/" + mAlbumviewpager.getAdapter().getCount();
                mTvCount.setText(text);
            } else {
                mTvCount.setText("0/0");
            }
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }

        @Override
        public void onPageScrollStateChanged(int arg0) {

        }
    };

    @Override
    public void onSingleTap() {
        finish();
    }

    public static void startPicActivity(Context context, List<String> url, int position) {

        Intent intent = new Intent(context, PicturePreviewActivity.class);
        intent.putExtra("data", (ArrayList) url);
        intent.putExtra("index", position);
        context.startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        finish();
    }
}
