package me.sadboyz.freelo.fragments;


import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import me.sadboyz.freelo.R;
import me.sadboyz.freelo.repositories.ProfilesRepository;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {

    Button saveprofileButton;
    TextInputEditText nameprofileTextEditText;
    TextInputEditText lastnameprofileTextInputEditText;
    TextInputEditText emailprofileTextInputEditText;

    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        /*nameprofileTextEditText = (TextInputEditText) view.findViewById(R.id.nameInputTextView);
        lastnameprofileTextInputEditText = (TextInputEditText) view.findViewById(R.id.lastnameProfileInputTextView);
        emailprofileTextInputEditText = (TextInputEditText) view.findViewById(R.id.emailProfileInputTextView);
        saveprofileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProfilesRepository pr = new ProfilesRepository();

            }
        });

        */
        return view;
    }

}
