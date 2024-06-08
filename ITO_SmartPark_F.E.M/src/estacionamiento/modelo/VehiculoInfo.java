/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package estacionamiento.modelo;

import java.sql.Timestamp;

public class VehiculoInfo {
    private String placa;
    private String tipoVehiculo;
    private Timestamp horaEntrada;
    private int numeroLugar;

    public VehiculoInfo(String placa, String tipoVehiculo, Timestamp horaEntrada, int numeroLugar) {
        this.placa = placa;
        this.tipoVehiculo = tipoVehiculo;
        this.horaEntrada = horaEntrada;
        this.numeroLugar = numeroLugar;
    }

    public String getPlaca() {
        return placa;
    }

    public String getTipoVehiculo() {
        return tipoVehiculo;
    }

    public Timestamp getHoraEntrada() {
        return horaEntrada;
    }

    public int getNumeroLugar() {
        return numeroLugar;
    }
}

