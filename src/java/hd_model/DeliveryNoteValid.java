/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hd_model;

import hd_bean.DeliveryItemBean;
import hibernate.Order;
import hibernate.OrderItem;
import hibernate.OrderItemHasDelivery;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author HASHA
 */
public class DeliveryNoteValid {
 
    public static List<DeliveryItemBean> availableOrderItems(Session session,int orderid){
     
        Order order = (Order) session.load(Order.class, orderid);
        List<OrderItem> itemList=session.createCriteria(OrderItem.class).add(Restrictions.eq("order", order)).list();
        
        ArrayList<DeliveryItemBean> addDeliveryViewList=new ArrayList<>();
        
                for (OrderItem orderItem : itemList) {
        List<OrderItemHasDelivery> deliveryItemList=session.createCriteria(OrderItemHasDelivery.class).add(Restrictions.eq("orderItem", orderItem)).list();
       
            int deliveredQty=0;       
            for (OrderItemHasDelivery orderItemHasDelivery : deliveryItemList) {
                deliveredQty=deliveredQty+orderItemHasDelivery.getQty();
            }
        if(deliveredQty < orderItem.getQty()){
        DeliveryItemBean item=new DeliveryItemBean();
        item.setOrderItem(orderItem);
        item.setDeliveredQty(deliveredQty);
        item.setToBeDeliveredQty(orderItem.getQty()-deliveredQty);
        addDeliveryViewList.add(item);
        }
            
            
        
        }
        
        
        
        return addDeliveryViewList;
    }
    
    public static int deliveredQtyForOrderItem(Session session,OrderItem orderItem){
     
        
       
       
        
        List<OrderItemHasDelivery> deliveryItemList=session.createCriteria(OrderItemHasDelivery.class).add(Restrictions.eq("orderItem", orderItem)).list();
       
            int deliveredQty=0;       
            for (OrderItemHasDelivery orderItemHasDelivery : deliveryItemList) {
                deliveredQty=deliveredQty+orderItemHasDelivery.getQty();
            }
        
            
            
        
       
        
        
        
        return deliveredQty;
    }
}
