
import java.sql.Connection;

import models.Conexion;
import screens.VentanaPrincipal;

public class App {
    public static void main(String[] args) throws Exception {
        Connection conn = null;

        
            conn = Conexion.getConnection();
            if (conn != null )
            {
                System.out.println("Conexión exitosa a la base de datos.");
            }
            else
            {
                System.out.println("No se pudo establecer la conexión a la base de datos.");
            }
        
        
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println("Error en el Look and Feel: " + e.getMessage());
        }
        VentanaPrincipal ventanaPrincipal = new VentanaPrincipal("Proyecto Escuela", conn);
        ventanaPrincipal.setSize(500,500);
        ventanaPrincipal.setVisible(true);
    }
}
