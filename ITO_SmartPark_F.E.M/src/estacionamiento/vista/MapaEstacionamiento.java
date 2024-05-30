/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package estacionamiento.vista;


import estacionamiento.modelo.VehiculoInfo;
import estacionamiento.modelo.ConexionBaseDeDatos;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.Timer;


/**
 *
 * @author Fernando Cruz
 */
public class MapaEstacionamiento extends javax.swing.JPanel {

    private Map<Integer, JLabel> lugares;
    private Map<Integer, VehiculoInfo> vehiculos;
    private int ultimoLugarOcupado; //Variable para rastrear el ultimo lugar ocupado
    private Timer timer; // Temporizador para actualizar el mapa

    public MapaEstacionamiento() {
        initComponents();
        lugares = new HashMap<>();
        vehiculos = new HashMap<>();
        ultimoLugarOcupado = -1; // Inicialmente ningún lugar está 
        inicializarLugares();
        actualizarMapa();
        
        // Inicializar el temporizador para actualizar el mapa cada segundo
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarMapa();
            }
        });
        timer.start();
        
    }
    
    
    private void inicializarLugares() {
        // Añadir los JLabels a lugares map y configurar el MouseListener
        lugares.put(1, jLabel1);
        lugares.put(2, jLabel2);
        lugares.put(3, jLabel3);
        lugares.put(4, jLabel4);
        lugares.put(5, jLabel5);
        lugares.put(6, jLabel6);
        lugares.put(7, jLabel7);
        lugares.put(8, jLabel8);
        lugares.put(9, jLabel9);
        lugares.put(10, jLabel10);
        lugares.put(11, jLabel11);
        lugares.put(12, jLabel12);
        lugares.put(13, jLabel13);
        lugares.put(14, jLabel14);
        lugares.put(15, jLabel15);
        lugares.put(16, jLabel16);
        lugares.put(17, jLabel17);
        lugares.put(18, jLabel18);
        lugares.put(19, jLabel19);
        lugares.put(20, jLabel20);
        lugares.put(21, jLabel21);
        lugares.put(22, jLabel22);
        lugares.put(23, jLabel23);
        lugares.put(24, jLabel24);
        lugares.put(25, jLabel25);

        for (Map.Entry<Integer, JLabel> entry : lugares.entrySet()) {
            JLabel label = entry.getValue();
            int spotId = entry.getKey();
            label.setOpaque(true);
            label.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    mostrarInformacionVehiculo(spotId);
                }
            });
        }
    }

    
    public void actualizarMapa() {
        vehiculos.clear();

        for (int i = 1; i <= 25; i++) {
            VehiculoInfo info = ConexionBaseDeDatos.obtenerInfoVehiculo(i);
            JLabel label = lugares.get(i);
            label.setForeground(Color.BLACK); // Establecer el color del texto a negro
            if (info != null) {
                vehiculos.put(i, info);
                if (i == ultimoLugarOcupado){
                    label.setBackground(Color.ORANGE); //Resaltar el último lugar ocupado
                }else{
                    label.setBackground(Color.RED);
                }
                long duracionMilis = System.currentTimeMillis() - info.getHoraEntrada().getTime();
                String formatoHora = formatoHora(duracionMilis);
                label.setText(String.format("<html><div style='text-align: center;'>Lugar <span style='color: black; font-weight: bold;'>%d</span><br>Placa: %s<br>Tiempo: %s</div></html>", i, info.getPlaca(), formatoHora));
            } else {
                label.setBackground(Color.GREEN);
                label.setHorizontalAlignment(JLabel.CENTER);
                label.setText(String.format("<html><div style='text-align: center;'>Lugar <span style='color: black; font-weight: bold;'>%d</span></div></html>", i));
            }
        }
    }
    
    private String formatoHora(long duracionMilis) {
        long segundos = duracionMilis / 1000;
        long minutos = segundos / 60;
        long horas = minutos / 60;
        long dias = horas / 24;

        segundos = segundos % 60;
        minutos = minutos % 60;
        horas = horas % 24;

        return String.format("%d días, %02d:%02d:%02d", dias, horas, minutos, segundos);
    }


    private void mostrarInformacionVehiculo(int spotId) {
        VehiculoInfo info = vehiculos.get(spotId);
        if (info == null) {
            JOptionPane.showMessageDialog(this, "No hay vehículo en este lugar.", "Información", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        DialogoVehiculo dialog = new DialogoVehiculo(null, true, info);
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);

        actualizarMapa();
    }
    
    public void registrarUltimoLugarOcupado(int idLugar) {
        ultimoLugarOcupado = idLugar;
        repaint();
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
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();

        setLayout(new java.awt.GridLayout(5, 10, 5, 5));

        jLabel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });
        add(jLabel1);

        jLabel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });
        add(jLabel2);

        jLabel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });
        add(jLabel3);

        jLabel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        add(jLabel4);

        jLabel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        add(jLabel5);

        jLabel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        add(jLabel6);

        jLabel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        add(jLabel7);

        jLabel8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        add(jLabel8);

        jLabel9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        add(jLabel9);

        jLabel10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        add(jLabel10);

        jLabel11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        add(jLabel11);

        jLabel12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        add(jLabel12);

        jLabel13.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        add(jLabel13);

        jLabel14.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        add(jLabel14);

        jLabel15.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        add(jLabel15);

        jLabel16.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        add(jLabel16);

        jLabel17.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        add(jLabel17);

        jLabel18.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        add(jLabel18);

        jLabel19.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        add(jLabel19);

        jLabel20.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        add(jLabel20);

        jLabel21.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        add(jLabel21);

        jLabel22.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        add(jLabel22);

        jLabel23.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        add(jLabel23);

        jLabel24.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        add(jLabel24);

        jLabel25.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        add(jLabel25);
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel1MouseClicked

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel2MouseClicked

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel3MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    // End of variables declaration//GEN-END:variables
}
