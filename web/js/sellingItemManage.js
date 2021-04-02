/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function load() {


    var req = new XMLHttpRequest();
    req.onreadystatechange = function () {
        if (req.readyState === 4 && req.status === 200) {
            var resp = req.responseText;
            document.getElementById("tbl").innerHTML = resp;
            $('#datatablex').DataTable();
        }
    };

    req.open("GET", "search", true);
    req.send();
}

function insert() {




    var name = document.getElementById("name").value;
    var date = document.getElementById("date").value;
    var price = document.getElementById("price").value;

    var param = "name=" + name + "&date=" + date + "&price=" + price;
 
    
    if(name!==""&&date!==""&&price!==""){
        var req = new XMLHttpRequest();
    req.onreadystatechange = function () {
        if (req.readyState === 4 && req.status === 200) {


            var resp = req.responseText;
            document.getElementById("tbl").innerHTML = resp;
            $('#datatablex').DataTable();

        }

    };
    req.open("POST", "insert?" + param, true);
    req.send();
    new PNotify({
        title: 'Added',
        text: 'Item added successfully',
        type: 'success',
        styling: 'bootstrap3'
    });

    document.getElementById("name").value = null;
    document.getElementById("date").value = null;
    document.getElementById("price").value = null;

}else{
    new PNotify({
                                  title: 'Check',
                                  text: 'please fill all fields',
                                  styling: 'bootstrap3'
                              });
    
}

    

    
}

function Delete() {

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

                    document.getElementById("tbl").innerHTML = resp;
                    $('#datatablex').DataTable();

                }
            };
            req.open("GET", "delete?" + param, true);
            req.send();

            //  document.getElementById("datatablex").deleteRow(rIndex);

        };
    }




}
function update() {

    new PNotify({
        title: 'Updated',
        text: 'Item updated successfully',
        type: 'success',
        styling: 'bootstrap3'
    });


    var table = document.getElementById('datatablex');


    for (var i = 1; i < table.rows.length; i++)
    {
        table.rows[i].onclick = function ()
        {
            rIndex = this.rowIndex;
            var id = this.cells[0].innerHTML;
            var name = this.cells[1].innerHTML;
            var date = this.cells[2].innerHTML;
            var price = this.cells[3].innerHTML;

            var param = "id=" + id + "&name=" + name + "&date=" + date + "&price=" + price;

            var req = new XMLHttpRequest();
            req.onreadystatechange = function () {

                if (req.readyState === 4 && req.status === 200) {

                    massage();

                    var resp = req.responseText;
                    document.getElementById("tbl").innerHTML = resp;
                    $('#datatablex').DataTable();
                }
            };
            req.open("POST", "update?" + param, true);
            req.send();


        };
    }





}
