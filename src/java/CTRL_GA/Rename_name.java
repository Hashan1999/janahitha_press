/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CTRL_GA;

import LGR_GA.JDBClog;
import LGR_GA.Logger;
import hibernate.Machine;
import hibernate.NewHibernateUtil;
import java.io.IOException;
import java.io.PrintWriter;
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
public class Rename_name extends HttpServlet {

    String responce;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SessionFactory factory = NewHibernateUtil.getSessionFactory();
        Session session = factory.openSession();
        new JDBClog().writeToFileSearch("JDBC Connection Opened", this.getClass().getName(), getServletContext().getRealPath(""));
        try {
            String machine_name = req.getParameter("name");
            String machine_id = req.getParameter("id");
            if (machine_id.isEmpty() || !machine_id.matches("[0-9]+")) {
                responce = "id";
            } else {
                if (machine_name.isEmpty()) {
                    responce = "null";
                } else {

                    Criteria machine_critea = session.createCriteria(Machine.class);
                    machine_critea.add(Restrictions.eq("idmachine", Integer.valueOf(machine_id)));
                    Machine machine = (Machine) machine_critea.uniqueResult();

                    if (machine != null) {
                        Transaction trans = session.beginTransaction();
                        machine.setName(machine_name);
                        session.update(machine);
                        trans.commit();
                        responce = "ok";
                    } else {
                        responce = "no";
                    }

                }
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
