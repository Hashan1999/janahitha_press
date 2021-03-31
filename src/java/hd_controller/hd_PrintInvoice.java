/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hd_controller;

import hd_bean.OrderItemInvoiceBean;
import hibernate.NewHibernateUtil;
import hibernate.Order;
import hibernate.OrderItem;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author HASHA
 */
@WebServlet(name = "hd_PrintInvoice", urlPatterns = {"/hd_PrintInvoice"})
public class hd_PrintInvoice extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
      
        String orderId=request.getParameter("orderId");
          Session hibernateSession =NewHibernateUtil.getSessionFactory().openSession();
        Order order=(Order) hibernateSession.load(Order.class, Integer.parseInt(orderId));
     List<OrderItem> orderItemList=(List<OrderItem>)hibernateSession.createCriteria(OrderItem.class).add(Restrictions.eq("order", order)).list();

       
               HashMap<String, Object> hm = new HashMap<>();
                hm.put("INV_ID", order.getIdorder());
                hm.put("INV_DATE", new SimpleDateFormat("YYYY/MM/dd").format(new Date()));
                hm.put("CUST", order.getCustomer().getFirstName()+" "+order.getCustomer().getLastName());
                hm.put("TEL", order.getCustomer().getContact());
                hm.put("ADDR", new SimpleDateFormat("YYYY/MM/dd").format(order.getDeliverDate()));
               
                hm.put("TOTAL", order.getSubtotal());
                hm.put("DISCOUNT", order.getDiscount());
                hm.put("PAYMENT", order.getSubtotal()-order.getDueAmount());
                hm.put("DUE_PAYMENT", order.getDueAmount());

                ArrayList<OrderItemInvoiceBean> itemList = new ArrayList<>();
              
                
         for (OrderItem orderItem : orderItemList) {
            itemList.add(new OrderItemInvoiceBean(orderItem.getIdorderItem(), orderItem.getSellingItems().getName(), orderItem.getUnitPrice(), orderItem.getQty()));
     
        }
                 
                    
                InputStream is = getServletContext().getResourceAsStream("/WEB-INF/classes/reports/invoice.jasper");
                JasperPrint jp;
        try {
            jp = JasperFillManager.fillReport(is, hm, new JRBeanCollectionDataSource(itemList));
         JasperExportManager.exportReportToPdfStream(jp, response.getOutputStream());
      
        } catch (Exception ex) {
            Logger.getLogger(hd_PrintInvoice.class.getName()).log(Level.SEVERE, null, ex);
        }

              
    
    

        
    }

    

}
