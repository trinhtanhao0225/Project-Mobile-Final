package com.example.fashionstoreapp.Adapter;

import static java.lang.Integer.parseInt;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.fashionstoreapp.Activity.ShowDetailActivity;
import com.example.fashionstoreapp.Interface.CartItemInterface;
import com.example.fashionstoreapp.Model.Cart;
import com.example.fashionstoreapp.Model.User;
import com.example.fashionstoreapp.R;
import com.example.fashionstoreapp.Retrofit.CartAPI;
import com.example.fashionstoreapp.Somethings.ObjectSharedPreferences;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

<<<<<<< HEAD
public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder>{
=======
public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {
>>>>>>> c52494fe6a3d80d0a4fe61fcfaed901e57911eec

    final CartItemInterface cartItemInterface;
    List<Cart> carts;
    Context context;

    public CartAdapter(CartItemInterface cartItemInterface, List<Cart> carts, Context context) {
        this.cartItemInterface = cartItemInterface;
        this.carts = carts;
        this.context = context;
    }

    @NonNull
    @Override
    public CartAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_cart, parent, false);
        return new CartAdapter.ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull CartAdapter.ViewHolder holder, int position) {
<<<<<<< HEAD
        Cart cart = carts.get(position);
        User user = ObjectSharedPreferences.getSavedObjectFromPreference(context, "User", "MODE_PRIVATE", User.class);
        holder.tvCount.setText(String.valueOf(carts.get(position).getCount()));
        holder.tvProductName.setText(cart.getProduct().getProduct_Name());
=======
        if (carts == null || carts.isEmpty()) {
            return; // Thoát nếu danh sách carts null hoặc rỗng
        }

        Cart cart = carts.get(position);
        if (cart == null || cart.getProduct() == null) {
            return; // Thoát nếu cart hoặc product null
        }

        User user = ObjectSharedPreferences.getSavedObjectFromPreference(context, "User", "MODE_PRIVATE", User.class);
        if (user == null) {
            Log.e("CartAdapter", "User is null");
            return; // Thoát nếu user null
        }

        // Thiết lập các giá trị giao diện
        holder.tvCount.setText(String.valueOf(cart.getCount()));
        holder.tvProductName.setText(cart.getProduct().getProductName());
>>>>>>> c52494fe6a3d80d0a4fe61fcfaed901e57911eec
        Locale localeEN = new Locale("en", "EN");
        NumberFormat en = NumberFormat.getInstance(localeEN);
        holder.tvPrice.setText(en.format(cart.getProduct().getPrice()));
        holder.tvTotalPrice.setText(en.format(cart.getProduct().getPrice() * cart.getCount()));
<<<<<<< HEAD
        Glide.with(holder.itemView.getContext())
                .load(cart.getProduct().getProductImage().get(0).getUrl_Image())
                .into(holder.ivImage);
//        int Total= 0;
//        for(Cart y: carts){
//            Total += y.getCount()*y.getProduct().getPrice();
//        }
//        CartActivity.tvTotal.setText(en.format(Total));
        holder.layout_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int price = parseInt(holder.tvTotalPrice.getText().toString().replace(",","")) * (-1);
                CartAPI.cartAPI.deleteCart(cart.getId(), user.getId()).enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        if (response.isSuccessful()){
=======

        // Kiểm tra và tải hình ảnh sản phẩm
        if (cart.getProduct().getProductImages() != null && !cart.getProduct().getProductImages().isEmpty()) {
            Glide.with(holder.itemView.getContext())
                    .load(cart.getProduct().getProductImages().get(0).getImageUrl())
                    .into(holder.ivImage);
        }

        // Sự kiện xóa sản phẩm
        holder.layout_delete.setOnClickListener(v -> {
            String totalPriceText = holder.tvTotalPrice.getText().toString().replace(",", "");
            try {
                int price = parseInt(totalPriceText) * (-1);
                CartAPI.cartAPI.deleteCart(cart.getId(), user.getId()).enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        if (response.isSuccessful()) {
>>>>>>> c52494fe6a3d80d0a4fe61fcfaed901e57911eec
                            cartItemInterface.onClickUpdatePrice(price);
                            notifyItemRemoved(holder.getAdapterPosition());
                            carts.remove(cart);
                        }
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
<<<<<<< HEAD
                        Log.e("====", t.getMessage());
                    }
                });
            }

        });

        holder.ivImage.setOnClickListener(v ->{
=======
                        Log.e("CartAdapter", "Delete cart failed: " + t.getMessage());
                    }
                });
            } catch (NumberFormatException e) {
                Log.e("CartAdapter", "Invalid total price format: " + totalPriceText);
            }
        });

        // Sự kiện nhấn vào hình ảnh sản phẩm
        holder.ivImage.setOnClickListener(v -> {
>>>>>>> c52494fe6a3d80d0a4fe61fcfaed901e57911eec
            Intent intent = new Intent(holder.itemView.getContext(), ShowDetailActivity.class);
            intent.putExtra("product", cart.getProduct());
            holder.itemView.getContext().startActivity(intent);
        });

<<<<<<< HEAD
        holder.ivPlus.setOnClickListener(v -> {
            if (cart.getCount()<cart.getProduct().getQuantity()){
                cart.setCount(cart.getCount()+1);
                CartAPI.cartAPI.addToCart(user.getId(), cart.getProduct().getId(), 1).enqueue(new Callback<Cart>() {
                    @Override
                    public void onResponse(Call<Cart> call, Response<Cart> response) {
                        int price = cart.getProduct().getPrice();
                        holder.tvCount.setText(parseInt(holder.tvCount.getText().toString())+1+"");
                        holder.tvTotalPrice.setText(en.format(price * parseInt(holder.tvCount.getText().toString())));
                        cartItemInterface.onClickUpdatePrice(price);
                    }
                    @Override
                    public void onFailure(Call<Cart> call, Throwable t) {
                        Log.e("++++","update cart fail");
=======
        // Sự kiện tăng số lượng
        holder.ivPlus.setOnClickListener(v -> {
            if (cart.getCount() < cart.getProduct().getQuantity()) {
                cart.setCount(cart.getCount() + 1);
                CartAPI.cartAPI.addToCart(user.getId(), cart.getProduct().getId(), 1).enqueue(new Callback<Cart>() {
                    @Override
                    public void onResponse(Call<Cart> call, Response<Cart> response) {
                        if (response.isSuccessful()) {
                            double price = cart.getProduct().getPrice();
                            holder.tvCount.setText(String.valueOf(parseInt(holder.tvCount.getText().toString()) + 1));
                            holder.tvTotalPrice.setText(en.format(price * parseInt(holder.tvCount.getText().toString())));
                            cartItemInterface.onClickUpdatePrice(price);
                        }
                    }

                    @Override
                    public void onFailure(Call<Cart> call, Throwable t) {
                        Log.e("CartAdapter", "Update cart failed: " + t.getMessage());
>>>>>>> c52494fe6a3d80d0a4fe61fcfaed901e57911eec
                    }
                });
            }
        });

<<<<<<< HEAD
        holder.ivMinus.setOnClickListener(v -> {
            if (cart.getCount()>1){
                cart.setCount(cart.getCount()-1);
                CartAPI.cartAPI.addToCart(user.getId(), cart.getProduct().getId(), -1).enqueue(new Callback<Cart>() {
                    @Override
                    public void onResponse(Call<Cart> call, Response<Cart> response) {
                        int price = cart.getProduct().getPrice();
                        holder.tvCount.setText(parseInt(holder.tvCount.getText().toString())-1+"");
                        holder.tvTotalPrice.setText(en.format(price * parseInt(holder.tvCount.getText().toString())));
                        cartItemInterface.onClickUpdatePrice(price*-1);
                    }
                    @Override
                    public void onFailure(Call<Cart> call, Throwable t) {
                        Log.e("++++","update cart fail");
=======
        // Sự kiện giảm số lượng
        holder.ivMinus.setOnClickListener(v -> {
            if (cart.getCount() > 1) {
                cart.setCount(cart.getCount() - 1);
                CartAPI.cartAPI.addToCart(user.getId(), cart.getProduct().getId(), -1).enqueue(new Callback<Cart>() {
                    @Override
                    public void onResponse(Call<Cart> call, Response<Cart> response) {
                        if (response.isSuccessful()) {
                            double price = cart.getProduct().getPrice();
                            holder.tvCount.setText(String.valueOf(parseInt(holder.tvCount.getText().toString()) - 1));
                            holder.tvTotalPrice.setText(en.format(price * parseInt(holder.tvCount.getText().toString())));
                            cartItemInterface.onClickUpdatePrice(price * -1);
                        }
                    }

                    @Override
                    public void onFailure(Call<Cart> call, Throwable t) {
                        Log.e("CartAdapter", "Update cart failed: " + t.getMessage());
>>>>>>> c52494fe6a3d80d0a4fe61fcfaed901e57911eec
                    }
                });
            }
        });
    }

<<<<<<< HEAD

    @Override
    public int getItemCount() {
        return carts.size();
=======
    @Override
    public int getItemCount() {
        return (carts != null) ? carts.size() : 0; // Trả về 0 nếu carts null
>>>>>>> c52494fe6a3d80d0a4fe61fcfaed901e57911eec
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTotalPrice, tvPrice, tvProductName, tvCount;
        ImageView ivImage, ivPlus, ivMinus;
<<<<<<< HEAD

        ConstraintLayout layout_delete;
=======
        ConstraintLayout layout_delete;

>>>>>>> c52494fe6a3d80d0a4fe61fcfaed901e57911eec
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            layout_delete = itemView.findViewById(R.id.layout_delete);
            tvTotalPrice = itemView.findViewById(R.id.tvTotalPrice);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            tvProductName = itemView.findViewById(R.id.tvProductName);
            tvCount = itemView.findViewById(R.id.tvCount);
            ivImage = itemView.findViewById(R.id.ivImage);
            ivPlus = itemView.findViewById(R.id.ivPlus);
            ivMinus = itemView.findViewById(R.id.ivMinus);
        }
    }
<<<<<<< HEAD
}
=======
}
>>>>>>> c52494fe6a3d80d0a4fe61fcfaed901e57911eec
