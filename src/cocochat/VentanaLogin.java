package cocochat;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

import java.io.IOException;

import javax.swing.GroupLayout;
import static javax.swing.GroupLayout.Alignment.LEADING;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import com.google.gson.Gson;
import static java.awt.image.ImageObserver.HEIGHT;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import pojos.pojoUsuario;

public class VentanaLogin extends JFrame
{  JTextField c2;
    HelperSocket hSocket;
    int contador=0;
    public VentanaLogin(HelperSocket s)
    {   
         this.hSocket=s;
        Font fuente = new Font("Gadugi",0,14);
        
        JLabel c1 = new JLabel("");
       c2 = new JTextField("");
        c2.setFont(fuente); 
        c2.setBackground(new Color(200,255,255));
        c2.setBorder(null);
        TextPrompt prueba2 = new TextPrompt("Nombre de usuario", c2);

        JTextField c3 = new JTextField("");
        c3.setFont(fuente);
        c3.setBackground(new Color(200,255,255));
        c3.setBorder(null);
         TextPrompt prueba3 = new TextPrompt("Contraseña", c3);
        
        JButton c4 = new JButton("Entrar");
        c4.setBackground(new Color(247,151,29));
        c4.setForeground(Color.black);
        c4.setFont(fuente);
        c4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        c4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                try{
                     if(c2.getText().equals("")|| c3.getText().equals("")){
                     JOptionPane.showMessageDialog(null,"Llena todos los campos ", "cocoChat" , HEIGHT);
                    } else{
                    hSocket.salida.writeUTF("a");
                    System.out.println("Se estan enviando los datos");
                  
                   
                    hSocket.salida.writeUTF(c2.getText()+"/"+c3.getText());
                    String response=hSocket.entrada.readUTF();
                    if(response.equals("null")){
                        contador++;
                        if(contador>=3){
                        contador=0;
                        VentanaRegistro1 ven = new VentanaRegistro1(hSocket);
                        ven.show();
                        }
                        System.out.println("El usuario no existe");
                    }else{
                    Gson gson=new Gson();
                        pojoUsuario user = gson.fromJson(response, pojoUsuario.class);
                        Home home=new Home(hSocket,user);
                       cerrar(evt);
                        home.show();
                       
                    }
                  }
                
                }catch(IOException e){
                    System.out.println("Hubo un error enviando los datos al server");
                }
            }
        });
        
        JLabel c5 = new JLabel("¿Olvidaste tu contraseña?");
        c5.setFont(fuente); 
        c5.setForeground(Color.white);
        c5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        
        c5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                c5MouseClicked(evt);
            }
        });
        
        
        
        JLabel c6 = new JLabel("¿No tienes una cuenta? ¡Registrate gratis!");
        c6.setFont(fuente); 
        c6.setForeground(Color.white);
        c6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        
        c6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
               VentanaRegistro1 ventana=new VentanaRegistro1(hSocket);
               ventana.show();
            }
            
        });

        GroupLayout layout = new GroupLayout( getContentPane() );
        getContentPane().setLayout( layout );
        layout.setAutoCreateGaps( true ); //SeparaciÃ³n entre componentes
        layout.setAutoCreateContainerGaps( true ); // separaciÃ³n con la ventana
        
        layout.setHorizontalGroup(layout.createParallelGroup(LEADING)
                .addComponent(c1, 100, 300, 500)//version con nombre de componente, tamaÃ±o minimo, preferente y tamaÃ±o mÃ¡ximo)
                .addComponent(c2, 100, 300, 300)
                .addComponent(c3, 100, 300, 300)
                .addComponent(c4, 100, 300, 500)
                .addComponent(c5, 100, 200, 200)
                .addComponent(c6, 260, 265, 270)
        );
        
        layout.setVerticalGroup(layout.createSequentialGroup()
                .addComponent(c1, 100, 150, 150)
                .addGap(30)
                .addComponent(c2, 30, 30,30)
                .addGap(15)
                .addComponent(c3, 30, 30, 30)
                .addGap(15)
                .addComponent(c4, 30,40,40)
                .addGap(15)
                .addComponent(c5, 40, 40, 40)
                .addGap(15)
                .addComponent(c6, 40, 40, 40)
        );
        
        this.getContentPane().setBackground(new Color(26,26,26));
        setTitle("Login");
        pack();
        setLocationRelativeTo(null);
        ImageIcon logo = new ImageIcon(getClass().getResource("/imagen/logo.png"));
        ImageIcon icono = new ImageIcon(logo.getImage().getScaledInstance(c1.getWidth(), c1.getWidth(), Image.SCALE_DEFAULT));
        
        c1.setIcon(icono);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }   
    
     private void c5MouseClicked(java.awt.event.MouseEvent evt) {  
         
         VentanaRecuperarContrasena a = new VentanaRecuperarContrasena(hSocket,c2.getText());
         a.show();
    }
     
     public void cerrar(java.awt.event.MouseEvent evt) {  
         this.hide();
        // VentanaRegistro1 a = new VentanaRegistro1 ();
         //a.show();
    }
}