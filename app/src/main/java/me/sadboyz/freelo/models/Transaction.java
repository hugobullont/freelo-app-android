package me.sadboyz.freelo.models;

import android.os.Bundle;

/**
 * Created by Usuario on 27/09/2017.
 */

public class Transaction {
    private String idTransaction;
    private String idUser;
    private Double amount;
    private String mode;
    private String idObject;
    private String date;
    private String transactionCode;

    public Transaction() {
    }

    public Transaction(String idTransaction, String idUser, Double amount, String mode, String idObject, String transactionCode, String date) {
        this.setIdTransaction(idTransaction);
        this.setIdUser(idUser);
        this.setAmount(amount);
        this.setMode(mode);
        this.setIdObject(idObject);
        this.setTransactionCode(transactionCode);
        this.setDate(date);
    }

    public String getIdTransaction() {
        return idTransaction;
    }

    public Transaction setIdTransaction(String idTransaction) {
        this.idTransaction = idTransaction;
        return this;
    }

    public String getIdUser() {
        return idUser;
    }

    public Transaction setIdUser(String idUser) {
        this.idUser = idUser;
        return this;
    }

    public Double getAmount() {
        return amount;
    }

    public Transaction setAmount(Double amount) {
        this.amount = amount;
        return this;
    }

    public String getMode() {
        return mode;
    }

    public Transaction setMode(String mode) {
        this.mode = mode;
        return this;
    }

    public String getIdObject() {
        return idObject;
    }

    public Transaction setIdObject(String idObject) {
        this.idObject = idObject;
        return this;
    }

    public String getTransactionCode() {
        return transactionCode;
    }

    public Transaction setTransactionCode(String transactionCode) {
        this.transactionCode = transactionCode;
        return this;
    }

    public Bundle toBundle()
    {
        Bundle bundle = new Bundle();
        bundle.putString("idTransaction",idTransaction);
        bundle.putString("idUser",idUser);
        bundle.putDouble("amount",amount);
        bundle.putString("mode",mode);
        bundle.putString("idObject",idObject);
        bundle.putString("transactionCode",transactionCode);
        bundle.putString("date",date);
        return bundle;
    }

    public static Transaction from(Bundle bundle)
    {
        Transaction transaction = new Transaction();
        transaction.setIdTransaction(bundle.getString("idTransaction")).setIdUser(bundle.getString("idUser"))
                .setAmount(bundle.getDouble("amount")).setMode(bundle.getString("mode")).setIdObject(bundle.getString("idObject"))
                .setTransactionCode(bundle.getString("transactionCode")).setDate(bundle.getString("date"));
        return transaction;
    }

    public String getDate() {
        return date;
    }

    public Transaction setDate(String date) {
        this.date = date;
        return this;
    }
}
