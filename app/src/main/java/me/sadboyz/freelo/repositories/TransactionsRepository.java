package me.sadboyz.freelo.repositories;

import android.provider.ContactsContract;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import me.sadboyz.freelo.global.SessionVariables;
import me.sadboyz.freelo.models.Transaction;

import static android.content.ContentValues.TAG;

/**
 * Created by Usuario on 27/09/2017.
 */

public class TransactionsRepository {
    private static TransactionsRepository instance;
    private static List<Transaction> transactions;

    public static TransactionsRepository getInstance()
    {
        if(transactions == null) transactions = new ArrayList<>();
        if(instance != null) return instance;
        instance = new TransactionsRepository();
        return instance;
    }

    public TransactionsRepository AddTransactionToDatabase(String idUser, Double amount, String mode,String idObject)
    {
        String key = DataReference.getInstance().child("transactions").push().getKey();
        String date = Calendar.getInstance().getTime().toString();
        Transaction transaction = new Transaction(key,idUser,amount,mode,idObject,mode+key,date);
        DataReference.getInstance().child("transactions").child(key).setValue(transaction);
        return this;
    }

    public void EventLoad()
    {
        DataReference.getInstance().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                long value = dataSnapshot.child("transactions").getChildrenCount();
                Log.d(TAG,"no of children: "+value);

                Iterable<DataSnapshot> iterable = dataSnapshot.child("transactions").getChildren();
                transactions = new ArrayList<Transaction>();
                while(iterable.iterator().hasNext()){
                    Transaction transaction = iterable.iterator().next().getValue(Transaction.class);
                    if(transaction.getIdUser().equals(SessionVariables.getInstance().getCurrentidUser()))
                        transactions.add(transaction);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG,"Failed to read value.",databaseError.toException());
            }
        });
    }

    public List<Transaction> getTransactionsForUser() { return transactions; }

}
