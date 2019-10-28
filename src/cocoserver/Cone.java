/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cocoserver;

import java.net.Socket;

/**
 *
 * @author Lenovo
 */
public class Cone {
    
    Socket socket;
    String usuario;
    
    
    Cone(Socket _socket, String _usuario){
        this.socket=_socket;
        this.usuario=_usuario;
        
    }
    
    
}
