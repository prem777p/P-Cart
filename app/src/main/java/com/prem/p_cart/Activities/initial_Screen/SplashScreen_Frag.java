package com.prem.p_cart.Activities.initial_Screen;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.prem.p_cart.Activities.Home_Screen.Side_Nev_Home_Activity;
import com.prem.p_cart.R;
import com.prem.p_cart.Utilities.SessionManager;


public class SplashScreen_Frag extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_splash_screen, container, false);
    }
}