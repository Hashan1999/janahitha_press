<%-- 
    Document   : printer_registration
    Created on : Sep 20, 2020, 1:00:06 PM
    Author     : Pasindu Maduranga
--%>

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

    <body class="nav-md">
        <div class="container body" >
            <div class="main_container">

                <jsp:include page="menu.jsp"/>


                <!-- page content -->
                <div class="right_col" role="main" id="main_body">
                    <div class="">
                        <div class="page-title">
                            <div class="title_left">
                                <h3>Manage Printing Machines</h3>
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

                                        <h2>Registration Form <span>Machine 
                                                <!--  Machine number !-->  
                                                <%
                                                    SessionFactory factory = NewHibernateUtil.getSessionFactory();
                                                    Session s = factory.openSession();
                                                    String number = "001";

                                                    Criteria machine_number_critera = s.createCriteria(Machine.class);

                                                    List<Machine> machine_list = machine_number_critera.list();
                                                    int machine_count = machine_list.size() + 1;
                                                    if (machine_count != 1) {
                                                        if (machine_count >= 10) {
                                                            number = "0" + machine_count;
                                                        } else if (machine_count >= 100) {
                                                            number = "" + machine_count;
                                                        } else {
                                                            number = "00" + (machine_count + 1);
                                                        }
                                                    }


                                                %>
                                                <%= number%>
                                            </span>
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
                                                <label class="col-form-label col-md-3 col-sm-3 label-align" for="first-name">Machine Name<span class="required">*</span>
                                                </label>
                                                <div class="col-md-6 col-sm-6 ">
                                                    <input type="text" id="preinter_name" required="required" class="form-control " autocomplete="off">
                                                </div>
                                            </div>


                                            <div class="ln_solid"></div>
                                            <div class="item form-group">
                                                <div class="col-md-6 col-sm-6 offset-md-3">
                                                    <button onclick="register_machine();" class="btn btn-success" type="button" id="reg-btn">Register</button>
                                                    <button class="btn btn-primary" type="reset" id="rst-btn">Reset</button>
                                                </div>
                                            </div>

                                        </form>
                                        <!-- End of registration Form !-->
                                    </div>


                                </div>
                            </div>
                        </div>
                        <!-- Table Content !-->
                        <div class="row" id="tbldata">

                            <div class="col-md-12 col-sm-12">
                                <div class="x_panel">
                                    <div class="x_title">
                                        <h2>Registered Printing Machines</h2>
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
                                                            <tr>
                                                                <th>Machine Number</th>
                                                                <th>Machine Name</th>
                                                                <th>Machine Status</th>
                                                                <th>Customize</th>


                                                            </tr>
                                                        </thead>
                                                        <tbody>
                                                            <%
                                                                for (Machine m : machine_list) {

                                                            %>

                                                            <tr>
                                                                <%                                                                    String num = "001";
                                                                    if (m.getIdmachine() >= 10) {
                                                                        num = "0" + m.getIdmachine();
                                                                    } else if (m.getIdmachine() >= 100) {
                                                                        num = m.getIdmachine() + "";
                                                                    } else {
                                                                        num = "00" + (m.getIdmachine() + 1);
                                                                    }
                                                                %>

                                                                <td>Machine <%= num%></td>
                                                                <td><%= m.getName()%></td>
                                                                <td><%= m.getMachineStatus().getStatus()%></td>
                                                                <td> 


                                                                    <button onclick="edit_machine('<%= m.getName()%>', '<%= m.getIdmachine()%>');" class="btn btn-success" type="button" id="delete_btn">
                                                                        Edit

                                                                    </button>
                                                                    <button onclick="remove_machine('<%= m.getIdmachine()%>')" class="btn btn-danger" type="button" id="remove_btn">
                                                                        <%
                                                                            if (m.getMachineStatus().getStatus().equals("Active")) {
                                                                        %>
                                                                        Deactivate
                                                                        <%
                                                                        } else {
                                                                        %>

                                                                        Activate
                                                                        <%
                                                                            }
                                                                        %>

                                                                    </button></td>


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

                                        <h2>Editing Form <span>Machine 
                                                <!--  Machine number !-->  
                                                <%

                                                %>
                                                <%= number%>
                                            </span>
                                        </h2>


                                        <ul class="nav navbar-right panel_toolbox">
                                            <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
                                            </li>


                                        </ul>
                                        <div class="clearfix"></div>
                                    </div>
                                    <div class="x_content">
                                        <br />     

                                        <form id="demo-form3" data-parsley-validate class="form-horizontal form-label-left">

                                            <div class="item form-group">
                                               
                                                <div class="col-md-6 col-sm-6 ">
                                                    <input type="hidden" id="rename_id" required="required" class="form-control " autocomplete="off">
                                                </div>
                                            </div>


                                            <div class="item form-group">
                                                <label class="col-form-label col-md-3 col-sm-3 label-align" for="first-name">Machine Name<span class="required">*</span>
                                                </label>
                                                <div class="col-md-6 col-sm-6 ">
                                                    <input type="text" id="rename_name" required="required" class="form-control " autocomplete="off">
                                                </div>
                                            </div>


                                            <div class="ln_solid"></div>
                                            <div class="item form-group">
                                                <div class="col-md-6 col-sm-6 offset-md-3">
                                                    <button onclick="rename_machine();" class="btn btn-success" type="button" id="rename-btn">Rename</button>
                                                    <button class="btn btn-primary" type="reset"  id="reset-btn">Reset</button>
                                                </div>
                                            </div>

                                        </form>
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
<%
    if (s.isOpen()) {
        s.flush();
        s.close();
    }
%>