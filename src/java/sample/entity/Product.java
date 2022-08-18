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
public class Product {
    private String productID;
    private String name;
    private String image;
    private double price;
    private int quantity;
    private String categoryID;

    public Product() {
    }

    public Product(String productID, String name, String image, double price, int quantity, String categoryID) {
        this.productID = productID;
        this.name = name;
        this.image = image;
        this.price = price;
        this.quantity = quantity;
        this.categoryID = categoryID;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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

    public String getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(String categoryID) {
        this.categoryID = categoryID;
    }

    @Override
    public String toString() {
        return "Product{" + "productID=" + productID + ", name=" + name + ", image=" + image + ", price=" + price + ", quantity=" + quantity + ", categoryID=" + categoryID + '}';
    }
    
   
    
}
