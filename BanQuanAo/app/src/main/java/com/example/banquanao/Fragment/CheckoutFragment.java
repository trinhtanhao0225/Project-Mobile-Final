package com.example.banquanao.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.fragment.app.FragmentTransaction;

import com.example.banquanao.R;
import android.widget.Button; // thêm nếu chưa có

import com.example.banquanao.ActivityCheckoutSuccess; // thêm nếu chưa import
import com.example.banquanao.VoucherActivity;

public class CheckoutFragment extends Fragment {

    private ImageButton btnBackCheckout;
    private ImageView imgAddAddress, imgMoreVoucher;
    private TextView txtShippingInfo;
    private Button btnPlaceOrder;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_checkout, container, false);

        btnBackCheckout = rootView.findViewById(R.id.btnBack_Cart);
        imgAddAddress = rootView.findViewById(R.id.imgAddAddress);
        txtShippingInfo = rootView.findViewById(R.id.txtShippingInfo); // Thêm TextView này trong layout
        btnPlaceOrder = rootView.findViewById(R.id.btnPlaceOrder);
        imgMoreVoucher = rootView.findViewById(R.id.imgMoreVoucher); // ánh xạ nút voucher

        btnBackCheckout.setOnClickListener(view -> {
            Fragment cartFragment = new CartFragment();
            FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container, cartFragment);
            transaction.addToBackStack(null);
            transaction.commit();
        });

        imgAddAddress.setOnClickListener(v -> {
            Fragment inputShippingFragment = new InputShippingFragment();
            requireActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, inputShippingFragment)
                    .addToBackStack(null)
                    .commit();
        });
        imgMoreVoucher.setOnClickListener(v -> {
            Intent intent = new Intent(requireContext(), VoucherActivity.class);
            startActivity(intent);
        });
        // Lắng nghe dữ liệu gửi từ InputShippingFragment
        getParentFragmentManager().setFragmentResultListener("shipping_info", this,
                new FragmentResultListener() {
                    @Override
                    public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle bundle) {
                        String name = bundle.getString("name");
                        String phone = bundle.getString("phone");
                        String address = bundle.getString("address");

                        txtShippingInfo.setText("Người nhận: " + name + "\nSĐT: " + phone + "\nĐịa chỉ: " + address);
                    }
                });

        btnPlaceOrder.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), ActivityCheckoutSuccess.class);
            startActivity(intent);
        });

        return rootView;
    }
}
