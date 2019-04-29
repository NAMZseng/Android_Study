package com.iot.android.lesson10;

import org.litepal.crud.LitePalSupport;

/**
 * 图书信息DAO类,继承LitePalSupport类，完成CRUD操作
 * @author Nanrong Zeng
 * @version 1.0
 */
public class Book extends LitePalSupport {
    private int bookId;
    private String author;
    private String press;
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

    public String getpress() {
        return press;
    }

    public void setpress(String press) {
        this.press = press;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
