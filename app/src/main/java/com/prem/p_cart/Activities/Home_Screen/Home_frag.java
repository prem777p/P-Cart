package com.prem.p_cart.Activities.Home_Screen;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.prem.p_cart.R;


public class Home_frag extends Fragment {


//     FragmentHomeFragBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_frag, container, false);
//
//        binding = FragmentHomeFragBinding.bind(view);
//
//        binding.cartBtn.setOnClickListener(view1 -> {
//            Toast.makeText(getContext(), "lkjg", Toast.LENGTH_SHORT).show();
//            startActivity(new Intent(getContext(), Cart_Activity.class));
//        });
//
//        binding.nortificationBtn.setOnClickListener(view2 -> {startActivity(new Intent(getContext(), Notification_Activity.class));
//            Toast.makeText(getContext(), "jhg", Toast.LENGTH_SHORT).show();
//        });
//
//        //  ------------------------ Set Adapter on Recycler View --------------------------------------
//        SetItemAdapter itemAdapter = new SetItemAdapter();
//        //  ------------------------ Linear Layout Manager on Recycler View --------------------------------------
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
//        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
//        binding.recylerViewHome.setLayoutManager(linearLayoutManager);
//        binding.recylerViewHome.setAdapter(itemAdapter);
//
//        binding.recylerViewHome2.setLayoutManager(linearLayoutManager2);
//        binding.recylerViewHome2.setAdapter(itemAdapter);


        return view;
    }

}