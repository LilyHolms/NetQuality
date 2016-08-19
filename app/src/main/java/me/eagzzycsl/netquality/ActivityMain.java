package me.eagzzycsl.netquality;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;

public class ActivityMain extends ActivityToolbar implements View.OnClickListener {
    private ArrayList<DatumInfo> netStates = new ArrayList<>();
    private String stamp;
    private FragmentInfo speedFragment;
    private MyFun myFun;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(this);
        speedFragment = (FragmentInfo) getFragmentManager().findFragmentById(R.id.fragment_speed);
        myFun = new MyFun(this);
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
            case R.id.action_history: {
                startActivity(new Intent(this, ActivityHis.class));
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
            case R.id.action_settings: {
                startActivity(new Intent(this, ActivitySettings.class));
                break;
            }
            case R.id.action_about: {
                startActivity(new Intent(this, ActivityAbout.class));
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
//        经度
        addInfo(MyInfoType.longitude, myFun.getLongitude());
//        纬度
        addInfo(MyInfoType.latitude, myFun.getLatitude());
//        ip地址
        addInfo(MyInfoType.ip, myFun.getIp());
    }

    private void addInfo(MyInfoType myInfoType, String value) {
        netStates.add(new DatumInfo(myInfoType, value));
    }
}
