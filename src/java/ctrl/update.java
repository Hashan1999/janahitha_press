/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ctrl;

import hibernate.SellingItems;
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

/**
 *
 * @author Batzy
 */
public class update extends HttpServlet {

    
 

    
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        System.out.println("wadaaaaaa");
        Session session = NewHibernateUtil.getSessionFactory().openSession();
		      SellingItems s = new SellingItems();
                      s.setIditem(Integer.parseInt(request.getParameter("id")));
                      s.setName(request.getParameter("name"));
                     
                      s.setPrice(Double.valueOf(request.getParameter("price")));
		session.beginTransaction();
		session.saveOrUpdate(s);
		session.getTransaction().commit();
		session.close();
                
               
                
                SessionFactory sf2 = NewHibernateUtil.getSessionFactory();
        Session s2 =sf2.openSession();
                Criteria c = s2.createCriteria(SellingItems.class);
       
        c.addOrder(Order.asc("iditem"));
        request.setAttribute("sList", (List<SellingItems>) c.list());
                
                request.getRequestDispatcher("table.jsp").forward(request, response);
        
       
    }

  

}
