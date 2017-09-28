package me.sadboyz.freelo.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import me.sadboyz.freelo.R;
import me.sadboyz.freelo.adapters.SearchAdapter;
import me.sadboyz.freelo.models.Reward;
import me.sadboyz.freelo.models.Work;
import me.sadboyz.freelo.repositories.WorksRepository;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment {

    RecyclerView searchRecyclerView;
    SearchAdapter searchAdapter;
    RecyclerView.LayoutManager searchLayoutManager;
    List<Work> works;



    public SearchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search,container,false);
        searchRecyclerView = (RecyclerView)view.findViewById(R.id.searchRecyclerView);

        searchLayoutManager = new LinearLayoutManager(view.getContext());
        searchRecyclerView.setLayoutManager(searchLayoutManager);
        works = WorksRepository.getInstance().getWorks();
        searchAdapter = new SearchAdapter(works);
        searchRecyclerView.setAdapter(searchAdapter);

        return view;

    }

}
