package com.example.banquanao.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.banquanao.R;

public class InputShippingFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_input_shipping, container, false);

        ImageButton btnBack = rootView.findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> {
            // Quay lại Fragment trước đó
            requireActivity().getSupportFragmentManager().popBackStack();
        });

        return rootView;
    }
}
