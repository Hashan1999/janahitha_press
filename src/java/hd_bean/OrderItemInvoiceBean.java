/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hd_bean;

/**
 *
 * @author HASHA
 */
public class OrderItemInvoiceBean {

    public OrderItemInvoiceBean(int id, String product, double unit_price, int qty) {
        this.id = id;
        this.product = product;
        this.unit_price = unit_price;
        this.qty = qty;
    }
 


    private int id;
    private String product;
    
    private double unit_price;
    private int qty;
    


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }


    public double getUnit_price() {
        return unit_price;
    }

    public void setUnit_price(double unit_price) {
        this.unit_price = unit_price;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }


}
