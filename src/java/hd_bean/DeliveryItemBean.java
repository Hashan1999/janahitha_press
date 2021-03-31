/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hd_bean;

import hibernate.OrderItem;

/**
 *
 * @author HASHA
 */
public class DeliveryItemBean {

    public DeliveryItemBean() {
    }

    public DeliveryItemBean(OrderItem orderItem, int deliveredQty, int toBeDeliveredQty, int orderItemId, int qty) {
        this.orderItem = orderItem;
        this.deliveredQty = deliveredQty;
        this.toBeDeliveredQty = toBeDeliveredQty;
        this.orderItemId = orderItemId;
        this.qty = qty;
    }




    private OrderItem orderItem;
    private int deliveredQty;
    private int toBeDeliveredQty;
    private int orderItemId;
    private int qty;

    public int getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(int orderItemId) {
        this.orderItemId = orderItemId;
    }

    public OrderItem getOrderItem() {
        return orderItem;
    }

    public void setOrderItem(OrderItem orderItem) {
        this.orderItem = orderItem;
    }

    public int getDeliveredQty() {
        return deliveredQty;
    }

    public void setDeliveredQty(int deliveredQty) {
        this.deliveredQty = deliveredQty;
    }

    public int getToBeDeliveredQty() {
        return toBeDeliveredQty;
    }

    public void setToBeDeliveredQty(int toBeDeliveredQty) {
        this.toBeDeliveredQty = toBeDeliveredQty;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }
}
