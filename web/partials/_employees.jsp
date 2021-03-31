<%-- 
    Document   : _employees
    Created on : Mar 24, 2021, 1:43:38 PM
    Author     : OBITO
--%>

<%@page import="java.util.List"%>
<%@page import="org.hibernate.Transaction"%>
<%@page import="org.hibernate.criterion.Restrictions"%>
<%@page import="hibernate.Employee"%>
<%@page import="org.hibernate.Criteria"%>
<%@page import="org.hibernate.Session"%>
<%@page import="hibernate.NewHibernateUtil"%>
<%@page import="org.hibernate.SessionFactory"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<table id="datatable-responsive" class="table table-striped table-bordered dt-responsive nowrap text-center" cellspacing="0" width="100%">
    <thead>
        <tr>
            <th class="text-center">ID</th>
            <th class="text-center">Name</th>
            <th class="text-center">Email</th>
            <th class="text-center">NIC</th>
            <th class="text-center">Photo</th>
            <th class="text-center" >Contact No.</th>
            <th class="text-center">Address</th>
            <th class="text-center">Employee Type</th>
            <th class="text-center">Status</th>
            <th class="text-center"></th>
        </tr>
    </thead>
    <tbody>
        <%
            SessionFactory sessionFactory = NewHibernateUtil.getSessionFactory();
            Session openSession = sessionFactory.openSession();
            Criteria createCriteria = openSession.createCriteria(Employee.class);
            Transaction beginTransaction = openSession.beginTransaction();
            try {
                if (!createCriteria.list().isEmpty()) {
                    List<Employee> employees = createCriteria.list();
        %>

        <%                                                        if (!employees.isEmpty()) {
                for (Employee employee : employees) {

        %>
        <tr>
            <td><%= employee.getIdemployee().toString()%></td>
            <td><%= employee.getName()%></td>
            <td><%= employee.getEmail()%></td>
            <td><%= employee.getNic()%></td>
            <td>
                <a href="<%= request.getContextPath()%>/static/employees/images/<%= employee.getPhoto()%>" data-toggle="lightbox">
                    <label class="badge  badge bg-green" style="cursor: pointer;">
                        View Image
                    </label>
                </a>
            </td>
            <td><%= employee.getContact()%></td>
            <td><%= employee.getEmployeeAddress().getNo() + " " + employee.getEmployeeAddress().getStreet1() + " " + employee.getEmployeeAddress().getStreet2() + " " + employee.getEmployeeAddress().getCity()%></td>
            <td><%
                if (employee.getType().toString().equalsIgnoreCase("0")) {
                    out.write("Administrator");
                } else if (employee.getType().toString().equalsIgnoreCase("1")) {
                    out.write("Employee");
                } else if (employee.getType().toString().equalsIgnoreCase("2")) {
                    out.write("Accountant");
                } else if (employee.getType().toString().equalsIgnoreCase("3")) {
                    out.write("Cashier");
                } else {
                    out.write("INVALID");
                }
                %></td>
            <td>
                <label class="badge  <%                                                                String badge2 = "";
                    if (employee.getEmployeeStatus().equals("1")) {
                        badge2 = "badge bg-green";
                    } else {
                        badge2 = "badge bg-red";
                    }
                    out.write(badge2);
                       %>">
                    <%      String status2 = "";
                        if (employee.getEmployeeStatus().equals("1")) {
                            status2 = "Active";
                        } else {
                            status2 = "Inactive";
                        }
                        out.write(status2);
                    %>
                </label>
            </td>
            <td>
                <span class="glyphicon glyphicon-edit" onclick="findSingle(<%= employee.getIdemployee().toString()%>);" style="font-size: 1.2em;margin-right: 5px;cursor: pointer;"></span>
                <i class="fa fa-file-image-o" style="font-size: 1.2em;margin-right: 5px;cursor: pointer;" onclick="updatePhoto(<%= employee.getIdemployee().toString()%>);"></i>
                <i class="fa fa-users" style="font-size: 1.2em;margin-right: 5px;cursor: pointer;" onclick="updateType(<%= employee.getIdemployee().toString()%>);"></i>
            </td>
        </tr>
        <%                }
            }
        %>
        <%
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

        %>
    </tbody>
</table>