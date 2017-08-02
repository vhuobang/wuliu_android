package com.arkui.fz_tools.dialog.old;

import android.app.Activity;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.arkui.fz_tools.R;
import com.arkui.fz_tools.dialog.old.bottom.BaseDialog;
import com.arkui.fz_tools.wheelview.WheelView;
import com.arkui.fz_tools.wheelview.utils.ScreenUtils;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * 地址选择器（包括省级、地级、县级）。
 * 地址数据见示例项目的“city.json”，来源于国家统计局官网（http://www.stats.gov.cn/tjsj/tjbz/xzqhdm）
 *
 * @author 李玉江[qq:1032694760]
 * @since 2015/12/15
 */
public class AddressPicker extends BaseDialog implements View.OnClickListener {
    private OnAddressPickListener onAddressPickListener;
    protected ArrayList<String> firstList = new ArrayList<String>();
    protected ArrayList<ArrayList<String>> secondList = new ArrayList<ArrayList<String>>();
    protected ArrayList<ArrayList<ArrayList<String>>> thirdList = new ArrayList<ArrayList<ArrayList<String>>>();
    protected String selectedFirstText = "", selectedSecondText = "", selectedThirdText = "";
    protected int selectedFirstIndex = 0, selectedSecondIndex = 0, selectedThirdIndex = 0;
    public static final int MATCH_PARENT = ViewGroup.LayoutParams.MATCH_PARENT;
    public static final int WRAP_CONTENT = ViewGroup.LayoutParams.WRAP_CONTENT;
    protected Activity activity;
    protected int screenWidthPixels;
    protected int screenHeightPixels;

    /**
     * The Text size.
     */
    protected int textSize = WheelView.TEXT_SIZE;
    /**
     * The Text color normal.
     */
    protected int textColorNormal = WheelView.TEXT_COLOR_NORMAL;
    /**
     * The Text color focus.
     */
    protected int textColorFocus = WheelView.TEXT_COLOR_FOCUS;
    /**
     * The Line color.
     */
    protected int lineColor = 0XFFD1D1D1;
    /**
     * The Line visible.
     */
    protected boolean lineVisible = true;
    /**
     * The Offset.
     */
    protected int offset = WheelView.OFF_SET;

    /**
     * Instantiates a new Address picker.
     *
     * @param activity the mActivity
     * @param data     the data
     */
    public AddressPicker(Activity activity, ArrayList<Province> data) {
        super(activity);
        DisplayMetrics displayMetrics = ScreenUtils.displayMetrics(activity);
        screenWidthPixels = displayMetrics.widthPixels;
        screenHeightPixels = displayMetrics.heightPixels;

        this.activity = activity;
        int provinceSize = data.size();
        //添加省
        for (int x = 0; x < provinceSize; x++) {
            Province pro = data.get(x);
            firstList.add(pro.getAreaName());
            ArrayList<City> cities = pro.getCities();
            ArrayList<String> xCities = new ArrayList<String>();
            ArrayList<ArrayList<String>> xCounties = new ArrayList<ArrayList<String>>();
            int citySize = cities.size();
            //添加地市
            for (int y = 0; y < citySize; y++) {
                City cit = cities.get(y);
                xCities.add(cit.getAreaName());
                ArrayList<County> counties = cit.getCounties();
                ArrayList<String> yCounties = new ArrayList<String>();
                int countySize = counties.size();
                //添加区县
                if (countySize == 0) {
                    yCounties.add(cit.getAreaName());
                } else {
                    for (int z = 0; z < countySize; z++) {
                        yCounties.add(counties.get(z).getAreaName());
                    }
                }
                xCounties.add(yCounties);
            }
            secondList.add(xCities);
            thirdList.add(xCounties);
        }

        //省市区布局

        view = LayoutInflater.from(activity).inflate(R.layout.layout_address, null);

        wv_province = (WheelView) view.findViewById(R.id.wv_province);

        wv_city = (WheelView) view.findViewById(R.id.wv_city);

        wv_county = (WheelView) view.findViewById(R.id.wv_county);

        TextView mtv_cancel = (TextView) view.findViewById(R.id.tv_cancel);
        mtv_cancel.setOnClickListener(this);
        TextView mtv_submit = (TextView) view.findViewById(R.id.tv_submit);
        mtv_submit.setOnClickListener(this);
    }

    View view;
    WheelView wv_province;
    WheelView wv_city;
    WheelView wv_county;

    /**
     * Sets selected item.
     */
    public void setSelectedItem(String firstText, String secondText, String thirdText) {
        for (int i = 0; i < firstList.size(); i++) {
            String ft = firstList.get(i);
            if (ft.contains(firstText)) {
                selectedFirstIndex = i;
                break;
            }
        }
        ArrayList<String> secondTexts = secondList.get(selectedFirstIndex);
        for (int j = 0; j < secondTexts.size(); j++) {
            String st = secondTexts.get(j);
            if (st.contains(secondText)) {
                selectedSecondIndex = j;
                break;
            }
        }
        if (TextUtils.isEmpty(thirdText) || thirdList.size() == 0) {
            return;//仅仅二级联动
        }
        ArrayList<String> thirdTexts = thirdList.get(selectedFirstIndex).get(selectedSecondIndex);
        for (int k = 0; k < thirdTexts.size(); k++) {
            String tt = thirdTexts.get(k);
            if (tt.contains(thirdText)) {
                selectedThirdIndex = k;
                break;
            }
        }
    }

    /**
     * Sets on address pick listener.
     *
     * @param listener the listener
     */
    public void setOnAddressPickListener(OnAddressPickListener listener) {
        this.onAddressPickListener = listener;
    }

    protected View makeCenterView() {
        if (firstList.size() == 0) {
            throw new IllegalArgumentException("please initial data at first, can't be empty");
        }
        final int width = screenWidthPixels / 3;
        wv_province.setLayoutParams(new LinearLayout.LayoutParams(width, WRAP_CONTENT));
        wv_province.setTextSize(textSize);
        wv_province.setTextColor(textColorNormal, textColorFocus);
        wv_province.setLineVisible(lineVisible);
        wv_province.setLineColor(lineColor);
        wv_province.setOffset(offset);
        wv_city.setLayoutParams(new LinearLayout.LayoutParams(width, WRAP_CONTENT));
        wv_city.setTextSize(textSize);
        wv_city.setTextColor(textColorNormal, textColorFocus);
        wv_city.setLineVisible(lineVisible);
        wv_city.setLineColor(lineColor);
        wv_city.setOffset(offset);
        wv_county.setLayoutParams(new LinearLayout.LayoutParams(width, WRAP_CONTENT));
        wv_county.setTextSize(textSize);
        wv_county.setTextColor(textColorNormal, textColorFocus);
        wv_county.setLineVisible(lineVisible);
        wv_county.setLineColor(lineColor);
        wv_county.setOffset(offset);
        wv_province.setItems(firstList, selectedFirstIndex);
        wv_province.setOnWheelViewListener(new WheelView.OnWheelViewListener() {
            @Override
            public void onSelected(boolean isUserScroll, int selectedIndex, String item) {
                selectedFirstText = item;
                selectedFirstIndex = selectedIndex;
                selectedThirdIndex = 0;
                //根据省份获取地市
                wv_city.setItems(secondList.get(selectedFirstIndex), isUserScroll ? 0 : selectedSecondIndex);
                //根据地市获取区县
                wv_county.setItems(thirdList.get(selectedFirstIndex).get(0), isUserScroll ? 0 : selectedThirdIndex);
            }
        });
        wv_city.setItems(secondList.get(selectedFirstIndex), selectedSecondIndex);
        wv_city.setOnWheelViewListener(new WheelView.OnWheelViewListener() {
            @Override
            public void onSelected(boolean isUserScroll, int selectedIndex, String item) {
                selectedSecondText = item;
                selectedSecondIndex = selectedIndex;
                //根据地市获取区县
                wv_county.setItems(thirdList.get(selectedFirstIndex).get(selectedSecondIndex), isUserScroll ? 0 : selectedThirdIndex);
            }
        });
        wv_county.setItems(thirdList.get(selectedFirstIndex).get(selectedSecondIndex), selectedThirdIndex);
        wv_county.setOnWheelViewListener(new WheelView.OnWheelViewListener() {
            @Override
            public void onSelected(boolean isUserScroll, int selectedIndex, String item) {
                selectedThirdText = item;
                selectedThirdIndex = selectedIndex;
            }
        });
        return view;
    }

    public void onSubmit() {
        if (onAddressPickListener != null) {
            onAddressPickListener.onAddressPicked(selectedFirstText, selectedSecondText, selectedThirdText);
        }
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.tv_cancel) {
            dismiss();

        } else if (i == R.id.tv_submit) {
            onSubmit();
            dismiss();

        }
    }

    /**
     * The interface On address pick listener.
     */
    public interface OnAddressPickListener {

        /**
         * On address picked.
         *
         * @param province the province
         * @param city     the city
         * @param county   the county ，if {@hideCounty} is true，this is null
         */
        void onAddressPicked(String province, String city, String county);

    }

    /**
     * The type Area.
     */
    public abstract static class Area implements Serializable {
        /**
         * The Area id.
         */
        String areaId;
        /**
         * The Area name.
         */
        String areaName;

        /**
         * Gets area id.
         *
         * @return the area id
         */
        public String getAreaId() {
            return areaId;
        }

        /**
         * Sets area id.
         *
         * @param areaId the area id
         */
        public void setAreaId(String areaId) {
            this.areaId = areaId;
        }

        /**
         * Gets area name.
         *
         * @return the area name
         */
        public String getAreaName() {
            return areaName;
        }

        /**
         * Sets area name.
         *
         * @param areaName the area name
         */
        public void setAreaName(String areaName) {
            this.areaName = areaName;
        }

        @Override
        public String toString() {
            return "areaId=" + areaId + ",areaName=" + areaName;
        }

    }

    /**
     * The type Province.
     */
    public static class Province extends Area implements Serializable {
        /**
         * The Cities.
         */
        ArrayList<City> cities = new ArrayList<City>();

        /**
         * Gets cities.
         *
         * @return the cities
         */
        public ArrayList<City> getCities() {
            return cities;
        }

        /**
         * Sets cities.
         *
         * @param cities the cities
         */
        public void setCities(ArrayList<City> cities) {
            this.cities = cities;
        }

    }

    /**
     * The type City.
     */
    public static class City extends Area implements Serializable {
        private ArrayList<County> counties = new ArrayList<County>();

        /**
         * Gets counties.
         *
         * @return the counties
         */
        public ArrayList<County> getCounties() {
            return counties;
        }

        /**
         * Sets counties.
         *
         * @param counties the counties
         */
        public void setCounties(ArrayList<County> counties) {
            this.counties = counties;
        }

    }

    @Override
    public View onCreateView() {
        return view;
    }

    @Override
    public void setUiBeforShow() {
        makeCenterView();
    }

    @Override
    protected void onStart() {
        super.onStart();
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT);

        mLlTop.setLayoutParams(layoutParams);
        //在底部显示
        mLlTop.setGravity(Gravity.BOTTOM);
        getWindow().setGravity(Gravity.BOTTOM);
    }

    /**
     * The type County.
     */
    public static class County extends Area implements Serializable {
    }

    public interface OnLinkageListener {

        void onPicked(String first, String second, String third);
    }
}
