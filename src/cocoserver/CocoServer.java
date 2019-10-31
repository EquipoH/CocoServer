/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cocoserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Lenovo
 */
public class CocoServer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        List<ServerManagment> conexiones = new ArrayList<ServerManagment>();
        
       
        // TODO code application logic here
        while (true) {
            ServerSocket server;
            Socket vinculo;

            try {
                server = new ServerSocket(123);
                vinculo = server.accept(); // El sistema se va a parar aqui hasta que reciva algo el socket
                System.out.println("Conectado");
                ServerManagment hilo = new ServerManagment(vinculo, conexiones);
                hilo.start();
                conexiones.add(hilo);

            } catch (IOException ex) {
                 
       
            }

        }
    }

}
