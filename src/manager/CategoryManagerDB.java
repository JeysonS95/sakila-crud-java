package manager;

import entity.Category;
import utils.DBConnection;

import java.sql.*;
import java.util.ArrayList;

public class CategoryManagerDB {
    public void post(Category category) {
        String sql = "INSERT INTO category (name) VALUES (?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, category.getName());
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("❌ Error al insertar categoría: " + e.getMessage());
        }
    }

    public Category get(int id) {
        String sql = "SELECT category_id, name FROM category WHERE category_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Category(
                    rs.getInt("category_id"),
                    rs.getString("name")
                );
            }

        } catch (SQLException e) {
            System.out.println("❌ Error al buscar categoría: " + e.getMessage());
        }

        return null;
    }

    public ArrayList<Category> getAll() {
        ArrayList<Category> list = new ArrayList<>();
        String sql = "SELECT category_id, name FROM category";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                list.add(new Category(
                    rs.getInt("category_id"),
                    rs.getString("name")
                ));
            }

        } catch (SQLException e) {
            System.out.println("❌ Error al obtener categorías: " + e.getMessage());
        }

        return list;
    }

    public void put(int id, String newName) {
        String sql = "UPDATE category SET name = ? WHERE category_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, newName);
            stmt.setInt(2, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("❌ Error al actualizar categoría: " + e.getMessage());
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM category WHERE category_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("❌ Error al eliminar categoría: " + e.getMessage());
        }
    }
}
