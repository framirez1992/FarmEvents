package com.far.farmevents;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.far.farmevents.Adapters.IListListener;
import com.far.farmevents.Adapters.MainMenuAdapter;
import com.far.farmevents.Adapters.models.FarmModel;
import com.far.farmevents.Adapters.models.OptionModel;
import com.far.farmevents.Globals.Global;
import com.far.farmevents.Globals.KV;
import com.far.farmevents.Models.Batch;
import com.far.farmevents.Models.Department;
import com.far.farmevents.Models.ModuleArea;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MainMenuFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainMenuFragment extends Fragment implements IListListener {

   MainActivity mainActivity;
   RecyclerView rv;
   Spinner spnDepartment, spnBatch;
   FarmModel farmModel;

    public MainMenuFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static MainMenuFragment newInstance(MainActivity mainActivity, FarmModel farmModel) {
        MainMenuFragment fragment = new MainMenuFragment();
        fragment.mainActivity = mainActivity;
        fragment.farmModel = farmModel;
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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main_menu, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.imgBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainActivity.setFarmList();
            }
        });

        ((TextView)view.findViewById(R.id.tvTitle)).setText(farmModel.getDescription());
        ((TextView)view.findViewById(R.id.tvUnit)).setText("Nave: 01");


        rv = view.findViewById(R.id.rv);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(mainActivity, 2);
        rv.setLayoutManager(gridLayoutManager);

        spnDepartment = view.findViewById(R.id.spnDepartment);
        spnBatch = view.findViewById(R.id.spnBatch);

        fillAdapter();
        fillDepartments();
        fillBatch();
    }

    private void fillAdapter(){

        ArrayList<OptionModel> objs = new ArrayList<>();
        objs.add(new OptionModel(Global.MODULES.DAILY_NEWS,R.drawable.news,mainActivity.getString(R.string.daily_news)));
        objs.add(new OptionModel(Global.MODULES.CONTROLLED_ENVIROMENT,R.drawable.temperature,mainActivity.getString(R.string.controlled_enviroment)));
        objs.add(new OptionModel(Global.MODULES.DISTRIBUTION,R.drawable.distribution,mainActivity.getString(R.string.chicken_distribution)));
        objs.add(new OptionModel(Global.MODULES.WAREHOUSE_REQUEST,R.drawable.warehouse,mainActivity.getString(R.string.warehouse_request)));
        objs.add(new OptionModel(Global.MODULES.EVENT_LOG,R.drawable.alert,mainActivity.getString(R.string.event_log)));
        objs.add(new OptionModel(Global.MODULES.EGG_PRODUCTION,R.drawable.egg_production,mainActivity.getString(R.string.egg_production)));
        objs.add(new OptionModel(Global.MODULES.OUTPUT_SUMMARY,R.drawable.sumary,mainActivity.getString(R.string.output_summary)));
        objs.add(new OptionModel(Global.MODULES.CHECK_LIST,R.drawable.check_list,mainActivity.getString(R.string.check_list)));


        MainMenuAdapter adapter = new MainMenuAdapter(mainActivity,this::onItemClick,objs);
        rv.setAdapter(adapter);
        rv.invalidate();
    }

    @Override
    public void onItemClick(Object o) {
        OptionModel om = (OptionModel)o;
        if(getDepartment() == null){
            Snackbar.make(getView(), mainActivity.getString(R.string.invalid_department), BaseTransientBottomBar.LENGTH_LONG).show();
            return;
        }
        if(getBatch() == null){
            Snackbar.make(getView(),mainActivity.getString(R.string.invalid_batch), BaseTransientBottomBar.LENGTH_LONG).show();
            return;
        }

        ModuleArea moduleArea = new ModuleArea(farmModel,getDepartment(), getBatch(),"01");
        if(om.getModule() == Global.MODULES.DAILY_NEWS){
            mainActivity.setDailyNewsFragment(moduleArea);
        }else if(om.getModule() == Global.MODULES.CONTROLLED_ENVIROMENT){
            mainActivity.setControlledEnviroment(moduleArea);
        }else if(om.getModule() == Global.MODULES.EGG_PRODUCTION){
            mainActivity.setEggProductionFragment(moduleArea);
        }else if(om.getModule() == Global.MODULES.OUTPUT_SUMMARY){
            mainActivity.setOutputSummaryFragment(moduleArea);
        }else if(om.getModule() == Global.MODULES.DISTRIBUTION){
            mainActivity.setDistributionFragment(moduleArea);
        }else if(om.getModule() == Global.MODULES.WAREHOUSE_REQUEST){
            mainActivity.setWarehouseRequestFragment(moduleArea);
        }else if(om.getModule() == Global.MODULES.CHECK_LIST){
            mainActivity.setCheckListFragment(moduleArea);
        }else if(om.getModule() == Global.MODULES.EVENT_LOG){
            mainActivity.setEventLogFragment(moduleArea);
        }

    }


    private void fillDepartments(){
        ArrayList<Department> data = new ArrayList<>();
        data.add(new Department(1,"01","Depto. 1"));
        data.add(new Department(2,"02","Depto. 2"));
        data.add(new Department(3,"03","Depto. 3"));
        ArrayAdapter<Department> adapter = new ArrayAdapter<Department>(mainActivity, android.R.layout.simple_list_item_1,data);
        spnDepartment.setAdapter(adapter);
    }

    private void fillBatch(){
        ArrayList<Batch> data = new ArrayList<>();
        data.add(new Batch(1,"01","Lote 1"));
        data.add(new Batch(2,"02","Lote 2"));
        data.add(new Batch(3,"03","Lote 3"));
        ArrayAdapter<Batch> adapter = new ArrayAdapter<Batch>(mainActivity, android.R.layout.simple_list_item_1,data);
        spnBatch.setAdapter(adapter);
    }

    private Department getDepartment(){
        Department dep = (Department)spnDepartment.getSelectedItem();
        return dep;
    }
    private Batch getBatch(){
        Batch batch = (Batch)spnBatch.getSelectedItem();
        return batch;
    }
}