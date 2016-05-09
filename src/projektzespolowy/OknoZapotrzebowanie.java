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
        FlowLayout experimentLayout = new FlowLayout();
        setTitle("Zapotrzebowanie");
        setSize(500, 400);
        //setLayout();

        JButton przyciskOK = new JButton("OK");
        przyciskOK.setBounds(460, 500, 100, 20);

        przyciskOK.addActionListener(this);

        setLayout(experimentLayout);

        add(new JLabel("                 "));
        add(new JLabel("Pb 95       "));
        add(new JLabel("Pb 98          "));
        add(new JLabel("ON          "));
        add(new JLabel("ON super     "));
        add(new JLabel("ON eko                              "));

       for(int i=0; i<5;i++){
        add(new JLabel("Stacja A:"));
        for (int l=0; l < 5; l++){
            add(new JLabel("     "));
            add(new JTextField(3));
        }
        add(new JLabel("                                  "));
       }
        
        add(przyciskOK);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
