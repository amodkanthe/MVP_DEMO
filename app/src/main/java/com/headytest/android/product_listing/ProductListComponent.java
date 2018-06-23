package com.headytest.android.product_listing;

import com.headytest.android.AScope;
import com.headytest.android.category_listing.CategoryListFragment;
import com.headytest.android.category_listing.FragmentListModule;

import dagger.Component;

/**
 * Created by scandid on 6/21/2018.
 */
@AScope
@Component(modules = {ProductListModule.class})
public interface ProductListComponent {

    void inject(ProductListingFragment productListingFragment);
}
