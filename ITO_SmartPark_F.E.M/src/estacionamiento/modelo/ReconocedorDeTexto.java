/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package estacionamiento.modelo;

import estacionamiento.modelo.ProcesadorDeImagenes;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.opencv.core.Mat;

import java.awt.image.BufferedImage;

public class ReconocedorDeTexto {
    private Tesseract tesseract;

    public ReconocedorDeTexto() {
        tesseract = new Tesseract();
        tesseract.setDatapath("C:\\Users\\Fercr\\Downloads\\tessdata");
        tesseract.setLanguage("eng");
        tesseract.setTessVariable("tessedit_char_whitelist", "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789-");
    }

    public String recognizeText(Mat mat) {
        Mat preprocessed = ProcesadorDeImagenes.preprocesarImagen(mat);
        BufferedImage bufferedImage = ProcesadorDeImagenes.Mat2BufferedImage(preprocessed);
        try {
            return tesseract.doOCR(bufferedImage).replaceAll("[^A-Z0-9-]", "");
        } catch (TesseractException e) {
            e.printStackTrace();
            return "";
        }
    }

    public String detectarFormato(String textoPlaca) {
        return TipoDeVehiculo.detectVehicleType(textoPlaca);
    }
}

