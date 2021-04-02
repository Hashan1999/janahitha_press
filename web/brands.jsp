<%@page import="hibernate.Brand"%>
<%@page import="hibernate.SellingItems"%>
<%@page import="java.util.List"%>

<select id="selectBrand" class="form-control" required>
                                                            
                                                            
                                                       

        <%
            List<Brand> sList = (List<Brand>) request.getAttribute("sList");
            for (Brand s : sList) {
        %>

        <option value="<%= s.getName() %>"><%= s.getName() %></option>


        <%
            }
        %>


 </select>