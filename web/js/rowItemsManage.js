/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function loadBrands() {


    var req = new XMLHttpRequest();
    req.onreadystatechange = function () {
        if (req.readyState === 4 && req.status === 200) {
            var resp = req.responseText;
            document.getElementById("tbl1").innerHTML = resp;
            $('#datatablex').DataTable();
        }
    };

    req.open("GET", "searchBrand", true);
    req.send();
}
function loadBrandsList() {


    var req = new XMLHttpRequest();
    req.onreadystatechange = function () {
        if (req.readyState === 4 && req.status === 200) {
            var resp = req.responseText;
            document.getElementById("brands").innerHTML = resp;
            $('#datatablex').DataTable();
        }
    };

    req.open("GET", "brandListLoad", true);
    req.send();
}
function loadRowItems() {


    var req = new XMLHttpRequest();
    req.onreadystatechange = function () {
        if (req.readyState === 4 && req.status === 200) {
            var resp = req.responseText;
            document.getElementById("tbl2").innerHTML = resp;
            $('#datatabley').DataTable();
        }
    };

    req.open("GET", "searchRowItems", true);
    req.send();
}

//function  isExists() {
// 
//    var param="name="+$("#name").val();
//
//    var req = new XMLHttpRequest();
//            req.onreadystatechange = function () {
//                if (req.readyState === 4 && req.status === 200) {
//                    var resp = req.responseText;
//                    alert(resp);
//                    
//                    if(resp=="true"){
//                        alert("wada");
//                     return true;   
//                    }else{
//                        return false;
//                    }
//                
//                    
//                }
//
//            };
//            req.open("GET", "brandExists?" + param, true);
//            req.send();
//    
//    
//    
//}


function brandInsert() {

    var name = document.getElementById("name").value;
    var param = "name=" + name;

    if (name !== "") {
        var req = new XMLHttpRequest();
        req.onreadystatechange = function () {
            if (req.readyState === 4 && req.status === 200) {
                var resp = req.responseText;


                if (resp == "true") {

                    new PNotify({
                        title: 'Error',
                        text: 'this name alredy exists',
                        type: 'error',
                        styling: 'bootstrap3'
                    });

                } else if (resp == "false") {

                    var req2 = new XMLHttpRequest();
                    req2.onreadystatechange = function () {
                        if (req2.readyState === 4 && req2.status === 200) {


                            var resp = req2.responseText;
                            document.getElementById("tbl1").innerHTML = resp;
                            $('#datatablex').DataTable();

                        }

                    };
                    req2.open("POST", "brandInsert?" + param, true);
                    req2.send();
                    new PNotify({
                        title: 'Added',
                        text: 'Brand added successfully',
                        type: 'success',
                        styling: 'bootstrap3'
                    });

                    document.getElementById("name").value = null;
                    document.getElementById("description").value = null;



                }


            }

        };
        req.open("GET", "brandExists?" + param, true);
        req.send();



    } else {
        new PNotify({
            title: 'Check',
            text: 'please fill all fields',
            styling: 'bootstrap3'
        });

    }

    loadBrandsList();


}




function rowItemsInsert() {



    var name = document.getElementById("name2").value;

    var selectBrand = document.getElementById("selectBrand").value;
   
    var discription = document.getElementById("description2").value;




    var param = "name=" + name + "&brand=" + selectBrand +"&description="+discription;




    if (name !== "" && selectBrand !== "") {

        var req = new XMLHttpRequest();
        req.onreadystatechange = function () {
            if (req.readyState === 4 && req.status === 200) {
                var resp = req.responseText;


                if (resp == "true") {

                    new PNotify({
                        title: 'Error',
                        text: 'this name alredy exists',
                        type: 'error',
                        styling: 'bootstrap3'
                    });

                } else if (resp == "false") {

                    var req2 = new XMLHttpRequest();
                    req2.onreadystatechange = function () {
                        if (req.readyState === 4 && req2.status === 200) {


                            var resp = req2.responseText;
                            document.getElementById("tbl2").innerHTML = resp;
                            $('#datatabley').DataTable();
                            new PNotify({
                                title: 'Added',
                                text: 'Item added successfully',
                                type: 'success',
                                styling: 'bootstrap3'
                            });

                        }

                    };
                    req2.open("POST", "rowItemInsert?" + param, true);
                    req2.send();


                    document.getElementById("name2").value = null;
                   document.getElementById("description2").value = null;


                }


            }

        };
        req.open("GET", "rawItemExists?" + param, true);
        req.send();




    } else {
        new PNotify({
            title: 'Check',
            text: 'please fill all fields',
            styling: 'bootstrap3'
        });

    }

    loadRowItems();


}

function brandDelete() {

    var table = document.getElementById('datatablex');


    for (var i = 1; i < table.rows.length; i++)
    {
        table.rows[i].onclick = function ()
        {
            rIndex = this.rowIndex;
            var id = this.cells[0].innerHTML;


            new PNotify({
                title: 'Deleted',
                text: 'Item delete successfully',
                type: 'success',
                styling: 'bootstrap3'
            });
            var param = "id=" + id;

            var req = new XMLHttpRequest();
            req.onreadystatechange = function () {
                if (req.readyState === 4 && req.status === 200) {

                    var resp = req.responseText;

                    document.getElementById("tbl1").innerHTML = resp;
                    $('#datatablex').DataTable();

                }
            };
            req.open("GET", "brandDelete?" + param, true);
            req.send();

            //  document.getElementById("datatablex").deleteRow(rIndex);

        };
    }


    loadBrandsList();

}
function rowItemDelete() {

    var table = document.getElementById('datatabley');


    for (var i = 1; i < table.rows.length; i++)
    {
        table.rows[i].onclick = function ()
        {
            rIndex = this.rowIndex;
            var id = this.cells[0].innerHTML;


            new PNotify({
                title: 'Deleted',
                text: 'Raw Item delete successfully',
                type: 'success',
                styling: 'bootstrap3'
            });
            var param = "id=" + id;

            var req = new XMLHttpRequest();
            req.onreadystatechange = function () {
                if (req.readyState === 4 && req.status === 200) {

                    var resp = req.responseText;

                    document.getElementById("tbl2").innerHTML = resp;
                    $('#datatabley').DataTable();

                }
            };
            req.open("GET", "rowItemDelete?" + param, true);
            req.send();

            //  document.getElementById("datatablex").deleteRow(rIndex);

        };
    }




}

function brandUpdate() {



    var table = document.getElementById('datatablex');


    for (var i = 1; i < table.rows.length; i++)
    {
        table.rows[i].onclick = function ()
        {
            rIndex = this.rowIndex;
            var id = this.cells[0].innerHTML;
            var name = this.cells[1].innerHTML;
            var param = "id=" + id + "&name=" + name;



            if (name !== "") {
                var req = new XMLHttpRequest();
                req.onreadystatechange = function () {
                    if (req.readyState === 4 && req.status === 200) {
                        var resp = req.responseText;


                        if (resp == "true") {

//                    new PNotify({
//                        title: 'Error',
//                        text: 'this name alredy exists',
//                        type: 'error',
//                        styling: 'bootstrap3'
//                    });

                        } else if (resp == "false") {


                            var req2 = new XMLHttpRequest();
                            req2.onreadystatechange = function () {

                                if (req2.readyState === 4 && req.status === 200) {



                                    var resp = req2.responseText;
                                    document.getElementById("tbl1").innerHTML = resp;
                                    $('#datatablex').DataTable();
                                    new PNotify({
                                        title: 'Updated',
                                        text: 'brand updated successfully',
                                        type: 'success',
                                        styling: 'bootstrap3'
                                    });

                                }
                            };
                            req2.open("POST", "brandUpdate?" + param, true);
                            req2.send();



                        }


                    }

                };
                req.open("GET", "brandExists?" + param, true);
                req.send();



            } else {
                new PNotify({
                    title: 'Check',
                    text: ' fill name field',
                    styling: 'bootstrap3'
                });

            }








        };
    }



    loadBrandsList();



}
function rowItemUpdatePage(id) {
    window.location.href = "rawItemUpdate.jsp?id=" + id;
}
function rowItemUpdate(id) {



 var name = document.getElementById("name2").value;

    var selectBrand = document.getElementById("selectBrand").value;
   
    var discription = document.getElementById("description2").value;
    
    

    var param = "name=" + name + "&brand=" + selectBrand +"&description="+discription+ "&id="+id;




    if (name !== "" && selectBrand !== "") {

        var req = new XMLHttpRequest();
        req.onreadystatechange = function () {
            if (req.readyState === 4 && req.status === 200) {
                var resp = req.responseText;


                if (resp == "true") {

                    new PNotify({
                        title: 'Error',
                        text: 'this name alredy exists',
                        type: 'error',
                        styling: 'bootstrap3'
                    });

                } else if (resp == "false") {

                    var req2 = new XMLHttpRequest();
                    req2.onreadystatechange = function () {
                        if (req.readyState === 4 && req2.status === 200) {


                            var resp = req2.responseText;
                           
                            
                            window.location.href = "rowItemsManage.jsp"; 
                            
                           
                            
                            }
                            


                    };
                        }
                    req2.open("POST", "rowItemUpdate?" + param, true);
                    req2.send();


                    document.getElementById("name2").value = null;
                   document.getElementById("description2").value = null;


                }


        };
        req.open("GET", "rawItemExists?" + param, true);
        req.send();
            





    } else {
        new PNotify({
            title: 'Check',
            text: 'please fill all fields',
            styling: 'bootstrap3'
        });

    }

}