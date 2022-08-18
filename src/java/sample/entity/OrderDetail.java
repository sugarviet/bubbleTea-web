/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.entity;

/**
 *
 * @author VietDang
 */
public class OrderDetail {
    private int orderDetailID;
    private int orderID;
    private String productID;
    private double price;
    private int quantity;

    public OrderDetail() {
        this.orderDetailID = 0;
        this.orderID = 0;
        this.productID = "";
        this.price = 0;
        this.quantity = 0;
    }

    public OrderDetail(int orderDetailID, int orderID, String productID, double price, int quantity) {
        this.orderDetailID = orderDetailID;
        this.orderID = orderID;
        this.productID = productID;
        this.price = price;
        this.quantity = quantity;
    }

    public int getOrderDetailID() {
        return orderDetailID;
    }

    public void setOrderDetailID(int orderDetailID) {
        this.orderDetailID = orderDetailID;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "OrderDetail{" + "orderDetailID=" + orderDetailID + ", orderID=" + orderID + ", productID=" + productID + ", price=" + price + ", quantity=" + quantity + '}';
    }
    
}
