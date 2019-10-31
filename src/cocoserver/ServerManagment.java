package cocoserver;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import com.google.gson.Gson;
import java.io.DataInputStream;
import java.net.Socket;
import java.util.List;
import pojos.pojoMensajesPendientes;
import pojos.pojoUsuario;

/**
 *
 * // * @author Usuario
 */
public class ServerManagment extends Thread {

    List<ServerManagment> conexiones;

    Socket vinculo;
    public String user = "sin usuario aun";

            public DataInputStream entrada;
            public DataOutputStream salida;
    ServerManagment(Socket _vinculo, List<ServerManagment> conexiones) {
        this.vinculo = _vinculo;
        this.conexiones = conexiones;
    }

    @Override
    public void run() {
        try {
            System.out.println("Nuevo hilo creado");


            salida = new DataOutputStream(vinculo.getOutputStream());
            entrada = new DataInputStream(vinculo.getInputStream());

            Helper helper = new Helper();

            salida.writeUTF("Hola Usted se conecto a COCOServer");

            while (true) {

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
                    String mensaje=entrada.readUTF();
                    Gson gson=new Gson();
                    pojoMensajesPendientes o=gson.fromJson(mensaje,pojoMensajesPendientes.class);
                    boolean flat=false;
                    for (ServerManagment serverma:conexiones){
                        if(o.getDestinatario().equals(serverma.user)){
                            serverma.salida.writeUTF(mensaje);
                            flat=true;
                            
                        }
                    }
                    if(!flat){
                        //mandar mensjae a la base de datos 
                    }
                    
                    
                    
                } else if (opcion.equals("d")) {
                    System.out.println(opcion);
                    //buscar informacion
                    System.out.println("Se solicita informacion desde desde: " + vinculo.getLocalAddress());
                    System.out.println("");
                } else if (opcion.equals("e")) {
                    //buscar informacion

                    System.out.println("Se solicita contraseña desde: " + vinculo.getLocalAddress());

                    String datos = entrada.readUTF();
                    String[] info = datos.split("/");
                    String response = helper.restorePassword(info[0], info[1]);
                    salida.writeUTF(response);

                } else if (opcion.equals("f")) {
                    //usuarios conectados

                    System.out.println("Se solicito usuarios conectados desde: " + vinculo.getLocalAddress());
                    String usuarios = "";
                    for (ServerManagment usuario : conexiones) {
                        usuarios += usuario.user + "/";
                    }

                    salida.writeUTF(usuarios);

                } else if (opcion.equals("g")) {
                    //usuarios conectados

                    System.out.println("Se solicito grupos desde: " + vinculo.getLocalAddress());
                    String user =entrada.readUTF();
                    System.out.println("el usuario a bucasr"+user);
                    String grupos=helper.getGrupos(user);
                    
                    
                    salida.writeUTF(grupos);

                }else if (opcion.equals("h")) {
                    //listas de amigos

                    System.out.println("Se solicitaron las listas de amigos desde: " + vinculo.getLocalAddress());
                    String listasDeAmigos = "";
                    for (ServerManagment lista : conexiones) {
                        listasDeAmigos += lista.user + "/";
                    }

                    salida.writeUTF(listasDeAmigos);

                }

            }

        } catch (IOException ex) {
            System.out.println("se cerro la conexion");
            conexiones.remove(this);
            this.stop();

        }
    }

}
