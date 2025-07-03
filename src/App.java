import javax.swing.JFrame;

import screens.VentanaPrincipal;

public class App {
    public static void main(String[] args) throws Exception {
    //    JFrame frame = new JFrame("App Java Swing");
    //    frame.setSize(500, 500);
    //    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    //    frame.setVisible(true);
        VentanaPrincipal ventanaPrincipal = new VentanaPrincipal();
        ventanaPrincipal.setSize(500,500);
        ventanaPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventanaPrincipal.setVisible(true);

    }
}
