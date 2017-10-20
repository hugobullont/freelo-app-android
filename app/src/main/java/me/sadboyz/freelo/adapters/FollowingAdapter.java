package me.sadboyz.freelo.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import me.sadboyz.freelo.R;
import me.sadboyz.freelo.activities.FollowingFreelosActivity;
import me.sadboyz.freelo.activities.WorkActivity;
import me.sadboyz.freelo.models.Application;
import me.sadboyz.freelo.models.Work;
import me.sadboyz.freelo.repositories.WorksRepository;

/**
 * Created by hugo on 12/10/17.
 */

public class FollowingAdapter extends RecyclerView.Adapter<FollowingAdapter.ViewHolder> {
    private List<Application> applications;

    public FollowingAdapter() {
    }

    public FollowingAdapter(List<Application> applications) {
        this.setApplications(applications);
    }


    @Override
    public FollowingAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(
                LayoutInflater
                        .from(parent.getContext())
                        .inflate(R.layout.card_following,parent,false)
        );
    }

    @Override
    public void onBindViewHolder(FollowingAdapter.ViewHolder holder, int position) {
        Application application = getApplications().get(position);
        final Work work = WorksRepository.getInstance().getWorkById(application.getIdWork());
        if(work != null) {
            holder.nameFollowingTextView.setText(work.getName());
            holder.infoButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = v.getContext();
                    Intent intent = new Intent("Following",null,context, WorkActivity.class);
                    intent.putExtras(work.toBundle());
                    context.startActivity(intent);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return applications.size();
    }

    public List<Application> getApplications() {
        return applications;
    }

    public void setApplications(List<Application> applications) {
        this.applications = applications;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView nameFollowingTextView;
        Button infoButton;
        public ViewHolder(View itemView) {
            super(itemView);
            nameFollowingTextView = (TextView) itemView.findViewById(R.id.nameFollowingTextView);
            infoButton = (Button) itemView.findViewById(R.id.infoButton);
        }
    }
}
