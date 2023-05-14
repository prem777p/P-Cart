package com.prem.p_cart.Activities.features;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.hishd.tinycart.model.Cart;
import com.hishd.tinycart.model.Item;
import com.hishd.tinycart.util.TinyCartHelper;
import com.prem.p_cart.Adapters.CartAdapter;
import com.prem.p_cart.Models.CartListener;
import com.prem.p_cart.Models.ProductItem;
import com.prem.p_cart.R;
import com.prem.p_cart.databinding.ActivityCartBinding;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Map;


public class Cart_Activity extends AppCompatActivity {

    ActivityCartBinding binding;
    ArrayList<ProductItem> productItems;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCartBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        productItems = new ArrayList<>();

//        ============================= Change the Color of Status bar =============================
        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(getColor(R.color.wincolor));


        // toolbar
        binding.toolbarCheckout.setNavigationOnClickListener(view -> onBackPressed());



        Cart cart = TinyCartHelper.getCart();
        //set adapter
        CartAdapter cartAdapter = new CartAdapter(this, productItems);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        binding.rViewCart.setLayoutManager(linearLayoutManager);
        binding.rViewCart.setAdapter(cartAdapter);
        cartAdapter.setOnItemClickedListener(new CartListener() {
            @Override
            public void onQuantityChanged() {
                binding.subtotalTv.setText(String.format("₹ %.2f", cart.getTotalPrice()));
            }

            @Override
            public void onItemDelete(int position) {
                productItems.remove(position);
                cartAdapter.notifyItemRemoved(position);
            }
        });



        for (Map.Entry<Item, Integer> item : cart.getAllItemsWithQty().entrySet() ) {
            ProductItem productItem = (ProductItem)  item.getKey();
            int quantity = item.getValue();
            productItem.setQuantity(quantity);

            productItems.add(productItem);
        }
        binding.subtotalTv.setText(String.format("₹ %.2f", cart.getTotalPrice()));


        binding.continuebtn.setOnClickListener(view -> startActivity(new Intent(this, CheckOut_Activity.class)));
    }

}