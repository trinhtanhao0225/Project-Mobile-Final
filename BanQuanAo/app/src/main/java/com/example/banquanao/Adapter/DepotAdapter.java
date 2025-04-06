package com.example.banquanao.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.banquanao.AllItemsActivity;
import com.example.banquanao.Model.ColorQuantity;
import com.example.banquanao.Model.Depot;
import com.example.banquanao.R;
import java.math.BigDecimal;
import java.util.List;

public class DepotAdapter extends RecyclerView.Adapter<DepotAdapter.DepotViewHolder> {
    private List<Depot> depotList;
    private AllItemsActivity activity; // Để gọi API hoặc hiển thị Toast

    private OnItemClickListener listener; // Interface cho sự kiện click

    // Interface để xử lý click
    public interface OnItemClickListener {
        void onItemClick(Depot depot, int position);
    }
    public DepotAdapter(AllItemsActivity activity, List<Depot> depotList,OnItemClickListener listener) {
        this.activity = activity;
        this.depotList = depotList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public DepotViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_menu_admin, parent, false);
        return new DepotViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DepotViewHolder holder, int position) {
        Depot depot = depotList.get(position);

        // Hiển thị tên
        holder.tvItemName.setText(depot.getName());

        // Tính giá sau discount
        BigDecimal price = depot.getPrice();
        holder.tvItemPrice.setText(String.format("$%.2f", price));

        // Tính tổng quantity từ stock (List<ColorQuantity>)
        int totalQuantity = calculateTotalQuantity(depot.getStock());
        holder.tvQuantity.setText(String.valueOf(totalQuantity));

        // Xử lý nút Increase
        holder.btnIncrease.setOnClickListener(v -> {
            // TODO: Cần chọn size và color cụ thể để tăng quantity
            // Gọi API để cập nhật stock
            int newQty = totalQuantity + 1;
            holder.tvQuantity.setText(String.valueOf(newQty));
        });

        // Xử lý nút Decrease
        holder.btnDecrease.setOnClickListener(v -> {
            // TODO: Cần chọn size và color cụ thể để giảm quantity
            // Gọi API để cập nhật stock
            int newQty = Math.max(0, totalQuantity - 1);
            holder.tvQuantity.setText(String.valueOf(newQty));
        });

        // Xử lý nút Delete
        holder.ivDelete.setOnClickListener(v -> {
            // Gọi API để xóa Depot
            deleteDepot(depot.getId(), position);
        });

        // TODO: Load ảnh từ depot.getImage() bằng Glide/Picasso
        // Ví dụ với Glide:
        // Glide.with(activity)
        //     .load(depot.getImage())
        //     .placeholder(R.drawable.placeholder)
        //     .into(holder.ivItemImage);

        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onItemClick(depot, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return depotList.size();
    }

    // Tính tổng quantity từ stock (List<ColorQuantity>)
    private int calculateTotalQuantity(List<ColorQuantity> stock) {
        if (stock == null || stock.isEmpty()) return 0;
        int total = 0;
        for (ColorQuantity cq : stock) {
            total += cq.getQuantity();
        }
        return total;
    }

    // Gọi API để xóa Depot
    private void deleteDepot(int depotId, int position) {
        // TODO: Gọi API xóa Depot
        // Dùng Retrofit hoặc Volley để gửi DELETE request đến server
        // Ví dụ với Retrofit:
        /*
        DepotApi apiService = ApiClient.getRetrofitInstance().create(DepotApi.class);
        Call<Void> call = apiService.deleteDepot(depotId);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    depotList.remove(position);
                    notifyItemRemoved(position);
                    notifyItemRangeChanged(position, depotList.size());
                    Toast.makeText(activity, "Depot deleted", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(activity, "Failed to delete: " + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(activity, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        */

        // Tạm thời xóa cục bộ để demo
        depotList.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, depotList.size());
    }

    static class DepotViewHolder extends RecyclerView.ViewHolder {
        ImageView ivItemImage, ivDelete;
        TextView tvItemName, tvItemPrice, tvQuantity;
        Button btnDecrease, btnIncrease;

        public DepotViewHolder(@NonNull View itemView) {
            super(itemView);
            ivItemImage = itemView.findViewById(R.id.ivItemImage);
            tvItemName = itemView.findViewById(R.id.tvItemName);
            tvItemPrice = itemView.findViewById(R.id.tvItemPrice);
            tvQuantity = itemView.findViewById(R.id.tvQuantity);
            btnDecrease = itemView.findViewById(R.id.btnDecrease);
            btnIncrease = itemView.findViewById(R.id.btnIncrease);
            ivDelete = itemView.findViewById(R.id.ivDelete);
        }
    }
}