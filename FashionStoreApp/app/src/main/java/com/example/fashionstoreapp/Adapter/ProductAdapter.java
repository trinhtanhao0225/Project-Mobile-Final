package com.example.fashionstoreapp.Adapter;

<<<<<<< HEAD

import static java.lang.Integer.parseInt;

import android.content.Context;
import android.content.Intent;
=======
import android.content.Context;
import android.content.Intent;
import android.util.Log;
>>>>>>> c52494fe6a3d80d0a4fe61fcfaed901e57911eec
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.fashionstoreapp.Activity.ShowDetailActivity;
import com.example.fashionstoreapp.Model.Cart;
import com.example.fashionstoreapp.Model.Product;
<<<<<<< HEAD
import com.example.fashionstoreapp.Model.User;
import com.example.fashionstoreapp.R;
import com.example.fashionstoreapp.Retrofit.CartAPI;
import com.example.fashionstoreapp.Somethings.ObjectSharedPreferences;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
=======
import com.example.fashionstoreapp.Model.ProductImage;
import com.example.fashionstoreapp.Model.User;
import com.example.fashionstoreapp.R;
import com.example.fashionstoreapp.Retrofit.CartAPI;
import com.example.fashionstoreapp.Retrofit.ProductAPI;
import com.example.fashionstoreapp.Somethings.ObjectSharedPreferences;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
>>>>>>> c52494fe6a3d80d0a4fe61fcfaed901e57911eec

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {
<<<<<<< HEAD
    List<Product> Products;

    Context context;
    public ProductAdapter(List<Product> products, Context context) {
        this.Products = products;
        this.context = context;
=======

    private List<Product> products;
    private final Context context;
    private final boolean hasUser;

    public ProductAdapter(List<Product> products, Context context, boolean hasUser) {
        this.products = products != null ? new ArrayList<>(products) : new ArrayList<>();
        this.context = context;
        this.hasUser = hasUser;
    }

    public void updateProducts(List<Product> newProducts) {
        this.products.clear();
        if (newProducts != null) {
            for (Product product : newProducts) {
                if (product != null && product.getProductName() != null && product.getProductImages() != null && !product.getProductImages().isEmpty()) {
                    this.products.add(product);
                } else {
                    Log.w("ProductAdapter", "Skipping invalid product: " + (product != null ? "ID: " + product.getId() : "null"));
                }
            }
        }
        notifyDataSetChanged();
>>>>>>> c52494fe6a3d80d0a4fe61fcfaed901e57911eec
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
<<<<<<< HEAD
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_products, parent, false);

        return new ViewHolder(inflate);
=======
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_products, parent, false);
        return new ViewHolder(view);
>>>>>>> c52494fe6a3d80d0a4fe61fcfaed901e57911eec
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
<<<<<<< HEAD
        Product product = Products.get(position);
        holder.title.setText(Products.get(position).getProduct_Name());
        Locale localeEN = new Locale("en", "EN");
        NumberFormat en = NumberFormat.getInstance(localeEN);
        holder.fee.setText(en.format(Products.get(position).getPrice()));

        Glide.with(holder.itemView.getContext())
                .load(Products.get(position).getProductImage().get(0).getUrl_Image())
                .into(holder.pic);

        holder.addBtn.setOnClickListener(v -> {
            User user = ObjectSharedPreferences.getSavedObjectFromPreference(context, "User", "MODE_PRIVATE", User.class);
            CartAPI.cartAPI.addToCart(user.getId(), product.getId(), 1).enqueue(new Callback<Cart>() {
                @Override
                public void onResponse(Call<Cart> call, Response<Cart> response) {
                    Cart cart = response.body();
                    if(cart !=null){
                        Toast.makeText(context.getApplicationContext(), "Thêm vào giỏ thành công", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(context.getApplicationContext(), "Thêm vào giỏ thất bại", Toast.LENGTH_SHORT).show();

=======
        Product product = products.get(position);

        if (product == null) {
            Log.e("ProductAdapter", "Product at position " + position + " is null");
            holder.title.setText("Unknown Product");
            holder.fee.setText("N/A");
            holder.deleteBtn.setVisibility(View.GONE);
            return;
        }

        // Cập nhật tên sản phẩm
        String productName = product.getProductName();
        if (productName != null) {
            holder.title.setText(productName);
        } else {
            Log.w("ProductAdapter", "Product name is null for ID: " + product.getId());
            holder.title.setText("Unknown Product");
        }

        // Định dạng giá
        Locale locale = new Locale("en", "EN");
        NumberFormat numberFormat = NumberFormat.getInstance(locale);
        holder.fee.setText(numberFormat.format(product.getPrice()) + " ₫");

        // Load hình ảnh
        holder.ivImage.setImageDrawable(null); // Xóa hình ảnh cũ
        List<ProductImage> productImages = product.getProductImages();
        if (productImages != null && !productImages.isEmpty()) {
            String imageUrl = productImages.get(0).getImageUrl();
            Log.d("ProductImage", "Product: " + productName + ", Image URL: " + imageUrl);
            Glide.with(context)
                    .load(imageUrl)
                    .skipMemoryCache(true)
                    .diskCacheStrategy(com.bumptech.glide.load.engine.DiskCacheStrategy.NONE)

                    .into(holder.ivImage);
        } else {
            Log.w("ProductImage", "No images for product: " + productName);
        }

        holder.deleteBtn.setVisibility(hasUser ? View.VISIBLE : View.GONE);
        holder.deleteBtn.setOnClickListener(v -> deleteProduct(product, position));


        holder.addBtn.setVisibility(hasUser ? View.VISIBLE : View.GONE);

        holder.addBtn.setOnClickListener(v -> {
            User user = ObjectSharedPreferences.getSavedObjectFromPreference(context, "UserPrefs", "User", User.class);
            if (user == null) {
                Toast.makeText(context, "Vui lòng đăng nhập để thêm sản phẩm", Toast.LENGTH_SHORT).show();
                return;
            }

            CartAPI.cartAPI.addToCart(user.getId(), product.getId(), 1).enqueue(new Callback<Cart>() {
                @Override
                public void onResponse(@NonNull Call<Cart> call, @NonNull Response<Cart> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        Toast.makeText(context, "Đã thêm vào giỏ", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(context, "Thêm vào giỏ thất bại", Toast.LENGTH_SHORT).show();
>>>>>>> c52494fe6a3d80d0a4fe61fcfaed901e57911eec
                    }
                }

                @Override
<<<<<<< HEAD
                public void onFailure(Call<Cart> call, Throwable t) {
                    Toast.makeText(context.getApplicationContext(), "Call API Add to cart fail", Toast.LENGTH_SHORT).show();
=======
                public void onFailure(@NonNull Call<Cart> call, @NonNull Throwable t) {
                    Toast.makeText(context, "Lỗi kết nối khi thêm vào giỏ", Toast.LENGTH_SHORT).show();
>>>>>>> c52494fe6a3d80d0a4fe61fcfaed901e57911eec
                }
            });
        });

<<<<<<< HEAD
        holder.itemView.setOnClickListener(v ->{
            Intent intent = new Intent(holder.itemView.getContext(), ShowDetailActivity.class);
            intent.putExtra("product", product);
            holder.itemView.getContext().startActivity(intent);
=======
        // Mở chi tiết sản phẩm
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, ShowDetailActivity.class);
            intent.putExtra("product", product);
            context.startActivity(intent);
>>>>>>> c52494fe6a3d80d0a4fe61fcfaed901e57911eec
        });
    }


<<<<<<< HEAD
    @Override
    public int getItemCount() {
        return Products.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title, fee;
        ImageView pic;
        ImageView addBtn;
=======
    private void deleteProduct(Product product, int position) {
        ProductAPI.productApi.removeProduct(product.getId()).enqueue(new Callback<Map<String, Object>>() {
            @Override
            public void onResponse(Call<Map<String, Object>> call, Response<Map<String, Object>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Map<String, Object> result = response.body();
                    if (Boolean.TRUE.equals(result.get("success"))) {
                        products.remove(position);
                        notifyItemRemoved(position);
                        notifyItemRangeChanged(position, products.size());
                        Toast.makeText(context, "Xóa sản phẩm thành công", Toast.LENGTH_SHORT).show();
                    } else {
                        String message = result.get("message") != null ? result.get("message").toString() : "Xóa sản phẩm thất bại";
                        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
                        Log.e("ProductAdapter", "Delete product error: " + message);
                    }
                } else {
                    Toast.makeText(context, "Xóa sản phẩm thất bại", Toast.LENGTH_SHORT).show();
                    Log.e("ProductAdapter", "Delete product error: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<Map<String, Object>> call, Throwable t) {
                Toast.makeText(context, "Lỗi kết nối: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("ProductAdapter", "Delete product failure: " + t.getMessage());
            }
        });
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView title, fee;
        ImageView ivImage, addBtn, deleteBtn;
>>>>>>> c52494fe6a3d80d0a4fe61fcfaed901e57911eec

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
<<<<<<< HEAD
            pic = itemView.findViewById(R.id.ivImage);
            fee = itemView.findViewById(R.id.fee);
            addBtn = itemView.findViewById(R.id.addBtn);
        }
    }
}
=======
            fee = itemView.findViewById(R.id.fee);
            ivImage = itemView.findViewById(R.id.ivImage);
            addBtn = itemView.findViewById(R.id.addBtn);
            deleteBtn = itemView.findViewById(R.id.deleteBtn);
        }
    }
}
>>>>>>> c52494fe6a3d80d0a4fe61fcfaed901e57911eec
