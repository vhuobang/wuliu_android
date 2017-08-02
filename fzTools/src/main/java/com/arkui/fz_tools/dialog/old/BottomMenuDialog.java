package com.arkui.fz_tools.dialog.old;

import android.content.Context;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.arkui.fz_tools.R;
import com.arkui.fz_tools.dialog.old.bottom.ActionSheetDialog;
import com.arkui.fz_tools.dialog.old.listener.OnItemClickListener;

/**
 * Created by 任少东 on 2016/07/29 15:43
 */
public class BottomMenuDialog extends ActionSheetDialog {
    private LinearLayout layout;
    private OnItemClickListener onItemClickListener;
    private int textSize = 16;
    private int textColor = 0xffffffff;

    public BottomMenuDialog(Context context,String[] menus) {
        super(context);
        setItems(menus);
        //没有标题
        isTitleShow(false);

    }

    /*protected void initView() {
        String[] stringItems = {"拍照", "从手机相册选择"};
        setItems(stringItems);
        //没有标题
        isTitleShow(false);

        //showAnim(mInnerShowAnim);
    }*/

   /* protected View getContentView() {
        layout = new LinearLayout(mContext);
        layout.setOrientation(LinearLayout.VERTICAL);
        // layout.setPadding(0, 40, 0, 40);
        layout.setGravity(Gravity.CENTER_HORIZONTAL);
        layout.setBackgroundColor(Color.WHITE);
        return layout;
    }*/

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

    /*  @Override
    public View onCreateView() {
        return contentView;
    }*/
/*

    @Override
    public void setUiBeforShow() {

    }
*/

    public void setMenus(String[] menus) {
       /* layout.removeAllViews();
        int height = dp2px(48);
        LinearLayout.LayoutParams menuParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, height);
        LinearLayout.LayoutParams division = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 2);
        LinearLayout.LayoutParams division2 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 2);


        for (int i = 0; i < menus.length + 1; i++) {
            TextView textView;
            View view;
            view = new View(mContext);
            view.setLayoutParams(division);
            view.setBackgroundColor(mContext.getResources().getColor(R.color.gray));
            if (i < menus.length) {
                textView = getTextView(menus[i], menuParams);
                textView.setTag(i);
                if (i != 0) {
                    layout.addView(view);
                }
            } else {
                // view.setBackgroundColor(mContext.getResources().getColor(R.color.gray2));
                view.setLayoutParams(division2);
                layout.addView(view);
                textView = getTextView("取消", menuParams);
                textView.setTag(-1);
                textView.setTextSize(textSize);
                textView.setTextColor(mContext.getResources().getColor(R.color.red));
            }
            textView.setOnClickListener(this);
            layout.addView(textView);
        }*/

        setItems(menus);
    }

    private TextView getTextView(String string, LinearLayout.LayoutParams params) {
        TextView textView = new TextView(mContext);
        textView.setLayoutParams(params);
        textView.setGravity(Gravity.CENTER);
        textView.setText(string);
        textView.setTextSize(textSize);
        textView.setTextColor(mContext.getResources().getColor(R.color.black_normal_color));
        return textView;
    }

  /*  @Override
    public void onClick(View v) {
        int pos = (int) v.getTag();
        if (onItemClickListener != null) {
            onItemClickListener.onClick(this, pos);
        }
        dismiss();
    }*/

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public int dp2px(float dp) {
        float density = mContext.getResources().getDisplayMetrics().density;
        return (int) (dp * density + 0.5f);
    }
}
