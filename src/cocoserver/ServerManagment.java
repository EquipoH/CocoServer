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
 * // * @author Usuario
 */
public class ServerManagment extends Thread {

    List<ServerManagment> conexiones;

    Socket vinculo;
    public String user = "sin usuario aun";

    ServerManagment(Socket _vinculo, List<ServerManagment> conexiones) {
        this.vinculo = _vinculo;
        this.conexiones = conexiones;
    }

    @Override
    public void run() {
        try {
            System.out.println("Nuevo hilo creado");

        
            DataInputStream entrada;
            DataOutputStream salida;

            salida = new DataOutputStream(vinculo.getOutputStream());
            entrada = new DataInputStream(vinculo.getInputStream());

            Helper helper = new Helper();

            salida.writeUTF("Hola Usted se conecto a COCOServer");

            while (true) {
                     
        for(ServerManagment conexion : conexiones){
            if(conexion.vinculo.isClosed()){
                conexiones.remove(conexion);
            }
        }
         System.out.println("conexiones activas");
        for(ServerManagment conexion : conexiones){
            if(conexion.vinculo.isClosed()){
                System.out.println(conexion.user);
                System.out.println("cerrado");
            }else{
                System.out.println(conexion.user);
                System.out.println("abierto");
            
            }
        } 
               
                String opcion = entrada.readUTF();
                System.out.println("Se leyo la entrada");

                if (opcion.equalsIgnoreCase("a")) {
                    //inciar sesion
                    System.out.println("Se intenta iniciar sesion desde: " + vinculo.getLocalAddress());
                    String datos = entrada.readUTF();
                    System.out.println("Datos: " + datos);
                    String[] arregloDatos = datos.split("/");
                    pojoUsuario user = helper.iniciarSesion(arregloDatos[0], arregloDatos[1]);
                  
                    if (user == null) {
                        //se envia que el usuario no existe
                        salida.writeUTF("null");
                    } else {
                          this.user = user.getCorreo();
                        Gson gson = new Gson();
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
                    String datos = entrada.readUTF();
                    Gson gson = new Gson();
                    pojoUsuario user = gson.fromJson(datos, pojoUsuario.class);

                    boolean s = helper.crearRegistro(user);
                    if (s) {
                        System.out.println("Se hizo un registro de nuevo usuario desde " + vinculo.getLocalAddress());
                        salida.writeUTF("true");
                    } else {
                        salida.writeUTF("false");
                    }

                } else if (opcion.equals("c")) {
                    //mandar mensaje
                    System.out.println("Se intenta mandar un mensaje desde: " + vinculo.getLocalAddress());
                } else if (opcion.equals("d")) {
                    System.out.println(opcion);
                    //buscar informacion
                    System.out.println("Se solicita informacion desde desde: " + vinculo.getLocalAddress());
                    System.out.println("");
                } else if (opcion.equals("e")) {
                    //buscar informacion

                    System.out.println("Se solicita contraseņa desde: " + vinculo.getLocalAddress());

                    String datos = entrada.readUTF();
                    String[] info = datos.split("/");
                    String response = helper.restorePassword(info[0], info[1]);
                    salida.writeUTF(response);

                } else if (opcion.equals("f")) {
                    //usuarios conectados

                    System.out.println("Se usuarios conectados desde: " + vinculo.getLocalAddress());
                    String usuarios = "";
                    for (ServerManagment usuario : conexiones) {
                        usuarios += usuario.user + "/";
                    }

                    salida.writeUTF(usuarios);

                }

            }

        } catch (IOException ex) {
            System.out.println("hubo un error en:");
            System.out.println(ex.toString());
        }
    }

}
