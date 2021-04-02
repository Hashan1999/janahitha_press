/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ctrl;

import hibernate.SellingItems;
import hibernate.StockOut;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import hibernate.NewHibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author Batzy
 */
public class stockOutInsert extends HttpServlet {

   
   

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         SessionFactory fac = NewHibernateUtil.getSessionFactory();
        Session session = fac.openSession();
        
        StockOut sI = new StockOut();
        
        sI.setDescription(request.getParameter("des"));
        sI.setDate(new Date());
      
           

            session.save(sI);
            Transaction tr = session.beginTransaction();
            tr.commit();
            
            response.getWriter().write("ok");
    }

    
}
