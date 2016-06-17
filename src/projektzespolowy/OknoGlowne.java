/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projektzespolowy;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.List;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;
import javax.imageio.ImageIO;
import javax.swing.*;

/**
 *
 * @author Paweł
 */
public class OknoGlowne extends JFrame {
    private JPanel lewy = new JPanel();
    private JPanel prawy = new JPanel();
    
    private JLabel napis_zapotrzebowanie = new JLabel("ZAPOTRZEBOWANIE");
    private JLabel napis_wyniki =new JLabel("WYNIKI");
    public OknoGlowne() throws IOException {
        super("Marszrutyzacja");
        setResizable(false);
        
        GridBagConstraints polozenie = new GridBagConstraints(); //ustawianie 
        getContentPane().setLayout( new GridBagLayout() );
        
        polozenie.gridx = 0;
        polozenie.gridy = 0;
        getContentPane().add(napis_zapotrzebowanie,polozenie);
        
        polozenie.gridx = 1;
        polozenie.gridy = 0;
        getContentPane().add(napis_wyniki,polozenie);
        
        polozenie.gridx = 0;
        polozenie.gridy = 1;
        lewy.setBackground(Color.LIGHT_GRAY);
        getContentPane().add(lewy,polozenie);
        
        polozenie.gridx = 1;
        polozenie.gridy = 1;
        prawy.setBackground(Color.GRAY);
        getContentPane().add(prawy,polozenie);
        
        lewy.setLayout( new GridLayout(OknoInicjalizujace.iloscStacji*2+1,6) );
        lewy.add(new JLabel());
        lewy.add(new JLabel("Pb 95"));
        lewy.add(new JLabel("Pb 98"));
        lewy.add(new JLabel("ON"));
        lewy.add(new JLabel("ON eko"));
        lewy.add(new JLabel("ON super"));
        
        //Zapotrzebowania
        for(int i=0;i<OknoInicjalizujace.iloscStacji;i++){
            lewy.add(new JLabel("Stacja " + ((char)('A' + i)) + ":  "));
            lewy.add(new JLabel( Integer.toString(OknoZapotrzebowanie.stacja[i].getZap_95())));
            lewy.add(new JLabel( Integer.toString(OknoZapotrzebowanie.stacja[i].getZap_98())));
            lewy.add(new JLabel( Integer.toString(OknoZapotrzebowanie.stacja[i].getZap_on())));
            lewy.add(new JLabel( Integer.toString(OknoZapotrzebowanie.stacja[i].getZap_on_eko())));
            lewy.add(new JLabel( Integer.toString(OknoZapotrzebowanie.stacja[i].getZap_on_s())));
            for(int j=0; j<6;j++) lewy.add( new JLabel() );
        }
        
        Vector<Wyniki> wyniki1 = new Vector<Wyniki>();
   
        
       // Test02.test02(wyniki1,OknoZapotrzebowanie.zapotrzebowania);
        
        Test01.test01(wyniki1,OknoZapotrzebowanie.zapotrzebowania);
        
        prawy.setLayout(new GridLayout(wyniki1.size()*2+1,13) );

        prawy.add( new JLabel( " Cysterna nr. ") );
        prawy.add( new JLabel( " Nazwa Stacji ") );
        prawy.add( new JLabel( " Trasa Cys. ") );

        for(int i=0; i<10;i++) prawy.add( new JLabel() );
        
        for( int i=0; i<wyniki1.size() ;i++ ){
            //numer cysterny
            prawy.add( new JLabel( Integer.toString((i%4)+1) ) );
            //nazwa stacji
            prawy.add( new JLabel(Character.toString(wyniki1.get(i).getCysterna().komora[0].nazwa_stacji) ) );
//odleglosc
            prawy.add(new JLabel( Integer.toString(wyniki1.get(i).getCysterna().dlugosc_trasy) ) );
//zapotrzebowania
            prawy.add( new JLabel( wyniki1.get(i).getCysterna().komora[0].nazwa_paliwa) );
            prawy.add( new JLabel( Integer.toString(wyniki1.get(i).getCysterna().komora[0].pojemnosc)) );
            prawy.add( new JLabel( wyniki1.get(i).getCysterna().komora[1].nazwa_paliwa) );
            prawy.add( new JLabel( Integer.toString(wyniki1.get(i).getCysterna().komora[1].pojemnosc)) );
            prawy.add( new JLabel( wyniki1.get(i).getCysterna().komora[2].nazwa_paliwa) );
            prawy.add( new JLabel( Integer.toString(wyniki1.get(i).getCysterna().komora[2].pojemnosc)) );
            prawy.add( new JLabel( wyniki1.get(i).getCysterna().komora[3].nazwa_paliwa) );
            prawy.add( new JLabel( Integer.toString(wyniki1.get(i).getCysterna().komora[3].pojemnosc)) );
            prawy.add( new JLabel( wyniki1.get(i).getCysterna().komora[4].nazwa_paliwa) );
            prawy.add( new JLabel( Integer.toString(wyniki1.get(i).getCysterna().komora[4].pojemnosc)) );
            for(int j=0; j<13;j++) prawy.add( new JLabel() );
        }
        
        //JPanel obrazPanel = new OknoGlownePanel();
        //add(obrazPanel);
        pack();
    }

}
