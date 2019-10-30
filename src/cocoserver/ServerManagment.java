package cocoserver;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import com.google.gson.Gson;
import java.io.DataInputStream;
import java.net.Socket;
import java.util.List;
import pojos.pojoUsuario;

/**
 *
// * @author Usuario
 */
public class ServerManagment extends Thread {

    List<ServerManagment> conexiones;

    Socket vinculo;

    
    
    ServerManagment(Socket _vinculo, List<ServerManagment> conexiones) {
        this.vinculo = _vinculo;
        this.conexiones = conexiones;
    }

    @Override
    public void run() {
        try {
            System.out.println("en el hilo");
            DataInputStream entrada;
            DataOutputStream salida;
            
            salida = new DataOutputStream(vinculo.getOutputStream());
            entrada = new DataInputStream(vinculo.getInputStream());
            
            
            
            Helper helper = new Helper();
         
            salida.writeUTF("Hola Usted se conecto a COCOServer");
            
            while (true) {
                if (vinculo.isClosed()) {
                    System.out.println("Socket desconectado");
                    this.stop();
                }
                String opcion = entrada.readUTF();

                if (opcion.equalsIgnoreCase("a")) {
                    //inciar sesion
                    System.out.println("Se intenta iniciar sesion desde: " + vinculo.getLocalAddress());
                    String datos = entrada.readUTF();
                    System.out.println("Datos: " + datos);
                    String[] arregloDatos = datos.split("/");
                    pojoUsuario user = helper.iniciarSesion(arregloDatos[0], arregloDatos[1]);
                    if(user==null){
                        //se envia que el usuario no existe
                        salida.writeUTF("null");
                    }else{
                       Gson gson=new Gson();
                         salida.writeUTF(gson.toJson(user));
                    }
             
                   
//                   System.out.println(user);
//                    System.out.println(user.getUsuario());
//                    System.out.println(user.getNombre());
//                    System.out.println(user.getApellidos());
//                    System.out.println(user.getCorreo());
//                    System.out.println(user.getConectado());
//                    System.out.println(user.getIdPreguntaRecuperacion());
//                    System.out.println(user.getRespuestaRecuperacion());

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
            System.out.println("hubo un error en:");
            System.out.println(ex.toString());
        }
    }

}
