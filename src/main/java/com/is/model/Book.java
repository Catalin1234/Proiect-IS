package com.is.model;

/**
 * Created by catal on 1/18/2017.
 */
public class Book {

    private String name;
    private int bookId;
    private String author;
    private float price;

    public Book(int bookId, String name, String author, float price){
        this.bookId  = bookId;
        this.name = name;
        this.author = author;
        this.price = price;
    }

    public Book(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                ", bookId=" + bookId +
                ", author='" + author + '\'' +
                ", price=" + price +
                '}';
    }
}
