/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cocoserver;

import java.io.IOException;
import java.net.Socket;
import java.util.List;

/**
 *
 * @author Usuario
 */
public class ServerManagment extends Thread{
    List<ServerManagment> conexiones;
       String msg="HÓLA";
       Socket vinculo;
       
       
    ServerManagment(Socket _vinculo,List<ServerManagment> conexiones){
    this.vinculo= _vinculo;
    this.conexiones=conexiones;
    }
    
    @Override
    public void run(){
        try{
        
        while(true){
            vinculo.getOutputStream().write(msg.getBytes());//Enviar un arreglo de bytes
                System.out.println("En el hilo");
        }        
                
                
        }catch(IOException ex){
        
        }
    }
    
    
    
}