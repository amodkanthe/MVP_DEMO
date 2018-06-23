package com.headytest.android.product_listing;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.headytest.android.R;
import com.google.common.collect.ImmutableListMultimap;
import com.google.common.collect.Multimaps;
import com.headytest.android.enities.Variant;

import java.util.List;
import java.util.Set;

/**
 * Created by scandid on 6/23/2018.
 */

public class VariantRecylerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    ImmutableListMultimap<Integer, Variant> byPrice;
    float tax;

    public VariantRecylerAdapter(ImmutableListMultimap<Integer, Variant> byPrice,
                                 float tax) {
        this.byPrice = byPrice;
        this.tax = tax;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.variant_item_layout, parent, false);
        VariantViewHolder viewHolder = new VariantViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        VariantViewHolder variantViewHolder = (VariantViewHolder) holder;
        Set keyset = byPrice.keySet();
        List<Variant> variants = byPrice.get((Integer) (keyset.toArray())[position]);
        for (Variant variant : variants) {
            variantViewHolder.tvColor.setText(variantViewHolder.tvColor.getText() + variant.getColor() + " ");
            variantViewHolder.tvSize.setText(variantViewHolder.tvSize.getText() + String.valueOf(variant.getSize()) + " ");
        }
        float taxValue = (float) (variants.get(0).getPrice() / tax);
        float price = variants.get(0).getPrice() + taxValue;
        variantViewHolder.tvPrice.setText("Rs. " + variantViewHolder.tvPrice.getText() + String.valueOf((int) Math.abs(price)));

    }

    @Override
    public int getItemCount() {
        return byPrice.keySet().toArray().length;
    }

    class VariantViewHolder extends RecyclerView.ViewHolder {
        TextView tvSize, tvColor, tvPrice;

        public VariantViewHolder(View itemView) {
            super(itemView);
            tvSize = (TextView) itemView.findViewById(R.id.tvSize);
            tvColor = (TextView) itemView.findViewById(R.id.tvColor);
            tvPrice = (TextView) itemView.findViewById(R.id.tvPrice);
        }
    }
}
