<%-- 
    Document   : hd_customer_Registration
    Created on : Sep 21, 2020, 4:29:19 PM
    Author     : HASHAN
--%>


<%@page import="hibernate.SellingItems"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    SellingItems item= null;
    if(request.getAttribute("item") != null){
item=(SellingItems)request.getAttribute("item");
}
%>
<!DOCTYPE html>
<div class="col-md-12 col-sm-12 ">
							<div class="x_panel">
								<div class="x_title">
									<h2>Update Selling Items <small>update your  Selling Items here</small></h2>
									
									<div class="clearfix"></div>
								</div>
								<div class="x_content">
									<br>
                                                                        <%
                                                                        if(item != null){
                                                                        %>
                                                                        
                                                                        <form id="customer-update-form" onsubmit="updateSellingProduct(<%= item.getIditem() %>);return false" data-parsley-validate class="form-horizontal form-label-left" >

										<div class="item form-group">
											<label class="col-form-label col-md-3 col-sm-3 label-align" for="update-name">Name <span class="required">*</span>
											</label>
											<div class="col-md-6 col-sm-6 ">
                                                                                            <input required="" type="text" id="update-name"  class="form-control" value="<%= item.getName() %>">
											</div>
										</div>
										
											 <div class="item form-group">
                                                <label class="col-form-label col-md-3 col-sm-3 label-align">Unit Price</label><br>
                                                <div class="col-md-6 col-sm-6 ">
                                                    <input  id="update-price" parsley-type="float" value="<%= item.getPrice() %>" class="form-control col-md-6"  placeholder="0" min="0.000001" data-parsley-validate  data-parsley-validation-threshold="1" data-parsley-trigger="change" 
    data-parsley-pattern="^[+-]?([0-9]+([.][0-9]*)?|[.][0-9]+)$"/>
                                                </div>
                                            </div> 
										
									
									
										<div class="ln_solid"></div>
										<div class="item form-group">
											<div class="col-md-6 col-sm-6 offset-md-3">
												<button class="btn btn-primary" type="reset">Reset</button>
												
                                                                                                <button type="submit" class="btn btn-success">Submit</button>
											</div>
										</div>

									</form>
                                                                        <%
                                                                        }
                                                                        %>
								</div>
							</div>
						</div>