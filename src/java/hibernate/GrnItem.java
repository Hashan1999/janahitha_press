package hibernate;
// Generated Mar 31, 2021 6:04:18 AM by Hibernate Tools 4.3.1



/**
 * GrnItem generated by hbm2java
 */
public class GrnItem  implements java.io.Serializable {


     private Integer idgrnItem;
     private Grn grn;
     private RawItem rawItem;
     private Double unitPrice;
     private Integer qty;

    public GrnItem() {
    }

	
    public GrnItem(Grn grn, RawItem rawItem) {
        this.grn = grn;
        this.rawItem = rawItem;
    }
    public GrnItem(Grn grn, RawItem rawItem, Double unitPrice, Integer qty) {
       this.grn = grn;
       this.rawItem = rawItem;
       this.unitPrice = unitPrice;
       this.qty = qty;
    }
   
    public Integer getIdgrnItem() {
        return this.idgrnItem;
    }
    
    public void setIdgrnItem(Integer idgrnItem) {
        this.idgrnItem = idgrnItem;
    }
    public Grn getGrn() {
        return this.grn;
    }
    
    public void setGrn(Grn grn) {
        this.grn = grn;
    }
    public RawItem getRawItem() {
        return this.rawItem;
    }
    
    public void setRawItem(RawItem rawItem) {
        this.rawItem = rawItem;
    }
    public Double getUnitPrice() {
        return this.unitPrice;
    }
    
    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }
    public Integer getQty() {
        return this.qty;
    }
    
    public void setQty(Integer qty) {
        this.qty = qty;
    }




}


