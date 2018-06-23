package com.headytest.android.category_listing;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;

import com.headytest.android.AScope;
import com.headytest.android.enities.Category;

import java.util.List;

import dagger.Module;
import dagger.Provides;

/**
 * Created by scandid on 6/21/2018.
 */
@Module
public class FragmentListModule {

    Context context;
    List<Category> categoryList;
    CategoryListRecyclerAdapter.CategoryClickListener categoryClickListener;


    public FragmentListModule(Context context,
                              List<Category> categoryList,
                              CategoryListRecyclerAdapter.CategoryClickListener categoryClickListener) {
        this.context = context;
        this.categoryList = categoryList;
        this.categoryClickListener = categoryClickListener;
    }

    @AScope
    @Provides
    LinearLayoutManager providesLinearLayoutManager() {
        return new LinearLayoutManager(context);
    }

    @AScope
    @Provides
    Context providesContext(Context context) {
        return context;
    }

    @AScope
    @Provides
    CategoryListRecyclerAdapter provideCategoryListRecyclerAdapter() {
        return new CategoryListRecyclerAdapter(categoryList, categoryClickListener);
    }

    @AScope
    @Provides
    CategoryListRecyclerAdapter.CategoryClickListener providesCategoryClickListener() {
        return categoryClickListener;
    }

    @AScope
    @Provides
    List<Category> provideCategoryList() {
        return categoryList;
    }

}
