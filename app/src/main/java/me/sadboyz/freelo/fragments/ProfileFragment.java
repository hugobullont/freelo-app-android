package me.sadboyz.freelo.fragments;


import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import de.hdodenhof.circleimageview.CircleImageView;
import me.sadboyz.freelo.R;
import me.sadboyz.freelo.global.SessionVariables;
import me.sadboyz.freelo.models.Profile;
import me.sadboyz.freelo.repositories.ProfilesRepository;
import me.sadboyz.freelo.repositories.UsersRepository;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {

    CircleImageView profileImage;
    TextView fullNameTextView;


    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        Profile profile = ProfilesRepository.getInstance().GetProfileByUserId(SessionVariables.CurrentidUser);
        profileImage = (CircleImageView) view.findViewById(R.id.profileImage);
        fullNameTextView = (TextView) view.findViewById(R.id.fullNameTextView);

        fullNameTextView.setText(profile.getCompleteName());

        Glide.with(getContext()).load(UsersRepository.getInstance(SessionVariables.FacebookId).profileImageUrl()).dontAnimate().into(profileImage);

        return view;
    }

}
