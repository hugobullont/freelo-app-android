package me.sadboyz.freelo.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import me.sadboyz.freelo.R;
import me.sadboyz.freelo.models.Reward;

/**
 * Created by Leonel on 23/09/2017.
 */

public class RewardsAdapter extends RecyclerView.Adapter<RewardsAdapter.ViewHolder> {

    private List<Reward> rewards;

    public RewardsAdapter() {
    }

    public RewardsAdapter(List<Reward> rewards) {
        this.setRewards(rewards);
    }


    @Override
    public RewardsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(
                LayoutInflater
                        .from(parent.getContext())
                        .inflate(R.layout.card_rewards,parent,false)
        );
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        final Reward reward = getRewards().get(position);
        holder.nameTextView.setText(reward.getName());
       // holder.descriptionTextView.setText(reward.getDescription());
        holder.priceTextView.setText("S/ "+String.format("%.2f",reward.getPrice()));
       // holder.quantityTextView.setText(String.valueOf(reward.getQuantity()));
        holder.pictureImageView.setImageResource(reward.getPictureID());

    }

    @Override
    public int getItemCount() {
        return getRewards().size();
    }

    public List<Reward> getRewards() {
        return rewards;
    }

    public RewardsAdapter setRewards(List<Reward> rewards) {
        this.rewards = rewards;
        return this;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView;
        //TextView descriptionTextView;
        TextView priceTextView;
        //TextView quantityTextView;
        ImageView pictureImageView;
        public ViewHolder(View itemView) {
            super(itemView);
            nameTextView  =(TextView)itemView.findViewById(R.id.nameRTextView);
            // descriptionTextView = (TextView)itemView.findViewById(R.id.descriptionRTextView);
            priceTextView = (TextView)itemView.findViewById(R.id.priceRTextView);
            // quantityTextView  =(TextView)itemView.findViewById(R.id.quantityRTextView);
            pictureImageView = (ImageView)itemView.findViewById(R.id.pictureRImageView);
        }
    }
}
