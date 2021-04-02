/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ctrl;

import hibernate.Brand;
import hibernate.Supplier;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import hibernate.NewHibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;

/**
 *
 * @author Batzy
 */
public class suppInsert extends HttpServlet {

   @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
         System.out.println("brand");
        SessionFactory fac = NewHibernateUtil.getSessionFactory();
        Session session = fac.openSession();
        
         Supplier sI = new Supplier();
        
        sI.setName(request.getParameter("name"));
        sI.setContact(request.getParameter("cont"));
       
      
            session.save(sI);
            Transaction tr = session.beginTransaction();
            tr.commit();
           // response.sendRedirect("sellingItemsManager.jsp");
           
         response.getWriter().write("ok");
        
    }
}
