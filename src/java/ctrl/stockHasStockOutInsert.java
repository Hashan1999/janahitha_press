/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ctrl;

import hibernate.Grn;
import hibernate.Stock;
import hibernate.StockHasStockOut;
import hibernate.StockHasStockOutId;
import hibernate.StockOut;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
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
public class stockHasStockOutInsert extends HttpServlet {

    
     @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         SessionFactory fac = NewHibernateUtil.getSessionFactory();
        Session session = fac.openSession();
        
         StockHasStockOut sho= new StockHasStockOut();
         StockHasStockOutId shoi=new StockHasStockOutId();
         
         
         Criteria c = session.createCriteria(StockOut.class);
         c.addOrder(Order.desc("idstockOutTime"));
         c.setMaxResults(1);
         StockOut so =(StockOut) c.uniqueResult();
         System.out.println(so.getIdstockOutTime());
         
         Criteria c2 = session.createCriteria(Stock.class);
         c2.add(Restrictions.eq("idstock",Integer.valueOf(request.getParameter("sId")) ));
         
        
         Stock s = (Stock) c2.uniqueResult();
         System.out.println(s.getIdstock());
         
         int qty = s.getQty();
         int costQty =Integer.parseInt(request.getParameter("qty"));
         
         shoi.setStockIdstock(s.getIdstock());
         shoi.setStockOutIdstockOutTime(so.getIdstockOutTime());
         
         sho.setId(shoi);
         sho.setStock(s);
         sho.setStockOut(so);
         sho.setStockOutQty(Integer.valueOf(request.getParameter("qty")));
         
        
          session.save(sho);
         Transaction tr = session.beginTransaction();
         
         tr.commit();
           
         
        
            
            response.getWriter().write("ok");
            
    }

}
