package hibernate;
// Generated Mar 31, 2021 6:04:18 AM by Hibernate Tools 4.3.1



/**
 * StockHasStockOut generated by hbm2java
 */
public class StockHasStockOut  implements java.io.Serializable {


     private StockHasStockOutId id;
     private Stock stock;
     private StockOut stockOut;
     private Integer stockOutQty;

    public StockHasStockOut() {
    }

	
    public StockHasStockOut(StockHasStockOutId id, Stock stock, StockOut stockOut) {
        this.id = id;
        this.stock = stock;
        this.stockOut = stockOut;
    }
    public StockHasStockOut(StockHasStockOutId id, Stock stock, StockOut stockOut, Integer stockOutQty) {
       this.id = id;
       this.stock = stock;
       this.stockOut = stockOut;
       this.stockOutQty = stockOutQty;
    }
   
    public StockHasStockOutId getId() {
        return this.id;
    }
    
    public void setId(StockHasStockOutId id) {
        this.id = id;
    }
    public Stock getStock() {
        return this.stock;
    }
    
    public void setStock(Stock stock) {
        this.stock = stock;
    }
    public StockOut getStockOut() {
        return this.stockOut;
    }
    
    public void setStockOut(StockOut stockOut) {
        this.stockOut = stockOut;
    }
    public Integer getStockOutQty() {
        return this.stockOutQty;
    }
    
    public void setStockOutQty(Integer stockOutQty) {
        this.stockOutQty = stockOutQty;
    }




}


