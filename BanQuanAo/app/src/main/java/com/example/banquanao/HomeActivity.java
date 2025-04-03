package com.example.banquanao;

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

    private Fragment currentFragment; // Biến để kiểm tra Fragment hiện tại

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Ánh xạ các LinearLayout điều hướng
        LinearLayout navHome = findViewById(R.id.nav_home);
        LinearLayout navCart = findViewById(R.id.nav_cart);
        LinearLayout navOrders = findViewById(R.id.nav_orders);
        LinearLayout navProfile = findViewById(R.id.nav_profile);

        // Mặc định load HomeFragment khi mở app
        if (savedInstanceState == null) {
            currentFragment = new HomeFragment();
            loadFragment(currentFragment);
        }

        // Xử lý sự kiện Click cho LinearLayout
        navHome.setOnClickListener(view -> switchFragment(new HomeFragment()));
        navCart.setOnClickListener(view -> switchFragment(new CartFragment()));
        navOrders.setOnClickListener(view -> switchFragment(new OrdersFragment()));
        navProfile.setOnClickListener(view -> switchFragment(new ProfileFragment()));
    }

    private void switchFragment(Fragment newFragment) {
        if (newFragment.getClass().equals(currentFragment.getClass())) {
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

        Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        if (currentFragment != null && currentFragment.getClass().equals(fragment.getClass())) {
            Log.d("HomeActivity", "Fragment already loaded: " + fragment.getClass().getSimpleName());
            return;
        }

        Log.d("HomeActivity", "Loading Fragment: " + fragment.getClass().getSimpleName());

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();

        getSupportFragmentManager().executePendingTransactions(); // Đảm bảo Fragment được cập nhật ngay lập tức
    }

}
