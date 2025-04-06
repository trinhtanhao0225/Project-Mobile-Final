package com.example.banquanao.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.banquanao.Adapter.CartAdapter;
import com.example.banquanao.R;

import java.util.ArrayList;

public class CartFragment extends Fragment {
    private RecyclerView recyclerView;
    private CartAdapter cartAdapter;
    private Button btnCheckout; // Thêm biến cho nút Checkout



    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_cart, container, false);

        recyclerView = rootView.findViewById(R.id.rcvCart);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Gán adapter cho RecyclerView
        cartAdapter = new CartAdapter(getContext(), new ArrayList<>());
        recyclerView.setAdapter(cartAdapter);

        // Gán nút Checkout từ layout
        btnCheckout = rootView.findViewById(R.id.btnCheckout);

        // Gán sự kiện khi nhấn nút Checkout
        btnCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Chuyển sang fragment thanh toán
                Fragment checkoutFragment = new CheckoutFragment();
                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, checkoutFragment); // fragment_container là ID trong layout chính
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        return rootView;
    }
}
