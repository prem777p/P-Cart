package com.prem.p_cart.Activities.Profile;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.prem.p_cart.Activities.features.Address_Activity;
import com.prem.p_cart.Activities.features.Cart_Activity;
import com.prem.p_cart.Activities.features.Notification_Activity;
import com.prem.p_cart.Activities.features.Watchlist_Activity;
import com.prem.p_cart.R;
import com.prem.p_cart.Utilities.SessionManager;
import com.prem.p_cart.databinding.ActivityProfileBinding;

public class Profile_Activity extends AppCompatActivity {

    ActivityProfileBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //============================= Change the Color of Status bar =============================
        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(getResources().getColor(R.color.wincolor));


        // show option menu bar on toolbar
        binding.toolbarProfile.inflateMenu(R.menu.menu_profile_toolbar);

        // switch to edit profile activity
        binding.editProfile.setOnClickListener(view1 -> startActivity(new Intent(this, Edit_Profile_Activity.class)));

        // toolbar
        binding.toolbarProfile.setNavigationOnClickListener(view1 -> onBackPressed());
        binding.toolbarProfile.setOnMenuItemClickListener(item -> {
            int id1 = item.getItemId();
            if (id1 == R.id.cart_toolbar) {
                startActivity(new Intent(getBaseContext(), Cart_Activity.class));
            }
            return false;
        });

        // ===================================================================================
        binding.tvFavoritePfile.setOnClickListener(view1 -> startActivity(new Intent(this, Watchlist_Activity.class)));
        binding.btnFavrate.setOnClickListener(view1 -> startActivity(new Intent(this, Watchlist_Activity.class)));

        // ===================================================================================
        binding.tvCridCouponPfile.setOnClickListener(view1 -> startActivity(new Intent(this, Notification_Activity.class)));
        binding.btnCreditCoupon.setOnClickListener(view1 -> startActivity(new Intent(this, Notification_Activity.class)));

        // ===================================================================================
        binding.tvShipingAddPfile.setOnClickListener(view1 -> startActivity(new Intent(this, Address_Activity.class)));
        binding.btnShipAdd.setOnClickListener(view1 -> startActivity(new Intent(this, Address_Activity.class)));

        // ===================================================================================
        binding.logOutPfile.setOnClickListener(view1 -> new SessionManager(this).logoutSession());
        binding.btnLogOut.setOnClickListener(view1 -> new SessionManager(this).logoutSession());

    }

    // item selected to perform action in it
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        // which item is select
        if (id == R.id.cart_toolbar) {
            Toast.makeText(this, "item2", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }
}