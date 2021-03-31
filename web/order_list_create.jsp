<%-- 
    Document   : order_list_create
    Created on : Dec 26, 2020, 2:11:17 PM
    Author     : Pasindu Maduranga
--%>

<%@page import="hibernate.Order"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<select class="form-control" id="select_order" onchange="set_order_desctiption()">
    <option value="null" selected="selected"></option>
    
    <%
       
      
        List<Order> order_list =(List<Order>)request.getAttribute("Order_list");
        for (Order o : order_list) {
    %>

    <option value="<%= o.getIdorder()%>"><%= o.getUniqueIdentifierText()%></option>
    <%
        }
    %>

</select>