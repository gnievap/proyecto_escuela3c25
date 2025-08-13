package screens;

import java.awt.Font;
import java.sql.Connection;
import java.util.ArrayList;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import models.Alumno;
import models.AlumnoDAO;
import models.CarreraDAO;


public class JInternalFrameInsertarAlumno  extends JInternalFrame {
    // Aquí irían los atributos y métodos específicos para insertar un alumno
    // Similar a lo que se hizo en JInternalFrameInsertarCarrera
    // Por ejemplo, etiquetas, campos de texto, botones, etc.

    private Connection conn; // Conexión a la base de datos
    private JLabel lblId;
    private JLabel lblNombre;
    private JLabel lblApat;
    private JLabel lblAmat;
    private JLabel lblDomicilio;
    private JLabel lblMunicipio;
    private JLabel lblBachillerato;
    private JLabel lblCarrera; // Añadir etiqueta para Carrera
    private JTextField txtId;
    private JTextField txtNombre;
    private JTextField txtApat;
    private JTextField txtAmat;
    private JTextField txtDomicilio;
    private JTextField txtMunicipio;
    private JTextField txtBachillerato;
    private JComboBox<String> jcbCarrera; // Añadir campo de texto para Carrera
    private JButton btnAceptar;
    private JButton btnCancelar;
    private AlumnoDAO alumnoDAO;
    private CarreraDAO carreraDAO;


    public JInternalFrameInsertarAlumno(Connection conn) {
        super("Insertar Alumno", 
              true,  // resizable
              true,  // closable
              true,  // maximizable
              true); // iconifiable (minimizable)
        this.conn = conn; // Inicializar la conexión a la base de datos 
        this.alumnoDAO = new AlumnoDAO(conn);  
        this.carreraDAO = new CarreraDAO(conn);
        this.setTitle("Insertar nuevo alumno");
        this.setSize(400, 400);
        initComponents();
    }

    private void initComponents() {
         // Obtener el último ID y sumarle 1 para el nuevo registro
        int lastId = alumnoDAO.obtenerUltimoId() + 1; 
        //Obtener los nombres de las carreras para el JComboBox
        ArrayList<String> nombresCarreras = carreraDAO.obtenerNombresCarreras();
        // Creación de objetos
        lblId = new JLabel("Id:");
        lblNombre = new JLabel("Nombre:");
        lblApat = new JLabel("Apellido Paterno:");
        lblAmat = new JLabel("Apellido Materno:");
        lblDomicilio = new JLabel("Domicilio:");
        lblMunicipio = new JLabel("Municipio:");
        lblBachillerato = new JLabel("Bachillerato:");  
        lblCarrera = new JLabel("Carrera:"); // Añadir etiqueta para Carrera

        txtId = new JTextField(Integer.toString(lastId));
        txtId.setEditable(false); // El ID no se puede editar
        txtId.setEnabled(false); // Deshabilitar el campo de texto

    
        txtNombre = new JTextField();
        txtApat = new JTextField();
        txtAmat = new JTextField();
        txtDomicilio = new JTextField();
        txtMunicipio = new JTextField();
        txtBachillerato = new JTextField();
        jcbCarrera = new JComboBox<>(); // Inicializar el JComboBox para Carrera
        for (String nombre : nombresCarreras) {
            jcbCarrera.addItem(nombre);
        }


        btnAceptar = new JButton("Aceptar");
        btnCancelar = new JButton("Cancelar");

        // Etiquetas
        lblId.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblNombre.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblApat.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblAmat.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblDomicilio.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblMunicipio.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblBachillerato.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblCarrera.setFont(new Font("Tahoma", Font.BOLD, 16));

        //Botones 
        btnAceptar.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnCancelar.setFont(new Font("Tahoma", Font.BOLD, 16));

        btnAceptar.addActionListener(e -> insertarAlumno());
        btnCancelar.addActionListener(e -> this.dispose());

        GroupLayout layout = new GroupLayout(getContentPane());
        this.setLayout(layout);

        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(lblId)
                .addComponent(txtId)
                .addComponent(lblNombre)
                .addComponent(txtNombre)
                .addComponent(lblApat)
                .addComponent(txtApat)
                .addComponent(lblAmat)
                .addComponent(txtAmat)
                .addComponent(lblDomicilio)
                .addComponent(txtDomicilio)
                .addComponent(lblMunicipio)
                .addComponent(txtMunicipio)
                .addComponent(lblBachillerato)
                .addComponent(txtBachillerato)
                .addComponent(lblCarrera)
                .addComponent(jcbCarrera) // Añadir el JComboBox para Carrera
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
                .addComponent(lblNombre)
                .addComponent(txtNombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addComponent(lblApat)
                .addComponent(txtApat, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addComponent(lblAmat)
                .addComponent(txtAmat, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addComponent(lblDomicilio)
                .addComponent(txtDomicilio, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addComponent(lblMunicipio)
                .addComponent(txtMunicipio, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addComponent(lblBachillerato)
                .addComponent(txtBachillerato, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addComponent(lblCarrera)
                .addComponent(jcbCarrera, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE) // Añadir el JComboBox para Carrera
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(
                    layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(btnAceptar)
                        .addComponent(btnCancelar)
                )
        );
    }

    private void insertarAlumno() {  
        int rows = 0;
        int id = Integer.parseInt(txtId.getText());
        String nombre = txtNombre.getText();
        String apellidoPaterno = txtApat.getText();
        String apellidoMaterno = txtAmat.getText();
        String domicilio = txtDomicilio.getText();
        String municipio = txtMunicipio.getText();
        int bachillerato = Integer.parseInt(txtBachillerato.getText());
        double promBach = 0.0; // Asignar un valor por defecto
        char genero = 'M'; // Asignar un valor por defecto, puede ser 'M' o 'F'
        int cuatrimestre = 1; // Asignar un valor por defecto
        double pagado = 0.0; // Asignar un valor por defecto
        int idCarrera = 1; // Asignar un valor por defecto
        
        
   
        if (txtId.getText().isEmpty() || txtNombre.getText().isEmpty() || txtApat.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos.");
                return;
        }
        else {
            Alumno alumno = new Alumno(id, nombre, apellidoPaterno, apellidoMaterno, domicilio, municipio, bachillerato, promBach, genero, cuatrimestre, pagado, idCarrera, 0.0);
            AlumnoDAO alumnoDAO = new AlumnoDAO(conn);
            rows = alumnoDAO.insertarAlumno(alumno);
            if (rows > 0) {
                JOptionPane.showMessageDialog(this, "Alumno insertado correctamente.");
                this.dispose(); // Cerrar el JInternalFrame
            } else {
                JOptionPane.showMessageDialog(this, "Error al insertar el alumno.");
            }
            txtId.setText("");
            txtNombre.setText("");
            txtApat.setText("");
            txtAmat.setText("");
            txtDomicilio.setText("");
            txtMunicipio.setText("");
            txtBachillerato.setText("");
        }
          
    }
    
}
