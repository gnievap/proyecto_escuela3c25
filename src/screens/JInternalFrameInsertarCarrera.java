package screens;

import java.awt.Font; //Necesaria para cambiar el tipo de letra
import java.sql.Connection;

import javax.swing.GroupLayout; //Necesario para el diseño del layout
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import models.Carrera;
import models.CarreraDAO;

public class JInternalFrameInsertarCarrera extends JInternalFrame{
    private JLabel lblId;
    private JLabel lblNombreCarrera;
    private JLabel lblMonto;
    private JTextField txtId;
    private JTextField txtNombreCarrera;
    private JTextField txtMonto;
    private JButton btnAceptar;
    private JButton btnCancelar;

    private Connection conn; // Conexión a la base de datos

    public JInternalFrameInsertarCarrera(Connection conn) {
        super("Insertar carrera", 
              true,  // resizable
              true,  // closable
              true,  // maximizable
              true); // iconifiable (minimizable)
        this.conn = conn; // Guardar la conexión a la base de datos
        this.setTitle("Insertar nueva carrera");
        this.setSize(400,400);
        initComponents();
    }

    private void initComponents(){
        // Creación de objetos
        lblId = new JLabel("Id:");
        lblNombreCarrera = new JLabel("Nombre de carrera:");
        lblMonto = new JLabel("Monto pagado:");
        txtId = new JTextField();
        txtNombreCarrera = new JTextField();
        txtMonto = new JTextField();
        btnAceptar = new JButton("Aceptar");
        btnCancelar = new JButton("Cancelar");

        // Etiquetas
        lblId.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblNombreCarrera.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblMonto.setFont(new Font("Tahoma", Font.BOLD, 16));
        //Botones 
        btnAceptar.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnCancelar.setFont(new Font("Tahoma", Font.BOLD, 16));

        //Listener para eventos de los botones
        btnAceptar.addActionListener(e -> insertarCarrera());
        btnCancelar.addActionListener(e -> this.dispose());

        GroupLayout layout = new GroupLayout(getContentPane());
        this.setLayout(layout);

        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(lblId)
                .addComponent(txtId)
                .addComponent(lblNombreCarrera)
                .addComponent(txtNombreCarrera)
                .addComponent(lblMonto)
                .addComponent(txtMonto)
                .addGroup(
                    layout.createSequentialGroup()
                        .addComponent(btnAceptar)
                        .addComponent(btnCancelar)
                )
        );

        layout.setVerticalGroup(
            layout.createSequentialGroup()
                .addComponent(lblId)
                .addComponent(txtId, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addComponent(lblNombreCarrera)
                .addComponent(txtNombreCarrera, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addComponent(lblMonto)
                .addComponent(txtMonto, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGroup(
                    layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(btnAceptar)
                        .addComponent(btnCancelar)
                )
        );
    }

    private void insertarCarrera(){
        int rows = 0;

        //Recuperar datos del formulario y meterlos en un objeto Carrera
        int id = Integer.parseInt(txtId.getText());
        String nombre = txtNombreCarrera.getText();
        double monto = Double.parseDouble(txtMonto.getText());

        //Validar que los campos no estén vacíos
        if (txtId.getText().isEmpty() || txtNombreCarrera.getText().isEmpty()
             || txtMonto.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese los datos solicitados");
            return;
        }
        else {
            Carrera carrera = new Carrera(id, nombre, monto);
            CarreraDAO carreraDAO = new CarreraDAO(conn);
            rows = carreraDAO.insertarCarrera(carrera);
            if (rows > 0 ){
                JOptionPane.showMessageDialog(this,"Carrera insertada correctamente");
            }
            else {
                JOptionPane.showMessageDialog(this,"Error al insertar la carrera");
            }
         }
        // Limpiar los campos del formulario
        txtId.setText("");
        txtNombreCarrera.setText("");   
        txtMonto.setText("");
        // Cerrar el JInternalFrame 
        this.dispose();

    }
    
}


