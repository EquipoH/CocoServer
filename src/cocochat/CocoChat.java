package cocochat;


import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CocoChat {

    public static void main(String[] args) {
        try {
            HelperSocket s=new HelperSocket(); 
            
            if(s.cliente!=null){
                if(s.cliente.isConnected()){
                    System.out.println("Se conecto correctamente");
                }else{
                    System.out.println("Ocurrio un error para conectarse");
                }
                String dato;
                System.out.println("apunto de leer");
                dato=s.entrada.readUTF();
                System.out.println("ya lei");
                System.out.println(dato);

                VentanaLogin a = new VentanaLogin(s);
                a.show();
            }else{
                System.out.println("No hay conexion con el servidor.");
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}