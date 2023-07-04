package com.far.farmevents.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.far.farmevents.Adapters.models.FarmModel;
import com.far.farmevents.R;

import java.util.ArrayList;

public class FarmListAdapter  extends RecyclerView.Adapter<FarmListAdapter.FarmVH> {

    IListListener listener;
    Context context;
    ArrayList<FarmModel> objects = new ArrayList();

    public  FarmListAdapter(Context context, IListListener listener, ArrayList<FarmModel>objects){
        this.context = context;
        this.listener = listener;
        this.objects = objects;
    }
    @NonNull
    @Override
    public FarmListAdapter.FarmVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        return new FarmListAdapter.FarmVH(inflater.inflate(R.layout.farm_row,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull FarmListAdapter.FarmVH holder, int position) {

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

    public class FarmVH extends RecyclerView.ViewHolder {

        private TextView tvCode, tvDescription;
        public FarmVH(@NonNull View itemView) {
            super(itemView);
            tvCode = itemView.findViewById(R.id.tvCode);
            tvDescription = itemView.findViewById(R.id.tvDescription);
        }
        public void fillData(FarmModel obj){
            tvCode.setText(obj.getCode());
            tvDescription.setText(obj.getDescription());
        }
    }
}
