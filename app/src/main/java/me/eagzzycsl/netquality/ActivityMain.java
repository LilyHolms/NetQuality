package me.eagzzycsl.netquality;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ActivityMain extends ActivityToolbar implements View.OnClickListener {
    private ArrayList<DatumInfo> netStates = new ArrayList<>();
    private String stamp;
    private FragmentInfo speedFragment;
    private MyFun myFun;

    private Button btn_action_test;
    private Button btn_action_history;
    private Button btn_action_settings;

    private Button action_save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(this);
        init_Bottom();
        speedFragment = (FragmentInfo) getFragmentManager().findFragmentById(R.id.fragment_speed);
        myFun = new MyFun(this);

        action_save = (Button) findViewById(R.id.action_save);
        action_save.setOnClickListener(this);
    }

    private void init_Bottom(){
        btn_action_test = (Button)findViewById(R.id.btn_action_test);
        btn_action_history = (Button)findViewById(R.id.btn_action_history);
        btn_action_settings = (Button)findViewById(R.id.btn_action_settings);
        btn_action_test.setOnClickListener(this);
        btn_action_history.setOnClickListener(this);
        btn_action_settings.setOnClickListener(this);

        Drawable ic_test_grey = getResources().getDrawable(R.drawable.ic_test_blue);
        ic_test_grey.setBounds(0, 0, ic_test_grey.getMinimumWidth(), ic_test_grey.getMinimumHeight());
        btn_action_test.setCompoundDrawables(null, ic_test_grey, null, null);
        btn_action_test.setTextColor(getResources().getColor(R.color.colorPrimary));

        Drawable ic_history_grey = getResources().getDrawable(R.drawable.ic_history_grey);
        ic_history_grey.setBounds(0, 0, ic_history_grey.getMinimumWidth(), ic_history_grey.getMinimumHeight());
        btn_action_history.setCompoundDrawables(null, ic_history_grey, null, null);
        btn_action_history.setTextColor(getResources().getColor(R.color.colorGreyText));

        Drawable ic_setting_blue = getResources().getDrawable(R.drawable.ic_setting_grey);
        ic_setting_blue.setBounds(0, 0, ic_setting_blue.getMinimumWidth(), ic_setting_blue.getMinimumHeight());
        btn_action_settings.setCompoundDrawables(null, ic_setting_blue, null, null);
        btn_action_settings.setTextColor(getResources().getColor(R.color.colorGreyText));

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.action_about: {
                startActivity(new Intent(this, ActivityAbout.class));
                break;
            }
        }
        return true;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fab: {
                getNetState();
                if (speedFragment.isContainData()) {
                    speedFragment.updateData();
                } else {
                    speedFragment.setData(netStates);
                }
                if (SettingSP.getInstance().getAutoSave()) {
                    save2DB();
                    if (SettingSP.getInstance().getTipWhenAutoSave()) {
                        Toast.makeText(this, R.string.auto_save_suc, Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(this, R.string.get_suc, Toast.LENGTH_SHORT).show();
                }
                break;
            }
            case R.id.btn_action_test: break;
            case R.id.btn_action_history: {
                startActivity(new Intent(this, ActivityHis.class));
                break;
            }
            case R.id.btn_action_settings: {
                startActivity(new Intent(this, ActivitySettings.class));
                break;
            }

            case R.id.action_save: {
                if (SettingSP.getInstance().getAutoSave()) {
                    Toast.makeText(this, R.string.no_need_save, Toast.LENGTH_SHORT).show();
                } else {
                    save2DB();
                    Toast.makeText(this, R.string.save_suc, Toast.LENGTH_SHORT).show();
                }
                break;
            }
        }
    }

    private void save2DB() {
        SQLMan.getInstance().insert(netStates, stamp);
    }

    private void getNetState() {
        netStates.clear();
        stamp = myFun.getStamp();
//        网络类型
        addInfo(MyInfoType.netType, myFun.getNetType());
//        时区，日期，时间
        String[] timeWithZone = myFun.getTimeWithZone();
        addInfo(MyInfoType.timeZone, timeWithZone[0]);
        addInfo(MyInfoType.date, timeWithZone[1]);
        addInfo(MyInfoType.time, timeWithZone[2]);
//        IMSI
        addInfo(MyInfoType.IMSI, myFun.getIMSI());
//        IMEI
        addInfo(MyInfoType.IMEI, myFun.getIMEI());
//        终端名称
        addInfo(MyInfoType.phoneName, myFun.getPhoneName());
//        操作系统版本
        addInfo(MyInfoType.osVersion, myFun.getOsVersion());
//        运营商名称
        addInfo(MyInfoType.operator, myFun.getOperator());
////        经度
//        addInfo(MyInfoType.longitude, myFun.getLongitude());
////        纬度
//        addInfo(MyInfoType.latitude, myFun.getLatitude());
//        ip地址
        addInfo(MyInfoType.ip, myFun.getIp());
    }

    private void addInfo(MyInfoType myInfoType, String value) {
        netStates.add(new DatumInfo(myInfoType, value));
    }
}
