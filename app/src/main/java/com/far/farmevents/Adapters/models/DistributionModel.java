package com.far.farmevents.Adapters.models;

public class DistributionModel {
    String sourceUnit, sex, incubator, provider, sourceBatch, sourceFarm;
    int quantity,age;

    public DistributionModel(){

    }

    public DistributionModel(String sourceUnit, String sex, String incubator, String provider, String sourceBatch, String sourceFarm, int quantity, int age) {
        this.sourceUnit = sourceUnit;
        this.sex = sex;
        this.incubator = incubator;
        this.provider = provider;
        this.sourceBatch = sourceBatch;
        this.sourceFarm = sourceFarm;
        this.quantity = quantity;
        this.age = age;
    }

    public String getSourceUnit() {
        return sourceUnit;
    }

    public void setSourceUnit(String sourceUnit) {
        this.sourceUnit = sourceUnit;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getIncubator() {
        return incubator;
    }

    public void setIncubator(String incubator) {
        this.incubator = incubator;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getSourceBatch() {
        return sourceBatch;
    }

    public void setSourceBatch(String sourceBatch) {
        this.sourceBatch = sourceBatch;
    }

    public String getSourceFarm() {
        return sourceFarm;
    }

    public void setSourceFarm(String sourceFarm) {
        this.sourceFarm = sourceFarm;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
