/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CTRL_GA;

import LGR_GA.JDBClog;
import hibernate.Customer;
import hibernate.Machine;
import hibernate.MachineStatus;
import hibernate.NewHibernateUtil;
import hibernate.Order;
import hibernate.Plate;
import hibernate.PlateHasPrintingJob;
import hibernate.PrintingJob;
import hibernate.PrintingJobStatus;
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
public class update_job extends HttpServlet {

    String responce;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SessionFactory factory = NewHibernateUtil.getSessionFactory();
        Session session = factory.openSession();
        new JDBClog().writeToFileSearch("JDBC Connection Opened", this.getClass().getName(), getServletContext().getRealPath(""));
        try {

            boolean validation = true;
            
          
            String customer = req.getParameter("cust");
            String plate_id = req.getParameter("plt");
            String machine_id = req.getParameter("mch");
            String reg_date = req.getParameter("regdt");
            String start_date = req.getParameter("strdt");
            String order_id = req.getParameter("ordid");
            String job_id = req.getParameter("Jobid");
          
            //validation null
            if (customer.isEmpty()) {
                validation = false;
                responce = "Need a Customer";
            } else if (plate_id == null) {
                validation = false;
                responce = "Need a Plate to print job";
            } else if (machine_id == null) {
                validation = false;
                responce = "Need a machine to print job";
            } else if (reg_date == null) {
                validation = false;
                responce = "Need a Register date";
            } else if (start_date == null) {
                validation = false;
                responce = "Need a Start Date";
            } else if (order_id == null) {
                validation = false;
                responce = "Need a Order To update print job";
            } else if (!plate_id.matches("[0-9]+")) {
                validation = false;
                responce = "Invalid Plate id";
            } else if (!machine_id.matches("[0-9]+")) {
                validation = false;
                responce = "Invalid Machine Id";
            } else if (order_id == null) {
                validation = false;
                responce = "Need a order to update print job";
            } else if (job_id == null) {
                validation = false;
                responce = "Need a job to update print job";
            } else if (job_id.isEmpty()) {
                validation = false;
                responce = "Need a job to update print job";
            } else if (!job_id.matches("[0-9]+")) {
                validation = false;
                responce = "Invalid job Id";
            }
            if (validation) {
                //customer validation
                Criteria customer_citera = session.createCriteria(Customer.class).add(Restrictions.eq("contact", customer));
                Customer custometObj = (Customer) customer_citera.uniqueResult();
                if (custometObj != null) {
                    //validate Plate
                    int plateId = Integer.valueOf(plate_id);
                    Criteria plate_critera = session.createCriteria(Plate.class).add(Restrictions.eq("idplate", plateId)).add(Restrictions.eq("customer", custometObj));
                    Plate plateObj = (Plate) plate_critera.uniqueResult();
                    if (plateObj != null) {
                        //validate order

                        Criteria order_critera = session.createCriteria(Order.class).add(Restrictions.eq("uniqueIdentifierText", order_id)).add(Restrictions.eq("customer", custometObj));
                        Order orderObj = (Order) order_critera.uniqueResult();
                        if (orderObj != null) {
                            Criteria mashine_status_critera = session.createCriteria(MachineStatus.class);
                            mashine_status_critera.add(Restrictions.eq("status", "Active"));
                            MachineStatus mashine_status = (MachineStatus) mashine_status_critera.uniqueResult();

                            int MachineId = Integer.parseInt(machine_id);
                            Criteria machine_critera = session.createCriteria(Machine.class).add(Restrictions.eq("machineStatus", mashine_status)).add(Restrictions.eq("id", MachineId));
                            Machine machineObj = (Machine) machine_critera.uniqueResult();
                            if (machineObj != null) {
                                //date validation
                                try {
                                    new SimpleDateFormat("yyyy-MM-dd").parse(reg_date);
                                    new SimpleDateFormat("yyyy-MM-dd").parse(start_date);
                                } catch (Exception e) {
                                    validation = false;
                                    responce = "Date format is invalid";
                                }
                                if (validation) {
                                    Date reg = new SimpleDateFormat("yyyy-MM-dd").parse(reg_date);
                                    Date start = new SimpleDateFormat("yyyy-MM-dd").parse(start_date);
                                    Criteria printjob_status = session.createCriteria(PrintingJobStatus.class).add(Restrictions.eq("status", "Pending"));
                                    PrintingJobStatus printingJobStatusObj = (PrintingJobStatus) printjob_status.uniqueResult();
                                    Transaction trans = session.beginTransaction();

                                    Criteria printJobCritera = session.createCriteria(PrintingJob.class);
                                    printJobCritera.add(Restrictions.eq("idprintingJob", Integer.parseInt(job_id)));

                                    PrintingJob printingJob = (PrintingJob) printJobCritera.uniqueResult();
                                    printingJob.setMachine(machineObj);
                                    printingJob.setOrder(orderObj);
                                    printingJob.setPrintingJobStatus(printingJobStatusObj);
                                    printingJob.setStartDate(start);
                                    printingJob.setRegDate(reg);
                                    session.update(printingJob);

                                    Criteria plateHasPrintJobCritera = session.createCriteria(PlateHasPrintingJob.class);
                                    plateHasPrintJobCritera.add(Restrictions.eq("printingJob", printingJob));

                                    PlateHasPrintingJob plateHasPrintingJob = (PlateHasPrintingJob) plateHasPrintJobCritera.uniqueResult();
                                    plateHasPrintingJob.setPlate(plateObj);
                                    session.update(plateHasPrintingJob);

                                    trans.commit();
                                    responce = "ok";

                                }

                            } else {
                                responce = "No machine selected";
                            }

                        } else {
                            responce = "No order selected";
                        }
                    } else {
                        responce = "No plate selected";
                    }

                } else {
                    responce = "no customer selected";
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            responce = "Server error";
        }
        if (session.isOpen()) {
            session.flush();
            session.close();
            new JDBClog().writeToFileSearch("JDBC Connection Closed", this.getClass().getName(), getServletContext().getRealPath(""));
        }

        resp.getWriter().write(responce);
    }

}
