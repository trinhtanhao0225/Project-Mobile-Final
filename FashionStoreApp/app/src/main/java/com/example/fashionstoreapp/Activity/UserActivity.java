package com.example.fashionstoreapp.Activity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
<<<<<<< HEAD
=======
import android.widget.Toast;
>>>>>>> c52494fe6a3d80d0a4fe61fcfaed901e57911eec

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.bumptech.glide.Glide;
import com.example.fashionstoreapp.Model.Order;
import com.example.fashionstoreapp.Model.User;
import com.example.fashionstoreapp.R;
import com.example.fashionstoreapp.Retrofit.UserAPI;
import com.example.fashionstoreapp.Somethings.ObjectSharedPreferences;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.text.NumberFormat;
import java.util.Locale;
<<<<<<< HEAD
=======
import java.util.Objects;
>>>>>>> c52494fe6a3d80d0a4fe61fcfaed901e57911eec

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserActivity extends AppCompatActivity {
<<<<<<< HEAD
    ImageView ivHome, ivUser, ivCart, ivHistory,ivAvatar;

    Button btnEditProfile, btnLogout;

    TextView tvFullName, tvId, tvTotalOrder, tvTotalPrice, tvChangePassword, tvEmail, tvPhone, tvAddress;

    User user;

    GoogleSignInOptions googleSignInOptions;
    GoogleSignInClient googleSignInClient;
=======
    ImageView ivHome, ivUser, ivCart, ivHistory, ivAvatar;
    Button btnEditProfile, btnLogout;
    TextView tvFullName, tvId, tvTotalOrder, tvTotalPrice, tvChangePassword, tvEmail, tvPhone, tvAddress;
    User user;
    GoogleSignInOptions googleSignInOptions;
    GoogleSignInClient googleSignInClient;

>>>>>>> c52494fe6a3d80d0a4fe61fcfaed901e57911eec
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        AnhXa();
        googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
<<<<<<< HEAD
        googleSignInClient = GoogleSignIn.getClient(this,googleSignInOptions);
=======
        googleSignInClient = GoogleSignIn.getClient(this, googleSignInOptions);
>>>>>>> c52494fe6a3d80d0a4fe61fcfaed901e57911eec
        appBarClick();
        LoadData();
        btnLogoutClick();
        tvChangePasswordClick();
        btnEditProfileClick();
    }

    private void btnEditProfileClick() {
        btnEditProfile.setOnClickListener(v -> {
            startActivity(new Intent(UserActivity.this, EditProfileActivity.class));
        });
    }

    private void tvChangePasswordClick() {
        tvChangePassword.setOnClickListener(v -> {
            Dialog dialog = new Dialog(UserActivity.this);
            dialog.setContentView(R.layout.dialog_change_password);
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            dialog.getWindow().getAttributes().windowAnimations = R.style.animation;
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.getWindow().setGravity(Gravity.BOTTOM);
            dialog.show();
<<<<<<< HEAD
            //Anh xa dialog
=======
            // Anh xa dialog
>>>>>>> c52494fe6a3d80d0a4fe61fcfaed901e57911eec
            ConstraintLayout clChangePassword = dialog.findViewById(R.id.clChangePassword);
            ConstraintLayout clChangePasswordSuccess = dialog.findViewById(R.id.clChangePasswordSuccess);
            EditText etOldPassword = dialog.findViewById(R.id.etOldPassword);
            EditText etNewPassword = dialog.findViewById(R.id.etNewPassword);
            EditText etReNewPassword = dialog.findViewById(R.id.etReNewPassword);
            TextView tvErrorChangePassword = dialog.findViewById(R.id.tvErrorChangePassword);
            Button btnChangePassword = dialog.findViewById(R.id.btnChangePassword);
            Button btnOK = dialog.findViewById(R.id.btnOk);
<<<<<<< HEAD
            //====
            btnChangePassword.setOnClickListener(v1 -> {
                String password = etOldPassword.getText().toString();
                String newPassword = etNewPassword.getText().toString();
                String reNewPassword =etReNewPassword.getText().toString();
                user = ObjectSharedPreferences.getSavedObjectFromPreference(UserActivity.this, "User", "MODE_PRIVATE", User.class);
                if(password.equals(user.getPassword())){
                    if(newPassword.equals(reNewPassword)){
=======
            // ====
            btnChangePassword.setOnClickListener(v1 -> {
                String password = etOldPassword.getText().toString();
                String newPassword = etNewPassword.getText().toString();
                String reNewPassword = etReNewPassword.getText().toString();
                user = ObjectSharedPreferences.getSavedObjectFromPreference(UserActivity.this, "User", "MODE_PRIVATE", User.class);
                if (user == null) {
                    tvErrorChangePassword.setText("Vui lòng đăng nhập lại");
                    return;
                }
                if (password.equals(user.getPassword())) {
                    if (newPassword.equals(reNewPassword)) {
>>>>>>> c52494fe6a3d80d0a4fe61fcfaed901e57911eec
                        UserAPI.userApi.changePassword(user.getId(), newPassword).enqueue(new Callback<String>() {
                            @Override
                            public void onResponse(Call<String> call, Response<String> response) {
                                String pass = response.body();
<<<<<<< HEAD
                                if(pass!= null){
=======
                                if (pass != null) {
>>>>>>> c52494fe6a3d80d0a4fe61fcfaed901e57911eec
                                    clChangePassword.setVisibility(View.GONE);
                                    clChangePasswordSuccess.setVisibility(View.VISIBLE);
                                    user.setPassword(pass);
                                    ObjectSharedPreferences.saveObjectToSharedPreference(UserActivity.this, "User", "MODE_PRIVATE", user);
<<<<<<< HEAD
                                    btnOK.setOnClickListener(v2 -> {
                                        dialog.dismiss();
                                    });
                                }

                            }
                            @Override
                            public void onFailure(Call<String> call, Throwable t) {

                            }
                        });
                    }else {
                        tvErrorChangePassword.setText("New password and confirm password do not match!");
                    }
                } else {
                    tvErrorChangePassword.setText("Your password is not correct");
=======
                                    btnOK.setOnClickListener(v2 -> dialog.dismiss());
                                } else {
                                    tvErrorChangePassword.setText("Lỗi đổi mật khẩu, vui lòng thử lại");
                                }
                            }

                            @Override
                            public void onFailure(Call<String> call, Throwable t) {
                                tvErrorChangePassword.setText("Lỗi kết nối: " + t.getMessage());
                            }
                        });
                    } else {
                        tvErrorChangePassword.setText("Mật khẩu mới và xác nhận mật khẩu không khớp!");
                    }
                } else {
                    tvErrorChangePassword.setText("Mật khẩu hiện tại không đúng");
>>>>>>> c52494fe6a3d80d0a4fe61fcfaed901e57911eec
                }
            });
        });
    }

    private void btnLogoutClick() {
        btnLogout.setOnClickListener(v -> {
            user = ObjectSharedPreferences.getSavedObjectFromPreference(UserActivity.this, "User", "MODE_PRIVATE", User.class);
<<<<<<< HEAD
            if (user.getPassword()==null){
                googleSignInClient.signOut().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(Task<Void> task) {
                        finish();
                        ObjectSharedPreferences.saveObjectToSharedPreference(UserActivity.this, "User", "MODE_PRIVATE", null);
                        startActivity(new Intent(UserActivity.this,LoginActivity.class));
                    }
                });
            }
            else{
                ObjectSharedPreferences.saveObjectToSharedPreference(UserActivity.this, "User", "MODE_PRIVATE", null);
                startActivity(new Intent(UserActivity.this, LoginActivity.class));
            }

=======
            if (user == null) {
                startActivity(new Intent(UserActivity.this, LoginActivity.class));
                finish();
                return;
            }
            if (Objects.equals(user.getLogin_Type(), "google")) {
                googleSignInClient.signOut().addOnCompleteListener(task -> {
                    ObjectSharedPreferences.saveObjectToSharedPreference(UserActivity.this, "User", "MODE_PRIVATE", null);
                    startActivity(new Intent(UserActivity.this, LoginActivity.class));
                    finish();
                });
            } else {
                ObjectSharedPreferences.saveObjectToSharedPreference(UserActivity.this, "User", "MODE_PRIVATE", null);
                startActivity(new Intent(UserActivity.this, LoginActivity.class));
                finish();
            }
>>>>>>> c52494fe6a3d80d0a4fe61fcfaed901e57911eec
        });
    }

    private void LoadData() {
        user = ObjectSharedPreferences.getSavedObjectFromPreference(UserActivity.this, "User", "MODE_PRIVATE", User.class);
<<<<<<< HEAD
        UserAPI.userApi.Login(user.getId(), user.getPassword()).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                user = response.body();
                ObjectSharedPreferences.saveObjectToSharedPreference(UserActivity.this, "User", "MODE_PRIVATE", user);
                Glide.with(getApplicationContext()).load(user.getAvatar()).into(ivAvatar);
                tvFullName.setText(user.getUser_Name());
                tvId.setText(user.getId());
                tvTotalOrder.setText(user.getOrder().size()+"");
                int totalPrice = 0;
                for (Order o : user.getOrder()){
                    totalPrice += o.getTotal();
                }
                Locale localeEN = new Locale("en", "EN");
                NumberFormat en = NumberFormat.getInstance(localeEN);
                tvTotalPrice.setText(en.format(totalPrice));
                tvPhone.setText(user.getPhone_Number());
                tvAddress.setText(user.getAddress());
                tvEmail.setText(user.getEmail());
                if(user.getLogin_Type().equals("google")){
                    tvChangePassword.setVisibility(View.GONE);
                }
            }
            @Override
            public void onFailure(Call<User> call, Throwable t) {

=======
        if (user == null || user.getId() == null || user.getPassword() == null) {
            Toast.makeText(UserActivity.this, "Vui lòng đăng nhập lại", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(UserActivity.this, LoginActivity.class));
            finish();
            return;
        }
        UserAPI.userApi.Login(user.getId(), user.getPassword()).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful() && response.body() != null) {
                    user = response.body();
                    ObjectSharedPreferences.saveObjectToSharedPreference(UserActivity.this, "User", "MODE_PRIVATE", user);


                    tvFullName.setText(user.getUser_Name() != null ? user.getUser_Name() : "N/A");
                    tvId.setText(user.getId() != null ? user.getId() : "N/A");
                    tvTotalOrder.setText(user.getOrder() != null ? String.valueOf(user.getOrder().size()) : "0");

                    int totalPrice = 0;
                    if (user.getOrder() != null) {
                        for (Order o : user.getOrder()) {
                            totalPrice += o.getTotal();
                        }
                    }
                    Locale localeEN = new Locale("en", "EN");
                    NumberFormat en = NumberFormat.getInstance(localeEN);
                    tvTotalPrice.setText(en.format(totalPrice));

                    tvPhone.setText(user.getPhone_Number() != null ? user.getPhone_Number() : "N/A");
                    tvAddress.setText(user.getAddress() != null ? user.getAddress() : "N/A");
                    tvEmail.setText(user.getEmail() != null ? user.getEmail() : "N/A");

                    // Kiểm tra login_type
                    if (Objects.equals(user.getLogin_Type(), "google")) {
                        tvChangePassword.setVisibility(View.GONE);
                    } else {
                        tvChangePassword.setVisibility(View.VISIBLE);
                    }
                } else {
                    Toast.makeText(UserActivity.this, "Không thể tải thông tin người dùng", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(UserActivity.this, "Lỗi kết nối: " + t.getMessage(), Toast.LENGTH_SHORT).show();
>>>>>>> c52494fe6a3d80d0a4fe61fcfaed901e57911eec
            }
        });
    }

    private void appBarClick() {
<<<<<<< HEAD
        ivHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserActivity.this, MainActivity.class));
                finish();
            }
        });
        ivUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserActivity.this, UserActivity.class));
                finish();
            }
        });
        ivCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserActivity.this, CartActivity.class));
                finish();
            }
        });

        ivHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserActivity.this, OrderActivity.class));
                finish();
            }
=======
        ivHome.setOnClickListener(v -> {
            startActivity(new Intent(UserActivity.this, MainActivity.class));
            finish();
        });
        ivUser.setOnClickListener(v -> {
            startActivity(new Intent(UserActivity.this, UserActivity.class));
            finish();
        });
        ivCart.setOnClickListener(v -> {
            startActivity(new Intent(UserActivity.this, CartActivity.class));
            finish();
        });
        ivHistory.setOnClickListener(v -> {
            startActivity(new Intent(UserActivity.this, OrderActivity.class));
            finish();
>>>>>>> c52494fe6a3d80d0a4fe61fcfaed901e57911eec
        });
    }

    private void AnhXa() {
<<<<<<< HEAD
//        tvFullName, tvId, tvTotalOrder, tvTotalPrice, tvChangePassword, tvEmail, tvPhone, tvAddress;
//        Button btnEditProfile, btnLogout;
//
//        ImageView ivAvatar;

=======
>>>>>>> c52494fe6a3d80d0a4fe61fcfaed901e57911eec
        ivHome = findViewById(R.id.ivHome);
        ivUser = findViewById(R.id.ivUser);
        ivCart = findViewById(R.id.ivCart);
        ivHistory = findViewById(R.id.ivHistory);
<<<<<<< HEAD

=======
>>>>>>> c52494fe6a3d80d0a4fe61fcfaed901e57911eec
        tvFullName = findViewById(R.id.tvFullName);
        tvId = findViewById(R.id.tvId);
        tvTotalOrder = findViewById(R.id.tvTotalOrder);
        tvTotalPrice = findViewById(R.id.tvTotalPrice);
        tvChangePassword = findViewById(R.id.tvChangePassword);
        tvEmail = findViewById(R.id.tvEmail);
        tvPhone = findViewById(R.id.tvPhone);
        tvAddress = findViewById(R.id.tvAddress);
        btnEditProfile = findViewById(R.id.btnEditProfile);
        btnLogout = findViewById(R.id.btnLogout);
        ivAvatar = findViewById(R.id.ivAvatar);
<<<<<<< HEAD

    }
}
=======
    }
}
>>>>>>> c52494fe6a3d80d0a4fe61fcfaed901e57911eec
