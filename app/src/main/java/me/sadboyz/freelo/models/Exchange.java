package me.sadboyz.freelo.models;

import android.os.Bundle;

/**
 * Created by Usuario on 27/09/2017.
 */

public class Exchange {
    private String idExchange;
    private String idUser;
    private String idReward;
    private String date;
    private boolean status;

    public Exchange() {
    }

    public Exchange(String idExchange, String idUser, String idReward, String date, boolean status) {
        this.setIdExchange(idExchange);
        this.setIdUser(idUser);
        this.setIdReward(idReward);
        this.setDate(date);
        this.setStatus(status);
    }

    public String getIdExchange() {
        return idExchange;
    }

    public Exchange setIdExchange(String idExchange) {
        this.idExchange = idExchange;
        return this;
    }

    public String getIdUser() {
        return idUser;
    }

    public Exchange setIdUser(String idUser) {
        this.idUser = idUser;
        return this;
    }

    public String getIdReward() {
        return idReward;
    }

    public Exchange setIdReward(String idReward) {
        this.idReward = idReward;
        return this;
    }

    public String getDate() {
        return date;
    }

    public Exchange setDate(String date) {
        this.date = date;
        return this;
    }

    public Bundle toBundle(){
        Bundle bundle = new Bundle();
        bundle.putString("idExchange",idExchange);
        bundle.putString("idUser",idUser);
        bundle.putString("idReward",idReward);
        bundle.putString("date",date);
        bundle.putBoolean("status",status);
        return bundle;
    }

    public static Exchange from(Bundle bundle)
    {
        Exchange exchange = new Exchange();
        exchange.setIdExchange(bundle.getString("idExchange")).setIdReward(bundle.getString("idReward"))
                .setIdUser(bundle.getString("idUser")).setDate(bundle.getString("date")).setStatus(bundle.getBoolean("status"));
        return exchange;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
