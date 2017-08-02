package com.arkui.fz_tools.dialog;

import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arkui.fz_tools.R;
import com.arkui.fz_tools.adapter.CityAdapter;
import com.arkui.fz_tools.adapter.MonthAdapter;
import com.arkui.fz_tools.adapter.StringAdapter;
import com.arkui.fz_tools.entity.TimeEntity;
import com.arkui.fz_tools.listener.OnWheelChangedListener;
import com.arkui.fz_tools.view.WheelView;
import com.arkui.fz_tools.wheelview.utils.DateUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by nmliz on 2017/6/30.
 */

public class EndTimePicker extends BaseDialogFragment implements OnWheelChangedListener {

    private WheelView mWvMonth;
    private WheelView mWvHour;
    private WheelView mWvMin;
    private List<TimeEntity> mTimeList;
    private int month;
    private int day;
    String listItem;

    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        return inflater.inflate(R.layout.dialog_end_time, container, false);
    }

    @Override
    protected void initView(View mRootView) {
        mWvMonth = (WheelView) mRootView.findViewById(R.id.wv_month);
        mWvHour = (WheelView) mRootView.findViewById(R.id.wv_hour);
        mWvMin = (WheelView) mRootView.findViewById(R.id.wv_min);
        initData();
    }

    private void initData() {
        mTimeList = new ArrayList<>();

        List<String> hourList = new ArrayList<>();

        //24小时的集合
        for (int i = 0; i <= 23; i++) {
            hourList.add(DateUtils.fillZero(i) + "时");
        }

        //60分钟集合
        List<String> minList = new ArrayList<>();
        for (int i = 0; i <= 60; i++) {
            minList.add(DateUtils.fillZero(i) + "分");
        }

        Calendar c = Calendar.getInstance();
        month = c.get(Calendar.MONTH) + 1;
        day = c.get(Calendar.DAY_OF_MONTH);
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);
        int year = c.get(Calendar.YEAR);
        //当前时间的集合
        List<String> currentHourList = new ArrayList<>();
        List<String> currentMinList = new ArrayList<>();
        for (int i = hour; i <= 24; i++) {
            currentHourList.add(DateUtils.fillZero(i) + "时");
        }

        for (int i = minute; i <= 60; i++) {
            currentMinList.add(DateUtils.fillZero(i) + "分");
        }

        for (int i = 0; i <= 30; i++) {
            int maxDayOfMonth = c.getActualMaximum(Calendar.DAY_OF_MONTH);
            if (day >= maxDayOfMonth) {
                //跨年了 年份要加1 便于上边那行 判断闰年
                this.month = (this.month += 1) == 13 ? 1 : this.month;
                //this.day = day % maxDayOfMonth;
                //年份加1 日期回归到1 取余和归1貌似一样的
                this.day = 1;
                year += 1;
            } else {
                this.day = day + 1;
            }

            switch (i) {
                case 0:
                    this.listItem = "今天";
                    mTimeList.add(new TimeEntity(listItem, currentHourList, currentMinList));
                    break;
                case 1:
                    this.listItem = "明天";
                    mTimeList.add(new TimeEntity(listItem, hourList, minList));
                    break;
                case 2:
                    this.listItem = "后天";
                    mTimeList.add(new TimeEntity(listItem, hourList, minList));
                    break;
                default:
                    this.listItem = String.format("%02d", this.month) + "月" + String.format("%02d", this.day) +
                            "日";
                    mTimeList.add(new TimeEntity(listItem, hourList, minList));
                    break;
            }
            //便于判断这个月最大天数
            c.set(year, month - 1, day);

        }
        //Log.e("fz", mTimeList.toString());

        MonthAdapter monthAdapter = new MonthAdapter(getActivity(), mTimeList);

        StringAdapter hourAdapter = new StringAdapter(getActivity(), mTimeList.get(0).getHourList());
        StringAdapter minAdapter = new StringAdapter(getActivity(), mTimeList.get(0).getMinList());

        mWvMonth.setViewAdapter(monthAdapter);
        mWvHour.setViewAdapter(hourAdapter);
        mWvMin.setViewAdapter(minAdapter);

        mWvMonth.addChangingListener(this);
    }


    @Override
    public int getGravity() {
        return Gravity.BOTTOM;
    }


    @Override
    public void onChanged(WheelView wheel, int oldValue, int newValue) {
        mWvHour.setViewAdapter(new StringAdapter(getContext(), mTimeList.get(newValue).getHourList()));
        mWvHour.setCurrentItem(0);
        mWvMin.setViewAdapter(new StringAdapter(getContext(), mTimeList.get(newValue).getMinList()));
        mWvMin.setCurrentItem(0);
    }

}
