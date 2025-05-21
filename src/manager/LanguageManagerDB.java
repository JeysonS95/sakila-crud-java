package manager;

import entity.Language;
import utils.DBConnection;

import java.sql.*;
import java.util.ArrayList;

public class LanguageManagerDB {

    public void post(Language lang) {
        String sql = "INSERT INTO language (name) VALUES (?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, lang.getName());
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("❌ Error al insertar idioma: " + e.getMessage());
        }
    }

    public Language get(int id) {
        String sql = "SELECT language_id, name FROM language WHERE language_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Language(
                    rs.getInt("language_id"),
                    rs.getString("name")
                );
            }

        } catch (SQLException e) {
            System.out.println("❌ Error al obtener idioma: " + e.getMessage());
        }

        return null;
    }

    public ArrayList<Language> getAll() {
        ArrayList<Language> list = new ArrayList<>();
        String sql = "SELECT language_id, name FROM language";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                list.add(new Language(
                    rs.getInt("language_id"),
                    rs.getString("name")
                ));
            }

        } catch (SQLException e) {
            System.out.println("❌ Error al listar idiomas: " + e.getMessage());
        }

        return list;
    }

    public void put(int id, String newName) {
        String sql = "UPDATE language SET name = ? WHERE language_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, newName);
            stmt.setInt(2, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("❌ Error al actualizar idioma: " + e.getMessage());
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM language WHERE language_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("❌ Error al eliminar idioma: " + e.getMessage());
        }
    }
}
