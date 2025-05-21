package entity;

public class Customer extends Entity {
    private int customer_id;
    private String first_name;
    private String last_name;
    private String email;

    public Customer(int customer_id, String first_name, String last_name, String email) {
        this.customer_id = customer_id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
    }

    public int getCustomerId() {
        return customer_id;
    }

    public String getFirstName() {
        return first_name;
    }

    public String getLastName() {
        return last_name;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return customer_id + " | " + first_name + " " + last_name + " | " + email;
    }
}
