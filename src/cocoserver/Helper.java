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

    public void iniciarSesion(String user, String password) {

        PreparedStatement sql;
        try {
            sql = con.prepareStatement("SELECT * FROM `usuario` where correo=" + user + " and contrasena=" + password + "");
            ResultSet rs = sql.executeQuery();
            while (rs.next()) {
                System.out.println("rs.getString(\"nombre\");");
                
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

}
