package com.fuful.domain;

/**
 * Created by SunRuiBin on 2019/11/16.
 */
public class OrderProductDetail {
//     map.put("count", rs.getInt("count"));
//                map.put("subtotal", rs.getString("subtotal"));
//                map.put("picture", rs.getString("picture"));
//                map.put("bookName", rs.getString("bookName"));
//                map.put("price", rs.getDouble("price"));
    private int count;
    private double subtotal;
    private String picture;
    private String bookName;
    private Double price;

    public int getCount() {
        return count;
    }



    public void setCount(int count) {
        this.count = count;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
