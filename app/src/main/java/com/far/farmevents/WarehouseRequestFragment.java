package com.far.farmevents;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.far.farmevents.Adapters.DistributionAdapter;
import com.far.farmevents.Adapters.IListListener;
import com.far.farmevents.Adapters.WarehouseAdapter;
import com.far.farmevents.Adapters.models.DistributionModel;
import com.far.farmevents.Adapters.models.WarehouseProductModel;
import com.far.farmevents.Models.ModuleArea;
import com.far.farmevents.Models.WarehouseProduct;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link WarehouseRequestFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WarehouseRequestFragment extends Fragment implements IListListener {

    MainActivity mainActivity;
    ModuleArea moduleArea;
    RecyclerView rv;
    ArrayList<WarehouseProductModel> data;
    WarehouseProductModel currentItem;
    LinearLayout llEdit, llList;
    EditText etQuantity;
    Button btnSave;


    public WarehouseRequestFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static WarehouseRequestFragment newInstance(MainActivity mainActivity, ModuleArea moduleArea) {
        WarehouseRequestFragment fragment = new WarehouseRequestFragment();
        fragment.mainActivity = mainActivity;
        fragment.moduleArea = moduleArea;
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        data = new ArrayList<>();
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_warehouse_request, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        llEdit = view.findViewById(R.id.llEdit);
        llList = view.findViewById(R.id.llList);

        ImageView imgBack =  llList.findViewById(R.id.imgBack);
        imgBack.setImageDrawable(mainActivity.getDrawable(R.drawable.ic_close));
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainActivity.setMainMenu(moduleArea.getFarmModel());
            }
        });
        ImageView imgSave = llList.findViewById(R.id.imgAction);
        imgSave.setImageDrawable(mainActivity.getDrawable(R.drawable.ic_save));
        imgSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        ((TextView)llList.findViewById(R.id.tvTitle)).setText(mainActivity.getString(R.string.warehouse_request));

        ((TextView)llList.findViewById(R.id.tvFarm)).setText(moduleArea.getFarmModel().getDescription());
        ((TextView)llList.findViewById(R.id.tvDepartment)).setText(moduleArea.getDepartment().getDescription());
        ((TextView)llList.findViewById(R.id.tvUnit)).setText(moduleArea.getUnitCode());

        ImageView imgBackAdd =  llEdit.findViewById(R.id.imgBack);
        imgBackAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showList();
            }
        });
        ((TextView)llEdit.findViewById(R.id.tvTitle)).setText(mainActivity.getString(R.string.edit));


        etQuantity = view.findViewById(R.id.etQuantity);
        btnSave = view.findViewById(R.id.btnSave);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentItem.setQuantity(Integer.parseInt(etQuantity.getText().toString()));
                rv.getAdapter().notifyDataSetChanged();
                rv.invalidate();
                showList();
            }
        });

        rv = view.findViewById(R.id.rv);
        LinearLayoutManager manager = new LinearLayoutManager(mainActivity,LinearLayoutManager.VERTICAL,false);
        rv.setLayoutManager(manager);

        WarehouseProduct wh = new WarehouseProduct(1,"001","Product 1");
        data.add(new WarehouseProductModel(wh,0));
        wh =  new WarehouseProduct(2,"002","Product 2");
        data.add(new WarehouseProductModel(wh,0));
        wh =  new WarehouseProduct(3,"003","Product 3");
        data.add(new WarehouseProductModel(wh,0));
        wh =  new WarehouseProduct(4,"004","Product 4");
        data.add(new WarehouseProductModel(wh,0));
        wh =  new WarehouseProduct(5,"005","Product 5");
        data.add(new WarehouseProductModel(wh,0));

        refresh();
    }

    private void refresh(){
        WarehouseAdapter adapter = new WarehouseAdapter(mainActivity,this::onItemClick,data);
        rv.setAdapter(adapter);
        rv.invalidate();

    }

    @Override
    public void onItemClick(Object o) {
        currentItem = (WarehouseProductModel)o;
        showEditQuantity();
        etQuantity.setText(String.valueOf(currentItem.getQuantity()));
    }

    private void showEditQuantity(){
        llEdit.setVisibility(View.VISIBLE);
        llList.setVisibility(View.GONE);
    }

    private void showList(){
        llList.setVisibility(View.VISIBLE);
        llEdit.setVisibility(View.GONE);
    }
}