package com.example.banquanao.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.banquanao.R;

public class CheckoutFragment extends Fragment {
    private ImageButton btnBackCheckout; // Thêm biến cho nút Back trong CheckoutFragment

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_checkout, container, false);

        // Gán nút Back từ layout
        btnBackCheckout = rootView.findViewById(R.id.btnBack_Cart);

        // Gán sự kiện khi nhấn nút Back
        btnBackCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Quay lại fragment Cart
                Fragment cartFragment = new CartFragment();
                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, cartFragment); // fragment_container là ID trong layout chính
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        return rootView;
    }
}
