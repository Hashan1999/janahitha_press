/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hd_controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
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
@WebServlet(name = "hd_LoadSellingItems", urlPatterns = {"/hd_LoadSellingItems"})
public class hd_LoadSellingItems extends HttpServlet {
  @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
        SessionFactory sessionFactory=null;
        try {
            sessionFactory=NewHibernateUtil.getSessionFactory();
        } catch (Throwable e) {
        e.printStackTrace();
        }
        
        if(sessionFactory != null){
            Session hibernateSession=sessionFactory.openSession();
        try {
          
            
          
            
                Criteria sellingItemCriteria =hibernateSession.createCriteria(SellingItems.class);
                List<SellingItems> sellingItemList=sellingItemCriteria.list();
                
                JsonObject sellingItemJsonObject=new JsonObject();
                for (SellingItems sellingItem : sellingItemList) {
                  sellingItemJsonObject.addProperty(sellingItem.getIditem().toString(),sellingItem.getIditem().toString()+"-"+sellingItem.getName());
                
            }
              
               response.getWriter().write(new Gson().toJson(sellingItemJsonObject));
                
           
            
            
        } catch (Exception e) {
        e.printStackTrace();
        }finally{
        if(hibernateSession.isOpen()){
        hibernateSession.flush();
        hibernateSession.close();
        }
        }
        }
    }

}
