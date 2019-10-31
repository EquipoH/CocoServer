package cocochat;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.io.IOException;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

/**
 *
 * @author Rafael
 */
public class VentanaInvitarAmigosAGrupo extends JFrame {

    public static void main(String[] args){
        VentanaInvitarAmigosAGrupo a = new VentanaInvitarAmigosAGrupo();
        a.show();
        
    }
    
    public VentanaInvitarAmigosAGrupo() {
        Font fuente = new Font("Gadugi",0,14);
        
        JButton c1 = new JButton("Invitar");
        c1.setBackground(new Color(247,151,29));
        c1.setForeground(Color.black);
        c1.setFont(fuente);
        c1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        c1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
//                try{
//                    hSocket.salida.writeUTF("a");
//                    System.out.println("Se estan enviando los datos");
//                    hSocket.salida.writeUTF(c2.getText()+"/"+c3.getText());
//                    String response=hSocket.entrada.readUTF();
//                    if(response.equals("null")){
//                        System.out.println("El usuario no existe");
//                    }else{
//                    Gson gson=new Gson();
//                        pojoUsuario user = gson.fromJson(response, pojoUsuario.class);
//                        Home home=new Home(hSocket,user);
//                       cerrar(evt);
//                        home.show();
//                       
//                    }
//                    
//                
//                }catch(IOException e){
//                    System.out.println("Hubo un error enviando los datos al server");
//                }
            }
        });
        
        JComboBox c2 = new JComboBox();
        JTextField c3 = new JTextField("");
        c3.setFont(fuente);
        c3.setBackground(new Color(200,255,255));
        c3.setBorder(null);
        TextPrompt x = new TextPrompt("Grupo", c3);
        
        GroupLayout layout = new GroupLayout( getContentPane() );
        getContentPane().setLayout( layout );
        layout.setAutoCreateGaps( true ); //Separación entre componentes
        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGap(30)
                .addGroup(layout.createParallelGroup()
                .addComponent(c2,300,300,300)
                .addComponent(c3,300,300,300)
                .addComponent(c1,300,300,300)
                )
                .addGap(30)

        );
        
        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGap(30)
                .addComponent(c2, 50, 50, 50)
                .addGap(20)
                .addComponent(c3,50,50,50)
                .addGap(20)
                .addComponent(c1,50,50,50)
                .addGap(30)
        );
        
        
        this.getContentPane().setBackground(new Color(26,26,26));
        setTitle("Invitar");
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
    public void cerrar(java.awt.event.MouseEvent evt) {  
         this.hide();
    }
}
