package com.prem.p_cart.Models;

public interface CartListener {
    public void onQuantityChanged();
    public void onItemDelete(int position);
}
