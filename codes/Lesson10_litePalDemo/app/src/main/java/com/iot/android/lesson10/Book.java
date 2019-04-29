package com.iot.android.lesson10;

/**
 * 图书信息DAO类
 * @author Nanrong Zeng
 * @version 1.0
 */
public class Book {
    private int bookId;
    private String author;
    private String  publisher;
    private float price;

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

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
