package me.sadboyz.freelo.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import me.sadboyz.freelo.R;
import me.sadboyz.freelo.models.Work;

/**
 * Created by Andre Puente on 27/09/2017.
 */

public class SearchAdapter  extends RecyclerView.Adapter<SearchAdapter.ViewHolder>{

    private List<Work> works;

    public SearchAdapter() {
    }

    public SearchAdapter(List<Work> works) {

        this.setWorks(works);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(
                LayoutInflater
                    .from(parent.getContext())
                    .inflate(R.layout.card_search,parent,false)
        );
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Work work = getWorks().get(position);
        holder.nameTextView.setText(work.getName());
        holder.pubPriceTextView.setText("S/ "+String.format("%.2f",work.getPubPrice()
                )
        );
    }

    @Override
    public int getItemCount() {
        return getWorks().size();
    }

    public List<Work> getWorks() {
        return works;
    }

    public SearchAdapter setWorks(List<Work> works) {
        this.works = works;
        return this;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView;
        TextView pubPriceTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            nameTextView = (TextView) itemView.findViewById(R.id.nameSTextView);
            pubPriceTextView =(TextView)itemView.findViewById(R.id.pubPriceTextView);
        }
    }
}
