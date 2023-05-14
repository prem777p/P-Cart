package com.prem.p_cart.Activities.features;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import com.prem.p_cart.R;
import com.prem.p_cart.Utilities.BasicConstant;
import com.prem.p_cart.databinding.ActivityPaymentBinding;

public class Payment_Activity extends AppCompatActivity {

    ActivityPaymentBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPaymentBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //      ============================= Change the Color of Status bar =============================
        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(getColor(R.color.wincolor));

        String ordercode = "";
//        String ordercode = getIntent().getStringExtra("orderId");
        binding.webview.setMixedContentAllowed(true);
        binding.webview.loadUrl(BasicConstant.PAYMENT_URL + ordercode);

        // toolbar
        binding.toolbarPayment.setNavigationOnClickListener(view -> onBackPressed());

    }
}