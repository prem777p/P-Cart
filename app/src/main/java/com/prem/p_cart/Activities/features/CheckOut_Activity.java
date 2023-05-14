package com.prem.p_cart.Activities.features;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.hishd.tinycart.model.Cart;
import com.hishd.tinycart.model.Item;
import com.hishd.tinycart.util.TinyCartHelper;
import com.prem.p_cart.Models.ProductItem;
import com.prem.p_cart.R;
import com.prem.p_cart.Utilities.BasicConstant;
import com.prem.p_cart.databinding.ActivityCheckOutBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class CheckOut_Activity extends AppCompatActivity {

    ActivityCheckOutBinding binding;
    Cart cart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCheckOutBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //      ============================= Change the Color of Status bar =============================
        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(getColor(R.color.wincolor));

        cart = TinyCartHelper.getCart();
        binding.productPriceTv.setText(String.format("₹ %.2f",cart.getTotalPrice()));
        float totalAmount = (float) (cart.getTotalPrice().doubleValue() - ((cart.getTotalPrice().doubleValue() / 100)* 5));
        binding.TotalAmountTv.setText("₹ "+totalAmount);

        // toolbar
        binding.toolbarPricedetail.setNavigationOnClickListener(view -> onBackPressed());


        binding.continuebtn.setOnClickListener(view -> startActivity(new Intent(this, Payment_Activity.class)));

        processOrder();
    }

//    void processOrder(){
//        RequestQueue queue = Volley.newRequestQueue(this);
//
//        JSONObject productOrder = new JSONObject();
//        JSONObject dataObject = new JSONObject();
//        try {
//
//            productOrder.put("address","unKnown");
//            productOrder.put("buyer","unKnown");
//            productOrder.put("comment", "unKnown");
//            productOrder.put("created_at", Calendar.getInstance().getTimeInMillis());
//            productOrder.put("last_update", Calendar.getInstance().getTimeInMillis());
//            productOrder.put("date_ship", Calendar.getInstance().getTimeInMillis());
//            productOrder.put("email", "unKnown");
//            productOrder.put("phone", "unKnown");
//            productOrder.put("serial", "cab8c1a4e4421a3b");
//            productOrder.put("shipping", "");
//            productOrder.put("shipping_location", "");
//            productOrder.put("shipping_rate", "0.0");
//            productOrder.put("status", "WAITING");
//            productOrder.put("tax", "unKnown");
//            productOrder.put("total_fees", "totalPrice");
//
//            JSONArray product_order_detail = new JSONArray();
//            for(Map.Entry<Item, Integer> item : cart.getAllItemsWithQty().entrySet()) {
//                ProductItem product = (ProductItem) item.getKey();
//                int quantity = item.getValue();
//                product.setQuantity(quantity);
//
//                JSONObject productObj = new JSONObject();
//                productObj.put("amount", quantity);
//                productObj.put("price_item", product.getPrice());
//                productObj.put("product_id", product.getId());
//                productObj.put("product_name", product.getName());
//                product_order_detail.put(productObj);
//            }
//
//            dataObject.put("product_order",productOrder);
//            dataObject.put("product_order_detail",product_order_detail);
//
//            Log.e("obk",dataObject.toString());
//
//        }catch (Exception ignored){
//
//        }
//        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, BasicConstant.POST_ORDER_URL, dataObject, response -> {
//            try {
//                if (response.getString("status").equals("success")) {
//                    Toast.makeText(this, "Success order.", Toast.LENGTH_SHORT).show();
//                    String orderNumber = response.getJSONObject("data").getString("orderId");
//                    Intent intent = new Intent();
//                    intent.putExtra("orderCode", orderNumber);
//                    Log.e("kh",orderNumber);
//                }
//                else {
//                    Toast.makeText(this, "Order Failed", Toast.LENGTH_SHORT).show();
//
//                    Log.e("obk",response.toString());
//                }
//            } catch (JSONException e) {
//                throw new RuntimeException(e);
//            }
//        }, error -> {
//
//        }) {
//            @Override
//            public Map<String, String> getHeaders() throws AuthFailureError {
//                Map<String, String> headers = new HashMap<>();
//                headers.put("Security","secure_code");
//                return headers;
//            }
//
//        };
//        queue.add(request);
//    }

    void processOrder() {
        RequestQueue queue = Volley.newRequestQueue(this);

        JSONObject productOrder = new JSONObject();
        JSONObject dataObject = new JSONObject();
        try {

            productOrder.put("address","binding");
            productOrder.put("buyer","binding");
            productOrder.put("comment", "binding");
            productOrder.put("created_at", Calendar.getInstance().getTimeInMillis());
            productOrder.put("last_update", Calendar.getInstance().getTimeInMillis());
            productOrder.put("date_ship", Calendar.getInstance().getTimeInMillis());
            productOrder.put("email", "emailBox");
            productOrder.put("phone", "phoneBox");
            productOrder.put("serial", "cab8c1a4e4421a3b");
            productOrder.put("shipping", "");
            productOrder.put("shipping_location", "");
            productOrder.put("shipping_rate", "0.0");
            productOrder.put("status", "WAITING");
            productOrder.put("tax", "tax");
            productOrder.put("total_fees", "no");

            JSONArray product_order_detail = new JSONArray();
            for(Map.Entry<Item, Integer> item : cart.getAllItemsWithQty().entrySet()) {
                ProductItem product = (ProductItem) item.getKey();
                int quantity = item.getValue();
                product.setQuantity(quantity);

                JSONObject productObj = new JSONObject();
                productObj.put("amount", quantity);
                productObj.put("price_item", product.getPrice());
                productObj.put("product_id", product.getId());
                productObj.put("product_name", product.getName());
                product_order_detail.put(productObj);
            }

            dataObject.put("product_order",productOrder);
            dataObject.put("product_order_detail",product_order_detail);


        } catch (JSONException e) {}

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, BasicConstant.POST_ORDER_URL, dataObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    if (response.getString("status").equals("success")) {
                        Toast.makeText(getBaseContext(), "Success order.", Toast.LENGTH_SHORT).show();
                        String orderNumber = response.getJSONObject("data").getString("code");
                                       Intent intent = new Intent();
                                        intent.putExtra("orderCode", orderNumber);
                                    }
                    else {
                        Toast.makeText(getBaseContext(), "Failed Not Found Api On SERVER", Toast.LENGTH_SHORT).show();
                    }
                    Log.e("res", response.toString());
                } catch (Exception e) {}
            }
         }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                headers.put("Security","secure_code");
                return headers;
            }
        } ;

        queue.add(request);
    }
}