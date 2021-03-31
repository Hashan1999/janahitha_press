/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CTRL_GA;

import LGR_GA.JDBClog;
import hibernate.NewHibernateUtil;
import hibernate.PrintingJob;
import hibernate.PrintingJobStatus;
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
public class ChangePrintJobStatus extends HttpServlet {

    String responce;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SessionFactory factory = NewHibernateUtil.getSessionFactory();
        Session session = factory.openSession();
        new JDBClog().writeToFileSearch("JDBC Connection Opened", this.getClass().getName(), getServletContext().getRealPath(""));
        try {
            String id = req.getParameter("id");
            String status = req.getParameter("status");
            if (!status.isEmpty()) {
                if (id.matches("[0-9]+")) {
                    Criteria PrintJobCritera = session.createCriteria(PrintingJob.class);
                    int printJobId = Integer.valueOf(id);
                    PrintJobCritera.add(Restrictions.eq("idprintingJob", printJobId));
                    PrintingJob printJob = (PrintingJob) PrintJobCritera.uniqueResult();
                    if (printJob != null) {
                        String printJobStatus = printJob.getPrintingJobStatus().getStatus().trim();
                        String updateedStatus;
                        switch (status) {
                            case "1":
                                updateedStatus = "Processing";
                                break;
                            case "2":
                                updateedStatus = "Finished";
                                break;
                            case "3":
                                updateedStatus = "Pending";
                                break;
                            case "4":
                                updateedStatus = "Canceled";
                                break;
                            default:
                                updateedStatus = null;
                                break;

                        }
                        if(updateedStatus != null){
                            if(printJobStatus.equals(updateedStatus.trim())){
                                responce = "The Status is already "+updateedStatus;  
                            }else{
                                Criteria printJobStatusCritera = session.createCriteria(PrintingJobStatus.class);
                                printJobStatusCritera.add(Restrictions.eq("status", updateedStatus));
                                List<PrintingJobStatus> printJobStatusList = printJobStatusCritera.list();
                                if(printJobStatusList.size()>0){
                                    Transaction trans = session.beginTransaction();
                                    PrintingJobStatus printStatus = printJobStatusList.get(0);
                                    printJob.setPrintingJobStatus(printStatus);
                                    session.update(printJob);
                                    trans.commit();
                                    
                                    responce = "ok";
                                }else{
                                    responce = "invalid Status Contact devaloper";
                                }
                                
                            }
                        }else{
                            responce = "Invalid Status";
                        }
                    } else {
                        responce = "No Print Job Selected";
                    }

                } else {
                    responce = "Invalid Print Job";
                }
            } else {
                responce = "Need a status";
            }
        } catch (Exception e) {
            e.printStackTrace();
            responce = "Server error contact the devaloper";
        }
        if (session.isOpen()) {
            session.flush();
            session.close();
            new JDBClog().writeToFileSearch("JDBC Connection Closed", this.getClass().getName(), getServletContext().getRealPath(""));
        }
        resp.getWriter().write(responce);
    }

}
