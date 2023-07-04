package com.far.farmevents.Models;

import com.far.farmevents.Adapters.models.FarmModel;

public class ModuleArea {

    FarmModel farmModel;
    Department department;
    Batch batch;
    String unitCode;

    public ModuleArea(){

    }

    public ModuleArea(FarmModel farmModel, Department department, Batch batch, String unitCode) {
        this.farmModel = farmModel;
        this.department = department;
        this.batch = batch;
        this.unitCode = unitCode;
    }

    public FarmModel getFarmModel() {
        return farmModel;
    }

    public void setFarmModel(FarmModel farmModel) {
        this.farmModel = farmModel;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Batch getBatch() {
        return batch;
    }

    public void setBatch(Batch batch) {
        this.batch = batch;
    }

    public String getUnitCode() {
        return unitCode;
    }

    public void setUnitCode(String unitCode) {
        this.unitCode = unitCode;
    }
}
