package com.example.fashionstoreapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
<<<<<<< HEAD
import android.content.SharedPreferences;
=======
>>>>>>> c52494fe6a3d80d0a4fe61fcfaed901e57911eec
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
<<<<<<< HEAD
=======
import android.widget.Toast;
>>>>>>> c52494fe6a3d80d0a4fe61fcfaed901e57911eec

import com.bumptech.glide.Glide;
import com.example.fashionstoreapp.Adapter.CategoryAdapter;
import com.example.fashionstoreapp.Adapter.ProductAdapter;
import com.example.fashionstoreapp.Model.Category;
import com.example.fashionstoreapp.Model.Product;
import com.example.fashionstoreapp.Model.User;
import com.example.fashionstoreapp.R;
import com.example.fashionstoreapp.Retrofit.CategoryAPI;
import com.example.fashionstoreapp.Retrofit.ProductAPI;
import com.example.fashionstoreapp.Somethings.ObjectSharedPreferences;

<<<<<<< HEAD
=======
import java.util.ArrayList;
>>>>>>> c52494fe6a3d80d0a4fe61fcfaed901e57911eec
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
<<<<<<< HEAD
    SharedPreferences sharedpreferences;
    private RecyclerView.Adapter adapter, adapter2, adapter3, adapter4;

    private RecyclerView recyclerViewCategoryList, recyclerViewNewProductList, recyclerViewBestSellersList, recyclerViewAllProductsList;
    TextView tvHiName;
    EditText etSearch;

    ImageView ivAvatar, ivHome, ivUser, ivCart, ivHistory, ivSearch;
    User user;

    //Api
=======
    private CategoryAdapter categoryAdapter;
    private ProductAdapter newProductsAdapter, bestSellersAdapter;
    private RecyclerView recyclerViewCategoryList, recyclerViewNewProductList, recyclerViewBestSellersList;
    private TextView tvHiName;
    private EditText etSearch;
    private ImageView ivAvatar, ivHome, ivUser, ivCart, ivHistory, ivSearch;
    private User user;
    private boolean hasUser;
>>>>>>> c52494fe6a3d80d0a4fe61fcfaed901e57911eec

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
<<<<<<< HEAD
        AnhXa();
        appBarClick();
        LoadUserInfor();
        LoadCategories();
        LoadNewProducts();
        LoadBestSellers();
        ivSearchClick();
        LoadAllProducts();

    }

    private void ivSearchClick() {
        ivSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ProductsActivity.class);
                intent.putExtra("searchContent", etSearch.getText().toString());
                startActivity(intent);
            }
        });

        etSearch.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // If the event is a key-down event on the "enter" button
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    // Perform action on key press
                    Intent intent = new Intent(MainActivity.this, ProductsActivity.class);
                    intent.putExtra("searchContent", etSearch.getText().toString());
                    intent.putExtra("category_id", "-1");
                    startActivity(intent);
                    return true;
                }
                return false;
            }
        });
    }

    private void appBarClick() {
        ivHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MainActivity.class));
                finish();
            }
        });
        ivUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, UserActivity.class));
                finish();
            }
        });
        ivCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, CartActivity.class));
                finish();
            }
        });

        ivHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, OrderActivity.class));
                finish();
            }
        });
    }

    private void LoadBestSellers() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false);
        recyclerViewBestSellersList = findViewById(R.id.view3);
        recyclerViewBestSellersList.setLayoutManager(linearLayoutManager);

        //GET API
        ProductAPI.productApi.getBestSellers().enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                List<Product> newProductsList = response.body();
                adapter3 = new ProductAdapter(newProductsList, MainActivity.this);
                recyclerViewBestSellersList.setAdapter(adapter3);
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Log.e("====", "Call API Get Best Sellers fail");
=======

        // Kiểm tra quyền admin
        user = ObjectSharedPreferences.getSavedObjectFromPreference(
                MainActivity.this, "User", "MODE_PRIVATE", User.class
        );
        hasUser = user != null;
        AnhXa();
        appBarClick();
        LoadUserInfor();
        ivSearchClick();
        initRecyclerViews();
        LoadCategories();
        LoadNewProducts();
        LoadBestSellers();
    }

    private void initRecyclerViews() {
        // Khởi tạo RecyclerView cho Categories
        recyclerViewCategoryList = findViewById(R.id.view1);
        recyclerViewCategoryList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        categoryAdapter = new CategoryAdapter(new ArrayList<>(), MainActivity.this);
        recyclerViewCategoryList.setAdapter(categoryAdapter);
        Log.d("RecyclerView", "Categories adapter initialized with empty list");

        // Khởi tạo RecyclerView cho New Products
        recyclerViewNewProductList = findViewById(R.id.view2);
        recyclerViewNewProductList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        newProductsAdapter = new ProductAdapter(new ArrayList<>(), MainActivity.this, hasUser);
        recyclerViewNewProductList.setAdapter(newProductsAdapter);
        Log.d("RecyclerView", "NewProducts adapter initialized with empty list");

        // Khởi tạo RecyclerView cho Best Sellers
        recyclerViewBestSellersList = findViewById(R.id.view3);
        recyclerViewBestSellersList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        bestSellersAdapter = new ProductAdapter(new ArrayList<>(), MainActivity.this, hasUser);
        recyclerViewBestSellersList.setAdapter(bestSellersAdapter);
        Log.d("RecyclerView", "BestSellers adapter initialized with empty list");
    }

    private void LoadCategories() {
        CategoryAPI.categoryAPI.GetAllCategories().enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Category> categoriesList = response.body();
                    Log.d("APIResponse", "Categories loaded: " + categoriesList.size() + " items");
                    for (Category category : categoriesList) {
                        Log.d("APIResponse", "Category: " + category.getCategory_Name() + ", Image: " + category.getCategory_Image());
                    }
                    categoryAdapter.updateCategories(categoriesList);
                    recyclerViewCategoryList.post(() -> {
                        Log.d("RecyclerView", "Categories adapter updated with " + categoriesList.size() + " items");
                        categoryAdapter.notifyDataSetChanged();
                    });
                    Toast.makeText(MainActivity.this, "Đã tải " + categoriesList.size() + " danh mục", Toast.LENGTH_SHORT).show();
                } else {
                    Log.e("APIError", "Get Categories response is null or unsuccessful: " + response.message());
                    Toast.makeText(MainActivity.this, "Lỗi tải danh mục: " + response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {
                Log.e("APIError", "Call API Get Categories fail: " + t.getMessage());
                Toast.makeText(MainActivity.this, "Lỗi kết nối: " + t.getMessage(), Toast.LENGTH_SHORT).show();
>>>>>>> c52494fe6a3d80d0a4fe61fcfaed901e57911eec
            }
        });
    }

    private void LoadNewProducts() {
<<<<<<< HEAD
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false);
        recyclerViewNewProductList = findViewById(R.id.view2);
        recyclerViewNewProductList.setLayoutManager(linearLayoutManager);

        //GET API
        ProductAPI.productApi.getNewProduct().enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                List<Product> newProductsList = response.body();
                adapter2 = new ProductAdapter(newProductsList, MainActivity.this);
                recyclerViewNewProductList.setAdapter(adapter2);
=======
        ProductAPI.productApi.getNewProduct().enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Product> newProductsList = response.body();
                    Log.d("APIResponse", "New Products loaded: " + newProductsList.size() + " items");
                    newProductsAdapter = new ProductAdapter(newProductsList, MainActivity.this, hasUser);
                    recyclerViewNewProductList.setAdapter(newProductsAdapter);
                    recyclerViewNewProductList.post(() -> {
                        Log.d("RecyclerView", "NewProducts adapter attached with " + newProductsList.size() + " items");
                        newProductsAdapter.notifyDataSetChanged();
                    });
                    Toast.makeText(MainActivity.this, "Đã tải sản phẩm mới", Toast.LENGTH_SHORT).show();
                } else {
                    Log.e("APIError", "Get New Products response is null or unsuccessful: " + response.message());
                    Toast.makeText(MainActivity.this, "Lỗi tải sản phẩm mới", Toast.LENGTH_SHORT).show();
                }
>>>>>>> c52494fe6a3d80d0a4fe61fcfaed901e57911eec
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
<<<<<<< HEAD
                Log.e("++++", t.getMessage());
                Log.e("====", "Call API Get New Products fail");
            }
        });
    }
    private void LoadAllProducts() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false);
        recyclerViewAllProductsList = findViewById(R.id.view4);
        recyclerViewAllProductsList.setLayoutManager(linearLayoutManager);

        ProductAPI.productApi.getAllProducts().enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                List<Product> allProductList = response.body();
                adapter4 = new ProductAdapter(allProductList, MainActivity.this);
                recyclerViewAllProductsList.setAdapter(adapter4);
=======
                Log.e("APIError", "Call API Get New Products fail: " + t.getMessage());
                Toast.makeText(MainActivity.this, "Lỗi kết nối: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void LoadBestSellers() {
        ProductAPI.productApi.getBestSellers().enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Product> bestSellersList = response.body();
                    Log.d("APIResponse", "Best Sellers loaded: " + bestSellersList.size() + " items");
                    bestSellersAdapter = new ProductAdapter(bestSellersList, MainActivity.this, hasUser);
                    recyclerViewBestSellersList.setAdapter(bestSellersAdapter);
                    recyclerViewBestSellersList.post(() -> {
                        Log.d("RecyclerView", "BestSellers adapter attached with " + bestSellersList.size() + " items");
                        bestSellersAdapter.notifyDataSetChanged();
                    });
                    Toast.makeText(MainActivity.this, "Đã tải sản phẩm bán chạy", Toast.LENGTH_SHORT).show();
                } else {
                    Log.e("APIError", "Get Best Sellers response is null or unsuccessful: " + response.message());
                    Toast.makeText(MainActivity.this, "Lỗi tải sản phẩm bán chạy", Toast.LENGTH_SHORT).show();
                }
>>>>>>> c52494fe6a3d80d0a4fe61fcfaed901e57911eec
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
<<<<<<< HEAD
                Log.e("====", "Call API Get All Products fail");
            }
        });
    }

    private void LoadCategories() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewCategoryList = findViewById(R.id.view1);
        recyclerViewCategoryList.setLayoutManager(linearLayoutManager);
        //Get API
        CategoryAPI.categoryAPI.GetAllCategories().enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                List<Category> categoriesList = response.body();
                adapter = new CategoryAdapter(categoriesList, MainActivity.this);
                recyclerViewCategoryList.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {
                Log.e("====", "Call API Get Categories fail");

=======
                Log.e("APIError", "Call API Get Best Sellers fail: " + t.getMessage());
                Toast.makeText(MainActivity.this, "Lỗi kết nối: " + t.getMessage(), Toast.LENGTH_SHORT).show();
>>>>>>> c52494fe6a3d80d0a4fe61fcfaed901e57911eec
            }
        });
    }

    private void LoadUserInfor() {
<<<<<<< HEAD
        user = ObjectSharedPreferences.getSavedObjectFromPreference(MainActivity.this, "User", "MODE_PRIVATE", User.class);
        tvHiName.setText("Hi "+ user.getUser_Name());
        Glide.with(getApplicationContext()).load(user.getAvatar()).into(ivAvatar);
=======
        user = ObjectSharedPreferences.getSavedObjectFromPreference(
                MainActivity.this, "User", "MODE_PRIVATE", User.class
        );
        if (user != null) {
            tvHiName.setText("Hi " + user.getUser_Name());
        } else {
            tvHiName.setText("Hi Guest");
        }
    }

    private void ivSearchClick() {
        ivSearch.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ProductsActivity.class);
            intent.putExtra("searchContent", etSearch.getText().toString());
            startActivity(intent);
        });

        etSearch.setOnKeyListener((v, keyCode, event) -> {
            if (event.getAction() == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                Intent intent = new Intent(MainActivity.this, ProductsActivity.class);
                intent.putExtra("searchContent", etSearch.getText().toString());
                intent.putExtra("category_id", "-1");
                startActivity(intent);
                return true;
            }
            return false;
        });
    }

    private void appBarClick() {
        ivHome.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, MainActivity.class));
            finish();
        });
        ivUser.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, UserActivity.class));
            finish();
        });
        ivCart.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, CartActivity.class));
            finish();
        });
        ivHistory.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, OrderActivity.class));
            finish();
        });
>>>>>>> c52494fe6a3d80d0a4fe61fcfaed901e57911eec
    }

    private void AnhXa() {
        tvHiName = findViewById(R.id.tvHiName);
        ivAvatar = findViewById(R.id.ivAvatar);
        ivHome = findViewById(R.id.ivHome);
        ivUser = findViewById(R.id.ivUser);
        ivCart = findViewById(R.id.ivCart);
        ivHistory = findViewById(R.id.ivHistory);
        etSearch = findViewById(R.id.etSearch);
        ivSearch = findViewById(R.id.ivSearch);
<<<<<<< HEAD

=======
>>>>>>> c52494fe6a3d80d0a4fe61fcfaed901e57911eec
    }
}