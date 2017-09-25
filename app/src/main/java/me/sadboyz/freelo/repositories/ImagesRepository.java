package me.sadboyz.freelo.repositories;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

/**
 * Created by Hugo on 25/09/2017.
 */

public class ImagesRepository {
    private static FirebaseStorage storage;
    private static StorageReference storageRef;
    private static ImagesRepository instance;

    public static ImagesRepository getInstance()
    {
        if(storage == null) storage = FirebaseStorage.getInstance();
        if(instance == null) instance = new ImagesRepository();
        return instance;
    }

    public StorageReference GetStorageReferenceFor(String url)
    {
        StorageReference storageRef = storage.getReference();
        StorageReference pathReference = storageRef.child(url);
        return pathReference;
    }
}
