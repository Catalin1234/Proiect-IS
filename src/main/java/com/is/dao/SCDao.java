package com.is.dao;

import com.is.model.ShoppingCart;

import java.util.List;

/**
 * Created by catal on 1/18/2017.
 */
public interface SCDao {

    List<ShoppingCart> getShoppingCart();
    void addToCart(ShoppingCart cart);

}
