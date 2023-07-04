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
 * Use the {@link OutputSummaryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OutputSummaryFragment extends Fragment {

    MainActivity mainActivity;
    ModuleArea moduleArea;
    EditText etQuantity,etGrossWeight,TareWeight,etNetWeight,etNumberOfCages,etVehicleRegistration,etDriver;
    Button btnSave;


    public OutputSummaryFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static OutputSummaryFragment newInstance(MainActivity mainActivity, ModuleArea moduleArea) {
        OutputSummaryFragment fragment = new OutputSummaryFragment();
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
        return inflater.inflate(R.layout.fragment_output_summary, container, false);
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
        ((TextView)view.findViewById(R.id.tvTitle)).setText(mainActivity.getString(R.string.output_summary));

        ((TextView)view.findViewById(R.id.tvFarm)).setText(moduleArea.getFarmModel().getDescription());
        ((TextView)view.findViewById(R.id.tvDepartment)).setText(moduleArea.getDepartment().getDescription());
        ((TextView)view.findViewById(R.id.tvUnit)).setText(moduleArea.getUnitCode());

        etQuantity = view.findViewById(R.id.etQuantity);
        etGrossWeight = view.findViewById(R.id.etGrossWeight);
        TareWeight = view.findViewById(R.id.TareWeight);
        etNetWeight = view.findViewById(R.id.etNetWeight);
        etNumberOfCages = view.findViewById(R.id.etNumberOfCages);
        etVehicleRegistration = view.findViewById(R.id.etVehicleRegistration);
        etDriver = view.findViewById(R.id.etDriver);

        btnSave = view.findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}