package com.arkui.fz_tools.view;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.arkui.fz_tools.R;
import com.arkui.fz_tools.entity.RBanner;

import java.util.List;

/**
 * Created by 任少东 on 2016/07/15 14:44
 */
public class RBannerView<T> extends LinearLayout {

    private static final int DelayTime = 4000;
    private static final int CHANGE_PIC = 0;
    private Context context;
    private ViewPager viewPager;
    private RIndicatorView indicator;
    private BannerAdapter adapter;
    private int size;
    private List<T> list;

    private OnItemClickListener onClickListener;
    private LoopTouchListener loopTouchListener;
    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case CHANGE_PIC:
                    viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
                    sendEmptyMessageDelayed(CHANGE_PIC, DelayTime);
                    break;
            }
        }
    };
    private ViewPager.OnPageChangeListener pageChangeListener = new ViewPager.OnPageChangeListener() {

        public void onPageSelected(int arg0) {
            if (size == 0) {
                return;
            }
            indicator.setIndex(arg0 % size);
        }

        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }

        public void onPageScrollStateChanged(int arg0) {

        }
    };
    private OnTouchListener touchListener = new OnTouchListener() {

        public boolean onTouch(View v, MotionEvent event) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                case MotionEvent.ACTION_MOVE:
                    if (loopTouchListener != null) {
                        loopTouchListener.onTouch();
                    }
                    handler.removeMessages(CHANGE_PIC);
                    break;
                case MotionEvent.ACTION_UP:
                case MotionEvent.ACTION_CANCEL:
                    if (loopTouchListener != null) {
                        loopTouchListener.onCancel();
                    }
                    handler.removeMessages(CHANGE_PIC);
                    if (size>1) {
                        handler.sendEmptyMessageDelayed(CHANGE_PIC, DelayTime);
                    }
                    break;
            }
            return false;
        }
    };

    public RBannerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public RBannerView(Context context) {
        this(context, null);
    }

    private void init(Context context) {
        this.context = context;
        LayoutInflater.from(context).inflate(R.layout.view_banner, this);
        viewPager = (ViewPager) findViewById(R.id.banner_viewPager);
        indicator = (RIndicatorView) findViewById(R.id.banner_indicator);
        adapter = new BannerAdapter();
        if (viewPager != null) {
            viewPager.setOnPageChangeListener(pageChangeListener);
            viewPager.setOnTouchListener(touchListener);
            viewPager.setAdapter(adapter);
        }
    }

    public void setData(List<T> list) {
        if (list == null || list.isEmpty()) {
            return;
        }
        this.list = list;
        this.size = list.size();
        initData();
    }

    private void initData() {
        adapter = new BannerAdapter();
        viewPager.setAdapter(adapter);
        indicator.setNum(size);
        viewPager.setCurrentItem(size * 1000);
        handler.removeMessages(CHANGE_PIC);
        if (size <= 1) {
            return;
        }
        handler.sendEmptyMessageDelayed(CHANGE_PIC, DelayTime);
    }

    public void setLoopTouchListener(LoopTouchListener loopTouchListener) {
        this.loopTouchListener = loopTouchListener;
    }

    public void setOnItemClickListener(OnItemClickListener clickListener) {
        this.onClickListener = clickListener;
    }

    public interface OnItemClickListener {
        public void onBannerClick(int position);
    }

    public interface LoopTouchListener {
        public void onTouch();

        public void onCancel();
    }

    private class BannerAdapter extends PagerAdapter {

        private OnClickListener clickListener = new OnClickListener() {

            public void onClick(View v) {
                if (onClickListener != null) {
                  //  int position = (Integer) v.getTag();
                    int currentItem = viewPager.getCurrentItem();
                    int position =currentItem%size;
                    onClickListener.onBannerClick(position);
                }
            }
        };

        public int getCount() {
            if (list == null) {
                return 0;
            }
            return Integer.MAX_VALUE >> 3;
        }

        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        public Object instantiateItem(ViewGroup container, int position) {
            position = position % size;
            ImageView imageView = new ImageView(context);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            //imageView.setTag(position);
            imageView.setOnClickListener(clickListener);
            if(list.get(position) instanceof RBanner){
                imageView.setImageResource(  ((List<RBanner>)list).get(position).getUrl2());
            }else{
                // 甩出加载图片接口 摆脱for循环添加数据
                if(onLoadUiData!=null){
                    onLoadUiData.loopLoadUiData(context,list.get(position),imageView);
                }
            }
           // LoadImg.loadImg(imageView,list.get(position).getUrl());
          //  Glide.with(mContext).load(list.get(position).getUrl2()).diskCacheStrategy(DiskCacheStrategy.ALL).into(imageView);
            container.addView(imageView);
           return imageView;
        }

        public void finishUpdate(View arg0) {
        }

        public void restoreState(Parcelable state, ClassLoader loader) {

        }

        public Parcelable saveState() {
            return null;
        }

        public void startUpdate(View arg0) {
        }

        public int getItemPosition(Object object) {
            return POSITION_NONE;
        }

    }

    //停止轮播图
    public void StopPlay(){
        handler.removeMessages(CHANGE_PIC);
    }
    //开始滚
    public void Start(){
        handler.sendEmptyMessageDelayed(CHANGE_PIC, DelayTime);
    }

    //加载图片
    public void setOnLoadUiData(RBannerView.onLoadUiData onLoadUiData) {
        this.onLoadUiData = onLoadUiData;
    }

    onLoadUiData<T> onLoadUiData;

    public interface onLoadUiData<T>{
        void loopLoadUiData(Context context, T t, ImageView imageView);
    }

}
