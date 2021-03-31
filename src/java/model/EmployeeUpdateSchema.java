/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author OBITO
 */
import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EmployeeUpdateSchema implements Serializable {

    @SerializedName("empid")
    @Expose
    private Integer empid;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("nic")
    @Expose
    private String nic;
    @SerializedName("contactno")
    @Expose
    private String contactno;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("address1")
    @Expose
    private String address1;
    @SerializedName("address2")
    @Expose
    private String address2;
    @SerializedName("addresscity")
    @Expose
    private String addresscity;
    private final static long serialVersionUID = 7103701160719163763L;

    /**
     * No args constructor for use in serialization
     *
     */
    public EmployeeUpdateSchema() {
    }

    /**
     *
     * @param addresscity
     * @param empid
     * @param address
     * @param address2
     * @param address1
     * @param name
     * @param nic
     * @param email
     * @param contactno
     */
    public EmployeeUpdateSchema(Integer empid, String name, String email, String nic, String contactno, String address, String address1, String address2, String addresscity) {
        super();
        this.empid = empid;
        this.name = name;
        this.email = email;
        this.nic = nic;
        this.contactno = contactno;
        this.address = address;
        this.address1 = address1;
        this.address2 = address2;
        this.addresscity = addresscity;
    }

    public Integer getEmpid() {
        return empid;
    }

    public void setEmpid(Integer empid) {
        this.empid = empid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getContactno() {
        return contactno;
    }

    public void setContactno(String contactno) {
        this.contactno = contactno;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getAddresscity() {
        return addresscity;
    }

    public void setAddresscity(String addresscity) {
        this.addresscity = addresscity;
    }

}
