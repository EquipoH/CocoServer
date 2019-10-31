/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cocochat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import java.net.Socket;

/**
 *
 * @author bonnesbaby14
 */
public class HelperSocket {

    public Socket cliente;
    public DataInputStream entrada;
    public DataOutputStream salida;

    public void closeSocket() {
        try {
            this.cliente.close();
            this.salida.close();
            this.entrada.close();
            
        } catch (IOException e) {

        }
    }

    public HelperSocket() {
        try {
            cliente = new Socket("192.168.1.101", 123);

            entrada = new DataInputStream(cliente.getInputStream());
            salida = new DataOutputStream(cliente.getOutputStream());

        } catch (IOException e) {
        }

    }

}
