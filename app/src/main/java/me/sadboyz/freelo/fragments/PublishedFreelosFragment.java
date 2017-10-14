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
import me.sadboyz.freelo.adapters.PublishedAdapter;
import me.sadboyz.freelo.models.Work;
import me.sadboyz.freelo.repositories.WorksRepository;

/**
 * A simple {@link Fragment} subclass.
 */
public class PublishedFreelosFragment extends Fragment {

    CardView publishedAlertCardView;
    SwipeRefreshLayout publishedFreelosSwipeRefreshLayout;
    RecyclerView publishedFreelosRecyclerView;
    PublishedAdapter publishedAdapter;
    RecyclerView.LayoutManager publishedFreelosLayoutManager;
    List<Work> works;

    public PublishedFreelosFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_published_freelos, container, false);
        publishedFreelosRecyclerView = (RecyclerView)view.findViewById(R.id.publishedFreelosRecyclerView);
        publishedAlertCardView = (CardView) view.findViewById(R.id.publishedAlertCardView);
        publishedFreelosSwipeRefreshLayout = (SwipeRefreshLayout)view.findViewById(R.id.publishedFreelosSwipeRefreshLayout);
        publishedFreelosSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                works = WorksRepository.getInstance().getPublishedWorks();
                publishedAdapter.setWorks(works);
                publishedFreelosRecyclerView.setAdapter(publishedAdapter);
                publishedFreelosSwipeRefreshLayout.setRefreshing(false);
                validateWorks();

            }
        });

        publishedFreelosLayoutManager = new LinearLayoutManager(view.getContext());
        publishedFreelosRecyclerView.setLayoutManager(publishedFreelosLayoutManager);
        works = WorksRepository.getInstance().getPublishedWorks();
        publishedAdapter = new PublishedAdapter(works);
        publishedFreelosRecyclerView.setAdapter(publishedAdapter);
        validateWorks();
        return view;
    }

    private void validateWorks(){
        if(works.isEmpty()){
            publishedAlertCardView.setVisibility(View.VISIBLE);
            publishedFreelosSwipeRefreshLayout.setVisibility(View.INVISIBLE);
        }
        else{
            publishedAlertCardView.setVisibility(View.INVISIBLE);
            publishedFreelosSwipeRefreshLayout.setVisibility(View.VISIBLE);
        }
    }


    @Override
    public void onResume() {
        super.onResume();
        works = WorksRepository.getInstance().getPublishedWorks();
        publishedAdapter.setWorks(works);
        publishedFreelosRecyclerView.setAdapter(publishedAdapter);
        validateWorks();
    }
}
