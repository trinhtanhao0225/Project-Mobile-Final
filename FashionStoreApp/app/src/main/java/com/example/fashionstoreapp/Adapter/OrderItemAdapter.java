package com.example.fashionstoreapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.fashionstoreapp.Model.Order_Item;
import com.example.fashionstoreapp.R;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class OrderItemAdapter extends RecyclerView.Adapter<OrderItemAdapter.ViewHolder> {

    private List<Order_Item> orderItems;
    private Context context;

    public OrderItemAdapter(List<Order_Item> orderItems, Context context) {
        this.context = context;
        this.orderItems = orderItems != null ? orderItems : new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.viewholder_order_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Order_Item orderItem = orderItems.get(position);

        // Load image safely
        if (orderItem.getProduct().getProductImages() != null &&
                !orderItem.getProduct().getProductImages().isEmpty()) {
            Glide.with(context)
                    .load(orderItem.getProduct().getProductImages().get(0).getImageUrl())
                    .into(holder.ivProductImage);
        }

        holder.tvProductName.setText(orderItem.getProduct().getProductName());
        holder.tvPrice.setText(formatPrice(orderItem.getProduct().getPrice()));
        holder.tvUnits.setText(String.valueOf(orderItem.getCount()));
        double total = orderItem.getProduct().getPrice() * orderItem.getCount();
        holder.tvTotalPrice.setText(formatPrice(total));
    }

    @Override
    public int getItemCount() {
        return orderItems.size();
    }

    private String formatPrice(double price) {
        Locale locale = new Locale("en", "EN");
        NumberFormat formatter = NumberFormat.getInstance(locale);
        return formatter.format(price);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvProductName, tvUnits, tvPrice, tvTotalPrice;
        ImageView ivProductImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvProductName = itemView.findViewById(R.id.tvProductName);
            tvUnits = itemView.findViewById(R.id.tvUnits);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            tvTotalPrice = itemView.findViewById(R.id.tvTotalPrice);
            ivProductImage = itemView.findViewById(R.id.ivProductImage);
        }
    }
}
