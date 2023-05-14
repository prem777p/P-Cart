package com.prem.p_cart.Activities.features;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.prem.p_cart.Adapters.NotificationAdapter;
import com.prem.p_cart.R;
import com.prem.p_cart.databinding.ActivityNortificationBinding;


public class Notification_Activity extends AppCompatActivity {

    ActivityNortificationBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNortificationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


//        ============================= Change the Color of Status bar =============================
        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(getColor(R.color.wincolor));

        //set adapter
        NotificationAdapter notificationAdapter = new NotificationAdapter(1);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        binding.rViewNotification.setLayoutManager(linearLayoutManager);
        binding.rViewNotification.setAdapter(notificationAdapter);

        // toolbar
        binding.toolbarNotification.setNavigationOnClickListener(view -> onBackPressed());
    }
}