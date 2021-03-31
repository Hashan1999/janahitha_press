/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CTRL_GA;

import LGR_GA.JDBClog;
import hibernate.NewHibernateUtil;
import hibernate.PrintingJob;
import java.io.IOException;
import java.io.PrintWriter;
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
public class Load_Job extends HttpServlet {

    String responce;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SessionFactory factory = NewHibernateUtil.getSessionFactory();
        Session session = factory.openSession();
        new JDBClog().writeToFileSearch("JDBC Connection Opened", this.getClass().getName(), getServletContext().getRealPath(""));
        try {
            String id = req.getParameter("id");
            if (id.matches("[0-9]+")) {
                int jobIdInt = Integer.parseInt(id);
                Criteria printJobCritera = session.createCriteria(PrintingJob.class).add(Restrictions.eq("idprintingJob", jobIdInt));
                PrintingJob printJobObj = (PrintingJob) printJobCritera.uniqueResult();
                if (printJobObj != null) {
                    req.setAttribute("job", printJobObj);
                    req.getRequestDispatcher("create_print_job_list.jsp").forward(req, resp);

                } else {
                    responce = "No print job founf";
                    resp.getWriter().write(responce);
                }

            } else {
                responce = "invalid print job";
                resp.getWriter().write(responce);
            }

        } catch (Exception e) {
            e.printStackTrace();
            responce = "Server error Contact the devaloper";
            resp.getWriter().write(responce);
        }
        if (session.isOpen()) {
            session.flush();
            session.close();
            new JDBClog().writeToFileSearch("JDBC Connection Closed", this.getClass().getName(), getServletContext().getRealPath(""));
        }

    }

}
