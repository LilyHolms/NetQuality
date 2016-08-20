package me.eagzzycsl.netquality;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ActivityHis extends ActivityToolbar implements View.OnClickListener{

    private Button btn_action_test;
    private Button btn_action_history;
    private Button btn_action_settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init_Bottom();

    }
    @Override
    public int getLayoutId() {
        return R.layout.activity_his;
    }

    private void init_Bottom(){
        btn_action_test = (Button)findViewById(R.id.btn_action_test);
        btn_action_history = (Button)findViewById(R.id.btn_action_history);
        btn_action_settings = (Button)findViewById(R.id.btn_action_settings);
        btn_action_test.setOnClickListener(this);
        btn_action_history.setOnClickListener(this);
        btn_action_settings.setOnClickListener(this);

        Drawable ic_test_grey = getResources().getDrawable(R.drawable.ic_test_grey);
        ic_test_grey.setBounds(0, 0, ic_test_grey.getMinimumWidth(), ic_test_grey.getMinimumHeight());
        btn_action_test.setCompoundDrawables(null, ic_test_grey, null, null);
        btn_action_test.setTextColor(getResources().getColor(R.color.colorGreyText));

        Drawable ic_history_grey = getResources().getDrawable(R.drawable.ic_history_blue);
        ic_history_grey.setBounds(0, 0, ic_history_grey.getMinimumWidth(), ic_history_grey.getMinimumHeight());
        btn_action_history.setCompoundDrawables(null, ic_history_grey, null, null);
        btn_action_history.setTextColor(getResources().getColor(R.color.colorPrimary));

        Drawable ic_setting_blue = getResources().getDrawable(R.drawable.ic_setting_grey);
        ic_setting_blue.setBounds(0, 0, ic_setting_blue.getMinimumWidth(), ic_setting_blue.getMinimumHeight());
        btn_action_settings.setCompoundDrawables(null, ic_setting_blue, null, null);
        btn_action_settings.setTextColor(getResources().getColor(R.color.colorGreyText));

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_action_test: {
                startActivity(new Intent(this, ActivityMain.class));
                break;
            }
            case R.id.btn_action_history: {
                break;
            }
            case R.id.btn_action_settings: {
                startActivity(new Intent(this, ActivitySettings.class));
                break;
            }
        }
    }
}
