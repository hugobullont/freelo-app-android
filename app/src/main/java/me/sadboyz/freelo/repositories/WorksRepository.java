package me.sadboyz.freelo.repositories;

import android.provider.ContactsContract;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import me.sadboyz.freelo.models.Application;
import me.sadboyz.freelo.models.Work;

import static android.content.ContentValues.TAG;

/**
 * Created by Hugo on 25/09/2017.
 */

public class WorksRepository {
    private static WorksRepository instance;
    private static List<Work> works;

    public static WorksRepository getInstance()
    {
        if(works == null) works = new ArrayList<>();
        if(instance != null) return instance;
        instance = new WorksRepository();
        return instance;
    }

    public WorksRepository AddWorkToDatabase(String name, String description, Double basePrice, Double pubPrice, String createdBy, String workedBy, String idCategory, String status)
    {
        String key = DataReference.getInstance().child("works").push().getKey();
        String date = Calendar.getInstance().getTime().toString();
        Work work = new Work(key,name,description,basePrice,pubPrice,date,createdBy,workedBy,idCategory,status);
        DataReference.getInstance().child("works").child(key).setValue(work);
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
                works = new ArrayList<Work>();
                List<Application> applications = ApplicationsRepository.getInstance().getApplicationsOfUser();
                while(iterable.iterator().hasNext()){
                    Work work = iterable.iterator().next().getValue(Work.class);
                    if(work.getStatus()=="open") {
                        for (Application application : applications) {
                            if (work.getIdWork() == application.getIdWork()) continue;
                            works.add(work);
                        }
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG,"Failed to read value.",databaseError.toException());
            }
        });
    }

    public List<Work> getWorks() {return works;}
}
