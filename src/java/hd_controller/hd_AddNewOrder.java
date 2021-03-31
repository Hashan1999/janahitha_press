/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hd_controller;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import hd_bean.OrderItemBean;
import hd_bean.OrderItemInvoiceBean;
import hd_bean.ResponseMsg;
import hd_model.InvalidMobileException;
import hd_model.hd_validation;
import hibernate.Customer;
import hibernate.NewHibernateUtil;
import hibernate.Order;
import hibernate.OrderItem;
import hibernate.OrderStatus;
import hibernate.SellingItems;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.TransactionException;
import org.hibernate.criterion.Restrictions;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
/**
 *
 * @author HASHAN
 */
@WebServlet(name = "hd_AddNewOrder", urlPatterns = {"/hd_AddNewOrder"})
public class hd_AddNewOrder extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ResponseMsg msg = new ResponseMsg(0, "Customer Registration Failed!", null);

        SessionFactory sessionFactory = null;

        try {
            sessionFactory = NewHibernateUtil.getSessionFactory();
        } catch (Throwable e) {
            e.printStackTrace();
        }

        if (sessionFactory != null) {
            Session hibernateSession = sessionFactory.openSession();
            Transaction transaction = null;

            try {
                //code 
                String orderIdentifier = "";
                String orderDescription = "";
                String orderDiscount = "0.0";
                String orderAdvancePayment = "0.0";

                String orderItemJsonArray = "";

                String orderFinishDate = "";
                String customerContactNumber = "";

                boolean isValidData = false;

                Enumeration<String> parameterNames = request.getParameterNames();

                while (parameterNames.hasMoreElements()) {
                    String paramName = parameterNames.nextElement();

                    if (paramName.equalsIgnoreCase("orderIdentifier")) {
                        orderIdentifier = request.getParameter(paramName);

                    } else if (paramName.equalsIgnoreCase("orderFinishDate")) {
                        orderFinishDate = request.getParameter(paramName);
                    } else if (paramName.equalsIgnoreCase("orderDescription")) {
                        orderDescription = request.getParameter(paramName);
                    } else if (paramName.equalsIgnoreCase("orderDiscount")) {
                        orderDiscount = request.getParameter(paramName);
                    } else if (paramName.equalsIgnoreCase("orderAdvancePayment")) {
                        orderAdvancePayment = request.getParameter(paramName);;
                    } else if (paramName.equalsIgnoreCase("customerContactNumber")) {
                        customerContactNumber = request.getParameter(paramName);
                    } else if (paramName.equalsIgnoreCase("orderItemJsonArray")) {
                        orderItemJsonArray = request.getParameter(paramName);
                    }
                }

                Customer customer=null;
                Date orderFinishValidDate=null;

                if (!orderIdentifier.equals("")) {

                    if (!customerContactNumber.equals("")) {
                        if (hd_model.hd_validation.getValidatedMobile(customerContactNumber) != "") {

                            customer = (Customer) hibernateSession.createCriteria(Customer.class).add(Restrictions.eq("contact", hd_validation.getValidatedMobile(customerContactNumber))).uniqueResult();

                            if (customer != null) {
                                if (orderFinishDate != "") {
                                    orderFinishValidDate = new SimpleDateFormat("yyyy-MM-dd").parse(orderFinishDate);
                                 

                                    Date date = new Date();
//                                    LocalDate localTodayDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
//                                    LocalDate localOrderFinishDate = orderFinishValidDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
//
//                                    if (localOrderFinishDate.getYear() >= localTodayDate.getYear()
//                                            && localOrderFinishDate.getMonthValue() >= localTodayDate.getMonthValue()
//                                            && localOrderFinishDate.getDayOfMonth() >= localTodayDate.getDayOfMonth()) {
                                        
                                        
                                        if(orderFinishValidDate.after(date)){
                                        
                                        

                                        if (hd_validation.isNumericDouble(orderDiscount)) {
                                            System.out.println(orderDiscount);
                                            if (hd_validation.isNumericDouble(orderAdvancePayment)) {

                                                isValidData = true;

                                            } else {

                                                msg = new ResponseMsg(0, "Invalid Advance Payment!", null);
                                            }
                                        } else {

                                            msg = new ResponseMsg(0, "Invalid Discount", null);
                                        }

                                    } else {
                               
                                        msg = new ResponseMsg(0, "Order Finish Date Should be today or Future Date!", null);
                                    }

                                } else {
                                    msg = new ResponseMsg(0, "Select Order Finish Date", null);
                                }
                            } else {
                                msg = new ResponseMsg(0, "Select a Valid Customer", null);
                            }
                        } else {
                            msg = new ResponseMsg(0, "Invalid Contact Number", null);
                        }
                    } else {
                        msg = new ResponseMsg(0, "Select a Customer!", null);
                    }
                } else {
                    msg = new ResponseMsg(0, "Empty Order Identifier!", null);
                }

                if (isValidData) {
                    TypeToken token=new TypeToken<ArrayList<OrderItemBean>>(){};
                    Type type = token.getType();
                    
                    ArrayList<OrderItemBean> jsonOrderItemList= new Gson().fromJson(orderItemJsonArray,type);
                    
                    if(jsonOrderItemList.size() != 0){
                    boolean orderItemsValid=true;
                    ArrayList<OrderItem> orderItemList=new ArrayList<>();
                    double total=0.0;
                    
                    
                    for (OrderItemBean orderItemBean : jsonOrderItemList) {
                        SellingItems item=(SellingItems) hibernateSession.load(SellingItems.class,orderItemBean.getItemId());
                        if(item != null){
                            OrderItem  newOrderItem=new OrderItem();
                            newOrderItem.setSellingItems(item);
                            newOrderItem.setQty(orderItemBean.getItemQty());
                            newOrderItem.setUnitPrice(orderItemBean.getItemUnitPrice());
                            newOrderItem.setOrderItemDescription(orderItemBean.getItemDescription());
                            orderItemList.add(newOrderItem);
                            total=total+(orderItemBean.getItemQty()*orderItemBean.getItemUnitPrice());
                        }else{
                        orderItemsValid=false;
                        msg = new ResponseMsg(0, item.getIditem()+" is a invalid Item!!", null);
                        break;
                        }
                    }
                    
                        if (orderItemsValid) {
                            transaction=hibernateSession.beginTransaction();
                                 Order order=new Order();
                                 order.setCustomer(customer);
                                 order.setDate(new Date());
                                 order.setDeliverDate(orderFinishValidDate);
                                 order.setDescription(orderDescription);
                                 order.setDiscount(Double.parseDouble(orderDiscount));
                                 order.setSubtotal(total-Double.parseDouble(orderDiscount));
                                 order.setOrderStatus((OrderStatus) hibernateSession.load(OrderStatus.class, 1));
                                 order.setDueAmount(total-(Double.parseDouble(orderDiscount)+Double.parseDouble(orderAdvancePayment)));
                                 order.setTotal(total);
                                 order.setUniqueIdentifierText(orderIdentifier);
                                   
                                 hibernateSession.save(order);
                                 transaction.commit();
                                 
                                 for (OrderItem orderItem : orderItemList) {
                                orderItem.setOrder(order);
                                    transaction=hibernateSession.beginTransaction();
                                hibernateSession.save(orderItem);
                                     transaction.commit();
                            }
                             
                                 
                      //       printInvoice(order,orderItemList,response);    
                            JsonObject obj=new JsonObject();
                            obj.addProperty("id", order.getIdorder());
                             msg = new ResponseMsg(1, "Order Added Succesfully!!"+order.getIdorder(),obj);    
                                 
                        
                        }
                    
                    
                    }else{
                     msg = new ResponseMsg(0, "Empty Order Order Items", null);
                    }

                }

                //      }
//catch (TransactionException e) {
//                if (transaction != null) {
//                    transaction.rollback();
//                }
//                e.printStackTrace();
//            } catch (ParseException e) {
//                msg = new ResponseMsg(0, "Invalid Date!", null);
//                e.printStackTrace();
            } catch (Exception e) {
                msg = new ResponseMsg(0, "Invalid Data found!! check enterd enter Data", null);
                e.printStackTrace();
            } finally {
                if (hibernateSession.isOpen()) {

                    hibernateSession.flush();
                    hibernateSession.close();
                }
            }
        }

        response.getWriter().write(new Gson().toJson(msg));

    }



   

}
