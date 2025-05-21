package manager;

import entity.Payment;
import utils.DBConnection;

import java.sql.*;
import java.util.ArrayList;

public class PaymentManagerDB {
    public void post(Payment p) {
        String sql = "INSERT INTO payment (customer_id, staff_id, rental_id, amount, payment_date) VALUES (?, 1, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, p.getCustomerId());
            stmt.setInt(2, p.getRentalId());
            stmt.setDouble(3, p.getAmount());
            stmt.setString(4, p.getPaymentDate());
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("❌ Error al insertar pago: " + e.getMessage());
        }
    }

    public Payment get(int id) {
        String sql = "SELECT * FROM payment WHERE payment_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Payment(
                    rs.getInt("payment_id"),
                    rs.getInt("customer_id"),
                    rs.getInt("rental_id"),
                    rs.getDouble("amount"),
                    rs.getString("payment_date")
                );
            }

        } catch (SQLException e) {
            System.out.println("❌ Error al buscar pago: " + e.getMessage());
        }

        return null;
    }

    public ArrayList<Payment> getAll() {
        ArrayList<Payment> list = new ArrayList<>();
        String sql = "SELECT * FROM payment";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                list.add(new Payment(
                    rs.getInt("payment_id"),
                    rs.getInt("customer_id"),
                    rs.getInt("rental_id"),
                    rs.getDouble("amount"),
                    rs.getString("payment_date")
                ));
            }

        } catch (SQLException e) {
            System.out.println("❌ Error al obtener pagos: " + e.getMessage());
        }

        return list;
    }

    public void put(int id, int newCustomerId, int newRentalId, double newAmount, String newDate) {
        String sql = "UPDATE payment SET customer_id = ?, rental_id = ?, amount = ?, payment_date = ? WHERE payment_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, newCustomerId);
            stmt.setInt(2, newRentalId);
            stmt.setDouble(3, newAmount);
            stmt.setString(4, newDate);
            stmt.setInt(5, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("❌ Error al actualizar pago: " + e.getMessage());
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM payment WHERE payment_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("❌ Error al eliminar pago: " + e.getMessage());
        }
    }
}
