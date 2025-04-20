package com.example.banquanao.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.banquanao.R;

public class VoucherAdapter extends RecyclerView.Adapter<VoucherAdapter.VoucherViewHolder> {

    private Context context;
    private OnVoucherUseListener listener;

    public VoucherAdapter(Context context, OnVoucherUseListener listener) {
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public VoucherViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_voucher, parent, false);
        return new VoucherViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VoucherViewHolder holder, int position) {
        holder.tvTitle.setText("Giảm 50K");
        holder.tvDescription.setText("Áp dụng cho đơn hàng từ 300K");
        holder.tvCategory.setText("Thời trang");
        holder.tvExpireDate.setText("HSD: 30/06/2025");

        holder.tvUseNow.setOnClickListener(v -> {
            String voucherName = holder.tvCategory.getText().toString();
            if (listener != null) {
                listener.onVoucherUse(voucherName);
            }
        });
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public static class VoucherViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvDescription, tvCategory, tvExpireDate, tvUseNow;

        public VoucherViewHolder(View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvDescription = itemView.findViewById(R.id.tvDescription);
            tvCategory = itemView.findViewById(R.id.tvCategory);
            tvExpireDate = itemView.findViewById(R.id.tvExpireDate);
            tvUseNow = itemView.findViewById(R.id.btnUseNow); // TextView nút "Dùng ngay"
        }
    }

    // Interface định nghĩa bên trong file này
    public interface OnVoucherUseListener {
        void onVoucherUse(String voucherName);
    }
}
