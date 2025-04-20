package com.example.banquanao;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.banquanao.Fragment.CartFragment;
import com.example.banquanao.Fragment.HomeFragment;
import com.example.banquanao.Fragment.OrdersFragment;
import com.example.banquanao.Fragment.ProfileFragment;

public class HomeActivity extends AppCompatActivity {

    private Fragment currentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Khởi tạo các điều hướng
        initNavigation();

        // Xử lý intent từ activity khác gửi sang
        handleIntent(getIntent());
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent); // Cập nhật lại intent hiện tại
        handleIntent(intent); // Gọi xử lý intent mới
    }

    // Xử lý chuyển fragment theo dữ liệu trong Intent
    private void handleIntent(Intent intent) {
        String goToFragment = intent.getStringExtra("goToFragment");
        if ("home".equals(goToFragment)) {
            currentFragment = new HomeFragment();
            loadFragment(currentFragment);
        } else {
            // Mặc định load HomeFragment nếu không có gì được gửi
            if (currentFragment == null) {
                currentFragment = new HomeFragment();
                loadFragment(currentFragment);
            }
        }
    }

    // Tách phần ánh xạ navigation
    private void initNavigation() {
        LinearLayout navHome = findViewById(R.id.nav_home);
        LinearLayout navCart = findViewById(R.id.nav_cart);
        LinearLayout navOrders = findViewById(R.id.nav_orders);
        LinearLayout navProfile = findViewById(R.id.nav_profile);

        navHome.setOnClickListener(view -> switchFragment(new HomeFragment()));
        navCart.setOnClickListener(view -> switchFragment(new CartFragment()));
        navOrders.setOnClickListener(view -> switchFragment(new OrdersFragment()));
        navProfile.setOnClickListener(view -> switchFragment(new ProfileFragment()));
    }

    private void switchFragment(Fragment newFragment) {
        if (newFragment.getClass().equals(currentFragment != null ? currentFragment.getClass() : null)) {
            Log.d("HomeActivity", "Fragment already loaded: " + newFragment.getClass().getSimpleName());
            return;
        }
        currentFragment = newFragment;
        loadFragment(newFragment);
    }

    private void loadFragment(Fragment fragment) {
        if (fragment == null) {
            Log.e("HomeActivity", "Fragment is null!");
            return;
        }

        Fragment current = getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        if (current != null && current.getClass().equals(fragment.getClass())) {
            Log.d("HomeActivity", "Fragment already loaded: " + fragment.getClass().getSimpleName());
            return;
        }

        Log.d("HomeActivity", "Loading Fragment: " + fragment.getClass().getSimpleName());

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();

        getSupportFragmentManager().executePendingTransactions();
    }
}
