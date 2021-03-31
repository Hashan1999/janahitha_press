<%-- 
    Document   : delivery note
    Created on : Mar 28, 2021, 11:51:40 AM
    Author     : HASHA
--%>
<%@page import="hd_model.DeliveryNoteValid"%>
<%@page import="hd_bean.DeliveryItemBean"%>
<%@page import="hibernate.OrderStatus"%>
<%@page import="org.hibernate.criterion.Restrictions"%>
<%@page import="hibernate.OrderItem"%>
<%@page import="hd_model.hd_validation"%>
<%

String orderid=request.getParameter("id");
if(orderid != null){
 if(hd_validation.isNumericInt(orderid)){
  Session hibernateSession=NewHibernateUtil.getSessionFactory().openSession();

Order o=(Order)hibernateSession.load(Order.class, Integer.parseInt(orderid));

%>   

  
  
<%@page import="hibernate.Order"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="hibernate.SellingItems"%>
<%@page import="java.util.List"%>
<%@page import="hibernate.Customer"%>
<%@page import="hibernate.NewHibernateUtil"%>
<%@page import="org.hibernate.Session"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <!-- Meta, title, CSS, favicons, etc. -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title> Janahitha Printers | Delivery Note</title>

    <!-- Bootstrap -->
    <link href="vendors/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Font Awesome -->
    <link href="vendors/font-awesome/css/font-awesome.min.css" rel="stylesheet">
    <!-- NProgress -->
    <link href="vendors/nprogress/nprogress.css" rel="stylesheet">

    <!-- PNotify -->
    <link href="vendors/pnotify/dist/pnotify.css" rel="stylesheet">
    <link href="vendors/pnotify/dist/pnotify.buttons.css" rel="stylesheet">
    <link href="vendors/pnotify/dist/pnotify.nonblock.css" rel="stylesheet">

    <!-- Datatables -->
    <link href="vendors/datatables.net-bs/css/dataTables.bootstrap.min.css" rel="stylesheet">
    <link href="vendors/datatables.net-buttons-bs/css/buttons.bootstrap.min.css" rel="stylesheet">
    <link href="vendors/datatables.net-fixedheader-bs/css/fixedHeader.bootstrap.min.css" rel="stylesheet">
    <link href="vendors/datatables.net-responsive-bs/css/responsive.bootstrap.min.css" rel="stylesheet">
    <link href="vendors/datatables.net-scroller-bs/css/scroller.bootstrap.min.css" rel="stylesheet">

    <!-- Custom Theme Style -->
    <link href="build/css/custom.min.css" rel="stylesheet">
  </head>

  <body class="nav-md">
    <div class="container body">
      <div class="main_container">
        <div class="col-md-3 left_col">
          <div class="left_col scroll-view">
            <div class="navbar nav_title" style="border: 0;">
              <a href="index.html" class="site_title"><i class="fa fa-paw"></i> <span>Gentelella Alela!</span></a>
            </div>

            <div class="clearfix"></div>

            <!-- menu profile quick info -->
            <div class="profile clearfix">
              <div class="profile_pic">
                <img src="images/img.jpg" alt="..." class="img-circle profile_img">
              </div>
              <div class="profile_info">
                <span>Welcome,</span>
                <h2>John Doe</h2>
              </div>
              <div class="clearfix"></div>
            </div>
            <!-- /menu profile quick info -->

            <br />

            <!-- sidebar menu -->
            <div id="sidebar-menu" class="main_menu_side hidden-print main_menu">
              <div class="menu_section">
                <h3>General</h3>
                <ul class="nav side-menu">
                  <li><a><i class="fa fa-home"></i> Home <span class="fa fa-chevron-down"></span></a>
                    <ul class="nav child_menu">
                      <li><a href="index.html">Dashboard</a></li>
                      <li><a href="index2.html">Dashboard2</a></li>
                      <li><a href="index3.html">Dashboard3</a></li>
                    </ul>
                  </li>
                  <li><a><i class="fa fa-edit"></i> Forms <span class="fa fa-chevron-down"></span></a>
                    <ul class="nav child_menu">
                      <li><a href="form.html">General Form</a></li>
                      <li><a href="form_advanced.html">Advanced Components</a></li>
                      <li><a href="form_validation.html">Form Validation</a></li>
                      <li><a href="form_wizards.html">Form Wizard</a></li>
                      <li><a href="form_upload.html">Form Upload</a></li>
                      <li><a href="form_buttons.html">Form Buttons</a></li>
                    </ul>
                  </li>
                  <li><a><i class="fa fa-desktop"></i> UI Elements <span class="fa fa-chevron-down"></span></a>
                    <ul class="nav child_menu">
                      <li><a href="general_elements.html">General Elements</a></li>
                      <li><a href="media_gallery.html">Media Gallery</a></li>
                      <li><a href="typography.html">Typography</a></li>
                      <li><a href="icons.html">Icons</a></li>
                      <li><a href="glyphicons.html">Glyphicons</a></li>
                      <li><a href="widgets.html">Widgets</a></li>
                      <li><a href="invoice.html">Invoice</a></li>
                      <li><a href="inbox.html">Inbox</a></li>
                      <li><a href="calendar.html">Calendar</a></li>
                    </ul>
                  </li>
                  <li><a><i class="fa fa-table"></i> Tables <span class="fa fa-chevron-down"></span></a>
                    <ul class="nav child_menu">
                      <li><a href="tables.html">Tables</a></li>
                      <li><a href="tables_dynamic.html">Table Dynamic</a></li>
                    </ul>
                  </li>
                  <li><a><i class="fa fa-bar-chart-o"></i> Data Presentation <span class="fa fa-chevron-down"></span></a>
                    <ul class="nav child_menu">
                      <li><a href="chartjs2.html">Chart JS2</a></li>
                      <li><a href="chartjs.html">Chart JS</a></li>
                      <li><a href="chartjs2.html">Chart JS2</a></li>
                      <li><a href="morisjs.html">Moris JS</a></li>
                      <li><a href="echarts.html">ECharts</a></li>
                      <li><a href="other_charts.html">Other Charts</a></li>
                    </ul>
                  </li>
                  <li><a><i class="fa fa-clone"></i>Layouts <span class="fa fa-chevron-down"></span></a>
                    <ul class="nav child_menu">
                      <li><a href="fixed_sidebar.html">Fixed Sidebar</a></li>
                      <li><a href="fixed_footer.html">Fixed Footer</a></li>
                    </ul>
                  </li>
                </ul>
              </div>
              <div class="menu_section">
                <h3>Live On</h3>
                <ul class="nav side-menu">
                  <li><a><i class="fa fa-bug"></i> Additional Pages <span class="fa fa-chevron-down"></span></a>
                    <ul class="nav child_menu">
                      <li><a href="e_commerce.html">E-commerce</a></li>
                      <li><a href="projects.html">Projects</a></li>
                      <li><a href="project_detail.html">Project Detail</a></li>
                      <li><a href="contacts.html">Contacts</a></li>
                      <li><a href="profile.html">Profile</a></li>
                    </ul>
                  </li>
                  <li><a><i class="fa fa-windows"></i> Extras <span class="fa fa-chevron-down"></span></a>
                    <ul class="nav child_menu">
                      <li><a href="page_403.html">403 Error</a></li>
                      <li><a href="page_404.html">404 Error</a></li>
                      <li><a href="page_500.html">500 Error</a></li>
                      <li><a href="plain_page.html">Plain Page</a></li>
                      <li><a href="login.html">Login Page</a></li>
                      <li><a href="pricing_tables.html">Pricing Tables</a></li>
                    </ul>
                  </li>
                  <li><a><i class="fa fa-sitemap"></i> Multilevel Menu <span class="fa fa-chevron-down"></span></a>
                    <ul class="nav child_menu">
                        <li><a href="#level1_1">Level One</a>
                        </li>
                        <li><a>Level One<span class="fa fa-chevron-down"></span></a>
                          <ul class="nav child_menu">
                            <li class="sub_menu"><a href="level2.html">Level Two</a>
                            </li>
                            <li><a href="#level2_1">Level Two</a>
                            </li>
                            <li><a href="#level2_2">Level Two</a>
                            </li>
                          </ul>
                        </li>
                        <li><a href="#level1_2">Level One</a>
                        </li>
                    </ul>
                  </li>                  
                  <li><a href="javascript:void(0)"><i class="fa fa-laptop"></i> Landing Page <span class="label label-success pull-right">Coming Soon</span></a></li>
                </ul>
              </div>

            </div>
            <!-- /sidebar menu -->

            <!-- /menu footer buttons -->
            <div class="sidebar-footer hidden-small">
              <a data-toggle="tooltip" data-placement="top" title="Settings">
                <span class="glyphicon glyphicon-cog" aria-hidden="true"></span>
              </a>
              <a data-toggle="tooltip" data-placement="top" title="FullScreen">
                <span class="glyphicon glyphicon-fullscreen" aria-hidden="true"></span>
              </a>
              <a data-toggle="tooltip" data-placement="top" title="Lock">
                <span class="glyphicon glyphicon-eye-close" aria-hidden="true"></span>
              </a>
              <a data-toggle="tooltip" data-placement="top" title="Logout" href="login.html">
                <span class="glyphicon glyphicon-off" aria-hidden="true"></span>
              </a>
            </div>
            <!-- /menu footer buttons -->
          </div>
        </div>

        <!-- top navigation -->
        <div class="top_nav">
            <div class="nav_menu">
                <div class="nav toggle">
                  <a id="menu_toggle"><i class="fa fa-bars"></i></a>
                </div>
                <nav class="nav navbar-nav">
                <ul class=" navbar-right">
                  <li class="nav-item dropdown open" style="padding-left: 15px;">
                    <a href="javascript:;" class="user-profile dropdown-toggle" aria-haspopup="true" id="navbarDropdown" data-toggle="dropdown" aria-expanded="false">
                      <img src="images/img.jpg" alt="">John Doe
                    </a>
                    <div class="dropdown-menu dropdown-usermenu pull-right" aria-labelledby="navbarDropdown">
                      <a class="dropdown-item"  href="javascript:;"> Profile</a>
                        <a class="dropdown-item"  href="javascript:;">
                          <span class="badge bg-red pull-right">50%</span>
                          <span>Settings</span>
                        </a>
                    <a class="dropdown-item"  href="javascript:;">Help</a>
                      <a class="dropdown-item"  href="login.html"><i class="fa fa-sign-out pull-right"></i> Log Out</a>
                    </div>
                  </li>
  
                  <li role="presentation" class="nav-item dropdown open">
                    <a href="javascript:;" class="dropdown-toggle info-number" id="navbarDropdown1" data-toggle="dropdown" aria-expanded="false">
                      <i class="fa fa-envelope-o"></i>
                      <span class="badge bg-green">6</span>
                    </a>
                    <ul class="dropdown-menu list-unstyled msg_list" role="menu" aria-labelledby="navbarDropdown1">
                      <li class="nav-item">
                        <a class="dropdown-item">
                          <span class="image"><img src="images/img.jpg" alt="Profile Image" /></span>
                          <span>
                            <span>John Smith</span>
                            <span class="time">3 mins ago</span>
                          </span>
                          <span class="message">
                            Film festivals used to be do-or-die moments for movie makers. They were where...
                          </span>
                        </a>
                      </li>
                      <li class="nav-item">
                        <a class="dropdown-item">
                          <span class="image"><img src="images/img.jpg" alt="Profile Image" /></span>
                          <span>
                            <span>John Smith</span>
                            <span class="time">3 mins ago</span>
                          </span>
                          <span class="message">
                            Film festivals used to be do-or-die moments for movie makers. They were where...
                          </span>
                        </a>
                      </li>
                      <li class="nav-item">
                        <a class="dropdown-item">
                          <span class="image"><img src="images/img.jpg" alt="Profile Image" /></span>
                          <span>
                            <span>John Smith</span>
                            <span class="time">3 mins ago</span>
                          </span>
                          <span class="message">
                            Film festivals used to be do-or-die moments for movie makers. They were where...
                          </span>
                        </a>
                      </li>
                      <li class="nav-item">
                        <a class="dropdown-item">
                          <span class="image"><img src="images/img.jpg" alt="Profile Image" /></span>
                          <span>
                            <span>John Smith</span>
                            <span class="time">3 mins ago</span>
                          </span>
                          <span class="message">
                            Film festivals used to be do-or-die moments for movie makers. They were where...
                          </span>
                        </a>
                      </li>
                      <li class="nav-item">
                        <div class="text-center">
                          <a class="dropdown-item">
                            <strong>See All Alerts</strong>
                            <i class="fa fa-angle-right"></i>
                          </a>
                        </div>
                      </li>
                    </ul>
                  </li>
                </ul>
              </nav>
            </div>
          </div>
        <!-- /top navigation -->

        <!-- page content -->
        <div class="right_col" role="main">
        
            <div class="page-title">
              <div class="title_left">
                <h3>Orders</h3>
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
<div class="x_panel">
						<div class="x_title row" >
                                                    <div class="col-6"><h2>Delivery Note</h2></div>	
                                                  
							<div class="clearfix"></div>
						</div>
						<div class="x_content">

							<div class="row">

								

								<div class="col-md-6 col-sm-12  form-group">
                                                                    <Lable class="form-control">Order Id - <%= o.getIdorder() %></lable>
								</div>

								<div class="col-md-6 col-sm-12  form-group">
									<Lable class="form-control">Customer Name - <%= o.getCustomer().getFirstName().toUpperCase()+" "+o.getCustomer().getLastName().toUpperCase() %></lable>
								</div>
								<div class="col-md-6 col-sm-12  form-group">
                                                                    <Lable class="form-control">Customer Contact - <%= o.getCustomer().getContact() %></lable>
								</div>

								<div class="col-md-6 col-sm-12  form-group">
                                                                    <Lable class="form-control">Order Date - <%= new SimpleDateFormat("YYYY-MM-dd").format(o.getDate()) %></lable>
								</div>
								<div class="col-md-6 col-sm-12  form-group">
                                                                     <Lable class="form-control">Order Deliver Date - <%= new SimpleDateFormat("YYYY-MM-dd").format(o.getDeliverDate()) %></lable>
								</div>

						
                                                                  
								<div class="col-md-6 col-sm-12  form-group">
                                                                    <Lable class="form-control">Total - <%= o.getTotal() %></lable>
								</div>

								<div class="col-md-6 col-sm-12  form-group">
									<Lable class="form-control">Discount - <%= o.getDiscount() %></lable>
								</div>
								<div class="col-md-6 col-sm-12  form-group">
                                                                    <Lable class="form-control">Due Amount- <%= o.getDueAmount() %></lable>
								</div>

								<div class="col-md-6 col-sm-12  form-group">
									<Lable class="form-control">Order Status - <%= o.getOrderStatus().getStatus() %></lable>
								</div>
                                                                <div class="col-md-4 col-sm-8  form-group">
                                                                    <select class="form-control" id="status_selecter">
                                                                      <%
                                                                     List<OrderStatus> statuses=hibernateSession.createCriteria(OrderStatus.class).list();
                                                                     for (OrderStatus elem : statuses) {
                                                                         %>
                                                                         <option class="dropdown-item" value="<%= elem.getIdorderStatus() %>" ><%= elem.getStatus().toUpperCase() %></option>
                                                                      
                                                                        <%    
                                                                         }
                                                                      %>
                                                                      
                                                                    </select>
								</div>
                                                                <div class="col-md-2 col-sm-4  form-group">
                                                                    <button class="btn btn-danger" onclick="changeStatus('<%= o.getIdorder() %>')">Change Status</button>
								</div>


								
							</div>
                                                                <div class="clearfix"></div>
                                                               <div class="col-md-12 col-sm-12  form-group">
                                                                   
                                                                   <label>Order Description -</label></br>
                                                                   <label><%= o.getDescription() %></label>
                                                                </div>

						</div>
					</div>
          
            
                      <div class="col-md-12 col-sm-12 ">
                <div class="x_panel">
                  <div class="x_title">
                    <h2>Delivery Items<small>search,update,print Orders from here</small></h2>
                  
                    <div class="clearfix"></div>
                  </div>
                  <div class="x_content">
                      <div class="row">
                          <div class="col-sm-12">
                            <div class="card-box table-responsive">
                  
                    <table id="order-datatable" class="table table-striped table-bordered" style="width:100%">
                      <thead>
                        <tr>
                          <th>ID</th>
                          <th>Name</th>
                          <th>Ordered Quantity</th>
                          <th>Delivered Quantity</th>
                          <th>To Be Delivered Quantity</th>
                          <th>Deliver Quantity</th>
                          <th>Order Item Description</th>
                        
                          <th></th>
                         
                          
                        </tr>
                      </thead>


                      <tbody id="customer_table_body">
                        <%
                      List<DeliveryItemBean>  deliveryList=DeliveryNoteValid.availableOrderItems(hibernateSession, o.getIdorder());
                       List<OrderItem> orders=(List<OrderItem>)hibernateSession.createCriteria(OrderItem.class).add(Restrictions.eq("order", o)).list();
for (DeliveryItemBean item : deliveryList) {
                       
%>

<tr id="orderItemTable<%= item.getOrderItem().getIdorderItem() %>">
                          <td><%= item.getOrderItem().getIdorderItem() %></td>
                          <td><%= item.getOrderItem().getSellingItems().getName() %></td>
                          <td><%= item.getOrderItem().getQty() %></td>
                          <td><%= item.getDeliveredQty() %></td>
                          <td><%= item.getToBeDeliveredQty() %></td>
                          <td><input type="number" value="0" id="text<%= item.getOrderItem().getIdorderItem() %>" class="form-control" placeholder="Enter Deliver Quantity"></td>
                          <td><%= item.getOrderItem().getOrderItemDescription() %></td>
                        
                        
                       
                          <td><i  onclick="removeOrder(<%= item.getOrderItem().getIdorderItem() %>);" class="fa fa-remove"></i></td>
                        </tr>
                          
                          <%
                          }
                       
if(deliveryList.isEmpty()){
%>
                      <div>
                          <label style="color: red;font-size: 30px;">All Items Are Delivered</label>
                      </div>  
                        <%
}
                          %>
                       
                      </tbody>
                    </table>
                         
                          
                  </div>
                          
                          <br><label>Delivery Note Description</label></br>
                          <textarea id="order-description" class="form-control"></textarea><br>
                          <div class="row no-print justify-content-end">
                                                <div class=" ">
                                      
                                                    <button class="btn btn-primary pull-right" style="margin-right: 5px;" onclick="confirmDeliveryNote();">Confirm Delivery Note</button>
                                                </div>
                                            </div>
                </div>
              </div>
            </div>
                </div>
              </div>
            
         
       
            
        </div>
        <!-- /page content -->
        

        <!-- footer content -->
        <footer>
          <div class="pull-right">
            Gentelella - Bootstrap Admin Template by <a href="https://colorlib.com">Colorlib</a>
          </div>
          <div class="clearfix"></div>
        </footer>
        <!-- /footer content -->
      </div>
    </div>

    <!-- /modal -->
    <button id="customer-modal-button" type="button" class="btn btn-primary" style="display:none;" data-toggle="modal" data-target=".bs-example-modal-lg">Large modal</button>

                  <div class="modal fade bs-example-modal-lg" tabindex="-1" role="dialog" aria-hidden="true">
                    <div class="modal-dialog modal-lg">
                      <div class="modal-content">

                        <div class="modal-header">
                          <h4 class="modal-title" id="myModalLabel">Update Customer</h4>
                          <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">×</span>
                          </button>
                        </div>
                        <div class="modal-body" id="customer-modal-body">

                     </div>
                      

                      </div>
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
	
     
	<!-- Parsley -->
	<script src="vendors/parsleyjs/dist/parsley.min.js"></script>
	
        <!-- PNotify -->
    <script src="vendors/pnotify/dist/pnotify.js"></script>
    <script src="vendors/pnotify/dist/pnotify.buttons.js"></script>
    <script src="vendors/pnotify/dist/pnotify.nonblock.js"></script>

    
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
        
        <!-- Custom Theme Scripts 
        -->
	<script src="build/js/custom.min.js"></script>
        
        <script type="text/javascript" src="build/hd_javascript/order.js"></script>
        <script type="text/javascript" src="build/hd_javascript/validation.js"></script>
  </body>
</html>
 <%    
 }else{
response.sendRedirect("all_Orders.jsp");
 }   
}else{
response.sendRedirect("all_Orders.jsp");
}


%>