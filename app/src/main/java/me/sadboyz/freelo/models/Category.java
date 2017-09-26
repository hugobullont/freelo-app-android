package me.sadboyz.freelo.models;

import android.os.Bundle;

/**
 * Created by Hugo on 25/09/2017.
 */

public class Category {
    private String idCategory;
    private String name;

    public Category() {
    }

    public Category(String idCategory, String name) {
        this.setIdCategory(idCategory);
        this.setName(name);
    }

    public String getIdCategory() {
        return idCategory;
    }

    public Category setIdCategory(String idCategory) {
        this.idCategory = idCategory;
        return this;
    }

    public String getName() {
        return name;
    }

    public Category setName(String name) {
        this.name = name;
        return this;
    }

    public Bundle toBundle(){
        Bundle bundle = new Bundle();
        bundle.putString("idCategory",idCategory);
        bundle.putString("name",name);
        return bundle;
    }

    public static Category from(Bundle bundle)
    {
        Category category = new Category();
        category.setIdCategory(bundle.getString("idCategory")).setName(bundle.getString("name"));
        return category;
    }
}
