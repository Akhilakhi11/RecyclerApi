package com.example.recyclerapi;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Interface_Api {

    @GET("products")
    Call<ProductListresponse>getAllData();
}
