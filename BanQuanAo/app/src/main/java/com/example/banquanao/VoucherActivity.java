package com.example.banquanao;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.banquanao.Adapter.VoucherAdapter;

public class VoucherActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private VoucherAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voucher);

        recyclerView = findViewById(R.id.recyclerViewVoucher);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Gán adapter và xử lý khi chọn voucher
        adapter = new VoucherAdapter(this, new VoucherAdapter.OnVoucherUseListener() {
            @Override
            public void onVoucherUse(String voucherName) {
                Intent resultIntent = new Intent();
                resultIntent.putExtra("voucher_name", voucherName);
                setResult(RESULT_OK, resultIntent);
                finish();
            }
        });

        recyclerView.setAdapter(adapter);
    }
}
