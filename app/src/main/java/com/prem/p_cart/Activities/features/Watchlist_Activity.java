package com.prem.p_cart.Activities.features;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import com.prem.p_cart.R;
import com.prem.p_cart.databinding.ActivityWatchlistBinding;


public class Watchlist_Activity extends AppCompatActivity {

    ActivityWatchlistBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityWatchlistBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

//        ============================= Change the Color of Status bar =============================
        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(getColor(R.color.wincolor));

        // toolbar
        binding.toolbarWatchlist.setNavigationOnClickListener(view -> onBackPressed());

    }
}