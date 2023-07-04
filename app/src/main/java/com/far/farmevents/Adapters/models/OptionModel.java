package com.far.farmevents.Adapters.models;

import com.far.farmevents.Globals.Global;

public class OptionModel {
    Global.MODULES module;
    int imgResource;
    String title;

    public OptionModel(Global.MODULES module, int imgResource, String title){
        this.module = module;
        this.imgResource = imgResource;
        this.title = title;
    }

    public Global.MODULES getModule() {
        return module;
    }

    public void setModule(Global.MODULES module) {
        this.module = module;
    }

    public int getImgResource() {
        return imgResource;
    }

    public void setImgResource(int imgResource) {
        this.imgResource = imgResource;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return title;
    }
}
