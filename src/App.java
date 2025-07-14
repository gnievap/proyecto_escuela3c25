
import screens.VentanaPrincipal;

public class App {
    public static void main(String[] args) throws Exception {
        
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        VentanaPrincipal ventanaPrincipal = new VentanaPrincipal("Proyecto Escuela");
        ventanaPrincipal.setSize(500,500);
        ventanaPrincipal.setVisible(true);
    }
}
