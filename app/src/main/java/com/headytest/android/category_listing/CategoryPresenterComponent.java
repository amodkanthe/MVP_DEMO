package com.headytest.android.category_listing;

import com.headytest.android.AScope;
import com.headytest.android.MainActivity;
import com.headytest.android.dagger_component.NetComponent;

import dagger.Component;
import dagger.Subcomponent;

/**
 * Created by scandid on 6/12/2018.
 */

@AScope
@Component(dependencies = NetComponent.class, modules = {CategoryContractModule.class})
public interface CategoryPresenterComponent {
    void inject(MainActivity activity);

}


