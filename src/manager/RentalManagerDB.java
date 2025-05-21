package manager;

import entity.Rental;
import utils.DBConnection;

import java.sql.*;
import java.util.ArrayList;

public class RentalManagerDB {
    public void post(Rental rental) {
        String sql = "INSERT INTO rental (rental_date, inventory_id, customer_id, staff_id) VALUES (?, ?, ?, 1)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, rental.getRentalDate());
            stmt.setInt(2, rental.getInventoryId());
            stmt.setInt(3, rental.getCustomerId());
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("❌ Error al insertar renta: " + e.getMessage());
        }
    }

    public Rental get(int id) {
        String sql = "SELECT rental_id, inventory_id, customer_id, rental_date FROM rental WHERE rental_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Rental(
                    rs.getInt("rental_id"),
                    rs.getInt("inventory_id"),
                    rs.getInt("customer_id"),
                    rs.getString("rental_date")
                );
            }

        } catch (SQLException e) {
            System.out.println("❌ Error al buscar renta: " + e.getMessage());
        }

        return null;
    }

    public ArrayList<Rental> getAll() {
        ArrayList<Rental> list = new ArrayList<>();
        String sql = "SELECT rental_id, inventory_id, customer_id, rental_date FROM rental";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                list.add(new Rental(
                    rs.getInt("rental_id"),
                    rs.getInt("inventory_id"),
                    rs.getInt("customer_id"),
                    rs.getString("rental_date")
                ));
            }

        } catch (SQLException e) {
            System.out.println("❌ Error al obtener rentas: " + e.getMessage());
        }

        return list;
    }

    public void put(int id, int newInventoryId, int newCustomerId, String newDate) {
        String sql = "UPDATE rental SET inventory_id = ?, customer_id = ?, rental_date = ? WHERE rental_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, newInventoryId);
            stmt.setInt(2, newCustomerId);
            stmt.setString(3, newDate);
            stmt.setInt(4, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("❌ Error al actualizar renta: " + e.getMessage());
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM rental WHERE rental_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("❌ Error al eliminar renta: " + e.getMessage());
        }
    }
}
