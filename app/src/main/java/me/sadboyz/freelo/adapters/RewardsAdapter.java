package me.sadboyz.freelo.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.List;

import me.sadboyz.freelo.R;
import me.sadboyz.freelo.activities.MainActivity;
import me.sadboyz.freelo.activities.RewardsActivity;
import me.sadboyz.freelo.models.Reward;
import me.sadboyz.freelo.repositories.ImagesRepository;

/**
 * Created by Leonel on 23/09/2017.
 */

public class RewardsAdapter extends RecyclerView.Adapter<RewardsAdapter.ViewHolder> {

    private List<Reward> rewards;
    private Fragment fragment;

    public RewardsAdapter() {
    }

    public RewardsAdapter(List<Reward> rewards, Fragment fragment) {
        this.setRewards(rewards);
        this.setFragment(fragment);
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
    public void onBindViewHolder(final ViewHolder holder, int position) {

        final Reward reward = getRewards().get(position);
        holder.nameTextView.setText(reward.getName());
       // holder.descriptionTextView.setText(reward.getDescription());
        holder.priceTextView.setText("S/ "+String.format("%.2f",reward.getPrice()));
       // holder.quantityTextView.setText(String.valueOf(reward.getQuantity()));
        //holder.pictureImageView.setImageResource(reward.getPictureID());


        ImagesRepository.getInstance().GetStorageReferenceFor(reward.getPictureID())
                .getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Glide.with(fragment).load(uri.toString()).into(holder.pictureImageView);
            }
        });


        holder.buttonR.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                Intent intent = new Intent(context, RewardsActivity.class);
                intent.putExtras(reward.toBundle());
                context.startActivity(intent);

                /*
                Context context = view.getContext();
                Intent intent = new Intent(context,RewardsActivity.class);
                context.startActivity(intent);*/

            }
        });


        /*Glide.with(fragment).using(new FirebaseImageLoader())
                .load(ImagesRepository.getInstance().GetStorageReferenceFor(reward.getPictureID()))
                .into(holder.pictureImageView);*/
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

    public Fragment getFragment() {
        return fragment;
    }

    public void setFragment(Fragment fragment) {
        this.fragment = fragment;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView;
        //TextView descriptionTextView;
        TextView priceTextView;
        //TextView quantityTextView;
        ImageView pictureImageView;
        Button buttonR;
        public ViewHolder(View itemView) {
            super(itemView);
            nameTextView  =(TextView)itemView.findViewById(R.id.nameRTextView);
            // descriptionTextView = (TextView)itemView.findViewById(R.id.descriptionRTextView);
            priceTextView = (TextView)itemView.findViewById(R.id.priceRTextView);
            // quantityTextView  =(TextView)itemView.findViewById(R.id.quantityRTextView);
            pictureImageView = (ImageView)itemView.findViewById(R.id.pictureRImageView);
            buttonR = (Button)itemView.findViewById(R.id.buttonR);
        }
    }
}
