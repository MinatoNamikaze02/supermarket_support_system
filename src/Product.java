

public class Product {

    private int productID;
    private String productName;
    private double sellingPrice;
    private double costPrice;
    int quantityPurchased;
    private int quantity;
    private String lastDateOfPurchase;

    Product(int productId,String productName){
        this.productID = productId;
        this.productName = productName;
    }

    public Product(int productID, String productNme, double sellingPrice, double costPrice, int quantityPurchased, int quantity, String lastDateOfPurchase) {
        this.productID = productID;
        this.productName = productName;
        this.sellingPrice = sellingPrice;
        this.costPrice = costPrice;
        this.quantityPurchased = quantityPurchased;
        this.quantity = quantity;
        this.lastDateOfPurchase = lastDateOfPurchase;
    }


    public int getProductID() {
        return productID;
    }

    public String getProductName() {
        return productName;
    }

    public double getSellingPrice() {
        return sellingPrice;
    }

    public double getCostPrice() {
        return costPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getLastDateOfPurchase() {
        return lastDateOfPurchase;
    }
    public String toString(){
        return "Product ID: " + productID + " " + "Product Name: " + productName;
    }

}
