package com.example.sandygorerazagad.interfaces;


import com.example.sandygorerazagad.model.RetroHours;

import retrofit2.http.GET;
import java.util.List;

import retrofit2.Call;

public interface Api {

    String BASE_URL = "https://gadsapi.herokuapp.com";

    @GET("/api/hours")
        // API's endpoints
    Call<List<RetroHours>> getUsersList();

// UserListResponse is POJO class to get the data from API, we use List<UserListResponse> in callback because the data in our API is starting from JSONArray

}
