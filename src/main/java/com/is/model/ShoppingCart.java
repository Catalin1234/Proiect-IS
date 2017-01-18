package com.is.model;

/**
 * Created by catal on 1/18/2017.
 */
public class ShoppingCart {

    private int cartId;
    private int bookId;
    private int userId;

    public ShoppingCart(){

    }
    public ShoppingCart(int cartId, int bookId, int userId){
        this.cartId = cartId;
        this.userId = userId;
        this.bookId = bookId;
    }

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "ShoppingCart{" +
                "cartId=" + cartId +
                ", bookId=" + bookId +
                ", userId=" + userId +
                '}';
    }
}
