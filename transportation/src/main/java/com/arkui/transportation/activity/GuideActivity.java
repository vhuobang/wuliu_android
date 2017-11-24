package com.arkui.transportation.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.arkui.fz_tools.utils.SPUtil;
import com.arkui.transportation.R;
import com.arkui.transportation.activity.user.LoginActivity;

public class GuideActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private MyViewPagerAdapter myViewPagerAdapter;
    private LinearLayout dotsLayout;
    private TextView[] dots;
    private int[] layouts;
    private Button btnSkip,btnNext;
    ///private PrefManager prefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //在setContentView()前检查是否第一次运行
        boolean isFirst = SPUtil.getInstance(getApplicationContext()).read("isFirst", false);
        if(isFirst){
            launchHomeScreen();
         //   finish();
        }

        //让状态栏透明
        if(Build.VERSION.SDK_INT >= 21){
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE|View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }

        setContentView(R.layout.activity_guide);

        viewPager = (ViewPager)findViewById(R.id.view_pager);
        dotsLayout = (LinearLayout)findViewById(R.id.layoutDots);
        btnNext = (Button) findViewById(R.id.btn_next);
        btnSkip = (Button) findViewById(R.id.btn_skip);

        //添加欢迎页面
        layouts = new int[]{
                R.mipmap.wl_guide1,
                R.mipmap.wl_guide2,
                R.mipmap.wl_guide3,

        };
        //添加点
        addBottomDots(0);

        //让状态栏透明
        changeStatusBarColor();

        myViewPagerAdapter = new MyViewPagerAdapter();
        viewPager.setAdapter(myViewPagerAdapter);
        viewPager.addOnPageChangeListener(viewPagerPageChangeListener);

        btnSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchHomeScreen();
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int current = getItem(+1);
                if(current < layouts.length){
                    viewPager.setCurrentItem(current);
                }else{
                    launchHomeScreen();
                }
            }
        });

    }

    private void addBottomDots(int currentPage){
        dots = new TextView[layouts.length];

        int[] colorsActive = getResources().getIntArray(R.array.array_dot_active);
        int[] colorsInactive = getResources().getIntArray(R.array.array_dot_inactive);

        dotsLayout.removeAllViews();
        for(int i = 0; i < dots.length; i++){
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));//圆点
            dots[i].setTextSize(35);
            dots[i].setTextColor(colorsInactive[currentPage]);
            dotsLayout.addView(dots[i]);
        }

        if(dots.length > 0){
            dots[currentPage].setTextColor(colorsActive[currentPage]);
        }
    }

    private int getItem(int i){
        return viewPager.getCurrentItem() + i;
    }

    private void launchHomeScreen(){
        SPUtil.getInstance(getApplicationContext()).save("isFirst",true);
        startActivity(new Intent(this,LoginActivity.class));
        finish();
    }

    /**
     * 让状态栏变透明
     */
    private void changeStatusBarColor(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }

    ViewPager.OnPageChangeListener viewPagerPageChangeListener = new ViewPager.OnPageChangeListener(){

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            addBottomDots(position);

            //改变下一步按钮text  “NEXT”或“GOT IT”
            if(position == layouts.length - 1){
                btnNext.setText(getString(R.string.start));
                btnSkip.setVisibility(View.GONE);
            }else{
                btnNext.setText(getString(R.string.next));
                btnSkip.setVisibility(View.VISIBLE);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    public class MyViewPagerAdapter extends PagerAdapter {

        private LayoutInflater layoutInflater;

        public MyViewPagerAdapter(){}

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
           // layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            ImageView view = new ImageView(GuideActivity.this);
            view.setBackgroundResource(layouts[position]);
            container.addView(view);
            return view;
        }

        @Override
        public int getCount() {
            return layouts.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            View view = (View)object;
            container.removeView(view);
        }
    }
}
