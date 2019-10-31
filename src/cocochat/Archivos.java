/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cocochat;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import pojos.pojoMensajesPendientes;

/**
 *
 * @author Rafael
 */
public class Archivos {

    private final String ARCHIVO = "myJSON.json";

    /**
     * @param args the command line arguments
     */
    public void Archivos() {

    }

    /**
     * Funcion de ejemplo para ver como leer  escribir un mensaje con esta clase
     */
    public void ejemplos() {
        Archivos archivo = new Archivos();
        //------------------------------Escribir en un archivo--------------------------------------------------------------
        pojoMensajesPendientes mensaje = new pojoMensajesPendientes(
                3.0/*Id del mensaje*/,
                "Emilio"/*Remitente*/,
                "Pintor"/*Destinatario*/,
                archivo.getHoraYFecha()/*Hora y fecha actual*/,
                "¿Qué tiene we?"/*Texto del mensaje*/,
                'N'/*Leido o No leido*/);
        archivo.escribirMensaje(mensaje);
        //------------------------------------------------------------------------------------------------------------------

        //--------------Leer un archivo(Imprime en consola todos los mensajes), devuelve el pojo del id que le mandas---------
        //------------------------------------------------------------------------------------------------------------------
    }

    /**
     * Esta funcion recibe un pojo de mensaje y lo escribe en el archivo JSON del usuario.
     * @param mensaje pojo de mensaje a escribir en JSON.
     */
    public void escribirMensaje(pojoMensajesPendientes mensaje) {
        double idMensaje = mensaje.getIdMensaje();
        String remitente = mensaje.getRemitente();
        String destinatario = mensaje.getDestinatario();
        String fechayhora = mensaje.getFechayhora();
        String texto = mensaje.getMensaje();

        JSONObject json = new JSONObject();
        JSONObject msj = new JSONObject();
        JSONArray mensajes = new JSONArray();
        try {

            msj.put("idMensaje", String.format("%d", (long) idMensaje));
            msj.put("remitente", remitente);
            msj.put("destinatario", destinatario);
            msj.put("fechayhora", fechayhora);
            msj.put("texto", texto);
            msj.put("leido", "N");

            File file = new File(ARCHIVO);//Con esta clase podemos saber si un archivo existe o no
            if (file.exists()) {//este metodo nos indica si el archivo existe o no
                FileReader fr = new FileReader(ARCHIVO);
                BufferedReader br = new BufferedReader(fr);

                String linea="", textoArchivo = "";
                while ((linea = br.readLine()) != null) {
                    textoArchivo += linea;
                }

                JSONObject lectorJSON = new JSONObject(textoArchivo);
                mensajes = lectorJSON.getJSONArray("mensajes");
                mensajes.put(msj);
                json.put("mensajes", mensajes);
//
                PrintWriter pw = new PrintWriter(ARCHIVO);
                pw.println(json.toString());
                pw.close();
            } else {
                mensajes.put(msj);
                json.put("mensajes", mensajes);
                PrintWriter pw = new PrintWriter(ARCHIVO);
                pw.println(json.toString());
                pw.close();
            }
        } catch (JSONException ex) {
            System.out.println("Error json");
        } catch (FileNotFoundException ex) {
            System.out.println("Error archivo");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * Esta funcion recibe el destinatario y el remitente del que se quiere leer un mensaje.
     * @param destino 
     * @param remitentes
     * @return 
     */
    public ArrayList<pojoMensajesPendientes> leerMensaje(String destino, String remitentes) {
        ArrayList<pojoMensajesPendientes> arreglo = new ArrayList<pojoMensajesPendientes>();

        pojoMensajesPendientes msg;
        double idMensaje = 0.0;
        String remitente = "";
        String destinatario = "";
        String fechayhora = "";
        String texto = "";
        String leido = "";

        try {

            File file = new File(ARCHIVO);//Con esta clase podemos saber si un archivo existe o no
            if (file.exists()) {//este metodo nos indica si el archivo existe o no
                FileReader fr = new FileReader(ARCHIVO);
                BufferedReader br = new BufferedReader(fr);

                String linea, textoArchivo = "";
                while ((linea = br.readLine()) != null) {
                    textoArchivo += linea;
                }

                JSONObject lectorJSON = new JSONObject(textoArchivo);
                JSONArray mensajes = lectorJSON.getJSONArray("mensajes");
                JSONObject mensaje;
                for (int i = 0; i < mensajes.length(); i++) {

                    mensaje = mensajes.getJSONObject(i);
                   
                        idMensaje = mensaje.getDouble("idMensaje");
                        remitente = mensaje.getString("remitente");
                        destinatario = mensaje.getString("destinatario");
                        fechayhora = mensaje.getString("fechayhora");
                        texto = mensaje.getString("texto");
                        leido = mensaje.getString("leido");
                        msg = new pojoMensajesPendientes(idMensaje, remitente, destinatario, fechayhora, texto, leido.charAt(0));
                        arreglo.add(msg);
                    
                    System.out.println("idMensaje: " + String.format("%d", (long) idMensaje));
                    System.out.println("remitente: " + remitente);
                    System.out.println("destinatario: " + destinatario);
                    System.out.println("fechayhora: " + fechayhora);
                    System.out.println("texto: " + texto);
                    System.out.println("-----------------");
                }
            } else {
                System.out.println("No se encontró el archivo json.");
            }
        } catch (FileNotFoundException err) {
            System.out.println(err.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (JSONException ex) {
            System.out.println("Error json");
            System.out.println(ex.toString());
        }

        return arreglo;
    }

    public String getHoraYFecha() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }
}
