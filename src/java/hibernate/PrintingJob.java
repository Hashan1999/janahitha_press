package hibernate;
// Generated Apr 2, 2021 12:14:54 PM by Hibernate Tools 4.3.1


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * PrintingJob generated by hbm2java
 */
public class PrintingJob  implements java.io.Serializable {


     private Integer idprintingJob;
     private Machine machine;
     private Order order;
     private PrintingJobStatus printingJobStatus;
     private Date regDate;
     private Date startDate;
     private Set plateHasPrintingJobs = new HashSet(0);

    public PrintingJob() {
    }

	
    public PrintingJob(Machine machine, Order order) {
        this.machine = machine;
        this.order = order;
    }
    public PrintingJob(Machine machine, Order order, PrintingJobStatus printingJobStatus, Date regDate, Date startDate, Set plateHasPrintingJobs) {
       this.machine = machine;
       this.order = order;
       this.printingJobStatus = printingJobStatus;
       this.regDate = regDate;
       this.startDate = startDate;
       this.plateHasPrintingJobs = plateHasPrintingJobs;
    }
   
    public Integer getIdprintingJob() {
        return this.idprintingJob;
    }
    
    public void setIdprintingJob(Integer idprintingJob) {
        this.idprintingJob = idprintingJob;
    }
    public Machine getMachine() {
        return this.machine;
    }
    
    public void setMachine(Machine machine) {
        this.machine = machine;
    }
    public Order getOrder() {
        return this.order;
    }
    
    public void setOrder(Order order) {
        this.order = order;
    }
    public PrintingJobStatus getPrintingJobStatus() {
        return this.printingJobStatus;
    }
    
    public void setPrintingJobStatus(PrintingJobStatus printingJobStatus) {
        this.printingJobStatus = printingJobStatus;
    }
    public Date getRegDate() {
        return this.regDate;
    }
    
    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }
    public Date getStartDate() {
        return this.startDate;
    }
    
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
    public Set getPlateHasPrintingJobs() {
        return this.plateHasPrintingJobs;
    }
    
    public void setPlateHasPrintingJobs(Set plateHasPrintingJobs) {
        this.plateHasPrintingJobs = plateHasPrintingJobs;
    }




}


