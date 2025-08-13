package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AlumnoDAO {
    private Connection conn;

    public AlumnoDAO(Connection conn) {
        this.conn = conn;
    }

    public int obtenerUltimoId() {
        String sql = "SELECT MAX(idalumno) AS max_id FROM alumnos";
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

    public int insertarAlumno(Alumno alumno) {
        String sql = "INSERT INTO alumnos (id_alumno, nombre, apellido_paterno, apellido_materno, domicilio, municipio, bachillerato, prom_bach, genero, cuatrimestre, pagado, id_carrera, prom_gral) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        int rows = 0;    

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, alumno.getIdAlumno());
            stmt.setString(2, alumno.getNombre());
            stmt.setString(3, alumno.getApellidoPaterno());
            stmt.setString(4, alumno.getApellidoMaterno());
            stmt.setString(5, alumno.getDomicilio());
            stmt.setString(6, alumno.getMunicipio());
            stmt.setInt(7, alumno.getBachillerato());
            stmt.setDouble(8, alumno.getPromBach());
            stmt.setString(9, String.valueOf(alumno.getGenero()));
            stmt.setInt(10, alumno.getCuatrimestre());
            stmt.setDouble(11, alumno.getPagado());
            stmt.setInt(12, alumno.getIdCarrera());
            stmt.setDouble(13, alumno.getPromgral());
            rows = stmt.executeUpdate();
        } catch (SQLException e){
            System.out.println("Error al insertar carrera: " + e.getMessage());
        }

        return rows;        
    }        
}
