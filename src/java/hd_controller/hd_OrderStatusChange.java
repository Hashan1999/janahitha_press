/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hd_controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import hd_bean.ResponseMsg;
import hd_model.hd_validation;
import hibernate.NewHibernateUtil;
import hibernate.Order;
import hibernate.OrderStatus;
import hibernate.SellingItems;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author HASHA
 */
@WebServlet(name = "hd_OrderStatusChange", urlPatterns = {"/hd_OrderStatusChange"})
public class hd_OrderStatusChange extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         ResponseMsg msg = new ResponseMsg(0, "Order Status Change Failed!", null);
        SessionFactory sessionFactory=null;
        try {
            sessionFactory=NewHibernateUtil.getSessionFactory();
        } catch (Throwable e) {
        e.printStackTrace();
        }
        
        if(sessionFactory != null){
            Session hibernateSession=sessionFactory.openSession();
        try {
          
            String orderId=request.getParameter("orderid");
            String statusId=request.getParameter("statusid");
            
            System.out.println(orderId);
            System.out.println(statusId);
            
            if(hd_validation.isNumericInt(statusId)){
             if(hd_validation.isNumericInt(orderId)){
            
                 Order order = (Order) hibernateSession.load(Order.class, Integer.parseInt(orderId));
                 OrderStatus orderstatus = (OrderStatus) hibernateSession.load(OrderStatus.class, Integer.parseInt(statusId));
                 
                  if(order != null){
               if(orderstatus != null){
            
                   order.setOrderStatus(orderstatus);
                   
                   Transaction transaction = hibernateSession.beginTransaction();
                   hibernateSession.update(order);
                    transaction.commit();
               hibernateSession.flush();
               hibernateSession.close();
                  msg = new ResponseMsg(1, "Order Status Updated!", null);  
            }else{
            msg = new ResponseMsg(0, "Invalid Status!", null);
            }
               
            }else{
            msg = new ResponseMsg(0, "Order Not Found!", null);
            }
                 
                 
            }else{
            msg = new ResponseMsg(0, "Invalid Order!", null);
            }
            }else{
            msg = new ResponseMsg(0, "Invalid Status!", null);
            }
            
            
                
            
        } catch (Exception e) {
        e.printStackTrace();
        }finally{
        if(hibernateSession.isOpen()){
        hibernateSession.flush();
        hibernateSession.close();
        }
        }
        }
           
        response.getWriter().write(new Gson().toJson(msg));
    }

}
