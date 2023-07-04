package com.far.farmevents.Adapters.models;

import com.far.farmevents.Models.CheckItem;

public class CheckItemModel {
    int id;
    String description;
    boolean checked;
    CheckItem checkItem;

    public CheckItemModel(CheckItem ci) {
        this.checkItem = ci;
        this.id = ci.getId();
        this.description = ci.getDescription();
        this.checked = ci.isChecked();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}
