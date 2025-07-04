package screens;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class JInternalFrameInsertarCarrera extends JInternalFrame{
    private JLabel jLabelId;
    private JLabel jLabelNombre;
    private JLabel jLabelMonto;
    private JTextField jTextFieldId;
    private JTextField jTextFieldNombre;
    private JTextField jTextFieldMonto;
    private JButton jButtonAceptar;
    private JButton jButtonCancelar;

    public JInternalFrameInsertarCarrera(){
        this.setTitle("Insertar nueva carrera");
        initComponents();
    }

    private void initComponents(){
        // Creación de objetos
        jLabelId = new JLabel();
        jLabelNombre = new JLabel();
        jLabelMonto = new JLabel();
        jTextFieldId = new JTextField();
        jTextFieldNombre = new JTextField();
        jTextFieldMonto = new JTextField();
        jButtonAceptar = new JButton();
        jButtonCancelar = new JButton();

        // Etiquetas
        jLabelId.setText("Id:");
        jLabelId.setFont(new Font("Tahoma", 0, 14));
        jLabelNombre.setText("Nombre de carrera:");
        jLabelNombre.setFont(new Font("Tahoma", 0, 14));
        jLabelMonto.setText("Monto pagado:");
        jLabelMonto.setFont(new Font("Tahoma", 0, 14));

        //Botones
        jButtonAceptar.setText("Aceptar");
        jButtonAceptar.setFont(new Font("Tahoma", 0, 14));
        jButtonCancelar.setText("Aceptar");
        jButtonCancelar.setFont(new Font("Tahoma", 0, 14));

        // Agregar componentes al JInternalFrame
        this.getContentPane().add(jLabelId);
        this.getContentPane().add(jTextFieldId);
        this.getContentPane().add(jLabelNombre);
        this.getContentPane().add(jTextFieldNombre);
        this.getContentPane().add(jLabelMonto);
        this.getContentPane().add(jTextFieldMonto);
        this.getContentPane().add(jButtonAceptar);
        this.getContentPane().add(jButtonCancelar);
    }
    
}
