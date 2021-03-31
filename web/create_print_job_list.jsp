<%-- 
    Document   : create_print_job_list
    Created on : Feb 14, 2021, 7:19:00 PM
    Author     : Pasindu Maduranga
--%>

<%@page import="hibernate.Order"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="hibernate.PlateHasPrintingJob"%>
<%@page import="hibernate.Customer"%>
<%@page import="hibernate.Plate"%>
<%@page import="hibernate.PrintingJob"%>
<%@page import="java.util.List"%>
<%@page import="org.hibernate.criterion.Restrictions"%>
<%@page import="hibernate.MachineStatus"%>
<%@page import="hibernate.NewHibernateUtil"%>
<%@page import="org.hibernate.Session"%>
<%@page import="org.hibernate.Criteria"%>
<%@page import="hibernate.Machine"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    if (request.getAttribute("job") != null) {
        Session s = NewHibernateUtil.getSessionFactory().openSession();
        PrintingJob printJob = (PrintingJob) request.getAttribute("job");


%>


<form id="demo-form3" data-parsley-validate class="form-horizontal form-label-left">

    <div class="item form-group">
        <label class="col-form-label col-md-3 col-sm-3 label-align" for="first-name">Job ID<span class="required">*</span>
        </label>
        <div class="col-md-6 col-sm-6 ">

            <input type="text" id="print_job3" required="required" class="form-control " value="<%= printJob.getIdprintingJob()%>"  autocomplete="off" readonly>
        </div>
    </div>

    <div class="item form-group">
        <label class="col-form-label col-md-3 col-sm-3 label-align" for="first-name">Customer Mobile<span class="required">*</span>
        </label>
        <div class="col-md-6 col-sm-6 ">
            <%
                String customer_name = printJob.getOrder().getCustomer().getFirstName() + " " + printJob.getOrder().getCustomer().getLastName();
                String customer_mobile = printJob.getOrder().getCustomer().getContact();
            %>
            <input type="text" id="customer_mobile2" required="required" class="form-control " autocomplete="off" value="<%= customer_mobile%>" readonly="">
        </div>
    </div>


    <div class="item form-group">
        <label class="col-form-label col-md-3 col-sm-3 label-align" for="first-name">Customer Name<span class="required">*</span>
        </label>
        <div class="col-md-6 col-sm-6 ">
            <input type="text" id="customer_name2" required="required" class="form-control " autocomplete="off" value="<%= customer_name%>" readonly="">
        </div>
    </div>


    <div class="item form-group">
        <label class="col-form-label col-md-3 col-sm-3 label-align" for="first-name">Registered Plate<span class="required">*</span>
        </label>
        <div class="col-md-6 col-sm-6 ">
            <select class="form-control" id="selected_val2" onchange="set_description2();">
                <option value="null" selected="selected"></option>
                <%
                    Customer customer = printJob.getOrder().getCustomer();
                    Criteria plate_critera = s.createCriteria(Plate.class);

                    plate_critera.add(Restrictions.eq("customer", customer));

                    Criteria customer_plate_critera = s.createCriteria(PlateHasPrintingJob.class);
                    customer_plate_critera.add(Restrictions.eq("printingJob", printJob));
                    PlateHasPrintingJob customer_plate = (PlateHasPrintingJob) customer_plate_critera.list().get(0);

                    List<Plate> plate_list = plate_critera.list();
                    for (Plate p : plate_list) {
                        String plate_value = p.getPlateCount() + " Plates Of ID: " + p.getIdplate() + " Rack No: " + p.getRackNumber();
                        String plate_description = p.getDescription() + "§" + p.getIdplate();
                        if (p.getIdplate() == customer_plate.getPlate().getIdplate()) {
                %>
                <option value="<%= plate_description%>" selected="selected"><%= plate_value%></option>
                <%
                } else {

                %>   

                <option value="<%= plate_description%>"><%= plate_value%></option>

                <%
                        }
                    }
                %>
            </select>
        </div>
    </div>


    <div class="item form-group">
        <label class="col-form-label col-md-3 col-sm-3 label-align" for="first-name">Plate Description<span class="required">*</span>
        </label>
        <div class="col-md-6 col-sm-6 ">
            <textarea type="text" id="plate_deacription2" required="required" class="form-control " rows="5" readonly><%= customer_plate.getPlate().getDescription()%></textarea>
        </div>
    </div>


    <div class="item form-group">
        <label class="col-form-label col-md-3 col-sm-3 label-align" for="first-name">Select Machine<span class="required">*</span>
        </label>
        <div class="col-md-6 col-sm-6 ">
            <select class="form-control" id="select_machine2">
                <%

                    Criteria machine_critera = s.createCriteria(Machine.class);

                    Criteria mashine_status_critera = s.createCriteria(MachineStatus.class);
                    mashine_status_critera.add(Restrictions.eq("status", "Active"));
                    MachineStatus mashine_status = (MachineStatus) mashine_status_critera.uniqueResult();

                    machine_critera.add(Restrictions.eq("machineStatus", mashine_status));

                    List<Machine> machine_list = machine_critera.list();
                    for (Machine m : machine_list) {

                        if (m.getIdmachine() == printJob.getMachine().getIdmachine()) {
                %>
                <option value="<%= m.getIdmachine()%>" selected="selected"><%= m.getName()%></option>
                <%
                } else {
                %>
                <option value="<%= m.getIdmachine()%>"><%= m.getName()%></option>
                <%
                    }
                %>


                <%
                    }
                %>

            </select>
        </div>
    </div>



    <div class="item form-group">
        <label class="col-form-label col-md-3 col-sm-3 label-align" for="first-name">Register Date<span class="required">*</span>
        </label>
        <div class="col-md-6 col-sm-6 ">
            <input type="date" id="reg_date2" required="required" class="form-control " autocomplete="off" value="<%= new SimpleDateFormat("yyyy-MM-dd").format(printJob.getRegDate())%>">
        </div>
    </div>

    <div class="item form-group">
        <label class="col-form-label col-md-3 col-sm-3 label-align" for="first-name">Start Date<span class="required">*</span>
        </label>
        <div class="col-md-6 col-sm-6 ">
            <input type="date" id="job_date2" required="required" class="form-control " autocomplete="off" value="<%= new SimpleDateFormat("yyyy-MM-dd").format(printJob.getStartDate())%>">
        </div>
    </div>

    <div class="item form-group">
        <label class="col-form-label col-md-3 col-sm-3 label-align" for="first-name">Select Order<span class="required">*</span>
        </label>
        <div class="col-md-6 col-sm-6 " id="orede_list2">
            <%
                Criteria order_critera = s.createCriteria(Order.class);
                order_critera.add(Restrictions.eq("customer", customer));
                List<Order> order_list = order_critera.list();


            %>
            <select class="form-control" id="select_order2" onchange="set_order_desctiption2()">

                <option value="null" selected="selected"></option>

                <%                                    for (Order o : order_list) {

                        if (o.getUniqueIdentifierText().equals(printJob.getOrder().getUniqueIdentifierText())) {
                %>
                <option value="<%= o.getIdorder()%>" selected="selected"><%= o.getUniqueIdentifierText()%></option>
                <%
                } else {


                %>
                <option value="<%= o.getIdorder()%>"><%= o.getUniqueIdentifierText()%></option>
                <%}
                    }
                %>

            </select>
        </div>
    </div>
    <div class="item form-group">
        <label class="col-form-label col-md-3 col-sm-3 label-align" for="first-name">Order Description<span class="required">*</span>
        </label>
        <div class="col-md-6 col-sm-6 ">
            <textarea type="text" id="order_description2" required="required" class="form-control " rows="5" readonly><%= printJob.getOrder().getDescription()%></textarea>
        </div>
    </div>



    <div class="ln_solid"></div>
    <div class="item form-group">
        <div class="col-md-6 col-sm-6 offset-md-3">

            <!-- Large modal -->
            <button type="button" class="btn btn-primary" data-toggle="modal" data-target=".bs-example-modal-lgs" id="load-model2">Load Full Order Details</button>

            <div class="modal fade bs-example-modal-lgs" tabindex="-1" role="dialog" aria-hidden="true">
                <div class="modal-dialog modal-lg">
                    <div class="modal-content">

                        <div class="modal-header">
                            <h4 class="modal-title" id="myModalLabel2"></h4>
                            <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">×</span>
                            </button>
                        </div>
                        <div class="modal-body">
                            <%
                                Order selectedOrder = printJob.getOrder();


                            %>
                            <div class="item form-group">
                                <label class="col-form-label col-md-3 col-sm-3 label-align" for="first-name">Unique ID
                                </label>
                                <div class="col-md-6 col-sm-6 ">
                                    <input type="text"  required="required" class="form-control " autocomplete="off" id="un-id2" readonly="true" value="<%= selectedOrder.getUniqueIdentifierText()%>">
                                </div>
                            </div>

                            <div class="item form-group">
                                <label class="col-form-label col-md-3 col-sm-3 label-align" for="first-name">Customer Name
                                </label>
                                <div class="col-md-6 col-sm-6 ">
                                    <input type="text"  required="required" class="form-control " autocomplete="off" id="order-cust-name2" readonly="true" value="<%= customer_name%>">
                                </div>
                            </div>

                            <div class="item form-group">
                                <label class="col-form-label col-md-3 col-sm-3 label-align" for="first-name">Customer Mobile
                                </label>
                                <div class="col-md-6 col-sm-6 ">
                                    <input type="text"  required="required" class="form-control " autocomplete="off" id="order-cust-mobile2" readonly="true" value="<%= customer_mobile%>">
                                </div>
                            </div>

                            <div class="item form-group">
                                <label class="col-form-label col-md-3 col-sm-3 label-align" for="first-name">Registered date
                                </label>
                                <div class="col-md-6 col-sm-6 ">
                                    <input type="text"  required="required" class="form-control " autocomplete="off" id="reg-date2" readonly="true" value="<%= new SimpleDateFormat("yyyy-MMM-dd").format(selectedOrder.getDate())%>">
                                </div>
                            </div>

                            <div class="item form-group">
                                <label class="col-form-label col-md-3 col-sm-3 label-align" for="first-name">Total
                                </label>
                                <div class="col-md-6 col-sm-6 ">
                                    <input type="text"  required="required" class="form-control " autocomplete="off" id="reg-tot2" readonly="true" value="<%= selectedOrder.getTotal()%>">
                                </div>
                            </div>

                            <div class="item form-group">
                                <label class="col-form-label col-md-3 col-sm-3 label-align" for="first-name">Discount
                                </label>
                                <div class="col-md-6 col-sm-6 ">
                                    <input type="text"  required="required" class="form-control " autocomplete="off" id="reg-dis2" readonly="true" value="<%= selectedOrder.getDiscount()%>">
                                </div>
                            </div>

                            <div class="item form-group">
                                <label class="col-form-label col-md-3 col-sm-3 label-align" for="first-name">Sub Total
                                </label>
                                <div class="col-md-6 col-sm-6 ">
                                    <input type="text"  required="required" class="form-control " autocomplete="off" id="reg-sub2" readonly="true" value="<%= selectedOrder.getSubtotal()%>">
                                </div>
                            </div>

                            <div class="item form-group">
                                <label class="col-form-label col-md-3 col-sm-3 label-align" for="first-name">Due Amount
                                </label>
                                <div class="col-md-6 col-sm-6 ">
                                    <input type="text"  required="required" class="form-control " autocomplete="off" id="reg-due2" readonly="true" value="<%= selectedOrder.getDueAmount()%>">
                                </div>
                            </div>

                            <div class="item form-group">
                                <label class="col-form-label col-md-3 col-sm-3 label-align" for="first-name">Deliver Date
                                </label>
                                <div class="col-md-6 col-sm-6 ">
                                    <input type="text"  required="required" class="form-control " autocomplete="off" id="reg-del-date2" readonly="true" value="<%= new SimpleDateFormat("yyyy-MMM-dd").format(selectedOrder.getDeliverDate())%>">
                                </div>
                            </div>

                            <div class="item form-group">
                                <label class="col-form-label col-md-3 col-sm-3 label-align" for="first-name">Order Status
                                </label>
                                <div class="col-md-6 col-sm-6 ">
                                    <input type="text"  required="required" class="form-control " autocomplete="off" id="reg-status2" readonly="true" value="<%= selectedOrder.getOrderStatus().getStatus()%>">
                                </div>
                            </div>






                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>

                        </div>

                    </div>
                </div>
            </div>

        </div>
    </div>






    <div class="ln_solid"></div>
    <div class="item form-group">
        <div class="col-md-6 col-sm-6 offset-md-3">
            <button class="btn bg-dark text-white" type="button" id="update_job" onclick="updatePlate('<%= printJob.getIdprintingJob() %>')">Update Printing Job</button>
            <button  class="btn btn-info" type="button" id="process_job" onclick="chagePrintJobStatus('1');" >Process</button>
            <button  class="btn btn-success" type="button" id="process_complete" onclick="chagePrintJobStatus('2');">Complete</button>
            <button  class="btn btn-warning text-white" type="button" id="process_pending" onclick="chagePrintJobStatus('3');">Back To Pending</button>
            <button  class="btn btn-danger" type="button" id="process_cancel" onclick="chagePrintJobStatus('4');">Cancel</button>

        </div>
    </div>

</form>
<!-- End of registration Form !-->

<%    }
%>