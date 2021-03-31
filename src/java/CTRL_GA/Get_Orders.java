/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CTRL_GA;

import hibernate.Customer;
import hibernate.NewHibernateUtil;
import hibernate.Order;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Pasindu Maduranga
 */
public class Get_Orders extends HttpServlet {
    String responce;
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
           String id = req.getParameter("id").trim();
            SessionFactory factory = NewHibernateUtil.getSessionFactory();
            Session session = factory.openSession();
            System.out.println(id+" asdas");
            Criteria customer_critera = session.createCriteria(Customer.class);
            customer_critera.add(Restrictions.eq("contact", id));
            System.out.println(customer_critera.list().size());
            

            Customer customer = (Customer)customer_critera.uniqueResult();
            if(customer != null ){
                
                ArrayList<Order> order_list = new ArrayList<>();
                
                Criteria order_critera = session.createCriteria(Order.class);
                order_critera.add(Restrictions.eq("customer", customer));
                order_list = (ArrayList<Order>)order_critera.list();
              
                req.setAttribute("Order_list", order_list);
                req.getRequestDispatcher("order_list_create.jsp").forward(req, resp);
                
                
            }else{
                
                responce = "no_user";
            }
           
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

  

}
