/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crud;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import hibernate.NewHibernateUtil;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.EmployeeUpdateSchema;
import model.Message;
import model.ResponseMessage;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import hibernate.Employee;
import org.apache.commons.io.FilenameUtils;
import hibernate.EmployeeAddress;

/**
 *
 * @author OBITO
 */
@WebServlet(name = "employee", urlPatterns = {"/employee", "/employee/*", "/employee/find/*", "/employee/insert/*", "/employee/delete/*", "/employee/update/*", "/employee/enable/*", "/employee/updatephoto/*", "/employee/updatetype/*"})
public class employee extends HttpServlet {

    private static ResponseMessage respone_message = new ResponseMessage();
    private static List<Message> messages = new ArrayList<>();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();
        try {
            switch (action) {
                case "/employee/find":
                    findEmployee(request, response);
                    break;
                case "/employee/insert":
                    insertEmployee(request, response);
                    break;
                case "/employee/delete":
                    deleteEmployee(request, response);
                    break;
                case "/employee/enable":
                    enableEmployee(request, response);
                    break;
                case "/employee/updatetype":
                    updateType(request, response);
                    break;
                case "/employee/updatephoto":
                    updatePhoto(request, response);
                    break;
                case "/employee/update":
                    updateEmployee(request, response);
                    break;
                default:
                    listEmployee(request, response);
                    break;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void findEmployee(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        respone_message.setStatus("error");
        respone_message.setData(null);
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();

        if (request.getParameterMap().containsKey("id")) {

            if (data_validation()) {
                try {
                    SessionFactory sessionFactory = null;
                    try {
                        sessionFactory = NewHibernateUtil.getSessionFactory();
                    } catch (Throwable e) {

                    }
                    if (sessionFactory != null) {
                        Session openSession = sessionFactory.openSession();
                        Transaction beginTransaction = null;
                        try {
                            beginTransaction = openSession.beginTransaction();
                            if (checkIdIsNumber(request.getParameter("id"))) {

                                Criteria createCriteria = openSession.createCriteria(Employee.class);

                                createCriteria.add(Restrictions.eq("idemployee", getIntId(request.getParameter("id"))));
                                if (!createCriteria.list().isEmpty()) {

                                    Employee uniqueResult = (Employee) createCriteria.uniqueResult();
                                    JsonObject jsonObject = new JsonObject();
                                    jsonObject.addProperty("empid", uniqueResult.getIdemployee());
                                    jsonObject.addProperty("name", uniqueResult.getName());
                                    jsonObject.addProperty("email", uniqueResult.getEmail());
                                    jsonObject.addProperty("nic", uniqueResult.getNic());
                                    jsonObject.addProperty("contactno", uniqueResult.getContact());
                                    jsonObject.addProperty("address", uniqueResult.getEmployeeAddress().getNo());
                                    jsonObject.addProperty("address1", uniqueResult.getEmployeeAddress().getStreet1());
                                    jsonObject.addProperty("address2", uniqueResult.getEmployeeAddress().getStreet1());
                                    jsonObject.addProperty("addresscity", uniqueResult.getEmployeeAddress().getCity());
                                    jsonObject.addProperty("status", uniqueResult.getEmployeeStatus());
                                    jsonObject.addProperty("type", uniqueResult.getType());
                                    respone_message.setStatus("success");
                                    respone_message.setData(jsonObject);
                                }
                                beginTransaction.commit();
                                openSession.flush();
                                openSession.close();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            if (beginTransaction != null) {
                                beginTransaction.rollback();
                            }
                        } finally {
                            if (openSession.isOpen()) {
                                openSession.close();
                            }
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        send_response(gson, response);
    }

    private void insertEmployee(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        respone_message.setStatus("error");
        respone_message.setData(null);
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();

        String realPath = request.getServletContext().getRealPath("");
        DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory(); // storeing mem or disk (temp)
        ServletFileUpload servletFileUpload = new ServletFileUpload(diskFileItemFactory); //seperating
        List<FileItem> fileList = null;
        String savefilename = "";
        boolean canthis = false;

        try {
            fileList = servletFileUpload.parseRequest(request); // seperated parts
            canthis = true;
        } catch (FileUploadException ex) {
            canthis = false;
            System.out.println("Empty Request");
            respone_message.setStatus("error");
            respone_message.setData(new String("Please fill all the required fileds"));
        }
        boolean passthisimage = false;

        if (canthis) {
            String name = "";
            String nic = "";
            String email = "";
            String contactno = "";
            String address = "";
            String address1 = "";
            String address2 = "";
            String addresscity = "";
            String type = "";
            for (FileItem fileItem : fileList) {
                if (fileItem.isFormField()) {
                    if (fileItem.getFieldName().equals("name")) {
                        name = fileItem.getString();
                        if (name.length() == 0) {
                            respone_message.setStatus("error");
                            respone_message.setData(new String("Employee name cannot be empty"));
                            canthis = false;
                        }
                    } else if (fileItem.getFieldName().equals("type")) {
                        type = fileItem.getString();
                        if (type.isEmpty() || type.equalsIgnoreCase("none")) {
                            respone_message.setStatus("error");
                            respone_message.setData(new String("Employee type cannot be empty"));
                            canthis = false;
                        }
                    } else if (fileItem.getFieldName().equals("email")) {
                        email = fileItem.getString();
                        if (email.isEmpty() || type.equalsIgnoreCase("none")) {
                            respone_message.setStatus("error");
                            respone_message.setData(new String("Employee email cannot be empty"));
                            canthis = false;
                        }
                    } else if (fileItem.getFieldName().equals("nic")) {
                        nic = fileItem.getString();
                        if (nic.isEmpty()) {
                            respone_message.setStatus("error");
                            respone_message.setData(new String("Employee nic cannot be empty"));
                            canthis = false;
                        }
                        if (!(nic.trim().matches("^([0-9]{9}[x|X|v|V]|[0-9]{12})$"))) {
                            respone_message.setStatus("error");
                            respone_message.setData(new String("Invalid Employee nic"));
                            canthis = false;
                        }
                    } else if (fileItem.getFieldName().equals("contactno")) {
                        contactno = fileItem.getString();
                        if (name.length() == 0) {
                            respone_message.setStatus("error");
                            respone_message.setData(new String("Employee contact no cannot be empty"));
                            canthis = false;
                        }
                        if (!(contactno.trim().matches("^[0-9]*$"))) {
                            respone_message.setStatus("error");
                            respone_message.setData(new String("Invalid Employee contact no"));
                            canthis = false;
                        }

                    } else if (fileItem.getFieldName().equals("address")) {
                        address = fileItem.getString();
                    } else if (fileItem.getFieldName().equals("address1")) {
                        address1 = fileItem.getString();
                    } else if (fileItem.getFieldName().equals("address2")) {
                        address2 = fileItem.getString();
                    } else if (fileItem.getFieldName().equals("addresscity")) {
                        addresscity = fileItem.getString();
                    }
                } else if (fileItem.getFieldName().equals("employeeimage")) {
                    if (fileItem.getSize() != 0) {
                        savefilename = System.currentTimeMillis() + "-" + fileItem.getName();
                        File file = new File(realPath + "//static//employees//images//" + savefilename);
                        try {
                            fileItem.write(file); // write to disk
                            BufferedImage bi = ImageIO.read(fileItem.getInputStream());
                            ImageIO.write(bi, FilenameUtils.getExtension(fileItem.getName()), file);
                            passthisimage = true;
                        } catch (Exception ex) {
                            respone_message.setStatus("error");
                            respone_message.setData(new String("Something Went Wrong!Please Try Again!"));
                        }
                    }
                }
            }
            if (canthis) {
                try {
                    SessionFactory sessionFactory = null;
                    try {
                        sessionFactory = NewHibernateUtil.getSessionFactory();
                    } catch (Throwable e) {

                    }
                    if (sessionFactory != null) {
                        Session openSession = sessionFactory.openSession();
                        Transaction beginTransaction = null;
                        try {
                            beginTransaction = openSession.beginTransaction();
                            Criteria createCriteria = openSession.createCriteria(Employee.class);
                            createCriteria.add(Restrictions.eq("nic", nic));
                            if (createCriteria.list().isEmpty()) {
                                createCriteria = openSession.createCriteria(Employee.class);
                                createCriteria.add(Restrictions.eq("email", email));
                                if (createCriteria.list().isEmpty()) {
                                    Employee employee = new Employee();
                                    employee.setContact(contactno);
                                    employee.setName(name);
                                    employee.setNic(nic);
                                    employee.setEmail(email);
                                    if (type.equalsIgnoreCase("none")) {
                                        employee.setType(1);
                                    } else if (type.equalsIgnoreCase("0")) {
                                        employee.setType(0);
                                    } else if (type.equalsIgnoreCase("1")) {
                                        employee.setType(1);
                                    } else if (type.equalsIgnoreCase("2")) {
                                        employee.setType(2);
                                    } else if (type.equalsIgnoreCase("3")) {
                                        employee.setType(3);
                                    } else {
                                        employee.setType(1);
                                    }
                                    if (passthisimage) {
                                        employee.setPhoto(savefilename);
                                    } else {
                                        employee.setPhoto("default.png");
                                    }
                                    EmployeeAddress employeeAddress = new EmployeeAddress();
                                    employeeAddress.setCity(addresscity);
                                    employeeAddress.setNo(address);
                                    employeeAddress.setStreet1(address1);
                                    employeeAddress.setStreet2(address2);
                                    employee.setEmployeeStatus("1");
                                    employee.setEmployeeAddress(employeeAddress);
                                    openSession.save(employeeAddress);
                                    openSession.save(employee);
                                    respone_message.setStatus("success");
                                    respone_message.setData(new String("Employee saved"));
                                } else {
                                    respone_message.setStatus("error");
                                    respone_message.setData(new String("Employee with this email is already added to the database"));
                                }
                            } else {
                                respone_message.setStatus("error");
                                respone_message.setData(new String("Employee with this nic is already added to the database"));
                            }

                            beginTransaction.commit();
                            openSession.flush();
                            openSession.close();
                        } catch (Exception e) {
                            e.printStackTrace();
                            if (beginTransaction != null) {
                                beginTransaction.rollback();
                            }
                        } finally {
                            if (openSession.isOpen()) {
                                openSession.close();
                            }
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }
        send_response(gson, response);
    }

    private void deleteEmployee(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        respone_message.setStatus("error");
        respone_message.setData(null);
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        if (request.getParameterMap().containsKey("id")) {
            if (data_validation()) {
                try {
                    SessionFactory sessionFactory = null;
                    try {
                        sessionFactory = NewHibernateUtil.getSessionFactory();
                    } catch (Throwable e) {

                    }
                    if (sessionFactory != null) {
                        Session openSession = sessionFactory.openSession();
                        Transaction beginTransaction = null;
                        try {
                            beginTransaction = openSession.beginTransaction();
                            if (checkIdIsNumber(request.getParameter("id"))) {
                                Criteria createCriteria = openSession.createCriteria(Employee.class);
                                createCriteria.add(Restrictions.eq("idemployee", getIntId(request.getParameter("id"))));
                                if (!createCriteria.list().isEmpty()) {
                                    Employee uniqueResult = (Employee) createCriteria.uniqueResult();
                                    if (!uniqueResult.getEmployeeStatus().equals("0")) {
                                        uniqueResult.setEmployeeStatus("0");
                                        openSession.update(uniqueResult);
                                        respone_message.setStatus("success");
                                    }
                                }
                                beginTransaction.commit();
                                openSession.flush();
                                openSession.close();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            if (beginTransaction != null) {
                                beginTransaction.rollback();
                            }
                        } finally {
                            if (openSession.isOpen()) {
                                openSession.close();
                            }
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        send_response(gson, response);
    }

    private void updateEmployee(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        respone_message.setStatus("error");
        respone_message.setData(null);
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        if (request.getParameterMap().containsKey("form_data")) {
            EmployeeUpdateSchema employeeUpdateSchema = gson.fromJson(request.getParameter("form_data"), EmployeeUpdateSchema.class);
            if (data_validation()) {
                try {
                    SessionFactory sessionFactory = null;
                    try {
                        sessionFactory = NewHibernateUtil.getSessionFactory();
                    } catch (Throwable e) {

                    }
                    if (sessionFactory != null) {
                        Session openSession = sessionFactory.openSession();
                        Transaction beginTransaction = null;
                        try {
                            beginTransaction = openSession.beginTransaction();
                            if (checkIdIsNumber(employeeUpdateSchema.getEmpid().toString())) {
                                Criteria createCriteria = openSession.createCriteria(Employee.class);
                                createCriteria.add(Restrictions.eq("idemployee", employeeUpdateSchema.getEmpid()));
                                if (!createCriteria.list().isEmpty()) {
                                    Employee employee = (Employee) createCriteria.uniqueResult();
                                    boolean noError = true;
                                    if (!employeeUpdateSchema.getName().isEmpty()) {
                                        employee.setName(employeeUpdateSchema.getName());
                                    }
                                    if (!employeeUpdateSchema.getContactno().isEmpty()) {
                                        employee.setContact(employeeUpdateSchema.getContactno());
                                    }
                                    if (!employeeUpdateSchema.getNic().isEmpty()) {
                                        createCriteria = openSession.createCriteria(Employee.class);
                                        createCriteria.add(Restrictions.eq("nic", employeeUpdateSchema.getNic()));
                                        Employee uniqueResult = (Employee) createCriteria.uniqueResult();
                                        if (createCriteria.list().isEmpty()) {
                                            employee.setNic(employeeUpdateSchema.getNic());
                                        } else if (employeeUpdateSchema.getEmpid() == uniqueResult.getIdemployee()) {
                                            employee.setNic(employeeUpdateSchema.getNic());
                                        } else {
                                            noError = false;
                                            respone_message.setData(new String("NIC exsits!"));
                                        }
                                    }
                                    if (!employeeUpdateSchema.getEmail().isEmpty()) {
                                        createCriteria = openSession.createCriteria(Employee.class);
                                        createCriteria.add(Restrictions.eq("email", employeeUpdateSchema.getEmail()));
                                        Employee uniqueResult = (Employee) createCriteria.uniqueResult();
                                        if (createCriteria.list().isEmpty()) {
                                            employee.setEmail(employeeUpdateSchema.getEmail());
                                        } else if (employeeUpdateSchema.getEmpid() == uniqueResult.getIdemployee()) {
                                            employee.setEmail(employeeUpdateSchema.getEmail());
                                        } else {
                                            noError = false;
                                            respone_message.setData(new String("Email exsits!"));
                                        }
                                    }
                                    if (!employeeUpdateSchema.getAddress().isEmpty()) {
                                        employee.getEmployeeAddress().setNo(employeeUpdateSchema.getAddress());
                                    }
                                    if (!employeeUpdateSchema.getAddress1().isEmpty()) {
                                        employee.getEmployeeAddress().setStreet1(employeeUpdateSchema.getAddress1());
                                    }
                                    if (!employeeUpdateSchema.getAddress2().isEmpty()) {
                                        employee.getEmployeeAddress().setStreet2(employeeUpdateSchema.getAddress2());
                                    }
                                    if (!employeeUpdateSchema.getAddresscity().isEmpty()) {
                                        employee.getEmployeeAddress().setCity(employeeUpdateSchema.getAddresscity());
                                    }
                                    if (noError) {
                                        openSession.update(employee);
                                        respone_message.setStatus("success");
                                    } else {
                                        respone_message.setStatus("error");
                                    }
                                }
                                beginTransaction.commit();
                                openSession.flush();
                                openSession.close();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            if (beginTransaction != null) {
                                beginTransaction.rollback();
                            }
                        } finally {
                            if (openSession.isOpen()) {
                                openSession.close();
                            }
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        send_response(gson, response);
    }

    private void listEmployee(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();

        JsonObject respone_obj = new JsonObject();
        respone_obj.addProperty("status", "error");
        respone_obj.add("data", new JsonArray());

        try {
            SessionFactory sessionFactory = null;
            try {
                sessionFactory = NewHibernateUtil.getSessionFactory();
            } catch (Throwable e) {
                e.printStackTrace();
            }
            if (sessionFactory != null) {
                Session openSession = sessionFactory.openSession();
                Transaction beginTransaction = null;
                try {
                    beginTransaction = openSession.beginTransaction();
                    Criteria createCriteria = openSession.createCriteria(Employee.class);
                    System.out.println(createCriteria.list().size());
                    if (!createCriteria.list().isEmpty()) {
                        List<Employee> list = createCriteria.list();
                        respone_obj.addProperty("status", "success");
                        JsonArray jsonArray = new JsonArray();
                        for (Employee employee : list) {
                            JsonObject jsonObject = new JsonObject();
                            jsonObject.addProperty("id", employee.getIdemployee().toString());
                            jsonArray.add(jsonObject);
                        }
                        respone_obj.add("data", jsonArray);
                    } else {
                        respone_obj.addProperty("status", "error");
                    }
                    beginTransaction.commit();
                    openSession.flush();
                    openSession.close();
                } catch (Exception e) {
                    if (beginTransaction != null) {
                        beginTransaction.rollback();
                    }
                } finally {
                    if (openSession.isOpen()) {
                        openSession.close();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        send_response(gson, response, respone_obj);
    }

    private void enableEmployee(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        respone_message.setStatus("error");
        respone_message.setData(new String(""));
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        if (request.getParameterMap().containsKey("id")) {
            if (data_validation()) {
                try {
                    SessionFactory sessionFactory = null;
                    try {
                        sessionFactory = NewHibernateUtil.getSessionFactory();
                    } catch (Throwable e) {

                    }
                    if (sessionFactory != null) {
                        Session openSession = sessionFactory.openSession();
                        Transaction beginTransaction = null;
                        try {
                            beginTransaction = openSession.beginTransaction();
                            if (checkIdIsNumber(request.getParameter("id"))) {
                                Criteria createCriteria = openSession.createCriteria(Employee.class);
                                createCriteria.add(Restrictions.eq("idemployee", getIntId(request.getParameter("id"))));
                                if (!createCriteria.list().isEmpty()) {
                                    Employee uniqueResult = (Employee) createCriteria.uniqueResult();
                                    if (uniqueResult.getEmployeeStatus().equals("0")) {
                                        uniqueResult.setEmployeeStatus("1");
                                        openSession.update(uniqueResult);
                                        respone_message.setStatus("success");
                                    }
                                }
                                beginTransaction.commit();
                                openSession.flush();
                                openSession.close();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            if (beginTransaction != null) {
                                beginTransaction.rollback();
                            }
                        } finally {
                            if (openSession.isOpen()) {
                                openSession.close();
                            }
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        send_response(gson, response);
    }

    private void updatePhoto(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        respone_message.setStatus("error");
        respone_message.setData(new String(""));
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();

        String realPath = request.getServletContext().getRealPath("");
        DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory(); // storeing mem or disk (temp)
        ServletFileUpload servletFileUpload = new ServletFileUpload(diskFileItemFactory); //seperating
        List<FileItem> fileList = null;
        String savefilename = "";
        boolean canthis = false;

        try {
            fileList = servletFileUpload.parseRequest(request); // seperated parts
            canthis = true;
        } catch (FileUploadException ex) {
            canthis = false;
            System.out.println("Empty Request");
            respone_message.setStatus("error");
            respone_message.setData(new String("Please fill all the required fileds"));
        }
        boolean passthisimage = false;

        if (canthis) {
            for (FileItem fileItem : fileList) {
                if (fileItem.getFieldName().equals("employeeimage")) {
                    if (fileItem.getSize() != 0) {
                        savefilename = System.currentTimeMillis() + "-" + fileItem.getName();
                        File file = new File(realPath + "//static//employees//images//" + savefilename);
                        try {
                            fileItem.write(file); // write to disk
                            BufferedImage bi = ImageIO.read(fileItem.getInputStream());
                            ImageIO.write(bi, FilenameUtils.getExtension(fileItem.getName()), file);
                            passthisimage = true;
                        } catch (Exception ex) {
                            respone_message.setStatus("error");
                            respone_message.setData(new String("Something Went Wrong!Please Try Again!"));
                        }
                    }
                }
            }
            if (canthis) {
                if (request.getParameterMap().containsKey("id")) {
                    if (data_validation()) {
                        try {
                            SessionFactory sessionFactory = null;
                            try {
                                sessionFactory = NewHibernateUtil.getSessionFactory();
                            } catch (Throwable e) {

                            }
                            if (sessionFactory != null) {
                                Session openSession = sessionFactory.openSession();
                                Transaction beginTransaction = null;
                                try {
                                    beginTransaction = openSession.beginTransaction();
                                    if (checkIdIsNumber(request.getParameter("id"))) {
                                        Criteria createCriteria = openSession.createCriteria(Employee.class);
                                        createCriteria.add(Restrictions.eq("idemployee", getIntId(request.getParameter("id"))));
                                        if (!createCriteria.list().isEmpty()) {
                                            System.out.println(passthisimage);
                                            if (passthisimage) {
                                                Employee uniqueResult = (Employee) createCriteria.uniqueResult();
                                                uniqueResult.setPhoto(savefilename);
                                                respone_message.setStatus("success");
                                            }
                                        }
                                        beginTransaction.commit();
                                        openSession.flush();
                                        openSession.close();
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    if (beginTransaction != null) {
                                        beginTransaction.rollback();
                                    }
                                } finally {
                                    if (openSession.isOpen()) {
                                        openSession.close();
                                    }
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        send_response(gson, response);
    }

    private void updateType(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        respone_message.setStatus("error");
        respone_message.setData(new String(""));
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        if (request.getParameterMap().containsKey("id")) {
            if (request.getParameterMap().containsKey("type")) {
                try {
                    SessionFactory sessionFactory = null;
                    try {
                        sessionFactory = NewHibernateUtil.getSessionFactory();
                    } catch (Throwable e) {

                    }
                    if (sessionFactory != null) {
                        Session openSession = sessionFactory.openSession();
                        Transaction beginTransaction = null;
                        try {
                            beginTransaction = openSession.beginTransaction();
                            if (checkIdIsNumber(request.getParameter("id"))) {
                                Criteria createCriteria = openSession.createCriteria(Employee.class);
                                createCriteria.add(Restrictions.eq("idemployee", getIntId(request.getParameter("id"))));
                                if (!createCriteria.list().isEmpty()) {
                                    Employee employee = (Employee) createCriteria.uniqueResult();
                                    String type = request.getParameter("type");
                                    if (type.equalsIgnoreCase("none")) {
                                        employee.setType(1);
                                    } else if (type.equalsIgnoreCase("0")) {
                                        employee.setType(0);
                                    } else if (type.equalsIgnoreCase("1")) {
                                        employee.setType(1);
                                    } else if (type.equalsIgnoreCase("2")) {
                                        employee.setType(2);
                                    } else if (type.equalsIgnoreCase("3")) {
                                        employee.setType(3);
                                    } else {
                                        employee.setType(1);
                                    }

                                    openSession.update(employee);
                                    respone_message.setStatus("success");

                                }
                                beginTransaction.commit();
                                openSession.flush();
                                openSession.close();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            if (beginTransaction != null) {
                                beginTransaction.rollback();
                            }
                        } finally {
                            if (openSession.isOpen()) {
                                openSession.close();
                            }
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                respone_message.setStatus("error");
                respone_message.setData(new String("No Employee Type selected"));
            }
        } else {
            respone_message.setStatus("error");
            respone_message.setData(new String("No Employee selected"));
        }
        send_response(gson, response);
    }

    private void send_response(Gson gson, HttpServletResponse response, JsonObject respone_obj) throws IOException {
        response.getWriter().write(gson.toJson(respone_obj));
    }

    private void send_response(Gson gson, HttpServletResponse response) throws IOException {
        response.getWriter().write(gson.toJson(respone_message));
    }

    private boolean data_validation() {
        return true;
    }

    private int getIntId(String id) {
        int parseInt = 0;
        try {
            parseInt = Integer.parseInt(id);
        } catch (Exception e) {

        }
        return parseInt;
    }

    private boolean checkIdIsNumber(String id) {
        Pattern pattern = Pattern.compile(".*[^0-9].*");
        return !pattern.matcher(id).matches();
    }
}
