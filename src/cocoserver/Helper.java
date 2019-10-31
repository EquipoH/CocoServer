/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cocoserver;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import static java.awt.PageAttributes.MediaType.A;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import pojos.pojoGrupo;
import  java.lang.reflect.Type;
import pojos.pojoAmigos;
import pojos.pojoListaAmigos;
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
        PreparedStatement sql, SQL2;
        try {
            sql = con.prepareStatement("INSERT INTO `usuario` (`usuario`, `nombre`, `apellidos`, `contrasena`, `correo`, `conectado`, `idPreguntaRecuperacion`, `respuestaRecuperacion`) VALUES ('" + user.getCorreo() + "', '" + user.getNombre() + "', '" + user.getApellidos() + "', '" + user.getContrasena() + "', '" + user.getCorreo() + "', 'N', '1', '" + user.getRespuestaRecuperacion() + "')");
            sql.execute();

            SQL2 = con.prepareStatement("SELECT * FROM usuario where correo='" + user.getCorreo() + "' and contrasena='" + user.getContrasena() + "'");
            ResultSet rs = SQL2.executeQuery();
            while (rs.next()) {
                result = true;

            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    public String restorePassword(String correo, String color) {
        String response = "null";
        PreparedStatement SQL2;
        try {

            System.out.println(correo);
            System.out.println(color);

            SQL2 = con.prepareStatement("SELECT contrasena FROM usuario where correo='" + correo + "' and respuestaRecuperacion='" + color + "'");
            ResultSet rs = SQL2.executeQuery();
            while (rs.next()) {
                System.out.println("enttroooooo");
                response = rs.getString("contrasena");

            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return response;
    }

    public String getGrupos(String user) {
        String response = "null";
        PreparedStatement SQL2;
          Gson gson=new Gson();
        ArrayList<pojoGrupo> arreglo=new ArrayList<pojoGrupo>();
      
      // Type listType = new TypeToken<ArrayList<pojoGrupo>>(){}.getType();
        
        
        try {

          
            System.out.println("aui estoy");
            SQL2 = con.prepareStatement("SELECT * FROM `grupo` where dueno='"+user+"'");
            ResultSet rs = SQL2.executeQuery();
            while (rs.next()) {
                System.out.println("Entre al ciclo");
                pojoGrupo pojo=new pojoGrupo(rs.getInt("idGrupo"),rs.getString("nombre"),rs.getString("dueno"));
                arreglo.add(pojo);

            }
            System.out.println("ser termino la busqueda");
            if(arreglo.isEmpty()){
                System.out.println("esta vacia");
            }else{
             response=gson.toJson(arreglo);
             System.out.println(response);
            }
            System.out.println("hasta aqui todo bien");
            
            

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return response;
    }
    
    public String getListasDeAmigos(String user) {
        String response = "null";
        PreparedStatement SQL;
        Gson gson=new Gson();
        ArrayList<pojoListaAmigos> arreglo=new ArrayList<pojoListaAmigos>();
          
        try {
            System.out.println("aqui estoy");
            SQL = con.prepareStatement("SELECT *FROM listaamigos where dueno='"+user+"'");
            ResultSet rs = SQL.executeQuery();
            while (rs.next()) {
                System.out.println("Entre al ciclo");
                pojoListaAmigos pojo=new pojoListaAmigos(rs.getInt("idListaAmigo"),rs.getString("nombre"),rs.getString("dueno"));
                arreglo.add(pojo);
            }
            System.out.println("ser termino la busqueda");
            if(arreglo.isEmpty()){
                System.out.println("esta vacia");
            }else{
             response=gson.toJson(arreglo);
             System.out.println(response);
            }
            System.out.println("hasta aqui todo bien");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return response;
    }
    
    public String getAmigosDeListaDeAmigos(String idListaAmigo) {
        String response = "null";
        PreparedStatement SQL;
        Gson gson=new Gson();
        ArrayList<pojoAmigos> arreglo=new ArrayList<>();
          
        try {
            System.out.println("aqui estoy");
            SQL = con.prepareStatement("SELECT *FROM amigos where listaAmigos="+idListaAmigo);
            ResultSet rs = SQL.executeQuery();
            while (rs.next()) {
                System.out.println("Entre al ciclo");
                pojoAmigos pojo=new pojoAmigos(rs.getInt("idAmigos"),rs.getString("amigo"),rs.getInt("listaAmigos"),(rs.getString("aceptado")).charAt(0),rs.getString("apodo"));
                arreglo.add(pojo);
            }
            System.out.println("ser termino la busqueda");
            if(arreglo.isEmpty()){
                System.out.println("esta vacia");
            }else{
             response=gson.toJson(arreglo);
             System.out.println(response);
            }
            System.out.println("hasta aqui todo bien");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return response;
    }
    
}
