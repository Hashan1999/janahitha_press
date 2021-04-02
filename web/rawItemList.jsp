<%@page import="hibernate.RawItem"%>
<%@page import="hibernate.Supplier"%>
<%@page import="hibernate.SellingItems"%>
<%@page import="java.util.List"%>



   
<select class="select2_single form-control" id="rawItemList" >
													
                                                                                                    
												

   
        <%
            List<RawItem> sList = (List<RawItem>) request.getAttribute("sList");
            for (RawItem s : sList) {
        %>

        <option><%= s.getName() %></option>
        


        <%
            }
        %>

   
</select>

