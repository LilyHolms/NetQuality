package me.eagzzycsl.netquality;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;

public abstract class BaseAdapter<D extends BaseDatum> extends RecyclerView.Adapter<BaseAdapter.RecViewHolder> {
    protected Context context;
    protected ArrayList<D> dataArray;

    public BaseAdapter(Context context) {
        this.context = context;
    }

    @Override
    public void onBindViewHolder(BaseAdapter.RecViewHolder holder, int position) {
        holder.setContent(dataArray.get(position));
    }


    @Override
    public int getItemCount() {
        return dataArray == null ? 0 : dataArray.size();
    }

    public abstract int getLayoutId();

    public void setDataArray(ArrayList<D> dataArray) {
        this.dataArray = dataArray;
    }

    public abstract class RecViewHolder extends RecyclerView.ViewHolder {
        public RecViewHolder(View itemView) {
            super(itemView);
        }

        public abstract void setContent(D data);
    }

}
