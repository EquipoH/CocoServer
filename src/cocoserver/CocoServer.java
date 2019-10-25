/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cocoserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Lenovo
 */
public class CocoServer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    
    
     while(true){
        ServerSocket server;
        Socket vinculo;
        //int algo;
     
       //byte[] arreglo;
       // arreglo= new byte[100];
        try{
        server = new ServerSocket(1234);
        vinculo=server.accept(); // El sistema se va a parar aqui hasta que reciva algo el socket
        System.out.println("Conectado");
        ServerManagment hilo=new ServerManagment(vinculo);
        hilo.start();
        
         //vinculo.getOutputStream().write('P');
         
       // System.out.println(vinculo.getInputStream().read());
        
        
        //vinculo.getInputStream().read(arreglo);
        //String me=new String(arreglo);
            //System.out.println(arreglo);
 
        
        
        //Siemrpe tenemos que cerrar el servidor del socket y cerrar el socket,  PRIMERO CIERRO SOCKET despues CIERRO SERVIDOR server.close(). vinculo.close().
        //Nota: Poner pausas en ciertas partes ya que el los clientes dependiendo desde donde entre (de la compu) trabajan a diferentes velocidades por lo que ponemos pausas System.in.read();
        //algo = OS.getInputStream().read();// .read devuelve un asccii
        }
       catch(IOException ex){  
        } 
        
        }
    }
    
    
    
    
    
    
    
    
    }
    


