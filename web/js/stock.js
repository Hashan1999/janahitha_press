var atmp = "id";

function loadRawItems() {
    var supName = $("#supList").val();
    var req = new XMLHttpRequest();

    req.onreadystatechange = function () {
        if (req.readyState === 4 && req.status === 200) {

            var grnID = req.responseText;




        }
    };
    var param2 = "supNamee=" + supName;
    req.open("POST", "loadSup?" + param, true);
    req.send()



}


$(document).ready(function () {
   
   



    



});
function setTotal() {
    var attempt = 0;

    $("#grnTable tr").each(function () {
        var currentRow = $(this);

        var col3_value = currentRow.find("td:eq(4)").text();

        attempt = (+attempt) + (+col3_value);



    });
    $("#total").text(attempt);
}
function insert() {

    if ($("#stockOut tr").length == "1") {

        new PNotify({
            title: '',
            text: 'add items',
            type: 'error',
            styling: 'bootstrap3'
        });

    } else {

        var des = $("#des").val();
        
        var total = $("#total").text();
        var param = "des=" + des;
        var req = new XMLHttpRequest();



        req.onreadystatechange = function () {


            if (req.readyState === 4 && req.status === 200) {

                var responce = req.responseText;
                if (responce === "ok") {

                    // $("#stockOut tr").each(function () {
                    var rowCount = (+$('#stockOut tr').length) - (+1);
                    
                    for (var row = 1; row < rowCount; row++) {

                        var stockId = $('#stockOut tbody tr:eq(' + row + ') td:eq(0)').text();
                        var qty = $('#stockOut tbody tr:eq(' + row + ') td:eq(2)').text();
                        
                        
                        var req2 = new XMLHttpRequest();
//                           req2.onreadystatechange = function () {
//                                 if (req2.readyState === 4 && req2.status === 200) {
//                                     
//                                     var param3 = "sId=" + stockId + "&qty=" + qty;
//                                     req2.open("POST", "stockUpdate?" + param2, true);
//                                     req2.send()
//                                     
//                                 }
//                               
//                           };
//                        var currentRow = $(this);
//
//                        if (currentRow.find("td:eq(0)").text() === "") {
//                            alert("wada");
//                        } else{
                        //var stockId = currentRow.find("td:eq(o)").text();

                        //  var qty = currentRow.find("td:eq(2)").text();




                        var param2 = "sId=" + stockId + "&qty=" + qty;
                        req2.open("POST", "stockHasStockOutInsert?" + param2, true);
                        req2.send();
                        

                        // }
                    }
                    // });

                }


            }

        };
        req.open("POST", "stockOutInsert?" + param, true);
        req.send();

        new PNotify({
            title: '',
            text: 'saved',
            type: 'success',
            styling: 'bootstrap3'
        });





    }
    stockUpdate();
}
function stockUpdate() {
    var rowCount = (+$('#stockOut tr').length) - (+1);

    for (var row = 1; row < rowCount; row++) {

        var stockId = $('#stockOut tbody tr:eq(' + row + ') td:eq(0)').text();
        var qty = $('#stockOut tbody tr:eq(' + row + ') td:eq(2)').text();

        var req2 = new XMLHttpRequest();
        //     req2.onreadystatechange = function () {
        //          if (req2.readyState === 4 && req2.status === 200) {
        //               
        var param3 = "sId=" + stockId + "&qty=" + qty;
        req2.open("POST", "stockUpdate?" + param3, true);
        req2.send();

        //          }

        //   };
//                        var currentRow = $(this);
//
//                        if (currentRow.find("td:eq(0)").text() === "") {
//                            alert("wada");
//                        } else{
        //var stockId = currentRow.find("td:eq(o)").text();

        //  var qty = currentRow.find("td:eq(2)").text();

       
        stockList();

    }
}
function resetTable() {
    $('#stockId').text("");
    $('#rawitem').text("");
    $('#qty').text("");
    $('#des').text("");
    
    var table = $('#stockOut').DataTable();

    table
            .clear()
            .draw();

    $("#stockOut").DataTable();
    $(".dataTables_empty").empty();

}
function grnItems(id) {

    window.location.href = "grnItems.jsp?id=" + id;
}

function loadStockOut(id) {
    window.location.href = "stockOut.jsp?id=" + id;
}
function addRaw(id) {
    var input = document.getElementById(id);
    var qty = input.value;



    if (atmp === id) {


    } else {
        input.addEventListener("keyup", function (event) {

            atmp = id;
            var xx=true;
            
            if (event.keyCode === 13) {


                var req2 = new XMLHttpRequest();
                req2.onreadystatechange = function () {
                    if (req2.readyState === 4 && req2.status === 200) {
                        var currentQty = req2.responseText;

                       
                        
                        if ((+currentQty) > (+input.value)) {
                            
                            for (var row = 0; row < $('#stockOut tr').length; row++) {
                                var rawItem = $('#stockOut tbody tr:eq(' + row + ') td:eq(0)').text();
                                var tableQty = $('#stockOut tbody tr:eq(' + row + ') td:eq(2)').text();

                                

                                if ($("#stockId").text() === rawItem) {
                                
                                    var newQty = (+tableQty)+(+input.value);
                                    if((+currentQty)>(+newQty)){
                                        
                                         var req = new XMLHttpRequest();

                            req.onreadystatechange = function () {
                                if (req.readyState === 4 && req.status === 200) {
                                    var rawItemName = req.responseText;
                                    $("#rawitem").text(rawItemName);
                                    $("#stockId").text(id);

                                    $("#qty").text(input.value);
                                    document.getElementById(id).value = "";
                                    document.getElementById("mybtn").focus(); 

                                }
                            };
                            var param = "id=" + id;
                            req.open("POST", "loadStock?" + param, true);
                            req.send();
                            document.getElementById(id).value("0");
                                        
                                        
                                    }else{
                                       new PNotify({
                                title: '',
                                text: 'out of stock',
                                type: 'error',
                                styling: 'bootstrap3'
                            }); 
                                    }
                                    
                                xx=false;
                                }


                            }

                            if(xx){
                                var req = new XMLHttpRequest();

                            req.onreadystatechange = function () {
                                if (req.readyState === 4 && req.status === 200) {
                                    var rawItemName = req.responseText;
                                    $("#rawitem").text(rawItemName);
                                    $("#stockId").text(id);

                                    $("#qty").text(input.value);
                                    document.getElementById(id).value = "";
                                    document.getElementById("mybtn").focus(); 

                                }
                            };
                            var param = "id=" + id;
                            req.open("POST", "loadStock?" + param, true);
                            req.send();
                            document.getElementById(id).value("0");
                            }
                            
                            

                        } else {
                            new PNotify({
                                title: '',
                                text: 'out of stock',
                                type: 'error',
                                styling: 'bootstrap3'
                            });

                        }

                    }

                };
                var param2 = "id=" + id;
                req2.open("POST", "qtyCheck?" + param2, true);
                req2.send();




            }
        });
    }
    
}
function addToTable() {


    $("#stockOut").DataTable();
    $(".dataTables_empty").empty();

    var des = $("#des").val();
    
    var qty = $("#qty").text();


    var x = true;
//            if (des == "") {
//                new PNotify({
//                    title: '',
//                    text: 'enter description',
//                    type: 'error',
//                    styling: 'bootstrap3'
//                });
//            } else {



    for (var row = 0; row < $('#stockOut tr').length; row++) {
        var rawItem = $('#stockOut tbody tr:eq(' + row + ') td:eq(0)').text();



        if ($("#stockId").text() === rawItem) {

            var currentQty = $('#stockOut tbody tr:eq(' + row + ') td:eq(2)').text();
            var newQty = (+currentQty) + (+$("#qty").text());



            $('#stockOut tbody tr:eq(' + row + ') td:eq(2)').text(newQty);


            x = false;
        }

    }

    if (x) {
        if ($("#stockOut tbody").length == 0) {
            $("#stocknOut").append("<tbody></tbody>");
        }

        // Append product to the table

        $("#stockOut tbody").append(
                "<tr>" +
                "<td>" + $("#stockId").text() + "</td>" +
                "<td>" + $("#rawitem").text() + "</td>" +
                "<td>" + $("#qty").text() + "</td>" +
                "</tr>");
        $('#stockOut').DataTable();
    }






    //   }
}
function stockList() {
    var req = new XMLHttpRequest();
    req.onreadystatechange = function () {
        if (req.readyState === 4 && req.status === 200) {
            var resp = req.responseText;
            document.getElementById("stockTable").innerHTML = resp;
            $('#stocktable').DataTable();
        }
    };

    req.open("POST", "stockTable", true);
    req.send();
}
function stockList2() {
    
    var req = new XMLHttpRequest();
    req.onreadystatechange = function () {
        if (req.readyState === 4 && req.status === 200) {
            var resp = req.responseText;
            document.getElementById("tabl3").innerHTML = resp;
           
               $('#stocktable2').DataTable( {
        dom: 'Bfrtip',
        buttons: [
            'excel', 'pdf', 'print'
        ]
    } );
            
            
        }
    };

    req.open("POST", "stockTable2", true);
    req.send();
}
function stockStatusChange(id){
    
    var req = new XMLHttpRequest();
    req.onreadystatechange = function () {
        if (req.readyState === 4 && req.status === 200) {
            stockList2();
        }
    };

    req.open("POST", "stockStatus?id="+id, true);
    req.send();
    
}