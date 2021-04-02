function loadSuppliers() {
    
    var req = new XMLHttpRequest();
    req.onreadystatechange = function () {
        if (req.readyState === 4 && req.status === 200) {
            var resp = req.responseText;
            document.getElementById("sup").innerHTML = resp;

        }
    };

    req.open("GET", "loadSuppliers", true);
    req.send();
}

function loadRawItems() {

    
    var req = new XMLHttpRequest();
    req.onreadystatechange = function () {
        if (req.readyState === 4 && req.status === 200) {
            var resp = req.responseText;
            document.getElementById("raw").innerHTML = resp;

        }
    };

    req.open("GET", "loadRaw", true);
    req.send();
}

$(document).ready(function () {

    $("#grnTable").DataTable();
    $(".dataTables_empty").empty();



    $('#quantity').keypress(function (e) {
        if (e.which == 13) {
            

            var uprice = $("#unitPrice").val();
            var qty = $("#quantity").val();
            var subTotal = uprice * qty;

            var x = true;
            if(uprice==""||qty==""){
                 new PNotify({
                        title: '',
                        text: 'enter unit price and quantity',
                        type: 'error',
                        styling: 'bootstrap3'
                    });
            }else{
            document.getElementById("supplierList").disabled = true;


            for (var row = 0; row < $('#grnTable tr').length; row++) {
                var rawItem = $('#grnTable tbody tr:eq(' + row + ') td:eq(1)').text();
                var Uprice = $('#grnTable tbody tr:eq(' + row + ') td:eq(2)').text()


                if ($("#rawItemList").val() === rawItem && $("#unitPrice").val() === Uprice) {
                    var currentQty = $('#grnTable tbody tr:eq(' + row + ') td:eq(3)').text();
                    var newQty = (+currentQty) + (+qty);
                    var newSubTotal = uprice * newQty;

                    $('#grnTable tbody tr:eq(' + row + ') td:eq(3)').text(newQty);
                    $('#grnTable tbody tr:eq(' + row + ') td:eq(4)').text(newSubTotal);

                    setTotal();
                    x = false;
                }

            }

            if (x) {
                if ($("#grnTable tbody").length == 0) {
                    $("#grnTable").append("<tbody></tbody>");
                }

                // Append product to the table

                $("#grnTable tbody").append(
                        "<tr>" +
                        "<td>" + $("#supplierList").val() + "</td>" +
                        "<td>" + $("#rawItemList").val() + "</td>" +
                        "<td>" + $("#unitPrice").val() + "</td>" +
                        "<td>" + $("#quantity").val() + "</td>" +
                        "<td>" + subTotal + "</td>" +
                        "</tr>");
                $('#grnTable').DataTable();
            }

            setTotal();



            document.getElementById("supplierList").disabled = true;
        }
    }
    });

    

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
    
    if($("#grnTable tr").length=="2"){
        
        new PNotify({
                        title: '',
                        text: 'add items',
                        type: 'error',
                        styling: 'bootstrap3'
                    });
        
    }else{
        
    var supplier = $("#supplierList").val();
    var total = $("#total").text();
    var param = "suplier=" + supplier + "&total=" + total;
    var req = new XMLHttpRequest();
   


    req.onreadystatechange = function () {
        if (req.readyState === 4 && req.status === 200) {

            var responce = req.responseText;
            if (responce === "ok") {

                $("#grnTable tr").each(function () {

                    var currentRow = $(this);

                    if (currentRow.find("td:eq(4)").text() == "") {

                    } else {
                        var rawItem = currentRow.find("td:eq(1)").text();
                        var uPrice = currentRow.find("td:eq(2)").text();
                        var qty = currentRow.find("td:eq(3)").text();
                        var subTotal = currentRow.find("td:eq(4)").text();
                        var req2 = new XMLHttpRequest();
                        
                        req2.onreadystatechange = function () {
                            if (req2.readyState === 4 && req2.status === 200) {
                               
                                var grnID = req2.responseText;
                                
                               
                                var param2 = "rawItemName=" + rawItem + "&grnId=" + grnID + "&qty=" + qty + "&subTotal=" + subTotal;
                                req2.open("POST", "addStock?" + param2, true);
                                req2.send();
                                resetTable();
                               
                                 $("#unitPrice").val('');
                                 $("#quantity").val('');
                         

                            }
                        };
                        var param2 = "rawItemName=" + rawItem + "&uPrice=" + uPrice + "&qty=" + qty + "&subTotal=" + subTotal;
                        req2.open("POST", "grnItemInsert?" + param2, true);
                        req2.send();
                        

                    }

                });

            }


        }

    };
    req.open("POST", "grnInsert?" + param, true);
    req.send();

                 new PNotify({
                        title: '',
                        text: 'grn added',
                        type: 'success',
                        styling: 'bootstrap3'
                    });
                            



}
}
function resetTable() {
    document.getElementById("supplierList").disabled = false;
    $("#total").text('00.00');
    var table = $('#grnTable').DataTable();

    table
            .clear()
            .draw();

    $("#grnTable").DataTable();
    $(".dataTables_empty").empty();

}
function grnItems(id) {

    window.location.href = "grnItems.jsp?id=" + id;
}

//function brandInsert() {



//
//    var name = document.getElementById("name").value;
//    var description = document.getElementById("description").value;
//
//
//    var param = "name=" + name + "&description=" + description;
//
//
//    if (name !== "" && description !== "") {
//        var req = new XMLHttpRequest();
//        req.onreadystatechange = function () {
//            if (req.readyState === 4 && req.status === 200) {
//
//
//                var resp = req.responseText;
//                document.getElementById("tbl1").innerHTML = resp;
//                $('#datatablex').DataTable();
//
//            }
//
//        };
//        req.open("POST", "brandInsert?" + param, true);
//        req.send();
//        new PNotify({
//            title: 'Added',
//            text: 'Brand added successfully',
//            type: 'success',
//            styling: 'bootstrap3'
//        });
//
//        document.getElementById("name").value = null;
//        document.getElementById("description").value = null;
//
//    } else {
//        new PNotify({
//            title: 'Check',
//            text: 'please fill all fields',
//            styling: 'bootstrap3'
//        });
//
//    }
//
//    loadBrandsList();
//
//
//}
