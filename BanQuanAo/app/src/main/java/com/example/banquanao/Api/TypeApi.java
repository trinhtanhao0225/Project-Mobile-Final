package com.example.banquanao.Api;

import com.example.banquanao.Model.Depot;
import com.example.banquanao.Model.Type;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface TypeApi {
    @GET("/getAllType")
    Call<List<Type>> getAllType();
}
