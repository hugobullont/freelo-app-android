package me.sadboyz.freelo.repositories;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import me.sadboyz.freelo.global.SessionVariables;
import me.sadboyz.freelo.models.Application;

import static android.content.ContentValues.TAG;

/**
 * Created by Usuario on 27/09/2017.
 */

public class ApplicationsRepository {
    private static ApplicationsRepository instance;
    private static List<Application> applications;

    public static ApplicationsRepository getInstance()
    {
        if(applications == null) applications = new ArrayList<>();
        if(instance != null) return instance;
        instance = new ApplicationsRepository();
        return instance;
    }

    public ApplicationsRepository AddApplicationToDatabase(String idUser, String idWork)
    {
        String key = DataReference.getInstance().child("applications").push().getKey();
        String date = Calendar.getInstance().getTime().toString();
        Application application = new Application(key,idWork,idUser,date,"open");
        DataReference.getInstance().child("applications").child(key).setValue(application);
        return this;
    }

    public void EventLoad()
    {
        DataReference.getInstance().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                long value = dataSnapshot.child("applications").getChildrenCount();
                Log.d(TAG,"no of children: "+value);
                Iterable<DataSnapshot> iterable = dataSnapshot.child("applications").getChildren();
                applications = new ArrayList<Application>();
                while(iterable.iterator().hasNext()){
                    Application application = iterable.iterator().next().getValue(Application.class);
                    if(application.getIdUser().equals(SessionVariables.CurrentidUser))
                        applications.add(application);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG,"Failed to read value.",databaseError.toException());
            }
        });
    }

    public List<Application> getApplicationsOfUser()
    {
        return applications;
    }

    public List<Application> getOpenApplications() {
        List<Application> openApplications = new ArrayList<>();
        for(Application app : applications){
            if(app.getStatus().equals("open")){
                openApplications.add(app);
            }
        }
        return openApplications;
    }

    public List<Application> getSelectedApplications(){
        List<Application> selectedApplications = new ArrayList<>();
        for(Application app : applications){
            if(app.getStatus().equals("selected")){
                selectedApplications.add(app);
            }
        }
        return selectedApplications;
    }

    public List<Application> getFinishApplications(){
        List<Application> finishApplications = new ArrayList<>();
        for(Application app : applications){
            if(app.getStatus().equals("finish")){
                finishApplications.add(app);
            }
        }
        return finishApplications;
    }

    public List<Application> getApplicationsByWorkId(String workId){
        List<Application> workApplications = new ArrayList<>();
        for(Application app : applications){
            if(app.getIdWork().equals(workId)){
                workApplications.add(app);
            }
        }
        return workApplications;
    }

    public ApplicationsRepository setUnfollowApplication(Application application){
        application.setStatus("deleted");
        DataReference.getInstance().child("applications").child(application.getIdApplication()).setValue(application);
        return this;
    }

}
