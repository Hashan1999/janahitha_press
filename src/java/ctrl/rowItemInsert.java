/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ctrl;

import hibernate.Brand;
import hibernate.RawItem;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import hibernate.NewHibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Batzy
 */
public class rowItemInsert extends HttpServlet {
 @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
      
           
   
         SessionFactory fac = NewHibernateUtil.getSessionFactory();
         Session session = fac.openSession();
         String brandName= request.getParameter("brand");
        
         Criteria c = session.createCriteria(Brand.class);
         c.add(Restrictions.eq("name",brandName));
         Brand b2 =(Brand) c.uniqueResult();
         Date date = new Date();
         
         
         
         RawItem sI = new RawItem();
         sI.setName(request.getParameter("name"));
         sI.setDescription(request.getParameter("description"));
         sI.setDate(date);
         sI.setBrand(b2);
         
         
        
         
         
         
         
         session.save(sI);
         Transaction tr = session.beginTransaction();
         tr.commit();
         
         
         Criteria c2 = session.createCriteria(RawItem.class);
         
         c.addOrder(Order.asc("idrawItems"));
         request.setAttribute("sList", (List<RawItem>) c2.list());
         
         
//        for(SellingItems s : (List<SellingItems>) c.list()){
//            System.out.println(s.getIditem());
//        
//        }
//


request.getRequestDispatcher("table3.jsp").forward(request, response);
    
    }

    

}
