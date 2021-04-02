/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ctrl;

import hibernate.Brand;
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
public class brandDelete extends HttpServlet {

     protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       System.out.println("delete"); 
//        SessionFactory sf2 = NewHibernateUtil.getSessionFactory();
//        Session s2 =sf2.openSession();
//        Transaction transaction = null;
//        
//        String id = request.getParameter("id");
//        
//        
//        
//        transaction = s2.getTransaction();
//        transaction.begin();
//      
//        
//         
//        SellingItems sItem= new SellingItems();
//        sItem.setIditem(Integer.parseInt(id));
//        s2.delete(sItem);
//        transaction.commit();
//        
//
//        Criteria c2 = s2.createCriteria(SellingItems.class);
//       
//        c2.addOrder(Order.asc("iditem"));
//        request.setAttribute("sList", (List<SellingItems>) c2.list());
//        
//        request.getRequestDispatcher("table.jsp").forward(request, response);


Session session = NewHibernateUtil.getSessionFactory().openSession();
		       Brand s = new Brand();
                      s.setIdbrand(Integer.parseInt(request.getParameter("id")));
                      
		session.beginTransaction();
		session.delete(s);
		session.getTransaction().commit();
		session.close();
                
              
             SessionFactory sf2 = NewHibernateUtil.getSessionFactory();
        Session s2 =sf2.openSession();
        
    

        Criteria c = s2.createCriteria(Brand.class);
       
        c.addOrder(Order.asc("idbrand"));
        request.setAttribute("sList", (List<Brand>) c.list());
        
        
        
        request.getRequestDispatcher("table2.jsp").forward(request, response);
    }


}
