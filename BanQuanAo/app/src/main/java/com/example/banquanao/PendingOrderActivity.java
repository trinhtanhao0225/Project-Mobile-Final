package com.example.banquanao;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import com.example.banquanao.Adapter.OrderAdapter;
import com.example.banquanao.Api.ApiClient;
import com.example.banquanao.Api.OrderApi;
import com.example.banquanao.Model.SaleHistory;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PendingOrderActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ImageView ivBack;
    private OrderApi orderApi;
    private OrderAdapter adapter;
    private static final String TAG = "PendingOrderActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pending_orders);

        recyclerView = findViewById(R.id.recyclerViewPendingOrders);
        ivBack = findViewById(R.id.ivBack);

        // Set up the back button
        ivBack.setOnClickListener(v -> finish());

        // Initialize Retrofit and API
        Retrofit retrofit = ApiClient.getRetrofitInstance();
        orderApi = retrofit.create(OrderApi.class);

        // Set up the adapter with an empty list initially
        adapter = new OrderAdapter(new ArrayList<>(), new OrderAdapter.OnOrderActionListener() {
            @Override
            public void onAcceptClick(SaleHistory order) {
                handleAcceptOrder(order);
            }

            @Override
            public void onDispatchClick(SaleHistory order) {
                handleDispatchOrder(order);
            }
        });
        recyclerView.setAdapter(adapter);

        // Fetch pending orders
        fetchPendingOrders();
    }

    private void fetchPendingOrders() {
        Call<List<SaleHistory>> call = orderApi.getStatusOrder(1);
        call.enqueue(new Callback<List<SaleHistory>>() {
            @Override
            public void onResponse(@NonNull Call<List<SaleHistory>> call, @NonNull Response<List<SaleHistory>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<SaleHistory> orders = response.body();
                    Log.d(TAG, "Fetched " + orders.size() + " pending orders");
                    adapter.updateOrders(orders);
                } else {
                    Log.e(TAG, "Failed to fetch orders: " + response.message());
                    Toast.makeText(
                            PendingOrderActivity.this,
                            "Failed to load orders: " + response.message(),
                            Toast.LENGTH_SHORT
                    ).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<SaleHistory>> call, @NonNull Throwable t) {
                Log.e(TAG, "Error fetching orders: " + t.getMessage());
                Toast.makeText(
                        PendingOrderActivity.this,
                        "Error connecting to server: " + t.getMessage(),
                        Toast.LENGTH_SHORT
                ).show();
            }
        });
    }

    private void handleAcceptOrder(SaleHistory order) {
        Call<String> call = orderApi.changeStatus(order.getSaleId(), 2); // 2 là id status ACCEPTED chẳng hạn
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(@NonNull Call<String> call, @NonNull Response<String> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Toast.makeText(
                            PendingOrderActivity.this,
                            response.body(), // Thông báo từ server
                            Toast.LENGTH_SHORT
                    ).show();
                    fetchPendingOrders(); // Cập nhật lại danh sách
                } else {
                    Toast.makeText(
                            PendingOrderActivity.this,
                            "Failed to accept order: " + response.message(),
                            Toast.LENGTH_SHORT
                    ).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<String> call, @NonNull Throwable t) {
                Toast.makeText(
                        PendingOrderActivity.this,
                        "Error: " + t.getMessage(),
                        Toast.LENGTH_SHORT
                ).show();
            }
        });
    }

    private void handleDispatchOrder(SaleHistory order) {
        Call<String> call = orderApi.changeStatus(order.getSaleId(), 3); // 3 là id status DISPATCHED chẳng hạn
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(@NonNull Call<String> call, @NonNull Response<String> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Toast.makeText(
                            PendingOrderActivity.this,
                            response.body(),
                            Toast.LENGTH_SHORT
                    ).show();
                    fetchPendingOrders();
                } else {
                    Toast.makeText(
                            PendingOrderActivity.this,
                            "Failed to dispatch order: " + response.message(),
                            Toast.LENGTH_SHORT
                    ).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<String> call, @NonNull Throwable t) {
                Toast.makeText(
                        PendingOrderActivity.this,
                        "Error: " + t.getMessage(),
                        Toast.LENGTH_SHORT
                ).show();
            }
        });
    }
}