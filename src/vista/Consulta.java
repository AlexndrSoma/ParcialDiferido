/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import dao.FiltroDao;
import java.awt.Container;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import modelo.Filtro;

/**
 *
 * @author uca
 */
public class Consulta extends JFrame {

    public JLabel lblNumInscripcion, lblNombre, lblPropietario, lblEdad, lblRaza, lblEstado;

    public JTextField numInscripcion, nombre, propietario, edad;
    public JComboBox raza;

    ButtonGroup estado = new ButtonGroup();
    public JRadioButton no;
    public JRadioButton si;
    public JTable resultados;

    public JPanel table;

    public JButton buscar, eliminar, insertar, limpiar, actualizar;

    private static final int ANCHOC = 130, ALTOC = 30;

    DefaultTableModel tm;

    public Consulta() {
        super("Inventario");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        agregarLabels();
        formulario();
        llenarTabla();
        Container container = getContentPane();
        container.add(lblNumInscripcion);
        container.add(lblNombre);
        container.add(lblPropietario);
        container.add(lblEdad);
        container.add(lblRaza);
        container.add(lblEstado);
        container.add(numInscripcion);
        container.add(nombre);
        container.add(propietario);
        container.add(edad);
        container.add(raza);
        container.add(si);
        container.add(no);
        container.add(buscar);
        container.add(insertar);
        container.add(actualizar);
        container.add(eliminar);
        container.add(limpiar);
        container.add(table);
        setSize(600, 600);
        eventos();

    }

    public final void agregarLabels() {
        lblNumInscripcion = new JLabel("Numero de Inscripcion");
        lblNombre = new JLabel("Nombre");
        lblPropietario = new JLabel("Propietario");
        lblEdad = new JLabel("Edad");
        lblRaza = new JLabel("Raza");
        lblEstado = new JLabel("Estado");
        lblNumInscripcion.setBounds(10, 10, ANCHOC, ALTOC);
        lblRaza.setBounds(10, 60, ANCHOC, ALTOC);
        lblNombre.setBounds(10, 110, ANCHOC, ALTOC);
        lblPropietario.setBounds(10, 160, ANCHOC, ALTOC);
        lblEdad.setBounds(10, 200, ANCHOC, ALTOC);
        lblEstado.setBounds(10, 250, ANCHOC, ALTOC);

    }

    public final void formulario() {

        numInscripcion = new JTextField();
        raza = new JComboBox();
        nombre = new JTextField();
        propietario = new JTextField();
        edad = new JTextField();
        si = new JRadioButton("si", true);
        no = new JRadioButton("no");
        resultados = new JTable();
        buscar = new JButton("Buscar");
        insertar = new JButton("Insertar");
        eliminar = new JButton("Eliminar");
        actualizar = new JButton("Actualizar");
        limpiar = new JButton("Limpiar");

        table = new JPanel();

        raza.addItem("Husky");
        raza.addItem("Pitbull");
        raza.addItem("Spunky");

        estado = new ButtonGroup();
        estado.add(si);
        estado.add(no);

        numInscripcion.setBounds(140, 10, ANCHOC, ALTOC);
        raza.setBounds(140, 60, ANCHOC, ALTOC);
        nombre.setBounds(140, 110, ANCHOC, ALTOC);
        propietario.setBounds(140, 160, ANCHOC, ALTOC);
        edad.setBounds(140, 200, ANCHOC, ALTOC);
        si.setBounds(140, 250, 50, ALTOC);
        no.setBounds(210, 250, 50, ALTOC);

        buscar.setBounds(300, 10, ANCHOC, ALTOC);
        insertar.setBounds(10, 300, ANCHOC, ALTOC);
        actualizar.setBounds(150, 300, ANCHOC, ALTOC);
        eliminar.setBounds(300, 300, ANCHOC, ALTOC);
        limpiar.setBounds(450, 300, ANCHOC, ALTOC);
        resultados = new JTable();
        table.setBounds(10, 360, 500, 200);
        table.add(new JScrollPane(resultados));
    }

    public void llenarTabla() {

        tm = new DefaultTableModel() {
            public Class<?> getColumnClass(int column) {
                switch (column) {
                    case 0:
                        return String.class;

                    case 1:
                        return String.class;

                    case 2:
                        return String.class;

                    case 3:
                        return String.class;
                    case 4:
                        return String.class;

                    default:
                        return Boolean.class;

                }
            }
        };

        tm.addColumn("No Inscripcion");
        tm.addColumn("Nombre");
        tm.addColumn("Propietario");
        tm.addColumn("Edad");
        tm.addColumn("Raza");
        tm.addColumn("Estado");

        FiltroDao fd = new FiltroDao();
        ArrayList<Filtro> filtros = fd.readAll();

        for (Filtro fi : filtros) {
            tm.addRow(new Object[]{fi.getNumInscripcion(), fi.getNombre(), fi.getPropietario(), fi.getEdad(), fi.getRaza(), fi.isEstado()});
        }

        resultados.setModel(tm);
    }

    public void eventos() {

        insertar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                FiltroDao fd = new FiltroDao();
                Filtro f = new Filtro(numInscripcion.getText(), nombre.getText(), propietario.getText(), edad.getText(), raza.getSelectedItem().toString(), si.isSelected());

                if (no.isSelected()) {
                    f.setEstado(false);
                }

                if (fd.create(f)) {
                    JOptionPane.showMessageDialog(null, "filtro registrado con exito");
                    limpiarCampos();
                    llenarTabla();
                } else {
                    JOptionPane.showMessageDialog(null, "Ocurrio un problema al momento de crear el filtro");

                }
            }

        });

        actualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {

                FiltroDao fd = new FiltroDao();
                Filtro f = new Filtro(numInscripcion.getText(), nombre.getText(), propietario.getText(), edad.getText(), raza.getSelectedItem().toString(), si.isSelected());

                if (no.isSelected()) {
                    f.setEstado(false);
                }

                if (fd.update(f)) {
                    JOptionPane.showMessageDialog(null, "filtro registrado con exito");
                    limpiarCampos();
                    llenarTabla();
                } else {
                    JOptionPane.showMessageDialog(null, "Ocurrio un problema al momento de crear el filtro");

                }

            }
        });

        eliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                FiltroDao fd = new FiltroDao();
                if (fd.delete(numInscripcion.getText())) {
                    JOptionPane.showMessageDialog(null, "FIltro eliminado");
                    limpiarCampos();
                    llenarTabla();

                } else {
                    JOptionPane.showMessageDialog(null, "Ocurrio un problema");

                }
            }

        });

       
        buscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {

                FiltroDao fd = new FiltroDao();
                Filtro f = fd.read(numInscripcion.getText());
                System.out.println(f);
              if (f == null) {
                    JOptionPane.showMessageDialog(null, "El filtto buscado no se ha encontrado");
                } else {
                    numInscripcion.setText(f.getNumInscripcion());
                    raza.setSelectedItem(f.getRaza());
                    nombre.setText(f.getNombre());

                    if (f.isEstado()) {
                        si.setSelected(true);
                    } else {
                        no.setSelected(true);
                    }
                }
            }

        });

        limpiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {

                limpiarCampos();
            }

        });
    }

    public void limpiarCampos() {
        numInscripcion.setText("");
        raza.setSelectedItem("FRAM");
        nombre.setText("");
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Consulta().setVisible(true);
            }
        });
    }

}
