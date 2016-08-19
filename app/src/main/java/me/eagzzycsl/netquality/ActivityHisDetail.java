package me.eagzzycsl.netquality;

import android.os.Bundle;

public class ActivityHisDetail extends ActivityToolbar {
    public static final String EXTRA_stamp = "extra_stamp";
    private FragmentInfo fragment_hisDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String stamp = this.getIntent().getExtras().getString(EXTRA_stamp);
        fragment_hisDetail = (FragmentInfo) getFragmentManager().findFragmentById(R.id.fragment_hisDetail);
        if (stamp != null) {
            fragment_hisDetail.setData(SQLMan.getInstance().read(stamp));
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_his_detail;
    }

}
