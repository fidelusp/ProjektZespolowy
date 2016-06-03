/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projektzespolowy;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;


public class OknoInicjalizujace extends JFrame implements ActionListener {
    
       JButton przyciskOK, wyjście;
       JTextField cysterny, stacje;
       static int iloscCystern, iloscStacji;
    
    public OknoInicjalizujace(){
            
        
        setTitle("Inicjalizacja");
        setSize(300, 125);
        setLayout(null);
        setResizable(false);
        
        przyciskOK = new JButton("OK");
        wyjście = new JButton("Wyjście");
        
        
        
        add(new JLabel("Ilość stacji")).setBounds(10, 15, 100, 20);
        add(stacje = new JTextField(3)).setBounds(80, 15, 40, 20);
        add(new JLabel("Ilość cystern")).setBounds(140, 15, 100, 20);
        add(cysterny = new JTextField(3)).setBounds(220, 15, 40, 20);
        
        add(przyciskOK).setBounds(60, 50, 60, 20);
        add(wyjście).setBounds(130, 50, 100, 20);
        
        wyjście.addActionListener(this);
        przyciskOK.addActionListener(this);
        
      
    }
        
       
         @Override
         public void actionPerformed(ActionEvent e) {
             
             Object źródło=e.getSource();
             
             if(źródło==przyciskOK){
                 
                 iloscCystern = Integer.parseInt(cysterny.getText());
                 iloscStacji = Integer.parseInt(stacje.getText());
                 
                 if(iloscCystern>0 && iloscStacji>0 && iloscStacji<31){
                     
                     
                     
                     this.dispose();
           
                    OknoZapotrzebowanie okno = new OknoZapotrzebowanie();   
                    okno.setLocationRelativeTo(null);  
                    okno.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    okno.setVisible(true);
                     
                 }else{
                     JOptionPane.showMessageDialog(this, "Popraw błędne dane!!", "Błędne dane", HEIGHT);
                 }
             
                
             }else if(źródło==wyjście){
                 
                 this.dispose();
             }
                
         }
         
         int getIloscStacji(){
             return iloscStacji;
         }
         
         int getIloscCystern(){
             return iloscCystern;
         }
        
}
