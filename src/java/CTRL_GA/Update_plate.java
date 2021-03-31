/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CTRL_GA;

import LGR_GA.JDBClog;
import LGR_GA.Logger;
import hibernate.Customer;
import hibernate.NewHibernateUtil;
import hibernate.Plate;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Pasindu Maduranga
 */
public class Update_plate extends HttpServlet {

    String responce;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        SessionFactory factory = NewHibernateUtil.getSessionFactory();
        Session session = factory.openSession();
        new JDBClog().writeToFileSearch("JDBC Connection Opened", this.getClass().getName(), getServletContext().getRealPath(""));
        try {
            String plate_rack = req.getParameter("rack");
            String plate_date = req.getParameter("date");
            String plate_count = req.getParameter("count");
            String plate_customer = req.getParameter("customer");
            String plate_description = req.getParameter("description");
            String id = req.getParameter("id");
            if (!plate_rack.isEmpty()) {
                if (!plate_date.isEmpty()) {
                    boolean date_validate = true;
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    try {
                        Date date = sdf.parse(plate_date);

                    } catch (Exception e) {
                        date_validate = false;
                    }
                    if (date_validate) {
                        if (!plate_count.isEmpty()) {
                            if (plate_count.matches("[0-9]+")) {
                                if (Integer.parseInt(plate_count) > 0) {
                                    if (!plate_customer.isEmpty()) {
                                        if (!plate_description.isEmpty()) {

                                            Criteria customer_critera = session.createCriteria(Customer.class);
                                            customer_critera.add(Restrictions.eq("contact", plate_customer));

                                            Customer customer = (Customer) customer_critera.uniqueResult();
                                            if (customer != null) {
                                                if (!id.isEmpty()) {
                                                    if (id.matches("[0-9]+")) {
                                                        Transaction trans = session.beginTransaction();

                                                        Criteria plate_critera = session.createCriteria(Plate.class);
                                                        plate_critera.add(Restrictions.eq("idplate", Integer.parseInt(id)));

                                                        Plate plate = (Plate) plate_critera.uniqueResult();
                                                        if (plate != null) {
                                                            plate.setCustomer(customer);
                                                            plate.setDate(sdf.parse(plate_date));
                                                            plate.setDescription(plate_description);
                                                            plate.setPlateCount(Integer.parseInt(plate_count));
                                                            plate.setRackNumber(plate_rack);

                                                            session.update(plate);
                                                            trans.commit();
                                                            responce = "ok";
                                                        } else {
                                                            responce = "no_plate";
                                                        }

                                                    } else {
                                                        responce = "invalid_id";
                                                    }
                                                } else {
                                                    responce = "no_id";
                                                }

                                            } else {
                                                responce = "no_customer";
                                            }
                                        } else {
                                            responce = "null_description";
                                        }
                                    } else {
                                        responce = "null_customer";
                                    }
                                } else {
                                    responce = "invalid_cout";
                                }
                            } else {
                                responce = "invalid_cout";
                            }
                        } else {
                            responce = "null_count";
                        }

                    } else {
                        responce = "invalid_date";
                    }

                } else {
                    responce = "null_date";
                }
            } else {
                responce = "null_rack";
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
