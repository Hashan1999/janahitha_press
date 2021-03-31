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
public class Remove_machine extends HttpServlet {

    String responce;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SessionFactory factory = NewHibernateUtil.getSessionFactory();
        Session session = factory.openSession();
        new JDBClog().writeToFileSearch("JDBC Connection Opened", this.getClass().getName(), getServletContext().getRealPath(""));
        try {
            String id = req.getParameter("id");

            if (id.isEmpty() || !id.matches("[0-9]+")) {
                responce = "id";

            } else {

                Transaction trans = session.beginTransaction();

                Criteria machine_critera = session.createCriteria(Machine.class);
                machine_critera.add(Restrictions.eq("idmachine", Integer.valueOf(id)));
                Machine machine = (Machine) machine_critera.uniqueResult();

                if (machine != null) {
                    if (machine.getMachineStatus().getStatus().equals("Deactive")) {
                        Criteria machine_status_critera = session.createCriteria(MachineStatus.class);
                        machine_status_critera.add(Restrictions.eq("status", "Active"));
                        MachineStatus machine_status = (MachineStatus) machine_status_critera.uniqueResult();

                        machine.setMachineStatus(machine_status);

                        session.update(machine);
                        trans.commit();
                        responce = "ok";
                    } else {
                        Set<PrintingJob> printing_jobs = machine.getPrintingJobs();
                        boolean is_active = false;

                        for (PrintingJob p : printing_jobs) {
                            String status = p.getPrintingJobStatus().getStatus();
                            if (status.equals("Pending")) {
                                is_active = true;
                            }
                        }

                        if (!is_active) {

                            Criteria machine_status_critera = session.createCriteria(MachineStatus.class);
                            machine_status_critera.add(Restrictions.eq("status", "Deactive"));
                            MachineStatus machine_status = (MachineStatus) machine_status_critera.uniqueResult();

                            machine.setMachineStatus(machine_status);

                            session.update(machine);
                            trans.commit();
                            responce = "ok";
                        } else {
                            responce = "active";
                        }
                    }

                } else {
                    responce = "no_res";
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
