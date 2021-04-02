function loadSuppliers() {


    var req = new XMLHttpRequest();
    req.onreadystatechange = function () {
        if (req.readyState === 4 && req.status === 200) {
            var resp = req.responseText;
            document.getElementById("tbal5").innerHTML = resp;
            $('#suppTable').DataTable();
        }
    };

    req.open("GET", "loadSupp", true);
    req.send();
}

function Insert() {
    
    var name = document.getElementById("name").value;
    var cont = document.getElementById("contact").value;
    var param = "name=" + name +"&cont="+cont;

    if (name == ""||cont =="") {
       
        new PNotify({
            title: 'Check',
            text: 'please fill all fields',
            styling: 'bootstrap3'
        });



    } else {
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


                              loadSuppliers();

                        }

                    };
                    req2.open("POST", "suppInsert?" + param, true);
                    req2.send();
                    new PNotify({
                        title: 'Added',
                        text: 'Supplier added successfully',
                        type: 'success',
                        styling: 'bootstrap3'
                    });

                    document.getElementById("name").value = null;
                    document.getElementById("contact").value = null;



                }


            }

        };
        req.open("GET", "suppExists?" + param, true);
        req.send();
    }

 


}
