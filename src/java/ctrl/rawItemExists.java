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
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import hibernate.NewHibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Batzy
 */
public class rawItemExists extends HttpServlet {

  
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       SessionFactory sf2 = NewHibernateUtil.getSessionFactory();
        Session s2 = sf2.openSession();
        String name = request.getParameter("name");

        Criteria c = s2.createCriteria(RawItem.class);
        c.add(Restrictions.eq("name",name));
        
        if (c.uniqueResult()== null) {
             response.getWriter().write("false");
        } else {
            response.getWriter().write("true");

        }
    }

   
   

}
