package screens;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.sql.Connection;
import java.util.ArrayList;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import models.Alumno;
import models.AlumnoDAO;
import models.BachilleratoDAO;
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
    private JLabel lblPromBach;
    private JLabel lblCarrera; // Añadir etiqueta para Carrera
    private JLabel lblGenero; // Añadir etiqueta para Género
    private JLabel lblCuatrimestre; // Añadir etiqueta para Cuatrimestre
    private JLabel lblPagado; // Añadir etiqueta para Pagado
   private JLabel lblPromGral; 
    private JTextField txtId;
    private JTextField txtNombre;
    private JTextField txtApat;
    private JTextField txtAmat;
    private JTextField txtDomicilio;
    private JComboBox<String> jcbMunicipio;
    private JTextField txtPromBach; // Añadir campo de texto para Promedio Bachillerato
    private JTextField txtGenero; // Añadir campo de texto para Género
    private JTextField txtPagado; // Añadir campo de texto para Pagado
    private JTextField txtPromGral; // Añadir campo de texto para Promedio General
    private JComboBox<String> jcbBachillerato;
    private JComboBox<String> jcbCarrera; 
    private JComboBox<String> jcbCuatrimestre; // Añadir campo de texto para Cuatrimestre
   
    private JButton btnAceptar;
    private JButton btnCancelar;
    private AlumnoDAO alumnoDAO;
    private CarreraDAO carreraDAO;
    private BachilleratoDAO bachilleratoDAO;


    public JInternalFrameInsertarAlumno(Connection conn) {
        super("Insertar Alumno", 
              true,  // resizable
              true,  // closable
              true,  // maximizable
              true); // iconifiable (minimizable)
        this.conn = conn; // Inicializar la conexión a la base de datos 
        this.alumnoDAO = new AlumnoDAO(conn);  
        this.carreraDAO = new CarreraDAO(conn);
        this.bachilleratoDAO = new BachilleratoDAO(conn);
        this.setTitle("Insertar nuevo alumno");
        this.setSize(400, 400);
        initComponents();
    }

    private void initComponents() {
    String[] municipios = {"Altzayanca", "Apizaco", "Huamantla", "Tlaxcala", "Chiautempan", "Nativitas", "San Pablo del Monte"};
    int lastId = alumnoDAO.obtenerUltimoId() + 1;
    ArrayList<String> nombresCarreras = carreraDAO.obtenerNombresCarreras();
    ArrayList<String> nombresBachilleratos = bachilleratoDAO.obtenerNombresBachilleratos();

    // === Creación de componentes ===
    lblId = new JLabel("Id:");
    lblNombre = new JLabel("Nombre:");
    lblApat = new JLabel("Apellido Paterno:");
    lblAmat = new JLabel("Apellido Materno:");
    lblDomicilio = new JLabel("Domicilio:");
    lblMunicipio = new JLabel("Municipio:");
    lblBachillerato = new JLabel("Bachillerato:");
    lblPromBach = new JLabel("Promedio Bachillerato:");
    lblGenero = new JLabel("Género:");
    lblCuatrimestre = new JLabel("Cuatrimestre:");
    lblPagado = new JLabel("Pagado:");
    lblPromGral = new JLabel("Promedio General:");
    lblCarrera = new JLabel("Carrera:");

    txtId = new JTextField(Integer.toString(lastId));
    txtId.setEditable(false);
    txtId.setEnabled(false);

    txtNombre = new JTextField();
    txtApat = new JTextField();
    txtAmat = new JTextField();
    txtDomicilio = new JTextField();
    txtPromBach = new JTextField();
    txtGenero = new JTextField();
    txtPagado = new JTextField();
    txtPromGral = new JTextField();
    jcbCuatrimestre = new JComboBox<>(new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11"});

    jcbMunicipio = new JComboBox<>(municipios);

    jcbBachillerato = new JComboBox<>();
    for (String bachillerato : nombresBachilleratos) {
        jcbBachillerato.addItem(bachillerato);
    }

    jcbCarrera = new JComboBox<>();
    for (String nombre : nombresCarreras) {
        jcbCarrera.addItem(nombre);
    }

    btnAceptar = new JButton("Aceptar");
    btnCancelar = new JButton("Cancelar");
    btnAceptar.addActionListener(e -> insertarAlumno());
    btnCancelar.addActionListener(e -> this.dispose());

    // === Fuente en labels y botones ===
    Font font = new Font("Tahoma", Font.BOLD, 16);
    for (JLabel lbl : new JLabel[]{lblId, lblNombre, lblApat, lblAmat, lblDomicilio, lblMunicipio,
                                   lblBachillerato, lblCarrera, lblPromBach, lblGenero,
                                   lblCuatrimestre, lblPagado, lblPromGral}) {
        lbl.setFont(font);
    }
    btnAceptar.setFont(font);
    btnCancelar.setFont(font);

    // === Panel izquierdo: Datos personales ===
    JPanel panelIzq = new JPanel();
    GroupLayout glIzq = new GroupLayout(panelIzq);
    panelIzq.setLayout(glIzq);
    glIzq.setAutoCreateGaps(true);
    glIzq.setAutoCreateContainerGaps(true);

    glIzq.setHorizontalGroup(
        glIzq.createParallelGroup()
            .addComponent(lblId).addComponent(txtId)
            .addComponent(lblNombre).addComponent(txtNombre)
            .addComponent(lblApat).addComponent(txtApat)
            .addComponent(lblAmat).addComponent(txtAmat)
            .addComponent(lblDomicilio).addComponent(txtDomicilio)
            .addComponent(lblMunicipio).addComponent(jcbMunicipio)
    );
    glIzq.setVerticalGroup(
        glIzq.createSequentialGroup()
            .addComponent(lblId).addComponent(txtId)
            .addComponent(lblNombre).addComponent(txtNombre)
            .addComponent(lblApat).addComponent(txtApat)
            .addComponent(lblAmat).addComponent(txtAmat)
            .addComponent(lblDomicilio).addComponent(txtDomicilio)
            .addComponent(lblMunicipio).addComponent(jcbMunicipio)
    );

    // === Panel derecho: Datos académicos ===
    JPanel panelDer = new JPanel();
    GroupLayout glDer = new GroupLayout(panelDer);
    panelDer.setLayout(glDer);
    glDer.setAutoCreateGaps(true);
    glDer.setAutoCreateContainerGaps(true);

    glDer.setHorizontalGroup(
        glDer.createParallelGroup()
            .addComponent(lblBachillerato).addComponent(jcbBachillerato)
            .addComponent(lblPromBach).addComponent(txtPromBach)
            .addComponent(lblCuatrimestre).addComponent(jcbCuatrimestre)
            .addComponent(lblCarrera).addComponent(jcbCarrera)
            .addComponent(lblPagado).addComponent(txtPagado)
            .addComponent(lblPromGral).addComponent(txtPromGral)
    );
    glDer.setVerticalGroup(
        glDer.createSequentialGroup()
            .addComponent(lblBachillerato).addComponent(jcbBachillerato)
            .addComponent(lblPromBach).addComponent(txtPromBach)
            .addComponent(lblCuatrimestre).addComponent(jcbCuatrimestre)
            .addComponent(lblCarrera).addComponent(jcbCarrera)
            .addComponent(lblPagado).addComponent(txtPagado)
            .addComponent(lblPromGral).addComponent(txtPromGral)
    );

    // === Panel principal con dos columnas ===
    JPanel panelPrincipal = new JPanel(new GridLayout(1, 2, 20, 0));
    panelPrincipal.add(panelIzq);
    panelPrincipal.add(panelDer);

    // === Panel botones ===
    JPanel panelBotones = new JPanel();
    panelBotones.add(btnAceptar);
    panelBotones.add(btnCancelar);

    // === Añadir todo al JInternalFrame ===
    getContentPane().setLayout(new BorderLayout());
    getContentPane().add(panelPrincipal, BorderLayout.CENTER);
    getContentPane().add(panelBotones, BorderLayout.SOUTH);

    pack();
}


    // private void initComponents() {
    //     String[] municipios = {"Altzayanca", "Apizaco", "Huamantla", "Tlaxcala", "Chiautempan", "Nativitas", "San Pablo del Monte"}; // Lista de municipios
    //      // Obtener el último ID y sumarle 1 para el nuevo registro
    //     int lastId = alumnoDAO.obtenerUltimoId() + 1; 
    //     //Obtener los nombres de las carreras para el JComboBox
    //     ArrayList<String> nombresCarreras = carreraDAO.obtenerNombresCarreras();
    //     // Obtener los nombres de los bachilleratos para el JComboBox
    //     ArrayList<String> nombresBachilleratos = bachilleratoDAO.obtenerNombresBachilleratos();
    //     // Creación de objetos
    //     lblId = new JLabel("Id:");
    //     lblNombre = new JLabel("Nombre:");
    //     lblApat = new JLabel("Apellido Paterno:");
    //     lblAmat = new JLabel("Apellido Materno:");
    //     lblDomicilio = new JLabel("Domicilio:");
    //     lblMunicipio = new JLabel("Municipio:");
    //     lblBachillerato = new JLabel("Bachillerato:");  
    //     lblPromBach = new JLabel("Promedio Bachillerato:"); // Añadir etiqueta para Promedio Bachillerato
    //     lblGenero = new JLabel("Género:"); // Añadir etiqueta para Género
    //     lblCuatrimestre = new JLabel("Cuatrimestre:"); // Añadir etiqueta para Cuatrimestre
    //     lblPagado = new JLabel("Pagado:"); // Añadir etiqueta para Pagado
    //     lblPromGral = new JLabel("Promedio General:"); // Añ
    //     lblCarrera = new JLabel("Carrera:"); // Añadir etiqueta para Carrera

    //     txtId = new JTextField(Integer.toString(lastId));
    //     txtId.setEditable(false); // El ID no se puede editar
    //     txtId.setEnabled(false); // Deshabilitar el campo de texto

    
    //     txtNombre = new JTextField();
    //     txtApat = new JTextField();
    //     txtAmat = new JTextField();
    //     txtDomicilio = new JTextField();
    //     txtPromBach = new JTextField(); // Campo de texto para Promedio Bachillerato
    //     txtGenero = new JTextField(); // Campo de texto para Género
    //     txtPagado = new JTextField(); // Campo de texto para Pagado
    //     txtPromGral = new JTextField();   // Campo de texto para Promedio General
    //     jcbCuatrimestre = new JComboBox<>(new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11"}); // Cuatrimestres disponibles

    //     jcbMunicipio = new JComboBox<>(municipios);
    //     jcbBachillerato = new JComboBox<>();
    //     for (String bachillerato : nombresBachilleratos) {
    //         jcbBachillerato.addItem(bachillerato);
    //     }

    //     jcbCarrera = new JComboBox<>(); // Inicializar el JComboBox para Carrera
    //     for (String nombre : nombresCarreras) {
    //         jcbCarrera.addItem(nombre);
    //     }

      
    //     btnAceptar = new JButton("Aceptar");
    //     btnCancelar = new JButton("Cancelar");

    //     // Etiquetas
    //     lblId.setFont(new Font("Tahoma", Font.BOLD, 16));
    //     lblNombre.setFont(new Font("Tahoma", Font.BOLD, 16));
    //     lblApat.setFont(new Font("Tahoma", Font.BOLD, 16));
    //     lblAmat.setFont(new Font("Tahoma", Font.BOLD, 16));
    //     lblDomicilio.setFont(new Font("Tahoma", Font.BOLD, 16));
    //     lblMunicipio.setFont(new Font("Tahoma", Font.BOLD, 16));
    //     lblBachillerato.setFont(new Font("Tahoma", Font.BOLD, 16));
    //     lblCarrera.setFont(new Font("Tahoma", Font.BOLD, 16));
    //     lblPromBach.setFont(new Font("Tahoma", Font.BOLD, 16)); // Añadir fuente para Promedio Bachillerato
    //     lblGenero.setFont(new Font("Tahoma", Font.BOLD, 16)); // Añadir fuente para Género
    //     lblCuatrimestre.setFont(new Font("Tahoma", Font.BOLD, 16)); // Añadir fuente para Cuatrimestre
    //     lblPagado.setFont(new Font("Tahoma", Font.BOLD, 16)); // Añadir fuente para Pagado
    //     lblPromGral.setFont(new Font("Tahoma", Font.BOLD, 16)); // Añadir fuente para

    //     //Botones 
    //     btnAceptar.setFont(new Font("Tahoma", Font.BOLD, 16));
    //     btnCancelar.setFont(new Font("Tahoma", Font.BOLD, 16));

    //     btnAceptar.addActionListener(e -> insertarAlumno());
    //     btnCancelar.addActionListener(e -> this.dispose());

    //     GroupLayout layout = new GroupLayout(getContentPane());
    //     this.setLayout(layout);

    //     layout.setAutoCreateGaps(true);
    //     layout.setAutoCreateContainerGaps(true);

    //     layout.setHorizontalGroup(
    //         layout.createParallelGroup(GroupLayout.Alignment.LEADING)
    //             .addComponent(lblId)
    //             .addComponent(txtId)
    //             .addComponent(lblNombre)
    //             .addComponent(txtNombre)
    //             .addComponent(lblApat)
    //             .addComponent(txtApat)
    //             .addComponent(lblAmat)
    //             .addComponent(txtAmat)
    //             .addComponent(lblDomicilio)
    //             .addComponent(txtDomicilio)
    //             .addComponent(lblMunicipio)
    //             .addComponent(jcbMunicipio)
    //             .addComponent(lblBachillerato)
    //             .addComponent(txtPromBach)
    //             .addComponent(jcbBachillerato)
    //             .addComponent(lblPromBach)
    //             .addComponent(lblCuatrimestre)
    //             .addComponent(jcbCuatrimestre)
                
    //             .addComponent(lblCarrera)
    //             .addComponent(jcbCarrera) // Añadir el JComboBox para Carrera
    //             .addComponent(lblPagado)
    //             .addComponent(txtPagado)
    //             .addComponent(lblPromGral)
    //             .addComponent(txtPromGral) // Añadir el campo de texto para Promedio
    //             .addGroup(
    //                 layout.createSequentialGroup()
    //                     .addComponent(btnAceptar)
    //                     .addComponent(btnCancelar)
    //             )
    //     );

    //     layout.setVerticalGroup(
    //         layout.createSequentialGroup()
    //             .addComponent(lblId)
    //             .addComponent(txtId, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
    //             .addComponent(lblNombre)
    //             .addComponent(txtNombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
    //             .addComponent(lblApat)
    //             .addComponent(txtApat, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
    //             .addComponent(lblAmat)
    //             .addComponent(txtAmat, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
    //             .addComponent(lblDomicilio)
    //             .addComponent(txtDomicilio, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
    //             .addComponent(lblMunicipio)
    //             .addComponent(jcbMunicipio, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
    //             .addComponent(lblBachillerato)
    //             .addComponent(jcbBachillerato, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
    //             .addComponent(lblPromBach)
    //             .addComponent(txtPromBach, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE) // Añadir campo de texto para Promedio Bachillerato
    //             .addComponent(lblCuatrimestre)
    //             .addComponent(jcbCuatrimestre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
    //             .addComponent(lblCarrera)
    //             .addComponent(jcbCarrera, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE) // Añadir el JComboBox para Carrera
    //             .addComponent(lblPagado)
    //             .addComponent(txtPagado, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
    //             .addComponent(lblPromGral)
    //             .addComponent(txtPromGral, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE) // Añadir el campo de texto para Promedio
    //             .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
    //             .addGroup(
    //                 layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
    //                     .addComponent(btnAceptar)
    //                     .addComponent(btnCancelar)
    //             )
    //     );
    // }

    private void insertarAlumno() {  
        int rows = 0;
        int id = Integer.parseInt(txtId.getText());
        String nombre = txtNombre.getText();
        String apellidoPaterno = txtApat.getText();
        String apellidoMaterno = txtAmat.getText();
        String domicilio = txtDomicilio.getText();
        //String municipio = jcbMunicipio.getText();
        int bachillerato = 1;
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
            Alumno alumno = new Alumno(id, nombre, apellidoPaterno, apellidoMaterno, domicilio, "Huamantla", bachillerato, promBach, genero, cuatrimestre, pagado, idCarrera, 0.0);
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
            //txtMunicipio.setText("");
            
        }
    
    }
    
}
