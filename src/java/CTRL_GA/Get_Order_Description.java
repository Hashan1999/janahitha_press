/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CTRL_GA;

import LGR_GA.JDBClog;
import Model_GA.Order_details;
import com.google.gson.Gson;
import hibernate.NewHibernateUtil;
import hibernate.Order;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
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
public class Get_Order_Description extends HttpServlet {

    String responce;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SessionFactory factory = NewHibernateUtil.getSessionFactory();
        Session session = factory.openSession();
        new JDBClog().writeToFileSearch("JDBC Connection Opened", this.getClass().getName(), getServletContext().getRealPath(""));
        try {
            String order_id = req.getParameter("id");
            if (order_id.matches("[0-9]+")) {
                System.out.println(order_id + "sdasdasd ");

                Criteria order_critera = session.createCriteria(Order.class);
                order_critera.add(Restrictions.eq("idorder", Integer.parseInt(order_id)));

                Order order_description = (Order) order_critera.uniqueResult();

                if (order_description != null) {

                    Order_details order_details = new Order_details();
                    order_details.setCustomer_mobile(order_description.getCustomer().getContact());
                    order_details.setCustomer_name(order_description.getCustomer().getFirstName() + " " + order_description.getCustomer().getLastName());
                    order_details.setDate(new SimpleDateFormat("yyyy-MMM-dd").format(order_description.getDate()));
                    order_details.setDel_date(new SimpleDateFormat("yyyy-MMM-dd").format(order_description.getDeliverDate()));
                    order_details.setDiscount(order_description.getDiscount() + "");
                    order_details.setDue(order_description.getDueAmount() + "");
                    order_details.setId(order_description.getUniqueIdentifierText());
                    order_details.setOrder_status(order_description.getOrderStatus().getStatus());
                    order_details.setSub(order_description.getSubtotal() + "");
                    order_details.setTotal(order_description.getTotal() + "");
                    order_details.setDescription(order_description.getDescription());

                    Gson gson = new Gson();
                    String json_array = gson.toJson(order_details);
                    responce = json_array;

                } else {
                    responce = "no_order";
                }

            } else {
                responce = "no_id";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (session.isOpen()) {
            session.flush();
            session.close();
            new JDBClog().writeToFileSearch("JDBC Connection Closed", this.getClass().getName(), getServletContext().getRealPath(""));
        }
        resp.getWriter().write(responce);
    }

}
