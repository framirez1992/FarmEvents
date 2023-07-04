package com.far.farmevents;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.far.farmevents.Models.ModuleArea;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EggProductionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EggProductionFragment extends Fragment {

    MainActivity mainActivity;
    ModuleArea moduleArea;
    EditText etFertileEggs,etDoubleEggs,etPlainEggs,etBrokenEggs,etAverageWeight,etAverageMass;
    Button btnSave;

    public EggProductionFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static EggProductionFragment newInstance(MainActivity mainActivity, ModuleArea moduleArea) {
        EggProductionFragment fragment = new EggProductionFragment();
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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_egg_production, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ImageView imgBack = view.findViewById(R.id.imgBack);
        imgBack.setImageDrawable(mainActivity.getDrawable(R.drawable.ic_close));
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainActivity.setMainMenu(moduleArea.getFarmModel());
            }
        });
        ((TextView)view.findViewById(R.id.tvTitle)).setText(mainActivity.getString(R.string.egg_production));

        ((TextView)view.findViewById(R.id.tvFarm)).setText(moduleArea.getFarmModel().getDescription());
        ((TextView)view.findViewById(R.id.tvDepartment)).setText(moduleArea.getDepartment().getDescription());
        ((TextView)view.findViewById(R.id.tvUnit)).setText(moduleArea.getUnitCode());

        etFertileEggs = view.findViewById(R.id.etFertileEggs);
        etDoubleEggs = view.findViewById(R.id.etDoubleEggs);
        etPlainEggs = view.findViewById(R.id.etPlainEggs);
        etBrokenEggs = view.findViewById(R.id.etBrokenEggs);
        etAverageWeight = view.findViewById(R.id.etAverageWeight);
        etAverageMass = view.findViewById(R.id.etAverageMass);
        btnSave = view.findViewById(R.id.btnSave);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}