package com.example.banquanao.Api;

import com.example.banquanao.Model.Depot;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface DepotApi {
    @GET("/getAllDepot")
    Call<List<Depot>> getAllDepot();
}
