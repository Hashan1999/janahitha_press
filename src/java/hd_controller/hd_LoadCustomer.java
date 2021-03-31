/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hd_controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import hd_model.hd_validation;
import hibernate.Customer;
import hibernate.NewHibernateUtil;
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
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author HASHAN
 */
@WebServlet(name = "hd_LoadCustomer", urlPatterns = {"/hd_LoadCustomer"})
public class hd_LoadCustomer extends HttpServlet {

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
          
            
          
            
                Criteria customerCriteria =hibernateSession.createCriteria(Customer.class);
                List<Customer> customerList=customerCriteria.list();
                
                JsonObject customerJsonObject=new JsonObject();
                for (Customer customer : customerList) {
                  customerJsonObject.addProperty(customer.getIdcustomer().toString(), customer.getFirstName()+" "+customer.getLastName()+" ["+customer.getContact()+"]");
                
            }
              
               response.getWriter().write(new Gson().toJson(customerJsonObject));
                
           
            
            
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
