package com.far.farmevents.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.far.farmevents.Adapters.models.WarehouseProductModel;
import com.far.farmevents.R;

import java.util.ArrayList;

public class WarehouseAdapter extends RecyclerView.Adapter<WarehouseAdapter.WarehouseProductVH> {

    IListListener listener;
    Context context;
    ArrayList<WarehouseProductModel> objects = new ArrayList();

    public  WarehouseAdapter(Context context, IListListener listener, ArrayList<WarehouseProductModel>objects){
        this.context = context;
        this.listener = listener;
        this.objects = objects;
    }
    @NonNull
    @Override
    public WarehouseAdapter.WarehouseProductVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        return new WarehouseAdapter.WarehouseProductVH(inflater.inflate(R.layout.warehouse_row,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull WarehouseAdapter.WarehouseProductVH holder, int position) {

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

    public class WarehouseProductVH extends RecyclerView.ViewHolder {

        private TextView tvProductName, tvProductCode, tvQuantity, tvQuantityLabel;
        CardView parent;
        public WarehouseProductVH(@NonNull View itemView) {
            super(itemView);
            tvProductName = itemView.findViewById(R.id.tvProductName);
            tvQuantity = itemView.findViewById(R.id.tvQuantity);
            tvQuantityLabel = itemView.findViewById(R.id.tvQuantityLabel);
            tvProductCode = itemView.findViewById(R.id.tvProductCode);
            parent = itemView.findViewById(R.id.parent);
        }
        public void fillData(WarehouseProductModel obj){
            tvProductName.setText(obj.getDescription());
            tvQuantity.setText(String.valueOf(obj.getQuantity()));
            tvProductCode.setText(obj.getCode());

            int cardColor;
            int textColor;
            if(obj.getQuantity() > 0){
                cardColor = context.getResources().getColor(R.color.purple_500);
                textColor = context.getResources().getColor(R.color.white);
            }else{
                cardColor = context.getResources().getColor(R.color.white);
                textColor = context.getResources().getColor(R.color.black);
            }

            parent.setCardBackgroundColor(cardColor);
            tvProductCode.setTextColor(textColor);
            tvQuantity.setTextColor(textColor);
            tvQuantityLabel.setTextColor(textColor);
            tvProductName.setTextColor(textColor);

        }
    }
}

