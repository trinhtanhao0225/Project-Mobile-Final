package com.example.banquanao.Api;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
public class ApiClient {
    private static Retrofit retrofit;
    private static final String BASE_URL = "http://10.0.2.2:8080/"; // Đổi thành URL server của bạn
    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            OkHttpClient client = new OkHttpClient.Builder()
                    .connectTimeout(30, TimeUnit.SECONDS) // Thời gian kết nối
                    .writeTimeout(30, TimeUnit.SECONDS)   // Thời gian ghi dữ liệu
                    .readTimeout(30, TimeUnit.SECONDS)    // Thời gian đọc dữ liệu
                    .build();
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();
        }
        return retrofit;
    }
}