package com.headytest.android.product_listing;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.headytest.android.R;
import com.headytest.android.enities.Ranking;

import java.util.List;

/**
 * Created by scandid on 6/24/2018.
 */

public class SortRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<Ranking> rankings;
    SortClickListener sortClickListener;

    public SortRecyclerAdapter(List<Ranking> rankings, SortClickListener sortClickListener) {
        this.rankings = rankings;
        this.sortClickListener = sortClickListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sort_item, parent, false);
        SortHolder viewHolder = new SortHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Ranking ranking = rankings.get(position);
        SortHolder sortHolder = (SortHolder) holder;
        sortHolder.tvTitle.setText(ranking.getRanking());

    }

    @Override
    public int getItemCount() {
        return rankings.size();
    }

    public class SortHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvTitle;

        public SortHolder(View itemView) {
            super(itemView);
            tvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (sortClickListener != null) {
                sortClickListener.onSortClick(rankings.get(getAdapterPosition()));
            }
        }
    }

    public interface SortClickListener {
        public void onSortClick(Ranking ranking);
    }
}
