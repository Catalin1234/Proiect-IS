package com.is.services;

import com.is.dao.implementation.DefaultBookDao;
import com.is.dao.implementation.DefaultSCDao;
import com.is.dao.implementation.DefaultUserDao;
import com.is.model.ShoppingCart;
import com.is.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by blackghost on 1/18/2017.
 */
@Service
public class HomeService {
    @Autowired
    private DefaultUserDao defaultUserDao;

    @Autowired
    private DefaultBookDao defaultBookDao;

    @Autowired
    private DefaultSCDao defaultSCDao;

    public void setDefaultUserDao(DefaultUserDao defaultUserDao) {
        this.defaultUserDao = defaultUserDao;
    }
    public void setDefaultBookDao(DefaultBookDao defaultBookDao) {
       this.defaultBookDao = defaultBookDao;
    }

    public void DefaultSCDao(DefaultSCDao defaultSCDao) {
        this.defaultSCDao = defaultSCDao;
    }

   public DefaultBookDao getBooks(){
        return defaultBookDao;
    }

    public DefaultSCDao getCart(){
        return defaultSCDao;
    }

    public void addToCart( int bookId, int userId ){
        int id = getCartId();
        ShoppingCart cart = new ShoppingCart(id, bookId, userId);
        defaultSCDao.addToCart(cart);
    }

    public int getCartId(){
        List<ShoppingCart> cart = defaultSCDao.getShoppingCart();
        int min = 1;
        if(cart.size()>0) {
            int max = cart.get(cart.size() - 1).getCartId();
            for (ShoppingCart u : cart) {
                if (min != u.getCartId()) return min;
                min++;
            }
            return max + 1;
        } else {
            return 1;
        }
    }
}
