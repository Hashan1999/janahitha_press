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
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Batzy
 */
public class rowItemUpdate extends HttpServlet {

     @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         
         
      
             SessionFactory fac = NewHibernateUtil.getSessionFactory();
             Session session = fac.openSession();
             
              SessionFactory fac2 = NewHibernateUtil.getSessionFactory();
             Session session2 = fac2.openSession();
             
             Criteria c = session.createCriteria(Brand.class);
             c.add(Restrictions.eq("name", request.getParameter("brand")));
             Brand b2 =(Brand) c.uniqueResult();
             
              Criteria c3 = session2.createCriteria(RawItem.class);
             c3.add(Restrictions.eq("idrawItem",Integer.valueOf(request.getParameter("id")) ));
             RawItem r=(RawItem) c3.uniqueResult();
             
             
             
             
             RawItem sI = new RawItem();
             sI.setIdrawItem(Integer.valueOf(request.getParameter("id")));
             sI.setName(request.getParameter("name"));
             sI.setDescription(request.getParameter("description"));
             sI.setBrand(b2);
             sI.setDate(r.getDate());
             
             session.beginTransaction();
             session.saveOrUpdate(sI);
             session.getTransaction().commit();
             session.close();
             
             response.getWriter().write("ok");
        

}
}
