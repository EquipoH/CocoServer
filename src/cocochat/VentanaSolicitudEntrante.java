/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cocochat;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;

/**
 *
 * @author migue
 */
public class VentanaSolicitudEntrante extends JFrame {
    
    public VentanaSolicitudEntrante(){
        
     this.setMinimumSize(new Dimension(150, 400));

     this.getContentPane().setBackground(new Color(26, 26, 26));
        
     Font fuente = new Font("Gadugi", 0, 14);    
        
     JLabel c1 = new JLabel("Grupos");
     JLabel c2 = new JLabel("Amistad");
     JComboBox c3 = new JComboBox();
     JComboBox c4 = new JComboBox();
     JButton c5 = new JButton("Aceptar");
     JButton c6 = new JButton("Aceptar");
     JLabel c7 = new JLabel("");
    
      c7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
             //Aqui se regresa a Home
            }
          });
      
     c1.setHorizontalAlignment(JLabel.CENTER);
     c1.setVerticalAlignment(JLabel.CENTER);
      
     c1.setOpaque(true);
     c1.setFont(fuente);
     c1.setBackground(new Color(247, 151, 29));
     
     c2.setHorizontalAlignment(JLabel.CENTER);
     c2.setVerticalAlignment(JLabel.CENTER);
     c2.setFont(fuente);
     c2.setOpaque(true);
     c2.setBackground(new Color(247, 151, 29));

     
      GroupLayout layout = new GroupLayout(getContentPane());
      getContentPane().setLayout(layout);
      layout.setAutoCreateGaps(true);
      layout.setAutoCreateContainerGaps(true);
      
       layout.setHorizontalGroup(layout.createSequentialGroup()
               .addComponent(c7,20,20,20)
                .addGroup(layout.createParallelGroup()
                        .addComponent(c1, 150, 300, 450)
                        .addComponent(c3, 150, 300, 450)
                        .addComponent(c5,150,300,450)
                        
                )
                .addGroup(layout.createParallelGroup()
                        .addComponent(c2, 150, 300, 450)
                        .addComponent(c4, 150, 300, 450)
                        .addComponent(c6,150,300,450)
                )
                
        );

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addComponent(c7,20,20,20)
                .addGroup(layout.createParallelGroup()
                        .addComponent(c1)
                        .addComponent(c2)
                        
                )
                .addGroup(layout.createParallelGroup()
                        .addComponent(c3,20,30,40 )
                        .addComponent(c4,20, 30, 40)
                )
                .addGroup(layout.createParallelGroup()
                        
                        .addComponent(c5, 20, 30, 40)
                        .addComponent(c6, 20, 30, 40)
                )
                   
        );

        setTitle("Solicitud");
        pack();
        setLocationRelativeTo(null);
        ImageIcon logo2 = new ImageIcon(getClass().getResource("/imagen/flecha5.png"));
        ImageIcon icono2 = new ImageIcon(logo2.getImage().getScaledInstance(c7.getWidth(), c7.getWidth(), Image.SCALE_DEFAULT));
        c7.setIcon(icono2);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
     
    }
    
}
