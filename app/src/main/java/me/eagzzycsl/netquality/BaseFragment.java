package me.eagzzycsl.netquality;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public abstract class BaseFragment<D extends BaseDatum> extends Fragment {
    private RecyclerView recyclerView;
    private BaseAdapter<D> adapter;
    private boolean containData = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = getAdapter();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_rec_fragment, container);
        recyclerView = (RecyclerView) view.findViewById(R.id.fragment_recyclerView);
        return view;
    }

    public abstract BaseAdapter<D> getAdapter();

    public void setData(ArrayList<D> dataArray) {
        if (adapter != null) {
            adapter.setDataArray(dataArray);
            containData = true;
            adapter.notifyDataSetChanged();
        }
    }

    public void updateData() {
        if (adapter != null) {
            adapter.notifyDataSetChanged();
        }
    }

    public boolean isContainData() {
        return this.containData;
    }

}