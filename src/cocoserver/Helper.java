/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cocoserver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import pojos.pojoUsuario;

/**
 *
 * @author Lenovo
 */
public class Helper {

    protected Connection con;
    ResultSet r;

    public Helper() {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost/chat", "root", "");
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    /**
     * Funcion consulta la informacion y retona un pojo de tipo usuario
     * @param user coorreo del usuario
     * @param password contraseña del usuario
     * @return retona un objeto pojo el cual contiene toda la informacion de la base de datos
     */
    public pojoUsuario iniciarSesion(String user, String password) {
        pojoUsuario datos = null;
        PreparedStatement sql;
        try {
            sql = con.prepareStatement("SELECT * FROM usuario where correo='" + user + "' and contrasena='" + password + "'");
            ResultSet rs = sql.executeQuery();
            while (rs.next()) {

                datos = new pojoUsuario(rs.getString("usuario"), rs.getString("nombre"), rs.getString("apellidos"), rs.getString("contrasena"), rs.getString("correo"), rs.getString("conectado").charAt(0), rs.getInt("idPreguntaRecuperacion"), rs.getString("respuestaRecuperacion"));

            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return datos;
    }

}
