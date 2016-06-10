/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projektzespolowy;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;


public class OknoInicjalizujace extends JFrame implements ActionListener {
    
       JButton przyciskOK, wyjście;
       JTextField cysterny, stacje;
       static int iloscCystern, iloscStacji;
    
       public static int[][] odleglosci= null;
       
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
                     
                     odleglosci = new int[iloscStacji+1][iloscStacji+1];
                     
                     // ZAMIENIC W METODZIE WCZYTAJODLEGLOSCI SCIEZKE DOSTEPU DO PLIKU !!
                     try {
                         czytajplik.WczytajOdleglosci(odleglosci);
                     } catch (FileNotFoundException ex) {
                         System.out.println("Okno Inicjalizujace, Cos nie tak z odczytem odleglosci");
                     }
                     
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
