package com.headytest.android.retrofit_interface;

import com.headytest.android.enities.Result;


import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by amod on 5/15/2018.
 */

public interface HeadyAPI {

    @GET("json")
    Observable<Result> getProducts();
//            , @Query(value = "barcode", encoded = true) String barcode
//            , @Query(value = "qrcode", encoded = true) String qrcode);


}
