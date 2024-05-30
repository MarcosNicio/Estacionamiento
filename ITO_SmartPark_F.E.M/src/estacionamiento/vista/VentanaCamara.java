/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package estacionamiento.vista;

import estacionamiento.modelo.ReconocedorDeTexto;
import estacionamiento.modelo.ProcesadorDeImagenes;
import estacionamiento.modelo.ConexionBaseDeDatos;
import estacionamiento.controlador.ControladorCamara;


import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.sql.SQLException;

/**
 *
 * @author Fernando Cruz
 */
public class VentanaCamara extends javax.swing.JFrame {

    static {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }
    
    private MapaEstacionamiento mapaEstacionamiento;
    private ControladorCamara controladorCamara;
    private ReconocedorDeTexto reconocedorDeTexto;
    private Mat currentFrame;
    private volatile boolean running;
    
    public VentanaCamara(MapaEstacionamiento mapaEstacionamiento) {
        this.mapaEstacionamiento = mapaEstacionamiento;
        initComponents();
        setLocationRelativeTo(null); 
        controladorCamara = new ControladorCamara();
        reconocedorDeTexto = new ReconocedorDeTexto();

        if (!controladorCamara.isCameraOpened()) {
            System.out.println("Error al abrir la cámara");
            return;
        }
        
        startCamera();
        
        // Añadimos el WindowListener
    addWindowListener(new java.awt.event.WindowAdapter() {
        @Override
        public void windowClosing(java.awt.event.WindowEvent windowEvent) {
            stopCamera();
        }
    });
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btnCapturar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        btnReconocer = new javax.swing.JButton();
        txtMostrar = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Reconocimiento de Placas");
        setAlwaysOnTop(true);
        setResizable(false);

        jPanel1.setToolTipText("Reconocimiento de Placas");
        jPanel1.setAutoscrolls(true);

        btnCapturar.setText("Capturar");
        btnCapturar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapturarActionPerformed(evt);
            }
        });

        jLabel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        btnReconocer.setText("Reconocer");
        btnReconocer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReconocerActionPerformed(evt);
            }
        });

        txtMostrar.setEditable(false);
        txtMostrar.setFont(new java.awt.Font("Segoe UI", 0, 32)); // NOI18N
        txtMostrar.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtMostrar.setText("No Placa....");
        txtMostrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMostrarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(btnReconocer)
                .addGap(18, 18, 18)
                .addComponent(btnCapturar)
                .addGap(40, 40, 40)
                .addComponent(txtMostrar, javax.swing.GroupLayout.PREFERRED_SIZE, 368, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 435, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtMostrar, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnReconocer)
                            .addComponent(btnCapturar)))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 666, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtMostrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMostrarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMostrarActionPerformed

    private void btnReconocerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReconocerActionPerformed
        // TODO add your handling code here:
        reconocerPlaca();
    }//GEN-LAST:event_btnReconocerActionPerformed

    private void btnCapturarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapturarActionPerformed
        // TODO add your handling code here:
        capturarPlaca();
    }//GEN-LAST:event_btnCapturarActionPerformed

     private void startCamera() {
        running = true;
        Thread cameraThread = new Thread(() -> {
            while (running) {
                currentFrame = controladorCamara.getFrame();
                if (currentFrame != null) {
                    int rectX = 80;
                    int rectY = 90;
                    int rectWidth = 460;
                    int rectHeight = 140;
                    Imgproc.rectangle(currentFrame, new Point(rectX, rectY), new Point(rectX + rectWidth, rectY + rectHeight), new Scalar(0, 0, 255), 2);

                    BufferedImage image = ProcesadorDeImagenes.Mat2BufferedImage(currentFrame);
                    ImageIcon imageIcon = new ImageIcon(image);
                    jLabel1.setIcon(imageIcon);
                    jLabel1.repaint();
                }
                try {
                    Thread.sleep(15);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        cameraThread.setDaemon(true);
        cameraThread.start();
    }

    private void reconocerPlaca() {
    if (currentFrame != null) {
        int rectX = 80;
        int rectY = 90;
        int rectWidth = 460;
        int rectHeight = 140;
        Mat roi = new Mat(currentFrame, new Rect(rectX, rectY, rectWidth, rectHeight));
        Mat contrastada = ProcesadorDeImagenes.mejorarContraste(roi); // Mejorar el contraste antes de reconocer el texto
        String plateText = reconocedorDeTexto.recognizeText(contrastada);
        String tipoVehiculo = reconocedorDeTexto.detectarFormato(plateText);
        txtMostrar.setText(plateText);

        boolean registrado = ConexionBaseDeDatos.esVehiculoRegistrado(plateText);
        boolean enEstacionamiento = ConexionBaseDeDatos.esVehiculoEnEstacionamiento(plateText);

        if (enEstacionamiento) {
            JOptionPane.showMessageDialog(this, "El vehículo ya se encuentra en el estacionamiento.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!registrado) {
            int confirm = JOptionPane.showConfirmDialog(this, "La placa no está registrada. ¿Desea registrarla?", "Placa no registrada", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                try {
                    if (ConexionBaseDeDatos.registrarVehiculo(plateText, tipoVehiculo)) {
                        JOptionPane.showMessageDialog(this, "Vehículo registrado exitosamente.", "Info", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(this, "La placa ya está registrada.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(this, "Error al registrar el vehículo: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                return; // Si el usuario no desea registrarse, no hacemos nada más.
            }
        }

        int idLugar = ConexionBaseDeDatos.obtenerLugarDisponible();
        if (idLugar != -1 && ConexionBaseDeDatos.ocuparLugar(idLugar, plateText, tipoVehiculo)) {
            JOptionPane.showMessageDialog(this, "Vehículo registrado y lugar asignado correctamente.", "Info", JOptionPane.INFORMATION_MESSAGE);
            mapaEstacionamiento.registrarUltimoLugarOcupado(idLugar); //Resaltar el último lugar ocupado
            mapaEstacionamiento.actualizarMapa();
            stopCamera();  // Detenemos la cámara aquí
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "No hay lugares disponibles.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}


    
    private void capturarPlaca() {
        String placa = txtMostrar.getText();
        System.out.println("Placa a capturar: " + placa);
        String tipoVehiculo = ConexionBaseDeDatos.obtenerTipoVehiculo(placa);
        System.out.println("Tipo de Vehículo: " + tipoVehiculo);
        int idLugar = ConexionBaseDeDatos.obtenerLugarDisponible();
        System.out.println("Lugar Disponible: " + idLugar);

        if (idLugar != -1 && ConexionBaseDeDatos.ocuparLugar(idLugar, placa, tipoVehiculo)) {
            System.out.println("Placa Capturada: " + placa);
            System.out.println("Lugar Ocupado: " + idLugar);
            stopCamera();
            dispose();

            mapaEstacionamiento.actualizarMapa();
        } else {
            JOptionPane.showMessageDialog(this, "No hay lugares disponibles.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    
    private void liberarPlaca() {
        String placa = txtMostrar.getText();
        if (ConexionBaseDeDatos.esVehiculoEnEstacionamiento(placa)) {
            double fare = ConexionBaseDeDatos.calcularTarifa(placa);
            int confirm = JOptionPane.showConfirmDialog(this, "El total a pagar es: $" + fare + ". ¿Desea proceder con la salida?", "Pago", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                ConexionBaseDeDatos.liberarLugar(placa);
                JOptionPane.showMessageDialog(this, "Vehículo salido del estacionamiento correctamente.", "Info", JOptionPane.INFORMATION_MESSAGE);
                mapaEstacionamiento.actualizarMapa();
                stopCamera();  // Detenemos la cámara aquí
            }
        } else {
            JOptionPane.showMessageDialog(this, "El vehículo no se encuentra en el estacionamiento.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    
    private void stopCamera() {
    running = false;
    if (controladorCamara != null) {
        controladorCamara.release();
    }
}

    
    /**
     * @param args the command line arguments
     
    public static void main(String[] args) throws UnsupportedLookAndFeelException {
        
        // Establece FlatLaf como Look and Feel
           //UIManager.setLookAndFeel(new FlatLightLaf());
        
        EventQueue.invokeLater(() -> {
            MainFrame mainFrame = new MainFrame();
            mainFrame.setVisible(true);
        });
    }
    */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCapturar;
    private javax.swing.JButton btnReconocer;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField txtMostrar;
    // End of variables declaration//GEN-END:variables
}