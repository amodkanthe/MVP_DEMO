package com.headytest.android.category_listing;

import com.headytest.android.AScope;
import com.headytest.android.dagger_component.NetComponent;

import dagger.Component;

/**
 * Created by scandid on 6/21/2018.
 */
@AScope
@Component(modules = {FragmentListModule.class})
public interface FragmentListComponent {

    void inject(CategoryListFragment categoryListFragment);
}
