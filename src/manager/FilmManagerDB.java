package manager;

import entity.Film;
import entity.Language;
import utils.DBConnection;

import java.sql.*;
import java.util.ArrayList;

public class FilmManagerDB {
    public void post(Film film) {
        String sql = "INSERT INTO film (title, description, language_id) VALUES (?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, film.getTitle());
            stmt.setString(2, film.getDescription());
            stmt.setInt(3, film.getLanguage().getLanguageId());
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("❌ Error al insertar film: " + e.getMessage());
        }
    }

    public Film get(int id) {
        String sql = "SELECT f.film_id, f.title, f.description, l.language_id, l.name " +
                     "FROM film f JOIN language l ON f.language_id = l.language_id " +
                     "WHERE f.film_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Language lang = new Language(
                    rs.getInt("language_id"),
                    rs.getString("name")
                );
                return new Film(
                    rs.getInt("film_id"),
                    rs.getString("title"),
                    rs.getString("description"),
                    lang
                );
            }

        } catch (SQLException e) {
            System.out.println("❌ Error al buscar film: " + e.getMessage());
        }

        return null;
    }

    public ArrayList<Film> getAll() {
        ArrayList<Film> list = new ArrayList<>();
        String sql = "SELECT f.film_id, f.title, f.description, l.language_id, l.name " +
                     "FROM film f JOIN language l ON f.language_id = l.language_id";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Language lang = new Language(
                    rs.getInt("language_id"),
                    rs.getString("name")
                );
                list.add(new Film(
                    rs.getInt("film_id"),
                    rs.getString("title"),
                    rs.getString("description"),
                    lang
                ));
            }

        } catch (SQLException e) {
            System.out.println("❌ Error al obtener films: " + e.getMessage());
        }

        return list;
    }

    public void put(int id, String newTitle, String newDesc, int newLangId) {
        String sql = "UPDATE film SET title = ?, description = ?, language_id = ? WHERE film_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, newTitle);
            stmt.setString(2, newDesc);
            stmt.setInt(3, newLangId);
            stmt.setInt(4, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("❌ Error al actualizar film: " + e.getMessage());
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM film WHERE film_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("❌ Error al eliminar film: " + e.getMessage());
        }
    }
}
