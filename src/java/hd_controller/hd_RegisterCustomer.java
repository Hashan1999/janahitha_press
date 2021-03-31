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
import java.io.IOException;
import java.io.PrintWriter;
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
 * @author HASHAN
 */
@WebServlet(name = "hd_RegisterCustomer", urlPatterns = {"/hd_RegisterCustomer"})
public class hd_RegisterCustomer extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ResponseMsg msg=new ResponseMsg(0, "Customer Registration Failed!", null);
        
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
            
            String firstName="";
            String lastName="";
            String contact="";
            String description="";
            String id="";
            boolean validateData=false;
            
            Enumeration<String> parameterNames = request.getParameterNames();

            while (parameterNames.hasMoreElements()) {
                String paramName = parameterNames.nextElement();
                
                if (paramName.equalsIgnoreCase("firstName")) {
                    firstName =request.getParameter("firstName");
                } else if (paramName.equalsIgnoreCase("lastName")) {
                    lastName = request.getParameter("lastName");
                }else if (paramName.equalsIgnoreCase("contact")) {
                    contact = request.getParameter("contact");
                }else if (paramName.equalsIgnoreCase("description")) {
                    description = request.getParameter("description");
                }else if (paramName.equalsIgnoreCase("id")) {
                    id = request.getParameter("id");
                }
            }
            
            
            if(!firstName.equals("")){
            
            if(!lastName.equals("")){
            
            if(!contact.equals("")){
            
              
                
            if(hd_model.hd_validation.getValidatedMobile(contact) != ""){
            
               validateData=true; 
                
            }else{
           
            msg=new ResponseMsg(0, "Invalid Contact Number!", null);
            }
            }else{
            msg=new ResponseMsg(0, "Enter Contact Number!", null);
            
            }
            }else{
            msg=new ResponseMsg(0, "Enter Last Name!", null);
            
            }
            }else{
            msg=new ResponseMsg(0, "Enter First Name!", null);
            
            }
            
            
            
            if (validateData) {
           if(id == ""){
           
                transaction=hibernateSession.beginTransaction();
                Customer newCustomer=new Customer();
                newCustomer.setContact(contact);
                newCustomer.setFirstName(firstName);
                newCustomer.setLastName(lastName);
                newCustomer.setDescription(description);
            
               hibernateSession.save(newCustomer);
                
               transaction.commit();
               hibernateSession.flush();
               hibernateSession.close();
               
                  JsonObject customerJsonObject=new JsonObject();
                  customerJsonObject.addProperty("id", newCustomer.getIdcustomer());
                  customerJsonObject.addProperty("firstName", firstName);
                  customerJsonObject.addProperty("lastName", lastName);
                  customerJsonObject.addProperty("contact", hd_validation.getValidatedMobile(contact));
                  customerJsonObject.addProperty("description", description);
                  
                  msg=new ResponseMsg(1, "Succesfully Updated", customerJsonObject);
            }else{
         
            
          
            if(hd_validation.isNumericInt(id)){
            
                Criteria customerCriteria =hibernateSession.createCriteria(Customer.class).add(Restrictions.eq("idcustomer", Integer.parseInt(id)));
                Customer customer=(Customer) customerCriteria.uniqueResult();
                if(customer != null){
                    transaction=hibernateSession.beginTransaction();
               customer.setFirstName(firstName);
               customer.setLastName(lastName);
               customer.setContact(hd_validation.getValidatedMobile(contact));
               customer.setDescription(description);
                    
                hibernateSession.update(customer);
                
               transaction.commit();
               hibernateSession.flush();
               hibernateSession.close();
               
                JsonObject customerJsonObject=new JsonObject();
                  customerJsonObject.addProperty("id", customer.getIdcustomer());
                  customerJsonObject.addProperty("firstName", firstName);
                  customerJsonObject.addProperty("lastName", lastName);
                  customerJsonObject.addProperty("contact", hd_validation.getValidatedMobile(contact));
                  customerJsonObject.addProperty("description", description);
                    msg=new ResponseMsg(1, "Succesfully Updated!", customerJsonObject); 
                }else{
                    msg=new ResponseMsg(0, "Customer doesn't Exist", null);
                }
            }else{
        msg=new ResponseMsg(0, "Invalid Customer", null);
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
