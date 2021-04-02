/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ctrl;

import hibernate.Grn;
import hibernate.GrnItem;
import hibernate.RawItem;
import hibernate.Supplier;
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
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Batzy
 */
public class grnItemInsert extends HttpServlet {

    
   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       SessionFactory fac = NewHibernateUtil.getSessionFactory();
        Session session = fac.openSession();
        
         String rawItem = request.getParameter("rawItemName");
         String  uPrice = request.getParameter("uPrice");
         String qty = request.getParameter("qty");
         String subTotal = request.getParameter("subTotal");
       
        
        
         Criteria c = session.createCriteria(Grn.class);        
         c.addOrder(Order.desc("idgrn"));
         c.setMaxResults(1);
         Grn g = (Grn) c.uniqueResult();
         
         Criteria c2 =session.createCriteria(RawItem.class);
         c2.add(Restrictions.eq("name", rawItem));
         RawItem ri =(RawItem) c2.uniqueResult();
         
         GrnItem gi = new GrnItem();
         gi.setGrn(g);
         gi.setRawItem(ri);
         gi.setQty(Integer.parseInt(qty));
         gi.setSubTotal(Double.parseDouble(subTotal));
         gi.setUnitPrice(Double.parseDouble(uPrice));
         
         session.save(gi);
       
         Transaction tr = session.beginTransaction();
            tr.commit();
         String grnid =g.getIdgrn().toString();   
        response.getWriter().write(grnid); 
    }

   

}
