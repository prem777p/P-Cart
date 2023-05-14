package com.prem.p_cart.Activities.initial_Screen;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.prem.p_cart.Activities.Home_Screen.Side_Nev_Home_Activity;
import com.prem.p_cart.R;
import com.prem.p_cart.Utilities.SessionManager;
import com.prem.p_cart.databinding.ActivityInitialBinding;


public class Initial_Activity extends AppCompatActivity {

    private ActivityInitialBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityInitialBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

//        ============================= Change the Color of Status bar =============================
        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(getColor(R.color.SplashScreen));


        //------------------ set Splash Screen on initial screen -------------------------
        getSupportFragmentManager().beginTransaction().add(R.id.initial_act, new SplashScreen_Frag()).commit();

        nextHoldScreen(new Login_Frag());


    }


    // ================= method for attaching login screen ============================================
    public void nextHoldScreen(Fragment fmg){

        new Thread(() -> {

            try {
                Thread.sleep(3000);

                boolean checkPresentData = new SessionManager(this).checkSession();

                if (checkPresentData) {
                    startActivity(new Intent(this, Side_Nev_Home_Activity.class));
                }else {
                    getSupportFragmentManager().beginTransaction().replace(R.id.initial_act, fmg).commit();
                }

            } catch (InterruptedException e) {

                getSupportFragmentManager().beginTransaction().replace(R.id.initial_act, fmg).commit();
            }
        }).start();
    }
}