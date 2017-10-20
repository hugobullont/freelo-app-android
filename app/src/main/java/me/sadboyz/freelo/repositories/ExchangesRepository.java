package me.sadboyz.freelo.repositories;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import me.sadboyz.freelo.global.SessionVariables;
import me.sadboyz.freelo.models.Exchange;
import me.sadboyz.freelo.models.Profile;
import me.sadboyz.freelo.models.Reward;

import static android.content.ContentValues.TAG;

/**
 * Created by Hugo on 27/09/2017.
 */

public class ExchangesRepository {
    private static ExchangesRepository instance;
    private static List<Exchange> exchanges;

    public static ExchangesRepository getInstance()
    {
        if(exchanges == null) exchanges = new ArrayList<>();
        if(instance != null) return instance;
        instance = new ExchangesRepository();
        return instance;
    }

    public boolean AddExchangeToDatabase(String idUser, String idReward){
        String key = DataReference.getInstance().child("exchanges").push().getKey();
        String date = Calendar.getInstance().getTime().toString();

        Reward reward = RewardsRepository.getInstance().getRewardById(idReward);

        if(!ValidatePost(reward)) return false;
        Exchange exchange = new Exchange(key,idUser,idReward,date,false);
        TransactionsRepository.getInstance().AddTransactionToDatabase(SessionVariables.CurrentidUser,reward.getPrice(),"exchange",key);
        DataReference.getInstance().child("exchanges").child(key).setValue(exchange);
        return true;
    }

    private boolean ValidatePost(Reward reward){
        Profile profile = ProfilesRepository.getInstance().GetProfileByUserId(SessionVariables.CurrentidUser);

        if(profile.getCredit() - reward.getPrice() < 0){
            return false;
        }
        Double credit = profile.getCredit() - reward.getPrice();
        profile.setCredit(credit);
        ProfilesRepository.getInstance().UpdateProfile(profile);
        RewardsRepository.getInstance().UpdateRewardQuantity(reward);
        return true;
    }

    public boolean ValidateCredit(Reward reward){
        if(ProfilesRepository.getInstance().GetProfileByUserId(SessionVariables.CurrentidUser).getCredit()
                - reward.getPrice() < 0){
            return false;
        }
        return true;
    }

    public void EventLoad()
    {
        DataReference.getInstance().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                long value = dataSnapshot.child("exchanges").getChildrenCount();
                Log.d(TAG,"no of children: "+value);

                Iterable<DataSnapshot> iterable = dataSnapshot.child("exchanges").getChildren();
                exchanges = new ArrayList<Exchange>();
                while(iterable.iterator().hasNext()){
                    Exchange exchange = iterable.iterator().next().getValue(Exchange.class);
                    if(exchange.getIdUser().equals(SessionVariables.CurrentidUser))
                        exchanges.add(exchange);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG,"Failed to read value.",databaseError.toException());
            }
        });
    }

    public List<Exchange> getExchangesOfUser()
    {
        return exchanges;
    }
}
