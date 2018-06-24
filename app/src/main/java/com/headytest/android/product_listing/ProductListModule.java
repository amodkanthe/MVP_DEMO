package com.headytest.android.product_listing;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;

import com.headytest.android.AScope;
import com.headytest.android.enities.Product;

import java.util.List;

import dagger.Module;
import dagger.Provides;

/**
 * Created by amod on 6/24/2018.
 */
@Module
public class ProductListModule {

    Context context;
    List<Product> products;


    public ProductListModule(Context context,
                             List<Product> products) {
        this.context = context;
        this.products = products;
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
    ProductsRecyclerAdapter provideCategoryListRecyclerAdapter() {
        return new ProductsRecyclerAdapter(products);
    }


    @AScope
    @Provides
    List<Product> provideProducts() {
        return products;
    }

}
