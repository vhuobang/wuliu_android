package com.arkui.fz_tools.utils;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


/**
 * Created by 任少东 on 2016/07/15 10:53
 */
public class ToastUtil {
    private static Toast toast;
    private static Toast centerToast;

    public static void showToast(Context context, String text) {
        if (toast == null) {
            toast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
        } else {
            toast.setText(text);
        }
        toast.show();
    }

   /* public static void showCenterToast(Context mContext, String text) {
        showCenterToast(mContext, R.mipmap.wdsc_duigou, text);
    }
*/

    public static void showCenterToast(Context context, int imgRes, String text) {
        if (centerToast == null) {
            LinearLayout toastLay = new LinearLayout(context);

            toastLay.setLayoutParams(new LinearLayout.LayoutParams(300, 200));

            toastLay.setOrientation(LinearLayout.VERTICAL);
            toastLay.setGravity(Gravity.CENTER);
            int dp=dp2px(context,1);
            toastLay.setPadding(dp*20, 10*dp, 20*dp, 10*dp);
            GradientDrawable drawable = new GradientDrawable();
            drawable.setCornerRadius(5*dp);
            drawable.setColor(0x88000000);
            toastLay.setBackgroundDrawable(drawable);
            ImageView image = new ImageView(context);
            image.setPadding(10*dp, 10*dp, 10*dp, 10*dp);
            image.setImageResource(imgRes);
            toastLay.addView(image);
            TextView textView = new TextView(context);
            textView.setTextColor(0xffffffff);
            textView.setTextSize(16);
            textView.setText(text);
            toastLay.addView(textView);
            centerToast = new Toast(context);
            centerToast.setView(toastLay);

            centerToast.setGravity(Gravity.CENTER, 0, 0);
        } else {
            LinearLayout layout = (LinearLayout) centerToast.getView();
            ImageView image = (ImageView) layout.getChildAt(0);
            TextView textView = (TextView) layout.getChildAt(1);
            image.setImageResource(imgRes);
            textView.setText(text);
        }
        centerToast.show();
    }
    public static int dp2px(Context context, int dp) {
        float density = context.getResources().getDisplayMetrics().density;
        return (int) (dp * density + 0.5f);
    }
}
