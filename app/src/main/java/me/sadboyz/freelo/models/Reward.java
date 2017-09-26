package me.sadboyz.freelo.models;

import android.os.Bundle;

/**
 * Created by Hugo on 23/09/2017.
 */

public class Reward {
    private String idReward;
    private String name;
    private String description;
    private Double price;
    private int quantity;
    private String pictureID;
    private boolean status;


    public Reward() {
    }

    public Reward(String idReward, String name, String description, Double price, int quantity, String pictureID, boolean status) {
        this.setIdReward(idReward);
        this.setName(name);
        this.setDescription(description);
        this.setPrice(price);
        this.setQuantity(quantity);
        this.setPictureID(pictureID);
        this.setStatus(status);
    }

    public String getIdReward() {
        return idReward;
    }

    public Reward setIdReward(String idReward) {
        this.idReward = idReward;
        return this;
    }

    public String getName() {
        return name;
    }

    public Reward setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Reward setDescription(String description) {
        this.description = description;
        return this;
    }

    public Double getPrice() {
        return price;
    }

    public Reward setPrice(Double price) {
        this.price = price;
        return this;
    }

    public int getQuantity() {
        return quantity;
    }

    public Reward setQuantity(int quantity) {
        this.quantity = quantity;
        return this;
    }

    public String getPictureID() {
        return pictureID;
    }

    public Reward setPictureID(String pictureID) {
        this.pictureID = pictureID;
        return this;
    }

    public boolean isStatus() {
        return status;
    }

    public Reward setStatus(boolean status) {
        this.status = status;
        return this;
    }


    public Bundle toBundle(){
        Bundle bundle = new Bundle();
        bundle.putString("idReward",getIdReward());
        bundle.putString("name", getName());
        bundle.putString("description", getDescription());
        bundle.putDouble("price", getPrice());
        bundle.putInt("quantity", getQuantity());
        bundle.putString("pictureID", getPictureID());
        return bundle;
    }

    public static Reward from(Bundle bundle)
    {
        Reward reward = new Reward();
        reward.setIdReward(bundle.getString("idReward"))
                .setName(bundle.getString("name"))
                .setDescription(bundle.getString("description"))
                .setPrice(bundle.getDouble("price"))
                .setQuantity(bundle.getInt("quantity"))
                .setPictureID(bundle.getString("pictureID"));

        return reward;
    }
}
