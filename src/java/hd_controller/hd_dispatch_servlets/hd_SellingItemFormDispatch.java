/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hd_controller.hd_dispatch_servlets;

import hd_model.hd_validation;
import hibernate.Customer;
import hibernate.NewHibernateUtil;
import hibernate.SellingItems;
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
 * @author HASHA
 */
@WebServlet(name = "hd_SellingItemFormDispatch", urlPatterns = {"/hd_SellingItemFormDispatch"})
public class hd_SellingItemFormDispatch extends HttpServlet {

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
            
                Criteria sellingItemCriteria =hibernateSession.createCriteria(SellingItems.class).add(Restrictions.eq("iditem", Integer.parseInt(id)));
                SellingItems item=(SellingItems) sellingItemCriteria.uniqueResult();
                if(item != null){
                request.setAttribute("item", item);
                request.getRequestDispatcher("hd_dispatch_jsp/hd_SellingItems_Registration.jsp").forward(request, response);
                
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
