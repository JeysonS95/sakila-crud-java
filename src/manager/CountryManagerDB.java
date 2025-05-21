package manager;

import entity.Country;
import utils.DBConnection;

import java.sql.*;
import java.util.ArrayList;

public class CountryManagerDB {
    public void post(Country country) {
        String sql = "INSERT INTO country (country) VALUES (?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, country.getCountry());
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("❌ Error al insertar país: " + e.getMessage());
        }
    }

    public Country get(int id) {
        String sql = "SELECT * FROM country WHERE country_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Country(
                    rs.getInt("country_id"),
                    rs.getString("country")
                );
            }

        } catch (SQLException e) {
            System.out.println("❌ Error al buscar país: " + e.getMessage());
        }

        return null;
    }

    public ArrayList<Country> getAll() {
        ArrayList<Country> list = new ArrayList<>();
        String sql = "SELECT * FROM country";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Country country = new Country(
                    rs.getInt("country_id"),
                    rs.getString("country")
                );
                list.add(country);
            }

        } catch (SQLException e) {
            System.out.println("❌ Error al obtener países: " + e.getMessage());
        }

        return list;
    }

    public void put(int id, String newName) {
        String sql = "UPDATE country SET country = ? WHERE country_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, newName);
            stmt.setInt(2, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("❌ Error al actualizar país: " + e.getMessage());
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM country WHERE country_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("❌ Error al eliminar país: " + e.getMessage());
        }
    }
}
