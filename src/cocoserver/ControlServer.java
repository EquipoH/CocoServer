/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cocoserver;

import java.util.List;

/**
 *
 * @author Lenovo
 */
public class ControlServer extends Thread {
    List<ServerManagment> conexiones;
    
    ControlServer(List<ServerManagment> conexiones){
        this.conexiones=conexiones;
    }
    
@Override
public void run (){
     for(ServerManagment conexion : conexiones){
            if(conexion.vinculo.isClosed()){
                conexiones.remove(conexion);
            }
        }
    
        for(ServerManagment conexion : conexiones){
            if(conexion.vinculo.isClosed()){
                System.out.println(conexion.user);
                System.out.println("cerrado");
            }else{
                System.out.println(conexion.user);
                System.out.println("abierto");
            
            }
        } 
             
}
    
}
