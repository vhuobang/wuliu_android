package com.arkui.fz_net.progress;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;

/**
 * Created by liukun on 16/3/10.
 */
public class ProgressDialogHandler extends Handler {

    public static final int SHOW_PROGRESS_DIALOG = 1;
    public static final int DISMISS_PROGRESS_DIALOG = 2;

    private ProgressDialog pd;

    private Activity activity;
    private boolean cancelable;
    private ProgressCancelListener mProgressCancelListener;

    public ProgressDialogHandler(Activity activity, ProgressCancelListener mProgressCancelListener,
                                 boolean cancelable) {
        super();
        this.activity = activity;
        this.mProgressCancelListener = mProgressCancelListener;
        this.cancelable = cancelable;
    }


    private void initProgressDialog(){
        if (pd == null) {
            pd = new ProgressDialog(activity);
            pd.setCancelable(cancelable);
            pd.setMessage("加载中");
            if (cancelable) {
                pd.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialogInterface) {
                        mProgressCancelListener.onCancelProgress();
                    }
                });
            }

            if(!activity.isFinishing()){
                if (!pd.isShowing()) {
                    pd.show();
                }
            }
        }
    }

    private void dismissProgressDialog(){
        if (pd != null) {
            pd.dismiss();
            pd = null;
        }
    }

    @Override
    public void handleMessage(Message msg) {
        switch (msg.what) {
            case SHOW_PROGRESS_DIALOG:
                initProgressDialog();
                break;
            case DISMISS_PROGRESS_DIALOG:
                dismissProgressDialog();
                break;
        }
    }

}
