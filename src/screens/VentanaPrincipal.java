package screens;

import java.sql.Connection;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class VentanaPrincipal extends JFrame{
    private Connection conn; // Conexión a la base de datos

    public JDesktopPane desktop;  // Escritorio dentro del Frame
    private JMenuBar jMenuBar;
    private JMenu jMenuAlumnos;
    private JMenu jMenuCarreras;
    private JMenu jMenuBachilleratos;
    private JMenuItem jMenuItemInsertar;
    private JMenuItem jMenuItemVerAlumnos;
    private JMenuItem jMenuItemInsertarCarreras;
    private JMenuItem jMenuItemVerCarreras;
    private JMenuItem jMenuItemInsertarBachillerato;
    private JMenuItem jMenuItemVerBachilleratos;

    public VentanaPrincipal(String title, Connection conn) {
        this.conn = conn; // Guardar la conexión a la base de datos
        this.setTitle(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initComponents();
    }

    private void initComponents(){
        // DesktopPane - escritorio
        desktop = new JDesktopPane();
        this.add(desktop);
        this.setContentPane(desktop); 
        // Barra de Menu
        jMenuBar = new JMenuBar();
        
        // Menu Alumnos
        jMenuAlumnos = new JMenu();
        jMenuAlumnos.setText("Alumnos");
        // Elemento de menu: Insertar
        jMenuItemInsertar = new JMenuItem();
        jMenuItemInsertar.setText("Insertar...");
        // Elemento de menu: Ver alumnos
        jMenuItemVerAlumnos = new JMenuItem();
        jMenuItemVerAlumnos.setText("Ver todos los alumnos...");

        // Menú Carreras
        jMenuCarreras = new JMenu();
        jMenuCarreras.setText("Carreras");
        // Elemento de menu: Ver Carreras
        jMenuItemVerCarreras = new JMenuItem();
        jMenuItemVerCarreras.setText("Ver todas las carreras...");
        // Elemento de menu: Insertar carrera
        jMenuItemInsertarCarreras = new JMenuItem();
        jMenuItemInsertarCarreras.setText("Insertar...");

        // Menú Bachilleratos
        jMenuBachilleratos = new JMenu();
        jMenuBachilleratos.setText("Bachilleratos");
        // Elemento de menu: Ver Bachilleratos
        jMenuItemVerBachilleratos = new JMenuItem();
        jMenuItemVerBachilleratos.setText("Ver todos los bachilleratos...");
        // Elemento de menu: Insertar Bachillerato
        jMenuItemInsertarBachillerato = new JMenuItem();
        jMenuItemInsertarBachillerato.setText("Insertar...");

        //Agregar el listener de eventos para ejecutar la acción de insertar carrera
        jMenuItemInsertarCarreras.addActionListener(e -> insertarCarreras());
        jMenuItemVerCarreras.addActionListener(e -> verCarreras());
        jMenuItemInsertarBachillerato.addActionListener(e -> insertarBachillerato());


        // Agregar menuItem a menu 
        jMenuAlumnos.add(jMenuItemVerAlumnos);
        jMenuAlumnos.add(jMenuItemInsertar);
        jMenuBar.add(jMenuAlumnos);

        jMenuCarreras.add(jMenuItemVerCarreras);
        jMenuCarreras.add(jMenuItemInsertarCarreras);
        jMenuBar.add(jMenuCarreras);

        jMenuBachilleratos.add(jMenuItemVerBachilleratos);
        jMenuBachilleratos.add(jMenuItemInsertarBachillerato);  
        jMenuBar.add(jMenuBachilleratos);

        this.setJMenuBar(jMenuBar);
        pack();
    }

    private void insertarCarreras(){
        //1. Crear un objeto tipo JInternalFrame
        JInternalFrameInsertarCarrera insertarCarrera = new JInternalFrameInsertarCarrera(this.conn);
        
        //2. Agregar el internal frame al escritorio(desktop)
        this.desktop.add(insertarCarrera);
        
        //3. Hacer visible el internal frame
        insertarCarrera.setVisible(true);
    }

    private void insertarBachillerato(){
        JInternalFrameInsertarBachillerato insertarBachillerato = new JInternalFrameInsertarBachillerato();
        this.desktop.add(insertarBachillerato); 
        insertarBachillerato.setVisible(true);
    }

    private void verCarreras(){
        JInternalFrameVerCarreras verCarreras = new JInternalFrameVerCarreras(this.conn);
        this.desktop.add(verCarreras); 
        verCarreras.setVisible(true);
    }

    
}
