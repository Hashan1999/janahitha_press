<%-- 
    Document   : plate_registration
    Created on : Sep 21, 2020, 5:39:45 PM
    Author     : Pasindu Maduranga
--%>

<%@page import="hibernate.Customer"%>
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
        <link href="css/css_GA.css" rel="stylesheet">


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
                                <h3>Manage Plates</h3>
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

                                        <h2>Registration Form <span>Plate 
                                                <!--  Machine number !-->  
                                                <%
                                                    SessionFactory factory = NewHibernateUtil.getSessionFactory();
                                                    Session s = factory.openSession();
                                                    String number = "001";

                                                    Criteria plate_number_critera = s.createCriteria(Plate.class);

                                                    List<Plate> plate_list = plate_number_critera.list();
                                                    int plate_count = plate_list.size() + 1;

                                                    if (plate_count != 1) {
                                                        if (plate_count >= 10) {
                                                            number = "0" + plate_count;
                                                        } else if (plate_count >= 100) {
                                                            number = "" + plate_count;
                                                        } else {
                                                            number = "00" + (plate_count + 1);
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
                                                <label class="col-form-label col-md-3 col-sm-3 label-align" for="first-name">Plate Rack Number<span class="required">*</span>
                                                </label>
                                                <div class="col-md-6 col-sm-6 ">
                                                    <input type="text" id="plate_rack" required="required" class="form-control " autocomplete="off">
                                                </div>
                                            </div>


                                            <div class="item form-group">
                                                <label class="col-form-label col-md-3 col-sm-3 label-align" for="first-name">Registration Date<span class="required">*</span>
                                                </label>
                                                <div class="col-md-6 col-sm-6 ">
                                                    <input type="date" id="plate_date" required="required" class="form-control " autocomplete="off">
                                                </div>
                                            </div>


                                            <div class="item form-group">
                                                <label class="col-form-label col-md-3 col-sm-3 label-align" for="first-name">Plate Count<span class="required">*</span>
                                                </label>
                                                <div class="col-md-6 col-sm-6 ">
                                                    <input type="number" id="plate_count" required="required" class="form-control " autocomplete="off">
                                                </div>
                                            </div>




                                            <div class="item form-group">
                                                <label class="col-form-label col-md-3 col-sm-3 label-align" for="first-name">Customer Mobile<span class="required">*</span>
                                                </label>
                                                <div class="col-md-6 col-sm-6 ">
                                                    <input type="number" id="customer_mobile" required="required" class="form-control " onkeyup="setName('customer_mobile','customer_name'); " />
                                                </div>
                                            </div>

                                            <div class="item form-group">
                                                <label class="col-form-label col-md-3 col-sm-3 label-align" for="first-name">Customer Name<span class="required">*</span>
                                                </label>
                                                <div class="col-md-6 col-sm-6">
                                                    <input type="text" name="country" id="customer_name" class="form-control" readonly/>
                                                </div>
                                            </div>

                                            <div class="item form-group">
                                                <label class="col-form-label col-md-3 col-sm-3 label-align" for="first-name">Description<span class="required">*</span>
                                                </label>
                                                <div class="col-md-6 col-sm-6 ">
                                                    <textarea class="resizable_textarea form-control" rows="8" id="des"></textarea>
                                                </div>
                                            </div>






                                            <div class="ln_solid"></div>
                                            <div class="item form-group">
                                                <div class="col-md-6 col-sm-6 offset-md-3">
                                                    <button onclick="register_plate();" class="btn btn-success" type="button" id="reg-btn">Register</button>
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
                                                            <tr >
                                                                <th>Plate Rack</th>
                                                                <th>Date</th>
                                                                <th>Plate Count</th>
                                                                <th>Customer Name</th>


                                                            </tr>
                                                        </thead>
                                                        <tbody>
                                                            <%
                                                                for (Plate p : plate_list) {

                                                            %>

                                                            <tr class="selected_row" onclick="set_customer('<%= p.getRackNumber()%>', '<%= p.getDate()%>', '<%= p.getPlateCount()%>', '<%= p.getIdplate()%>', '<%= p.getCustomer().getContact()%>','<%= p.getCustomer().getFirstName()+" "+p.getCustomer().getLastName() %>', '<%= p.getDescription()%>');">
                                                                <%                                                                    String num = "001";
                                                                    if (p.getIdplate() >= 10) {
                                                                        num = "0" + p.getIdplate();
                                                                    } else if (p.getIdplate() >= 100) {
                                                                        num = p.getIdplate() + "";
                                                                    } else {
                                                                        num = "00" + (p.getIdplate() + 1);
                                                                    }
                                                                %>


                                                                <td><%= p.getRackNumber()%></td>
                                                                <td><%= p.getDate()%></td>
                                                                <td><%= p.getPlateCount()%></td>
                                                                <td><%= p.getCustomer().getFirstName()+" "+p.getCustomer().getLastName() %></td>

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

                                        <h2>Editing Form 
                                                <!--  Machine number !-->  

                                               
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
                                            <input type="hidden" value="" id="plate_id"/>
                                            
                                            <div class="item form-group">
                                                <label class="col-form-label col-md-3 col-sm-3 label-align" for="first-name">Plate ID<span class="required">*</span>
                                                </label>
                                                <div class="col-md-6 col-sm-6 ">
                                                    <input type="text" id="palte_no" required="required" class="form-control " autocomplete="off" readonly>
                                                </div>
                                            </div>
                                            
                                            
                                            
                                            <div class="item form-group">
                                                <label class="col-form-label col-md-3 col-sm-3 label-align" for="first-name">Plate Rack Number<span class="required">*</span>
                                                </label>
                                                <div class="col-md-6 col-sm-6 ">
                                                    <input type="text" id="plate_rack2" required="required" class="form-control " autocomplete="off">
                                                </div>
                                            </div>


                                            <div class="item form-group">
                                                <label class="col-form-label col-md-3 col-sm-3 label-align" for="first-name">Registration Date<span class="required">*</span>
                                                </label>
                                                <div class="col-md-6 col-sm-6 ">
                                                    <input type="date" id="plate_date2" required="required" class="form-control " autocomplete="off">
                                                </div>
                                            </div>


                                            <div class="item form-group">
                                                <label class="col-form-label col-md-3 col-sm-3 label-align" for="first-name">Plate Count<span class="required">*</span>
                                                </label>
                                                <div class="col-md-6 col-sm-6 ">
                                                    <input type="number" id="plate_count2" required="required" class="form-control " autocomplete="off">
                                                </div>
                                            </div>


                                            <div class="item form-group">
                                                <label class="col-form-label col-md-3 col-sm-3 label-align" for="first-name">Customer Mobile<span class="required">*</span>
                                                </label>
                                                <div class="col-md-6 col-sm-6 ">
                                                    <input type="number" id="customer_mobile2" required="required" class="form-control " autocomplete="off">
                                                </div>
                                            </div>
                                            
                                             <div class="item form-group">
                                                <label class="col-form-label col-md-3 col-sm-3 label-align" for="first-name">Customer Name<span class="required">*</span>
                                                </label>
                                                <div class="col-md-6 col-sm-6 ">
                                                    <input type="text" id="customer_name2" required="required" class="form-control " readonly>
                                                </div>
                                            </div>


                                            

                                            <div class="item form-group">
                                                <label class="col-form-label col-md-3 col-sm-3 label-align" for="first-name">Description<span class="required">*</span>
                                                </label>
                                                <div class="col-md-6 col-sm-6 ">
                                                    <textarea class="resizable_textarea form-control" rows="8" id="des2"></textarea>
                                                </div>
                                            </div>



                                            <div class="ln_solid"></div>
                                            <div class="item form-group">
                                                <div class="col-md-6 col-sm-6 offset-md-3" id="button-group2">
                                                    <button onclick="update_plates()" class="btn btn-success" type="button" id="reg-btn2">Update</button>
                                                    <button class="btn btn-primary" type="reset" id="rst-btn2">Reset</button>
                                                    <button class="btn btn-danger" type="button" id="delete-btn" onclick="remove_plate();">Remove</button>
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

        <!-- jQuery autocomplete -->
       
    </body>
</html>
<%
    if (s.isOpen()) {
        s.flush();
        s.close();
    }
%>