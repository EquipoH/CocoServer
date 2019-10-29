package cocoserver;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.List;

/**
 *
 * @author Usuario
 */
public class ServerManagment extends Thread {

    List<ServerManagment> conexiones;
    String msg = "HOLA";
    Socket vinculo;

    ServerManagment(Socket _vinculo, List<ServerManagment> conexiones) {
        this.vinculo = _vinculo;
        this.conexiones = conexiones;
    }

    @Override
    public void run() {
        try {
            DataInputStream entrada;
            DataOutputStream salida;
            entrada = new DataInputStream(vinculo.getInputStream());

            salida = new DataOutputStream(vinculo.getOutputStream());
            Helper helper=new Helper();
           

            salida.writeUTF("Hola");

            System.out.println("En el hilo");
            while (true) {
                if (vinculo.isClosed()) {
                    System.out.println("Socket desconectado");
                    this.stop();
                }
                String opcion = entrada.readUTF();

                if (opcion.equalsIgnoreCase("a")) {
                    //inciar sesion
                    System.out.println("Se intenta iniciar sesion desde: " + vinculo.getLocalAddress());
                    

                } else if (opcion.equals("b")) {
                    //registro
                    System.out.println("Se intenta hacer un registro  desde: " + vinculo.getLocalAddress());
                } else if (opcion.equals("c")) {
                    //mandar mensaje
                    System.out.println("Se intenta mandar un mensaje desde: " + vinculo.getLocalAddress());
                } else if (opcion.equals("d")) {
                    System.out.println(opcion);
                    //buscar informacion
                    System.out.println("Se solicita informacion desde desde: " + vinculo.getLocalAddress());

                }

            }

        } catch (IOException ex) {

        }
    }

}
