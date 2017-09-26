package me.sadboyz.freelo.repositories;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import me.sadboyz.freelo.models.Category;

import static android.content.ContentValues.TAG;

/**
 * Created by Hugo on 25/09/2017.
 */

public class CategoriesRepository {
    private static CategoriesRepository instance;
    private static List<Category> categories;

    public static CategoriesRepository getInstance()
    {
        if(categories==null) categories = new ArrayList<>();
        if(instance != null) return instance;
        instance = new CategoriesRepository();
        return instance;
    }

    public CategoriesRepository AddCategoryToDatabase(String name)
    {
        String key = DataReference.getInstance().child("categories").push().getKey();
        Category category = new Category(key,name);
        DataReference.getInstance().child("categories").child(key).setValue(category);
        return this;
    }

    public void EventLoad()
    {
        DataReference.getInstance().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                long value = dataSnapshot.child("categories").getChildrenCount();
                Log.d(TAG,"no of children: "+value);

                Iterable<DataSnapshot> iterable = dataSnapshot.child("categories").getChildren();
                categories = new ArrayList<Category>();
                while (iterable.iterator().hasNext()){
                    Category category = iterable.iterator().next().getValue(Category.class);
                    categories.add(category);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG,"Failed to read value.",databaseError.toException());
            }
        });
    }

    public List<Category> getCategories(){
        return categories;
    }
}
