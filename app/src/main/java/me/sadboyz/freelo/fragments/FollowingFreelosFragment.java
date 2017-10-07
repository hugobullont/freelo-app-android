package me.sadboyz.freelo.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import me.sadboyz.freelo.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FollowingFreelosFragment extends Fragment {


    public FollowingFreelosFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_following_freelos, container, false);
    }

}
