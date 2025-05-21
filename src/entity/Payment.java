package entity;

public class Payment extends Entity {
    private int paymentId;
    private int customerId;
    private int rentalId;
    private double amount;
    private String paymentDate;

    public Payment(int paymentId, int customerId, int rentalId, double amount, String paymentDate) {
        this.paymentId = paymentId;
        this.customerId = customerId;
        this.rentalId = rentalId;
        this.amount = amount;
        this.paymentDate = paymentDate;
    }

    public int getPaymentId() {
        return paymentId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public int getRentalId() {
        return rentalId;
    }

    public double getAmount() {
        return amount;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    @Override
    public String toString() {
        return paymentId + " | Customer: " + customerId + " | Rental: " + rentalId + " | $" + amount + " | Date: " + paymentDate;
    }
}
