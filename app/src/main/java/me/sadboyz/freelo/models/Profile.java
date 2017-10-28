package me.sadboyz.freelo.models;

import android.os.Bundle;

/**
 * Created by Hugo on 27/09/2017.
 */

public class Profile {
    private String idProfile;
    private String name;
    private String lastName;
    private String email;
    private String description;
    private String phoneNumber;
    private Double rating;
    private String bank;
    private String bankAccount;
    private Double credit;
    private String idUser;
    private int idTheme;

    public Profile() {
    }

    public Profile(String idProfile, String name, String lastName, String email, String description, String phoneNumber, Double rating, String bank, String bankAccount, Double credit, String idUser) {
        this.setIdProfile(idProfile);
        this.setName(name);
        this.setLastName(lastName);
        this.setEmail(email);
        this.setDescription(description);
        this.setPhoneNumber(phoneNumber);
        this.setRating(rating);
        this.setBank(bank);
        this.setBankAccount(bankAccount);
        this.setCredit(credit);
        this.setIdUser(idUser);
        this.setIdTheme(1);
    }


    public String getIdProfile() {
        return idProfile;
    }

    public Profile setIdProfile(String idProfile) {
        this.idProfile = idProfile;
        return this;
    }

    public String getName() {
        return name;
    }

    public Profile setName(String name) {
        this.name = name;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public Profile setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Profile setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Profile setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Profile setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public Double getRating() {
        return rating;
    }

    public Profile setRating(Double rating) {
        this.rating = rating;
        return this;
    }

    public String getBank() {
        return bank;
    }

    public Profile setBank(String bank) {
        this.bank = bank;
        return this;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public Profile setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
        return this;
    }

    public Double getCredit() {
        return credit;
    }

    public Profile setCredit(Double credit) {
        this.credit = credit;
        return this;
    }

    public String getIdUser() {
        return idUser;
    }

    public Profile setIdUser(String idUser) {
        this.idUser = idUser;
        return this;
    }

    public Bundle toBundle()
    {
        Bundle bundle = new Bundle();
        bundle.putString("idProfile",idProfile);
        bundle.putString("name",name);
        bundle.putString("lastName",lastName);
        bundle.putString("email",email);
        bundle.putString("description",description);
        bundle.putString("phoneNumber",phoneNumber);
        bundle.putDouble("rating",rating);
        bundle.putString("bank",bank);
        bundle.putString("bankAccount",bankAccount);
        bundle.putDouble("credit",credit);
        bundle.putString("idUser",idUser);
        bundle.putInt("idTheme",idTheme);
        return bundle;
    }

    public static Profile from(Bundle bundle)
    {
        Profile profile = new Profile();
        profile.setIdProfile(bundle.getString("idProfile")).setName(bundle.getString("name"))
                .setLastName(bundle.getString("lastName")).setEmail(bundle.getString("email"))
                .setDescription(bundle.getString("description")).setPhoneNumber(bundle.getString("phoneNumber"))
                .setRating(bundle.getDouble("rating")).setBank(bundle.getString("bank")).setBankAccount(bundle.getString("bankAccount"))
                .setCredit(bundle.getDouble("credit")).setIdUser(bundle.getString("idUser")).setIdTheme(bundle.getInt("idTheme"));
        return profile;
    }

    public String getCompleteName(){
        return name + " " + lastName;
    }

    public int getIdTheme() {
        return idTheme;
    }

    public Profile setIdTheme(int idTheme) {
        this.idTheme = idTheme;
        return this;
    }
}
