package com.headytest.android.category_listing;

import com.headytest.android.enities.Result;
import com.headytest.android.retrofit_interface.HeadyAPI;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

/**
 * Created by scandid on 6/12/2018.
 */

public class CategoryPresenterImpl implements CategoryContract.CategoryPresenter {

    HeadyAPI headyAPI;

    @Inject
    public CategoryPresenterImpl(HeadyAPI headyAPI) {
        this.headyAPI = headyAPI;
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void getCategoryLiast() {


    }
}
