function register_machine() {
    document.getElementById("reg-btn").disabled = true;
    document.getElementById("rst-btn").disabled = true;
    var machine_name = document.getElementById("preinter_name").value;
    var request = new XMLHttpRequest();

    request.onreadystatechange = function () {

        if (request.readyState === 4) {
            if (request.status === 200) {
                document.getElementById("reg-btn").disabled = false;
                document.getElementById("rst-btn").disabled = false;
                var responce = request.responseText;


                switch (responce) {
                    case "ok":
                        new PNotify({
                            title: 'Registration  Success',
                            text: 'The new machince registred',
                            type: 'success',
                            styling: 'bootstrap3'
                        });


                        location.reload();



                        document.getElementById("preinter_name").value = "";
                        break;
                    case "null":
                        new PNotify({
                            title: 'Oh No!',
                            text: 'Machine name can not be empty',
                            type: 'error',
                            styling: 'bootstrap3'
                        });


                        break;

                    case "invalid":
                        new PNotify({
                            title: 'Oh No!',
                            text: 'Something terrible happened. Contact the devalopers',
                            type: 'error',
                            styling: 'bootstrap3'
                        });

                        break;
                    case "in_sys":
                        new PNotify({
                            title: 'Oh No!',
                            text: 'Machine is already registered',
                            type: 'error',
                            styling: 'bootstrap3'
                        });

                        break;
                }


            }
        }
    };

    if (machine_name !== "") {
        request.open("GET", "reg_machine?name=" + machine_name, true);
        request.send();
    } else {
        new PNotify({
            title: 'Oh No!',
            text: 'Machine name can not be empty',
            type: 'error',
            styling: 'bootstrap3'
        });
        document.getElementById("reg-btn").disabled = false;
        document.getElementById("rst-btn").disabled = false;

    }

}
function remove_machine(id) {
    var request = new XMLHttpRequest();

    document.getElementById("delete_btn").disabled = true;
    document.getElementById("remove_btn").disabled = true;

    request.onreadystatechange = function () {
        if (request.readyState === 4) {
            if (request.status === 200) {
                var responce = request.responseText;
                switch (responce) {
                    case "ok":
                        location.reload();
                        break;
                    case "no_res":
                        new PNotify({
                            title: 'Oh No!',
                            text: 'No result detected to delete',
                            type: 'error',
                            styling: 'bootstrap3'
                        });
                        break;
                    case "invalid":
                        new PNotify({
                            title: 'Oh No!',
                            text: 'Something went wrong contact the devalopers',
                            type: 'error',
                            styling: 'bootstrap3'
                        });
                        break;
                    case "id":
                        new PNotify({
                            title: 'Oh No!',
                            text: 'Invalid id detected',
                            type: 'error',
                            styling: 'bootstrap3'
                        });
                        break;
                }
                document.getElementById("delete_btn").disabled = false;
                document.getElementById("remove_btn").disabled = false;
            }
        }
    };
    request.open("GET", "Remove_machine?id=" + id, true);
    request.send();

}

function edit_machine(val, id) {
    document.getElementById("delete_btn").disabled = true;
    document.getElementById("remove_btn").disabled = true;

    document.getElementById("rename_name").value = "";
    document.getElementById("rename_id").value = "";
    document.getElementById("rename_name").value = val;
    document.getElementById("rename_id").value = id;

    document.getElementById("delete_btn").disabled = false;
    document.getElementById("remove_btn").disabled = false;

}
function rename_machine() {
    var machine_name = document.getElementById("rename_name").value;
    var machine_id = document.getElementById("rename_id").value;

    document.getElementById("reset-btn").disabled = true;
    document.getElementById("rename-btn").disabled = true;


    var request = new XMLHttpRequest();
    request.onreadystatechange = function () {
        if (request.readyState === 4) {
            if (request.status === 200) {
                var responce = request.responseText;
                switch (responce) {
                    case "ok":
                        location.reload();
                        break
                    case "id":
                        new PNotify({
                            title: 'Oh No!',
                            text: 'Invalid Machine Number',
                            type: 'error',
                            styling: 'bootstrap3'
                        });
                        break;
                    case "null":
                        new PNotify({
                            title: 'Oh No!',
                            text: 'Machine Name Cannot be empty',
                            type: 'error',
                            styling: 'bootstrap3'
                        });
                        break;
                    case "no":
                        new PNotify({
                            title: 'Oh No!',
                            text: 'No result detected for the id',
                            type: 'error',
                            styling: 'bootstrap3'
                        });
                        break;
                    case "invalid":
                        new PNotify({
                            title: 'Oh No!',
                            text: 'Something went wrong contact the devalopers',
                            type: 'error',
                            styling: 'bootstrap3'
                        });
                        break;
                }
                document.getElementById("reset-btn").disabled = false;
                document.getElementById("rename-btn").disabled = false;
            }
        }
    };
    if (machine_id !== "") {
        if (machine_name !== "") {
            request.open("GET", "Rename_name?name=" + machine_name + "&id=" + machine_id, true);
            request.send();
        } else {
            new PNotify({
                title: 'Oh No!',
                text: 'Machine Name Cannot be empty',
                type: 'error',
                styling: 'bootstrap3'
            });
            document.getElementById("reset-btn").disabled = false;
            document.getElementById("rename-btn").disabled = false;
        }
    } else {
        new PNotify({
            title: 'Oh No!',
            text: 'Invalid Machine Number',
            type: 'error',
            styling: 'bootstrap3'
        });
        document.getElementById("reset-btn").disabled = false;
        document.getElementById("rename-btn").disabled = false;
    }




}








function set_order_desctiption2() {
    var order_id = document.getElementById("select_order2").value;
    var request = new XMLHttpRequest();
    if (order_id !== "") {
        request.onreadystatechange = function () {
            if (request.readyState === 4) {
                if (request.status === 200) {
                    // need to validate
                    var responce = request.responseText;
                    var responce_object = JSON.parse(responce);
                    document.getElementById("order_description2").innerHTML = responce_object.description;
                    document.getElementById("myModalLabel2").innerHTML = "The Order Details Of " + responce_object.customer_name;
                    document.getElementById("order-cust-name2").value = responce_object.customer_name;
                    document.getElementById("order-cust-mobile2").value = responce_object.customer_mobile;
                    document.getElementById("un-id2").value = responce_object.id;
                    document.getElementById("reg-date2").value = responce_object.date;
                    document.getElementById("reg-tot2").value = responce_object.total;
                    document.getElementById("reg-dis2").value = responce_object.discount;
                    document.getElementById("reg-sub2").value = responce_object.sub;
                    document.getElementById("reg-due2").value = responce_object.due;
                    document.getElementById("reg-del-date2").value = responce_object.del_date;
                    document.getElementById("reg-status2").value = responce_object.order_status;
                }
            }
        };
        request.open("POST", "Get_Order_Description", true);
        request.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        request.send("id=" + order_id);
    } else {
        new PNotify({
            title: 'Oh No!',
            text: 'Invalid order id',
            type: 'error',
            styling: 'bootstrap3'
        });
    }
    if (order_id === "null") {
        document.getElementById("order_description2").innerHTML = "";
        document.getElementById("myModalLabel2").innerHTML = "";
        document.getElementById("order-cust-name2").value = "";
        document.getElementById("order-cust-mobile2").value = "";
        document.getElementById("un-id2").value = "";
        document.getElementById("reg-date2").value = "";
        document.getElementById("reg-tot2").value = "";
        document.getElementById("reg-dis2").value = "";
        document.getElementById("reg-sub2").value = "";
        document.getElementById("reg-due2").value = "";
        document.getElementById("reg-del-date2").value = "";
        document.getElementById("reg-status2").value = "";
    }

}















function set_order_desctiption() {
    var order_id = document.getElementById("select_order").value;
    var request = new XMLHttpRequest();
    if (order_id !== "") {
        request.onreadystatechange = function () {
            if (request.readyState === 4) {
                if (request.status === 200) {
                    // need to validate
                    var responce = request.responseText;
                    var responce_object = JSON.parse(responce);
                    document.getElementById("order_description").innerHTML = responce_object.description;
                    document.getElementById("myModalLabel").innerHTML = "The Order Details Of " + responce_object.customer_name;
                    document.getElementById("order-cust-name").value = responce_object.customer_name;
                    document.getElementById("order-cust-mobile").value = responce_object.customer_mobile;
                    document.getElementById("un-id").value = responce_object.id;
                    document.getElementById("reg-date").value = responce_object.date;
                    document.getElementById("reg-tot").value = responce_object.total;
                    document.getElementById("reg-dis").value = responce_object.discount;
                    document.getElementById("reg-sub").value = responce_object.sub;
                    document.getElementById("reg-due").value = responce_object.due;
                    document.getElementById("reg-del-date").value = responce_object.del_date;
                    document.getElementById("reg-status").value = responce_object.order_status;
                }
            }
        };
        request.open("POST", "Get_Order_Description", true);
        request.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        request.send("id=" + order_id);
    } else {
        new PNotify({
            title: 'Oh No!',
            text: 'Invalid order id',
            type: 'error',
            styling: 'bootstrap3'
        });
    }
    if (order_id === "null") {
        document.getElementById("order_description").innerHTML = "";
        document.getElementById("myModalLabel").innerHTML = "";
        document.getElementById("order-cust-name").value = "";
        document.getElementById("order-cust-mobile").value = "";
        document.getElementById("un-id").value = "";
        document.getElementById("reg-date").value = "";
        document.getElementById("reg-tot").value = "";
        document.getElementById("reg-dis").value = "";
        document.getElementById("reg-sub").value = "";
        document.getElementById("reg-due").value = "";
        document.getElementById("reg-del-date").value = "";
        document.getElementById("reg-status").value = "";
    }

}
function register_plate() {
    document.getElementById("reg-btn").disabled = true;
    document.getElementById("rst-btn").disabled = true;

    var plate_rack = document.getElementById("plate_rack").value;
    var plate_date = document.getElementById("plate_date").value;
    var plate_count = document.getElementById("plate_count").value;
    var customer_list = document.getElementById("customer_mobile").value;
    var description = document.getElementById("des").value;

    var request = new XMLHttpRequest();
    request.onreadystatechange = function () {
        if (request.readyState === 4) {
            if (request.status === 200) {
                var responce = request.responseText;
                switch (responce) {
                    case "ok":
                        location.reload();
                        break;
                    case "no_customer":
                        new PNotify({
                            title: 'Oh No!',
                            text: 'Plate customer is not registred',
                            type: 'error',
                            styling: 'bootstrap3'
                        });

                        document.getElementById("reg-btn").disabled = false;
                        document.getElementById("rst-btn").disabled = false;
                        break;
                    case "null_description":
                        new PNotify({
                            title: 'Oh No!',
                            text: 'Plate description can not be empty',
                            type: 'error',
                            styling: 'bootstrap3'
                        });

                        document.getElementById("reg-btn").disabled = false;
                        document.getElementById("rst-btn").disabled = false;
                        break;
                    case "null_customer":
                        new PNotify({
                            title: 'Oh No!',
                            text: 'Plate customer can not be empty',
                            type: 'error',
                            styling: 'bootstrap3'
                        });

                        document.getElementById("reg-btn").disabled = false;
                        document.getElementById("rst-btn").disabled = false;
                        break;
                    case "invalid_cout":
                        new PNotify({
                            title: 'Oh No!',
                            text: 'Count only can be numbers',
                            type: 'error',
                            styling: 'bootstrap3'
                        });

                        document.getElementById("reg-btn").disabled = false;
                        document.getElementById("rst-btn").disabled = false;
                        break;
                    case "null_count":
                        new PNotify({
                            title: 'Oh No!',
                            text: 'Plate count can not be empty',
                            type: 'error',
                            styling: 'bootstrap3'
                        });

                        document.getElementById("reg-btn").disabled = false;
                        document.getElementById("rst-btn").disabled = false;
                        break;
                    case "invalid_date":
                        new PNotify({
                            title: 'Oh No!',
                            text: 'Invalid date detected',
                            type: 'error',
                            styling: 'bootstrap3'
                        });

                        document.getElementById("reg-btn").disabled = false;
                        document.getElementById("rst-btn").disabled = false;
                        break;
                    case "null_date":
                        new PNotify({
                            title: 'Oh No!',
                            text: 'Plate date can not be empty',
                            type: 'error',
                            styling: 'bootstrap3'
                        });

                        document.getElementById("reg-btn").disabled = false;
                        document.getElementById("rst-btn").disabled = false;
                        break;
                    case "null_rack":
                        new PNotify({
                            title: 'Oh No!',
                            text: 'Plate rack number can not be empty',
                            type: 'error',
                            styling: 'bootstrap3'
                        });

                        document.getElementById("reg-btn").disabled = false;
                        document.getElementById("rst-btn").disabled = false;
                        break;
                    case "invalid":
                        new PNotify({
                            title: 'Oh No!',
                            text: 'Something is wrong contact the devalopers',
                            type: 'error',
                            styling: 'bootstrap3'
                        });

                        document.getElementById("reg-btn").disabled = false;
                        document.getElementById("rst-btn").disabled = false;
                        break;


                }

            }
        }
    };

    if (plate_rack !== "") {
        if (plate_date !== "") {
            if (plate_count !== "") {
                if (customer_list !== "") {
                    if (description !== "") {

                        request.open("POST", "Register_plate", true);
                        request.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
                        request.send("rack=" + plate_rack + "&date=" + plate_date + "&count=" + plate_count + "&customer=" + customer_list + "&description=" + description);

                    } else {
                        new PNotify({
                            title: 'Oh No!',
                            text: 'Plate description can not be empty',
                            type: 'error',
                            styling: 'bootstrap3'
                        });

                        document.getElementById("reg-btn").disabled = false;
                        document.getElementById("rst-btn").disabled = false;
                    }
                } else {
                    new PNotify({
                        title: 'Oh No!',
                        text: 'Plate customer can not be empty',
                        type: 'error',
                        styling: 'bootstrap3'
                    });

                    document.getElementById("reg-btn").disabled = false;
                    document.getElementById("rst-btn").disabled = false;
                }
            } else {
                new PNotify({
                    title: 'Oh No!',
                    text: 'Plate count can not be empty',
                    type: 'error',
                    styling: 'bootstrap3'
                });

                document.getElementById("reg-btn").disabled = false;
                document.getElementById("rst-btn").disabled = false;
            }
        } else {
            new PNotify({
                title: 'Oh No!',
                text: 'Plate date can not be empty',
                type: 'error',
                styling: 'bootstrap3'
            });

            document.getElementById("reg-btn").disabled = false;
            document.getElementById("rst-btn").disabled = false;
        }
    } else {
        new PNotify({
            title: 'Oh No!',
            text: 'Plate rack number can not be empty',
            type: 'error',
            styling: 'bootstrap3'
        });

        document.getElementById("reg-btn").disabled = false;
        document.getElementById("rst-btn").disabled = false;
    }
}
function assign_customer() {
    var mobile = document.getElementById("cust_lst").value;
    document.getElementById("customer_mobile").value = mobile;

}

function set_customer(rack, date, count, id, mobile, name, description) {
    document.getElementById("plate_rack2").value = rack;
    document.getElementById("plate_date2").value = date;
    document.getElementById("plate_count2").value = count;

    document.getElementById("des2").value = description;
    document.getElementById("customer_mobile2").value = mobile;
    document.getElementById("customer_name2").value = name;
    document.getElementById("plate_id").value = id;
    document.getElementById("palte_no").value = id;
}

function update_plates() {
    document.getElementById("reg-btn2").disabled = true;
    document.getElementById("rst-btn2").disabled = true;
    document.getElementById("delete-btn").disabled = true;

    var plate_rack = document.getElementById("plate_rack2").value;
    var plate_date = document.getElementById("plate_date2").value;
    var plate_count = document.getElementById("plate_count2").value;
    var customer_list = document.getElementById("customer_mobile2").value;
    var description = document.getElementById("des2").value;
    var id = document.getElementById("plate_id").value;

    var request = new XMLHttpRequest();
    request.onreadystatechange = function () {
        if (request.readyState === 4) {
            if (request.status === 200) {
                var responce = request.responseText;
                switch (responce) {
                    case "ok":
                        location.reload();
                        break;
                    case "no_customer":
                        new PNotify({
                            title: 'Oh No!',
                            text: 'Plate customer is not registred',
                            type: 'error',
                            styling: 'bootstrap3'
                        });

                        document.getElementById("reg-btn2").disabled = false;
                        document.getElementById("rst-btn2").disabled = false;
                        document.getElementById("delete-btn").disabled = false;
                        break;
                    case "null_description":
                        new PNotify({
                            title: 'Oh No!',
                            text: 'Plate description can not be empty',
                            type: 'error',
                            styling: 'bootstrap3'
                        });

                        document.getElementById("reg-btn2").disabled = false;
                        document.getElementById("rst-btn2").disabled = false;
                        document.getElementById("delete-btn").disabled = false;
                        break;
                    case "null_customer":
                        new PNotify({
                            title: 'Oh No!',
                            text: 'Plate customer can not be empty',
                            type: 'error',
                            styling: 'bootstrap3'
                        });

                        document.getElementById("reg-btn2").disabled = false;
                        document.getElementById("rst-btn2").disabled = false;
                        document.getElementById("delete-btn").disabled = false;
                        break;
                    case "invalid_cout":
                        new PNotify({
                            title: 'Oh No!',
                            text: 'Count only can be numbers',
                            type: 'error',
                            styling: 'bootstrap3'
                        });

                        document.getElementById("reg-btn2").disabled = false;
                        document.getElementById("rst-btn2").disabled = false;
                        document.getElementById("delete-btn").disabled = false;
                        break;
                    case "null_count":
                        new PNotify({
                            title: 'Oh No!',
                            text: 'Plate count can not be empty',
                            type: 'error',
                            styling: 'bootstrap3'
                        });

                        document.getElementById("reg-btn2").disabled = false;
                        document.getElementById("rst-btn2").disabled = false;
                        document.getElementById("delete-btn").disabled = false;
                        break;
                    case "invalid_date":
                        new PNotify({
                            title: 'Oh No!',
                            text: 'Invalid date detected',
                            type: 'error',
                            styling: 'bootstrap3'
                        });

                        document.getElementById("reg-btn2").disabled = false;
                        document.getElementById("rst-btn2").disabled = false;
                        document.getElementById("delete-btn").disabled = false;
                        break;
                    case "null_date":
                        new PNotify({
                            title: 'Oh No!',
                            text: 'Plate date can not be empty',
                            type: 'error',
                            styling: 'bootstrap3'
                        });

                        document.getElementById("reg-btn2").disabled = false;
                        document.getElementById("rst-btn2").disabled = false;
                        document.getElementById("delete-btn").disabled = false;
                        break;
                    case "null_rack":
                        new PNotify({
                            title: 'Oh No!',
                            text: 'Plate rack number can not be empty',
                            type: 'error',
                            styling: 'bootstrap3'
                        });

                        document.getElementById("reg-btn2").disabled = false;
                        document.getElementById("rst-btn2").disabled = false;
                        document.getElementById("delete-btn").disabled = false;
                        break;
                    case "invalid":
                        new PNotify({
                            title: 'Oh No!',
                            text: 'Something is wrong contact the devalopers',
                            type: 'error',
                            styling: 'bootstrap3'
                        });

                        document.getElementById("reg-btn2").disabled = false;
                        document.getElementById("rst-btn2").disabled = false;
                        document.getElementById("delete-btn").disabled = false;
                        break;
                    case "invalid_id":
                        new PNotify({
                            title: 'Oh No!',
                            text: 'Invalid plate id',
                            type: 'error',
                            styling: 'bootstrap3'
                        });

                        document.getElementById("reg-btn2").disabled = false;
                        document.getElementById("rst-btn2").disabled = false;
                        document.getElementById("delete-btn").disabled = false;
                        break;
                    case "no_plate":
                        new PNotify({
                            title: 'Oh No!',
                            text: 'No plate detected',
                            type: 'error',
                            styling: 'bootstrap3'
                        });

                        document.getElementById("reg-btn2").disabled = false;
                        document.getElementById("rst-btn2").disabled = false;
                        document.getElementById("delete-btn").disabled = false;
                        break;


                }

            }
        }
    };

    if (plate_rack !== "") {
        if (plate_date !== "") {
            if (plate_count !== "") {
                if (customer_list !== "") {
                    if (description !== "") {

                        request.open("POST", "Update_plate", true);
                        request.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
                        request.send("rack=" + plate_rack + "&date=" + plate_date + "&count=" + plate_count + "&customer=" + customer_list + "&description=" + description + "&id=" + id);

                    } else {
                        new PNotify({
                            title: 'Oh No!',
                            text: 'Plate description can not be empty',
                            type: 'error',
                            styling: 'bootstrap3'
                        });

                        document.getElementById("reg-btn2").disabled = false;
                        document.getElementById("rst-btn2").disabled = false;
                        document.getElementById("delete-btn").disabled = false;
                    }
                } else {
                    new PNotify({
                        title: 'Oh No!',
                        text: 'Plate customer can not be empty',
                        type: 'error',
                        styling: 'bootstrap3'
                    });

                    document.getElementById("reg-btn2").disabled = false;
                    document.getElementById("rst-btn2").disabled = false;
                    document.getElementById("delete-btn").disabled = false;
                }
            } else {
                new PNotify({
                    title: 'Oh No!',
                    text: 'Plate count can not be empty',
                    type: 'error',
                    styling: 'bootstrap3'
                });

                document.getElementById("reg-btn2").disabled = false;
                document.getElementById("rst-btn2").disabled = false;
                document.getElementById("delete-btn").disabled = false;
            }
        } else {
            new PNotify({
                title: 'Oh No!',
                text: 'Plate date can not be empty',
                type: 'error',
                styling: 'bootstrap3'
            });

            document.getElementById("reg-btn2").disabled = false;
            document.getElementById("rst-btn2").disabled = false;
            document.getElementById("delete-btn").disabled = false;
        }
    } else {
        new PNotify({
            title: 'Oh No!',
            text: 'Plate rack number can not be empty',
            type: 'error',
            styling: 'bootstrap3'
        });

        document.getElementById("reg-btn2").disabled = false;
        document.getElementById("rst-btn2").disabled = false;
        document.getElementById("delete-btn").disabled = false;
    }



}






var customers = {};
var mobiles = {};
function init_autocomplete() {

    var request = new XMLHttpRequest();
    request.onreadystatechange = function () {
        if (request.readyState === 4) {
            if (request.status === 200) {


                var responce = request.responseText;
                var customer_list = JSON.parse(responce);


                customer_list.forEach(function (val) {
                    var cust_id = val.id;
                    var cust_name = val.name;
                    var cust_c = val.user;

                    mobiles [cust_c] = cust_id;

                    customers[cust_id] = cust_name;
                });





                if (typeof ($.fn.autocomplete) === 'undefined') {

                    return;
                }



                var mobileArray = $.map(mobiles, function (value, key) {
                    return {
                        value: value,
                        data: key
                    };
                });

                // initialize autocomplete with custom appendTo
                $('#customer_mobile').autocomplete({
                    lookup: mobileArray
                }

                );

                if (typeof ($.fn.autocomplete) === 'undefined') {

                    return;
                }



                var mobileArray = $.map(mobiles, function (value, key) {
                    return {
                        value: value,
                        data: key
                    };
                });

                // initialize autocomplete with custom appendTo
                $('#customer_mobile2').autocomplete({
                    lookup: mobileArray
                }

                );




            }
        }
    };


    request.open("GET", "Suggections", true);
    request.send();





}
;

var plate_id;
var customer_mobile_number;
function setName(id, set) {

    if (document.getElementById(id) !== null) {
        var mobile = document.getElementById(id).value;
    }
    var name = customers[mobile];



    if (document.getElementById(set) !== null) {
        document.getElementById(set).value = name;




        if (document.getElementById(set).value === "undefined") {
            document.getElementById(set).value = "";
        }

    }

    if (document.getElementById("selected_val") !== null && document.getElementById(id) !== null) {

        var request = new XMLHttpRequest();
        request.onreadystatechange = function () {

            if (request.readyState === 4) {
                if (request.status === 200) {
                    var responce = request.responseText;
                    switch (responce) {
                        case "no_result":
                            break;
                        case "invalid":
                            break;
                        default:
                            var plate_list = JSON.parse(responce);


                            plate_list.forEach(function (val) {
                                var optag = document.createElement("OPTION");
                                document.getElementById("selected_val").appendChild(optag);

                                var value = val.plate_count + " Plates Of ID: " + val.plate_no + " Rack No: " + val.rack_no;
                                optag.value = val.description + "§" + val.plate_no;
                                optag.innerHTML = value;
                                plate_id = val.plate_no;



                            });


                            var reques2 = new XMLHttpRequest();

                            reques2.onreadystatechange = function () {
                                if (reques2.readyState === 4) {
                                    if (reques2.status === 200) {
                                        var responce = reques2.responseText;

                                        document.getElementById("orede_list").innerHTML = responce;
                                    }
                                }
                            };
                            reques2.open("POST", "Get_Orders", true);
                            reques2.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
                            reques2.send("id=" + mobile);



                    }
                }
            }
        };
        var mobile = document.getElementById(id).value;
        request.open("GET", "Get_Plates?number=" + mobile, true);
        request.send();

    }




}
function set_description() {


    document.getElementById("plate_deacription").value = document.getElementById("selected_val").value.split("§")[0];

}

function set_description2() {


    document.getElementById("plate_deacription2").value = document.getElementById("selected_val2").value.split("§")[0];

}


function remove_plate() {
    document.getElementById("reg-btn2").disabled = true;
    document.getElementById("rst-btn2").disabled = true;
    document.getElementById("delete-btn").disabled = true;

    var plate_id = document.getElementById("plate_id").value;
    var request = new XMLHttpRequest();

    request.onreadystatechange = function () {
        if (request.readyState === 4) {
            if (request.status === 200) {
                var responce = request.responseText;
                switch (responce) {
                    case "ok":
                        location.reload();
                        break;
                    case "active":
                        new PNotify({
                            title: 'Oh No!',
                            text: 'This plate is in use',
                            type: 'error',
                            styling: 'bootstrap3'
                        });
                        break;
                    case "no_res":
                        new PNotify({
                            title: 'Oh No!',
                            text: 'No such plate registered',
                            type: 'error',
                            styling: 'bootstrap3'
                        });
                        break;
                    case "invalid_id":
                        new PNotify({
                            title: 'Oh No!',
                            text: 'Invalid plate id',
                            type: 'error',
                            styling: 'bootstrap3'
                        });
                        break;
                    case "null":
                        new PNotify({
                            title: 'Oh No!',
                            text: 'Plate id can not be empty',
                            type: 'error',
                            styling: 'bootstrap3'
                        });
                        break;
                    case "invalid":
                        new PNotify({
                            title: 'Oh No!',
                            text: 'something went wrong please contact the devalopers',
                            type: 'error',
                            styling: 'bootstrap3'
                        });
                        break;



                }
                document.getElementById("reg-btn2").disabled = false;
                document.getElementById("rst-btn2").disabled = false;
                document.getElementById("delete-btn").disabled = false;
            }
        }
    };


    if (plate_id !== "") {
        request.open("GET", "Remove_plate?id=" + plate_id, true);
        request.send();
    } else {
        new PNotify({
            title: 'Oh No!',
            text: 'Plate id can not be empty',
            type: 'error',
            styling: 'bootstrap3'
        });
        document.getElementById("reg-btn2").disabled = false;
        document.getElementById("rst-btn2").disabled = false;
        document.getElementById("delete-btn").disabled = false;

    }

}



/**
 *  Ajax Autocomplete for jQuery, version 1.2.24
 *  (c) 2015 Tomas Kirda
 *
 *  Ajax Autocomplete for jQuery is freely distributable under the terms of an MIT-style license.
 *  For details, see the web site: https://github.com/devbridge/jQuery-Autocomplete
 */

/*jslint  browser: true, white: true, plusplus: true, vars: true */
/*global define, window, document, jQuery, exports, require */

// Expose plugin as an AMD module if AMD loader is present:
(function (factory) {
    'use strict';
    if (typeof define === 'function' && define.amd) {
        // AMD. Register as an anonymous module.
        define(['jquery'], factory);
    } else if (typeof exports === 'object' && typeof require === 'function') {
        // Browserify
        factory(require('jquery'));
    } else {
        // Browser globals
        factory(jQuery);
    }
}(function ($) {
    'use strict';

    var
            utils = (function () {
                return {
                    escapeRegExChars: function (value) {
                        return value.replace(/[\-\[\]\/\{\}\(\)\*\+\?\.\\\^\$\|]/g, "\\$&");
                    },
                    createNode: function (containerClass) {
                        var div = document.createElement('div');
                        div.className = containerClass;
                        div.style.position = 'absolute';
                        div.style.display = 'none';
                        return div;
                    }
                };
            }()),
            keys = {
                ESC: 27,
                TAB: 9,
                RETURN: 13,
                LEFT: 37,
                UP: 38,
                RIGHT: 39,
                DOWN: 40
            };

    function Autocomplete(el, options) {
        var noop = function () { },
                that = this,
                defaults = {
                    ajaxSettings: {},
                    autoSelectFirst: false,
                    appendTo: document.body,
                    serviceUrl: null,
                    lookup: null,
                    onSelect: null,
                    width: 'auto',
                    minChars: 1,
                    maxHeight: 300,
                    deferRequestBy: 0,
                    params: {},
                    formatResult: Autocomplete.formatResult,
                    delimiter: null,
                    zIndex: 9999,
                    type: 'GET',
                    noCache: false,
                    onSearchStart: noop,
                    onSearchComplete: noop,
                    onSearchError: noop,
                    preserveInput: false,
                    containerClass: 'autocomplete-suggestions',
                    tabDisabled: false,
                    dataType: 'text',
                    currentRequest: null,
                    triggerSelectOnValidInput: true,
                    preventBadQueries: true,
                    lookupFilter: function (suggestion, originalQuery, queryLowerCase) {
                        return suggestion.value.toLowerCase().indexOf(queryLowerCase) !== -1;
                    },
                    paramName: 'query',
                    transformResult: function (response) {
                        return typeof response === 'string' ? $.parseJSON(response) : response;
                    },
                    showNoSuggestionNotice: false,
                    noSuggestionNotice: 'No results',
                    orientation: 'bottom',
                    forceFixPosition: false
                };

        // Shared variables:
        that.element = el;
        that.el = $(el);
        that.suggestions = [];
        that.badQueries = [];
        that.selectedIndex = -1;
        that.currentValue = that.element.value;
        that.intervalId = 0;
        that.cachedResponse = {};
        that.onChangeInterval = null;
        that.onChange = null;
        that.isLocal = false;
        that.suggestionsContainer = null;
        that.noSuggestionsContainer = null;
        that.options = $.extend({}, defaults, options);
        that.classes = {
            selected: 'autocomplete-selected',
            suggestion: 'autocomplete-suggestion'
        };
        that.hint = null;
        that.hintValue = '';
        that.selection = null;

        // Initialize and set options:
        that.initialize();
        that.setOptions(options);
    }

    Autocomplete.utils = utils;

    $.Autocomplete = Autocomplete;

    Autocomplete.formatResult = function (suggestion, currentValue) {
        var pattern = '(' + utils.escapeRegExChars(currentValue) + ')';

        return suggestion.value
                .replace(new RegExp(pattern, 'gi'), '<strong>$1<\/strong>')
                .replace(/&/g, '&amp;')
                .replace(/</g, '&lt;')
                .replace(/>/g, '&gt;')
                .replace(/"/g, '&quot;')
                .replace(/&lt;(\/?strong)&gt;/g, '<$1>');
    };

    Autocomplete.prototype = {

        killerFn: null,

        initialize: function () {
            var that = this,
                    suggestionSelector = '.' + that.classes.suggestion,
                    selected = that.classes.selected,
                    options = that.options,
                    container;

            // Remove autocomplete attribute to prevent native suggestions:
            that.element.setAttribute('autocomplete', 'off');

            that.killerFn = function (e) {
                if ($(e.target).closest('.' + that.options.containerClass).length === 0) {
                    that.killSuggestions();
                    that.disableKillerFn();
                }
            };

            // html() deals with many types: htmlString or Element or Array or jQuery
            that.noSuggestionsContainer = $('<div class="autocomplete-no-suggestion"></div>')
                    .html(this.options.noSuggestionNotice).get(0);

            that.suggestionsContainer = Autocomplete.utils.createNode(options.containerClass);

            container = $(that.suggestionsContainer);

            container.appendTo(options.appendTo);

            // Only set width if it was provided:
            if (options.width !== 'auto') {
                container.width(options.width);
            }

            // Listen for mouse over event on suggestions list:
            container.on('mouseover.autocomplete', suggestionSelector, function () {
                that.activate($(this).data('index'));
            });

            // Deselect active element when mouse leaves suggestions container:
            container.on('mouseout.autocomplete', function () {
                that.selectedIndex = -1;
                container.children('.' + selected).removeClass(selected);
            });

            // Listen for click event on suggestions list:
            container.on('click.autocomplete', suggestionSelector, function () {
                that.select($(this).data('index'));
            });

            that.fixPositionCapture = function () {
                if (that.visible) {
                    that.fixPosition();
                }
            };

            $(window).on('resize.autocomplete', that.fixPositionCapture);

            that.el.on('keydown.autocomplete', function (e) {
                that.onKeyPress(e);
            });
            that.el.on('keyup.autocomplete', function (e) {
                that.onKeyUp(e);
            });
            that.el.on('blur.autocomplete', function () {
                that.onBlur();
            });
            that.el.on('focus.autocomplete', function () {
                that.onFocus();
            });
            that.el.on('change.autocomplete', function (e) {
                that.onKeyUp(e);
            });
            that.el.on('input.autocomplete', function (e) {
                that.onKeyUp(e);
            });
        },

        onFocus: function () {
            var that = this;
            that.fixPosition();
            if (that.options.minChars === 0 && that.el.val().length === 0) {
                that.onValueChange();
            }
        },

        onBlur: function () {
            this.enableKillerFn();
        },

        abortAjax: function () {
            var that = this;
            if (that.currentRequest) {
                that.currentRequest.abort();
                that.currentRequest = null;
            }
        },

        setOptions: function (suppliedOptions) {
            var that = this,
                    options = that.options;

            $.extend(options, suppliedOptions);

            that.isLocal = $.isArray(options.lookup);

            if (that.isLocal) {
                options.lookup = that.verifySuggestionsFormat(options.lookup);
            }

            options.orientation = that.validateOrientation(options.orientation, 'bottom');

            // Adjust height, width and z-index:
            $(that.suggestionsContainer).css({
                'max-height': options.maxHeight + 'px',
                'width': options.width + 'px',
                'z-index': options.zIndex
            });
        },

        clearCache: function () {
            this.cachedResponse = {};
            this.badQueries = [];
        },

        clear: function () {
            this.clearCache();
            this.currentValue = '';
            this.suggestions = [];
        },

        disable: function () {
            var that = this;
            that.disabled = true;
            clearInterval(that.onChangeInterval);
            that.abortAjax();
        },

        enable: function () {
            this.disabled = false;
        },

        fixPosition: function () {
            // Use only when container has already its content

            var that = this,
                    $container = $(that.suggestionsContainer),
                    containerParent = $container.parent().get(0);
            // Fix position automatically when appended to body.
            // In other cases force parameter must be given.
            if (containerParent !== document.body && !that.options.forceFixPosition) {
                return;
            }

            // Choose orientation
            var orientation = that.options.orientation,
                    containerHeight = $container.outerHeight(),
                    height = that.el.outerHeight(),
                    offset = that.el.offset(),
                    styles = {'top': offset.top, 'left': offset.left};

            if (orientation === 'auto') {
                var viewPortHeight = $(window).height(),
                        scrollTop = $(window).scrollTop(),
                        topOverflow = -scrollTop + offset.top - containerHeight,
                        bottomOverflow = scrollTop + viewPortHeight - (offset.top + height + containerHeight);

                orientation = (Math.max(topOverflow, bottomOverflow) === topOverflow) ? 'top' : 'bottom';
            }

            if (orientation === 'top') {
                styles.top += -containerHeight;
            } else {
                styles.top += height;
            }

            // If container is not positioned to body,
            // correct its position using offset parent offset
            if (containerParent !== document.body) {
                var opacity = $container.css('opacity'),
                        parentOffsetDiff;

                if (!that.visible) {
                    $container.css('opacity', 0).show();
                }

                parentOffsetDiff = $container.offsetParent().offset();
                styles.top -= parentOffsetDiff.top;
                styles.left -= parentOffsetDiff.left;

                if (!that.visible) {
                    $container.css('opacity', opacity).hide();
                }
            }

            // -2px to account for suggestions border.
            if (that.options.width === 'auto') {
                styles.width = (that.el.outerWidth() - 2) + 'px';
            }

            $container.css(styles);
        },

        enableKillerFn: function () {
            var that = this;
            $(document).on('click.autocomplete', that.killerFn);
        },

        disableKillerFn: function () {
            var that = this;
            $(document).off('click.autocomplete', that.killerFn);
        },

        killSuggestions: function () {
            var that = this;
            that.stopKillSuggestions();
            that.intervalId = window.setInterval(function () {
                if (that.visible) {
                    that.el.val(that.currentValue);
                    that.hide();
                }

                that.stopKillSuggestions();
            }, 50);
        },

        stopKillSuggestions: function () {
            window.clearInterval(this.intervalId);
        },

        isCursorAtEnd: function () {
            var that = this,
                    valLength = that.el.val().length,
                    selectionStart = that.element.selectionStart,
                    range;

            if (typeof selectionStart === 'number') {
                return selectionStart === valLength;
            }
            if (document.selection) {
                range = document.selection.createRange();
                range.moveStart('character', -valLength);
                return valLength === range.text.length;
            }
            return true;
        },

        onKeyPress: function (e) {
            var that = this;

            // If suggestions are hidden and user presses arrow down, display suggestions:
            if (!that.disabled && !that.visible && e.which === keys.DOWN && that.currentValue) {
                that.suggest();
                return;
            }

            if (that.disabled || !that.visible) {
                return;
            }

            switch (e.which) {
                case keys.ESC:
                    that.el.val(that.currentValue);
                    that.hide();
                    break;
                case keys.RIGHT:
                    if (that.hint && that.options.onHint && that.isCursorAtEnd()) {
                        that.selectHint();
                        break;
                    }
                    return;
                case keys.TAB:
                    if (that.hint && that.options.onHint) {
                        that.selectHint();
                        return;
                    }
                    if (that.selectedIndex === -1) {
                        that.hide();
                        return;
                    }
                    that.select(that.selectedIndex);
                    if (that.options.tabDisabled === false) {
                        return;
                    }
                    break;
                case keys.RETURN:
                    if (that.selectedIndex === -1) {
                        that.hide();
                        return;
                    }
                    that.select(that.selectedIndex);
                    break;
                case keys.UP:
                    that.moveUp();
                    break;
                case keys.DOWN:
                    that.moveDown();
                    break;
                default:
                    return;
            }

            // Cancel event if function did not return:
            e.stopImmediatePropagation();
            e.preventDefault();

        },

        onKeyUp: function (e) {
            var that = this;

            if (that.disabled) {
                return;
            }

            switch (e.which) {
                case keys.UP:
                case keys.DOWN:
                    return;
            }

            clearInterval(that.onChangeInterval);

            if (that.currentValue !== that.el.val()) {
                that.findBestHint();
                if (that.options.deferRequestBy > 0) {
                    // Defer lookup in case when value changes very quickly:
                    that.onChangeInterval = setInterval(function () {
                        that.onValueChange();
                    }, that.options.deferRequestBy);
                } else {
                    that.onValueChange();
                }
            }
        },

        onValueChange: function () {
            var that = this,
                    options = that.options,
                    value = that.el.val(),
                    query = that.getQuery(value);

            if (that.selection && that.currentValue !== query) {
                that.selection = null;
                (options.onInvalidateSelection || $.noop).call(that.element);
            }

            clearInterval(that.onChangeInterval);
            that.currentValue = value;
            that.selectedIndex = -1;

            // Check existing suggestion for the match before proceeding:
            if (options.triggerSelectOnValidInput && that.isExactMatch(query)) {
                that.select(0);
                return;
            }

            if (query.length < options.minChars) {
                that.hide();
            } else {
                that.getSuggestions(query);
            }

        },

        isExactMatch: function (query) {
            var suggestions = this.suggestions;

            return (suggestions.length === 1 && suggestions[0].value.toLowerCase() === query.toLowerCase());
        },

        getQuery: function (value) {
            var delimiter = this.options.delimiter,
                    parts;

            if (!delimiter) {
                return value;
            }
            parts = value.split(delimiter);
            return $.trim(parts[parts.length - 1]);
        },

        getSuggestionsLocal: function (query) {
            var that = this,
                    options = that.options,
                    queryLowerCase = query.toLowerCase(),
                    filter = options.lookupFilter,
                    limit = parseInt(options.lookupLimit, 10),
                    data;

            data = {
                suggestions: $.grep(options.lookup, function (suggestion) {
                    return filter(suggestion, query, queryLowerCase);
                })
            };

            if (limit && data.suggestions.length > limit) {
                data.suggestions = data.suggestions.slice(0, limit);
            }

            return data;
        },

        getSuggestions: function (q) {
            var response,
                    that = this,
                    options = that.options,
                    serviceUrl = options.serviceUrl,
                    params,
                    cacheKey,
                    ajaxSettings;

            options.params[options.paramName] = q;
            params = options.ignoreParams ? null : options.params;

            if (options.onSearchStart.call(that.element, options.params) === false) {
                return;
            }

            if ($.isFunction(options.lookup)) {
                options.lookup(q, function (data) {
                    that.suggestions = data.suggestions;
                    that.suggest();
                    options.onSearchComplete.call(that.element, q, data.suggestions);
                });
                return;
            }

            if (that.isLocal) {
                response = that.getSuggestionsLocal(q);
            } else {
                if ($.isFunction(serviceUrl)) {
                    serviceUrl = serviceUrl.call(that.element, q);
                }
                cacheKey = serviceUrl + '?' + $.param(params || {});
                response = that.cachedResponse[cacheKey];
            }

            if (response && $.isArray(response.suggestions)) {
                that.suggestions = response.suggestions;
                that.suggest();
                options.onSearchComplete.call(that.element, q, response.suggestions);
            } else if (!that.isBadQuery(q)) {
                that.abortAjax();

                ajaxSettings = {
                    url: serviceUrl,
                    data: params,
                    type: options.type,
                    dataType: options.dataType
                };

                $.extend(ajaxSettings, options.ajaxSettings);

                that.currentRequest = $.ajax(ajaxSettings).done(function (data) {
                    var result;
                    that.currentRequest = null;
                    result = options.transformResult(data, q);
                    that.processResponse(result, q, cacheKey);
                    options.onSearchComplete.call(that.element, q, result.suggestions);
                }).fail(function (jqXHR, textStatus, errorThrown) {
                    options.onSearchError.call(that.element, q, jqXHR, textStatus, errorThrown);
                });
            } else {
                options.onSearchComplete.call(that.element, q, []);
            }

        },

        isBadQuery: function (q) {
            if (!this.options.preventBadQueries) {
                return false;
            }

            var badQueries = this.badQueries,
                    i = badQueries.length;

            while (i--) {
                if (q.indexOf(badQueries[i]) === 0) {
                    return true;
                }
            }

            return false;
        },

        hide: function () {
            var that = this,
                    container = $(that.suggestionsContainer);

            if ($.isFunction(that.options.onHide) && that.visible) {
                that.options.onHide.call(that.element, container);
            }

            that.visible = false;
            that.selectedIndex = -1;
            clearInterval(that.onChangeInterval);
            $(that.suggestionsContainer).hide();
            that.signalHint(null);

        },

        suggest: function () {
            if (this.suggestions.length === 0) {
                if (this.options.showNoSuggestionNotice) {
                    this.noSuggestions();
                } else {
                    this.hide();
                }
                return;
            }

            var that = this,
                    options = that.options,
                    groupBy = options.groupBy,
                    formatResult = options.formatResult,
                    value = that.getQuery(that.currentValue),
                    className = that.classes.suggestion,
                    classSelected = that.classes.selected,
                    container = $(that.suggestionsContainer),
                    noSuggestionsContainer = $(that.noSuggestionsContainer),
                    beforeRender = options.beforeRender,
                    html = '',
                    category,
                    formatGroup = function (suggestion, index) {
                        var currentCategory = suggestion.data[groupBy];

                        if (category === currentCategory) {
                            return '';
                        }

                        category = currentCategory;

                        return '<div class="autocomplete-group"><strong>' + category + '</strong></div>';
                    };

            if (options.triggerSelectOnValidInput && that.isExactMatch(value)) {
                that.select(0);
                return;
            }

            // Build suggestions inner HTML:
            $.each(that.suggestions, function (i, suggestion) {
                if (groupBy) {
                    html += formatGroup(suggestion, value, i);
                }

                html += '<div class="' + className + '" data-index="' + i + '">' + formatResult(suggestion, value) + '</div>';
            });

            this.adjustContainerWidth();

            noSuggestionsContainer.detach();
            container.html(html);

            if ($.isFunction(beforeRender)) {
                beforeRender.call(that.element, container);
            }

            that.fixPosition();
            container.show();

            // Select first value by default:
            if (options.autoSelectFirst) {
                that.selectedIndex = 0;
                container.scrollTop(0);
                container.children('.' + className).first().addClass(classSelected);
            }

            that.visible = true;
            that.findBestHint();

        },

        noSuggestions: function () {
            var that = this,
                    container = $(that.suggestionsContainer),
                    noSuggestionsContainer = $(that.noSuggestionsContainer);

            this.adjustContainerWidth();

            // Some explicit steps. Be careful here as it easy to get
            // noSuggestionsContainer removed from DOM if not detached properly.
            noSuggestionsContainer.detach();
            container.empty(); // clean suggestions if any
            container.append(noSuggestionsContainer);

            that.fixPosition();

            container.show();
            that.visible = true;
        },

        adjustContainerWidth: function () {
            var that = this,
                    options = that.options,
                    width,
                    container = $(that.suggestionsContainer);

            // If width is auto, adjust width before displaying suggestions,
            // because if instance was created before input had width, it will be zero.
            // Also it adjusts if input width has changed.
            // -2px to account for suggestions border.
            if (options.width === 'auto') {
                width = that.el.outerWidth() - 2;
                container.width(width > 0 ? width : 300);
            }
        },

        findBestHint: function () {
            var that = this,
                    value = that.el.val().toLowerCase(),
                    bestMatch = null;

            if (!value) {
                return;
            }

            $.each(that.suggestions, function (i, suggestion) {
                var foundMatch = suggestion.value.toLowerCase().indexOf(value) === 0;
                if (foundMatch) {
                    bestMatch = suggestion;
                }
                return !foundMatch;
            });

            that.signalHint(bestMatch);
        },

        signalHint: function (suggestion) {
            var hintValue = '',
                    that = this;
            if (suggestion) {
                hintValue = that.currentValue + suggestion.value.substr(that.currentValue.length);
            }
            if (that.hintValue !== hintValue) {
                that.hintValue = hintValue;
                that.hint = suggestion;
                (this.options.onHint || $.noop)(hintValue);
            }
        },

        verifySuggestionsFormat: function (suggestions) {
            // If suggestions is string array, convert them to supported format:
            if (suggestions.length && typeof suggestions[0] === 'string') {
                return $.map(suggestions, function (value) {
                    return {value: value, data: null};
                });
            }

            return suggestions;
        },

        validateOrientation: function (orientation, fallback) {
            orientation = $.trim(orientation || '').toLowerCase();

            if ($.inArray(orientation, ['auto', 'bottom', 'top']) === -1) {
                orientation = fallback;
            }

            return orientation;
        },

        processResponse: function (result, originalQuery, cacheKey) {
            var that = this,
                    options = that.options;

            result.suggestions = that.verifySuggestionsFormat(result.suggestions);

            // Cache results if cache is not disabled:
            if (!options.noCache) {
                that.cachedResponse[cacheKey] = result;
                if (options.preventBadQueries && result.suggestions.length === 0) {
                    that.badQueries.push(originalQuery);
                }
            }

            // Return if originalQuery is not matching current query:
            if (originalQuery !== that.getQuery(that.currentValue)) {
                return;
            }

            that.suggestions = result.suggestions;
            that.suggest();

        },

        activate: function (index) {
            var that = this,
                    activeItem,
                    selected = that.classes.selected,
                    container = $(that.suggestionsContainer),
                    children = container.find('.' + that.classes.suggestion);

            container.find('.' + selected).removeClass(selected);

            that.selectedIndex = index;

            if (that.selectedIndex !== -1 && children.length > that.selectedIndex) {
                activeItem = children.get(that.selectedIndex);
                $(activeItem).addClass(selected);

                return activeItem;
            }

            return null;
        },

        selectHint: function () {
            var that = this,
                    i = $.inArray(that.hint, that.suggestions);

            that.select(i);
        },

        select: function (i) {
            var that = this;
            that.hide();
            that.onSelect(i);
            setName('customer_mobile', 'customer_name');

            setName('customer_mobile2', 'customer_name2');

        },

        moveUp: function () {
            var that = this;

            if (that.selectedIndex === -1) {
                return;
            }

            if (that.selectedIndex === 0) {
                $(that.suggestionsContainer).children().first().removeClass(that.classes.selected);
                that.selectedIndex = -1;
                that.el.val(that.currentValue);
                that.findBestHint();
                return;
            }

            that.adjustScroll(that.selectedIndex - 1);
        },

        moveDown: function () {
            var that = this;

            if (that.selectedIndex === (that.suggestions.length - 1)) {
                return;
            }

            that.adjustScroll(that.selectedIndex + 1);
        },

        adjustScroll: function (index) {
            var that = this,
                    activeItem = that.activate(index);

            if (!activeItem) {
                return;
            }

            var offsetTop,
                    upperBound,
                    lowerBound,
                    heightDelta = $(activeItem).outerHeight();

            offsetTop = activeItem.offsetTop;
            upperBound = $(that.suggestionsContainer).scrollTop();
            lowerBound = upperBound + that.options.maxHeight - heightDelta;

            if (offsetTop < upperBound) {
                $(that.suggestionsContainer).scrollTop(offsetTop);
            } else if (offsetTop > lowerBound) {
                $(that.suggestionsContainer).scrollTop(offsetTop - that.options.maxHeight + heightDelta);
            }

            if (!that.options.preserveInput) {
                that.el.val(that.getValue(that.suggestions[index].value));
            }
            that.signalHint(null);
        },

        onSelect: function (index) {
            var that = this,
                    onSelectCallback = that.options.onSelect,
                    suggestion = that.suggestions[index];

            that.currentValue = that.getValue(suggestion.value);

            if (that.currentValue !== that.el.val() && !that.options.preserveInput) {
                that.el.val(that.currentValue);
            }

            that.signalHint(null);
            that.suggestions = [];
            that.selection = suggestion;

            if ($.isFunction(onSelectCallback)) {
                onSelectCallback.call(that.element, suggestion);
            }
        },

        getValue: function (value) {
            var that = this,
                    delimiter = that.options.delimiter,
                    currentValue,
                    parts;

            if (!delimiter) {
                return value;
            }

            currentValue = that.currentValue;
            parts = currentValue.split(delimiter);

            if (parts.length === 1) {
                return value;
            }

            return currentValue.substr(0, currentValue.length - parts[parts.length - 1].length) + value;
        },

        dispose: function () {
            var that = this;
            that.el.off('.autocomplete').removeData('autocomplete');
            that.disableKillerFn();
            $(window).off('resize.autocomplete', that.fixPositionCapture);
            $(that.suggestionsContainer).remove();
        }
    };

    // Create chainable jQuery plugin:
    $.fn.autocomplete = $.fn.devbridgeAutocomplete = function (options, args) {
        var dataKey = 'autocomplete';
        // If function invoked without argument return
        // instance of the first matched element:
        if (arguments.length === 0) {
            return this.first().data(dataKey);
        }

        return this.each(function () {
            var inputElement = $(this),
                    instance = inputElement.data(dataKey);

            if (typeof options === 'string') {
                if (instance && typeof instance[options] === 'function') {
                    instance[options](args);
                }
            } else {
                // If instance already exists, destroy it:
                if (instance && instance.dispose) {
                    instance.dispose();
                }
                instance = new Autocomplete(this, options);
                inputElement.data(dataKey, instance);
            }
        });
    };
}));
function save_print_job() {

    document.getElementById("load-orders").disabled = true;
    document.getElementById("load-model").disabled = true;



    var customer = document.getElementById("customer_mobile").value;
    var plate_id = document.getElementById("selected_val").value.split("§")[1];
    var machine = document.getElementById("select_machine").value;
    var registered_date = document.getElementById("reg_date").value;
    var start_date = document.getElementById("job_date").value;
    var order_id = document.getElementById("un-id").value;


    var request = new XMLHttpRequest();
    request.onreadystatechange = function () {
        if (request.readyState === 4) {
            if (request.status === 200) {
                var responce = request.responseText;
                if (responce === "ok") {
                    location.reload();
                } else {
                    new PNotify({
                        title: 'Oh No!',
                        text: responce,
                        type: 'error',
                        styling: 'bootstrap3'
                    });

                }
                document.getElementById("load-orders").disabled = false;
                document.getElementById("load-model").disabled = false;
            }
        }
    };
    request.open("POST", "Save_job", true);
    request.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    request.send("cust=" + customer + "&plt=" + plate_id + "&mch=" + machine + "&regdt=" + registered_date + "&strdt=" + start_date + "&ordid=" + order_id);
}
function setPrinjobDetails(id) {
    var request = new XMLHttpRequest();
    request.onreadystatechange = function () {
        if (request.readyState === 4) {
            if (request.status === 200) {
                var responce = request.responseText;
                document.getElementById("print_job_details").innerHTML = "";
                document.getElementById("print_job_details").innerHTML = responce;
                init_autocomplete();



            }
        }
    };
    request.open("POST", "Load_Job", true);
    request.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    request.send("id=" + id);


}
function chagePrintJobStatus(status) {

    var printJob_id = document.getElementById("print_job3").value;
    var request = new XMLHttpRequest();
    request.onreadystatechange = function () {
        if (request.readyState === 4) {
            if (request.status === 200) {
                var responce = request.responseText;


                if (responce.trim() === "ok") {
                    location.reload();
                } else {
                    new PNotify({
                        title: 'Oh No!',
                        text: responce,
                        type: 'error',
                        styling: 'bootstrap3'
                    });
                }



            }
        }
    };
    request.open("GET", "ChangePrintJobStatus?id=" + printJob_id + "&status=" + status, true);

    request.send();

}

function updatePlate(id) {
   // document.getElementById("load-orders2").disabled = true;
  //  document.getElementById("load-model2").disabled = true;


   
    var customer = document.getElementById("customer_mobile2").value;
    var plate_id = document.getElementById("selected_val2").value.split("§")[1];
    var machine = document.getElementById("select_machine2").value;
    var registered_date = document.getElementById("reg_date2").value;
    var start_date = document.getElementById("job_date2").value;
    var order_id = document.getElementById("un-id2").value;


    var request = new XMLHttpRequest();
    request.onreadystatechange = function () {
        if (request.readyState === 4) {
            if (request.status === 200) {
                var responce = request.responseText;
                if (responce === "ok") {
                    location.reload();
                } else {
                    new PNotify({
                        title: 'Oh No!',
                        text: responce,
                        type: 'error',
                        styling: 'bootstrap3'
                    });

                }
                //document.getElementById("load-orders2").disabled = false;
               // document.getElementById("load-model2").disabled = false;
            }
        }
    };
    request.open("POST", "update_job", true);
    request.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    request.send("cust=" + customer + "&plt=" + plate_id + "&mch=" + machine + "&regdt=" + registered_date + "&strdt=" + start_date + "&ordid=" + order_id+ "&Jobid=" + id);
}





