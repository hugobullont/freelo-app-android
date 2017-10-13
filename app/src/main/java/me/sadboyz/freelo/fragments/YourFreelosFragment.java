package me.sadboyz.freelo.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import me.sadboyz.freelo.R;
import me.sadboyz.freelo.adapters.YourFreelosAdapter;
import me.sadboyz.freelo.models.Application;
import me.sadboyz.freelo.repositories.ApplicationsRepository;

/**
 * A simple {@link Fragment} subclass.
 */
public class YourFreelosFragment extends Fragment {

    CardView yourFreelosAlertCardView;
    SwipeRefreshLayout yourFreelosSwipeRefreshLayout;
    RecyclerView yourFreelosRecyclerView;
    YourFreelosAdapter yourFreelosAdapter;
    RecyclerView.LayoutManager yourFreelosLayoutManager;
    List<Application> applications;

    public YourFreelosFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_your_freelos, container, false);
        yourFreelosRecyclerView = (RecyclerView)view.findViewById(R.id.yourFreelosRecyclerView);
        yourFreelosAlertCardView = (CardView) view.findViewById(R.id.yourFreelosAlertCardView);
        yourFreelosSwipeRefreshLayout = (SwipeRefreshLayout)view.findViewById(R.id.yourFreelosSwipeRefreshLayout);
        yourFreelosSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                applications = ApplicationsRepository.getInstance().getSelectedApplications();
                yourFreelosAdapter.setApplications(applications);
                yourFreelosRecyclerView.setAdapter(yourFreelosAdapter);
                yourFreelosSwipeRefreshLayout.setRefreshing(false);
                validateApplications();

            }
        });

        yourFreelosLayoutManager = new LinearLayoutManager(view.getContext());
        yourFreelosRecyclerView.setLayoutManager(yourFreelosLayoutManager);
        applications = ApplicationsRepository.getInstance().getSelectedApplications();
        yourFreelosAdapter = new YourFreelosAdapter(applications);
        yourFreelosRecyclerView.setAdapter(yourFreelosAdapter);
        validateApplications();
        return view;
    }

    private void validateApplications(){

        if(applications.isEmpty()){
            yourFreelosAlertCardView.setVisibility(View.VISIBLE);
            yourFreelosSwipeRefreshLayout.setVisibility(View.INVISIBLE);
        }
        else{
            yourFreelosAlertCardView.setVisibility(View.INVISIBLE);
            yourFreelosSwipeRefreshLayout.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        applications = ApplicationsRepository.getInstance().getSelectedApplications();
        yourFreelosAdapter.setApplications(applications);
        yourFreelosRecyclerView.setAdapter(yourFreelosAdapter);
        validateApplications();
    }
}
