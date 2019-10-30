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
     *
     * @param user coorreo del usuario
     * @param password contraseña del usuario
     * @return retona un objeto pojo el cual contiene toda la informacion de la
     * base de datos
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

    public boolean crearRegistro(pojoUsuario user) {
        boolean result = false;
        PreparedStatement sql,SQL2;
        try {
            sql = con.prepareStatement("INSERT INTO `usuario` (`usuario`, `nombre`, `apellidos`, `contrasena`, `correo`, `conectado`, `idPreguntaRecuperacion`, `respuestaRecuperacion`) VALUES ('" + user.getCorreo() + "', '" + user.getNombre() + "', '" + user.getApellidos() + "', '" + user.getContrasena() + "', '" + user.getCorreo() + "', 'N', '1', '" + user.getRespuestaRecuperacion() + "')");
            sql.execute();
           
            
            SQL2 = con.prepareStatement("SELECT * FROM usuario where correo='" + user.getCorreo() + "' and contrasena='" + user.getContrasena() + "'");
            ResultSet rs = SQL2.executeQuery();
            while(rs.next()) {
                result = true;

            }
            

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }
    
     public String restorePassword(String correo,String color) {
        String response="null";
        PreparedStatement SQL2;
        try {
           
            System.out.println(correo);
            System.out.println(color);
            
            
            SQL2 = con.prepareStatement("SELECT contrasena FROM usuario where correo='" + correo + "' and respuestaRecuperacion='" + color + "'");
            ResultSet rs = SQL2.executeQuery();
            while(rs.next()) {
                System.out.println("enttroooooo");
                response=rs.getString("contrasena");

            }
            

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return response;
    }

}
