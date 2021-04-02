<%@page import="hibernate.Supplier"%>
<%@page import="hibernate.SellingItems"%>
<%@page import="java.util.List"%>



   
<select class="select2_single form-control" id="supplierList">
													
                                                                                                    
												

   
        <%
            List<Supplier> sList = (List<Supplier>) request.getAttribute("sList");
            for (Supplier s : sList) {
        %>

        <option><%= s.getName()%></option>
        


        <%
            }
        %>

   
</select>

