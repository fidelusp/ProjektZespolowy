/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projektzespolowy;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;


public class OknoInicjalizujace extends JFrame implements ActionListener {
        
       JButton przyciskOK, wyjście;
        
    public OknoInicjalizujace(){
            
        FlowLayout experimentLayout = new FlowLayout();    
        setTitle("Inicjalizacja");
        setSize(200, 125);
        
        
        przyciskOK = new JButton("OK");
        wyjście = new JButton("Wyjście");
        
        setLayout(experimentLayout);
        
        add(new JLabel("   Ilość stacji    "));
        add(new JTextField(3));
        add(new JLabel("   Ilość cystern"));
        add(new JTextField(3));
        
        add(przyciskOK);
        add(wyjście);
        
        wyjście.addActionListener(this);
        przyciskOK.addActionListener(this);
    }
        
       
         @Override
         public void actionPerformed(ActionEvent e) {
             
             Object źródło=e.getSource();
             
             if(źródło==przyciskOK){
             
                dispose();
           
                OknoZapotrzebowanie okno = new OknoZapotrzebowanie();   
                okno.setLocationRelativeTo(null);  
                okno.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                okno.setVisible(true);
             }else if(źródło==wyjście){
                 
                 this.dispose();
             }
                
         }
        
}
