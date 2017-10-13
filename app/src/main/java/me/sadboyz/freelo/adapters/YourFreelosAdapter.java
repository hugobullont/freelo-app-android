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
import me.sadboyz.freelo.repositories.WorksRepository;

/**
 * Created by hugo on 13/10/17.
 */

public class YourFreelosAdapter extends RecyclerView.Adapter<YourFreelosAdapter.ViewHolder> {
    private List<Application> applications;

    public YourFreelosAdapter() {
    }

    @Override
    public YourFreelosAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater
        .from(parent.getContext())
        .inflate(R.layout.card_your_freelos,parent,false));
    }

    @Override
    public void onBindViewHolder(YourFreelosAdapter.ViewHolder holder, int position) {
        Application application = getApplications().get(position);
        Work work = WorksRepository.getInstance().getWorkById(application.getIdWork());
        if(work != null){
            holder.nameYourFreeloTextView.setText(work.getName());
            holder.priceYourFreeloTextView.setText("S/ "+String.format("%.2f",work.getPubPrice()));
            holder.infoButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = v.getContext();
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return getApplications().size();
    }

    public YourFreelosAdapter(List<Application> applications) {
        this.setApplications(applications);
    }

    public List<Application> getApplications() {
        return applications;
    }

    public void setApplications(List<Application> applications) {
        this.applications = applications;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView nameYourFreeloTextView;
        TextView priceYourFreeloTextView;
        Button infoButton;

        public ViewHolder(View itemView) {
            super(itemView);
            nameYourFreeloTextView = (TextView) itemView.findViewById(R.id.nameYourFreeloTextView);
            priceYourFreeloTextView = (TextView)itemView.findViewById(R.id.priceYourFreeloTextView);
            infoButton = (Button)itemView.findViewById(R.id.infoButton);
        }
    }
}
