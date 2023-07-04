package com.far.farmevents;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.far.farmevents.Adapters.FarmListAdapter;
import com.far.farmevents.Adapters.IListListener;
import com.far.farmevents.Adapters.MainMenuAdapter;
import com.far.farmevents.Adapters.models.FarmModel;
import com.far.farmevents.Models.Farm;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FarmListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FarmListFragment extends Fragment implements IListListener {

    MainActivity mainActivity;
    TabLayout tlFarms;
    RecyclerView rv;
    public FarmListFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static FarmListFragment newInstance(MainActivity mainActivity) {
        FarmListFragment fragment = new FarmListFragment();
        fragment.mainActivity = mainActivity;
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
        return inflater.inflate(R.layout.fragment_farm_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tlFarms = view.findViewById(R.id.tlFarms);
        tlFarms.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
               int p = tab.getPosition();
               fillList(p);

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        rv = view.findViewById(R.id.rv);
        LinearLayoutManager manager = new LinearLayoutManager(mainActivity);
        rv.setLayoutManager(manager);

        fillList(0);

    }



    private void fillList(int position){
        tlFarms.setEnabled(false);

        ArrayList<FarmModel> data = new ArrayList<>();

        if(position == 0){
            Farm f = new Farm(1,"001","Granja 1");
            data.add(new FarmModel(f));
            f = new Farm(2,"002","Granja 2");
            data.add(new FarmModel(f));
            f = new Farm(3,"003","Granja 3");
            data.add(new FarmModel(f));
            f = new Farm(4,"004","Granja 4");
            data.add(new FarmModel(f));
            f = new Farm(5,"005","Granja 5");
            data.add(new FarmModel(f));
            f = new Farm(6,"006","Granja 6");
            data.add(new FarmModel(f));
            f = new Farm(7,"007","Granja 7");
            data.add(new FarmModel(f));
        }else if(position == 1){
            Farm f = new Farm(8,"008","Granja 8");
            data.add(new FarmModel(f));
            f = new Farm(9,"009","Granja 9");
            data.add(new FarmModel(f));
            f = new Farm(10,"010","Granja 10");
            data.add(new FarmModel(f));
            f = new Farm(11,"011","Granja 11");
            data.add(new FarmModel(f));
        }else{
            Farm f = new Farm(12,"012","Granja 12");
            data.add(new FarmModel(f));
            f = new Farm(13,"013","Granja 13");
            data.add(new FarmModel(f));
            f = new Farm(14,"014","Granja 14");
            data.add(new FarmModel(f));
        }

        FarmListAdapter adapter = new FarmListAdapter(mainActivity,this::onItemClick,data);
        rv.setAdapter(adapter);
        rv.invalidate();

        tlFarms.setEnabled(true);
    }

    @Override
    public void onItemClick(Object o) {
        mainActivity.setMainMenu((FarmModel)o);
    }
}