/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cocoserver;

import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author Usuario
 */
public class ServerManagment extends Thread{
       String msg="HÓLA";
       Socket vinculo;
       
    ServerManagment(Socket _vinculo){
    this.vinculo= _vinculo;
    }
    
    @Override
    public void run(){
        try{
        vinculo.getOutputStream().write(msg.getBytes());//Enviar un arreglo de bytes
                System.out.println("En el hilo");
        }catch(IOException ex){
        
        }
    }
    
    
    
}