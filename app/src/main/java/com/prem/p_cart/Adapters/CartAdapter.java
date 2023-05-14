package com.prem.p_cart.Adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hishd.tinycart.model.Cart;
import com.hishd.tinycart.model.Item;
import com.hishd.tinycart.util.TinyCartHelper;
import com.prem.p_cart.Models.CartListener;
import com.prem.p_cart.Models.ProductItem;
import com.prem.p_cart.R;
import com.prem.p_cart.Utilities.LoadImage;
import com.prem.p_cart.databinding.CartItemBinding;
import com.prem.p_cart.databinding.QuantityDialogBinding;

import java.util.ArrayList;
import java.util.Map;


public class CartAdapter extends RecyclerView.Adapter<CartAdapter.Holder> {
    Context context;
    ArrayList<ProductItem> productItems;
    CartListener cartListener;
    Cart cart;

    public CartAdapter(Context context, ArrayList<ProductItem> productItems) {
        this.context = context;
        this.productItems = productItems;
        cart = TinyCartHelper.getCart();
    }

    public void setOnItemClickedListener(CartListener cartListener){
        this.cartListener = cartListener;
    }

    @NonNull
    @Override
    public CartAdapter.Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.cart_item,viewGroup,false);

        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartAdapter.Holder holder, int i) {
        ProductItem productItem = productItems.get(i);
        new LoadImage().glideLoadImage(context, productItem.getIcon(), holder.binding.cartIv);
        holder.binding.ProductNameTv.setText(productItem.getName());
        holder.binding.PriceTv.setText(productItem.getPrice());
        holder.binding.setQuantityBtn.setOnClickListener(view -> {
            PopupMenu popupMenu = new PopupMenu(context, holder.binding.setQuantityBtn);
            popupMenu.getMenuInflater().inflate(R.menu.change_cart_item_quantity, popupMenu.getMenu());
            popupMenu.setOnMenuItemClickListener(menuItem -> {
                int id = menuItem.getItemId();
                switch (id){
                    case R.id.increaseQuantity_2:
                        productItem.setQuantity(2);
                        holder.binding.setQuantityBtn.setText("QTY: "+"2");
                        notifyDataSetChanged();
                        cart.updateItem(productItem, productItem.getQuantity());
                        cartListener.onQuantityChanged();
                        break;

                    case R.id.increaseQuantity_3:
                        productItem.setQuantity(3);
                        holder.binding.setQuantityBtn.setText("QTY: "+"3");
                        notifyDataSetChanged();
                        cart.updateItem(productItem, productItem.getQuantity());
                        cartListener.onQuantityChanged();
                        break;

                    case R.id.increaseQuantity_4:
                        productItem.setQuantity(4);
                        holder.binding.setQuantityBtn.setText("QTY: "+"4");
                        notifyDataSetChanged();
                        cart.updateItem(productItem, productItem.getQuantity());
                        cartListener.onQuantityChanged();
                        break;

                    case R.id.increaseQuantity_MoreQuantity:
                        QuantityDialogBinding quantityDialogBinding = QuantityDialogBinding.inflate(LayoutInflater.from(context));
                         AlertDialog alertDialog = new AlertDialog.Builder(context)
                                .setView(quantityDialogBinding.getRoot())
                                .create();
                         alertDialog.show();

                         quantityDialogBinding.quantityBtn.setOnClickListener(view1 -> {
                             String quantity = quantityDialogBinding.quantityTv.getText().toString();
                             alertDialog.dismiss();
                                if( !quantity.equals("") ){

                                    holder.binding.setQuantityBtn.setText("QTY: "+quantity);
                                    productItem.setQuantity(Integer.parseInt(quantity));
                                    notifyDataSetChanged();
                                    cart.updateItem(productItem, productItem.getQuantity());
                                    cartListener.onQuantityChanged();
                                }
                         });
                         break;
                }
                return true;
            });
            popupMenu.show();
        });

        holder.binding.removeBtn.setOnClickListener(view ->
            {
                cart.removeItem(productItem);
                cartListener.onItemDelete(i);
            });
    }

    @Override
    public int getItemCount() {
        return productItems.size();
    }

    public static class Holder extends RecyclerView.ViewHolder {
        CartItemBinding binding;
        public Holder(@NonNull View itemView) {
            super(itemView);
            binding = CartItemBinding.bind(itemView);
        }
    }
}
