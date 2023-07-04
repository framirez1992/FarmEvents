package com.far.farmevents;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

import com.far.farmevents.Adapters.models.FarmModel;
import com.far.farmevents.Adapters.models.OptionModel;
import com.far.farmevents.Models.ModuleArea;

public class MainActivity extends AppCompatActivity {

    Fragment currentFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setLogin();
    }

    @Override
    public void onBackPressed() {
        if(currentFragment instanceof MainMenuFragment){
            setFarmList();
        }
        //super.onBackPressed();
    }

    private void setFragment(Fragment fragment){
        currentFragment = fragment;
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager
                .beginTransaction()
                .replace(R.id.container, currentFragment)
                .commit();
    }

    public void setLogin(){
        setFragment(LoginFragment.newInstance(MainActivity.this));
    }
    public void setFarmList(){
        setFragment(FarmListFragment.newInstance(MainActivity.this));
    }

    public void setMainMenu(FarmModel farmModel){
        setFragment(MainMenuFragment.newInstance(MainActivity.this,farmModel));
    }

    public void setDailyNewsFragment(ModuleArea moduleArea){
        setFragment(DailyNewsFragment.newInstance(MainActivity.this,moduleArea));
    }

    public void setControlledEnviroment(ModuleArea moduleArea){
        setFragment(ControlledEnviromentFragment.newInstance(MainActivity.this,moduleArea));
    }

    public void setEggProductionFragment(ModuleArea moduleArea){
        setFragment(EggProductionFragment.newInstance(MainActivity.this,moduleArea));
    }
    public void setOutputSummaryFragment(ModuleArea moduleArea){
        setFragment(OutputSummaryFragment.newInstance(MainActivity.this,moduleArea));
    }
    public void setDistributionFragment(ModuleArea moduleArea){
        setFragment(DistributionFragment.newInstance(MainActivity.this,moduleArea));
    }

    public void setWarehouseRequestFragment(ModuleArea moduleArea){
        setFragment(WarehouseRequestFragment.newInstance(MainActivity.this,moduleArea));
    }

    public void setCheckListFragment(ModuleArea moduleArea){
        setFragment(CheckListFragment.newInstance(MainActivity.this,moduleArea));
    }

    public void setEventLogFragment(ModuleArea moduleArea){
        setFragment(EventLogFragment.newInstance(MainActivity.this,moduleArea));
    }


}