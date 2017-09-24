package me.sadboyz.freelo.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
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

    public RewardsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_rewards, container, false);
        rewardsRecyclerView = (RecyclerView)view.findViewById(R.id.rewardsRecyclerView);

        rewardLayoutManager= new GridLayoutManager(view.getContext(),1);
        rewardsRecyclerView.setAdapter(rewardsAdapter);
        rewardsRecyclerView.setLayoutManager(rewardLayoutManager);
        inicializadorDatos();
        inicializadorAdaptador();
        return view;
    }


    public void inicializadorDatos(){
        //rewards = new ArrayList<>();
        /*RewardsRepository.getInstance().AddRewardToDatabase("Hola","Dbieo",25.0,3,R.mipmap.ic_launcher,true);
        RewardsRepository.getInstance().AddRewardToDatabase("Hola", "Bienvenido",35.0, 3, R.mipmap.ic_launcher,true);
        RewardsRepository.getInstance().AddRewardToDatabase("Hola", "Bienvenido",46.0, 3, R.mipmap.ic_launcher,true);
        RewardsRepository.getInstance().AddRewardToDatabase("Hola", "Bienvenido",47.0, 3, R.mipmap.ic_launcher,false);*/
        rewards = RewardsRepository.getInstance().GetActiveRewards();
        /*rewards.add(new Reward("1","Hola","Dbieo",2.3,3,R.mipmap.ic_launcher,true));
        rewards.add(new Reward("2","Hola", "Bienvenido",2.3, 3, R.mipmap.ic_launcher,true));
        rewards.add(new Reward("3","Hola", "Bienvenido",4.6, 3, R.mipmap.ic_launcher,true));
        rewards.add(new Reward("4","Hola", "Bienvenido",4.7, 3, R.mipmap.ic_launcher,false));*/
    }
    public RewardsAdapter adaptador;
    private void inicializadorAdaptador() {
        adaptador = new RewardsAdapter(rewards);
        rewardsRecyclerView.setAdapter(adaptador);
    }

}
