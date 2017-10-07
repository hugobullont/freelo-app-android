package me.sadboyz.freelo.fragments;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import me.sadboyz.freelo.R;
import me.sadboyz.freelo.adapters.NotificationsPagerAdapter;
import me.sadboyz.freelo.repositories.RewardsRepository;

/**
 * A simple {@link Fragment} subclass.
 */
public class NotificationsFragment extends Fragment implements TabLayout.OnTabSelectedListener {
    private TabLayout notificationsTabLayout;
    private ViewPager notificationsViewPager;


    public NotificationsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_notifications, container, false);

        notificationsTabLayout = (TabLayout) view.findViewById(R.id.notificationsTabLayout);
        notificationsViewPager = (ViewPager) view.findViewById(R.id.notificationsViewPager);

        NotificationsPagerAdapter pagerAdapter = new NotificationsPagerAdapter(getActivity().getSupportFragmentManager(),notificationsTabLayout.getTabCount());

        notificationsViewPager.setAdapter(pagerAdapter);
        notificationsTabLayout.addOnTabSelectedListener(this);



        return view;
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        notificationsViewPager.setCurrentItem(tab.getPosition());

    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}
