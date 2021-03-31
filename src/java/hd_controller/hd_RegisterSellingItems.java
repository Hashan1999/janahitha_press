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
import hibernate.Customer;
import hibernate.NewHibernateUtil;
import hibernate.SellingItems;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author HASHA
 */
@WebServlet(name = "hd_RegisterSellingItems", urlPatterns = {"/hd_RegisterSellingItems"})
public class hd_RegisterSellingItems extends HttpServlet {

  @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ResponseMsg msg=new ResponseMsg(0, "Selling Item Registration Failed!", null);
        
        SessionFactory sessionFactory=null;
        try {
            sessionFactory=NewHibernateUtil.getSessionFactory();
        } catch (Throwable e) {
        e.printStackTrace();
        }
        
        if(sessionFactory != null){
            Session hibernateSession=sessionFactory.openSession();
            Transaction transaction=null;
        
        try {
            
            String name="";
            String price="";
      
            String id="";
            boolean validateData=false;
            
            Enumeration<String> parameterNames = request.getParameterNames();

            while (parameterNames.hasMoreElements()) {
                String paramName = parameterNames.nextElement();
                
                if (paramName.equalsIgnoreCase("name")) {
                    name =request.getParameter("name");
                } else if (paramName.equalsIgnoreCase("price")) {
                    price = request.getParameter("price");
                }else if (paramName.equalsIgnoreCase("id")) {
                    id = request.getParameter("id");
                }
            }
            
            
         
            
            if(!name.equals("")){
            
            if(!price.equals("")){
            
              
                
            if(hd_model.hd_validation.isNumericDouble(price)){
            
               validateData=true; 
                
            }else{
           
            msg=new ResponseMsg(0, "Invalid Price!", null);
            }
            }else{
            msg=new ResponseMsg(0, "Enter Price!", null);
            
            }
            }else{
            msg=new ResponseMsg(0, "Enter Name!", null);
            
            }
           
            
            
            
            if (validateData) {
           if(id == ""){
           
                transaction=hibernateSession.beginTransaction();
                SellingItems item=new SellingItems();
                
                Date date=new Date();
                
                item.setName(name);
                item.setPrice(Double.valueOf(price));
                item.setDate(date);
                hibernateSession.save(item);
                
               transaction.commit();
               hibernateSession.flush();
               hibernateSession.close();
               
                  JsonObject sellingItemJsonObject=new JsonObject();
                  sellingItemJsonObject.addProperty("id", item.getIditem());
                  sellingItemJsonObject.addProperty("name",name);
                  sellingItemJsonObject.addProperty("price",price);
                  sellingItemJsonObject.addProperty("date",new SimpleDateFormat("YYYY/mm/dd").format(date));
                 
                  msg=new ResponseMsg(1, "Succesfully Updated", sellingItemJsonObject);
            }else{
         
            
          
            if(hd_validation.isNumericInt(id)){
            
                Criteria sellingItemCriteria =hibernateSession.createCriteria(SellingItems.class).add(Restrictions.eq("iditem", Integer.parseInt(id)));
                SellingItems sellingItem=(SellingItems) sellingItemCriteria.uniqueResult();
                if(sellingItem != null){
                    transaction=hibernateSession.beginTransaction();
                Date date=new Date();
                
                sellingItem.setName(name);
                sellingItem.setPrice(Double.valueOf(price));
                sellingItem.setDate(date);;
                    
                hibernateSession.update(sellingItem);
                
               transaction.commit();
               hibernateSession.flush();
               hibernateSession.close();
               
               JsonObject sellingItemJsonObject=new JsonObject();
                  sellingItemJsonObject.addProperty("id", sellingItem.getIditem());
                  sellingItemJsonObject.addProperty("name",name);
                  sellingItemJsonObject.addProperty("price",price);
                  sellingItemJsonObject.addProperty("date",new SimpleDateFormat("YYYY/MM/dd").format(date));
                    msg=new ResponseMsg(1, "Succesfully Updated!", sellingItemJsonObject); 
                }else{
                    msg=new ResponseMsg(0, "Selling Item Doesn't Exist", null);
                }
            }else{
        msg=new ResponseMsg(0, "Invalid Item", null);
            }
                
                
            }
           
           
           
            }
               
            
        } catch (Exception e) {
            if(transaction != null){
            transaction.rollback();
            }
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
