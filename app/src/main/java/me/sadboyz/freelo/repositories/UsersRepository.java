package me.sadboyz.freelo.repositories;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import me.sadboyz.freelo.global.SessionVariables;
import me.sadboyz.freelo.models.User;

import static android.content.ContentValues.TAG;

/**
 * Created by Usuario on 27/09/2017.
 */

public class UsersRepository {
    private static UsersRepository instance;
    private static User currentUser;
    private List<User> allUsers;
    private String idFacebook;

    public static UsersRepository getInstance(String idFacebook)
    {
        if(instance != null && instance.getIdFacebook() != null) return instance;
        instance = new UsersRepository();
        instance.setAllUsers(new ArrayList<User>());
        if(idFacebook != null) instance.setIdFacebook(idFacebook);
        currentUser = null;
        return instance;
    }

    public static UsersRepository getInstance(){
        if(instance != null) return instance;
        return null;
    }


    public UsersRepository AddUserToDatabase(String idFacebook)
    {
        String key = DataReference.getInstance().child("users").push().getKey();
        User user = new User(key,idFacebook);
        DataReference.getInstance().child("users").child(key).setValue(user);
        SessionVariables.getInstance().setCurrentidUser(key);
        return this;
    }

    public String getIdFacebook() {
        return idFacebook;
    }

    public void setIdFacebook(String idFacebook) {
        this.idFacebook = idFacebook;
    }

    public void InitialLoad() {
        DataReference.getInstance().addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                long value = dataSnapshot.child("users").getChildrenCount();
                Log.d(TAG,"no of children: "+value);

                Iterable<DataSnapshot> iterable = dataSnapshot.child("users").getChildren();
                while(iterable.iterator().hasNext()){
                    allUsers = new ArrayList<>();
                    User user = iterable.iterator().next().getValue(User.class);
                    allUsers.add(user);

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG,"Failed to read value.",databaseError.toException());
            }
        });
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
                    allUsers = new ArrayList<>();
                    User user = iterable.iterator().next().getValue(User.class);
                    allUsers.add(user);
                    if(user.getIdFacebook().equals(getIdFacebook())) setCurrentUser(user);

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

    public User getUserByFacebookId(){
        for(User u: allUsers){
            if(u.getIdFacebook().equals(getIdFacebook())) return u;
        }
        return null;
    }

    public UsersRepository setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
        SessionVariables.getInstance().setCurrentidUser(currentUser.getIdUser());
        return this;
    }

    public String profileImageUrl(){
        String url = "https://graph.facebook.com/" + getIdFacebook() + "/picture?type=large";
        return url;
    }

    public List<User> getAllUsers() {
        return allUsers;
    }

    public void setAllUsers(List<User> allUsers) {
        this.allUsers = allUsers;
    }
}
