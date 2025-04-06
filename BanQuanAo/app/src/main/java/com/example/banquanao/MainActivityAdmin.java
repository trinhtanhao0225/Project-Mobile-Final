package com.example.banquanao;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivityAdmin extends AppCompatActivity {

    // Khai báo các view
    private TextView tvPendingOrder;
    private TextView tvCompletedOrder;
    private TextView tvWholeTimeEarning;
    private Button btnAddMenu;
    private Button btnAllItemMenu;
    private Button btnProfile;
    private Button btnCreateNewUser;
    private Button btnLogOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_admin); // Giả sử file XML tên là activity_main_admin.xml

        // Khởi tạo các view
        initializeViews();

        // Thiết lập các sự kiện click
        setupButtonListeners();

        // Cập nhật dữ liệu ban đầu (có thể thay bằng dữ liệu thực từ database)
        updateStats();
    }

    private void initializeViews() {
        // Liên kết các view với ID từ XML
        tvPendingOrder = findViewById(R.id.tvPendingOrder);
        tvCompletedOrder = findViewById(R.id.tvCompletedOrder);
        tvWholeTimeEarning = findViewById(R.id.tvWholeTimeEarning);
        btnAddMenu = findViewById(R.id.btnAddMenu);
        btnAllItemMenu = findViewById(R.id.btnAllItemMenu);
        btnProfile = findViewById(R.id.btnProfile);
        btnCreateNewUser = findViewById(R.id.btnCreateNewUser);
        btnLogOut = findViewById(R.id.btnLogOut);
    }

    private void setupButtonListeners() {
        // Xử lý sự kiện click cho nút Add Menu
        btnAddMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToMenuItems = new Intent(MainActivityAdmin.this, AddDepotActivity.class);
                startActivity(goToMenuItems);
            }
        });

        // Xử lý sự kiện click cho nút All Item Menu
        btnAllItemMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToMenuItems = new Intent(MainActivityAdmin.this, AllItemsActivity.class);
                startActivity(goToMenuItems);            }
        });

        // Xử lý sự kiện click cho nút Profile
        btnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: Thêm code để mở activity/fragment profile
            }
        });

        // Xử lý sự kiện click cho nút Create New User
        btnCreateNewUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: Thêm code để mở activity/fragment tạo user mới
            }
        });

        // Xử lý sự kiện click cho nút Log Out
        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: Thêm code xử lý logout (ví dụ: quay về màn login)
                finish();
            }
        });
    }

    private void updateStats() {
        // Đây là dữ liệu mẫu, bạn có thể thay bằng dữ liệu thực từ database
        tvPendingOrder.setText("30");
        tvCompletedOrder.setText("10");
        tvWholeTimeEarning.setText("100$");

        // Ví dụ nếu muốn lấy dữ liệu thực tế:
        /*
        DatabaseHelper db = new DatabaseHelper(this);
        tvPendingOrder.setText(String.valueOf(db.getPendingOrdersCount()));
        tvCompletedOrder.setText(String.valueOf(db.getCompletedOrdersCount()));
        tvWholeTimeEarning.setText(String.format("%.2f$", db.getTotalEarnings()));
        */
    }
}