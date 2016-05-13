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
import sun.net.www.content.audio.x_aiff;

/**
 *
 * @author Paweł
 */
public class OknoZapotrzebowanie extends JFrame implements ActionListener {
    
    
    JTextField pb95, pb98, on, onEko, onSuper;
    Stacja stacja[];
    int i=0;
    
            
    public OknoZapotrzebowanie() {
        
        setTitle("Zapotrzebowanie");
        setSize(500, 400);
        setLayout(null);

        JButton przyciskOK = new JButton("OK");
        przyciskOK.setBounds(460, 500, 100, 20);

         
        
        
       
        add(new JLabel("Pb 95")).setBounds(100, 5, 50, 50);
        add(new JLabel("Pb 98")).setBounds(170, 5, 50, 50);
        add(new JLabel("ON")).setBounds(250, 5, 50, 50);
        add(new JLabel("ON eko")).setBounds(310, 5, 50, 50);
        add(new JLabel("ON super")).setBounds(380, 5, 70, 50);
        
        OknoInicjalizujace x= new OknoInicjalizujace();
        
        
            
         
               
               for(i=0; i<x.getIloscStacji();i++){      // nie powinno być więcej niz 30 stacje
           
               
            add(new JLabel("Stacja "+(i+1)+":")).setBounds(24, 40+i*30, 70, 50);
            for (int l=0; l < 5; l++){ 
                if(l==0){
                   add(pb95 = new JTextField(3)).setBounds(100+l*70+l, 55+i*30, 40, 20);
                  
                        //tablica[l] =Integer.parseInt(paliwo.getText());  
               }else if(l==1){
                   add(pb98 = new JTextField(3)).setBounds(100+l*70+l, 55+i*30, 40, 20);
                   //add(pb98 = new JTextField(3)).setBounds(100+l*70+l, 55+i*30, 40, 20);
                  // stacja[i].nazwa = "Stacja"+(i+1);
                   //stacja[i].zap_pb98 =Integer.parseInt(paliwo.getText());       
               }else if(l==2){
                   add(on = new JTextField(3)).setBounds(100+l*70+l, 55+i*30, 40, 20);
                 //  stacja[i].nazwa = "Stacja"+(i+1);
                  // stacja[i].zap_on =Integer.parseInt(paliwo.getText());         
               }else if(l==3){
                   add(onEko = new JTextField(3)).setBounds(100+l*70+l, 55+i*30, 40, 20);
                  // stacja[i].nazwa = "Stacja"+(i+1);
                  // stacja[i].zap_on_eko =Integer.parseInt(paliwo.getText());         
               }else if(l==4){
                   add(onSuper = new JTextField(3)).setBounds(100+l*70+l, 55+i*30, 40, 20);
                   //stacja[i].nazwa = "Stacja"+(i+1);
                   //stacja[i].zap_on_s =Integer.parseInt(paliwo.getText());        
               }
            }
            
           przyciskOK.addActionListener(this);
           
       }
  

        add(przyciskOK).setBounds(200,60+30*i,100,40);

    }
                
            
            
        

    

    
    @Override
    public void actionPerformed(ActionEvent e) {
          
          stacja[i].zap_pb95 = Integer.parseInt(pb95.getText());
          
         
          i++;
        this.dispose();
        OknoGlowne okno=new OknoGlowne(); 
        okno.setLocationRelativeTo(null);  
        okno.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        okno.setVisible(true); 
    }

}
