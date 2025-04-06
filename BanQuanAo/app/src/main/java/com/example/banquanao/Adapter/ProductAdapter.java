//package com.example.banquanao.Adapter;
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.TextView;
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//import com.bumptech.glide.Glide;
//import com.example.banquanao.Model.Depot;
//import com.example.banquanao.R;
//
//import java.util.List;
//
//public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {
//    private Context context;
//    private List<Depot> productList;
//
//    public ProductAdapter(Context context, List<Depot> productList) {
//        this.context = context;
//        this.productList = productList;
//    }
//
//    @NonNull
//    @Override
//    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(context).inflate(R.layout.item_product, parent, false);
//        return new ProductViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
//        Depot product = productList.get(position);
//        holder.tvProductName.setText(product.getName());
//        holder.tvProductPrice.setText(product.getPrice() + " VNĐ");
//
//        // Load ảnh bằng Glide
//        Glide.with(context).load(product.getImageUrl()).into(holder.imgProduct);
//    }
//
//    @Override
//    public int getItemCount() {
//        return productList.size();
//    }
//
//    public static class ProductViewHolder extends RecyclerView.ViewHolder {
//        ImageView imgProduct;
//        TextView tvProductName, tvProductPrice;
//
//        public ProductViewHolder(@NonNull View itemView) {
//            super(itemView);
//            imgProduct = itemView.findViewById(R.id.imgProduct);
//            tvProductName = itemView.findViewById(R.id.tvProductName);
//            tvProductPrice = itemView.findViewById(R.id.tvProductPrice);
//        }
//    }
//}
