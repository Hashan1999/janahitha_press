package hibernate;
// Generated Mar 31, 2021 6:04:18 AM by Hibernate Tools 4.3.1



/**
 * OrderItemHasDelivery generated by hbm2java
 */
public class OrderItemHasDelivery  implements java.io.Serializable {


     private Integer deliveryItemId;
     private Delivery delivery;
     private OrderItem orderItem;
     private int qty;

    public OrderItemHasDelivery() {
    }

    public OrderItemHasDelivery(Delivery delivery, OrderItem orderItem, int qty) {
       this.delivery = delivery;
       this.orderItem = orderItem;
       this.qty = qty;
    }
   
    public Integer getDeliveryItemId() {
        return this.deliveryItemId;
    }
    
    public void setDeliveryItemId(Integer deliveryItemId) {
        this.deliveryItemId = deliveryItemId;
    }
    public Delivery getDelivery() {
        return this.delivery;
    }
    
    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
    }
    public OrderItem getOrderItem() {
        return this.orderItem;
    }
    
    public void setOrderItem(OrderItem orderItem) {
        this.orderItem = orderItem;
    }
    public int getQty() {
        return this.qty;
    }
    
    public void setQty(int qty) {
        this.qty = qty;
    }




}


