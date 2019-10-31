/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cocochat;

import com.google.gson.Gson;
import java.awt.Color;
import java.awt.Image;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.List;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.GroupLayout;
import static javax.swing.GroupLayout.Alignment.LEADING;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import pojos.pojoMensajesPendientes;

/**
 *
 * @author migue
 */
public class Chat extends JFrame implements Runnable {

    List<pojoMensajesPendientes> al;
    Archivos archivo;
    Thread hilo;
    JTextArea c2;
    JFormattedTextField c1;
    JTextField c3;
    JButton c4;
    JLabel c5;
    int a;
    java.util.Date fecha = new Date();
    String destino, remitente;
    HelperSocket hSocket;

    Chat(String destino, String remitente, HelperSocket hSocket) {
        archivo = new Archivos();

        hilo = new Thread(this);
        hilo.start();
        this.destino = destino;
        this.remitente = remitente;
        this.hSocket = hSocket;

        c1 = new javax.swing.JFormattedTextField();
        c1.setText("");
        c1.setFont(new java.awt.Font("Segoe UI Black", 1, 15)); // NOI18N
        c1.setText("PERFIL");
        c1.setEditable(false);
        c1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 255)));

        c2 = new javax.swing.JTextArea();
        c2.setEditable(false);
        c2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 255)));

        c3 = new JTextField();
        c3.setFont(new java.awt.Font("Segoe UI Black", 1, 15)); // NOI18N
        TextPrompt prueba4 = new TextPrompt("Escribe un mensaje...", c3);

        c4 = new JButton("OK");
        c4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                //actualizar usuaruos conectados
                Date date = new Date();
                DateFormat hourdateFormat = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
                try {
                    hSocket.salida.writeUTF("c");
                    pojoMensajesPendientes o = new pojoMensajesPendientes(0, remitente, destino, hourdateFormat.format(date), c3.getText(), 'n');

                    Gson gson = new Gson();
                    String json = gson.toJson(o, pojoMensajesPendientes.class);

                    hSocket.salida.writeUTF(json);
                    
                    archivo.escribirMensaje(o);
                    

                al.removeAll(al);
                al = archivo.leerMensaje(destino, remitente);
                c2.setText("");
                String aux="";
                for (pojoMensajesPendientes mensaje : al) {
                    if((mensaje.getDestinatario().equals(destino) &&
                            mensaje.getRemitente().equals(remitente))||
                            (mensaje.getDestinatario().equals(remitente) &&
                            mensaje.getRemitente().equals(destino))){
                        aux=c2.getText() + (mensaje.getIdMensaje() + mensaje.getRemitente() + ": " + mensaje.getMensaje() + "\n");
                        c2.setText(aux);
                    }
                    
                }
                   

                } catch (IOException e) {

                }

            }
        });;

        c5 = new JLabel("");
        c5.setText("");
        c5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setAutoCreateGaps(true); //Separación entre componentes
        layout.setAutoCreateContainerGaps(true); // separación con la ventana

        layout.setHorizontalGroup(layout.createParallelGroup(LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addComponent(c5, 20, 20, 20)
                        .addComponent(c1, 265, 265, 265)//version con nombre de componente, tamaño minimo, preferente y tamaño máximo)
                )
                .addGroup(layout.createParallelGroup()
                        .addComponent(c2, 100, 300, 300))
                .addGroup(layout.createSequentialGroup()
                        .addComponent(c3, 215, 215, 215)
                        .addComponent(c4, 80, 80, 80))
        );

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup()
                        .addComponent(c5, 50, 50, 50)
                        .addComponent(c1, 50, 50, 50)
                )
                .addGroup(layout.createSequentialGroup()
                        .addComponent(c2, 300, 300, 300)
                        .addGap(15))
                .addGroup(layout.createParallelGroup()
                        .addComponent(c3, 30, 30, 30)
                        .addComponent(c4, 30, 30, 30))
        );

        this.getContentPane();
        setTitle("CHAT");
        pack();

        ImageIcon logo2 = new ImageIcon(getClass().getResource("/imagen/flecha5.png"));
        ImageIcon icono2 = new ImageIcon(logo2.getImage().getScaledInstance(c5.getWidth(), c5.getWidth(), Image.SCALE_AREA_AVERAGING));
        c5.setIcon(icono2);
        
        
        al = archivo.leerMensaje(destino, remitente);

        for (pojoMensajesPendientes mensaje : al) {
            c2.setText(c2.getText() + (mensaje.getIdMensaje() + mensaje.getRemitente() + ": " + mensaje.getMensaje() + "\n"));
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                al = new ArrayList<pojoMensajesPendientes>();
                String jason = hSocket.entrada.readUTF();

                Gson gson = new Gson();
                pojoMensajesPendientes s = gson.fromJson(jason, pojoMensajesPendientes.class);
                System.out.println(s.getIdMensaje());

                archivo.escribirMensaje(s);

                al.removeAll(al);
                al = archivo.leerMensaje(destino, remitente);

                for (pojoMensajesPendientes mensaje : al) {
                    c2.setText(c2.getText() + (mensaje.getIdMensaje() + mensaje.getRemitente() + ": " + mensaje.getMensaje() + "\n"));
                }

            } catch (IOException e) {

            }
        }
    }

}
