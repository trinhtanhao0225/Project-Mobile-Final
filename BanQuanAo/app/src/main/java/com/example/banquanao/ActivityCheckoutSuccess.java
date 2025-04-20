package com.example.banquanao;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ActivityCheckoutSuccess extends AppCompatActivity {

    Button btnContinue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout_success);

        btnContinue = findViewById(R.id.btnContinueShopping);

        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Quay lại MainActivity và chuyển về FragmentHome
                Intent intent = new Intent(ActivityCheckoutSuccess.this, HomeActivity.class);
                intent.putExtra("goToFragment", "home");
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                finish(); // đóng màn hình hiện tại
            }

        });
    }
}

