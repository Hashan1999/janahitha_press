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
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author HASHAN
 */
@WebServlet(name = "hd_GetSellingItemForId", urlPatterns = {"/hd_GetSellingItemForId"})
public class hd_GetSellingItemForId extends HttpServlet {

   @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
        ResponseMsg msg=new ResponseMsg(0, "Failed!", null);
        SessionFactory sessionFactory=null;
        try {
            sessionFactory=NewHibernateUtil.getSessionFactory();
        } catch (Throwable e) {
        e.printStackTrace();
        }
        
        if(sessionFactory != null){
            Session hibernateSession=sessionFactory.openSession();
        try {
           String id=request.getParameter("id");
            boolean validateData=false;
           
          if(id != null && !id.trim().isEmpty()){
               if(hd_validation.isNumericInt(id)){
               
                   validateData=true;
                   
               }else{
               
                 msg=new ResponseMsg(0, "Invalid Selling Item!", null);
               }
                    
          }else{
                 msg=new ResponseMsg(0, "Empty Selling Item ID!", null);
          
          }
            
          
          if(validateData){
             SellingItems sellingItem=(SellingItems) hibernateSession.load(SellingItems.class, Integer.parseInt(id));
                 
             
             if(sellingItem != null){
                JsonObject sellingItemJsonObject=new JsonObject();
                
                  sellingItemJsonObject.addProperty("id",sellingItem.getIditem());
                  sellingItemJsonObject.addProperty("name",sellingItem.getName());
                  sellingItemJsonObject.addProperty("price",sellingItem.getPrice());
                 msg=new ResponseMsg(1, "", sellingItemJsonObject);
            
             }else{
             
                 msg=new ResponseMsg(0, "", null);
             }
             
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
       System.out.println(new Gson().toJson(msg));
        response.getWriter().write(new Gson().toJson(msg));
        
        
    }

}
