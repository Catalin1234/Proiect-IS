package com.is.dao.mapper;

import com.is.model.Book;
import com.is.model.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by catal on 1/18/2017.
 */
public class BookRowMapper implements RowMapper<Book> {

    @Override
    public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
        Book book = new Book();
        book.setBookId(rs.getInt(1));
        book.setName(rs.getString(2));
        book.setAuthor(rs.getString(3));
        book.setPrice(rs.getFloat(4));
        return book;
    }

}
