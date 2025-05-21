package manager;

import entity.City;
import entity.Country;
import utils.DBConnection;

import java.sql.*;
import java.util.ArrayList;

public class CityManagerDB {
    public void post(City city) {
        String sql = "INSERT INTO city (city, country_id) VALUES (?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, city.getCity());
            stmt.setInt(2, city.getCountry().getCountryId());
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("❌ Error al insertar ciudad: " + e.getMessage());
        }
    }

    public City get(int id) {
        String sql = "SELECT c.city_id, c.city, co.country_id, co.country " +
                     "FROM city c JOIN country co ON c.country_id = co.country_id " +
                     "WHERE c.city_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Country country = new Country(
                    rs.getInt("country_id"),
                    rs.getString("country")
                );

                return new City(
                    rs.getInt("city_id"),
                    rs.getString("city"),
                    country
                );
            }

        } catch (SQLException e) {
            System.out.println("❌ Error al buscar ciudad: " + e.getMessage());
        }

        return null;
    }

    public ArrayList<City> getAll() {
        ArrayList<City> list = new ArrayList<>();
        String sql = "SELECT c.city_id, c.city, co.country_id, co.country " +
                     "FROM city c JOIN country co ON c.country_id = co.country_id";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Country country = new Country(
                    rs.getInt("country_id"),
                    rs.getString("country")
                );

                City city = new City(
                    rs.getInt("city_id"),
                    rs.getString("city"),
                    country
                );

                list.add(city);
            }

        } catch (SQLException e) {
            System.out.println("❌ Error al obtener ciudades: " + e.getMessage());
        }

        return list;
    }

    public void put(int id, String newName, int newCountryId) {
        String sql = "UPDATE city SET city = ?, country_id = ? WHERE city_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, newName);
            stmt.setInt(2, newCountryId);
            stmt.setInt(3, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("❌ Error al actualizar ciudad: " + e.getMessage());
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM city WHERE city_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("❌ Error al eliminar ciudad: " + e.getMessage());
        }
    }
}
                