package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class BachilleratoDAO {
    
    private Connection conn;

    public BachilleratoDAO(Connection conn) {
        this.conn = conn;
    }

    public int obtenerUltimoId() {
        String sql = "SELECT MAX(id) AS max_id FROM bachilleratos";
        int ultimoId = 0;

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                ultimoId = rs.getInt("max_id");
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener el último ID: " + e.getMessage());
        }

        return ultimoId;
    }

    public boolean existeBachillerato(String nombre) {
        String sql = "SELECT COUNT(*) FROM bachilleratos WHERE nombre = ?";
        boolean existe = false;

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nombre);
            ResultSet rs = stmt.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                existe = true;
            }
        } catch (SQLException e) {
            System.out.println("Error al verificar si el bachillerato existe: " + e.getMessage());
        }

        return existe;
    }

    public int insertarBachillerato(Bachillerato bachillerato) {

        String sql = "INSERT INTO bachilleratos (id, nombre) VALUES (?, ?)";
        int rows = 0;

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, bachillerato.getIdBachillerato());
            stmt.setString(2, bachillerato.getNombre());
            rows = stmt.executeUpdate();
        } catch (SQLException e){
            System.out.println("Error al insertar bachillerato: " + e.getMessage());
        }

        return rows; 
        
    }

    public ArrayList<Bachillerato> obtenerBachilleratos() {
        String sql = "SELECT idbachillerato, nombre FROM bachilleratos";
        ArrayList<Bachillerato> bachilleratos = new ArrayList<>();

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                int id = rs.getInt("idbachillerato");
                String nombre = rs.getString("nombre");
                bachilleratos.add(new Bachillerato(id, nombre));
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener los bachilleratos: " + e.getMessage());
        }

        return bachilleratos;
    }

    public ArrayList<String> obtenerNombresBachilleratos(){
        ArrayList<String> nombresBachilleratos = new ArrayList<>();
        String sql = "SELECT nombre FROM bachilleratos ORDER BY id";

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            /* El ResultSet es el resultado del select,
               una matriz con el contenido de la tabla carreras */
            while (rs.next()) { // mientras haya renglones en el resultSet
                // Se recuperan los datos de cada renglón del rs
                String nombre = rs.getString("nombre");
                // Se agrega el nuevo objeto Carrera al arreglo
                nombresBachilleratos.add(nombre);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }

        return nombresBachilleratos;
    }


}
