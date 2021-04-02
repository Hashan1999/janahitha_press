/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ctrl;

import hibernate.Brand;
import hibernate.Grn;
import hibernate.Supplier;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

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
public class grnInsert extends HttpServlet {

   

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
        
         SessionFactory fac = NewHibernateUtil.getSessionFactory();
        Session session = fac.openSession();
        
        
        String supName = request.getParameter("suplier");
        
        Criteria c = session.createCriteria(Supplier.class);
        c.add(Restrictions.eq("name", supName));
        
        Supplier s =(Supplier) c.uniqueResult();
        
        
        
        
        Grn g = new Grn();
        
        
        g.setSupplier(s);
        g.setTotal(Double.parseDouble(request.getParameter("total")));
        g.setDate(new Date());
        
        

            session.save(g);
            Transaction tr = session.beginTransaction();
            tr.commit();
            
            response.getWriter().write("ok");
                    
                    
                    }

    
  

}
