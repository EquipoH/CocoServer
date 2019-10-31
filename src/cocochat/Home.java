package cocochat;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;
import pojos.pojoGrupo;
import pojos.pojoMensajesPendientes;
import pojos.pojoUsuario;

public class Home extends JFrame {

    HelperSocket hSocket;
    pojoUsuario myUser;
    boolean on = true;
    Thread hilo;

    public Home(HelperSocket hSocket, pojoUsuario myUser) {

        this.hSocket = hSocket;
        this.myUser = myUser;

        this.setMinimumSize(new Dimension(150, 400));

        this.getContentPane().setBackground(new Color(26, 26, 26));
        Font fuente = new Font("Gadugi", 0, 14);

        JLabel c1 = new JLabel("Conectados");
        JLabel c2 = new JLabel("Grupos");
        JLabel c3 = new JLabel("Amigos");
        JComboBox c4 = new JComboBox();
        JComboBox c5 = new JComboBox();
        JComboBox c6 = new JComboBox();
        JButton c7 = new JButton("Chat");
        JButton c8 = new JButton("Solicitud");
        JButton c9 = new JButton("Chat");
        JButton c10 = new JButton("Chat");
        JButton c11 = new JButton("Solicitud");
        JButton c12 = new JButton("Crear Grupo");

        //c11.setBackground(new Color(24, 84, 245));
        c11.setForeground(Color.BLACK);
        c11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {

                VentanaSolicitudEntrante ventana = new VentanaSolicitudEntrante();
                ventana.show();
            }
        });

        //c12.setBackground(new Color(28, 241, 32));
        c12.setForeground(Color.BLACK);

        c4.setBackground(new Color(247, 151, 29));
        c4.setForeground(Color.black);
        c4.setFont(fuente);

        c1.setHorizontalAlignment(JLabel.CENTER);
        c1.setVerticalAlignment(JLabel.CENTER);
        c1.setFont(fuente);
        c1.setOpaque(true);
        c1.setBackground(new Color(247, 151, 29));
        c1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                //actualizar usuaruos conectados
                on = false;
                try {
                    hSocket.salida.writeUTF("f");
                    System.out.println("esperando para leer");
                    String usuarioS = hSocket.entrada.readUTF();
                    System.out.println("ya se leyo");
                    String[] usuarios = usuarioS.split("/");
                    c4.removeAllItems();
                    for (String usuario : usuarios) {
                        if (usuario.equals("me cerre")) {
                        } else {
                            c4.addItem(usuario);
                        }
                        System.out.println(usuario);
                    }

                } catch (IOException e) {
                }
            }
        });;

        c2.setHorizontalAlignment(JLabel.CENTER);
        c2.setVerticalAlignment(JLabel.CENTER);
        c2.setFont(fuente);
        c2.setOpaque(true);
        c2.setBackground(new Color(247, 151, 29));
        c2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                //actualizar usuaruos conectados
                try {
                    hSocket.salida.writeUTF("g");
                    hSocket.salida.writeUTF(myUser.getUsuario());
                    String json = hSocket.entrada.readUTF();
                    ArrayList<pojoGrupo> arreglo = new ArrayList<pojoGrupo>();

                    java.lang.reflect.Type listTyspe = listTyspe = new TypeToken<ArrayList<pojoGrupo>>() {
                    }.getType();
                    arreglo = new Gson().fromJson(json, listTyspe);
                    c5.removeAllItems();
                    for (pojoGrupo grupo : arreglo) {
                        c5.addItem(grupo.getNombre());
                    }

                } catch (IOException e) {
                }
            }
        });;

        c3.setHorizontalAlignment(JLabel.CENTER);
        c3.setVerticalAlignment(JLabel.CENTER);
        c3.setFont(fuente);
        c3.setOpaque(true);
        c3.setBackground(new Color(247, 151, 29));
        c3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                //actualizar usuaruos conectados
                try {
                    hSocket.salida.writeUTF("h");
                    hSocket.salida.writeUTF(myUser.getUsuario());
                    String json = hSocket.entrada.readUTF();
                    Gson gson = new Gson();

                    c4.removeAllItems();
//                    for (String usuario : usuarios) {
//                        if(usuario.equals("me cerre")){
//                        }else{
//                        c4.addItem(usuario);}
//                        System.out.println(usuario);
//                    }

                } catch (IOException e) {
                }
            }
        });;
        c7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                //actualizar usuaruos conectados

                String user = c4.getSelectedItem().toString();
                Chat chat = new Chat(user, myUser.getUsuario(), hSocket);
                chat.show();

            }
        });;

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup()
                        .addComponent(c1, 150, 300, 450)
                        .addComponent(c4, 150, 300, 450)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(c7, 150, 150, 150)
                                .addComponent(c8, 150, 150, 150)
                        )
                )
                .addGroup(layout.createParallelGroup()
                        .addComponent(c2, 150, 300, 450)
                        .addComponent(c5, 150, 300, 450)
                        .addComponent(c9, 150, 300, 450)
                        .addComponent(c11, 150, 150, 250)
                )
                .addGroup(layout.createParallelGroup()
                        .addComponent(c3, 150, 300, 450)
                        .addComponent(c6, 150, 300, 450)
                        .addComponent(c10, 150, 300, 450)
                        .addComponent(c12, 150, 150, 250)
                )
        );

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup()
                        .addComponent(c1)
                        .addComponent(c2)
                        .addComponent(c3)
                )
                .addGroup(layout.createParallelGroup()
                        .addComponent(c4, 20, 30, 40)
                        .addComponent(c5, 20, 30, 40)
                        .addComponent(c6, 20, 30, 40)
                )
                .addGroup(layout.createParallelGroup()
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(c7, 20, 30, 40)
                        )
                        .addComponent(c8, 20, 30, 40)
                        .addComponent(c9, 20, 30, 40)
                        .addGap(230)
                        .addComponent(c10, 20, 30, 40)
                        .addGap(230)
                )
                .addGroup(layout.createParallelGroup()
                        .addComponent(c11, 20, 30, 40)
                        .addComponent(c12, 20, 30, 40)
                )
        );

        setTitle("CocoChat");
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent evt) {
                hSocket.closeSocket();
            }
        });
    }

}
