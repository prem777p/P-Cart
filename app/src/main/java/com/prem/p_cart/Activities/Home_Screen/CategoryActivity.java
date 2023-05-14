package com.prem.p_cart.Activities.Home_Screen;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.prem.p_cart.Activities.features.Cart_Activity;
import com.prem.p_cart.Adapters.SetItemAdapter;
import com.prem.p_cart.Models.ProductItem;
import com.prem.p_cart.R;
import com.prem.p_cart.Utilities.BasicConstant;
import com.prem.p_cart.databinding.ActivityCategoryBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CategoryActivity extends AppCompatActivity {

    ActivityCategoryBinding binding;
    ArrayList<ProductItem> productItems;
    SetItemAdapter itemAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        binding = ActivityCategoryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // toolbar
        binding.toolbarCategory.setNavigationOnClickListener(view -> onBackPressed());
        binding.toolbarCategory.setOnMenuItemClickListener(item -> {
            int id1 = item.getItemId();
            if (id1 == R.id.cart_toolbar) {
                startActivity(new Intent(getBaseContext(), Cart_Activity.class));
            }
            return true;
        });

        //      ============================= Change the Color of Status bar =============================
        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(getColor(R.color.wincolor));

        productItems = new ArrayList<>();
        int catId = getIntent().getIntExtra("catId",1);
        getProduct(catId);


        // set adapter
        itemAdapter = new SetItemAdapter(this, productItems);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);
        binding.categoryRc.setLayoutManager(gridLayoutManager);
        binding.categoryRc.setAdapter(itemAdapter);

    }

    private void getProduct(int id) {
        RequestQueue queue = Volley.newRequestQueue(this);

        String url = BasicConstant.GET_PRODUCTS_URL + "?category_id"+id;
        StringRequest request = new StringRequest(Request.Method.GET, url, response -> {
            try {
                JSONObject object = new JSONObject(response);

                if (object.getString("status").equals("success")){
                    JSONArray productArray = object.getJSONArray("products");
                    Log.e("size", String.valueOf(productArray.length()));
                    for(int i=0; i<productArray.length(); i++){
                        JSONObject subObject = productArray.getJSONObject(i);
                        productItems.add(new ProductItem(
                                subObject.getString("name"),
                                BasicConstant.PRODUCTS_IMAGE_URL+subObject.getString("image"),
                                subObject.getDouble("id"),
                                subObject.getInt("price"),
                                subObject.getInt("id"),
                                subObject.getString("price_discount")
                        ));
                    }
                    itemAdapter.notifyDataSetChanged();
                }
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        }, error -> { });

        queue.add(request);
    }

}