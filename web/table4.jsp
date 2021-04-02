<%@page import="hibernate.Supplier"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="hibernate.RawItem"%>
<%@page import="hibernate.Brand"%>
<%@page import="hibernate.SellingItems"%>
<%@page import="java.util.List"%>


<table id="suppTable" class="table table-striped table-bordered" style="width:100%">
    <thead>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Contact</th>
            
        </tr>
    </thead>


    <tbody>
        <%
            List<Supplier> sList = (List<Supplier>) request.getAttribute("sList");
            for (Supplier s : sList) {
                
        %>

        <tr>
            <td ><%= s.getIdsupplier() %></td>
            <td ><%= s.getName()%></td>
            <td ><%= s.getContact() %></td>
           
        </tr>


        <%
            }
        %>

    </tbody>
</table>

