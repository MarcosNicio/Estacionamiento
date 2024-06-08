/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package estacionamiento.modelo;

import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Usuario {
    private static Connection con;
    private static final String URL = "jdbc:mysql://localhost:3306/estacionamiento?useSSL=false";
    private static final String USUARIO = "root";
    private static final String CONTRASENA = ""; 

    public Usuario() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(URL, USUARIO, CONTRASENA);
        } catch (Exception e) {
            System.err.println("Error: " + e);
        }
    }

    public boolean verificarLogin(String correo, String contrasena) {
        Statement st;
        ResultSet rs;
        boolean loginExitoso = false;

        try {
            st = con.createStatement();
            String query = "SELECT * FROM usuarios WHERE correo = '" + correo + "' AND contrasena = '" + contrasena + "'";
            rs = st.executeQuery(query);

            if (rs.next()) {
                loginExitoso = true;
            }
        } catch (SQLException e) {
            System.err.println("Error: " + e);
        }
        return loginExitoso;
    }

    public static void main(String[] args) {
        Usuario us = new Usuario();
        boolean login = us.verificarLogin("admin@example.com", "adminpassword");

        if (login) {
            System.out.println("Login exitoso");
        } else {
            System.out.println("Login fallido");
        }
    }
}
