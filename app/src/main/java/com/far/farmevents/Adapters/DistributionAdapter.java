package com.far.farmevents.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.far.farmevents.Adapters.models.DistributionModel;
import com.far.farmevents.R;

import java.util.ArrayList;

public class DistributionAdapter extends RecyclerView.Adapter<DistributionAdapter.DistributionVH> {

    IListListener listener;
    Context context;
    ArrayList<DistributionModel> objects = new ArrayList();

    public  DistributionAdapter(Context context, IListListener listener, ArrayList<DistributionModel>objects){
        this.context = context;
        this.listener = listener;
        this.objects = objects;
    }
    @NonNull
    @Override
    public DistributionAdapter.DistributionVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        return new DistributionAdapter.DistributionVH(inflater.inflate(R.layout.distribution_row,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull DistributionAdapter.DistributionVH holder, int position) {

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

    public class DistributionVH extends RecyclerView.ViewHolder {

        private TextView tvSourceUnit, tvQuantity, tvSex,
                tvIncubator,tvProvider, tvSourceBatch, tvAge, tvSourceFarm;
        public DistributionVH(@NonNull View itemView) {
            super(itemView);
            tvSourceUnit = itemView.findViewById(R.id.tvSourceUnit);
            tvQuantity = itemView.findViewById(R.id.tvQuantity);
            tvSex = itemView.findViewById(R.id.tvSex);
            tvIncubator = itemView.findViewById(R.id.tvIncubator);
            tvProvider = itemView.findViewById(R.id.tvProvider);
            tvSourceBatch = itemView.findViewById(R.id.tvSourceBatch);
            tvAge = itemView.findViewById(R.id.tvAge);
            tvSourceFarm = itemView.findViewById(R.id.tvSourceFarm);
        }
        public void fillData(DistributionModel obj){
            tvSourceUnit.setText(obj.getSourceUnit());
            tvQuantity.setText(String.valueOf(obj.getQuantity()));
            tvSex.setText(obj.getSex());
            tvIncubator.setText(obj.getIncubator());
            tvProvider.setText(obj.getProvider());
            tvSourceBatch.setText(obj.getSourceBatch());
            tvAge.setText(String.valueOf( obj.getAge()).concat(" ").concat(context.getString(R.string.days)));
            tvSourceFarm.setText(obj.getSourceFarm());
        }
    }
}
