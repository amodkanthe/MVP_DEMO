package com.headytest.android.category_listing;

import com.headytest.android.AScope;
import com.headytest.android.retrofit_interface.HeadyAPI;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by scandid on 6/12/2018.
 */
@Module
public class CategoryContractModule {

    HeadyAPI headyAPI;

    public CategoryContractModule(HeadyAPI headyAPI) {
        this.headyAPI = headyAPI;
    }


    @Provides
    @AScope
    CategoryContract.CategoryPresenter providesCategoryPresenter(CategoryPresenterImpl categoryPresenter) {
        return (CategoryContract.CategoryPresenter) categoryPresenter;
    }

    @Provides
    @AScope
    HeadyAPI providesHeadyAPI() {
        return headyAPI;
    }
}
