package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/*CarreraDAO -> Contiene todos los métodos para interactuar 
con la tabla Carreras de la base de datos.
Insert, Select, Update, Delete.
*/

public class CarreraDAO {
    private Connection conn;

    public CarreraDAO(Connection conn) {
        this.conn = conn;
    }

    public int insertarCarrera(Carrera carrera) {

        String sql = "INSERT INTO carreras (idcarrera, nombre, monto) VALUES (?, ?, ?)";
        int rows = 0;

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, carrera.getIdcarrera());
            stmt.setString(2, carrera.getNombre());
            stmt.setDouble(3, carrera.getMonto());
            rows = stmt.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }

        return rows; 
        
    }


    
}
