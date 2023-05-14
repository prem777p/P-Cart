package com.prem.p_cart.Activities.features;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.prem.p_cart.R;
import com.prem.p_cart.databinding.ActivityAddressCheckoutBinding;

public class AddressCheckout_Activity extends AppCompatActivity {

    ActivityAddressCheckoutBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddressCheckoutBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //      ============================= Change the Color of Status bar =============================
        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(getColor(R.color.wincolor));


        // toolbar
        binding.toolbarAddressCheckout.setNavigationOnClickListener(view -> onBackPressed());


    }
}