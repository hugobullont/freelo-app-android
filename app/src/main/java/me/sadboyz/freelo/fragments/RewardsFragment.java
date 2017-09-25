package me.sadboyz.freelo.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import me.sadboyz.freelo.R;
import me.sadboyz.freelo.adapters.RewardsAdapter;
import me.sadboyz.freelo.models.Reward;
import me.sadboyz.freelo.repositories.RewardsRepository;

/**
 * A simple {@link Fragment} subclass.
 */
public class RewardsFragment extends Fragment {
    RecyclerView rewardsRecyclerView;
    RewardsAdapter rewardsAdapter;
    RecyclerView.LayoutManager rewardLayoutManager;
    List<Reward> rewards;
    SwipeRefreshLayout rewardsSwipeRefreshLayout;

    public RewardsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_rewards, container, false);
        rewardsRecyclerView = (RecyclerView)view.findViewById(R.id.rewardsRecyclerView);
        rewardsSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.rewardsSwipeRefreshLayout);
        rewardsSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //RewardsRepository.getInstance().SetActiveRewards();
                rewards = RewardsRepository.getInstance().getRewards();
                rewardsAdapter.setRewards(rewards);
                rewardsRecyclerView.setAdapter(rewardsAdapter);
                rewardsSwipeRefreshLayout.setRefreshing(false);
            }
        });

        rewardLayoutManager= new LinearLayoutManager(view.getContext());
        rewardsRecyclerView.setLayoutManager(rewardLayoutManager);
        rewards = RewardsRepository.getInstance().getRewards();
        rewardsAdapter = new RewardsAdapter(rewards);
        rewardsRecyclerView.setAdapter(rewardsAdapter);
        return view;
    }




}
