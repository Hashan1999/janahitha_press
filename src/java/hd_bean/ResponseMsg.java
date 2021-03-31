/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hd_bean;

/**
 *
 * @author HASHAN
 */
public class ResponseMsg {

    public ResponseMsg(int status, String message, Object contend) {
        this.status = status;
        this.message = message;
        this.contend = contend;
    }
    private int status;

    private String message;
    private Object contend;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getContend() {
        return contend;
    }

    public void setContend(Object contend) {
        this.contend = contend;
    }
    
}
