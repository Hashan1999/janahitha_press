/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



function changeStatus(id){
    
  var selecter = document.getElementById("status_selecter");
  
  var statusid=selecter.value;
  var orderid=id;
  
  
    alert(statusid);
       var request = new XMLHttpRequest();

    request.onreadystatechange = function () {
        if (request.readyState === 4) {
            if (request.status === 200) {
                var response = request.responseText;
                    var jsonResponse = JSON.parse(response);
                    alert(response);

                    if (jsonResponse.status === 1) {
                        
                           new PNotify({
                title: 'Order Status Update',
                text: jsonResponse.message,
                type: 'succes',
                styling: 'bootstrap3'
            });
                   
                    location.reload();
                    
                    }else{
                           new PNotify({
                title: 'Order Status Update',
                text: jsonResponse.message,
                type: 'error',
                styling: 'bootstrap3'
            });
                    }

            }
        }

    };
   request.open("GET", "hd_OrderStatusChange?orderid=" + orderid+"&statusid="+statusid, true);
   request.send();
}

function init_DataTables() {
    if (console.log("run_datatables"),
            void 0 !== $.fn.DataTable) {
        console.log("init_DataTables");
        TableManageButtons = function () {
            "use strict";
            return {
                init: function () {
                    $("#order-datatable").length && $("#order-datatable").DataTable({
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

function confirmDeliveryNote() {
  
    var orderItemTable = document.getElementById('order-datatable');

    var description = $('#order-description').val();
   
    var orderItemJsonArray = [];
   
    var isValidData = false;
    var iterateOrderTable = true;

                      if(orderItemTable.rows.length>1){
                          isValidData=true;
                          
                        // LOOP THROUGH EACH ROW OF THE TABLE AFTER HEADER.
                        for (var i = 1; i < orderItemTable.rows.length; i++) {
                            var orderItemJsonObject = {};

                            // GET THE CELLS COLLECTION OF THE CURRENT ROW.
                            var objCells = orderItemTable.rows.item(i).cells;
                            // LOOP THROUGH EACH CELL OF THE CURENT ROW TO READ CELL VALUES.
                            for (var j = 0; j < objCells.length; j++) {

                                if (iterateOrderTable) {



                                if (j === 0 && isNumber(objCells.item(j).innerHTML) && objCells.item(j).innerHTML.length!==0) {
                                          
                         orderItemJsonObject["orderItemId"] = objCells.item(j).innerHTML;
                                    } else if (j === 4 && isNumber(objCells.item(j).innerHTML) && objCells.item(j).innerHTML.length!==0) {
                                       // orderItemJsonObject["itemQty"] = objCells.item(j).innerHTML;
                                    } else if (j === 5) {
                                             var id=objCells.item(0).innerHTML;
                        var qty=document.getElementById("text"+id).value;
                               if(qty.length ===0){
                                   qty=0;
                               }
                               orderItemJsonObject["qty"] =qty;
                               alert(qty);
                                      //  orderItemJsonObject["itemUnitPrice"] = objCells.item(j).innerHTML;
                                    } else if (j === 1) {
                                        continue;
                                    } else if (j === 2) {
                                        continue;
                                    } else if (j === 6) {
                                        continue;
                                    }
                    else if (j === 7) {
                                        continue;
                                    }                
                    else if(j === 3){
                                      //  orderItemJsonObject["itemDescription"] = objCells.item(j).innerHTML;
                                    } else {
                                        iterateOrderTable = false;
                                        new PNotify({
//        title: 'Confirm Order',
//        text: 'Order Identifier and Order Description cannot contains following characters &,'+"'"+',?',
//        type: 'error',
//        styling: 'bootstrap3'
//    });    new PNotify({
                                            title: 'Delivery Note',
                                            text: 'Invalid Order Item Detail found in Row' + i,
                                            type: 'error',
                                            styling: 'bootstrap3'
                                        });
                                        break;
                                    }



                                }

                            }
                            orderItemJsonArray.push(orderItemJsonObject);

                        }
                    }else{
                                         new PNotify({
        title: 'Order Items',
        text: 'Add Items to Order',
      type: 'error',
        styling: 'bootstrap3'
   });  
                    }

   
    

    alert(JSON.stringify(orderItemJsonArray));
   console.log(JSON.stringify(orderItemJsonArray));
   
   if(isValidData===true && iterateOrderTable===true){
       var request=new XMLHttpRequest();
       request.onreadystatechange=function (){
               if(request.readyState===4){
           if(request.status===200){
                   var response=request.responseText;
                   var jsonresponse=JSON.parse(response);
                   if(jsonresponse.status===1){
                       
                    new PNotify({
            title: 'Delivery Note',
            text: jsonresponse.message,
            type: 'success',
            styling: 'bootstrap3'
        });    
                       
                       
                       
                   }else{
                           new PNotify({
            title: 'Delivery Note',
            text: jsonresponse.message,
            type: 'error',
            styling: 'bootstrap3'
        });
                   }
                   
               }
           }
           
       };
       
        var parameters = "deliveryNoteDescription="+description+"&orderItemJsonArray=" + JSON.stringify(orderItemJsonArray)  ;
        alert(parameters);
        request.open("POST","hd_AddNewDeliveryNote",true);
        request.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        request.send(parameters);
   
    }
   

}