package com.far.farmevents.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.far.farmevents.Adapters.models.CheckItemModel;
import com.far.farmevents.Adapters.models.WarehouseProductModel;
import com.far.farmevents.R;

import java.util.ArrayList;

public class CheckListAdapter extends RecyclerView.Adapter<CheckListAdapter.CheckListVH> {

    IListListener listener;
    Context context;
    ArrayList<CheckItemModel> objects = new ArrayList();

    public  CheckListAdapter(Context context, IListListener listener, ArrayList<CheckItemModel>objects){
        this.context = context;
        this.listener = listener;
        this.objects = objects;
    }
    @NonNull
    @Override
    public CheckListAdapter.CheckListVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        return new CheckListAdapter.CheckListVH(inflater.inflate(R.layout.check_row,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull CheckListAdapter.CheckListVH holder, int position) {

        holder.fillData(objects.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(objects.get(position));
            }
        });

    }

    @Override
    public int getItemCount() {
        return objects.size();
    }

    public class CheckListVH extends RecyclerView.ViewHolder {

        private TextView tvTask;
        CheckBox cbCheck;
        public CheckListVH(@NonNull View itemView) {
            super(itemView);
            tvTask = itemView.findViewById(R.id.tvTask);
            cbCheck = itemView.findViewById(R.id.cbCheck);
        }
        public void fillData(CheckItemModel obj){
            tvTask.setText(obj.getDescription());
            cbCheck.setChecked(obj.isChecked());
        }
    }
}

