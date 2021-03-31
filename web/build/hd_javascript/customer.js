/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function registerCustomer() {
    var form = document.getElementById("demo-form2");
    $('#demo-form2').parsley().validate();
    if ($('#demo-form2').parsley().isValid()) {
        var firstName = document.getElementById('first-name').value;
        var lastName = document.getElementById('last-name').value;
        var contact = document.getElementById('contact').value;
        var description = document.getElementById('description').value;



        var request = new XMLHttpRequest();
        request.onreadystatechange = function () {
            if (request.readyState === 4) {
                if (request.status === 200) {
                    var response = request.responseText;
                    var jsonResponse = JSON.parse(response);
                    alert(response);

                    if (jsonResponse.status === 1) {
                        $("#demo-form2").trigger("reset");
                        viewMyPopup('Customer Registration', 'New Customer Successfully Registered!', 'success', 'bootstrap3');
                        //  var t = $('#customer-datatable').DataTable();

                        var rowIndex = $('#customer-datatable').dataTable().fnAddData([
                            jsonResponse.contend.id,
                            jsonResponse.contend.firstName,
                            jsonResponse.contend.lastName,
                            jsonResponse.contend.contact,
                            jsonResponse.contend.description,
                            "<button onclick='dispatchUpdateForm(" + jsonResponse.contend.id + ");' class='btn btn-dark'>Update</button>"
                        ]);
                        var row = $('#customer-datatable').dataTable().fnGetNodes(rowIndex);
                        $(row).attr('id', 'customerTable' + jsonResponse.contend.id);






                    } else {
                        viewMyPopup('Customer Registration', jsonResponse.message, 'error', 'bootstrap3');

                    }


                }

            }
        };
        var parameters = "firstName=" + firstName + "&lastName=" + lastName + "&contact=" + contact + "&description=" + description;

        request.open("POST", "hd_RegisterCustomer", true);
        request.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        request.send(parameters);
    }


}
function updateCustomer(id) {
    var form = document.getElementById("customer-update-form");
    $('#customer-update-form').parsley().validate();
    if ($('#customer-update-form').parsley().isValid()) {

        var firstName = document.getElementById('update-first-name').value;

        var lastName = document.getElementById('update-last-name').value;

        var contact = document.getElementById('update-contact').value;

        var description = document.getElementById('update-description').value;




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
                            jsonResponse.contend.firstName,
                            jsonResponse.contend.lastName,
                            jsonResponse.contend.contact,
                            jsonResponse.contend.description,
                            "<button onclick='dispatchUpdateForm(" + jsonResponse.contend.id + ");' class='btn btn-dark'>Update</button>"
                        ]);

                        var row = $('#customer-datatable').dataTable().fnGetNodes(rowIndex);
                        $(row).attr('id', 'customerTable' + jsonResponse.contend.id);
                        document.getElementById("customer-modal-button").click();
                        viewMyPopup('Customer Update', jsonResponse.message, 'success', 'bootstrap3');


                    } else {
                        viewMyPopup('Customer Update', jsonResponse.message, 'error', 'bootstrap3');

                    }


                }

            }
        };
        var parameters = "firstName=" + firstName + "&lastName=" + lastName + "&contact=" + contact + "&description=" + description + "&id=" + id;
        alert(parameters);
        request.open("POST", "hd_RegisterCustomer", true);
        request.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        request.send(parameters);
    }


}

function dispatchUpdateForm(customerid) {

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

                    viewMyPopup('Customer Update', 'Failed!', 'error', 'bootstrap3');

                }

            }
        }

    };
    request.open("GET", "hd_CustomerFormDispatch?id=" + customerid, true);
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

function init_autocomplete_customer() {

    var request = new XMLHttpRequest();


    request.onreadystatechange = function () {
        if (request.readyState === 4) {
            if (request.status === 200) {
                var response = JSON.parse(request.responseText);
                // var response ={"1":"1 202020 5567i67 (0756819181)","2":"2 darshana DARSHANA (0756819181)","3":"3 kumara perera (0764755477)","4":"4 wwwwwww wwwwwwwwww (0758974561)","5":"5 opop 5567i67 (0756819181)","6":"6 opop 5567i67 (0756819181)","7":"7 opop 5567i67 (0756819181)","8":"8 opop 5567i67 (0756819181)","9":"9 rrrrrrrrrrrrr rrrrrrrrrrr (0758947845)"};

                if (typeof ($.fn.autocomplete) === 'undefined') {
                    return;
                }
                console.log('init_autocomplete');



                var countriesArray = $.map(response, function (value, key) {
                    return {
                        value: value,
                        data: key
                    };
                });

                // initialize autocomplete with custom appendTo
                $('#customer-autocomplte').autocomplete({
                    lookup: countriesArray,
                    triggerSelectOnValidInput: false,
                    onSelect: function (suggestion) {
                        alert('You selected: ' + suggestion.value + ', ' + suggestion.data);


                        var allData = suggestion.value.split('[');
                        var name = allData[0];
                        var contact = allData[1];

                        var customerData = document.getElementById("customer-address");
                        var namelabel = document.createElement('label');
                        namelabel.id = suggestion.data;
                        namelabel.innerHTML = name;
                        customerData.appendChild(namelabel);

                        var breaktag = document.createElement('br');
                        customerData.appendChild(breaktag);

                        var contactlabel = document.createElement('label');
                        contactlabel.innerHTML = contact;
                        customerData.appendChild(contactlabel);

                        // $('#order-unit-price').innerHtml=suggestion.data;
                    }
                });


            }
        }

    };
    request.open("GET", "hd_LoadCustomer", true);
    request.send();



}
function init_autocomplete_sellingItems() {

    var request = new XMLHttpRequest();


    request.onreadystatechange = function () {
        if (request.readyState === 4) {
            if (request.status === 200) {
                var response = JSON.parse(request.responseText);
                // var response ={"1":"1 202020 5567i67 (0756819181)","2":"2 darshana DARSHANA (0756819181)","3":"3 kumara perera (0764755477)","4":"4 wwwwwww wwwwwwwwww (0758974561)","5":"5 opop 5567i67 (0756819181)","6":"6 opop 5567i67 (0756819181)","7":"7 opop 5567i67 (0756819181)","8":"8 opop 5567i67 (0756819181)","9":"9 rrrrrrrrrrrrr rrrrrrrrrrr (0758947845)"};

                if (typeof ($.fn.autocomplete) === 'undefined') {
                    return;
                }
                console.log('init_autocomplete');



                var countriesArray = $.map(response, function (value, key) {
                    return {
                        value: value,
                        data: key
                    };
                });

                // initialize autocomplete with custom appendTo
                $('#selling-item-autocomplte').autocomplete({
                    lookup: countriesArray,
                    triggerSelectOnValidInput: false,
                    onSelect: function (suggestion) {
                        alert('You selected: ' + suggestion.value + ', ' + suggestion.data);
                        autofill_sellingiyem_data(suggestion.data);
                  
                        // $('#order-unit-price').innerHtml=suggestion.data;
                    }
                });


            }
        }

    };
    request.open("GET", "hd_LoadSellingItems", true);
    request.send();



}

function autofill_sellingiyem_data(sellingItemId) {

   
    if (isNumber(sellingItemId)) {


        var request = new XMLHttpRequest();
        request.onreadystatechange = function () {
            if (request.readyState === 4) {
                if (request.status === 200) {
                    var response = request.responseText;
                    var jsonResponse = JSON.parse(response);


                    if (jsonResponse.status === 1) {
                   
                        document.getElementById('order-unit-price').value = jsonResponse.contend.price;

                    } else {
                        viewMyPopup('Customer Registration', jsonResponse.message, 'error', 'bootstrap3');

                    }


                }

            }
        };

        request.open("GET", "hd_GetSellingItemForId?id=" + sellingItemId, true);
        request.send();
    } else {

        new PNotify({
            title: 'Order',
            text: 'Invalid Selling Item Id',
            type: 'error',
            styling: 'bootstrap3'
        });
    }


}

function addOrderItemToTable() {

    $('#selling-item-autocomplte').parsley().validate();

    $('#order-unit-price').parsley().validate();
    $('#order-quantity').parsley().validate();

    if ($('#selling-item-autocomplte').parsley().isValid() &&
            $('#order-unit-price').parsley().isValid() &&
            $('#order-quantity').parsley().isValid()) {

        try {
            var itemData = document.getElementById('selling-item-autocomplte').value.split('-');
            var itemId = itemData[0].trim();
            var itemName = itemData[1].trim();


            var unitPrice = document.getElementById('order-unit-price').value;
            var quantity = document.getElementById('order-quantity').value;
            var orderItemDescription = document.getElementById('order-item-description').value;


            if (isNumber(itemId)) {
                if (unitPrice.length !== 0 && isPositiveFloatNumber(unitPrice)) {
                    if (quantity.length !== 0 && isPositiveFloatNumber(quantity)) {



                        var request = new XMLHttpRequest();
                        request.onreadystatechange = function () {
                            if (request.readyState === 4) {
                                if (request.status === 200) {
                                    var response = request.responseText;
                                    var jsonResponse = JSON.parse(response);


                                    if (jsonResponse.status === 1) {


                                        var removeTd = document.createElement('td');
                                        var removeicon = document.createElement("i");
                                        removeicon.className = "fa fa-remove";
                                        removeTd.appendChild(removeicon);
                                        removeTd.className = 'btnDelete';

                                        var idTd = document.createElement('td');
                                        idTd.innerHTML = itemId;

                                        var productNameTd = document.createElement('td');
                                        productNameTd.innerHTML = itemName;

                                        var unitPriceTd = document.createElement('td');
                                        unitPriceTd.innerHTML = unitPrice;

                                        var qtyTd = document.createElement('td');
                                        qtyTd.innerHTML = quantity;

                                        var subtotalTd = document.createElement('td');
                                        subtotalTd.innerHTML = parseFloat(unitPrice) * parseFloat(quantity);
                                        subtotalTd.className = 'subtotal';
                                      
                                        var itemDescription = document.createElement('td');
                                        itemDescription.innerHTML = orderItemDescription;
                                       


                                        var newRow = document.createElement('tr');
                                        newRow.appendChild(removeTd);
                                        newRow.appendChild(idTd);
                                        newRow.appendChild(productNameTd);
                                        newRow.appendChild(itemDescription);
                                        newRow.appendChild(unitPriceTd);
                                        newRow.appendChild(qtyTd);
                                        newRow.appendChild(subtotalTd);

                                        document.getElementById('order-item-table').appendChild(newRow);
                                        tableSubtotal();
                                        setFullTotal();
                                        new PNotify({
                                            title: 'Order',
                                            text: 'Order item added to order Succesfully!',
                                            type: 'success',
                                            styling: 'bootstrap3'
                                        });

                                    } else {
                                        new PNotify({
                                            title: 'Order',
                                            text: 'Invalid Order Item',
                                            type: 'error',
                                            styling: 'bootstrap3'
                                        });

                                    }


                                }

                            }
                        };


                        request.open("GET", "hd_GetSellingItemForId?id=" + itemId, true);
                        request.send();






                    } else {
                        new PNotify({
                            title: 'Order',
                            text: 'Invalid Quantity',
                            type: 'error',
                            styling: 'bootstrap3'
                        });
                    }
                } else {
                    new PNotify({
                        title: 'Order',
                        text: 'Invalid Unit Price',
                        type: 'error',
                        styling: 'bootstrap3'
                    });
                }
            } else {
                new PNotify({
                    title: 'Order',
                    text: 'Invalid Order Item',
                    type: 'error',
                    styling: 'bootstrap3'
                });
            }


        } catch (e) {
            new PNotify({
                title: 'Order',
                text: 'Invalid Order Item',
                type: 'error',
                styling: 'bootstrap3'
            });
        }






    } else {

        alert('validate data');
    }


}




$(document).ready(function () {
    init_DataTables();
    init_autocomplete_customer();
    init_autocomplete_sellingItems();
    tableSubtotal();
});


$("#order-item-table").on('click', '.btnDelete', function () {
    $(this).closest('tr').remove();
    tableSubtotal();
    setFullTotal();
});

function tableSubtotal() {

    var total = 0;
    $('#order-item-table .subtotal').each(function () {

        total = parseFloat(total) + parseFloat($(this).html());
    });
    document.getElementById("order-subtotal").innerHTML = total;
}

$("#discount").bind("keyup", function (e) {


    var orderSubtotal = Number($('#order-subtotal').html());
    var discount = Number($('#discount').val());
    var payment = Number($('#payment').val());


    var subTotal = $('#order-subtotal').html();

    if (subTotal.length === 0) {
        orderSubtotal = 0;
    }


    if (parseFloat(orderSubtotal) > parseFloat(discount)) {
        setFullTotal();

    } else {

        $('#discount').val(discount.toString().substring(0, discount.toString().length - 1));

        new PNotify({
            title: 'Discount',
            text: 'Invalid Discount',
            type: 'error',
            styling: 'bootstrap3'
        });

    }



});
$("#payment").bind("keyup", function (e) {
    setFullTotal();
});

function setFullTotal() {
    var orderSubtotal = Number($('#order-subtotal').html());
    var discount = Number($('#discount').val());
    var payment = Number($('#payment').val());


    document.getElementById("full-total").innerHTML = orderSubtotal - (discount + payment);



}

function confirmOrder() {
    var customer = $('#customer-autocomplte').val();
    var orderItemTable = document.getElementById('order-item-table');

    var orderIdentifier = $('#order-identifier').val();
    var orderFinishDate = $('#order-date').val();
    var orderDescription = $('#order-description').val().trim();
    var orderDiscount = $('#discount').val();
    var orderAdvancePayment = $('#payment').val();
    var customerContactNumber;

    var orderItemJsonArray = [];
    var iterateOrderTable = true;
    var isValidData = false;

   
    
    if(orderDiscount.trim().length === 0){
        orderDiscount="0.0";
    }
    if(orderAdvancePayment.trim().length === 0){
        orderAdvancePayment="0.0";
    }
    
   
    
    if (customer.trim().length !== 0) {
        var customerArray = customer.split("[");
        customerContactNumber = customerArray[1].split("]")[0];
        if (isValidatedMobile(customerContactNumber)) {
            if (orderIdentifier.trim().length !== 0) {
                if (orderFinishDate.trim().length !== 0) {
                    const date1 = new Date(orderFinishDate);
                    const date2 = new Date();
                    const orderDate = Math.abs(date1);
                    const nowDate = Math.abs(date2);

                    if ((orderDate - nowDate) > 0 || (date1.getYear() === date2.getYear() && date1.getMonth() === date2.getMonth() && date1.getDate() === date2.getDate())) {
                        //if(!isContainsEscapeCharacters(orderIdentifier) && !isContainsEscapeCharacters(orderDescription)){

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



                                    if (j === 1 && isNumber(objCells.item(j).innerHTML) && objCells.item(j).innerHTML.length!==0) {
                                        orderItemJsonObject["itemId"] = objCells.item(j).innerHTML;
                                    } else if (j === 4 && isNumber(objCells.item(j).innerHTML) && objCells.item(j).innerHTML.length!==0) {
                                        orderItemJsonObject["itemQty"] = objCells.item(j).innerHTML;
                                    } else if (j === 5 && isNumber(objCells.item(j).innerHTML) && objCells.item(j).innerHTML.length!==0) {
                                        orderItemJsonObject["itemUnitPrice"] = objCells.item(j).innerHTML;
                                    } else if (j === 0) {
                                        continue;
                                    } else if (j === 2) {
                                        continue;
                                    } else if (j === 6) {
                                        continue;
                                    }else if(j === 3){
                                        orderItemJsonObject["itemDescription"] = objCells.item(j).innerHTML;
                                    } else {
                                        iterateOrderTable = false;
                                        new PNotify({
//        title: 'Confirm Order',
//        text: 'Order Identifier and Order Description cannot contains following characters &,'+"'"+',?',
//        type: 'error',
//        styling: 'bootstrap3'
//    });    new PNotify({
                                            title: 'Order Item',
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

//             }else{
//                         new PNotify({
//        title: 'Confirm Order',
//        text: 'Order Identifier and Order Description cannot contains following characters &,'+"'"+',?',
//        type: 'error',
//        styling: 'bootstrap3'
//    });  
//                 
//             }

                    } else {

                        new PNotify({
                            title: 'Confirm Order',
                            text: 'Invalid Date!',
                            type: 'error',
                            styling: 'bootstrap3'
                        });
                    }



                } else {
                    new PNotify({
                        title: 'Confirm Order',
                        text: 'Select a Order Complete Date!',
                        type: 'error',
                        styling: 'bootstrap3'
                    });
                }

            } else {
                new PNotify({
                    title: 'Confirm Order',
                    text: 'Enter a Order Identifier!',
                    type: 'error',
                    styling: 'bootstrap3'
                });
            }
        } else {
            new PNotify({
                title: 'Confirm Order',
                text: 'Invalid Customer!',
                type: 'error',
                styling: 'bootstrap3'
            });
        }

    } else {
        new PNotify({
            title: 'Confirm Order',
            text: 'Select Customer',
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
                  alert(jsonresponse.contend.id);     
                    new PNotify({
            title: 'Confirm Order',
            text: jsonresponse.message,
            type: 'success',
            styling: 'bootstrap3'
        });    
        
            var request2 = new XMLHttpRequest();

    request2.onreadystatechange = function () {
        if (request2.readyState === 4) {
            if (request2.status === 200) {
                var response2 = request2.responseText;
      
               var myWindow = window.open("", "Order", "width=1300,height=1000");
                 myWindow.document.write(response2);
                 myWindow.print();

            }
        }

    };
    request.open("GET", "hd_PrintInvoice?orderId=" + jsonresponse.contend.id, true);
    request.send();
        
        
                       
                       
                       
                   }else{
                           new PNotify({
            title: 'Confirm Order',
            text: jsonresponse.message,
            type: 'error',
            styling: 'bootstrap3'
        });
                   }
                   
               }
           }
           
       };
       
        var parameters = "orderIdentifier=" + orderIdentifier  + "&orderFinishDate=" + orderFinishDate  + "&orderDescription=" + orderDescription  + "&orderDiscount=" + orderDiscount+ "&orderAdvancePayment=" + orderAdvancePayment+ "&customerContactNumber=" + customerContactNumber+ "&orderItemJsonArray=" + JSON.stringify(orderItemJsonArray)  ;
        alert(parameters);
        request.open("POST","hd_AddNewOrder",true);
        request.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        request.send(parameters);
   
    }
   

}