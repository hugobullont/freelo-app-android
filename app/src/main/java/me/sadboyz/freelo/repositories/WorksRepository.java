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

import me.sadboyz.freelo.global.SessionVariables;
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

    public WorksRepository AddWorkToDatabase(String name, String description, Double basePrice, Double pubPrice, String createdBy, String idCategory)
    {
        String key = DataReference.getInstance().child("works").push().getKey();
        String date = Calendar.getInstance().getTime().toString();
        Work work = new Work(key,name,description,basePrice,pubPrice,date,createdBy,"null",idCategory,"open");
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
                while(iterable.iterator().hasNext()){
                    Work work = iterable.iterator().next().getValue(Work.class);
                    if(work.getStatus().equals("open") && !work.getCreatedBy().equals(SessionVariables.CurrentidUser)) {
                        works.add(work);
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG,"Failed to read value.",databaseError.toException());
            }
        });
    }
    public Work getWorkById(String workId){
        for (Work work:works) {
            if(work.getIdWork().equals(workId))
                return work;
        }
        return null;
    }


    public List<Work> getWorks() {
        List<Application> applications = ApplicationsRepository.getInstance().getApplicationsOfUser();

        List<Work> worksNoApps = works;
        for(Application application : applications) {
            for(Work work : worksNoApps){
                if(application.getIdWork().equals(work.getIdWork())){
                    worksNoApps.remove(work); break;
                }
            }
        }


        return worksNoApps;}
}
