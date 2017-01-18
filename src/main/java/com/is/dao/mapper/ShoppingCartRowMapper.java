package com.is.dao.mapper;

import com.is.model.ShoppingCart;
import com.is.model.User;


import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 * Created by catal on 1/18/2017.
 */
public class ShoppingCartRowMapper implements RowMapper<ShoppingCart>{

    @Override
    public ShoppingCart mapRow(ResultSet rs, int rowNum) throws SQLException {
        ShoppingCart s = new ShoppingCart();
        s.setCartId(rs.getInt(1));
        s.setBookId(rs.getInt(2));
        s.setUserId(rs.getInt(3));
        return s;
    }

}
