package com.example.banquanao;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.banquanao.Api.ApiClient;
import com.example.banquanao.Api.UserApi;


import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class OtpActivity extends AppCompatActivity {
    private EditText otp1, otp2, otp3, otp4, otp5, otp6;
    private Button btnSubmit;
    private TextView tvResendOtp;
    private UserApi userApi;
    private String email; // Email nhận OTP

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);

        // Nhận email từ Intent trước đó (nếu có)
        email = getIntent().getStringExtra("email");

        otp1 = findViewById(R.id.otp1);
        otp2 = findViewById(R.id.otp2);
        otp3 = findViewById(R.id.otp3);
        otp4 = findViewById(R.id.otp4);
        otp5 = findViewById(R.id.otp5);
        otp6 = findViewById(R.id.otp6);
        btnSubmit = findViewById(R.id.btnSubmit);
        tvResendOtp = findViewById(R.id.tvResendOtp);  // Khai báo TextView Resend OTP

        setupOtpAutoMove();

        // Khởi tạo API
        Retrofit retrofit = ApiClient.getRetrofitInstance();
        userApi = retrofit.create(UserApi.class);

        btnSubmit.setOnClickListener(v -> verifyOtp());

        // Xử lý sự kiện click gửi lại OTP
        tvResendOtp.setOnClickListener(v -> resendOtp());
    }

    private void verifyOtp() {
        String otp = otp1.getText().toString() + otp2.getText().toString() +
                otp3.getText().toString() + otp4.getText().toString() +
                otp5.getText().toString() + otp6.getText().toString();

        if (otp.length() != 6) {
            Toast.makeText(OtpActivity.this, "Vui lòng nhập đầy đủ mã OTP!", Toast.LENGTH_SHORT).show();
            return;
        }

        Call<Map<String, String>> call = userApi.verifyOtp(email, otp);
        call.enqueue(new Callback<Map<String, String>>() {
            @Override
            public void onResponse(@NonNull Call<Map<String, String>> call, @NonNull Response<Map<String, String>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    String message = response.body().get("message");
                    Log.d("OtpActivity", "Xác thực OTP thành công: " + message);
                    Toast.makeText(OtpActivity.this, message, Toast.LENGTH_SHORT).show();

                    // Chuyển sang màn hình đăng nhập
                    Intent intent = new Intent(OtpActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Log.d("OtpActivity", "Xác thực OTP thất bại: " + response.message());
                    Toast.makeText(OtpActivity.this, "Mã OTP không hợp lệ!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<Map<String, String>> call, @NonNull Throwable t) {
                Log.e("OtpActivity", "Lỗi kết nối API: " + t.getMessage());
                Toast.makeText(OtpActivity.this, "Lỗi kết nối, vui lòng thử lại!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void resendOtp() {
        Call<Map<String, String>> call = userApi.regenerateOtp(email);
        call.enqueue(new Callback<Map<String, String>>() {
            @Override
            public void onResponse(@NonNull Call<Map<String, String>> call, @NonNull Response<Map<String, String>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    String message = response.body().get("message");
                    Log.d("OtpActivity", "Gửi lại OTP thành công: " + message);
                    Toast.makeText(OtpActivity.this, message, Toast.LENGTH_SHORT).show();
                } else {
                    Log.d("OtpActivity", "Gửi lại OTP thất bại: " + response.message());
                    Toast.makeText(OtpActivity.this, "Không thể gửi lại OTP!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<Map<String, String>> call, @NonNull Throwable t) {
                Log.e("OtpActivity", "Lỗi kết nối API: " + t.getMessage());
                Toast.makeText(OtpActivity.this, "Lỗi kết nối, vui lòng thử lại!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setupOtpAutoMove() {
        EditText[] otpFields = {otp1, otp2, otp3, otp4, otp5, otp6};

        for (int i = 0; i < otpFields.length; i++) {
            final int index = i; // Biến final để sử dụng trong lambda
            final int nextIndex = index + 1;
            final int prevIndex = index - 1;

            otpFields[index].setOnKeyListener((v, keyCode, event) -> {
                if (otpFields[index].getText().length() == 1 && nextIndex < otpFields.length) {
                    otpFields[nextIndex].requestFocus();
                } else if (otpFields[index].getText().length() == 0 && prevIndex >= 0) {
                    otpFields[prevIndex].requestFocus();
                }
                return false;
            });
        }
    }
}

