package com.example.fashionstoreapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fashionstoreapp.Model.Order;
import com.example.fashionstoreapp.R;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

<<<<<<< HEAD
public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder>{
=======
public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> {
>>>>>>> c52494fe6a3d80d0a4fe61fcfaed901e57911eec

    List<Order> orders;
    Context context;

    public OrderAdapter(List<Order> orders, Context context) {
        this.orders = orders;
        this.context = context;
    }

    @NonNull
    @Override
    public OrderAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_order, parent, false);
        return new OrderAdapter.ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderAdapter.ViewHolder holder, int position) {
<<<<<<< HEAD
        holder.orderLayout.setBackground(context.getDrawable(R.drawable.order_item_background));
        Order order = orders.get(position);
        if (order.getId()<10)
            holder.tvOderId.setText("Order #0"+order.getId());
        else
            holder.tvOderId.setText("Order #"+order.getId());
        String pattern = "dd-MM-yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        holder.tvPurchaseDay.setText(simpleDateFormat.format(order.getBooking_Date()));
        Locale localeEN = new Locale("en", "EN");
        NumberFormat en = NumberFormat.getInstance(localeEN);
        holder.tvTotalPrice.setText(en.format(order.getTotal()));
        holder.tvQuantity.setText(order.getOrder_Item().size()+"");

        holder.itemView.setOnClickListener(v -> {
            if(holder.orderLayout2.getVisibility() == View.VISIBLE){
                holder.orderLayout.setBackground(context.getDrawable(R.drawable.order_item_background));
                holder.orderLayout1.setBackground(null);
                holder.orderLayout2.setVisibility(View.GONE);
                holder.ivHide.setVisibility(View.GONE);
                holder.ivShowMore.setVisibility(View.VISIBLE);
            }
            else{
                holder.orderLayout.setBackground(null);
                holder.orderLayout1.setBackground(context.getDrawable(R.drawable.order_item_background));
                holder.orderLayout2.setVisibility(View.VISIBLE);
                holder.ivHide.setVisibility(View.VISIBLE);
                holder.ivShowMore.setVisibility(View.GONE);
                if (order.getOrder_Item().size()>1)
                    holder.tvTotalItem.setText(order.getOrder_Item().size()+" Items");
                else
                    holder.tvTotalItem.setText(order.getOrder_Item().size()+" Item");
                holder.tvFullName.setText(order.getFullname());
                holder.tvPhoneNumber.setText(order.getPhone());
                holder.tvAddress.setText(order.getAddress());
                holder.orderLayout2.requestFocus();
            }

        });

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false);
        holder.recyclerViewOrderItem = holder.itemView.findViewById(R.id.recyclerViewOrderItem);
        holder.recyclerViewOrderItem.setLayoutManager(linearLayoutManager);
        holder.orderItemAdapter = new OrderItemAdapter(order.getOrder_Item(), context);
        holder.recyclerViewOrderItem.setAdapter(holder.orderItemAdapter);
=======
        if (orders == null || orders.isEmpty()) {
            return; // Thoát nếu danh sách orders null hoặc rỗng
        }

        Order order = orders.get(position);
        if (order == null) {
            return; // Thoát nếu order null
        }

        // Thiết lập giao diện
        if (holder.orderLayout != null) {
            holder.orderLayout.setBackground(context.getDrawable(R.drawable.order_item_background));
        }

        // Thiết lập ID đơn hàng
        holder.tvOderId.setText(order.getId() < 10 ? "Order #0" + order.getId() : "Order #" + order.getId());

        // Thiết lập ngày mua
        if (order.getBooking_Date() != null) {
            String pattern = "dd-MM-yyyy";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            holder.tvPurchaseDay.setText(simpleDateFormat.format(order.getBooking_Date()));
        } else {
            holder.tvPurchaseDay.setText("N/A");
        }

        // Thiết lập tổng giá
        Locale localeEN = new Locale("en", "EN");
        NumberFormat en = NumberFormat.getInstance(localeEN);
        holder.tvTotalPrice.setText(en.format(order.getTotal()));

        // Thiết lập số lượng
        if (order.getOrder_Item() != null) {
            holder.tvQuantity.setText(String.valueOf(order.getOrder_Item().size()));
        } else {
            holder.tvQuantity.setText("0");
        }

        // Sự kiện nhấn vào item
        holder.itemView.setOnClickListener(v -> {
            if (holder.orderLayout == null || holder.orderLayout1 == null || holder.orderLayout2 == null) {
                return; // Thoát nếu các layout null
            }

            if (holder.orderLayout2.getVisibility() == View.VISIBLE) {
                holder.orderLayout.setBackground(context.getDrawable(R.drawable.order_item_background));
                holder.orderLayout1.setBackground(null);
                holder.orderLayout2.setVisibility(View.GONE);
                if (holder.ivHide != null) {
                    holder.ivHide.setVisibility(View.GONE);
                }
                if (holder.ivShowMore != null) {
                    holder.ivShowMore.setVisibility(View.VISIBLE);
                }
            } else {
                holder.orderLayout.setBackground(null);
                holder.orderLayout1.setBackground(context.getDrawable(R.drawable.order_item_background));
                holder.orderLayout2.setVisibility(View.VISIBLE);
                if (holder.ivHide != null) {
                    holder.ivHide.setVisibility(View.VISIBLE);
                }
                if (holder.ivShowMore != null) {
                    holder.ivShowMore.setVisibility(View.GONE);
                }

                // Thiết lập thông tin chi tiết
                if (order.getOrder_Item() != null) {
                    holder.tvTotalItem.setText(order.getOrder_Item().size() > 1
                            ? order.getOrder_Item().size() + " Items"
                            : order.getOrder_Item().size() + " Item");
                } else {
                    holder.tvTotalItem.setText("0 Items");
                }

                holder.tvFullName.setText(order.getFullname() != null ? order.getFullname() : "N/A");
                holder.tvPhoneNumber.setText(order.getPhone() != null ? order.getPhone() : "N/A");
                holder.tvAddress.setText(order.getAddress() != null ? order.getAddress() : "N/A");

                holder.orderLayout2.requestFocus();
            }
        });

        // Thiết lập RecyclerView cho OrderItem
        if (holder.recyclerViewOrderItem != null && order.getOrder_Item() != null) {
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
            holder.recyclerViewOrderItem.setLayoutManager(linearLayoutManager);
            holder.orderItemAdapter = new OrderItemAdapter(order.getOrder_Item(), context);
            holder.recyclerViewOrderItem.setAdapter(holder.orderItemAdapter);
        }
>>>>>>> c52494fe6a3d80d0a4fe61fcfaed901e57911eec
    }

    @Override
    public int getItemCount() {
<<<<<<< HEAD
        return orders.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvOderId, tvPurchaseDay, tvQuantity,tvTotalPrice, tvTotalItem, tvFullName, tvPhoneNumber, tvAddress;
=======
        return (orders != null) ? orders.size() : 0; // Trả về 0 nếu orders null
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvOderId, tvPurchaseDay, tvQuantity, tvTotalPrice, tvTotalItem, tvFullName, tvPhoneNumber, tvAddress;
>>>>>>> c52494fe6a3d80d0a4fe61fcfaed901e57911eec
        ImageView ivShowMore, ivHide;
        ConstraintLayout orderLayout, orderLayout1, orderLayout2;
        RecyclerView.Adapter orderItemAdapter;
        RecyclerView recyclerViewOrderItem;
<<<<<<< HEAD
=======

>>>>>>> c52494fe6a3d80d0a4fe61fcfaed901e57911eec
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvOderId = itemView.findViewById(R.id.tvOderId);
            tvPurchaseDay = itemView.findViewById(R.id.tvPurchaseDay);
            tvQuantity = itemView.findViewById(R.id.tvQuantity);
            tvTotalPrice = itemView.findViewById(R.id.tvTotalPrice);
            ivShowMore = itemView.findViewById(R.id.ivShowMore);
            orderLayout = itemView.findViewById(R.id.orderLayout);
            ivHide = itemView.findViewById(R.id.ivHide);
            orderLayout1 = itemView.findViewById(R.id.orderLayout1);
            orderLayout2 = itemView.findViewById(R.id.orderLayout2);
            tvTotalItem = itemView.findViewById(R.id.tvTotalItem);
            tvFullName = itemView.findViewById(R.id.tvFullName);
<<<<<<< HEAD
            tvPhoneNumber =itemView.findViewById(R.id.tvPhoneNumber);
            tvAddress =itemView.findViewById(R.id.tvAddress);
        }
    }
}
=======
            tvPhoneNumber = itemView.findViewById(R.id.tvPhoneNumber);
            tvAddress = itemView.findViewById(R.id.tvAddress);
            recyclerViewOrderItem = itemView.findViewById(R.id.recyclerViewOrderItem);
        }
    }
}
>>>>>>> c52494fe6a3d80d0a4fe61fcfaed901e57911eec
