/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function registerSellingItems() {
    var form = document.getElementById("demo-form2");
    $('#demo-form2').parsley().validate();
    if ($('#demo-form2').parsley().isValid()) {
        var name = document.getElementById('name').value;
        var price = document.getElementById('price').value;
    



        var request = new XMLHttpRequest();
        request.onreadystatechange = function () {
            if (request.readyState === 4) {
                if (request.status === 200) {
                    var response = request.responseText;
                    var jsonResponse = JSON.parse(response);
                    alert(response);

                    if (jsonResponse.status === 1) {
                        $("#demo-form2").trigger("reset");
                        viewMyPopup('Selling Item Registration', 'New Selling Item Successfully Registered!', 'success', 'bootstrap3');
                        //  var t = $('#customer-datatable').DataTable();

                        var rowIndex = $('#order-datatable').dataTable().fnAddData([
                            jsonResponse.contend.id,
                            jsonResponse.contend.name,
                            jsonResponse.contend.price,
                            jsonResponse.contend.date,
                           
                            "<button onclick='dispatchSellingItemUpdateForm(" + jsonResponse.contend.id + ");' class='btn btn-dark'>Update</button>"
                        ]);
                        var row = $('#order-datatable').dataTable().fnGetNodes(rowIndex);
                        $(row).attr('id', 'customerTable' + jsonResponse.contend.id);






                    } else {
                        viewMyPopup('Selling Item  Registration', jsonResponse.message, 'error', 'bootstrap3');

                    }


                }

            }
        };
        var parameters = "name=" + name + "&price=" + price;

        request.open("POST", "hd_RegisterSellingItems", true);
        request.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        request.send(parameters);
    }


}
function updateSellingProduct(id) {
    var form = document.getElementById("customer-update-form");
    $('#customer-update-form').parsley().validate();
    if ($('#customer-update-form').parsley().isValid()) {

        var name = document.getElementById('update-name').value;

        var price = document.getElementById('update-price').value;

      




        var request = new XMLHttpRequest();
        request.onreadystatechange = function () {
            if (request.readyState === 4) {
                if (request.status === 200) {

                    var response = request.responseText;
                    var jsonResponse = JSON.parse(response);
                    alert(response);


                    if (jsonResponse.status === 1) {

                        alert('customerTable' + jsonResponse.contend.id);
                        $('#customer-datatable').dataTable().fnDeleteRow($('#customer-datatable').dataTable().$('#customerTable' + jsonResponse.contend.id)[0]);
//                        
                        //  $('#customer-datatable').dataTable().fnDeleteRow( $('#customerTable'+jsonResponse.contend.id, $('#customer-datatable').dataTable().fnGetNodes()) );

                        var rowIndex = $('#customer-datatable').dataTable().fnAddData([
                            jsonResponse.contend.id,
                            jsonResponse.contend.name,
                            jsonResponse.contend.price,
                            jsonResponse.contend.date,
                           
                            "<button onclick='dispatchSellingItemUpdateForm(" + jsonResponse.contend.id + ");' class='btn btn-dark'>Update</button>"
                        ]);

                        var row = $('#customer-datatable').dataTable().fnGetNodes(rowIndex);
                        $(row).attr('id', 'customerTable' + jsonResponse.contend.id);
                        document.getElementById("customer-modal-button").click();
                        viewMyPopup('Selling Item Update', jsonResponse.message, 'success', 'bootstrap3');


                    } else {
                        viewMyPopup('Selling Item Update', jsonResponse.message, 'error', 'bootstrap3');

                    }


                }

            }
        };
        var parameters = "name=" + name + "&price=" + price + "&id=" + id;
        alert(parameters);
        request.open("POST", "hd_RegisterSellingItems", true);
        request.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        request.send(parameters);
    }


}

function dispatchSellingItemUpdateForm(itemid) {

    // if(isNumber(customerid)){
    var request = new XMLHttpRequest();

    request.onreadystatechange = function () {
        if (request.readyState === 4) {
            if (request.status === 200) {
                var response = request.responseText;
                if (response !== 0) {
                    document.getElementById("customer-modal-body").innerHTML = response;
                    document.getElementById("customer-modal-button").click();

                } else {

                    viewMyPopup('Selling Item Update', 'Failed!', 'error', 'bootstrap3');

                }

            }
        }

    };
    request.open("GET", "hd_SellingItemFormDispatch?id=" + itemid, true);
    request.send();


    //}else {
    //viewMyPopup('Customer Update','Update Failed!','error','bootstrap3');
    //}


}
function viewMyPopup(title, text, type, styling) {
    new PNotify({
        title: title,
        text: text,
        type: type,
        styling: styling
    });
}
function init_DataTables() {
    if (console.log("run_datatables"),
            void 0 !== $.fn.DataTable) {
        console.log("init_DataTables");
        TableManageButtons = function () {
            "use strict";
            return {
                init: function () {
                    $("#customer-datatable").length && $("#customer-datatable").DataTable({
                        dom: "Blfrtip",
                        buttons: [{
                                extend: "copy",
                                className: "btn-sm"
                            }, {
                                extend: "csv",
                                className: "btn-sm"
                            }, {
                                extend: "excel",
                                className: "btn-sm"
                            }, {
                                extend: "pdfHtml5",
                                className: "btn-sm"
                            }, {
                                extend: "print",
                                className: "btn-sm"
                            }],
                        responsive: !0
                    })
                }
            }
        }(),
                $("#datatable").dataTable(),
                $("#datatable-keytable").DataTable({
            keys: !0
        }),
                $("#datatable-responsive").DataTable(),
                $("#datatable-scroller").DataTable({
            ajax: "js/datatables/json/scroller-demo.json",
            deferRender: !0,
            scrollY: 380,
            scrollCollapse: !0,
            scroller: !0
        }),
                $("#datatable-fixed-header").DataTable({
            fixedHeader: !0
        });
        var e = $("#datatable-checkbox");
        e.dataTable({
            order: [[1, "asc"]],
            columnDefs: [{
                    orderable: !1,
                    targets: [0]
                }]
        }),
                e.on("draw.dt", function () {
                    $("checkbox input").iCheck({
                        checkboxClass: "icheckbox_flat-green"
                    })
                }),
                TableManageButtons.init()
    }
}
$(document).ready(function () {
    init_DataTables();

});