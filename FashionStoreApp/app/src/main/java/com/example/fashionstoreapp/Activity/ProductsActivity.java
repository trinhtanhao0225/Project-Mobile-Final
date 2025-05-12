package com.example.fashionstoreapp.Activity;

import android.content.Intent;
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

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fashionstoreapp.Adapter.ProductAdapter;
import com.example.fashionstoreapp.Model.Category;
import com.example.fashionstoreapp.Model.Product;
<<<<<<< HEAD
import com.example.fashionstoreapp.R;
import com.example.fashionstoreapp.Retrofit.ProductAPI;

=======
import com.example.fashionstoreapp.Model.User;
import com.example.fashionstoreapp.R;
import com.example.fashionstoreapp.Retrofit.ProductAPI;
import com.example.fashionstoreapp.Somethings.ObjectSharedPreferences;

import java.util.ArrayList;
>>>>>>> c52494fe6a3d80d0a4fe61fcfaed901e57911eec
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductsActivity extends AppCompatActivity {

<<<<<<< HEAD
    ProductAdapter productAdapter;
    EditText etSearch;

    TextView tvResult;
    ImageView ivSearch, ivHome, ivUser, ivCart, ivHistory;
    RecyclerView rcProduct;
=======
    private ProductAdapter productAdapter;
    private EditText etSearch;
    private TextView tvResult;
    private ImageView ivSearch, ivHome, ivUser, ivCart, ivHistory;
    private RecyclerView rcProduct;
>>>>>>> c52494fe6a3d80d0a4fe61fcfaed901e57911eec

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);
<<<<<<< HEAD
        AnhXa();
        appBarClick();
        Category category = (Category) getIntent().getSerializableExtra("category");
        if (category == null){
            etSearch.setText(getIntent().getSerializableExtra("searchContent").toString());
            loadProductSearch();
        }
        else {
            loadProductCategory(category);
        }
        ivSearchClick();
    }

    private void loadProductCategory(Category category) {
        tvResult.setText(category.getProduct().size()+ " Results of category: " + category.getCategory_Name());
        productAdapter=new ProductAdapter(category.getProduct(),ProductsActivity.this);
        rcProduct.setHasFixedSize(true);
        GridLayoutManager layoutManager=new GridLayoutManager(getApplicationContext(),2);
        rcProduct.setLayoutManager(layoutManager);
        rcProduct.setAdapter(productAdapter);
    }

    private void ivSearchClick() {
        ivSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProductsActivity.this, ProductsActivity.class);
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
                    Intent intent = new Intent(ProductsActivity.this, ProductsActivity.class);
                    intent.putExtra("searchContent", etSearch.getText().toString());
                    intent.putExtra("category_id", "-1");
                    startActivity(intent);
                    return true;
                }
                return false;
            }
        });
    }

    private void loadProductSearch() {
        ProductAPI.productApi.search(etSearch.getText().toString()).enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                List<Product> newProductsList = response.body();
                tvResult.setText(newProductsList.size() +" Results");
                productAdapter=new ProductAdapter(newProductsList,ProductsActivity.this);
                rcProduct.setHasFixedSize(true);
                GridLayoutManager layoutManager=new GridLayoutManager(getApplicationContext(),2);
                rcProduct.setLayoutManager(layoutManager);
                rcProduct.setAdapter(productAdapter);
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Log.e("====", "Call API Search fail");
            }
        });
    }

    private void appBarClick() {
        ivHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProductsActivity.this, MainActivity.class));
                finish();
            }
        });
        ivUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProductsActivity.this, UserActivity.class));
                finish();
            }
        });
        ivCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProductsActivity.this, CartActivity.class));
                finish();
            }
        });

        ivHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProductsActivity.this, OrderActivity.class));
                finish();
            }
        });
    }
    private void AnhXa() {
        rcProduct = findViewById(R.id.view);
        etSearch = findViewById(R.id.etSearch);
        ivSearch = findViewById(R.id.ivSearch);
        //appBar
=======

        initViews();
        setupAppBar();
        setupRecyclerView();
        setupSearch();
        loadInitialData();
    }

    private void initViews() {
        rcProduct = findViewById(R.id.view);
        etSearch = findViewById(R.id.etSearch);
        ivSearch = findViewById(R.id.ivSearch);
>>>>>>> c52494fe6a3d80d0a4fe61fcfaed901e57911eec
        ivHome = findViewById(R.id.ivHome);
        ivCart = findViewById(R.id.ivCart);
        ivHistory = findViewById(R.id.ivHistory);
        ivUser = findViewById(R.id.ivUser);
<<<<<<< HEAD
        //
        tvResult = findViewById(R.id.tvResult);
    }
}
=======
        tvResult = findViewById(R.id.tvResult);
    }

    private void setupRecyclerView() {
        rcProduct.setHasFixedSize(true);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        rcProduct.setLayoutManager(layoutManager);
        productAdapter = new ProductAdapter(new ArrayList<>(), this, true); // Truyền isAdmin
        rcProduct.setAdapter(productAdapter);
        rcProduct.post(() -> {
            Log.d("RecyclerView", "Adapter attached");
            productAdapter.notifyDataSetChanged();
        });
    }

    private void setupAppBar() {
        ivHome.setOnClickListener(v -> navigateTo(MainActivity.class));
        ivUser.setOnClickListener(v -> navigateTo(UserActivity.class));
        ivCart.setOnClickListener(v -> navigateTo(CartActivity.class));
        ivHistory.setOnClickListener(v -> navigateTo(OrderActivity.class));
    }

    private void setupSearch() {
        ivSearch.setOnClickListener(v -> performSearch());
        etSearch.setOnKeyListener((v, keyCode, event) -> {
            if (event.getAction() == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                performSearch();
                return true;
            }
            return false;
        });
    }

    private void loadInitialData() {
        Category category = (Category) getIntent().getSerializableExtra("category");
        String searchContent = getIntent().getStringExtra("searchContent");

        if (category != null) {
            loadProductCategory(category);
        } else if (searchContent != null && !searchContent.isEmpty()) {
            etSearch.setText(searchContent);
            performSearch();
        } else {
            tvResult.setText("No data to display");
            Log.w("ProductsActivity", "No category or search content provided");
            fetchAllProducts();
        }
    }

    private void fetchAllProducts() {
        ProductAPI.productApi.getProducts().enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Product> products = response.body();
                    processProducts(products);
                } else {
                    tvResult.setText("Failed to load products");
                    Log.e("APIError", "Get all products response is null or unsuccessful: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                tvResult.setText("Failed to load products");
                Log.e("APIError", "Get all products API failed: " + t.getMessage());
            }
        });
    }

    private void loadProductCategory(Category category) {
        if (category == null) {
            tvResult.setText("Invalid category");
            Log.e("ProductsActivity", "Category is null");
            return;
        }

        List<Product> products = category.getProduct();
        processProducts(products);
    }

    private void processProducts(List<Product> products) {
        if (products != null && !products.isEmpty()) {
            List<Product> validProducts = new ArrayList<>();
            for (Product product : products) {
                if (product == null || product.getProductName() == null || product.getProductImages() == null || product.getProductImages().isEmpty()) {
                    Log.e("ProductsActivity", "Invalid product: " + (product != null ? "ID: " + product.getId() : "null"));
                    continue;
                }
                validProducts.add(product);
                String imageUrl = product.getProductImages().get(0).getImageUrl();
                Log.d("CategoryProduct", "Product: " + product.getProductName() + ", Image URL: " + imageUrl);
            }
            tvResult.setText(validProducts.size() + " Results");
            productAdapter.updateProducts(validProducts);
            productAdapter.notifyDataSetChanged();
        } else {
            tvResult.setText("No products found");
            Log.e("CategoryError", "Product list is null or empty");
        }
    }

    private void performSearch() {
        String query = etSearch.getText().toString().trim();
        if (query.isEmpty()) {
            tvResult.setText("Please enter a search query");
            Log.w("ProductsActivity", "Empty search query");
            return;
        }

        ProductAPI.productApi.search(query).enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Product> products = response.body();
                    processProducts(products);
                } else {
                    tvResult.setText("No results found");
                    Log.e("APIError", "Search response is null or unsuccessful: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                tvResult.setText("Failed to load products");
                Log.e("APIError", "Search API failed: " + t.getMessage());
            }
        });
    }

    private void navigateTo(Class<?> targetActivity) {
        Intent intent = new Intent(ProductsActivity.this, targetActivity);
        startActivity(intent);
        finish();
    }
}
>>>>>>> c52494fe6a3d80d0a4fe61fcfaed901e57911eec
