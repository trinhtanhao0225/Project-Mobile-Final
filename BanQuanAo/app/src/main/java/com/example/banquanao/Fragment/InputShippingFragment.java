package com.example.banquanao.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;

import com.example.banquanao.R;

public class InputShippingFragment extends Fragment {

    private EditText edtName, edtPhone, edtAddress;
    private Button btnSaveInfo;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_input_shipping, container, false);

        ImageButton btnBack = rootView.findViewById(R.id.btnBack);
        edtName = rootView.findViewById(R.id.edtName);
        edtPhone = rootView.findViewById(R.id.edtPhone);
        edtAddress = rootView.findViewById(R.id.edtAddress);
        btnSaveInfo = rootView.findViewById(R.id.btnSaveInfo);

        btnBack.setOnClickListener(v -> {
            requireActivity().getSupportFragmentManager().popBackStack();
        });

        btnSaveInfo.setOnClickListener(v -> {
            Bundle result = new Bundle();
            result.putString("name", edtName.getText().toString());
            result.putString("phone", edtPhone.getText().toString());
            result.putString("address", edtAddress.getText().toString());

            // Gửi dữ liệu cho Fragment A
            getParentFragmentManager().setFragmentResult("shipping_info", result);

            // Quay lại Fragment trước
            requireActivity().getSupportFragmentManager().popBackStack();
        });

        return rootView;
    }
}
