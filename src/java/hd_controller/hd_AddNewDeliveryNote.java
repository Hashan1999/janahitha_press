/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hd_controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import hd_bean.DeliveryItemBean;
import hd_bean.OrderItemBean;
import hd_bean.ResponseMsg;
import hd_model.DeliveryNoteValid;
import hd_model.hd_validation;
import hibernate.Customer;
import hibernate.Delivery;
import hibernate.NewHibernateUtil;
import hibernate.Order;
import hibernate.OrderItem;
import hibernate.OrderItemHasDelivery;
import hibernate.OrderStatus;
import hibernate.SellingItems;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author HASHA
 */
@WebServlet(name = "hd_AddNewDeliveryNote", urlPatterns = {"/hd_AddNewDeliveryNote"})
public class hd_AddNewDeliveryNote extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ResponseMsg msg = new ResponseMsg(0, "Delivery Note Failed!", null);

        SessionFactory sessionFactory = null;

        try {
            sessionFactory = NewHibernateUtil.getSessionFactory();
        } catch (Throwable e) {
            e.printStackTrace();
        }

        if (sessionFactory != null) {
            Session hibernateSession = sessionFactory.openSession();
            Transaction transaction = null;

            try {
                //code 
              
                String deliveryNoteDescription = "";
           

                String orderItemJsonArray = "";


              

                Enumeration<String> parameterNames = request.getParameterNames();

                while (parameterNames.hasMoreElements()) {
                    String paramName = parameterNames.nextElement();

                    if (paramName.equalsIgnoreCase("deliveryNoteDescription")) {
                        deliveryNoteDescription = request.getParameter(paramName);

                    } else if (paramName.equalsIgnoreCase("orderItemJsonArray")) {
                        orderItemJsonArray = request.getParameter(paramName);
                    }
                }

            
                          
                   
            

                    TypeToken token=new TypeToken<ArrayList<DeliveryItemBean>>(){};
                    Type type = token.getType();
                    
                    ArrayList<DeliveryItemBean> jsonOrderItemList= new Gson().fromJson(orderItemJsonArray,type);
                    
                    if(jsonOrderItemList.size() != 0){
                    boolean orderItemsValid=true;
                    ArrayList<OrderItemHasDelivery> orderItemList=new ArrayList<>();
                    double total=0.0;
                    
                    
                    for (DeliveryItemBean orderItemBean : jsonOrderItemList) {
                        OrderItem item=(OrderItem) hibernateSession.load(OrderItem.class,orderItemBean.getOrderItemId());
                        if(item != null && orderItemBean.getQty() != 0){
                            OrderItemHasDelivery  newOrderItem=new OrderItemHasDelivery();
                            newOrderItem.setOrderItem(item);
                            newOrderItem.setQty(orderItemBean.getQty());
                            
                            orderItemList.add(newOrderItem);
                            
                            
                            int availableToDeliver=item.getQty()-DeliveryNoteValid.deliveredQtyForOrderItem(hibernateSession, item);
                        if(orderItemBean.getQty() > availableToDeliver){
                            orderItemsValid=false;
                        msg = new ResponseMsg(0, item.getIdorderItem()+"  Delivery Qty Must Be Lower than Available for Delivery Quantity!!!", null);
                        break;
                        }
                        }else{
                        orderItemsValid=false;
                        msg = new ResponseMsg(0, item.getIdorderItem()+" is a invalid Item!!", null);
                        break;
                        }
                    }
                    
                        if (orderItemsValid) {
                            transaction=hibernateSession.beginTransaction();
                                 Delivery delivery=new Delivery();
                        delivery.setDate(new Date());
                        delivery.setDescription(deliveryNoteDescription);
                                   
                                 hibernateSession.save(delivery);
                                 transaction.commit();
                                 
                                 for (OrderItemHasDelivery orderItem : orderItemList) {
                                orderItem.setDelivery(delivery);
                                    transaction=hibernateSession.beginTransaction();
                                hibernateSession.save(orderItem);
                                     transaction.commit();
                            }
                             
                             msg = new ResponseMsg(1, "Delivery Note Added Succesfully!!"+delivery.getIddeliveryNote(), null);    
                                 
                        
                        }
                    
                    
                    }else{
                     msg = new ResponseMsg(0, "Empty Order Order Items", null);
                    }

            

            } catch (Exception e) {
                msg = new ResponseMsg(0, "Invalid Data found!! check enterd enter Data", null);
                e.printStackTrace();
            } finally {
                if (hibernateSession.isOpen()) {

                    hibernateSession.flush();
                    hibernateSession.close();
                }
            }
        }

        response.getWriter().write(new Gson().toJson(msg));

    }

}
