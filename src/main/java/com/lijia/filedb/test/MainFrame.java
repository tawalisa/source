package com.lijia.filedb.test;

import java.awt.Color; 
import java.awt.Point; 
  
import javax.swing.JFrame; 
import javax.swing.JPanel; 
import javax.swing.SwingUtilities; 
  
  
public class MainFrame extends JFrame { 
    
   private JPanel p; 
    
   public MainFrame() { 
     p = new JPanel(); 
     p.setBackground(Color.red); 
      
     p.setSize(200, 200); 
      
     this.setSize(500, 500); 
     this.setLayout(null); 
     this.getContentPane().add(p); 
     this.setDefaultCloseOperation(EXIT_ON_CLOSE); 
      
     new Thread(new Runnable() { 
  
       @Override 
       public void run() { 
         for (int i = 0; i < 100; i++) { 
           try { 
             Thread.sleep(100); 
           } catch (InterruptedException e) { 
           } 
//           SwingUtilities.invokeLater(new Runnable() { 
//  
//             @Override 
//             public void run() { 
               Point point = p.getLocation(null); 
               p.setLocation(point.x + 1, point.y + 1); 
                
//             }}
//           ); 
         } 
       }}).start(); 
   } 
  
   public static void main(String[] args) { 
     MainFrame f = new MainFrame(); 
     f.setVisible(true); 
   } 
} 