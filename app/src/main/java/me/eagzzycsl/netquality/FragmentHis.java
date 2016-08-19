package me.eagzzycsl.netquality;

import android.os.Bundle;


public class FragmentHis extends BaseFragment<DatumHis> {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setData(SQLMan.getInstance().readAll());
    }

    @Override
    public AdapterHis getAdapter() {
        return new AdapterHis(getActivity());
    }
}
