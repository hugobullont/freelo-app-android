package me.sadboyz.freelo.fragments;


import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

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
    TextView creditTextView;
    Spinner themeSpinner;
    Button applyThemeButton;
    Profile profile;


    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        profile = ProfilesRepository.getInstance().GetProfileByUserId(SessionVariables.getInstance().getCurrentidUser());
        profileImage = (CircleImageView) view.findViewById(R.id.profileImage);
        fullNameTextView = (TextView) view.findViewById(R.id.fullNameTextView);
        creditTextView = (TextView) view.findViewById(R.id.creditTextView);
        themeSpinner = (Spinner) view.findViewById(R.id.themeSpinner);
        setThemesToSpinner();
        applyThemeButton = (Button) view.findViewById(R.id.applyThemeButton);
        applyThemeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int idTheme = themeSpinner.getSelectedItemPosition()+1;
                if(idTheme != profile.getIdTheme()) {
                    profile.setIdTheme(idTheme);
                    ProfilesRepository.getInstance().UpdateProfile(profile);
                    getActivity().recreate();
                }
            }
        });

        fullNameTextView.setText(profile.getCompleteName());

        Glide.with(getContext()).load(UsersRepository.getInstance(SessionVariables.getInstance().getFacebookId()).profileImageUrl()).dontAnimate().into(profileImage);
        creditTextView.setText("S/ " + String.format("%.2f",profile.getCredit()));
        return view;
    }

    private void setThemesToSpinner(){
        List<String> themes = new ArrayList<>();
        themes.add("Original");
        themes.add("Verde");
        themes.add("Rojo");
        themes.add("Morado");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item,themes);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        themeSpinner.setAdapter(adapter);
        themeSpinner.setSelection(profile.getIdTheme()-1);
    }

}
