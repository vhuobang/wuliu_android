package com.arkui.fz_tools.utils;

import android.app.Activity;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.TextView;

/**
 * 倒计时效果
 * @author lipeng
 *
 */
public class TimeCountUtil extends CountDownTimer {
 
	private View view;// 按钮
	public TimeCountUtil(View view) {
		super(1000*60,1000 );
		this.view = view;
	}

	@Override
	public void onTick(long millisUntilFinished) {
		view.setClickable(false); // 设置不能点击。
		((TextView) view).setText(millisUntilFinished / 1000 + "S后重新发送"); // 设置倒计时时间。

	}

	@Override
	public void onFinish() {
		((TextView) view).setText("重新获取验证码");
		view.setClickable(true); // 重新获得点击。
	}
}
