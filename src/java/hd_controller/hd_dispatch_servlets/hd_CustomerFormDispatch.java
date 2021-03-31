/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hd_controller.hd_dispatch_servlets;

import hd_model.hd_validation;
import hibernate.Customer;
import hibernate.NewHibernateUtil;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "hd_CustomerFormDispatch", urlPatterns = {"/hd_CustomerFormDispatch"})
public class hd_CustomerFormDispatch extends HttpServlet {

    
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
            String id=request.getParameter("id");
            
            if(id != null && id != ""){
            if(hd_validation.isNumericInt(id)){
            
                Criteria customerCriteria =hibernateSession.createCriteria(Customer.class).add(Restrictions.eq("idcustomer", Integer.parseInt(id)));
                Customer customer=(Customer) customerCriteria.uniqueResult();
                if(customer != null){
                request.setAttribute("customer", customer);
                request.getRequestDispatcher("hd_dispatch_jsp/hd_customer_Registration.jsp").forward(request, response);
                
                }else{
                  response.getWriter().write(0);
                }
            }else{
            response.getWriter().write(0);
            }
                
                
            }else{
            response.getWriter().write(0);
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
    }

}
