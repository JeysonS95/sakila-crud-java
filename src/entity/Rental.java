package entity;

public class Rental extends Entity {
    private int rentalId;
    private int inventoryId;
    private int customerId;
    private String rentalDate;

    public Rental(int rentalId, int inventoryId, int customerId, String rentalDate) {
        this.rentalId = rentalId;
        this.inventoryId = inventoryId;
        this.customerId = customerId;
        this.rentalDate = rentalDate;
    }

    public int getRentalId() {
        return rentalId;
    }

    public int getInventoryId() {
        return inventoryId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public String getRentalDate() {
        return rentalDate;
    }

    @Override
    public String toString() {
        return rentalId + " | Inventory: " + inventoryId + " | Customer: " + customerId + " | Date: " + rentalDate;
    }
}
