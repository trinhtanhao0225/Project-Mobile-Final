package com.example.banquanao.Api;

import okhttp3.Credentials;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static Retrofit retrofit = null;
    private static final String BASE_URL = "http://10.0.2.2:8080/"; // Thay bằng URL API của bạn

    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            // Tạo OkHttpClient với Basic Auth Interceptor
            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
            httpClient.addInterceptor(new BasicAuthInterceptor("admin", "123456")); // Thay bằng username và password thực tế

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient.build())
                    .build();
        }
        return retrofit;
    }
}

// Class Interceptor cho Basic Authentication
class BasicAuthInterceptor implements Interceptor {
    private String credentials;

    public BasicAuthInterceptor(String username, String password) {
        this.credentials = Credentials.basic(username, password);
    }

    @Override
    public okhttp3.Response intercept(Chain chain) throws java.io.IOException {
        Request originalRequest = chain.request();
        Request authenticatedRequest = originalRequest.newBuilder()
                .header("Authorization", credentials)
                .build();
        return chain.proceed(authenticatedRequest);
    }
}