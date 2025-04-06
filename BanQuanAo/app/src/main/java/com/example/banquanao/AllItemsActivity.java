package com.example.banquanao;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.banquanao.Adapter.DepotAdapter;
import com.example.banquanao.Model.Depot;
import com.example.banquanao.Api.DepotApi;
import com.example.banquanao.Api.ApiClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AllItemsActivity extends AppCompatActivity implements DepotAdapter.OnItemClickListener {
    private RecyclerView rvAllItems;
    private ImageView ivBack;
    private DepotAdapter adapter;
    private List<Depot> depotList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_item_admin);

        rvAllItems = findViewById(R.id.rvAllItems);
        ivBack = findViewById(R.id.ivBack);

        depotList = new ArrayList<>();
        adapter = new DepotAdapter(this, depotList, this); // Khởi tạo adapter với listener
        rvAllItems.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rvAllItems.setAdapter(adapter);

        ivBack.setOnClickListener(v -> finish());
        loadDepotItems();
    }

    private void loadDepotItems() {
        Log.d("AllItemsActivity", "Fetching depot items...");
        DepotApi apiService = ApiClient.getRetrofitInstance().create(DepotApi.class);
        Call<List<Depot>> call = apiService.getAllDepot();
        call.enqueue(new Callback<List<Depot>>() {
            @Override
            public void onResponse(@NonNull Call<List<Depot>> call, @NonNull Response<List<Depot>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Log.d("AllItemsActivity", "Depot items fetched: " + response.body().size());
                    depotList.clear(); // Xóa dữ liệu cũ
                    depotList.addAll(response.body()); // Thêm dữ liệu mới
                    adapter.notifyDataSetChanged(); // Cập nhật adapter
                } else {
                    Log.e("AllItemsActivity", "Response error: " + response.code());
                    Toast.makeText(AllItemsActivity.this, "Failed: " + response.code(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Depot>> call, @NonNull Throwable t) {
                Log.e("AllItemsActivity", "API call failed: " + t.getMessage());
                Toast.makeText(AllItemsActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onItemClick(Depot depot, int position) {
        // Khi nhấn vào item, gửi Depot tại vị trí position sang AddDepotActivity
        Log.d("AllItemsActivity", "Item clicked at position: " + position + ", Depot: " + depot.getName());
        Intent intent = new Intent(this, AddDepotActivity.class);
        intent.putExtra("depot_id", depot.getId()); // Gửi depotId
        // Nếu cần gửi toàn bộ Depot object, có thể serialize nó
        intent.putExtra("depot", depot); // Depot cần implements Serializable hoặc Parcelable
        startActivity(intent);
    }
}