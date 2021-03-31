/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function isValidEmail(mail) {

    var pattern = "^[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$";
    //var pattern = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    if (mail.match(pattern) === null) {
        return false;
    } else {
        return true;
    }
}
function isValidPassword(passwor) {

    var pattern = "((?=.*[a-z])(?=.*\\d)(?=.*[A-Z])(?=.*[@#$%!]).{8,40})";
   if (passwor.match(pattern) === null) {
        return false;
    } else {
        return true;
    }
}
function isNumber(number) {
  
    var pattern = "^[0-9]*$";
   if (number.match(pattern) === null) {
        return false;
    } else {
        return true;
    }
}
function isPrice(number) {
  
    var pattern = "^(?=.+)(?:[1-9]\d*|0)?(?:\.\d+)?$";
   if (number.match(pattern) === null) {
        return false;
    } else {
        return true;
    }
}
function isPositiveFloatNumber(number) {
  
    var pattern = "^[+-]?([0-9]+([.][0-9]*)?|[.][0-9]+)$";
   if (number.match(pattern) === null) {
        return false;
    } else {
        return true;
    }
}

function  isContainsEscapeCharacters(value) {

    if (value.contains("&") || value.contains("'") || value.contains("?")) {
        return true;
    } else {
        return false;
    }

}

function isValidPostalCode(code) {
    var postalcode = code.toString();
    var pattern = "^(\\d\\s*\\.*-*){5}$";
    if (postalcode.match(pattern) === null) {
        return false;
    } else {
        return true;
    }
}

function isValidateNic(nic) {
    nic = nic.trim().toLowerCase();
    var nic_state = true;
    if (nic.length === 10) {
        if (nic.includes("v") || nic.includes("x") || nic.includes("V") || nic.includes("X")) {
            try {
                var number = nic.substring(0, 9);
                parseInt(number);
                var days = parseInt(nic.substring(2, 5));
                if (366 < days) {
                    nic_state = days > 500;
                }
                if (days > 866) {
                    nic_state = false;
                }
            } catch (error) {
                nic_state = false;
            }

        } else {
            nic_state = false;
        }
    } else {
        if (nic.length === 12) {
            try {
                parseInt(nic);
                var days = parseInt(nic.substring(4, 7));
                if (366 < days) {
                    nic_state = days > 500;
                }
                if (days > 866) {

                    nic_state = false;
                }
            } catch (error) {

                nic_state = false;
            }

        } else {

            nic_state = false;
        }
    }

    return nic_state;

}

var acceptableThirdDigits = "0125678";

function  isValidatedMobile(mobile) {
    var isValid = false;
    mobile = mobile.trim();

    if (mobile.length === 10) {
        if (mobile.charAt(0) === '0') {
            try {
                parseInt(mobile);
                var a = acceptableThirdDigits.split('').join(',');
                for (var i = 0; i < a.length; i++) {
                    if (mobile.charAt(2) === a[i]) {
                        isValid = true;

                        break;
                    }
                }
            } catch (error) {
                isValid = false;
            }
        }
    } else if (mobile.length === 9) {
        try {
            parseInt(mobile);
            var a = acceptableThirdDigits.split('').join(',');
            for (var i = 0; i < a.length; i++) {
                if (mobile.charAt(1) === a[i]) {
                    isValid = true;

                    break;
                }
            }
        } catch (error) {
            isValid = false;
        }

    } else if (mobile.length === 11) {
        if (mobile.substring(0, 2) === "94") {
            mobile = mobile.substring(2);
            try {
                parseInt(mobile);
                var a = acceptableThirdDigits.split('').join(',');
                for (var i = 0; i < a.length; i++) {
                    if (mobile.charAt(1) === a[i]) {
                        isValid = true;

                        break;
                    }
                }
            } catch (error) {
                isValid = false;
            }
        }
    } else if (mobile.length === 12) {
        if (mobile.substring(0, 3) === "+94") {
            mobile = mobile.substring(3);
            try {
                parseInt(mobile);
                var a = acceptableThirdDigits.split('').join(',');
                for (var i = 0; i < a.length; i++) {
                    if (mobile.charAt(1) === a[i]) {
                        isValid = true;

                        break;
                    }
                }
            } catch (error) {
                isValid = false;
            }
        }
    }

    return isValid;
}

