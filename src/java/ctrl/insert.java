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
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;

/**
 *
 * @author Batzy
 */
public class insert extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        SessionFactory fac = NewHibernateUtil.getSessionFactory();
        Session session = fac.openSession();
        
        SellingItems sI = new SellingItems();
        
        sI.setName(request.getParameter("name"));
       
      
        System.out.println("nama"+request.getParameter("name"));
        System.out.println("date"+request.getParameter("date"));
        
        sI.setPrice(Double.valueOf(request.getParameter("price")));
           

            session.save(sI);
            Transaction tr = session.beginTransaction();
            tr.commit();
           // response.sendRedirect("sellingItemsManager.jsp");
           
            SessionFactory sf2 = NewHibernateUtil.getSessionFactory();
        Session s2 =sf2.openSession();
        
    

        Criteria c = s2.createCriteria(SellingItems.class);
       
        c.addOrder(Order.asc("iditem"));
        request.setAttribute("sList", (List<SellingItems>) c.list());
        
//        for(SellingItems s : (List<SellingItems>) c.list()){
//            System.out.println(s.getIditem());
//        
//        }
//        
        
        
        request.getRequestDispatcher("table.jsp").forward(request, response);
        
        
    }

    

}
