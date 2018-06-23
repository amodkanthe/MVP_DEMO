package com.headytest.android.product_listing;

import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.common.base.Function;
import com.google.common.collect.ImmutableListMultimap;
import com.google.common.collect.Multimaps;
import com.headytest.android.R;
import com.headytest.android.enities.Product;
import com.headytest.android.enities.Variant;

import java.util.List;


/**
 * Created by scandid on 6/23/2018.
 */

public class ProductsRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<Product> products;

    public ProductsRecyclerAdapter(List<Product> products) {
        this.products = products;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_list_item, parent, false);
        ProductViewHolder viewHolder = new ProductViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ProductViewHolder productViewHolder = (ProductViewHolder) holder;
        Product product = products.get(position);
        productViewHolder.tvTitle.setText(product.getName());
        productViewHolder.rvVariants.setLayoutManager(new LinearLayoutManager(holder.itemView.getContext()));
        ImmutableListMultimap<Integer, Variant> byPrice = Multimaps.index(product.getVariants(), TO_PRICE);
        productViewHolder.rvVariants.setAdapter(new VariantRecylerAdapter(byPrice, product.getTax().getValue()));
        productViewHolder.rvVariants.addItemDecoration(new DividerItemDecoration(holder.itemView.getContext(), DividerItemDecoration.VERTICAL));
        productViewHolder.rvVariants.addItemDecoration(new DividerItemDecoration(holder.itemView.getContext(), DividerItemDecoration.VERTICAL));
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder {
        RecyclerView rvVariants;
        TextView tvTitle;

        public ProductViewHolder(View itemView) {
            super(itemView);
            tvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
            rvVariants = (RecyclerView) itemView.findViewById(R.id.rvVariants);

        }
    }

    private static final Function<Variant, Integer> TO_PRICE =
            new Function<Variant, Integer>() {
                @Override
                public Integer apply(Variant item) {
                    return item.getPrice();
                }
            };
}
