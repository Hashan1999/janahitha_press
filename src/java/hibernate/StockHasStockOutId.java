package hibernate;
// Generated Apr 2, 2021 12:14:54 PM by Hibernate Tools 4.3.1



/**
 * StockHasStockOutId generated by hbm2java
 */
public class StockHasStockOutId  implements java.io.Serializable {


     private int stockIdstock;
     private int stockOutIdstockOutTime;

    public StockHasStockOutId() {
    }

    public StockHasStockOutId(int stockIdstock, int stockOutIdstockOutTime) {
       this.stockIdstock = stockIdstock;
       this.stockOutIdstockOutTime = stockOutIdstockOutTime;
    }
   
    public int getStockIdstock() {
        return this.stockIdstock;
    }
    
    public void setStockIdstock(int stockIdstock) {
        this.stockIdstock = stockIdstock;
    }
    public int getStockOutIdstockOutTime() {
        return this.stockOutIdstockOutTime;
    }
    
    public void setStockOutIdstockOutTime(int stockOutIdstockOutTime) {
        this.stockOutIdstockOutTime = stockOutIdstockOutTime;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof StockHasStockOutId) ) return false;
		 StockHasStockOutId castOther = ( StockHasStockOutId ) other; 
         
		 return (this.getStockIdstock()==castOther.getStockIdstock())
 && (this.getStockOutIdstockOutTime()==castOther.getStockOutIdstockOutTime());
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + this.getStockIdstock();
         result = 37 * result + this.getStockOutIdstockOutTime();
         return result;
   }   


}


