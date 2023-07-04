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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.far.farmevents.Adapters.CheckListAdapter;
import com.far.farmevents.Adapters.IListListener;
import com.far.farmevents.Adapters.models.CheckItemModel;
import com.far.farmevents.Adapters.models.WarehouseProductModel;
import com.far.farmevents.Globals.KV;
import com.far.farmevents.Models.CheckItem;
import com.far.farmevents.Models.ModuleArea;
import com.far.farmevents.Models.Provider;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CheckListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CheckListFragment extends Fragment implements IListListener {

    MainActivity mainActivity;
    ModuleArea moduleArea;
    RecyclerView rv;
    Spinner spnType;
    ArrayList<CheckItemModel> data;
    CheckItemModel currentItem;

    public CheckListFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static CheckListFragment newInstance(MainActivity mainActivity, ModuleArea moduleArea) {
        CheckListFragment fragment = new CheckListFragment();
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
        return inflater.inflate(R.layout.fragment_check_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ImageView imgBack =  view.findViewById(R.id.imgBack);
        imgBack.setImageDrawable(mainActivity.getDrawable(R.drawable.ic_close));
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainActivity.setMainMenu(moduleArea.getFarmModel());
            }
        });
        ImageView imgSave = view.findViewById(R.id.imgAction);
        imgSave.setImageDrawable(mainActivity.getDrawable(R.drawable.ic_save));
        imgSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        ((TextView)view.findViewById(R.id.tvTitle)).setText(mainActivity.getString(R.string.check_list));

        ((TextView)view.findViewById(R.id.tvFarm)).setText(moduleArea.getFarmModel().getDescription());
        ((TextView)view.findViewById(R.id.tvDepartment)).setText(moduleArea.getDepartment().getDescription());
        ((TextView)view.findViewById(R.id.tvUnit)).setText(moduleArea.getUnitCode());



        spnType = view.findViewById(R.id.spnType);
        spnType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                KV selection = (KV)spnType.getSelectedItem();
                loadCheckListByType(selection);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        rv = view.findViewById(R.id.rv);
        LinearLayoutManager manager = new LinearLayoutManager(mainActivity,LinearLayoutManager.VERTICAL,false);
        rv.setLayoutManager(manager);

        fillTypes();
    }


    @Override
    public void onItemClick(Object o) {
        currentItem = (CheckItemModel)o;
        currentItem.setChecked(!currentItem.isChecked());
        rv.getAdapter().notifyDataSetChanged();
        rv.invalidate();
    }

    private void refresh(){
        CheckListAdapter adapter = new CheckListAdapter(mainActivity,this::onItemClick,data);
        rv.setAdapter(adapter);
        rv.invalidate();

    }
    private void fillTypes(){
        ArrayList<KV> data = new ArrayList<>();
        data.add(new KV("01","Task Type 1"));
        data.add(new KV("02","Task Type 2"));
        data.add(new KV("03","Task Type 3"));
        ArrayAdapter<KV> adapter = new ArrayAdapter<KV>(mainActivity, android.R.layout.simple_list_item_1,data);
        spnType.setAdapter(adapter);
    }

    private void loadCheckListByType(KV type){
        CheckItem ci;
        data.clear();
        if(type.getKey().equals("01")){
            ci = new CheckItem(1,"Task 1",false);
            data.add(new CheckItemModel(ci));
            ci =  new CheckItem(2,"Task 2", false);
            data.add(new CheckItemModel(ci));
            ci =  new CheckItem(3,"Task 3", false);
            data.add(new CheckItemModel(ci));
            ci =  new CheckItem(4,"Task 4", false);
            data.add(new CheckItemModel(ci));
            ci =  new CheckItem(5,"Task 5", false);
            data.add(new CheckItemModel(ci));
        }else if(type.getKey().equals("02")){
            ci = new CheckItem(1,"Task 6",false);
            data.add(new CheckItemModel(ci));
            ci =  new CheckItem(2,"Task 7", false);
            data.add(new CheckItemModel(ci));
        }else{
            ci = new CheckItem(1,"Task 8",false);
            data.add(new CheckItemModel(ci));
            ci =  new CheckItem(2,"Task 9", false);
            data.add(new CheckItemModel(ci));
            ci =  new CheckItem(3,"Task 10", false);
            data.add(new CheckItemModel(ci));
        }

        refresh();
    }
}