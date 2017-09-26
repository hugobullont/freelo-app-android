package me.sadboyz.freelo.repositories;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Hugo on 25/09/2017.
 */

public class DataReference {
    private static DatabaseReference mDatabase;
    public static DatabaseReference getInstance()
    {
        if(mDatabase == null)
        {
            mDatabase = FirebaseDatabase.getInstance().getReference();
        }
        return mDatabase;
    }
}
