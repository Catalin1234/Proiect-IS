package com.is.dao.implementation;

import com.is.dao.BookDao;
import com.is.dao.mapper.BookRowMapper;
import com.is.dao.mapper.UserRowMapper;
import com.is.model.Book;
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
public class DefaultBookDao implements BookDao{

    @Autowired
    @Qualifier("dataSource")
    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public DataSource getDataSource() {
        return this.dataSource;
    }
    @Override
    public List<Book> getAllBooks(){
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        List<Book> books = jdbcTemplate.query("select * from book", new BookRowMapper());
        return books;
    }
    @Override
    public void deleteBook(int bookId){
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        String SQLCommand = "delete from book where bookId = " + bookId;
        jdbcTemplate.update(SQLCommand);
    }
    @Override
    public void updateBook(Book book){
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.update("UPDATE book SET " + "name = ? , " +
                        "author = ?," +
                        " price = ?, " ,
                book.getName(),
                book.getAuthor(),
                book.getPrice());
    }
    @Override
    public void addBook(Book book){
        String sql = "INSERT INTO book "
                + "(bookId, name, author, price) VALUES (?, ?, ?,?)";
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.update(sql,
                new Object[]{book.getBookId(),book.getName(), book.getAuthor(), book.getPrice(), });
    }

    public int getIdByName(String name){
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        String sqlQuery = "select bookId from book where name = '" + name +"';";
        return jdbcTemplate.queryForObject(sqlQuery, Integer.class);
    }

}
