/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CTRL_GA;

import LGR_GA.JDBClog;
import LGR_GA.Logger;
import Model_GA.plate_list;
import com.google.gson.Gson;
import hibernate.Customer;
import hibernate.NewHibernateUtil;
import hibernate.Plate;
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
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Pasindu Maduranga
 */
public class Get_Plates extends HttpServlet {

    String responce;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       
        SessionFactory factory = NewHibernateUtil.getSessionFactory();
        Session session = factory.openSession();
        new JDBClog().writeToFileSearch("JDBC Connection Opened", this.getClass().getName(), getServletContext().getRealPath(""));
        try {

            String number = req.getParameter("number");
            System.out.println(number);
            Criteria customer_critera = session.createCriteria(Customer.class);

            customer_critera.add(Restrictions.eq("contact", number));
            Customer customer = (Customer) customer_critera.uniqueResult();
            if (customer != null) {
                Criteria plate_critera = session.createCriteria(Plate.class);
                plate_critera.add(Restrictions.eq("customer", customer));

                List<Plate> plate_list = plate_critera.list();
                ArrayList<plate_list> plate_set_list = new ArrayList<>();
                
                for (Plate pl : plate_list) {
                      plate_list p = new plate_list();
                      p.setPlate_no(pl.getIdplate()+"");
                      p.setPlate_count(pl.getPlateCount()+"");
                      p.setDescription(pl.getDescription());
                      p.setRack_no(pl.getRackNumber()+"");
                      plate_set_list.add(p);
                }
                Gson gson = new Gson();
                String json_array = gson.toJson(plate_set_list);
            
                responce = json_array;

            }else{
                responce = "no_result";
            }

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

        resp.getWriter().write(responce);
    }

}
