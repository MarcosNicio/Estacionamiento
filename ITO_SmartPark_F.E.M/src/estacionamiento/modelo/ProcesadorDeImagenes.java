/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package estacionamiento.modelo;

import org.opencv.core.Mat;
import org.opencv.core.CvType;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;

public class ProcesadorDeImagenes {
    public static BufferedImage Mat2BufferedImage(Mat mat) {
        int type = BufferedImage.TYPE_BYTE_GRAY;
        if (mat.channels() > 1) {
            type = BufferedImage.TYPE_3BYTE_BGR;
        }
        BufferedImage image = new BufferedImage(mat.cols(), mat.rows(), type);
        final byte[] targetPixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
        mat.get(0, 0, targetPixels);
        return image;
    }
    
    public static Mat preprocesarImagen(Mat mat) {
        Mat gray = new Mat();
        Mat blur = new Mat();
        Mat binary = new Mat();
        
        // Convertir a escala de grises
        Imgproc.cvtColor(mat, gray, Imgproc.COLOR_BGR2GRAY);
        
        // Aplicar desenfoque para reducir el ruido
        Imgproc.GaussianBlur(gray, blur, new Size(5, 5), 0);
        
        // Aplicar umbralización
        Imgproc.adaptiveThreshold(blur, binary, 255, Imgproc.ADAPTIVE_THRESH_GAUSSIAN_C, Imgproc.THRESH_BINARY, 11, 2);
        
        return binary;
    }
    
    // Método para mejorar el contraste de la imagen
    public static Mat mejorarContraste(Mat mat) {
        Mat contrastada = new Mat();
        mat.convertTo(contrastada, -1, 1.5, 0); // Aumenta el contraste
        return contrastada;
    }
}
