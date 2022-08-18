/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.shopping;

import java.util.HashMap;
import java.util.Map;
import sample.entity.Product;

/**
 *
 * @author VietDang
 */
public class Cart {
 private Map<String, Product> cart;

    public Cart() {
    }

    public Cart(Map<String, Product> cart) {
        this.cart = cart;
    }

    public Map<String, Product> getCart() {
        return cart;
    }

    public void setCart(Map<String, Product> cart) {
        this.cart = cart;
    }
    public boolean add(Product tea){
        boolean check = false;
        if(this.cart == null){
            this.cart = new HashMap<>();
        }
        if(this.cart.containsKey(tea.getProductID())){
            int currentQuantity = this.cart.get(tea.getProductID()).getQuantity();
            tea.setQuantity(currentQuantity + tea.getQuantity());
        }
        this.cart.put(tea.getProductID(), tea);
        check = true;
        return check;
    }
    public boolean remove(String id){
        boolean check = false;
        if(this.cart != null){
            if(this.cart.containsKey(id)){
                this.cart.remove(id);
                check = true;
            }
        }
        return check;
    }
    public boolean edit(String id, Product tea){
        boolean check = false;
        if(this.cart != null){
            if(this.cart.containsKey(id)){
                this.cart.replace(id, tea);
                check = true;
            }
        }
        return check;
    }   
}
