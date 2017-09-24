package me.sadboyz.freelo.repositories;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import me.sadboyz.freelo.models.Reward;

import static android.content.ContentValues.TAG;

/**
 * Created by Usuario on 23/09/2017.
 */

public class RewardsRepository {
    private static DatabaseReference mDatabase;
    private static RewardsRepository instance;
    private List<Reward> rewards;
    public static RewardsRepository getInstance()
    {
        if(mDatabase == null)
        {
            mDatabase = FirebaseDatabase.getInstance().getReference();
        }

        if(instance != null) return instance;
        instance = new RewardsRepository();
        return instance;
    }

    public RewardsRepository AddRewardToDatabase(String name, String description, Double price, int quantity, int pictureId, boolean status){
        String key = mDatabase.push().getKey();
        Reward reward = new Reward(key,name,description,price,quantity,pictureId,status);
        mDatabase.child("rewards").setValue(reward);
        return this;
    }

    public List<Reward> GetActiveRewards()
    {
        rewards = new ArrayList<>();
        List<Reward> original = new ArrayList<>();

        mDatabase.addValueEventListener(new ValueEventListener(){
            @Override
            public void onDataChange(DataSnapshot dataSnapshot){
          /* This method is called once with the initial value and again whenever data at this location is updated.*/
                long value=dataSnapshot.getChildrenCount();
                Log.d(TAG,"no of children: "+value);

                GenericTypeIndicator<List<Reward>> genericTypeIndicator =new GenericTypeIndicator<List<Reward>>(){};

                rewards = dataSnapshot.getValue(genericTypeIndicator);
            }

            @Override
            public void onCancelled(DatabaseError error){
                // Failed to read value
                Log.w(TAG,"Failed to read value.",error.toException());
            }
        });

        original = rewards;
        rewards = new ArrayList<>();

        for (Reward r: original) {
            if(r.isStatus()){
                rewards.add(r);
            }
        }

        return rewards;
    }


}
