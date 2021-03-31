/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hd_bean;

/**
 *
 * @author HASHAN
 */
public class OrderItemBean {

    public OrderItemBean(int itemId, int itemQty, String itemName, double itemUnitPrice, String itemDescription) {
        this.itemId = itemId;
        this.itemQty = itemQty;
        this.itemName = itemName;
        this.itemUnitPrice = itemUnitPrice;
        this.itemDescription = itemDescription;
    }

    public OrderItemBean() {
    }

  
    private int itemId;
    private int itemQty;
    private String itemName;
    private double itemUnitPrice;
private String itemDescription;

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getItemQty() {
        return itemQty;
    }

    public void setItemQty(int itemQty) {
        this.itemQty = itemQty;
    }

    public double getItemUnitPrice() {
        return itemUnitPrice;
    }

    public void setItemUnitPrice(double itemUnitPrice) {
        this.itemUnitPrice = itemUnitPrice;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
}
