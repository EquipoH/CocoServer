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
    
       public void createUser(Object d) {
           
           
        PreparedStatement sql;
        try {
            sql = con.prepareStatement("INSERT INTO `usuario` (`usuario`, `nombre`, `apellidos`, `contrasena`, `correo`, `conectado`, `idPreguntaRecuperacion`, `respuestaRecuperacion`) VALUES ('"+d.toString()+"', '"+d.toString()+"', '"+d.toString()+"', '"+d.toString()+"', '"+d.toString()+"', 'N', '"+d.toString()+"', '"+d.toString()+"');");
            sql.execute();
            System.out.println();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }
    
}
