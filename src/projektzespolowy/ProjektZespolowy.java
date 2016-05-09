/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projektzespolowy;

import javax.swing.*;


/**
 *
 * @author PasterzM
 */
public class ProjektZespolowy {

    /**
     * @param args the command line arguments
     */
  
    public static void main(String[] args) {
        
  
        OknoZapotrzebowanie okno = new OknoZapotrzebowanie();
        okno.setLocationRelativeTo(null);  
        okno.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
       
        okno.setVisible(true);
       
//        Okno okno=new Okno(); 
//        okno.setLocationRelativeTo(null);  
//        okno.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
//        okno.setVisible(true); 
    }
    
}
