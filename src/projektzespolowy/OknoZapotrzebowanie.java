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

/**
 *
 * @author Paweł
 */
public class OknoZapotrzebowanie extends JFrame implements ActionListener {

    private JLabel pole;

    public OknoZapotrzebowanie() {
        //FlowLayout experimentLayout = new FlowLayout();
        setTitle("Zapotrzebowanie");
        setSize(500, 400);
        setLayout(null);

        JButton przyciskOK = new JButton("OK");
        przyciskOK.setBounds(460, 500, 100, 20);

        przyciskOK.addActionListener(this);
        
        
       
        add(new JLabel("Pb 95")).setBounds(100, 5, 50, 50);
        add(new JLabel("Pb 98")).setBounds(170, 5, 50, 50);
        add(new JLabel("ON")).setBounds(250, 5, 50, 50);
        add(new JLabel("ON eko")).setBounds(310, 5, 50, 50);
        add(new JLabel("ON super")).setBounds(380, 5, 70, 50);
        
        
            int i;

       for(i=0; i<30;i++){      // nie powinno być więcej niz 30 stacje
           
               
            add(new JLabel("Stacja "+(i+1)+":")).setBounds(24, 40+i*30, 70, 50);
            for (int l=0; l < 5; l++){ 
                add(new JTextField(3)).setBounds(100+l*70+l, 55+i*30, 40, 20);
            }
            
           
           
       }
  

        add(przyciskOK).setBounds(200,60+30*i,100,40);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.dispose();
        OknoGlowne okno=new OknoGlowne(); 
        okno.setLocationRelativeTo(null);  
        okno.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        okno.setVisible(true); 
    }

}
