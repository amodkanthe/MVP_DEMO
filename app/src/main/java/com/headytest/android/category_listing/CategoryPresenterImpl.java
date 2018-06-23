package com.headytest.android.category_listing;

import com.headytest.android.enities.Category;
import com.headytest.android.enities.Result;
import com.headytest.android.retrofit_interface.HeadyAPI;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

/**
 * Created by scandid on 6/12/2018.
 */

public class CategoryPresenterImpl implements CategoryContract.CategoryPresenter {
    final CompositeDisposable compositeDisposable = new CompositeDisposable();
    Retrofit retrofit;
    CategoryContract.CategoryView categoryView;

    @Inject
    public CategoryPresenterImpl(Retrofit retrofit) {
        this.retrofit = retrofit;
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void getCategoryLiast() {
        categoryView.onPreAPIRequest();
        compositeDisposable.add(retrofit.create(HeadyAPI.class).getProducts()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<Result>() {
                    @Override
                    public void onNext(Result result) {
                        categoryView.onAPISuccess();
                        //Sort categories based on number of sub-categories
                        sortCategories(result);

                        //Arrange category sub-category tree
                        List<Category> subCategories = new ArrayList<>();
                        for (Category category : result.getCategories()) {
                            for (Integer id : category.getChildCategories()) {
                                Category subcCategory = getCategoryByID(id, result.getCategories());
                                if (subcCategory != null) {
                                    subCategories.add(subcCategory);
                                    category.getChildCategoriesList().add(subcCategory);
                                }
                            }
                        }
                        result.getCategories().removeAll(subCategories);
                        categoryView.setCategoryList(result);



                    }

                    @Override
                    public void onError(Throwable e) {
                        categoryView.onAPIError();
                    }

                    @Override
                    public void onComplete() {
                    }
                }));

    }

    private void sortCategories(Result result) {
        Collections.sort(result.getCategories(), new Comparator<Category>() {
            @Override
            public int compare(Category category, Category t1) {
                return category.getChildCategories().size() > t1.getChildCategories().size() ? -1 : 1;
            }
        });
    }

    Category getCategoryByID(int id, List<Category> categories) {
        Category category = null;
        for (Category category1 : categories) {
            if (category1.getId() == id) {
                category = category1;
                break;
            }
        }
        return category;
    }

    @Override
    public void setView(CategoryContract.CategoryView categoryView) {
        this.categoryView = categoryView;
    }
}
