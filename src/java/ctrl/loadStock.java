/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ctrl;

import hibernate.Stock;
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
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Batzy
 */
public class loadStock extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
         SessionFactory sf2 = NewHibernateUtil.getSessionFactory();
        Session s2 =sf2.openSession();
        
        int id = Integer.parseInt(request.getParameter("id"));

        Criteria c = s2.createCriteria(Stock.class);
        c.add(Restrictions.eq("idstock",id));
      
       Stock s = (Stock) c.uniqueResult();
       
     
      String name = s.getRawItem().getName();
        
         response.getWriter().write(name);
       
        
        
    }
}
