/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package estacionamiento.modelo;

public class TipoDeVehiculo {

    public static String detectVehicleType(String placa) {
        if (placa.matches("^[A-Z]{3}-\\d{2}-\\d{2}$")) {
            return "Transporte Privado Automóvil";
        } else if (placa.matches("^\\d{2}-\\d{2}-[A-Z]{3}$")) {
            return "Transporte Público Automóvil";
        } else if (placa.matches("^[A-Z]{2}-\\d{4}-[A-Z]{1}$")) {
            return "Transporte Privado Camión";
        } else if (placa.matches("^[A-Z]{3}\\d{1}[A-Z]{1}$")) {
            return "Transporte Privado Motocicleta";
        } else if (placa.matches("^[A-Z]{2}\\d{1}[A-Z]{2}$")) {
            return "Transporte Público Motocicleta";
        } else if (placa.matches("^[A-Z]{3}\\d{4}$")) {
            return "Automóvil Particular";
        } else if (placa.matches("^[A-Z]{2}\\d{3}[A-Z]{1}$")) {
            return "Motocicleta";
        } else if (placa.matches("^[A-Z]{2}-\\d{4}-[A-Z]{1}$")) {
            return "Remolque";
        } else if (placa.matches("^RT-\\d{4}$")) {
            return "Remolque";
        } else if (placa.matches("^TC-\\d{4}$")) {
            return "Vehículo de Carga";
        } else if (placa.matches("^AG-\\d{4}$")) {
            return "Vehículo Agrícola";
        } else if (placa.matches("^\\d{3}-[A-Z]{2}$")) {
            return "Vehículo Oficial";
        } else {
            return "Formato Desconocido";
        }
    }
}
