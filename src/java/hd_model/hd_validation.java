/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hd_model;

/**
 *
 * @author HASHAN
 */
public class hd_validation {
     final static String acceptableThirdDigits = "0125678";

    public static String getValidatedMobile(String mobile) throws InvalidMobileException {
        boolean isValid = false;
        mobile = mobile.trim();
        String validatedMobile = "";
        if (mobile.length() == 10) {
            if (mobile.charAt(0) == '0') {
                try {
                    Integer.parseInt(mobile);
                    char[] a = acceptableThirdDigits.toCharArray();
                    for (int i = 0; i < a.length; i++) {
                        if (mobile.charAt(2) == a[i]) {
                            isValid = true;
                            validatedMobile = "94" + mobile.substring(1);
                            break;
                        }
                    }
                } catch (Exception e) {
                    isValid = false;
                }
            }
        } else if (mobile.length() == 9) {
            try {
                Integer.parseInt(mobile);
                char[] a = acceptableThirdDigits.toCharArray();
                for (int i = 0; i < a.length; i++) {
                    if (mobile.charAt(1) == a[i]) {
                        isValid = true;
                        validatedMobile = "94" + mobile;
                        break;
                    }
                }
            } catch (Exception e) {
                isValid = false;
            }

        } else if (mobile.length() == 11) {
            if (mobile.substring(0, 2).equals("94")) {
                mobile = mobile.substring(2);
                try {
                    Integer.parseInt(mobile);
                    char[] a = acceptableThirdDigits.toCharArray();
                    for (int i = 0; i < a.length; i++) {
                        if (mobile.charAt(1) == a[i]) {
                            isValid = true;
                            validatedMobile = "94" + mobile;
                            break;
                        }
                    }
                } catch (Exception e) {
                    isValid = false;
                }
            }
        } else if (mobile.length() == 12) {
            if (mobile.substring(0, 3).equals("+94")) {
                mobile = mobile.substring(3);
                try {
                    Integer.parseInt(mobile);
                    char[] a = acceptableThirdDigits.toCharArray();
                    for (int i = 0; i < a.length; i++) {
                        if (mobile.charAt(1) == a[i]) {
                            isValid = true;
                            validatedMobile = "94" + mobile;
                            break;
                        }
                    }
                } catch (Exception e) {
                    isValid = false;
                }
            }
        }
       
        return validatedMobile;
    }

    public static boolean isNumericInt(String str) { 
  try {  
    Integer.parseInt(str);  
    return true;
  } catch(NumberFormatException e){  
    return false;  
  }  
}
    public static boolean isNumericDouble(String str) { 
  try {  
    Double.valueOf(str);
    return true;
  } catch(NumberFormatException e){  
    return false;  
  }  
}
}
