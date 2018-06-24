package com.headytest.android;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.google.gson.Gson;
import com.headytest.android.category_listing.CategoryContract;
import com.headytest.android.category_listing.CategoryListFragment;
import com.headytest.android.category_listing.CategoryPresenterImpl;
import com.headytest.android.category_listing.CategoryContractModule;


//import com.headytest.android.category_listing.DaggerCategoryPresenterComponent;
import com.headytest.android.category_listing.DaggerCategoryPresenterComponent;
import com.headytest.android.enities.Category;
import com.headytest.android.enities.Product;
import com.headytest.android.enities.Ranking;
import com.headytest.android.enities.Result;
import com.headytest.android.enities.Variant;
import com.headytest.android.product_listing.ProductListActivity;
import com.headytest.android.retrofit_interface.HeadyAPI;


import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity implements CategoryContract.CategoryView, CategoryListFragment.OnFragmentInteractionListener {


    @Inject
    @AScope
    CategoryContract.CategoryPresenter categoryPresenter;

    @Inject
    @Singleton
    Retrofit retrofit;

    View viewError, viewCategories, viewProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewError = findViewById(R.id.llNoConnection);
        viewProgress = findViewById(R.id.progBar);
        viewCategories = findViewById(R.id.llCategories);
        DaggerCategoryPresenterComponent.builder()
                .netComponent(((App) getApplicationContext()).getNetComponent())
                .categoryContractModule(new CategoryContractModule())
                .build()
                .inject(this);
        categoryPresenter.setView(this);
        categoryPresenter.getCategoryLiast();
        findViewById(R.id.btnRetry).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                categoryPresenter.getCategoryLiast();
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        categoryPresenter.onStop();
    }

    @Override
    public void onPreAPIRequest() {
        viewError.setVisibility(View.GONE);
        viewProgress.setVisibility(View.VISIBLE);
        viewCategories.setVisibility(View.GONE);

    }

    @Override
    public void onAPISuccess() {
        viewError.setVisibility(View.GONE);
        viewProgress.setVisibility(View.GONE);
        viewCategories.setVisibility(View.VISIBLE);
    }

    @Override
    public void onAPIError() {
        viewError.setVisibility(View.VISIBLE);
        viewProgress.setVisibility(View.GONE);
        viewCategories.setVisibility(View.GONE);
    }

    @Override
    public void setCategoryList(Result result) {
        Gson gson = new Gson();
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragmentContainer,
                        CategoryListFragment.newInstance(gson.toJson(result.getCategories()).toString()
                                , gson.toJson(result.getRankings()).toString()), null)
                .commit();
    }

    @Override
    public void onFragmentInteraction(List<Category> categories, List<Ranking> rankings) {
        Gson gson = new Gson();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragmentContainer,
                        CategoryListFragment.newInstance(gson.toJson(categories).toString()
                                , gson.toJson(rankings).toString()), null)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onFragmentInteractionListProducts(List<Product> products, List<Ranking> rankings) {
        Gson gson = new Gson();

        startActivity(new Intent(this, ProductListActivity.class)
                .putExtra("param1", gson.toJson(products).toString())
                .putExtra("param2", gson.toJson(rankings).toString()));

    }
}
