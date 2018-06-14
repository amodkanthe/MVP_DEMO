package com.headytest.android;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.headytest.android.app_constants.Constants;

import com.headytest.android.dagger_component.DaggerNetComponent;
import com.headytest.android.dagger_component.NetComponent;
import com.headytest.android.dagger_modules.ApplicationModule;
import com.headytest.android.dagger_modules.NetworkModule;


/**
 * Created by scandid on 9/26/2017.
 */

public class App extends Application {

    private NetComponent mNetComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mNetComponent = DaggerNetComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .networkModule(new NetworkModule(Constants.BASE_URL))
                .build();

    }


    public NetComponent getNetComponent() {
        return mNetComponent;
    }

}
