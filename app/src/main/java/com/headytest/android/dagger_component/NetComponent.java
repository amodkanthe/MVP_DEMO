package com.headytest.android.dagger_component;


import com.headytest.android.MainActivity;
import com.headytest.android.dagger_modules.ApplicationModule;
import com.headytest.android.dagger_modules.NetworkModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by scandid on 9/26/2017.
 */
@Singleton
@Component(modules = {ApplicationModule.class, NetworkModule.class})
public interface NetComponent {
    void inject(MainActivity activity);


}
