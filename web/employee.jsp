
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!-- Meta, title, CSS, favicons, etc. -->
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <title>Employee Management | Janahitha</title>

        <!-- Bootstrap -->
        <link href="<%= request.getContextPath()%>/vendors/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">
        <!-- Font Awesome -->
        <link href="<%= request.getContextPath()%>/vendors/font-awesome/css/font-awesome.min.css" rel="stylesheet">
        <!-- NProgress -->
        <link href="<%= request.getContextPath()%>/vendors/nprogress/nprogress.css" rel="stylesheet">
        <!-- iCheck -->
        <link href="<%= request.getContextPath()%>/vendors/iCheck/skins/flat/green.css" rel="stylesheet">
        <!-- Datatables -->
        <link href="<%= request.getContextPath()%>/vendors/datatables.net-bs/css/dataTables.bootstrap.min.css" rel="stylesheet">
        <link href="<%= request.getContextPath()%>/vendors/datatables.net-buttons-bs/css/buttons.bootstrap.min.css" rel="stylesheet">
        <link href="<%= request.getContextPath()%>/vendors/datatables.net-fixedheader-bs/css/fixedHeader.bootstrap.min.css" rel="stylesheet">
        <link href="<%= request.getContextPath()%>/vendors/datatables.net-responsive-bs/css/responsive.bootstrap.min.css" rel="stylesheet">
        <link href="<%= request.getContextPath()%>/vendors/datatables.net-scroller-bs/css/scroller.bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ekko-lightbox/5.3.0/ekko-lightbox.css" integrity="sha512-Velp0ebMKjcd9RiCoaHhLXkR1sFoCCWXNp6w4zj1hfMifYB5441C+sKeBl/T/Ka6NjBiRfBBQRaQq65ekYz3UQ==" crossorigin="anonymous" />

        <!-- Custom Theme Style -->
        <link href="<%= request.getContextPath()%>/build/css/custom.min.css" rel="stylesheet">
        <style>
            td {
                border: 1px #DDD solid;
                padding: 5px;
                cursor: pointer;
            }
            table.dataTable tbody tr.selected {
                background-color: rgba(0, 123, 255, 0.25) !important;
            }
            table.dataTable tbody tr {
                background-color: #FFF !important;
            }
            table.dataTable tbody tr:hover {
                background-color: rgba(0, 123, 255, 0.25) !important;
            }
            .btnx{
                margin-right: 5px !important;
            }
        </style>
    </head>
    <body>
        <div class="container body">
            <div class="main_container">

                <!-- page content -->
                <div class="right_col" role="main">
                    <div class="">
                        <div class="page-title">
                            <div class="title_left">
                                <h3>Employee Management</h3>
                            </div>
                        </div>

                        <div class="clearfix" style="margin-bottom: 10px;"></div>

                        <div class="row">
                            <div class="col-md-12 col-sm-12 col-xs-12">
                                <div class="x_panel">
                                    <div class="x_title">
                                        <h2>Current Employees</h2>
                                        <ul class="nav navbar-right panel_toolbox">
                                            <li>
                                                <button type="button" class="btn btn-info" data-toggle="modal" data-target="#modalEmployee">New Employee</button>
                                            </li>
                                            <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
                                            </li>
                                        </ul>
                                        <div class="clearfix"></div>
                                    </div>
                                    <div class="x_content" id="content_table">	

                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- /page content -->

            </div>
        </div>
        <div class="modal fade" id="modalEmployee" role="dialog">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title">Employee Details</h4>
                    </div>
                    <div class="modal-body">
                        <form class="form-horizontal form-label-left" id="employeeform" enctype="multipart/form-data">
                            <div class="form-group">
                                <label class="control-label col-md-3 col-sm-3 col-xs-12" for="first-name">Full Name <span class="required">*</span>
                                </label>
                                <div class="col-md-6 col-sm-6 col-xs-12">
                                    <input type="text" id="name"  name="name" required="required" class="form-control col-md-7 col-xs-12">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-md-3 col-sm-3 col-xs-12" for="nic">NIC<span class="required">*</span>
                                </label>
                                <div class="col-md-6 col-sm-6 col-xs-12">
                                    <input type="text" id="nic"  name="nic" required="required" class="form-control col-md-7 col-xs-12">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-md-3 col-sm-3 col-xs-12" for="email">Email<span class="required">*</span>
                                </label>
                                <div class="col-md-6 col-sm-6 col-xs-12">
                                    <input type="text" id="email"  name="email" required="required" class="form-control col-md-7 col-xs-12">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-md-3 col-sm-3 col-xs-12" for="contactno">Contact No.<span class="required">*</span>
                                </label>
                                <div class="col-md-6 col-sm-6 col-xs-12">
                                    <input type="text" id="contactno"  name="contactno" required="required" class="form-control col-md-7 col-xs-12">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-md-3 col-sm-3 col-xs-12" for="address">Address<span class="required">*</span>
                                </label>
                                <div class="col-md-6 col-sm-6 col-xs-12">
                                    <input type="text" id="address" name="address" required="required" class="form-control col-md-7 col-xs-12" placeholder="House No.">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-md-3 col-sm-3 col-xs-12" for="address1">
                                </label>
                                <div class="col-md-6 col-sm-6 col-xs-12">
                                    <input type="text" id="address1"  name="address1" required="required" class="form-control col-md-7 col-xs-12"  placeholder="Street 1">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-md-3 col-sm-3 col-xs-12" for="address2">
                                </label>
                                <div class="col-md-6 col-sm-6 col-xs-12">
                                    <input type="text" id="address2"  name="address2" required="required" class="form-control col-md-7 col-xs-12" placeholder="Street 2">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-md-3 col-sm-3 col-xs-12" for="addresscity">
                                </label>
                                <div class="col-md-6 col-sm-6 col-xs-12">
                                    <input type="text" id="addresscity" name="addresscity" required="" class="form-control col-md-7 col-xs-12" placeholder="City">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-md-3 col-sm-3 col-xs-12">Employee Type</label>
                                <div class="col-md-6 col-sm-6 col-xs-12">
                                    <select class="form-control col-md-7 col-xs-12" name="type" id="type">
                                        <option selected="" disabled="" value="none">Choose option</option>
                                        <option value="0">Administrator </option>
                                        <option value="1">Employee </option>
                                        <option value="2">Accountant </option>
                                        <option value="3">Cashier </option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-md-3 col-sm-3 col-xs-12" for="photo">Photo<span class="required">*</span>
                                </label>
                                <div class="col-md-6 col-sm-6 col-xs-12">
                                    <div style="margin-bottom: 10px;">
                                        <a class='btn btn-primary' href='javascript:;'>
                                            Choose Image...
                                            <input type="file" id="employeeimage" name="employeeimage" style='position:absolute;z-index:2;top:0;left:0;filter: alpha(opacity=0);-ms-filter:"progid:DXImageTransform.Microsoft.Alpha(Opacity=0)";opacity:0;background-color:transparent;color:transparent;' name="file_source" size="40"  onchange='$("#upload-file-info").html($(this).val());'>
                                        </a>
                                    </div>
                                    <span class='label label-info' style="margin-top: 10px;" id="upload-file-info"></span>
                                </div>
                            </div>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-info" onclick="saveEmployee();" >Save</button>
                        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                    </div>
                </div>

            </div>
        </div>
        <div class="modal fade" id="modalEmployeeUpdate" role="dialog">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title">Employee Details - <strong><span id="empheader">EMP01</span></strong></h4>
                    </div>
                    <div class="modal-body">
                        <form class="form-horizontal form-label-left" id="employeeformUpdate" enctype="multipart/form-data">
                            <input type="hidden" name="uempid" id="uempid">
                            <div class="form-group">
                                <label class="control-label col-md-3 col-sm-3 col-xs-12" for="first-name">Full Name <span class="required">*</span>
                                </label>
                                <div class="col-md-6 col-sm-6 col-xs-12">
                                    <input type="text" id="uname"  name="uname" required="required" class="form-control col-md-7 col-xs-12">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-md-3 col-sm-3 col-xs-12" for="nic">NIC<span class="required">*</span>
                                </label>
                                <div class="col-md-6 col-sm-6 col-xs-12">
                                    <input type="text" id="unic"  name="unic" required="required" class="form-control col-md-7 col-xs-12">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-md-3 col-sm-3 col-xs-12" for="email">Email<span class="required">*</span>
                                </label>
                                <div class="col-md-6 col-sm-6 col-xs-12">
                                    <input type="text" id="uemail"  name="uemail" required="required" class="form-control col-md-7 col-xs-12">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-md-3 col-sm-3 col-xs-12" for="contactno">Contact No.<span class="required">*</span>
                                </label>
                                <div class="col-md-6 col-sm-6 col-xs-12">
                                    <input type="text" id="ucontactno"  name="ucontactno" required="required" class="form-control col-md-7 col-xs-12">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-md-3 col-sm-3 col-xs-12" for="address">Address<span class="required">*</span>
                                </label>
                                <div class="col-md-6 col-sm-6 col-xs-12">
                                    <input type="text" id="uaddress" name="uaddress" required="required" class="form-control col-md-7 col-xs-12" placeholder="House No.">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-md-3 col-sm-3 col-xs-12" for="address1">
                                </label>
                                <div class="col-md-6 col-sm-6 col-xs-12">
                                    <input type="text" id="uaddress1"  name="uaddress1" required="required" class="form-control col-md-7 col-xs-12"  placeholder="Street 1">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-md-3 col-sm-3 col-xs-12" for="address2">
                                </label>
                                <div class="col-md-6 col-sm-6 col-xs-12">
                                    <input type="text" id="uaddress2"  name="uaddress2" required="required" class="form-control col-md-7 col-xs-12" placeholder="Street 2">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-md-3 col-sm-3 col-xs-12" for="addresscity">
                                </label>
                                <div class="col-md-6 col-sm-6 col-xs-12">
                                    <input type="text" id="uaddresscity" name="uaddresscity" required="" class="form-control col-md-7 col-xs-12" placeholder="City">
                                </div>
                            </div>
                            <div class="form-group" id="ustatusgroup" style="display: none;">
                                <label class="control-label col-md-3 col-sm-3 col-xs-12" for="ustatus">
                                    Employee Status
                                </label>
                                <div class="col-md-6 col-sm-6 col-xs-12">
                                    <button class="btn btn-success col-md-7 col-xs-12" id="ustatus" onclick="activateEmployee();"><span id="ustatustext">Activate Employee</span></button>
                                </div>
                            </div>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-info" onclick="updateEmployee();" >Save</button>
                        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                    </div>
                </div>

            </div>
        </div>
        <div class="modal fade" id="modalEmployeeType" role="dialog">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title">Employee Details - <strong><span id="Typeempheader">EMP01</span></strong></h4>
                    </div>
                    <div class="modal-body">
                        <form class="form-horizontal form-label-left" id="employeeformType" enctype="multipart/form-data">
                            <input type="hidden" name="Typeempid" id="Typeempid">
                            <div class="form-group">
                                <label class="control-label col-md-3 col-sm-3 col-xs-12" for="first-name">Full Name <span class="required">*</span>
                                </label>
                                <div class="col-md-6 col-sm-6 col-xs-12">
                                    <input type="text" id="Typename"  name="Typename" required="required" class="form-control col-md-7 col-xs-12" disabled="">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-md-3 col-sm-3 col-xs-12" for="nic">NIC<span class="required">*</span>
                                </label>
                                <div class="col-md-6 col-sm-6 col-xs-12">
                                    <input type="text" id="Typenic"  name="Typenic" required="required" class="form-control col-md-7 col-xs-12" disabled="">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-md-3 col-sm-3 col-xs-12">Employee Type</label>
                                <div class="col-md-6 col-sm-6 col-xs-12">
                                    <select class="form-control col-md-7 col-xs-12" name="Typetype" id="Typetype">
                                        <option selected="" disabled="" value="none">Choose option</option>
                                        <option value="0">Administrator </option>
                                        <option value="1">Employee </option>
                                        <option value="2">Accountant </option>
                                        <option value="3">Cashier </option>
                                    </select>
                                </div>
                            </div>

                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-info" onclick="changeType();" >Save</button>
                        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                    </div>
                </div>

            </div>
        </div>
        <!-- jQuery -->
        <script src="<%= request.getContextPath()%>/vendors/jquery/dist/jquery.min.js"></script>
        <!-- Bootstrap -->
        <script src="<%= request.getContextPath()%>/vendors/bootstrap/dist/js/bootstrap.min.js"></script>
        <!-- FastClick -->
        <script src="<%= request.getContextPath()%>/vendors/fastclick/lib/fastclick.js"></script>
        <!-- NProgress -->
        <script src="<%= request.getContextPath()%>/vendors/nprogress/nprogress.js"></script>
        <!-- iCheck -->
        <script src="<%= request.getContextPath()%>/vendors/iCheck/icheck.min.js"></script>
        <!-- Datatables -->
        <script src="<%= request.getContextPath()%>/vendors/datatables.net/js/jquery.dataTables.min.js"></script>
        <script src="<%= request.getContextPath()%>/vendors/datatables.net-bs/js/dataTables.bootstrap.min.js"></script>
        <script src="<%= request.getContextPath()%>/vendors/datatables.net-buttons/js/dataTables.buttons.min.js"></script>
        <script src="<%= request.getContextPath()%>/vendors/datatables.net-buttons-bs/js/buttons.bootstrap.min.js"></script>
        <script src="<%= request.getContextPath()%>/vendors/datatables.net-buttons/js/buttons.flash.min.js"></script>
        <script src="<%= request.getContextPath()%>/vendors/datatables.net-buttons/js/buttons.html5.min.js"></script>
        <script src="<%= request.getContextPath()%>/vendors/datatables.net-buttons/js/buttons.print.min.js"></script>
        <script src="<%= request.getContextPath()%>/vendors/datatables.net-fixedheader/js/dataTables.fixedHeader.min.js"></script>
        <script src="<%= request.getContextPath()%>/vendors/datatables.net-keytable/js/dataTables.keyTable.min.js"></script>
        <script src="<%= request.getContextPath()%>/vendors/datatables.net-responsive/js/dataTables.responsive.min.js"></script>
        <script src="<%= request.getContextPath()%>/vendors/datatables.net-responsive-bs/js/responsive.bootstrap.js"></script>
        <script src="<%= request.getContextPath()%>/vendors/datatables.net-scroller/js/dataTables.scroller.min.js"></script>
        <script src="<%= request.getContextPath()%>/vendors/jszip/dist/jszip.min.js"></script>
        <script src="<%= request.getContextPath()%>/vendors/pdfmake/build/pdfmake.min.js"></script>
        <script src="<%= request.getContextPath()%>/vendors/pdfmake/build/vfs_fonts.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/ekko-lightbox/5.3.0/ekko-lightbox.min.js" integrity="sha512-Y2IiVZeaBwXG1wSV7f13plqlmFOx8MdjuHyYFVoYzhyRr3nH/NMDjTBSswijzADdNzMyWNetbLMfOpIPl6Cv9g==" crossorigin="anonymous"></script>

        <!-- Custom Theme Scripts -->
        <script src="<%= request.getContextPath()%>/build/js/custom.min.js"></script>
        <script>
                            $(document).on('click', '[data-toggle="lightbox"]', function (event) {
                                event.preventDefault();
                                $(this).ekkoLightbox();
                            });
                            $(function () {
                                $.fn.dataTable.Buttons.defaults.dom.button.className = 'btn';
                                loadEmployees();
                            });
                            var table;
                            function loadEmployees() {
                                $.ajax({
                                    type: "GET",
                                    url: "<%= request.getContextPath()%>/partials/_employees.jsp",
                                    success: function (data) {
                                        document.getElementById("content_table").innerHTML = data;
                                        table = $('#datatable-responsive').DataTable({
                                            dom: 'Bfrtip',
                                            "iDisplayLength": 100,
                                            "lengthChange": false,
                                            buttons: [
                                                {
                                                    text: 'Edit Employee',
                                                    className: 'btn-warning btnx',
                                                    action: function (e, dt, node, config) {
                                                        if (table.rows('.selected').any()) {
                                                            var itemId = table.row('.selected').data()[0];
                                                            findSingle(itemId);
                                                        }
                                                    }
                                                },
                                                {
                                                    text: 'Remove Employee',
                                                    className: 'btn-danger btnx',
                                                    action: function (e, dt, node, config) {
                                                        if (table.rows('.selected').any()) {
                                                            var itemId = table.row('.selected').data()[0];
                                                            removeEmployee(itemId);
                                                        }
                                                    }
                                                },
                                            ]
                                        });
                                        $('#datatable-responsive tbody').on('click', 'tr', function () {
                                            if ($(this).hasClass('selected')) {
                                                $(this).removeClass('selected');
                                            } else {
                                                table.$('tr.selected').removeClass('selected');
                                                $(this).addClass('selected');
                                            }
                                        });
                                    },
                                    error: function (errMsg) {
                                        alert("Error");
                                    }
                                });
                            }
                            function saveEmployee() {
                                if (!$('#name').val()) {
                                    alert("Please fill out the Employee name");
                                    return;
                                }
                                if (!$('#nic').val()) {
                                    alert("Please fill out the Employee nic");
                                    return;
                                }
                                if (!$('#contactno').val()) {
                                    alert("Please fill out the Employee contact no");
                                    return;
                                }
                                var formData = new FormData($('#employeeform')[0]);
                                $.ajax({
                                    url: "<%= request.getContextPath()%>/employee/insert",
                                    type: 'POST',
                                    data: formData,
                                    processData: false,
                                    contentType: false
                                }).done(function (d) {
                                    if (d.status == "success") {
                                        $('#modalEmployee').modal('hide');
                                        loadEmployees();
                                        clearInputs();
                                    } else {
                                        alert(d.data);
                                    }
                                    console.log(d);
                                });
                            }
                            function findSingle(id) {
                                $('#uempid').val("");
                                $('#uname').val("");
                                $('#uemail').val("");
                                $('#unic').val("");
                                $('#ucontactno').val("");
                                $('#uaddress').val("");
                                $('#uaddress1').val("");
                                $('#uaddress2').val("");
                                $('#uaddresscity').val("");
                                document.getElementById("ustatusgroup").style.display = "none";
                                $.ajax({
                                    type: "POST",
                                    url: "<%= request.getContextPath()%>/employee/find?id=" + id,
                                    dataType: "json",
                                    success: function (data) {
                                        console.log(data);
                                        console.log(data.data.members);
                                        if (data.status === "success") {
                                            var obj = data.data.members;
                                            document.getElementById("empheader").value = obj.empid;
                                            document.getElementById("uempid").value = obj.empid;
                                            document.getElementById("uemail").value = obj.email;
                                            document.getElementById("uname").value = obj.name;
                                            document.getElementById("unic").value = obj.nic;
                                            document.getElementById("ucontactno").value = obj.contactno;
                                            document.getElementById("uaddress").value = obj.address;
                                            document.getElementById("uaddress1").value = obj.address1;
                                            document.getElementById("uaddress2").value = obj.address2;
                                            document.getElementById("uaddresscity").value = obj.addresscity;
                                            if (obj.status == 0) {
                                                document.getElementById("ustatusgroup").style.display = "block";
                                            }
                                            $('#modalEmployeeUpdate').modal('show');
                                        } else {
                                            alert("Error");
                                        }
                                    },
                                    error: function (errMsg) {
                                        alert("Error");
                                    }
                                });
                            }
                            function removeEmployee(id) {
                                var isDone = false;
                                if (confirm('Are you sure you want to remove this employee?')) {
                                    $.ajax({
                                        type: "POST",
                                        async: false,
                                        url: "<%= request.getContextPath()%>/employee/delete?id=" + id,
                                        dataType: "json",
                                        success: function (data) {
                                            if (data.status === "success") {
                                                isDone = true;
                                                loadEmployees();
                                            } else {
                                                alert("Error");
                                            }
                                        },
                                        error: function (errMsg) {
                                            alert("Error");
                                        }
                                    });

                                }
                                return  isDone;
                            }
                            function activateEmployee() {
                                var isDone = false;
                                if (confirm('Are you sure you want to re-enable this employee?')) {
                                    $.ajax({
                                        type: "POST",
                                        async: false,
                                        url: "<%= request.getContextPath()%>/employee/enable?id=" + document.getElementById("uempid").value,
                                        dataType: "json",
                                        success: function (data) {
                                            if (data.status === "success") {
                                                isDone = true;
                                                findSingle(document.getElementById("uempid").value);
                                                loadEmployees();
                                            } else {
                                                alert(data.data);
                                            }
                                        },
                                        error: function (errMsg) {
                                            alert("Error");
                                        }
                                    });

                                }
                                return  isDone;
                            }
                            function updatePhoto(id) {
                                var input = document.createElement('input');
                                input.type = 'file';
                                input.onchange = function (e) {
                                    var file = e.target.files[0];
                                    console.log(file);
                                    var formData = new FormData();
                                    formData.append('employeeimage', file);
                                    $.ajax({
                                        url: "<%= request.getContextPath()%>/employee/updatephoto?id=" + id,
                                        type: 'POST',
                                        data: formData,
                                        processData: false,
                                        contentType: false
                                    }).done(function (d) {
                                        if (d.status == "success") {
                                            loadEmployees();
                                        } else {
                                            alert(d.data);
                                        }
                                        console.log(d);
                                    });
                                };
                                input.click();
                            }
                            function updateType(id) {
                                $('#Typename').val("");
                                $('#Typenic').val("");
                                $('#Typeempid').val("");
                                $.ajax({
                                    type: "POST",
                                    url: "<%= request.getContextPath()%>/employee/find?id=" + id,
                                    dataType: "json",
                                    success: function (data) {
                                        console.log(data);
                                        console.log(data.data.members);
                                        if (data.status === "success") {
                                            var obj = data.data.members;
                                            document.getElementById("Typeempheader").value = obj.empid;
                                            document.getElementById("Typename").value = obj.name;
                                            document.getElementById("Typenic").value = obj.nic;
                                            document.getElementById("Typeempid").value = obj.empid;
                                            if (obj.type == 0) {
                                                $('#Typetype').val('0').change();
                                            } else if (data.data.type == 1) {
                                                $('#Typetype').val('1').change();
                                            } else if (data.data.type == 2) {
                                                $('#Typetype').val('2').change();
                                            } else if (data.data.type == 3) {
                                                $('#Typetype').val('3').change();
                                            } else {
                                                $('#Typetype').val('1').change();
                                            }
                                            $('#modalEmployeeType').modal('show');
                                        } else {
                                            alert("Error");
                                        }
                                    },
                                    error: function (errMsg) {
                                        alert("Error");
                                    }
                                });
                            }
                            function changeType() {
                                var isDone = false;
                                if (confirm('Are you sure you want to change employee type?')) {
                                    $.ajax({
                                        type: "POST",
                                        async: false,
                                        url: "<%= request.getContextPath()%>/employee/updatetype?id=" + document.getElementById("Typeempid").value + "&type=" + document.getElementById("Typetype").value,
                                        dataType: "json",
                                        success: function (data) {
                                            if (data.status === "success") {
                                                isDone = true;
                                                $('#modalEmployeeType').modal('hide');
                                                loadEmployees();
                                            } else {
                                                alert(data.data);
                                            }
                                        },
                                        error: function (errMsg) {
                                            alert("Error");
                                        }
                                    });

                                }
                                return  isDone;
                            }
                            function updateEmployee() {
                                if (!$('#uname').val()) {
                                    alert("Please fill out the Employee name");
                                    return;
                                }
                                if (!$('#unic').val()) {
                                    alert("Please fill out the Employee nic");
                                    return;
                                }
                                if (!$('#ucontactno').val()) {
                                    alert("Please fill out the Employee contact no");
                                    return;
                                }
                                $.ajax({
                                    type: "POST",
                                    url: "<%= request.getContextPath()%>//employee/update/",
                                    data: {"form_data": JSON.stringify(create_data_json())},
                                    dataType: "json",
                                    success: function (data) {
                                        if (data.status === "success") {
                                            $('#modalEmployeeUpdate').modal('hide');
                                            loadEmployees();
                                        } else {
                                            alert(data.data);
                                        }
                                    },
                                    error: function (errMsg) {
                                        alert("Error");
                                    }
                                });
                            }
                            function clearInputs() {
                                $('#name').val("");
                                $('#nic').val("");
                                $('#eamil').val("");
                                $('#contactno').val("");
                                $('#address').val("");
                                $('#address1').val("");
                                $('#address2').val("");
                                $('#addresscity').val("");
                                $("#employeeimage").val(null);
                                $("#upload-file-info").html("")
                            }
                            function validate_data_string(data) {
                                if (!data) {
                                    return "";
                                }
                                return data;
                            }
                            function create_data_json() {
                                var json = {
                                    "empid": validate_data_string(document.getElementById("uempid").value),
                                    "name": validate_data_string(document.getElementById("uname").value),
                                    "email": validate_data_string(document.getElementById("uemail").value),
                                    "nic": validate_data_string(document.getElementById("unic").value),
                                    "contactno": validate_data_string(document.getElementById("ucontactno").value),
                                    "address": validate_data_string(document.getElementById("uaddress").value),
                                    "address1": validate_data_string(document.getElementById("uaddress1").value),
                                    "address2": validate_data_string(document.getElementById("uaddress2").value),
                                    "addresscity": validate_data_string(document.getElementById("uaddresscity").value)
                                };
                                return json;
                            }
        </script>
    </body>
</html>