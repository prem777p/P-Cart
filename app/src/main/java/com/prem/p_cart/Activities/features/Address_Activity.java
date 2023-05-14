package com.prem.p_cart.Activities.features;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.prem.p_cart.Adapters.AddressAdapter;
import com.prem.p_cart.R;
import com.prem.p_cart.databinding.ActivityAddressBinding;


public class Address_Activity extends AppCompatActivity {

    ActivityAddressBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddressBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

//        ============================= Change the Color of Status bar =============================
        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(getColor(R.color.wincolor));

        // toolbar
        binding.toolbarAddress.setNavigationOnClickListener(view -> onBackPressed());

        //set adapter
        AddressAdapter addressAdapter = new AddressAdapter(1);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        binding.rViewAddress.setLayoutManager(linearLayoutManager);
        binding.rViewAddress.setAdapter(addressAdapter);
    }
}