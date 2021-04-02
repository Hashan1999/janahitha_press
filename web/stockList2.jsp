<%@page import="hibernate.Stock"%>
<%@page import="java.util.List"%>
<%@page import="java.util.List"%>


<table id="stocktable2" class="table table-striped table-bordered" style="width:100%" >
                                        
                                        
                                        <thead>
                                            <tr>
                                                <th>Id</th>
                                                <th>Date</th>
                                                <th>Item</th>
                                                <th>Quantity</th>
                                                <th>Grn</th>
                                                <th>Status</th>

                                            </tr>
                                            
                                        </thead>
                                        
                                        
                                  

<%
  List<Stock> stock = (List<Stock>) request.getAttribute("sList");
                                                
                                                for(Stock s : stock) {
                                               
                                               
                                                
                                              %>
                                                
                                                <tr>
                                                    <td><%= s.getIdstock() %></td>
                                                    <td><%= s.getAddedDate() %></td>
                                                    <td><%= s.getRawItem().getName() %></td>
                                                    <td><%= s.getQty() %></td>
                                                    <td><%= s.getGrn().getIdgrn() %></td>
                                                    <% if(s.getStatus()==1){ %>
                                                    
                                                    <td ><button onclick="stockStatusChange(<%= s.getIdstock() %>)">active</button></td>
                                                    <% }else{ %>
                                                    <td ><button onclick="stockStatusChange(<%= s.getIdstock() %>)">deactivated</button></td>
                                                    <% } %>
                                                </tr>
                                            
                                            <%    
                                                }


                                            %>
                                            
     </table>                                         