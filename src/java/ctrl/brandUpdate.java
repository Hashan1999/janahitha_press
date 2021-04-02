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
public class brandUpdate extends HttpServlet {

     @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        
		       Brand s = new Brand();
                      s.setIdbrand(Integer.parseInt(request.getParameter("id")));
                      s.setName(request.getParameter("name"));
                    
                   
		session.beginTransaction();
		session.saveOrUpdate(s);
		session.getTransaction().commit();
		session.close();
                
               
                
         
             SessionFactory sf2 = NewHibernateUtil.getSessionFactory();
        Session s2 =sf2.openSession();
        
    

        Criteria c = s2.createCriteria(Brand.class);
       
        c.addOrder(Order.asc("idbrand"));
        request.setAttribute("sList", (List<Brand>) c.list());
        
//        for(SellingItems s : (List<SellingItems>) c.list()){
//            System.out.println(s.getIditem());
//        
//        }
//        
        
        
        request.getRequestDispatcher("table2.jsp").forward(request, response);
       
    }

}
