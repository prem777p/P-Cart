package com.prem.p_cart.Activities.Home_Screen;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.prem.p_cart.Activities.Profile.Profile_Activity;
import com.prem.p_cart.Activities.features.Cart_Activity;
import com.prem.p_cart.Activities.features.Notification_Activity;
import com.prem.p_cart.Adapters.SetItemAdapter;
import com.prem.p_cart.Models.Category;
import com.prem.p_cart.Models.ProductItem;
import com.prem.p_cart.R;
import com.prem.p_cart.Utilities.BasicConstant;
import com.prem.p_cart.databinding.ActivitySideNevHomeBinding;

import org.imaginativeworld.whynotimagecarousel.model.CarouselItem;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Side_Nev_Home_Activity extends AppCompatActivity {

    ActivitySideNevHomeBinding binding;
    ArrayList<Category> categories;
    ArrayList<ProductItem> productItems;
    SetItemAdapter itemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySideNevHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        categories = new ArrayList<>();
        productItems = new ArrayList<>();
        getCategories();
        getProduct();
        getBanner();
        //sample data

        categories.add(new Category("fashion", "https://i.seadn.io/gae/OGpebYaykwlc8Tbk-oGxtxuv8HysLYKqw-FurtYql2UBd_q_-ENAwDY82PkbNB68aTkCINn6tOhpA8pF5SAewC2auZ_44Q77PcOo870?auto=format&w=1000", "#18ab4e", "some description", 1));
        categories.add(new Category("fashion", "https://i.seadn.io/gae/OGpebYaykwlc8Tbk-oGxtxuv8HysLYKqw-FurtYql2UBd_q_-ENAwDY82PkbNB68aTkCINn6tOhpA8pF5SAewC2auZ_44Q77PcOo870?auto=format&w=1000", "#18ab4e", "some description", 1));
        categories.add(new Category("fashion", "https://i.seadn.io/gae/OGpebYaykwlc8Tbk-oGxtxuv8HysLYKqw-FurtYql2UBd_q_-ENAwDY82PkbNB68aTkCINn6tOhpA8pF5SAewC2auZ_44Q77PcOo870?auto=format&w=1000", "#18ab4e", "some description", 1));
        categories.add(new Category("fashion", "https://i.seadn.io/gae/OGpebYaykwlc8Tbk-oGxtxuv8HysLYKqw-FurtYql2UBd_q_-ENAwDY82PkbNB68aTkCINn6tOhpA8pF5SAewC2auZ_44Q77PcOo870?auto=format&w=1000", "#18ab4e", "some description", 1));

        //        ============================= Change the Color of Status bar =============================
        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(getColor(R.color.SplashScreen));


        // ================================= change color of icon and text of Navigation drawer ===============================
        binding.navigationViewHm.setItemIconTintList(ColorStateList.valueOf(getColor(R.color.login_text_or)));
        binding.navigationViewHm.setItemTextColor(ColorStateList.valueOf(getColor(R.color.login_text_or)));

        binding.navigationViewHm.setNavigationItemSelectedListener(menuItem -> {
            int id = menuItem.getItemId();
            switch (id) {
                case R.id.offer_menu_sbar:

                case R.id.order_menu_sbar:

                case R.id.coupon_menu_sbar:

                case R.id.nortification_menu_sbar:
                    startActivity(new Intent(this, Notification_Activity.class));
                    binding.drawerLayout.closeDrawer(GravityCompat.START);
                    break;

                case R.id.cart_menu_sbar:
                    startActivity(new Intent(this, Cart_Activity.class));
                    binding.drawerLayout.closeDrawer(GravityCompat.START);
                    break;

                case R.id.account_menu_sbar:
                    startActivity(new Intent(this, Profile_Activity.class));
                    binding.drawerLayout.closeDrawer(GravityCompat.START);
                    break;

            }
            return true;
        });


        // call hm layout method ===================
        hmLayout();
    }

    private void getBanner() {
        RequestQueue queue = Volley.newRequestQueue(this);

        StringRequest request = new StringRequest(Request.Method.GET, BasicConstant.GET_OFFERS_URL, response -> {
            try {
                JSONObject object = new JSONObject(response);

                if (object.getString("status").equals("success")){
                    JSONArray bannerArray = object.getJSONArray("news_infos");

                    for(int i=0; i<bannerArray.length(); i++){
                        JSONObject subObject = bannerArray.getJSONObject(i);
                        // bind banner comes from server
//                        binding.hmLayout.bannerContainer.addData(new CarouselItem(
//                                BasicConstant.GET_OFFERS_URL + subObject.getString("image")
//                        ));

                    }
                    // temp binding
                    binding.hmLayout.bannerContainer.addData(new CarouselItem("https://static.vecteezy.com/system/resources/previews/002/453/548/original/sale-discount-banner-template-promotion-illustration-free-vector.jpg"));
                    binding.hmLayout.bannerContainer.addData(new CarouselItem("https://d1csarkz8obe9u.cloudfront.net/posterpreviews/modern-black-friday-sale-facebook-banner-template-design-b294cd4a4197410381b03b41d8d6e2cb_screen.jpg?ts=1561415608"));
                    binding.hmLayout.bannerContainer.addData(new CarouselItem(R.drawable.banner_home));
                    binding.hmLayout.bannerContainer.addData(new CarouselItem("https://media.istockphoto.com/id/1198469116/vector/online-shopping-promotional-sale-banner-with-full-shopping-cart-and-smartphone.jpg?s=612x612&w=0&k=20&c=tMa1D8QpaDjJgyQW-XdzIaVFkMqEwgkaj3JlY0UyDJY="));
                }
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        }, error -> { });

        queue.add(request);
    }

    private void getProduct() {
        RequestQueue queue = Volley.newRequestQueue(this);

        String url = BasicConstant.GET_PRODUCTS_URL + "?count=50";
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

    private void getCategories() {
        RequestQueue queue = Volley.newRequestQueue(this);

        StringRequest request = new StringRequest(Request.Method.GET, BasicConstant.GET_CATEGORIES_URL, response -> {
            try {
                JSONObject mainObj = new JSONObject(response);

                if (mainObj.getString("status").equals("success")){
                    JSONArray categoryArray = mainObj.getJSONArray("categories");

                    for(int i=0; i<categoryArray.length(); i++){
                        JSONObject object = categoryArray.getJSONObject(i);
                        categories.add(new Category(
                                object.getString("name"),
                                BasicConstant.CATEGORIES_IMAGE_URL + object.getString("icon"),
                                object.getString("color"),
                                object.getString("brief"),
                                object.getInt("id")
                        ));

                    }
                }
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        }, error -> { });

        queue.add(request);
    }

    //============== Close the Navigation =========================================
    @Override
    public void onBackPressed() {
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            moveTaskToBack(true);
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(1);
        }
    }

    // ============================ hm layout ===========================================
    private void hmLayout() {
        //  ------------------------ Set Adapter on Recycler View --------------------------------------
        itemAdapter = new SetItemAdapter(this, productItems);
        //  ------------------------ Linear Layout Manager on Recycler View --------------------------------------
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        binding.hmLayout.recylerViewHome.setLayoutManager(linearLayoutManager);
        binding.hmLayout.recylerViewHome.setAdapter(itemAdapter);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        binding.hmLayout.recylerViewHome2.setLayoutManager(gridLayoutManager);
        binding.hmLayout.recylerViewHome2.setAdapter(itemAdapter);

        binding.hmLayout.sideNev.setOnClickListener(view ->
                binding.drawerLayout.openDrawer(GravityCompat.START)
        );


        // ==========================  bind data comes form server category item ========================
//        LoadImage image = new LoadImage();
//       if(categories.size()>0){
//           Category category = categories.get(0);
//           image.glideLoadImage(this, category.getIcon(), binding.hmLayout.category2Iv);
//           binding.hmLayout.category2Iv.setBorderColor(Color.parseColor(category.getColor()));
//           binding.hmLayout.category2Tv.setText(category.getName());
    binding.hmLayout.category.setOnClickListener(view -> startActivity(new Intent(this, CategoryActivity.class)));
    binding.hmLayout.category2Iv.setOnClickListener(view -> startActivity(new Intent(this, CategoryActivity.class)));

//           category = categories.get(1);
//           image.glideLoadImage(this, category.getIcon(), binding.hmLayout.category3Iv);
//           binding.hmLayout.category3Iv.setBorderColor(Color.parseColor(category.getColor()));
//           binding.hmLayout.category3Tv.setText(category.getName());
    binding.hmLayout.category3Iv.setOnClickListener(view -> startActivity(new Intent(this, CategoryActivity.class)));

//           category = categories.get(2);
//           image.glideLoadImage(this, category.getIcon(), binding.hmLayout.category4Iv);
//           binding.hmLayout.category4Iv.setBorderColor(Color.parseColor(category.getColor()));
//           binding.hmLayout.category4Tv.setText(category.getName());
        binding.hmLayout.category4Iv.setOnClickListener(view -> startActivity(new Intent(this, CategoryActivity.class)));

//
//           category = categories.get(3);
//           image.glideLoadImage(this, category.getIcon(), binding.hmLayout.category5Iv);
//           binding.hmLayout.category5Iv.setBorderColor(Color.parseColor(category.getColor()));
//           binding.hmLayout.category5Tv.setText(category.getName());
        binding.hmLayout.category5Iv.setOnClickListener(view -> startActivity(new Intent(this, CategoryActivity.class)));

//       }

        // ==========================  button click ================================

        binding.hmLayout.cartBtn.setOnClickListener(view -> startActivity(new Intent(this, Cart_Activity.class)));

        binding.hmLayout.nortificationBtn.setOnClickListener(view -> startActivity(new Intent(this, Notification_Activity.class)));
    }

}