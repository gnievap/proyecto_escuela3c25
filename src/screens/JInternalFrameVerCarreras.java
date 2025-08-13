package screens;

import java.awt.Font;
import java.sql.Connection;
import java.util.ArrayList;

import javax.swing.GroupLayout;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import models.Carrera;
import models.CarreraDAO;

public class JInternalFrameVerCarreras extends JInternalFrame {
    private JLabel lblTitulo;
    private JTable tableCarreras;
    private JScrollPane scrollPane;
    private DefaultTableModel tableModel;
    private Connection conn;
    private ArrayList<Carrera> carreras;

   public JInternalFrameVerCarreras(Connection conn) {
        super("Ver Carreras", 
              true,  // resizable
              true,  // closable
              true,  // maximizable
              true); // iconifiable (minimizable)
        this.conn = conn; // Inicializar la conexión a la base de datos
        this.setTitle("Lista de Carreras");
        this.setSize(600, 400);
        initComponents();
    }

    private void initComponents() {
        lblTitulo = new JLabel("Listado de Carreras");
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 16));

        // Aquí se debería cargar la lista de carreras desde la base de datos
        // y crear el JTable con los datos obtenidos.
        String[] columnNames = {"ID Carrera", "Nombre", "Monto", " ", " "};
        tableModel = new DefaultTableModel(columnNames, 0);
        tableCarreras = new JTable(tableModel); // Placeholder para el JTable
        scrollPane = new JScrollPane(tableCarreras);


        rellenarTabla();
        tableCarreras.setModel(tableModel);
        tableModel.fireTableDataChanged();

        GroupLayout layout = new GroupLayout(getContentPane());
        this.setLayout(layout);

        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(lblTitulo)
                .addComponent(scrollPane)
        );

        layout.setVerticalGroup(
            layout.createSequentialGroup()
                .addComponent(lblTitulo)
                .addComponent(scrollPane)
        );

        this.pack();
    }

    public void rellenarTabla(){
        CarreraDAO carreraDAO = new CarreraDAO(conn);
        carreras = carreraDAO.obtenerCarreras();
        tableModel.setRowCount(0);
        //Añadir las carreras al modelo de la tabla
        for (Carrera carrera : carreras) {
            Object[] rowData = {
                carrera.getIdcarrera(),
                carrera.getNombre(),
                carrera.getMonto(),
                "Actualizar", // Placeholder para acciones
                "Eliminar"
            };
            tableModel.addRow(rowData);
    }
}        

    
}
