package com.headytest.android.category_listing;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.headytest.android.R;
import com.headytest.android.enities.Category;

import java.util.List;

/**
 * Created by amod on 6/21/2018.
 */

public class CategoryListRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<Category> categoryList;
    CategoryClickListener categoryClickListener;

    public CategoryListRecyclerAdapter(List<Category> categoryList, CategoryClickListener categoryClickListener) {
        this.categoryList = categoryList;
        this.categoryClickListener = categoryClickListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_category_list_item, parent, false);
        CategoryViewHolder categoryViewHolder = new CategoryViewHolder(view);
        return categoryViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        CategoryViewHolder categoryViewHolder = (CategoryViewHolder) holder;
        categoryViewHolder.tvTitle.setText(categoryList.get(position).getName());
        categoryViewHolder.ivArrow.setVisibility(categoryList.get(position).getChildCategoriesList().size() > 0 ? View.VISIBLE : View.GONE);
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    class CategoryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tvTitle;
        ImageView ivArrow;

        public CategoryViewHolder(View itemView) {
            super(itemView);
            tvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
            ivArrow = (ImageView) itemView.findViewById(R.id.ivArrow);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (categoryClickListener != null) {
                categoryClickListener.onCategoryClicked(categoryList.get(getAdapterPosition()));
            }
        }
    }

    public interface CategoryClickListener {
        public void onCategoryClicked(Category category);
    }
}
