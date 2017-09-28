package me.sadboyz.freelo.repositories;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import me.sadboyz.freelo.global.SessionVariables;
import me.sadboyz.freelo.models.User;

import static android.content.ContentValues.TAG;

/**
 * Created by Usuario on 27/09/2017.
 */

public class UsersRepository {
    private static UsersRepository instance;
    private User currentUser;
    private String idFacebook;

    public static UsersRepository getInstance(String idFacebook)
    {
        if(instance != null) return instance;
        instance = new UsersRepository();
        instance.setIdFacebook(idFacebook);
        return instance;
    }



    public UsersRepository AddUserToDatabase(String idFacebook)
    {
        String key = DataReference.getInstance().child("users").push().getKey();
        User user = new User(key,idFacebook);
        DataReference.getInstance().child("users").child(key).setValue(user);
        return this;
    }

    public String getIdFacebook() {
        return idFacebook;
    }

    public void setIdFacebook(String idFacebook) {
        this.idFacebook = idFacebook;
    }

    public void EventLoad()
    {
        DataReference.getInstance().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                long value = dataSnapshot.child("users").getChildrenCount();
                Log.d(TAG,"no of children: "+value);

                Iterable<DataSnapshot> iterable = dataSnapshot.child("users").getChildren();
                while(iterable.iterator().hasNext()){
                    User user = iterable.iterator().next().getValue(User.class);
                    if(user.getIdFacebook().equals(idFacebook)) setCurrentUser(user);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG,"Failed to read value.",databaseError.toException());
            }
        });
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public UsersRepository setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
        SessionVariables.CurrentidUser =  currentUser.getIdUser();
        return this;
    }
}
