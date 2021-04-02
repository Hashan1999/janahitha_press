/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ctrl;

import hibernate.Stock;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import hibernate.NewHibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Batzy
 */
public class stockUpdate extends HttpServlet {

    

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
      
        SessionFactory fac2 = NewHibernateUtil.getSessionFactory();
        Session session2 = fac2.openSession(); 
        int costQty =Integer.parseInt(request.getParameter("qty"));
        
        Criteria c2 = session2.createCriteria(Stock.class);
         c2.add(Restrictions.eq("idstock",Integer.valueOf(request.getParameter("sId")) ));
         Stock s = (Stock) c2.uniqueResult();
         System.out.println(s.getIdstock());
         
         int qty = s.getQty();
        
        Criteria c4 = session2.createCriteria(Stock.class);
        c4.add(Restrictions.eq("idstock",Integer.valueOf(request.getParameter("sId")) ));
          Stock s2 =(Stock)c4.uniqueResult();
                      s2.setIdstock(Integer.parseInt(request.getParameter("sId")));
                      s2.setQty(qty-costQty);
                     
                     
		
		session2.saveOrUpdate(s2);
                Transaction tr = session2.beginTransaction();
         
                tr.commit();
		
		session2.close();
    }

    

}
