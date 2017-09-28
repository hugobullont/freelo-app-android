package me.sadboyz.freelo.repositories;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import me.sadboyz.freelo.global.SessionVariables;
import me.sadboyz.freelo.models.Profile;

import static android.content.ContentValues.TAG;

/**
 * Created by Hugo on 27/09/2017.
 */

public class ProfilesRepository {
    private static ProfilesRepository instance;
    private static List<Profile> profiles;

    public static ProfilesRepository getInstance()
    {
        if (profiles == null) profiles = new ArrayList<>();
        if(instance!=null)return instance;
        instance = new ProfilesRepository();
        return instance;
    }

    public ProfilesRepository AddProfileToDatabase(String name, String lastName, String email,String description, String phoneNumber,
                                                   String bank,String bankAccount)
    {
        String key = DataReference.getInstance().child("profiles").push().getKey();
        Profile profile = new Profile(key,name,lastName,email,description,phoneNumber,0.0,bank, bankAccount,0.0, SessionVariables.CurrentidUser);
        DataReference.getInstance().child("profiles").child(key).setValue(profile);
        return  this;
    }

    public void EventLoad()
    {
        DataReference.getInstance().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                long value = dataSnapshot.child("applications").getChildrenCount();
                Log.d(TAG,"no of children: "+value);
                Iterable<DataSnapshot> iterable = dataSnapshot.child("applications").getChildren();
                profiles = new ArrayList<Profile>();
                while(iterable.iterator().hasNext()){
                    Profile profile = iterable.iterator().next().getValue(Profile.class);
                    profiles.add(profile);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG,"Failed to read value.",databaseError.toException());
            }
        });
    }

    public Profile GetProfileByUserId(String idUser)
    {
        for (Profile profile:profiles) {
            if(profile.getIdUser() == idUser)
                return profile;
        }
        return null;
    }

}
