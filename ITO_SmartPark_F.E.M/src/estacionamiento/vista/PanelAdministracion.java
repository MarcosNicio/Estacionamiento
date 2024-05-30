/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package estacionamiento.vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

public class PanelAdministracion extends javax.swing.JPanel {

    private Connection conexion;

    
    public PanelAdministracion() {
        initComponents();
         try {
            conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/estacionamiento?useSSL=false", "root", "");
        } catch (Exception e) {
            e.printStackTrace();
        }
        cargarEmpleados();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jScrollPane1 = new javax.swing.JScrollPane();
        modeloTabla = new javax.swing.JTable();
        btnAgregar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();

        setLayout(new java.awt.GridBagLayout());

        modeloTabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Nombre", "Correo", "Contraseña", "Rol", "Fecha de Ingreso"
            }
        ));
        jScrollPane1.setViewportView(modeloTabla);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 572;
        gridBagConstraints.ipady = 395;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(20, 6, 0, 6);
        add(jScrollPane1, gridBagConstraints);

        btnAgregar.setText("Agregar Empleado");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(18, 85, 24, 0);
        add(btnAgregar, gridBagConstraints);

        btnEliminar.setText("Eliminar Empleado");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(18, 12, 24, 0);
        add(btnEliminar, gridBagConstraints);

        btnActualizar.setText("Actualizar Datos");
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(18, 12, 24, 0);
        add(btnActualizar, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        // TODO add your handling code here:
         mostrarDialogoRegistro(false, null); // false indica que no es una actualización, null porque no hay idEmpleado
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        // TODO add your handling code here:
        eliminarEmpleado();
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        // TODO add your handling code here:
        int filaSeleccionada = modeloTabla.getSelectedRow();
        if (filaSeleccionada != -1) {
            int idEmpleado = (int) modeloTabla.getValueAt(filaSeleccionada, 0);
            mostrarDialogoRegistro(true, idEmpleado); // true indica que es una actualización
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un empleado para actualizar.");
        }
    }//GEN-LAST:event_btnActualizarActionPerformed

    
    private void cargarEmpleados() {
        DefaultTableModel modelo = (DefaultTableModel) modeloTabla.getModel();
        modelo.setRowCount(0); // Limpiar tabla antes de cargar datos
        try {
            Statement stmt = conexion.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT id, nombre, correo, contrasena, rol, fecha_registro FROM usuarios");
            while (rs.next()) {
                modelo.addRow(new Object[]{
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getString("correo"),
                    rs.getString("contrasena"),
                    rs.getString("rol"),
                    rs.getTimestamp("fecha_registro")
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void mostrarDialogoRegistro(boolean esActualizacion, Integer idEmpleado) {
        //DialogoRegistro dialogo = new DialogoRegistro(null, true);
        DialogoRegistro dialogo = new DialogoRegistro(new JFrame(), true, esActualizacion, idEmpleado);
        dialogo.setLocationRelativeTo(null);
        dialogo.setVisible(true);

        if (dialogo.isRegistroExitoso()) {
            // Actualizar la tabla después de registrar un nuevo empleado
            cargarEmpleados();
        }
    }
    
    private void eliminarEmpleado() {
        int filaSeleccionada = modeloTabla.getSelectedRow();
        if (filaSeleccionada != -1) {
            int idEmpleado = (int) modeloTabla.getValueAt(filaSeleccionada, 0);
            try {
                // Llamar al procedimiento almacenado para eliminar el usuario y restablecer el AUTO_INCREMENT
                CallableStatement stmt = conexion.prepareCall("{CALL DeleteUserAndResetAI(?)}");
                stmt.setInt(1, idEmpleado);
                stmt.executeUpdate();

                // Actualizar la tabla después de eliminar el empleado
                cargarEmpleados();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un empleado para eliminar.");
        }
    }
    
    /**
    private void eliminarEmpleado() {
        int filaSeleccionada = modeloTabla.getSelectedRow();
        if (filaSeleccionada != -1) {
            int idEmpleado = (int) modeloTabla.getValueAt(filaSeleccionada, 0);
            try {
                PreparedStatement pstmt = conexion.prepareStatement("DELETE FROM usuarios WHERE id = ?");
                pstmt.setInt(1, idEmpleado);
                pstmt.executeUpdate();
                ((DefaultTableModel) modeloTabla.getModel()).removeRow(filaSeleccionada);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un empleado para eliminar.");
        }
    }
    */
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable modeloTabla;
    // End of variables declaration//GEN-END:variables
}