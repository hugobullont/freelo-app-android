package me.sadboyz.freelo.models;

import android.os.Bundle;

/**
 * Created by Hugo on 25/09/2017.
 */

public class Work {
    private String idWork;
    private String name;
    private String description;
    private Double basePrice;
    private Double pubPrice;
    private String date;
    private String createdBy;
    private String workedBy;
    private String idCategory;
    private String status;

    public Work() {
    }

    public Work(String idWork, String name, String description, Double basePrice, Double pubPrice, String date, String createdBy, String workedBy, String idCategory,String status) {
        this.setIdWork(idWork);
        this.setName(name);
        this.setDescription(description);
        this.setBasePrice(basePrice);
        this.setPubPrice(pubPrice);
        this.setDate(date);
        this.setCreatedBy(createdBy);
        this.setWorkedBy(workedBy);
        this.setIdCategory(idCategory);
        this.setStatus(status);
    }

    public String getIdWork() {
        return idWork;
    }

    public Work setIdWork(String idWork) {
        this.idWork = idWork;
        return this;
    }

    public String getName() {
        return name;
    }

    public Work setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Work setDescription(String description) {
        this.description = description;
        return this;
    }

    public Double getBasePrice() {
        return basePrice;
    }

    public Work setBasePrice(Double basePrice) {
        this.basePrice = basePrice;
        return this;
    }

    public Double getPubPrice() {
        return pubPrice;
    }

    public Work setPubPrice(Double pubPrice) {
        this.pubPrice = pubPrice;
        return this;
    }

    public String getDate() {
        return date;
    }

    public Work setDate(String date) {
        this.date = date;
        return this;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public Work setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public String getWorkedBy() {
        return workedBy;
    }

    public Work setWorkedBy(String workedBy) {
        this.workedBy = workedBy;
        return this;
    }

    public Bundle toBundle(){
        Bundle bundle = new Bundle();
        bundle.putString("idWork",idWork);
        bundle.putString("name",name);
        bundle.putString("description",description);
        bundle.putDouble("basePrice",basePrice);
        bundle.putDouble("pubPrice",pubPrice);
        bundle.putString("date",date);
        bundle.putString("createdBy",createdBy);
        bundle.putString("workedBy",workedBy);
        bundle.putString("idCategory",idCategory);
        bundle.putString("status",status);
        return bundle;
    }

    public static Work from(Bundle bundle)
    {
        Work work = new Work();
        work.setIdWork(bundle.getString("idWork")).setName(bundle.getString("name"))
                .setDescription(bundle.getString("description")).setBasePrice(bundle.getDouble("basePrice"))
                .setPubPrice(bundle.getDouble("pubPrice")).setDate(bundle.getString("date"))
                .setCreatedBy(bundle.getString("createdBy")).setWorkedBy(bundle.getString("workedBy"))
                .setIdCategory(bundle.getString("idCategory")).setStatus(bundle.getString("status"));
        return work;
    }

    public String getIdCategory() {
        return idCategory;
    }

    public Work setIdCategory(String idCategory) {
        this.idCategory = idCategory;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public Work setStatus(String status) {
        this.status = status;
        return this;
    }
}
