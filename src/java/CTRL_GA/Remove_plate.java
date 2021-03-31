/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CTRL_GA;

import LGR_GA.JDBClog;
import LGR_GA.Logger;
import hibernate.NewHibernateUtil;
import hibernate.Plate;
import hibernate.PrintingJob;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Set;
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
public class Remove_plate extends HttpServlet {

    String responce;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SessionFactory factory = NewHibernateUtil.getSessionFactory();
        Session session = factory.openSession();
        new JDBClog().writeToFileSearch("JDBC Connection Opened", this.getClass().getName(), getServletContext().getRealPath(""));

        try {
           
            String id = req.getParameter("id");
            System.out.println(id);
            if (!id.isEmpty()) {

                if (id.matches("[0-9]+")) {
                    Criteria plate_critera = session.createCriteria(Plate.class);
                    plate_critera.add(Restrictions.eq("idplate", Integer.valueOf(id)));

                    Plate plate = (Plate) plate_critera.uniqueResult();
                    if (plate != null) {
                        Set<PrintingJob> printing_jobs = plate.getPlateHasPrintingJobs();
                        boolean is_active = false;
                        for (PrintingJob p : printing_jobs) {
                            if (p.getPrintingJobStatus().getStatus().equals("Pending")) {
                                is_active = true;
                                break;
                            }
                        }
                        if (!is_active) {
                            Transaction trans = session.beginTransaction();
                            session.delete(plate);
                            trans.commit();

                            responce = "ok";
                        } else {
                            responce = "active";
                        }

                    } else {
                        responce = "no_res";
                    }

                } else {
                    responce = "invalid_id";
                }

            } else {
                responce = "null";
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
