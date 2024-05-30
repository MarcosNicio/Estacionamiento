/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package estacionamiento.modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class ConexionBaseDeDatos{
    private static final String URL = "jdbc:mysql://localhost:3306/estacionamiento?useSSL=false";
    private static final String USUARIO = "root";
    private static final String CONTRASENA = ""; // Deja en blanco si no hay contraseña

    public static Connection obtenerConexion() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, CONTRASENA);
    }

    public static boolean esVehiculoRegistrado(String placa) {
        String consulta = "SELECT pensionado FROM vehiculos WHERE placa = ?";
        try (Connection conexion = obtenerConexion();
             PreparedStatement stmt = conexion.prepareStatement(consulta)) {
            stmt.setString(1, placa);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean esVehiculoEnEstacionamiento(String placa) {
        String consulta = "SELECT id FROM lugares WHERE placa = ? AND ocupado = 1";
        try (Connection conexion = obtenerConexion();
             PreparedStatement stmt = conexion.prepareStatement(consulta)) {
            stmt.setString(1, placa);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static int obtenerLugarDisponible() {
        String consulta = "SELECT id FROM lugares WHERE ocupado = 0 LIMIT 1";
        try (Connection conexion = obtenerConexion();
             PreparedStatement stmt = conexion.prepareStatement(consulta);
             ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                return rs.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public static boolean ocuparLugar(int idLugar, String placa, String tipoVehiculo) {
        String consulta = "UPDATE lugares SET ocupado = 1, placa = ?, tipo_vehiculo = ?, hora_entrada = NOW() WHERE id = ?";
        try (Connection conexion = obtenerConexion();
             PreparedStatement stmt = conexion.prepareStatement(consulta)) {
            stmt.setString(1, placa);
            stmt.setString(2, tipoVehiculo);
            stmt.setInt(3, idLugar);
            int filasAfectadas = stmt.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean registrarVehiculo(String placa, String tipoVehiculo) throws SQLException {
        if (esVehiculoRegistrado(placa)) {
            return false;
        }

        String consulta = "INSERT INTO vehiculos (placa, tipo_vehiculo, pensionado, tiempo_transcurrido) VALUES (?, ?, 0, '00:00:00')";
        try (Connection conexion = obtenerConexion();
             PreparedStatement stmt = conexion.prepareStatement(consulta)) {
            stmt.setString(1, placa);
            stmt.setString(2, tipoVehiculo);
            int filasAfectadas = stmt.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            if (e.getErrorCode() == 1062) { // Error de duplicado
                return false;
            } else {
                throw e;
            }
        }
    }

    public static boolean liberarLugar(String placa) {
        String consulta = "UPDATE lugares SET ocupado = 0, placa = NULL, tipo_vehiculo = NULL, hora_entrada = NULL WHERE placa = ?";
        try (Connection conexion = obtenerConexion();
             PreparedStatement stmt = conexion.prepareStatement(consulta)) {
            stmt.setString(1, placa);
            int filasAfectadas = stmt.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static double calcularTarifa(String placa) {
        String consulta = "SELECT TIMESTAMPDIFF(MINUTE, hora_entrada, NOW()) AS minutos FROM lugares WHERE placa = ?";
        try (Connection conexion = obtenerConexion();
             PreparedStatement stmt = conexion.prepareStatement(consulta)) {
            stmt.setString(1, placa);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int minutos = rs.getInt("minutos");
                    return calcularTarifaSegunMinutos(minutos);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0.0;
    }
    
    private static double calcularTarifaSegunMinutos(int minutos) {
        int horas = minutos / 60;
        int minutosRestantes = minutos % 60;

        double tarifa = 0.0;
        if (minutosRestantes > 30) {
            tarifa = (horas + 1) * 10.0; // Se cobra una hora extra si se pasa de 30 minutos
        } else if (minutosRestantes > 0) {
            tarifa = horas * 10.0 + 5.0; // Se cobra la mitad de la hora si se pasa de 0 minutos pero no más de 30
        } else {
            tarifa = horas * 10.0; // Se cobra por horas completas
        }
        return tarifa;
    }
    

    public static VehiculoInfo obtenerInfoVehiculo(int idLugar) {
        String consulta = "SELECT * FROM lugares WHERE id = ?";
        try (Connection conexion = obtenerConexion();
             PreparedStatement stmt = conexion.prepareStatement(consulta)) {
            stmt.setInt(1, idLugar);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    boolean ocupado = rs.getBoolean("ocupado");
                    if (ocupado) {
                        String placa = rs.getString("placa");
                        String tipoVehiculo = rs.getString("tipo_vehiculo");
                        Timestamp horaEntrada = rs.getTimestamp("hora_entrada");
                        return new VehiculoInfo(placa, tipoVehiculo, horaEntrada, idLugar);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static VehiculoInfo obtenerInfoVehiculoPorPlaca(String placa) {
        String consulta = "SELECT * FROM lugares WHERE placa = ?";
        try (Connection conexion = obtenerConexion();
             PreparedStatement stmt = conexion.prepareStatement(consulta)) {
            stmt.setString(1, placa);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    boolean ocupado = rs.getBoolean("ocupado");
                    if (ocupado) {
                        int idLugar = rs.getInt("id");
                        String tipoVehiculo = rs.getString("tipo_vehiculo");
                        Timestamp horaEntrada = rs.getTimestamp("hora_entrada");
                        return new VehiculoInfo(placa, tipoVehiculo, horaEntrada, idLugar);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String obtenerTipoVehiculo(String placa) {
        String consulta = "SELECT tipo_vehiculo FROM vehiculos WHERE placa = ?";
        try (Connection conexion = obtenerConexion();
             PreparedStatement stmt = conexion.prepareStatement(consulta)) {
            stmt.setString(1, placa);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("tipo_vehiculo");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "Desconocido";
    }
}
