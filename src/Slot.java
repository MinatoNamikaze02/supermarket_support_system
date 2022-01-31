public class Slot {
    private int slotNumber;
    private int ProductId;

    Slot(int slotNumber, int productId) {
        this.slotNumber = slotNumber;
        this.ProductId = productId;
    }

    public int getSlotNumber() {
        return slotNumber;
    }

    public int getProductId() {
        return ProductId;
    }
    public String toString() {
        return "Slot: " +  slotNumber + " " + "Product: " + ProductId;
    }
}
