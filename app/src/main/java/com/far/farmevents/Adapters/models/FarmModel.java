package com.far.farmevents.Adapters.models;

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.far.farmevents.Models.Farm;

public class FarmModel {
    String code;
    String description;
    Farm farm;

    public  FarmModel(){
    }

    public FarmModel(Farm farm) {
        this.code = farm.getCode();
        this.description = farm.getDescription();
        this.farm = farm;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Farm getFarm() {
        return farm;
    }

    public void setFarm(Farm farm) {
        this.farm = farm;
    }
}
