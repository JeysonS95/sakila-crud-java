package manager;

import java.sql.*;
import java.util.ArrayList;
import entity.Actor;
import utils.DBConnection;

public class ActorManagerDB {
    public void post(Actor actor) {
        String sql = "INSERT INTO actor (first_name, last_name) VALUES (?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, actor.getFirstName());
            stmt.setString(2, actor.getLastName());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al insertar actor: " + e.getMessage());
        }
    }

    public Actor get(int id) {
        String sql = "SELECT * FROM actor WHERE actor_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Actor(
                    rs.getInt("actor_id"),
                    rs.getString("first_name"),
                    rs.getString("last_name")
                );
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener actor: " + e.getMessage());
        }
        return null;
    }

    public ArrayList<Actor> getAll() {
        ArrayList<Actor> list = new ArrayList<>();
        String sql = "SELECT * FROM actor";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                list.add(new Actor(
                    rs.getInt("actor_id"),
                    rs.getString("first_name"),
                    rs.getString("last_name")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Error al listar actores: " + e.getMessage());
        }
        return list;
    }

    public void put(int id, String newFirstName, String newLastName) {
        String sql = "UPDATE actor SET first_name = ?, last_name = ? WHERE actor_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, newFirstName);
            stmt.setString(2, newLastName);
            stmt.setInt(3, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al actualizar actor: " + e.getMessage());
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM actor WHERE actor_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al eliminar actor: " + e.getMessage());
        }
    }
}
