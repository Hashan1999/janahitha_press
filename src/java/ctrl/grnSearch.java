/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ctrl;

import hibernate.Grn;
import hibernate.GrnItem;
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
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Batzy
 */
public class grnSearch extends HttpServlet {

     
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
//        
//         SessionFactory sf2 = NewHibernateUtil.getSessionFactory();
//        Session s2 =sf2.openSession();
//        String id = request.getParameter("id");
//    
//
//        Criteria c = s2.createCriteria(GrnItem.class);
//       
//        c.addOrder(Order.asc("idgrn_item"));
//        request.setAttribute("sList", (List<Grn>) c.list());
//        c.add(Restrictions.eq("name", id));
//        
//        request.getRequestDispatcher("grnItems.jsp").forward(request, response);
        
        
        
        
       
        
    }

    
    
    

}
