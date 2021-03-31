/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CTRL_GA;

import LGR_GA.JDBClog;
import LGR_GA.Logger;
import hibernate.Machine;
import hibernate.MachineStatus;
import hibernate.NewHibernateUtil;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
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
public class reg_machine extends HttpServlet {

    String responce;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SessionFactory factory = NewHibernateUtil.getSessionFactory();
        Session session = factory.openSession();
        new JDBClog().writeToFileSearch("JDBC Connection Opened", this.getClass().getName(), getServletContext().getRealPath(""));
        try {
            String machine_name = req.getParameter("name");

            if (!machine_name.isEmpty()) {

                Criteria machine_critera = session.createCriteria(Machine.class);
                machine_critera.add(Restrictions.eq("name", machine_name).ignoreCase());

                List<Machine> is_in_system = machine_critera.list();
                if (is_in_system.size() > 0) {
                    
                    
                    responce = "in_sys";
                } else {

                    Criteria  mashine_status_critera = session.createCriteria(MachineStatus.class);
                    mashine_status_critera.add(Restrictions.eq("status", "Active"));
                    MachineStatus machine_status = (MachineStatus) mashine_status_critera.uniqueResult();
                    
                    Transaction trans = session.beginTransaction();
                    Machine new_machine = new Machine();
                    new_machine.setName(machine_name);
                    new_machine.setMachineStatus(machine_status);

                    session.save(new_machine);
                    trans.commit();
                    responce = "ok";
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
