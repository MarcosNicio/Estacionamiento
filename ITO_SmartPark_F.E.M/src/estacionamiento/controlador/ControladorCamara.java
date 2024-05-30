/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package estacionamiento.controlador;


import org.opencv.core.Mat;
import org.opencv.videoio.VideoCapture;

public class ControladorCamara {
    private VideoCapture capture;
    private Mat mat;

    public ControladorCamara() {
        capture = new VideoCapture(0); // Abre la primera cámara conectada
        mat = new Mat();
    }

    public boolean isCameraOpened() {
        return capture.isOpened();
    }

    public Mat getFrame() {
        if (capture.read(mat)) {
            return mat;
        }
        return null;
    }

    public void release() {
        capture.release();
    }
}


