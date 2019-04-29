package com.iot.android.lesson10;

import org.litepal.annotation.Column;
import org.litepal.crud.LitePalSupport;

/**
 * 图书信息DAO类,继承LitePalSupport类，完成CRUD操作
 * @author Nanrong Zeng
 * @version 1.0
 */
public class Book extends LitePalSupport {
    /**
    * Litepal数据库自动生成的自增的ID
    */
    private long id;
    @Column(unique = true)
    private String name;
    private String author;
    private String press; 
    private float price;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPress() {
        return press;
    }

    public void setPress(String press) {
        this.press = press;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
