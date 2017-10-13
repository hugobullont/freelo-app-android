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
import me.sadboyz.freelo.adapters.FollowingAdapter;
import me.sadboyz.freelo.models.Application;
import me.sadboyz.freelo.repositories.ApplicationsRepository;

/**
 * A simple {@link Fragment} subclass.
 */
public class FollowingFreelosFragment extends Fragment {

    RecyclerView followingRecyclerView;
    FollowingAdapter followingAdapter;
    RecyclerView.LayoutManager followingLayoutManager;
    List<Application> applications;
    SwipeRefreshLayout followingSwipeRefreshLayout;
    CardView followingAlertCardView;

    public FollowingFreelosFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_following_freelos, container, false);
        followingRecyclerView = (RecyclerView)view.findViewById(R.id.followingRecyclerView);
        followingAlertCardView = (CardView)view.findViewById(R.id.followingAlertCardView);
        followingSwipeRefreshLayout = (SwipeRefreshLayout)view.findViewById(R.id.followingSwipeRefreshLayout);
        followingSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                applications = ApplicationsRepository.getInstance().getOpenApplications();
                followingAdapter.setApplications(applications);
                followingRecyclerView.setAdapter(followingAdapter);
                followingSwipeRefreshLayout.setRefreshing(false);
                validateApplications();
            }
        });

        followingLayoutManager = new LinearLayoutManager(view.getContext());
        followingRecyclerView.setLayoutManager(followingLayoutManager);
        applications = ApplicationsRepository.getInstance().getOpenApplications();
        followingAdapter = new FollowingAdapter(applications);
        followingRecyclerView.setAdapter(followingAdapter);

        validateApplications();
        return view;
    }

    private void validateApplications(){

        if(applications.isEmpty()){
            followingAlertCardView.setVisibility(View.VISIBLE);
            followingSwipeRefreshLayout.setVisibility(View.INVISIBLE);
        }
        else{
            followingAlertCardView.setVisibility(View.INVISIBLE);
            followingSwipeRefreshLayout.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        applications = ApplicationsRepository.getInstance().getOpenApplications();
        followingAdapter.setApplications(applications);
        followingRecyclerView.setAdapter(followingAdapter);
        validateApplications();
    }

}
