package com.is.dao.implementation;

import com.is.dao.SCDao;
import com.is.dao.mapper.ShoppingCartRowMapper;
import com.is.dao.mapper.UserRowMapper;
import com.is.model.Book;
import com.is.model.ShoppingCart;
import com.is.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

/**
 * Created by catal on 1/18/2017.
 */
@Repository
public class DefaultSCDao implements SCDao {

    @Autowired
    @Qualifier("dataSource")
    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public DataSource getDataSource() {
        return this.dataSource;
    }

    public List<ShoppingCart> getShoppingCart(){
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        List<ShoppingCart> cart = jdbcTemplate.query("select * from shoppingCart", new ShoppingCartRowMapper());
        return cart;
    }

    @Override
    public void addToCart(ShoppingCart cart){
        String sql = "INSERT INTO shoppingCart "
                + "(cartId, bookId, userId) VALUES (?, ?, ?)";
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.update(sql,
                new Object[]{cart.getCartId(),cart.getBookId(), cart.getUserId() });
    }



}
