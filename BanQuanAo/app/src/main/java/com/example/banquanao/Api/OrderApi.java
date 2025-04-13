package com.example.banquanao.Api;

import com.example.banquanao.Model.Depot;
import com.example.banquanao.Model.SaleHistory;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface OrderApi {
    @GET("/get-status-history-detail")
    Call<List<SaleHistory>> getStatusOrder(@Query("status") int status);

    @POST("/change-status")
    Call <String> changeStatus(@Query("saleHistoryId") int id,@Query("newStatusId") int status);
}
