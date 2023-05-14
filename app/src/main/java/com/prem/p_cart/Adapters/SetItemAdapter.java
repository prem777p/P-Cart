package com.prem.p_cart.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.prem.p_cart.Activities.Home_Screen.Product_Detail_Activity;
import com.prem.p_cart.Models.ProductItem;
import com.prem.p_cart.R;
import com.prem.p_cart.Utilities.LoadImage;
import com.prem.p_cart.databinding.HomeItemLayoutBinding;

import java.util.ArrayList;

public class SetItemAdapter extends RecyclerView.Adapter<SetItemAdapter.ViewHolder> {

    Context context;
    ArrayList<ProductItem> productItems;

    public SetItemAdapter(Context context, ArrayList<ProductItem> productItems) {
        this.context = context;
        this.productItems = productItems;
    }

    @NonNull
    @Override
    public SetItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.home_item_layout,viewGroup,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SetItemAdapter.ViewHolder viewHolder, int i) {
        ProductItem item = productItems.get(i);
        new LoadImage().glideLoadImage(context,item.getIcon(),viewHolder.binding.productIv);
        viewHolder.binding.priceTv.setText(item.getPrice());
        viewHolder.binding.itemDescptionTv.setText(item.getName());
        viewHolder.binding.itemReatingRB.setRating((float)item.getRating());

        viewHolder.binding.productItemCard.setOnClickListener(view -> {
            Intent intent = new Intent(context, Product_Detail_Activity.class);
            intent.putExtra("name", item.getName());
            intent.putExtra("icon", item.getIcon());
            intent.putExtra("price", item.getPrice());
            intent.putExtra("id", item.getId());
            intent.putExtra("description", item.getDescription());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return productItems.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        HomeItemLayoutBinding binding;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = HomeItemLayoutBinding.bind(itemView);
        }
    }
}
