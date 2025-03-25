package com.example.banquanao;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class OtpActivity extends AppCompatActivity {
    private EditText otp1, otp2, otp3, otp4, otp5, otp6;
    private Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);

        otp1 = findViewById(R.id.otp1);
        otp2 = findViewById(R.id.otp2);
        otp3 = findViewById(R.id.otp3);
        otp4 = findViewById(R.id.otp4);
        otp5 = findViewById(R.id.otp5);
        otp6 = findViewById(R.id.otp6);
        btnSubmit = findViewById(R.id.btnSubmit);

        setupOtpAutoMove();

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String code = otp1.getText().toString() + otp2.getText().toString() +
                        otp3.getText().toString() + otp4.getText().toString() +
                        otp5.getText().toString() + otp6.getText().toString();
//Khi gắn API coi lại chỗ này
                if (code.length() == 0) {
                    Toast.makeText(OtpActivity.this, "Xác nhận thành công!", Toast.LENGTH_SHORT).show();

                    // Quay lại LoginActivity
                    Intent intent = new Intent(OtpActivity.this, LoginActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK); // Xóa các activity trước đó
                    startActivity(intent);
                    finish(); // Đóng OtpActivity
                } else {
                    Toast.makeText(OtpActivity.this, "Vui lòng nhập đầy đủ mã OTP!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void setupOtpAutoMove() {
        EditText[] otpFields = {otp1, otp2, otp3, otp4, otp5, otp6};

        for (int i = 0; i < otpFields.length; i++) {
            final int nextIndex = i + 1;
            final int prevIndex = i - 1;

            otpFields[i].addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if (count == 1 && nextIndex < otpFields.length) {
                        otpFields[nextIndex].requestFocus();
                    } else if (count == 0 && prevIndex >= 0) {
                        otpFields[prevIndex].requestFocus();
                    }
                }

                @Override
                public void afterTextChanged(Editable s) {}
            });
        }
    }
}
