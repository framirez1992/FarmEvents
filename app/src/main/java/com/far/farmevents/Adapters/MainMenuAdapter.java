package com.far.farmevents.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.far.farmevents.Adapters.models.OptionModel;
import com.far.farmevents.R;

import java.util.ArrayList;

public class MainMenuAdapter extends RecyclerView.Adapter<MainMenuAdapter.OptionVH> {

    IListListener listener;
    Context context;
    ArrayList<OptionModel> objects = new ArrayList();

    public  MainMenuAdapter(Context context, IListListener listener, ArrayList<OptionModel>objects){
        this.context = context;
        this.listener = listener;
        this.objects = objects;
    }
    @NonNull
    @Override
    public MainMenuAdapter.OptionVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        return new MainMenuAdapter.OptionVH(inflater.inflate(R.layout.main_menu_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MainMenuAdapter.OptionVH holder, int position) {

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

    public class OptionVH extends RecyclerView.ViewHolder {

        private TextView tvTitle;
        private ImageView img;
        public OptionVH(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            img = itemView.findViewById(R.id.img);
        }
        public void fillData(OptionModel obj){
            tvTitle.setText(obj.getTitle());
            img.setImageResource(obj.getImgResource());
        }
    }
}
