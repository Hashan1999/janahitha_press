/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ctrl;

import hibernate.Stock;
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
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Batzy
 */
public class stockStatus extends HttpServlet {

    
      @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
       SessionFactory sf2 = NewHibernateUtil.getSessionFactory();
        Session s2 =sf2.openSession();
        
          System.out.println(request.getParameter("id"));
       int id =Integer.parseInt(request.getParameter("id"));

        Criteria c = s2.createCriteria(Stock.class);
        c.add(Restrictions.eq("idstock",id));
        Stock s = (Stock) c.uniqueResult();
        
         Byte b = s.getStatus();
          System.out.println(b);
         
         
        
       if(b==1){
       
         Criteria c2 = s2.createCriteria(Stock.class);
        c2.add(Restrictions.eq("idstock",id)); 
        Stock s3 = (Stock) c2.uniqueResult();
            byte status = 2;
            
                s3.setStatus(status);
                s2.saveOrUpdate(s3);
                Transaction tr = s2.beginTransaction();
         
                tr.commit();
                response.getWriter().write("ok");
        
           
       }else{
       Criteria c2 = s2.createCriteria(Stock.class);
        c2.add(Restrictions.eq("idstock",id)); 
        Stock s3 = (Stock) c2.uniqueResult();
            byte status = 1;
            
                s3.setStatus(status);
                s2.saveOrUpdate(s3);
                Transaction tr = s2.beginTransaction();
         
                tr.commit();
                response.getWriter().write("ok");
       }
        
//        for(SellingItems s : (List<SellingItems>) c.list()){
//            System.out.println(s.getIditem());
//        
//        }
//        
        
     
    }


}
