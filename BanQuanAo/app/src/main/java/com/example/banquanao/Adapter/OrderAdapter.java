package com.example.banquanao.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.banquanao.Model.SaleHistory;
import com.example.banquanao.R;
import java.util.ArrayList;
import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder> {

    private List<SaleHistory> orders;
    private OnOrderActionListener onOrderActionListener;

    public interface OnOrderActionListener {
        void onAcceptClick(SaleHistory order);
        void onDispatchClick(SaleHistory order);
    }

    public OrderAdapter(List<SaleHistory> orders, OnOrderActionListener listener) {
        this.orders = orders != null ? orders : new ArrayList<>();
        this.onOrderActionListener = listener;
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_order, parent, false);
        return new OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        SaleHistory order = orders.get(position);

        // Set the placeholder image (since you're not handling image URL yet)
        holder.imageOrder.setImageResource(R.drawable.food_placeholder);

        // Set the customer name (use a placeholder since you're not handling it yet)
        holder.textCustomerName.setText("NAME Customer");

        // Keep the commented-out section as is
//        if (order.getImageUrl() != null && !order.getImageUrl().isEmpty()) {
//            Glide.with(holder.itemView.getContext())
//                    .load(order.getImageUrl())
//                    .placeholder(R.drawable.food_placeholder)
//                    .error(R.drawable.food_placeholder)
//                    .into(holder.imageOrder);
//        } else {
//            holder.imageOrder.setImageResource(R.drawable.food_placeholder);
//        }
//
//        holder.textCustomerName.setText(order.getCustomerName() != null ? "NAME " + order.getCustomerName() : "NAME Customer");

        // Set the quantity
        holder.textQuantity.setText("QTY " + order.getTotalSold());

        holder.buttonAccept.setOnClickListener(v -> {
            if (onOrderActionListener != null) {
                onOrderActionListener.onAcceptClick(order);
            }
        });

        holder.buttonDispatch.setOnClickListener(v -> {
            if (onOrderActionListener != null) {
                onOrderActionListener.onDispatchClick(order);
            }
        });
    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

    public void updateOrders(List<SaleHistory> newOrders) {
        this.orders.clear();
        this.orders.addAll(newOrders != null ? newOrders : new ArrayList<>());
        notifyDataSetChanged();
    }

    static class OrderViewHolder extends RecyclerView.ViewHolder {
        ImageView imageOrder;
        TextView textCustomerName;
        TextView textQuantity;
        Button buttonAccept;
        Button buttonDispatch;

        OrderViewHolder(@NonNull View itemView) {
            super(itemView);
            imageOrder = itemView.findViewById(R.id.imageOrder);
            textCustomerName = itemView.findViewById(R.id.textCustomerName);
            textQuantity = itemView.findViewById(R.id.textQuantity);
            buttonAccept = itemView.findViewById(R.id.buttonAccept);
            buttonDispatch = itemView.findViewById(R.id.buttonDispatch);
        }
    }
}