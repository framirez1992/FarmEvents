package com.far.farmevents.Models;

public class CheckItem {
    int id;
    String description;
    boolean checked;

    public CheckItem(int id, String description, boolean checked) {
        this.id = id;
        this.description = description;
        this.checked = checked;
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
