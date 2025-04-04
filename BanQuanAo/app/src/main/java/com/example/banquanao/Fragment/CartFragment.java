package com.example.banquanao.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.banquanao.R;

public class CartFragment extends Fragment {
    private TextView txtQuantity;
    private Button btnIncrease, btnDecrease;
    private int quantity = 1;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_cart, container, false);

    }


}
