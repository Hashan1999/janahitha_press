<%@page import="hibernate.Stock"%>
<%@page import="java.util.List"%>
<%@page import="java.util.List"%>


<table id="stocktable" class="table table-striped table-bordered" style="width:100%" >
                                        
                                        
                                        <thead>
                                            <tr>
                                                <th>Id</th>
                                                <th>Date</th>
                                                <th>Item</th>
                                                <th>Quantity</th>
                                                <th>Grn</th>
                                                <th>Out Quantity</th>

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
                                                    <td ><input type="text" oninput="this.value=this.value.replace(/[^\d]/,'')" id="<%= s.getIdstock() %>" onclick="addRaw(<%= s.getIdstock() %>)" title="type quantity and press enter"></td>
                                                </tr>
                                            
                                            <%    
                                                }


                                            %>
                                            
     </table>                                         