package com.prem.p_cart.Activities.Home_Screen;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.database.FirebaseDatabase;
import com.hishd.tinycart.model.Cart;
import com.hishd.tinycart.util.TinyCartHelper;
import com.prem.p_cart.Activities.features.Cart_Activity;
import com.prem.p_cart.Activities.features.CheckOut_Activity;
import com.prem.p_cart.Models.Category;
import com.prem.p_cart.Models.ProductItem;
import com.prem.p_cart.R;
import com.prem.p_cart.Utilities.BasicConstant;
import com.prem.p_cart.Utilities.LoadImage;
import com.prem.p_cart.databinding.ActivityProductDetailBinding;

import org.json.JSONException;
import org.json.JSONObject;

public class Product_Detail_Activity extends AppCompatActivity {

    ActivityProductDetailBinding binding;
    ProductItem currProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProductDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // toolbar
        binding.toolbarProductDetail.setNavigationOnClickListener(view -> onBackPressed());

//      ============================= Change the Color of Status bar =============================
        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(getColor(R.color.wincolor));

        String name = getIntent().getStringExtra("name");
        String image = getIntent().getStringExtra("icon");
        int price = getIntent().getIntExtra("price", 0);
        int id = getIntent().getIntExtra("id", 0);
        String description = getIntent().getStringExtra("name");

        new LoadImage().glideLoadImage(this, image, binding.productImage);
        getProductDetail(id);

        // handel cart
        Cart cart = TinyCartHelper.getCart();
        binding.addToCartBtn.setOnClickListener(view ->
                cart.addItem(currProduct, 1)
        );

        // toolbar
        binding.toolbarProductDetail.setNavigationOnClickListener(view -> onBackPressed());
        binding.toolbarProductDetail.setOnMenuItemClickListener(item -> {
            int id1 = item.getItemId();
            if (id1 == R.id.cart_toolbar) {
                startActivity(new Intent(getBaseContext(), Cart_Activity.class));
            }
            return true;
        });

        binding.BuyNow.setOnClickListener(view ->{
                        cart.addItem(currProduct, 1);
                startActivity(new Intent(this, CheckOut_Activity.class));
        });
    }

    private void getProductDetail(int id) {
        RequestQueue queue = Volley.newRequestQueue(this);

        String url = BasicConstant.GET_PRODUCT_DETAILS_URL + id;

        StringRequest request = new StringRequest(Request.Method.GET, url, response -> {
            try {
                JSONObject object = new JSONObject(response);

                if (object.getString("status").equals("success")) {
                    JSONObject product = object.getJSONObject("product");
                    String description = product.getString("description");

                    binding.descriptionTv.setText(
                            Html.fromHtml(description)
                    );

                    currProduct = new ProductItem(
                            product.getString("name"),
                            BasicConstant.PRODUCTS_IMAGE_URL + product.getString("image"),
                            product.getDouble("id"),
                            product.getInt("price"),
                            product.getInt("id"),
                            product.getString("price_discount"));

                    binding.productNameTv.setText(currProduct.getName());
                    binding.priceTv.setText(currProduct.getPrice());
                    binding.ratingBar.setRating((float) ((currProduct.getRating()/1000)+3));

                }
            } catch (
                    JSONException e) {
                throw new RuntimeException(e);
            }
        }, error -> {

        });

        queue.add(request);
    }
}