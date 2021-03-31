<%-- 
    Document   : printer_registration
    Created on : Sep 20, 2020, 1:00:06 PM
    Author     : Pasindu Maduranga
--%>


<%@page import="hibernate.Customer"%>
<%@page import="hibernate.PlateHasPrintingJob"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="hibernate.Order"%>
<%@page import="hibernate.MachineStatus"%>
<%@page import="org.hibernate.criterion.Restrictions"%>
<%@page import="hibernate.PrintingJob"%>
<%@page import="hibernate.Plate"%>
<%@page import="java.util.List"%>
<%@page import="hibernate.Machine"%>
<%@page import="org.hibernate.Criteria"%>
<%@page import="org.hibernate.Session"%>
<%@page import="hibernate.NewHibernateUtil"%>
<%@page import="org.hibernate.SessionFactory"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!-- Meta, title, CSS, favicons, etc. -->
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <title>Gentelella Alela! | </title>

        <!-- Bootstrap -->
        <link href="vendors/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">
        <!-- Font Awesome -->
        <link href="vendors/font-awesome/css/font-awesome.min.css" rel="stylesheet">
        <!-- NProgress -->
        <link href="vendors/nprogress/nprogress.css" rel="stylesheet">

        <!-- Custom Theme Style -->
        <link href="build/css/custom.min.css" rel="stylesheet">


        <link href="vendors/pnotify/dist/pnotify.css" rel="stylesheet">
        <link href="vendors/pnotify/dist/pnotify.buttons.css" rel="stylesheet">
        <link href=vendors/pnotify/dist/pnotify.nonblock.css" rel="stylesheet">
    </head>
    <%
        SessionFactory factory = NewHibernateUtil.getSessionFactory();
        Session s = factory.openSession();


    %>
    <body class="nav-md">
        <div class="container body" >
            <div class="main_container">

                <jsp:include page="menu.jsp"/>


                <!-- page content -->
                <div class="right_col" role="main" id="main_body">
                    <div class="">
                        <div class="page-title">
                            <div class="title_left">
                                <h3>Printing Job</h3>
                            </div>

                            <div class="title_right">
                                <div class="col-md-5 col-sm-5   form-group pull-right top_search">
                                    <div class="input-group">
                                        <input type="text" class="form-control" placeholder="Search for...">
                                        <span class="input-group-btn">
                                            <button class="btn btn-default" type="button">Go!</button>
                                        </span>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="clearfix"></div>

                        <div class="row">
                            <div class="col-md-12 col-sm-12 ">
                                <div class="x_panel">
                                    <div class="x_title" >

                                        <h2>Printing Job
                                        </h2>


                                        <ul class="nav navbar-right panel_toolbox">
                                            <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
                                            </li>


                                        </ul>
                                        <div class="clearfix"></div>
                                    </div>
                                    <div class="x_content">
                                        <br />     

                                        <form id="demo-form2" data-parsley-validate class="form-horizontal form-label-left">

                                            <div class="item form-group">
                                                <label class="col-form-label col-md-3 col-sm-3 label-align" for="first-name">Job ID<span class="required">*</span>
                                                </label>
                                                <div class="col-md-6 col-sm-6 ">
                                                    <%                                                        Criteria plate_critera = s.createCriteria(PrintingJob.class);
                                                        plate_critera.addOrder(org.hibernate.criterion.Order.asc("idprintingJob"));
                                                        List<PrintingJob> printing_job_list = plate_critera.list();

                                                        int job_number = printing_job_list.size() + 1;


                                                    %>
                                                    <input type="text" id="print_job" required="required" class="form-control " value="<%= job_number%>"  autocomplete="off" readonly>
                                                </div>
                                            </div>

                                            <div class="item form-group">
                                                <label class="col-form-label col-md-3 col-sm-3 label-align" for="first-name">Customer Mobile<span class="required">*</span>
                                                </label>
                                                <div class="col-md-6 col-sm-6 ">
                                                    <input type="text" id="customer_mobile" required="required" class="form-control " autocomplete="off">
                                                </div>
                                            </div>


                                            <div class="item form-group">
                                                <label class="col-form-label col-md-3 col-sm-3 label-align" for="first-name">Customer Name<span class="required">*</span>
                                                </label>
                                                <div class="col-md-6 col-sm-6 ">
                                                    <input type="text" id="customer_name" required="required" class="form-control " autocomplete="off">
                                                </div>
                                            </div>


                                            <div class="item form-group">
                                                <label class="col-form-label col-md-3 col-sm-3 label-align" for="first-name">Registered Plate<span class="required">*</span>
                                                </label>
                                                <div class="col-md-6 col-sm-6 ">
                                                    <select class="form-control" id="selected_val" onchange="set_description();">
                                                        <option value="null" selected="selected"></option>

                                                    </select>
                                                </div>
                                            </div>


                                            <div class="item form-group">
                                                <label class="col-form-label col-md-3 col-sm-3 label-align" for="first-name">Plate Description<span class="required">*</span>
                                                </label>
                                                <div class="col-md-6 col-sm-6 ">
                                                    <textarea type="text" id="plate_deacription" required="required" class="form-control " rows="5" readonly></textarea>
                                                </div>
                                            </div>


                                            <div class="item form-group">
                                                <label class="col-form-label col-md-3 col-sm-3 label-align" for="first-name">Select Machine<span class="required">*</span>
                                                </label>
                                                <div class="col-md-6 col-sm-6 ">
                                                    <select class="form-control" id="select_machine">
                                                        <%
                                                            Criteria machine_critera = s.createCriteria(Machine.class);

                                                            Criteria mashine_status_critera = s.createCriteria(MachineStatus.class);
                                                            mashine_status_critera.add(Restrictions.eq("status", "Active"));
                                                            MachineStatus mashine_status = (MachineStatus) mashine_status_critera.uniqueResult();

                                                            machine_critera.add(Restrictions.eq("machineStatus", mashine_status));

                                                            List<Machine> machine_list = machine_critera.list();
                                                            for (Machine m : machine_list) {
                                                        %>

                                                        <option value="<%= m.getIdmachine()%>"><%= m.getName()%></option>
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
                                                    <input type="date" id="reg_date" required="required" class="form-control " autocomplete="off">
                                                </div>
                                            </div>

                                            <div class="item form-group">
                                                <label class="col-form-label col-md-3 col-sm-3 label-align" for="first-name">Start Date<span class="required">*</span>
                                                </label>
                                                <div class="col-md-6 col-sm-6 ">
                                                    <input type="date" id="job_date" required="required" class="form-control " autocomplete="off">
                                                </div>
                                            </div>

                                            <div class="item form-group">
                                                <label class="col-form-label col-md-3 col-sm-3 label-align" for="first-name">Select Order<span class="required">*</span>
                                                </label>
                                                <div class="col-md-6 col-sm-6 " id="orede_list">
                                                    <select class="form-control" id="select_order" onchange="set_order_desctiption()">
                                                        <option value="null" selected="selected"></option>



                                                    </select>
                                                </div>
                                            </div>
                                            <div class="item form-group">
                                                <label class="col-form-label col-md-3 col-sm-3 label-align" for="first-name">Order Description<span class="required">*</span>
                                                </label>
                                                <div class="col-md-6 col-sm-6 ">
                                                    <textarea type="text" id="order_description" required="required" class="form-control " rows="5" readonly></textarea>
                                                </div>
                                            </div>



                                            <div class="ln_solid"></div>
                                            <div class="item form-group">
                                                <div class="col-md-6 col-sm-6 offset-md-3">

                                                    <!-- Large modal -->
                                                    <button type="button" class="btn btn-primary" data-toggle="modal" data-target=".bs-example-modal-lg" id="load-model">Load Full Order Details</button>

                                                    <div class="modal fade bs-example-modal-lg" tabindex="-1" role="dialog" aria-hidden="true">
                                                        <div class="modal-dialog modal-lg">
                                                            <div class="modal-content">

                                                                <div class="modal-header">
                                                                    <h4 class="modal-title" id="myModalLabel"></h4>
                                                                    <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">×</span>
                                                                    </button>
                                                                </div>
                                                                <div class="modal-body">

                                                                    <div class="item form-group">
                                                                        <label class="col-form-label col-md-3 col-sm-3 label-align" for="first-name">Unique ID
                                                                        </label>
                                                                        <div class="col-md-6 col-sm-6 ">
                                                                            <input type="text"  required="required" class="form-control " autocomplete="off" id="un-id" readonly="true">
                                                                        </div>
                                                                    </div>

                                                                    <div class="item form-group">
                                                                        <label class="col-form-label col-md-3 col-sm-3 label-align" for="first-name">Customer Name
                                                                        </label>
                                                                        <div class="col-md-6 col-sm-6 ">
                                                                            <input type="text"  required="required" class="form-control " autocomplete="off" id="order-cust-name" readonly="true">
                                                                        </div>
                                                                    </div>

                                                                    <div class="item form-group">
                                                                        <label class="col-form-label col-md-3 col-sm-3 label-align" for="first-name">Customer Mobile
                                                                        </label>
                                                                        <div class="col-md-6 col-sm-6 ">
                                                                            <input type="text"  required="required" class="form-control " autocomplete="off" id="order-cust-mobile" readonly="true">
                                                                        </div>
                                                                    </div>

                                                                    <div class="item form-group">
                                                                        <label class="col-form-label col-md-3 col-sm-3 label-align" for="first-name">Registered date
                                                                        </label>
                                                                        <div class="col-md-6 col-sm-6 ">
                                                                            <input type="text"  required="required" class="form-control " autocomplete="off" id="reg-date" readonly="true">
                                                                        </div>
                                                                    </div>

                                                                    <div class="item form-group">
                                                                        <label class="col-form-label col-md-3 col-sm-3 label-align" for="first-name">Total
                                                                        </label>
                                                                        <div class="col-md-6 col-sm-6 ">
                                                                            <input type="text"  required="required" class="form-control " autocomplete="off" id="reg-tot" readonly="true">
                                                                        </div>
                                                                    </div>

                                                                    <div class="item form-group">
                                                                        <label class="col-form-label col-md-3 col-sm-3 label-align" for="first-name">Discount
                                                                        </label>
                                                                        <div class="col-md-6 col-sm-6 ">
                                                                            <input type="text"  required="required" class="form-control " autocomplete="off" id="reg-dis" readonly="true">
                                                                        </div>
                                                                    </div>

                                                                    <div class="item form-group">
                                                                        <label class="col-form-label col-md-3 col-sm-3 label-align" for="first-name">Sub Total
                                                                        </label>
                                                                        <div class="col-md-6 col-sm-6 ">
                                                                            <input type="text"  required="required" class="form-control " autocomplete="off" id="reg-sub" readonly="true">
                                                                        </div>
                                                                    </div>

                                                                    <div class="item form-group">
                                                                        <label class="col-form-label col-md-3 col-sm-3 label-align" for="first-name">Due Amount
                                                                        </label>
                                                                        <div class="col-md-6 col-sm-6 ">
                                                                            <input type="text"  required="required" class="form-control " autocomplete="off" id="reg-due" readonly="true">
                                                                        </div>
                                                                    </div>

                                                                    <div class="item form-group">
                                                                        <label class="col-form-label col-md-3 col-sm-3 label-align" for="first-name">Deliver Date
                                                                        </label>
                                                                        <div class="col-md-6 col-sm-6 ">
                                                                            <input type="text"  required="required" class="form-control " autocomplete="off" id="reg-del-date" readonly="true">
                                                                        </div>
                                                                    </div>

                                                                    <div class="item form-group">
                                                                        <label class="col-form-label col-md-3 col-sm-3 label-align" for="first-name">Order Status
                                                                        </label>
                                                                        <div class="col-md-6 col-sm-6 ">
                                                                            <input type="text"  required="required" class="form-control " autocomplete="off" id="reg-status" readonly="true">
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
                                                    <button onclick="save_print_job();" class="btn btn-success" type="button" id="load-orders">Register Printing Job</button>

                                                </div>
                                            </div>

                                        </form>
                                        <!-- End of registration Form !-->
                                    </div>


                                </div>
                            </div>
                        </div>

                        <div class="clearfix"></div>
                        <div class="row" id="tbldata">

                            <div class="col-md-12 col-sm-12">
                                <div class="x_panel">
                                    <div class="x_title">
                                        <h2>Registered Printing Jobs</h2>
                                        <ul class="nav navbar-right panel_toolbox">
                                            <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
                                            </li>

                                        </ul>
                                        <div class="clearfix"></div>
                                    </div>
                                    <div class="x_content">
                                        <div class="row">
                                            <div class="col-sm-12">
                                                <div class="card-box table-responsive"  id="tblvalue">



                                                    <table id="datatable-responsive" class="table table-striped table-bordered dt-responsive nowrap" cellspacing="0" width="100%">
                                                        <thead>
                                                            <tr >
                                                                <th>Job Id</th>
                                                                <th>Registered Date</th>
                                                                <th>Start Date</th>
                                                                <th>Machine</th>
                                                                <th>Customer</th>
                                                                <th>Status</th>


                                                            </tr>
                                                        </thead>
                                                        <tbody id="table_body">
                                                            <%
                                                                Criteria printjob_critera = s.createCriteria(PrintingJob.class);
                                                                List<PrintingJob> printJobList = printjob_critera.list();
                                                                for (PrintingJob p : printJobList) {
                                                            %>
                                                            <tr value="<%= p.getIdprintingJob()%>" style=" cursor: pointer;" onclick="setPrinjobDetails('<%= p.getIdprintingJob()%>')">
                                                                <td><%= p.getIdprintingJob()%></td>
                                                                <td><%= new SimpleDateFormat("yyyy-MMM-dd").format(p.getRegDate())%></td>
                                                                <td><%= new SimpleDateFormat("yyyy-MMM-dd").format(p.getStartDate())%></td>
                                                                <td><%=  p.getMachine().getName()%></td>
                                                                <%
                                                                    Criteria plateHasjob_critera = s.createCriteria(PlateHasPrintingJob.class).add(Restrictions.eq("printingJob", p));
                                                                    PlateHasPrintingJob plateJobObj = (PlateHasPrintingJob) plateHasjob_critera.uniqueResult();
                                                                    Customer cus = plateJobObj.getPlate().getCustomer();

                                                                    String customer_name = cus.getFirstName() + " " + cus.getLastName();
                                                                %>

                                                                <td><%= customer_name%></td>
                                                                <td><%= p.getPrintingJobStatus().getStatus()%></td>
                                                            </tr>
                                                            <%
                                                                }
                                                            %>

                                                        </tbody>
                                                    </table>



                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>





                        <div class="clearfix"></div>
                        <div class="row">
                            <div class="col-md-12 col-sm-12 ">
                                <div class="x_panel">
                                    <div class="x_title" >

                                        <h2>Edit Printing Job 
                                        </h2>


                                        <ul class="nav navbar-right panel_toolbox">
                                            <li><a class="collapse-link" ><i class="fa fa-chevron-up"></i></a>
                                            </li>


                                        </ul>
                                        <div class="clearfix"></div>
                                    </div>
                                    <div class="x_content">
                                        <br />     
                                        <div id="print_job_details">
                                            <form id="demo-form3" data-parsley-validate class="form-horizontal form-label-left">

                                                <div class="item form-group">
                                                    <label class="col-form-label col-md-3 col-sm-3 label-align" for="first-name">Job ID<span class="required">*</span>
                                                    </label>
                                                    <div class="col-md-6 col-sm-6 ">

                                                        <input type="text" id="print_job3" required="required" class="form-control " value=""  autocomplete="off" readonly>
                                                    </div>
                                                </div>

                                                <div class="item form-group">
                                                    <label class="col-form-label col-md-3 col-sm-3 label-align" for="first-name">Customer Mobile<span class="required">*</span>
                                                    </label>
                                                    <div class="col-md-6 col-sm-6 ">
                                                        <input type="text" id="customer_mobile2" required="required" class="form-control " autocomplete="off" readonly="true">
                                                    </div>
                                                </div>


                                                <div class="item form-group">
                                                    <label class="col-form-label col-md-3 col-sm-3 label-align" for="first-name">Customer Name<span class="required">*</span>
                                                    </label>
                                                    <div class="col-md-6 col-sm-6 ">
                                                        <input type="text" id="customer_name2" required="required" class="form-control " autocomplete="off" readonly="true">
                                                    </div>
                                                </div>


                                                <div class="item form-group">
                                                    <label class="col-form-label col-md-3 col-sm-3 label-align" for="first-name">Registered Plate<span class="required">*</span>
                                                    </label>
                                                    <div class="col-md-6 col-sm-6 ">
                                                        <select class="form-control" id="selected_val2" disabled onchange="set_description2();">
                                                            <option value="null" selected="selected"></option>

                                                        </select>
                                                    </div>
                                                </div>


                                                <div class="item form-group">
                                                    <label class="col-form-label col-md-3 col-sm-3 label-align" for="first-name">Plate Description<span class="required">*</span>
                                                    </label>
                                                    <div class="col-md-6 col-sm-6 ">
                                                        <textarea type="text" id="plate_deacription2" required="required" class="form-control " rows="5" readonly></textarea>
                                                    </div>
                                                </div>


                                                <div class="item form-group">
                                                    <label class="col-form-label col-md-3 col-sm-3 label-align" for="first-name">Select Machine<span class="required">*</span>
                                                    </label>
                                                    <div class="col-md-6 col-sm-6 ">
                                                        <select class="form-control" id="select_machine2" disabled>
                                                            <%
                                                                for (Machine m : machine_list) {
                                                            %>

                                                            <option value="<%= m.getIdmachine()%>"><%= m.getName()%></option>
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
                                                        <input type="date" id="reg_date2" required="required" class="form-control " autocomplete="off" readonly="true">
                                                    </div>
                                                </div>

                                                <div class="item form-group">
                                                    <label class="col-form-label col-md-3 col-sm-3 label-align" for="first-name">Start Date<span class="required">*</span>
                                                    </label>
                                                    <div class="col-md-6 col-sm-6 ">
                                                        <input type="date" id="job_date2" required="required" class="form-control " autocomplete="off" readonly="true">
                                                    </div>
                                                </div>

                                                <div class="item form-group">
                                                    <label class="col-form-label col-md-3 col-sm-3 label-align" for="first-name">Select Order<span class="required">*</span>
                                                    </label>
                                                    <div class="col-md-6 col-sm-6 " id="orede_list2">
                                                        <select class="form-control" id="select_order2" onchange="set_order_desctiption2()" disabled>
                                                            <option value="null" selected="selected"></option>



                                                        </select>
                                                    </div>
                                                </div>
                                                <div class="item form-group">
                                                    <label class="col-form-label col-md-3 col-sm-3 label-align" for="first-name">Order Description<span class="required">*</span>
                                                    </label>
                                                    <div class="col-md-6 col-sm-6 ">
                                                        <textarea type="text" id="order_description2" required="required" class="form-control " rows="5" readonly></textarea>
                                                    </div>
                                                </div>



                                                <div class="ln_solid"></div>
                                                <div class="item form-group">
                                                    <div class="col-md-6 col-sm-6 offset-md-3">

                                                        <!-- Large modal -->
                                                        <button type="button" class="btn btn-primary" data-toggle="modal" data-target=".bs-example-modal-lgs" id="load-model2" disabled>Load Full Order Details</button>

                                                        <div class="modal fade bs-example-modal-lgs" tabindex="-1" role="dialog" aria-hidden="true">
                                                            <div class="modal-dialog modal-lg">
                                                                <div class="modal-content">

                                                                    <div class="modal-header">
                                                                        <h4 class="modal-title" id="myModalLabel2"></h4>
                                                                        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">×</span>
                                                                        </button>
                                                                    </div>
                                                                    <div class="modal-body">

                                                                        <div class="item form-group">
                                                                            <label class="col-form-label col-md-3 col-sm-3 label-align" for="first-name">Unique ID
                                                                            </label>
                                                                            <div class="col-md-6 col-sm-6 ">
                                                                                <input type="text"  required="required" class="form-control " autocomplete="off" id="un-id2" readonly="true">
                                                                            </div>
                                                                        </div>

                                                                        <div class="item form-group">
                                                                            <label class="col-form-label col-md-3 col-sm-3 label-align" for="first-name">Customer Name
                                                                            </label>
                                                                            <div class="col-md-6 col-sm-6 ">
                                                                                <input type="text"  required="required" class="form-control " autocomplete="off" id="order-cust-name2" readonly="true">
                                                                            </div>
                                                                        </div>

                                                                        <div class="item form-group">
                                                                            <label class="col-form-label col-md-3 col-sm-3 label-align" for="first-name">Customer Mobile
                                                                            </label>
                                                                            <div class="col-md-6 col-sm-6 ">
                                                                                <input type="text"  required="required" class="form-control " autocomplete="off" id="order-cust-mobile2" readonly="true">
                                                                            </div>
                                                                        </div>

                                                                        <div class="item form-group">
                                                                            <label class="col-form-label col-md-3 col-sm-3 label-align" for="first-name">Registered date
                                                                            </label>
                                                                            <div class="col-md-6 col-sm-6 ">
                                                                                <input type="text"  required="required" class="form-control " autocomplete="off" id="reg-date2" readonly="true">
                                                                            </div>
                                                                        </div>

                                                                        <div class="item form-group">
                                                                            <label class="col-form-label col-md-3 col-sm-3 label-align" for="first-name">Total
                                                                            </label>
                                                                            <div class="col-md-6 col-sm-6 ">
                                                                                <input type="text"  required="required" class="form-control " autocomplete="off" id="reg-tot2" readonly="true">
                                                                            </div>
                                                                        </div>

                                                                        <div class="item form-group">
                                                                            <label class="col-form-label col-md-3 col-sm-3 label-align" for="first-name">Discount
                                                                            </label>
                                                                            <div class="col-md-6 col-sm-6 ">
                                                                                <input type="text"  required="required" class="form-control " autocomplete="off" id="reg-dis2" readonly="true">
                                                                            </div>
                                                                        </div>

                                                                        <div class="item form-group">
                                                                            <label class="col-form-label col-md-3 col-sm-3 label-align" for="first-name">Sub Total
                                                                            </label>
                                                                            <div class="col-md-6 col-sm-6 ">
                                                                                <input type="text"  required="required" class="form-control " autocomplete="off" id="reg-sub2" readonly="true">
                                                                            </div>
                                                                        </div>

                                                                        <div class="item form-group">
                                                                            <label class="col-form-label col-md-3 col-sm-3 label-align" for="first-name">Due Amount
                                                                            </label>
                                                                            <div class="col-md-6 col-sm-6 ">
                                                                                <input type="text"  required="required" class="form-control " autocomplete="off" id="reg-due2" readonly="true">
                                                                            </div>
                                                                        </div>

                                                                        <div class="item form-group">
                                                                            <label class="col-form-label col-md-3 col-sm-3 label-align" for="first-name">Deliver Date
                                                                            </label>
                                                                            <div class="col-md-6 col-sm-6 ">
                                                                                <input type="text"  required="required" class="form-control " autocomplete="off" id="reg-del-date2" readonly="true">
                                                                            </div>
                                                                        </div>

                                                                        <div class="item form-group">
                                                                            <label class="col-form-label col-md-3 col-sm-3 label-align" for="first-name">Order Status
                                                                            </label>
                                                                            <div class="col-md-6 col-sm-6 ">
                                                                                <input type="text"  required="required" class="form-control " autocomplete="off" id="reg-status2" readonly="true">
                                                                            </div>
                                                                        </div>






                                                                    </div>
                                                                    <div class="modal-footer">
                                                                        <button type="button" class="btn btn-secondary" data-dismiss="modal" >Close</button>

                                                                    </div>

                                                                </div>
                                                            </div>
                                                        </div>

                                                    </div>
                                                </div>






                                                <div class="ln_solid"></div>
                                                <div class="item form-group">
                                                    <div class="col-md-6 col-sm-6 offset-md-3">
                                                        <button class="btn bg-dark text-white" type="button" id="update_job" disabled >Update Printing Job</button>
                                                        <button  class="btn btn-info" type="button" id="process_job" onclick="chagePrintJobStatus('1');" disabled>Process</button>
                                                        <button  class="btn btn-success" type="button" id="process_complete" onclick="chagePrintJobStatus('2');" disabled>Complete</button>
                                                        <button  class="btn btn-warning text-white" type="button" id="process_pending" onclick="chagePrintJobStatus('3');" disabled>Back To Pending</button>
                                                        <button  class="btn btn-danger" type="button" id="process_cancel" onclick="chagePrintJobStatus('4');" disabled>Cancel</button>

                                                    </div>
                                                </div>

                                            </form>
                                        </div>
                                        <!-- End of registration Form !-->
                                    </div>


                                </div>
                            </div>
                        </div>






                    </div>
                </div>
                <!-- /page content -->


                <jsp:include page="footer.jsp"/>


            </div>
        </div>

        <!-- jQuery -->
        <script src="vendors/jquery/dist/jquery.min.js"></script>
        <!-- Bootstrap -->
        <script src="vendors/bootstrap/dist/js/bootstrap.bundle.min.js"></script>
        <!-- FastClick -->
        <script src="vendors/fastclick/lib/fastclick.js"></script>
        <!-- NProgress -->
        <script src="vendors/nprogress/nprogress.js"></script>

        <!-- Custom Theme Scripts -->
        <script src="build/js/custom.min.js"></script>

        <!-- PNotify -->
        <script src="vendors/pnotify/dist/pnotify.js"></script>
        <script src="vendors/pnotify/dist/pnotify.buttons.js"></script>
        <script src="vendors/pnotify/dist/pnotify.nonblock.js"></script>

        <!-- iCheck -->
        <script src="vendors/iCheck/icheck.min.js"></script>
        <!-- Datatables -->
        <script src="vendors/datatables.net/js/jquery.dataTables.min.js"></script>
        <script src="vendors/datatables.net-bs/js/dataTables.bootstrap.min.js"></script>
        <script src="vendors/datatables.net-buttons/js/dataTables.buttons.min.js"></script>
        <script src="vendors/datatables.net-buttons-bs/js/buttons.bootstrap.min.js"></script>
        <script src="vendors/datatables.net-buttons/js/buttons.flash.min.js"></script>
        <script src="vendors/datatables.net-buttons/js/buttons.html5.min.js"></script>
        <script src="vendors/datatables.net-buttons/js/buttons.print.min.js"></script>
        <script src="vendors/datatables.net-fixedheader/js/dataTables.fixedHeader.min.js"></script>
        <script src="vendors/datatables.net-keytable/js/dataTables.keyTable.min.js"></script>
        <script src="vendors/datatables.net-responsive/js/dataTables.responsive.min.js"></script>
        <script src="vendors/datatables.net-responsive-bs/js/responsive.bootstrap.js"></script>
        <script src="vendors/datatables.net-scroller/js/dataTables.scroller.min.js"></script>
        <script src="vendors/jszip/dist/jszip.min.js"></script>
        <script src="vendors/pdfmake/build/pdfmake.min.js"></script>
        <script src="vendors/pdfmake/build/vfs_fonts.js"></script>


        <!-- customer scripts !-->
        <script src="js/js_GA.js"></script>
    </body>
</html>
<%    if (s.isOpen()) {
        s.flush();
        s.close();
    }
%>