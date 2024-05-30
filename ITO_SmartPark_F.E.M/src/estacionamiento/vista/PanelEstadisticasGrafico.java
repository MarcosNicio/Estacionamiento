/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package estacionamiento.vista;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


public class PanelEstadisticasGrafico extends javax.swing.JPanel {

    private Connection conexion;
    private CardLayout cardLayout;
    
    public PanelEstadisticasGrafico() {
        initComponents();
        
         try {
            conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/estacionamiento?useSSL=false", "root", "");
        } catch (Exception e) {
            e.printStackTrace();
        }

        addCharts();
 
    }
        
    

    private void addCharts() {
        panelOcupacion.setLayout(new BorderLayout());
        panelOcupacion.add(createOcupacionChart(), BorderLayout.CENTER);

        panelHistorialOcupacion.setLayout(new BorderLayout());
        panelHistorialOcupacion.add(createHistorialOcupacionChart(), BorderLayout.CENTER);

        panelUsoTipoVehiculo.setLayout(new BorderLayout());
        panelUsoTipoVehiculo.add(createUsoTipoVehiculoChart(), BorderLayout.CENTER);

        panelRegistroUsuarios.setLayout(new BorderLayout());
        panelRegistroUsuarios.add(createRegistroUsuariosChart(), BorderLayout.CENTER);
    }

    private ChartPanel createOcupacionChart() {
        DefaultPieDataset dataset = new DefaultPieDataset();

        try {
            Statement stmt = conexion.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT COUNT(*) AS total, ocupado FROM lugares GROUP BY ocupado");
            while (rs.next()) {
                String estado = rs.getInt("ocupado") == 1 ? "Ocupado" : "Disponible";
                dataset.setValue(estado, rs.getInt("total"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        JFreeChart chart = ChartFactory.createPieChart(
                "Ocupación Actual",
                dataset,
                true,
                true,
                false
        );

        return new ChartPanel(chart);
    }

    private ChartPanel createHistorialOcupacionChart() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        try {
            Statement stmt = conexion.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT DATE(hora_entrada) AS fecha, COUNT(*) AS ocupacion FROM lugares WHERE ocupado = 1 GROUP BY DATE(hora_entrada)");
            while (rs.next()) {
                dataset.addValue(rs.getInt("ocupacion"), "Ocupación", rs.getDate("fecha"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        JFreeChart chart = ChartFactory.createLineChart(
                "Historial de Ocupación",
                "Fecha",
                "Número de Lugares Ocupados",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        return new ChartPanel(chart);
    }

    private ChartPanel createUsoTipoVehiculoChart() {
        DefaultPieDataset dataset = new DefaultPieDataset();

        try {
            Statement stmt = conexion.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT tipo_vehiculo, COUNT(*) AS total FROM lugares WHERE ocupado = 1 GROUP BY tipo_vehiculo");
            while (rs.next()) {
                dataset.setValue(rs.getString("tipo_vehiculo"), rs.getInt("total"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        JFreeChart chart = ChartFactory.createPieChart(
                "Uso por Tipo de Vehículo",
                dataset,
                true,
                true,
                false
        );

        return new ChartPanel(chart);
    }

    private ChartPanel createRegistroUsuariosChart() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        try {
            Statement stmt = conexion.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT rol, COUNT(*) AS total FROM usuarios GROUP BY rol");
            while (rs.next()) {
                dataset.addValue(rs.getInt("total"), rs.getString("rol"), rs.getString("rol"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        JFreeChart chart = ChartFactory.createBarChart(
                "Registro de Usuarios",
                "Rol",
                "Número de Usuarios",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        return new ChartPanel(chart);
    }
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        opciones = new javax.swing.JComboBox<>();
        contenedor = new javax.swing.JPanel();
        panelOcupacion = new javax.swing.JPanel();
        panelHistorialOcupacion = new javax.swing.JPanel();
        panelUsoTipoVehiculo = new javax.swing.JPanel();
        panelRegistroUsuarios = new javax.swing.JPanel();

        setPreferredSize(new java.awt.Dimension(600, 500));

        jLabel1.setText("Generar estadística:");

        opciones.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Por Ocupacion", "Por Historial de Ocupacion", "Por Uso de Tipo de Vehiculo", "Por Registro de Usuarios" }));
        opciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                opcionesActionPerformed(evt);
            }
        });

        contenedor.setLayout(new java.awt.CardLayout());

        javax.swing.GroupLayout panelOcupacionLayout = new javax.swing.GroupLayout(panelOcupacion);
        panelOcupacion.setLayout(panelOcupacionLayout);
        panelOcupacionLayout.setHorizontalGroup(
            panelOcupacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 604, Short.MAX_VALUE)
        );
        panelOcupacionLayout.setVerticalGroup(
            panelOcupacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 431, Short.MAX_VALUE)
        );

        contenedor.add(panelOcupacion, "Por Ocupacion");

        javax.swing.GroupLayout panelHistorialOcupacionLayout = new javax.swing.GroupLayout(panelHistorialOcupacion);
        panelHistorialOcupacion.setLayout(panelHistorialOcupacionLayout);
        panelHistorialOcupacionLayout.setHorizontalGroup(
            panelHistorialOcupacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 604, Short.MAX_VALUE)
        );
        panelHistorialOcupacionLayout.setVerticalGroup(
            panelHistorialOcupacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 431, Short.MAX_VALUE)
        );

        contenedor.add(panelHistorialOcupacion, "Por Historial de Ocupacion");

        javax.swing.GroupLayout panelUsoTipoVehiculoLayout = new javax.swing.GroupLayout(panelUsoTipoVehiculo);
        panelUsoTipoVehiculo.setLayout(panelUsoTipoVehiculoLayout);
        panelUsoTipoVehiculoLayout.setHorizontalGroup(
            panelUsoTipoVehiculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 604, Short.MAX_VALUE)
        );
        panelUsoTipoVehiculoLayout.setVerticalGroup(
            panelUsoTipoVehiculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 431, Short.MAX_VALUE)
        );

        contenedor.add(panelUsoTipoVehiculo, "Por Uso de Tipo de Vehiculo");

        javax.swing.GroupLayout panelRegistroUsuariosLayout = new javax.swing.GroupLayout(panelRegistroUsuarios);
        panelRegistroUsuarios.setLayout(panelRegistroUsuariosLayout);
        panelRegistroUsuariosLayout.setHorizontalGroup(
            panelRegistroUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 604, Short.MAX_VALUE)
        );
        panelRegistroUsuariosLayout.setVerticalGroup(
            panelRegistroUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 431, Short.MAX_VALUE)
        );

        contenedor.add(panelRegistroUsuarios, "Por Registro de Usuarios");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(162, 162, 162)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(opciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(contenedor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(opciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(contenedor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void opcionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_opcionesActionPerformed
        String selectedItem = (String) opciones.getSelectedItem();
        CardLayout cl = (CardLayout) (contenedor.getLayout());
        cl.show(contenedor, selectedItem);
    }//GEN-LAST:event_opcionesActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel contenedor;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JComboBox<String> opciones;
    private javax.swing.JPanel panelHistorialOcupacion;
    private javax.swing.JPanel panelOcupacion;
    private javax.swing.JPanel panelRegistroUsuarios;
    private javax.swing.JPanel panelUsoTipoVehiculo;
    // End of variables declaration//GEN-END:variables
}
