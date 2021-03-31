<%-- 
    Document   : hd_customer_Registration
    Created on : Sep 21, 2020, 4:29:19 PM
    Author     : HASHAN
--%>

<%@page import="hibernate.Customer"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    Customer customer= null;
    if(request.getAttribute("customer") != null){
customer=(Customer)request.getAttribute("customer");
}
%>
<!DOCTYPE html>
<div class="col-md-12 col-sm-12 ">
							<div class="x_panel">
								<div class="x_title">
									<h2>Update Customer <small>update your  customer here</small></h2>
									
									<div class="clearfix"></div>
								</div>
								<div class="x_content">
									<br>
                                                                        <%
                                                                        if(customer != null){
                                                                        %>
                                                                        
                                                                        <form id="customer-update-form" onsubmit="updateCustomer(<%= customer.getIdcustomer() %>);return false" data-parsley-validate class="form-horizontal form-label-left" >

										<div class="item form-group">
											<label class="col-form-label col-md-3 col-sm-3 label-align" for="first-name">First Name <span class="required">*</span>
											</label>
											<div class="col-md-6 col-sm-6 ">
                                                                                            <input required="" type="text" id="update-first-name"  class="form-control" value="<%= customer.getFirstName() %>">
											</div>
										</div>
										<div class="item form-group">
											<label class="col-form-label col-md-3 col-sm-3 label-align" for="last-name">Last Name <span class="required">*</span>
											</label>
											<div class="col-md-6 col-sm-6 ">
                                                                                            <input required="" type="text" id="update-last-name" name="last-name" class="form-control" value="<%= customer.getLastName() %>">
											</div>
										</div>
										<div class="item form-group">
											<label for="contact" class="col-form-label col-md-3 col-sm-3 label-align">Contact Number</label>
											<div class="col-md-6 col-sm-6 ">
                                                                                            <input required="" id="update-contact" class="form-control"  type="tel" name="contact" value="<%= customer.getContact() %>">
											</div>
										</div>
										<div class="item form-group">
											<label for="description" class="col-form-label col-md-3 col-sm-3 label-align">Description</label>
											<div class="col-md-6 col-sm-6 ">
												<textarea id="update-description"  class="form-control" name="description" data-parsley-trigger="keyup" ><%= customer.getDescription() %></textarea>
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