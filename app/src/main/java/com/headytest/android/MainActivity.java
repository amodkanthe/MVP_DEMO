package com.headytest.android;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.headytest.android.category_listing.CategoryContract;
import com.headytest.android.category_listing.CategoryPresenterImpl;
import com.headytest.android.category_listing.CategoryContractModule;


//import com.headytest.android.category_listing.DaggerCategoryPresenterComponent;
import com.headytest.android.category_listing.DaggerCategoryPresenterComponent;
import com.headytest.android.enities.Result;
import com.headytest.android.retrofit_interface.HeadyAPI;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity implements CategoryContract.CategoryView {


    @Inject
    @AScope
    CategoryContract.CategoryPresenter categoryPresenter;

    @Inject
    @Singleton
    Retrofit retrofit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DaggerCategoryPresenterComponent.builder()
                .netComponent(((App) getApplicationContext()).getNetComponent())
                .categoryContractModule(new CategoryContractModule(retrofit.create(HeadyAPI.class)))
                .build()
                .inject(this);
//        categoryPresenter.setCategoryView(this);
//        categoryPresenter.setRetrofit(retrofit);
        categoryPresenter.getCategoryLiast();

        // ((App) getApplication()).getNetComponent().n.inject(this);

//        ((App)getApplication()).getNetComponent()
//                .(new CategoryContractModule(this, retrofit.create(HeadyAPI.class)))
//                .inject(this /*fragment*/);


    }


    @Override
    public void onPreAPIRequest() {

    }

    @Override
    public void onAPISuccess() {

    }

    @Override
    public void onAPIError() {

    }

    @Override
    public void setCategoryList(Result result) {

    }
}
