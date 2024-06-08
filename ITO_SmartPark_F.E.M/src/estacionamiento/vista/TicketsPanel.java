/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package estacionamiento.vista;

import javax.swing.*;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;



import java.awt.Desktop;

import java.io.FileOutputStream;
import java.io.IOException;

import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.LineSeparator;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.HorizontalAlignment;
import com.itextpdf.barcodes.Barcode128;
import com.itextpdf.barcodes.BarcodeQRCode;

import com.itextpdf.kernel.geom.PageSize;

import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.pdf.canvas.draw.SolidLine;
import java.awt.BorderLayout;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.apache.pdfbox.rendering.PDFRenderer;


import java.awt.image.BufferedImage;
import java.io.File;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import org.apache.pdfbox.pdmodel.PDDocument;




public class TicketsPanel extends javax.swing.JPanel {

    private String placa;
    private String tipoVehiculo;
    private int numeroLugar;
    private Date horaEntrada;
    private long duracion;
    private double tarifa;
    private String modoPago;
    private ByteArrayOutputStream byteArrayOutputStream;
    private BufferedImage image;
    
    
    public TicketsPanel(String placa, String tipoVehiculo, int numeroLugar, Date horaEntrada, long duracion, double tarifa, String modoPago) {
        initComponents();
        
        this.placa = placa;
        this.tipoVehiculo = tipoVehiculo;
        this.numeroLugar = numeroLugar;
        this.horaEntrada = horaEntrada;
        this.duracion = duracion;
        this.tarifa = tarifa;
        this.modoPago = modoPago;
        
        
        
         generarTicketPDF();
         
    }
    
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnGuardarPDF = new javax.swing.JButton();
        btnImprimir = new javax.swing.JButton();
        btnEnviarCorreo = new javax.swing.JButton();
        label = new javax.swing.JLabel();

        setToolTipText("Generar Ticket");
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnGuardarPDF.setText("Guardar PDF");
        btnGuardarPDF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarPDFActionPerformed(evt);
            }
        });
        add(btnGuardarPDF, new org.netbeans.lib.awtextra.AbsoluteConstraints(279, 552, 101, -1));

        btnImprimir.setText("Imprimir");
        btnImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImprimirActionPerformed(evt);
            }
        });
        add(btnImprimir, new org.netbeans.lib.awtextra.AbsoluteConstraints(66, 552, -1, -1));

        btnEnviarCorreo.setText("Enviar Correo");
        btnEnviarCorreo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEnviarCorreoActionPerformed(evt);
            }
        });
        add(btnEnviarCorreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 552, -1, -1));

        label.setOpaque(true);
        add(label, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 410, 520));
    }// </editor-fold>//GEN-END:initComponents

    private void btnImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimirActionPerformed
        imprimirTicket();
    }//GEN-LAST:event_btnImprimirActionPerformed

    private void btnGuardarPDFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarPDFActionPerformed
        guardarTicketAutomaticamente();
    }//GEN-LAST:event_btnGuardarPDFActionPerformed

    private void btnEnviarCorreoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEnviarCorreoActionPerformed
        String destinatario = JOptionPane.showInputDialog(this, "Introduce la dirección de correo electrónico del destinatario:");
        if (destinatario != null && !destinatario.isEmpty()) {
            enviarCorreo(destinatario);
        }
    }//GEN-LAST:event_btnEnviarCorreoActionPerformed

    private double calcularTarifa(long duracion) {
        long minutos = duracion / 1000 / 60;
        long horas = minutos / 60;
        minutos = minutos % 60;

        double tarifaPorHora = 10.0;
        double tarifaTotal = horas * tarifaPorHora;

        if (minutos > 30) {
            tarifaTotal += tarifaPorHora;
        } else if (minutos > 0) {
            tarifaTotal += tarifaPorHora / 2;
        }

        return tarifaTotal;
    }



     private void generarTicketPDF(){
        byteArrayOutputStream = new ByteArrayOutputStream();
        PdfWriter writer = new PdfWriter(byteArrayOutputStream);
        PdfDocument pdf = new PdfDocument(writer);
        pdf.setDefaultPageSize(PageSize.A6);
        Document document = new Document(pdf);
        document.setMargins(20, 20, 20, 20);

        try {
            Image logo = new Image(ImageDataFactory.create("C:\\Users\\Fercr\\Downloads\\Logo.png"))
                .setWidth(60)
                .setHeight(60)
                .setHorizontalAlignment(HorizontalAlignment.CENTER);
            document.add(logo);

            document.add(new Paragraph("SMARTPARK ITO")
                .setTextAlignment(TextAlignment.CENTER)
                .setBold()
                .setFontSize(12));
            document.add(new Paragraph("Oaxaca, México")
                .setTextAlignment(TextAlignment.CENTER)
                .setFontSize(10));

            document.add(new LineSeparator(new SolidLine(1f)));

            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            document.add(new Paragraph("Desde: " + dateFormat.format(horaEntrada))
                .setTextAlignment(TextAlignment.CENTER));
            document.add(new Paragraph("Hasta: " + dateFormat.format(new Date()))
                .setTextAlignment(TextAlignment.CENTER));
            document.add(new Paragraph("Placa: " + placa)
                .setTextAlignment(TextAlignment.CENTER));

            document.add(new Paragraph("Pagado: $" + tarifa)
                .setTextAlignment(TextAlignment.CENTER));
            document.add(new LineSeparator(new SolidLine(1f)));

            
            Random random = new Random();
            String barcodeCode = "" + random.nextInt(1000000); //Genera un número aleatorio y lo añade como prefijo "PLACA-"
            
            Barcode128 barcode128 = new Barcode128(pdf);
            barcode128.setCodeType(Barcode128.CODE128);
            barcode128.setCode(barcodeCode);
            Image barcodeImage = new Image(barcode128.createFormXObject(pdf));
            barcodeImage.setWidth(130);
            barcodeImage.setHorizontalAlignment(HorizontalAlignment.CENTER);
            barcodeImage.setMarginTop(10);
            document.add(barcodeImage);
            
            
            String qrContent = String.format(
                "Informacion del Ticket:\n" +
            "-------------------\n" +
            "Placa: %s\n" +
            "Tipo de Vehículo: %s\n" +
            "Número de Lugar: %d\n" +
            "Hora de Entrada: %s\n" +
            "Tiempo Transcurrido: %s\n" +
            "Tarifa por Hora: $%.2f\n" +
            "Costo Total: $%.2f\n" +
            "Modo de Pago: %s\n",
                placa, tipoVehiculo, numeroLugar, dateFormat.format(horaEntrada), formatoHora(duracion), 10.00, tarifa, modoPago
            );
            
            BarcodeQRCode qrCode = new BarcodeQRCode(qrContent);
            Image qrCodeImage = new Image(qrCode.createFormXObject(null, pdf));
            qrCodeImage.setWidth(40);  // Reduciendo el tamaño del QR a 35
            qrCodeImage.setHeight(40);
            qrCodeImage.setHorizontalAlignment(HorizontalAlignment.CENTER);
            document.add(qrCodeImage);

            document.add(new Paragraph("¡Gracias por su visita!")
                .setTextAlignment(TextAlignment.CENTER)
                .setItalic());
            
        document.close(); // Cierre el documento antes de escribir el byteArray en un archivo
        writer.close();

        // Guarda el PDF temporalmente para previsualización
        File tempFile = File.createTempFile("preview", ".pdf");
        try (FileOutputStream fos = new FileOutputStream(tempFile)) {
            fos.write(byteArrayOutputStream.toByteArray());
        }

        if (tempFile.exists() && tempFile.length() > 0) {
            mostrarPDFEnPanel(tempFile.getAbsolutePath());
        } else {
            System.out.println("El archivo PDF temporal está vacío o no existe.");
        }

        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            if (pdf != null) {
            pdf.close(); // Asegúrate de cerrar el objeto PdfDocument para liberar recursos.
            
            }
        }
        
    }    
     
     
    private void mostrarPDFEnPanel(String filePath) {
    if (filePath == null || filePath.isEmpty()) {
        System.out.println("Ruta de archivo no proporcionada o vacía.");
        return;
    }

    File file = new File(filePath);
    if (!file.exists() || file.length() == 0) {
        System.out.println("Archivo no encontrado o está vacío.");
        return;
    }

    try {
        PDDocument document = PDDocument.load(file);
        PDFRenderer renderer = new PDFRenderer(document);
        BufferedImage image = renderer.renderImageWithDPI(0, 500); // Renderiza la primera página con DPI 500
        
        // Obtener las dimensiones de la imagen original
        int originalWidth = image.getWidth();
        int originalHeight = image.getHeight();

        // Calcular las dimensiones deseadas para tamaño A6 (105 x 148 mm)
        double desiredWidth = 400; // Puedes ajustar este valor según tus necesidades
        double desiredHeight = (desiredWidth / originalWidth) * originalHeight;
        
        // Escalar la imagen al tamaño deseado
        BufferedImage scaledImage = new BufferedImage((int) desiredWidth, (int) desiredHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = scaledImage.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2d.drawImage(image, 0, 0, (int) desiredWidth, (int) desiredHeight, null);
        g2d.dispose();

        ImageIcon icon = new ImageIcon(scaledImage);
        
        label.setIcon(icon);

        //panel.removeAll();
        //panel.setLayout(new BorderLayout());
        //panel.add(label, BorderLayout.CENTER);
        //panel.revalidate();
        //panel.repaint();
        document.close();
    } catch (IOException e) {
        e.printStackTrace();
        System.out.println("Error al cargar el PDF: " + e.getMessage());
    }
}



    private int getTicketNumber() {
        int ticketNumber = 1;
        File dir = new File("C:\\Users\\Fercr\\Documents\\TKEstacionamiento");
        if (!dir.exists()) {
            dir.mkdirs();
        } else {
            File[] files = dir.listFiles((d, name) -> name.startsWith("EstacionamientoTicket_") && name.endsWith(".pdf"));
            if (files != null) {
                ticketNumber = files.length + 1;
            }
        }
        return ticketNumber;
    }

    private void guardarTicketAutomaticamente() {
        try {
            int ticketNumber = getTicketNumber();
            String filePath = "C:\\Users\\Fercr\\Documents\\TKEstacionamiento\\EstacionamientoTicket_" + String.format("%02d", ticketNumber) + ".pdf";
            try (FileOutputStream fos = new FileOutputStream(filePath)) {
                byteArrayOutputStream.writeTo(fos);
            }

            JOptionPane.showMessageDialog(this, "PDF guardado exitosamente en: " + filePath);

            // Mostrar la carpeta donde se guardó el archivo
            Desktop.getDesktop().open(new File("C:\\Users\\Fercr\\Documents\\TKEstacionamiento"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void imprimirTicket() {
        try {
            File tempFile = File.createTempFile("ticket_", ".pdf");
            try (FileOutputStream fos = new FileOutputStream(tempFile)) {
                byteArrayOutputStream.writeTo(fos);
            }
            byteArrayOutputStream.flush();

            if (Desktop.isDesktopSupported()) {
                Desktop desktop = Desktop.getDesktop();
                if (desktop.isSupported(Desktop.Action.PRINT)) {
                    desktop.print(tempFile);
                } else {
                    JOptionPane.showMessageDialog(this, "La acción de impresión no es soportada en este sistema.");
                }
            } else {
                JOptionPane.showMessageDialog(this, "El sistema no soporta acciones de escritorio.");
            }
        } catch (IOException e) {
            e.printStackTrace();
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

     private void enviarCorreo(String correoDestinatario) {
        String correoenvia = "raphtiparking@gmail.com";
        String contrasenia = "AOMXYESWXCDXOMZB";
        String mensaje = "Adjunto encontrará su ticket de estacionamiento.";

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.user", correoenvia);
        props.put("mail.smtp.auth", "true");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(correoenvia, contrasenia);
            }
        });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(correoenvia));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(correoDestinatario));
            message.setSubject("Ticket de Estacionamiento");
            message.setText(mensaje);

            // Crear el contenido HTML
            Multipart multipart = new MimeMultipart("mixed");

            MimeBodyPart messageBodyPart1 = new MimeBodyPart();
            messageBodyPart1.setContent("<div style=\"background-color: #004080; color: #fff; padding: 10px 20px; border-radius: 8px 8px 0 0;\"> <h1 style=\"margin: 0; font-size: 24px; text-align: center;\">Ticket de Estacionamiento</h1></div>", "text/html");
            multipart.addBodyPart(messageBodyPart1);

            MimeBodyPart messageBodyPart2 = new MimeBodyPart();
            messageBodyPart2.setContent("<div style=\"text-align: center;\"><p>Estimado cliente de RaphtiParking,</p><p>¡Gracias por elegir nuestro servicio de estacionamiento! Valoramos tu confianza y estamos comprometidos a brindarte una experiencia segura y conveniente. Siempre estamos aquí para ayudarte con tus necesidades de estacionamiento.</p><p>¡Esperamos verte pronto en RaphtiParking!</p><p>Atentamente,<br>El equipo de RaphtiParking</p></div>", "text/html");
            multipart.addBodyPart(messageBodyPart2);

            MimeBodyPart messageBodyPart3 = new MimeBodyPart();
            String logoHTML = "<div style=\"text-align: center;\"><img src=\"cid:logo\" alt=\"Logotipo\" style=\"max-width: 100px; display: block; margin: 0 auto;\"></div>";
            messageBodyPart3.setContent(logoHTML, "text/html");
            multipart.addBodyPart(messageBodyPart3);

            // Agregar logotipo
            MimeBodyPart logoPart = new MimeBodyPart();
            DataSource logoDataSource = new FileDataSource("C:\\Users\\Fercr\\Downloads\\Logo.png");
            logoPart.setDataHandler(new DataHandler(logoDataSource));
            logoPart.setHeader("Content-ID", "<logo>");
            multipart.addBodyPart(logoPart);

            MimeBodyPart messageBodyPart4 = new MimeBodyPart();
            messageBodyPart4.setContent("<div style=\"text-align: center; padding: 10px; font-size: 12px; color: #777; border-top: 1px solid #ddd; margin-top: 20px;\"><p>&copy; 2024 Servicio de Estacionamiento. Todos los derechos reservados.</p></div>", "text/html");
            multipart.addBodyPart(messageBodyPart4);

            // Parte del archivo adjunto
            MimeBodyPart attachPart = new MimeBodyPart();
            String filePath = "C:\\Users\\Fercr\\Documents\\TKEstacionamiento\\EstacionamientoTicket_" + String.format("%02d", getTicketNumber() - 1) + ".pdf";
            attachPart.attachFile(filePath);
            multipart.addBodyPart(attachPart);

            message.setContent(multipart);

            Transport.send(message);

            JOptionPane.showMessageDialog(this, "Correo enviado correctamente.");
        } catch (MessagingException | IOException e) {
            JOptionPane.showMessageDialog(this, "Error al enviar el correo: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEnviarCorreo;
    private javax.swing.JButton btnGuardarPDF;
    private javax.swing.JButton btnImprimir;
    private javax.swing.JLabel label;
    // End of variables declaration//GEN-END:variables
}