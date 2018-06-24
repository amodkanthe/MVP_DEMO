package com.headytest.android.category_listing;

import com.headytest.android.AScope;
import com.headytest.android.retrofit_interface.HeadyAPI;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by amod on 6/12/2018.
 */
@Module
public class CategoryContractModule {


    public CategoryContractModule() {
    }


    @Provides
    @AScope
    CategoryContract.CategoryPresenter providesCategoryPresenter(Retrofit retrofit) {
        return new CategoryPresenterImpl(retrofit);
    }


}
