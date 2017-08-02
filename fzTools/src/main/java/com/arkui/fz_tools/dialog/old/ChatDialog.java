package com.arkui.fz_tools.dialog.old;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.arkui.fz_tools.R;
import com.arkui.fz_tools.utils.ToastUtil;


/**
 * Created by 任少东 on 2016/08/15 16:07
 */
public class ChatDialog extends RBottomDialog implements View.OnClickListener, DialogInterface.OnShowListener {
    public OnSendClickListener listener;
    private EditText editText;
    private TextView send;
    private InputMethodManager imm;

    public void setArticle_id(String article_id) {
        this.article_id = article_id;
    }

    public String getArticle_id() {
        return article_id;
    }

    private String article_id;

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    private int position;

    public ChatDialog(Context context) {
        super(context);
        imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        setOnShowListener(this);
    }

    @Override
    protected View getContentView() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_chat, null);
        initView(view);
        return view;
    }

    private void initView(View view) {
        editText = (EditText) view.findViewById(R.id.chat_edit);
        send = (TextView) view.findViewById(R.id.chat_send);
        send.setOnClickListener(this);
    }

    public void showSoftInput() {
        imm.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT);
    }

    @Override
    public void onClick(View v) {
        String content = editText.getText().toString().trim();
        if (content.length() == 0) {
            ToastUtil.showToast(getContext(),"内容不能为空");
            return;
        }
        if (listener != null) {
            listener.onSend(content);
            editText.setText("");
        }
        dismiss();
    }

    public void setOnSendListener(OnSendClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onShow(DialogInterface dialog) {
        showSoftInput();
    }

    public interface OnSendClickListener {
        void onSend(String content);
    }
}
