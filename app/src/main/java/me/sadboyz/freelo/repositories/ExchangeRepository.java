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

import static android.content.ContentValues.TAG;

/**
 * Created by Hugo on 27/09/2017.
 */

public class ExchangeRepository {
    private static ExchangeRepository instance;
    private static List<Exchange> exchanges;

    public static ExchangeRepository getInstance()
    {
        if(exchanges == null) exchanges = new ArrayList<>();
        if(instance != null) return instance;
        instance = new ExchangeRepository();
        return instance;
    }

    public ExchangeRepository AddExchangeToDatabase(String idUser, String idReward){
        String key = DataReference.getInstance().child("exchanges").push().getKey();
        String date = Calendar.getInstance().getTime().toString();
        Exchange exchange = new Exchange(key,idUser,idReward,date,false);
        DataReference.getInstance().child("exchanges").child(key).setValue(exchange);
        return this;
    }

    public void EventLoad()
    {
        DataReference.getInstance().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                long value = dataSnapshot.child("works").getChildrenCount();
                Log.d(TAG,"no of children: "+value);

                Iterable<DataSnapshot> iterable = dataSnapshot.child("works").getChildren();
                exchanges = new ArrayList<Exchange>();
                while(iterable.iterator().hasNext()){
                    Exchange exchange = iterable.iterator().next().getValue(Exchange.class);
                    if(exchange.getIdUser() == SessionVariables.CurrentidUser)
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
