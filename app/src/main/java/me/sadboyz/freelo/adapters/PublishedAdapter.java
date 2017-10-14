package me.sadboyz.freelo.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import me.sadboyz.freelo.R;
import me.sadboyz.freelo.models.Application;
import me.sadboyz.freelo.models.Work;
import me.sadboyz.freelo.repositories.ApplicationsRepository;


/**
 * Created by hugo on 14/10/17.
 */

public class PublishedAdapter extends RecyclerView.Adapter<PublishedAdapter.ViewHolder> {
    private List<Work> works;

    public PublishedAdapter() {
    }

    @Override
    public PublishedAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater
        .from(parent.getContext())
        .inflate(R.layout.card_published,parent,false));
    }

    @Override
    public void onBindViewHolder(PublishedAdapter.ViewHolder holder, int position) {
        Work work = getWorks().get(position);
        List<Application> applications = ApplicationsRepository.getInstance().getApplicationsByWorkId(work.getIdWork());
        holder.namePublishedTextView.setText(work.getName());
        holder.infoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
            }
        });

        holder.postulantsPublishedTextView.setText("Postulantes: "+ applications.size());
    }

    @Override
    public int getItemCount() {
        return works.size();
    }

    public PublishedAdapter(List<Work> works) {
        this.works = works;
    }

    public List<Work> getWorks() {
        return works;
    }

    public void setWorks(List<Work> works) {
        this.works = works;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView namePublishedTextView;
        TextView postulantsPublishedTextView;
        Button infoButton;


        public ViewHolder(View itemView) {
            super(itemView);
            namePublishedTextView = (TextView) itemView.findViewById(R.id.namePublishedTextView);
            postulantsPublishedTextView = (TextView) itemView.findViewById(R.id.postulantsPublishedTextView);
            infoButton = (Button) itemView.findViewById(R.id.infoButton);
        }
    }
}
