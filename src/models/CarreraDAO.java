package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/*CarreraDAO -> Contiene todos los métodos para interactuar 
con la tabla Carreras de la base de datos.
Insert, Select, Update, Delete.
*/

public class CarreraDAO {
    private Connection conn;

    public CarreraDAO(Connection conn) {
        this.conn = conn;
    }

    public int obtenerUltimoId() {
        String sql = "SELECT MAX(idcarrera) AS max_id FROM carreras";
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

    public boolean existeCarrera(String nombre) {
        String sql = "SELECT COUNT(*) FROM carreras WHERE nombre = ?";
        boolean existe = false;

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nombre);
            ResultSet rs = stmt.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                existe = true;
            }
        } catch (SQLException e) {
            System.out.println("Error al verificar si la carrera existe: " + e.getMessage());
        }

        return existe;
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
            System.out.println("Error al insertar carrera: " + e.getMessage());
        }

        return rows; 
        
    }

    public ArrayList<Carrera> obtenerCarreras(){
        ArrayList<Carrera> listaCarreras = new ArrayList<>();
        String sql = "SELECT * FROM carreras ORDER BY idcarrera";

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            /* El ResultSet es el resultado del select,
               una matriz con el contenido de la tabla carreras */
            while (rs.next()) { // mientras haya renglones en el resultSet
                // Se recuperan los datos de cada renglón del rs
                int idcarrera = rs.getInt("idcarrera");
                String nombre = rs.getString("nombre");
                double monto = rs.getDouble("monto");

                // Se crea un POJO con los datos de cada renglon del rs
                Carrera unaCarrera = new Carrera(idcarrera, nombre, monto);
                // Se agrega el nuevo objeto Carrera al arreglo
                listaCarreras.add(unaCarrera);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }

        // Se regresa un arreglo con objetos Carrera
        return listaCarreras;
    }




    
}
