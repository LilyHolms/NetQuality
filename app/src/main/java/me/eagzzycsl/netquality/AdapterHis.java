package me.eagzzycsl.netquality;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

public class AdapterHis extends BaseAdapter<DatumHis> {
    public AdapterHis(Context context) {
        super(context);
    }

    @Override
    public int getLayoutId() {
        return R.layout.layout_item_his;
    }

    @Override
    public HisHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new HisHolder(LayoutInflater.from(context).inflate(getLayoutId(), parent, false));
    }

    public class HisHolder extends RecViewHolder implements View.OnClickListener, View.OnLongClickListener{
        private final TextView item_stamp;


        public HisHolder(View itemView) {
            super(itemView);
            item_stamp = (TextView) itemView.findViewById(R.id.item_stamp);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        @Override
        public void setContent(DatumHis data) {
            item_stamp.setText(data.getStamp());
            itemView.setTag(data.getStamp());

        }

        @Override
        public boolean onLongClick(View view) {
            String stamp = (String) view.getTag();
            SQLMan.getInstance().delete(stamp);
            Toast.makeText(view.getContext(), "删除成功", Toast.LENGTH_SHORT).show();
            context.startActivity(new Intent(MyApplication.getContext(), ActivityHis.class));
            return true;
        }


        @Override
        public void onClick(View view) {
            String stamp = (String) view.getTag();
            Intent intent = new Intent(MyApplication.getContext(), ActivityHisDetail.class);
            intent.putExtra(ActivityHisDetail.EXTRA_stamp, stamp);
            context.startActivity(intent);
        }


    }

}
