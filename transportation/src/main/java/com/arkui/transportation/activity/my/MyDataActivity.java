package com.arkui.transportation.activity.my;

import android.app.Activity;
import android.content.Intent;
import android.widget.EditText;
import android.widget.ImageView;

import com.arkui.fz_tools.ui.BaseActivity;
import com.arkui.transportation.R;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MyDataActivity extends BaseActivity {

    @BindView(R.id.et_content)
    EditText mEtContent;
    @BindView(R.id.iv_clean)
    ImageView mIvClean;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_my_data);
        String title = getIntent().getStringExtra("title");
        setTitle(title);
        setRight("确定");
    }

    @Override
    public void initView() {
        super.initView();
        ButterKnife.bind(this);

        int type = getIntent().getIntExtra("type", 0);

        if(type==101){
            mEtContent.setHint("请输入昵称");
        }else{
            mEtContent.setHint("请输入QQ号码");
        }
    }

    public static void openActivity(Activity activity, String title, int type) {
        Intent intent = new Intent(activity, MyDataActivity.class);
        intent.putExtra("title", title);
        intent.putExtra("type", type);
        activity.startActivityForResult(intent, type);
    }
}
