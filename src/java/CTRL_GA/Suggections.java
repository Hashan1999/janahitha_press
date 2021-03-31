/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CTRL_GA;

import LGR_GA.JDBClog;
import LGR_GA.Logger;
import Model_GA.customer_list;
import com.google.gson.Gson;
import hibernate.Customer;
import hibernate.NewHibernateUtil;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Pasindu Maduranga
 */
public class Suggections extends HttpServlet {

    String responce;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        SessionFactory factory = NewHibernateUtil.getSessionFactory();
        Session session = factory.openSession();
        new JDBClog().writeToFileSearch("JDBC Connection Opened", this.getClass().getName(), getServletContext().getRealPath(""));

        try {
           

            Criteria customer_name_critera = session.createCriteria(Customer.class);
          
            
            List<Customer> customer_list = customer_name_critera.list();
            
            ArrayList<customer_list> cus_lst = new ArrayList<>();
            
            for(Customer c : customer_list){
              customer_list cl = new customer_list();
              cl.setId(c.getContact());
              cl.setName(c.getFirstName()+" "+c.getLastName());
              cl.setUser("c"+c.getIdcustomer());
              cus_lst.add(cl);
              
            }
            Gson gson = new Gson();
            resp.getWriter().write(gson.toJson(cus_lst));
            
            
        } catch (Exception e) {
            new Logger().writeToFileSearch(e.toString(), this.getClass().getName(), getServletContext().getRealPath(""));
            e.printStackTrace();
            responce = "invalid";
        }

        if (session.isOpen()) {
            session.flush();
            session.close();
            new JDBClog().writeToFileSearch("JDBC Connection Closed", this.getClass().getName(), getServletContext().getRealPath(""));
        }
       
    }

}
