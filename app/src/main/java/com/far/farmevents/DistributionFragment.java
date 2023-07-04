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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.far.farmevents.Adapters.DistributionAdapter;
import com.far.farmevents.Adapters.IListListener;
import com.far.farmevents.Adapters.models.DistributionModel;
import com.far.farmevents.Globals.KV;
import com.far.farmevents.Models.Department;
import com.far.farmevents.Models.ModuleArea;
import com.far.farmevents.Models.Provider;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DistributionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DistributionFragment extends Fragment implements IListListener {

    MainActivity mainActivity;
    ModuleArea moduleArea;
    RecyclerView rv;
    FloatingActionButton btnAdd;
    ArrayList<DistributionModel> data;
    LinearLayout llList, llAdd;

    EditText etSourceUnit, etQuantity, etIncubator, etSourceBatch, etAge, etSourceFarm;
    Spinner spnProvider, spnSex;
    Button btnSave;
    public DistributionFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static DistributionFragment newInstance(MainActivity mainActivity, ModuleArea moduleArea) {
        DistributionFragment fragment = new DistributionFragment();
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
        return inflater.inflate(R.layout.fragment_distribution, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

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

        ((TextView)llList.findViewById(R.id.tvTitle)).setText(mainActivity.getString(R.string.chicken_distribution));

        ((TextView)view.findViewById(R.id.tvFarm)).setText(moduleArea.getFarmModel().getDescription());
        ((TextView)view.findViewById(R.id.tvDepartment)).setText(moduleArea.getDepartment().getDescription());
        ((TextView)view.findViewById(R.id.tvUnit)).setText(moduleArea.getUnitCode());


        llAdd = view.findViewById(R.id.llAdd);
        ImageView imgBackAdd =  llAdd.findViewById(R.id.imgBack);
        imgBackAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             showList();
            }
        });
        ((TextView)llAdd.findViewById(R.id.tvTitle)).setText(mainActivity.getString(R.string.add_provenance));


        etSourceUnit = view.findViewById(R.id.etSourceUnit);
        spnSex = view.findViewById(R.id.spnSex);
        etQuantity = view.findViewById(R.id.etQuantity);
        etIncubator = view.findViewById(R.id.etIncubator);
        etSourceBatch = view.findViewById(R.id.etSourceBatch);
        etAge = view.findViewById(R.id.etAge);
        spnProvider = view.findViewById(R.id.spnProvider);
        etSourceFarm = view.findViewById(R.id.etSourceFarm);
        btnSave = view.findViewById(R.id.btnSave);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addDistribution();
                showList();
            }
        });


        rv = view.findViewById(R.id.rv);
        LinearLayoutManager manager = new LinearLayoutManager(mainActivity,LinearLayoutManager.VERTICAL,false);
        rv.setLayoutManager(manager);

        btnAdd = view.findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAdd();
                clearAddFields();
            }
        });

        fillSex();
        fillProviders();

       /* data.add(new DistributionModel("nave1","M","abc","Prov","123","catalina2",100,2));
        refresh();*/

    }

    @Override
    public void onItemClick(Object o) {

    }

    private void refresh(){
        DistributionAdapter adapter = new DistributionAdapter(mainActivity,this::onItemClick,data);
        rv.setAdapter(adapter);
        rv.invalidate();

    }

    private void showList(){
        llList.setVisibility(View.VISIBLE);
        llAdd.setVisibility(View.GONE);
    }

    private void showAdd(){
        llList.setVisibility(View.GONE);
        llAdd.setVisibility(View.VISIBLE);
    }

    private void clearAddFields(){
        etSourceFarm.setText("");
        etSourceUnit.setText("");
        etQuantity.setText("");
        etIncubator.setText("");
        etSourceBatch.setText("");
        etAge.setText("");
        if(spnProvider.getAdapter().getCount() > 0){
            spnProvider.setSelection(0);
        }
        if(spnSex.getAdapter().getCount() > 0){
            spnSex.setSelection(0);
        }


    }

    private void fillProviders(){
        ArrayList<Provider> data = new ArrayList<>();
        data.add(new Provider(1,"01","Provider 1"));
        data.add(new Provider(2,"02","Provider 2"));
        data.add(new Provider(3,"03","Provider 3"));
        ArrayAdapter<Provider> adapter = new ArrayAdapter<Provider>(mainActivity, android.R.layout.simple_list_item_1,data);
        spnProvider.setAdapter(adapter);
    }

    private void fillSex(){
        ArrayList<KV> data = new ArrayList<>();
        data.add(new KV("01",mainActivity.getString(R.string.male)));
        data.add(new KV("02",mainActivity.getString(R.string.female)));
        ArrayAdapter<KV> adapter = new ArrayAdapter<KV>(mainActivity, android.R.layout.simple_list_item_1,data);
        spnSex.setAdapter(adapter);
    }

    private void addDistribution(){
        String provider = "";
        String sex=  ((KV)spnSex.getSelectedItem()).getValue();
        int quantity = 0;
        int age = 0;
        if(spnProvider.getAdapter()!= null && spnProvider.getAdapter().getCount() > 0){
          provider =  ((Provider)spnProvider.getSelectedItem()).getDescription();
        }

        try{
            quantity = Integer.parseInt(etQuantity.getText().toString());
        }catch (Exception e){

        }
        try{
            age = Integer.parseInt(etAge.getText().toString());
        }catch (Exception e){

        }
        data.add(new DistributionModel(etSourceUnit.getText().toString(),sex,etIncubator.getText().toString(),provider,etSourceBatch.getText().toString(),etSourceFarm.getText().toString(),quantity,age));
        refresh();
    }

}