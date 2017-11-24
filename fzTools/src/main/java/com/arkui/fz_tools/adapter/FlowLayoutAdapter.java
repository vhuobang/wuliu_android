package com.arkui.fz_tools.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.arkui.fz_tools.R;
import com.arkui.fz_tools.entity.RemarkEntity;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;

import java.util.List;

/**
 * Created by 84658 on 2017/9/25.
 */

public class FlowLayoutAdapter extends TagAdapter<RemarkEntity> {

    private Context mContext;
    private LayoutInflater mInflater;
    public FlowLayoutAdapter(List<RemarkEntity> datas, Context context) {
        super(datas);
        mContext=context;
    }

    @Override
    public View getView(FlowLayout parent, int position, RemarkEntity s) {
        mInflater = LayoutInflater.from(mContext);
        TextView tv = (TextView) mInflater.inflate(R.layout.tv,
                parent, false);
        tv.setText(s.getContent());
        return tv;
    }
}
