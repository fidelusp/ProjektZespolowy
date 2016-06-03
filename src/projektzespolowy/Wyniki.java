/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projektzespolowy;

import projektzespolowy.Cysterna.Komora;

/**
 *
 * @author PasterzM
 */
public class Wyniki {

    private int dlugosc_trasy;  //suma dlugosci wszystkich tras przez ktore ma pzrejechac cysterna
    private Stacja stacje;  //stacje przez ktore musi przejechac cysterna
/*    
     private int wynik_pb95;
     private int wynik_98;
     private int wynik_on;
     private int wynik_on_s;
     private int wynik_on_eko;
     */
    private Cysterna cysterna;

    Wyniki(Cysterna cys, int numer_testu) {
        switch (numer_testu) {
            case 1:
                cysterna = new Cysterna(cys);
                stacje = new Stacja();
                stacje.set_nazwa(cys.komora[0].nazwa_stacji);
                dlugosc_trasy = cys.dlugosc_trasy;
                //this.dlugosc_trasy = cys.dlugosc_trasy;
                
                
                for (int i = 0; i < cys.komora.length; i++) {
                        cysterna.komora[i].pojemnosc = cys.komora[i].pojemnosc;
                        cysterna.komora[i].nazwa_stacji = cys.komora[i].nazwa_stacji;
                        cysterna.komora[i].nazwa_paliwa = cys.komora[i].nazwa_paliwa;
                    /*
                    if (cys.komora[i].nazwa_paliwa.equals("ONeko")) {
                        stacje.setZap_on_eko(stacje.getZap_on_eko() + cys.komora[i].pojemnosc);
                    }
                            */
                }
                break;
            case 2:
                cysterna = new Cysterna(cys);
                break;
        }

    }

    public void wyswietl_wynik(int numer_testu) {
        switch (numer_testu) {
            case 1:
                 System.out.println(stacje.getNazwa() + " " + cysterna.komora[0].pojemnosc + " " + cysterna.komora[1].pojemnosc + " " + cysterna.komora[2].pojemnosc + " " + cysterna.komora[3].pojemnosc + " " + cysterna.komora[4].pojemnosc);
                //System.out.println(stacje.getNazwa() + " " + dlugosc_trasy + " " + stacje.getZap_95() + " " + stacje.getZap_98() + " " + stacje.getZap_on() + " " + stacje.getZap_on_eko() + " " + stacje.getZap_on_s());
                break;
            case 2:
                //for (int i = 0; i < cysterna.komora2.length; i++) {
                    if (cysterna.komora2[0].nazwa_paliwa.equals("PB95")) {
                        System.out.println("PB95 "+ cysterna.komora2[0].pojemnosc + " " + cysterna.komora2[1].pojemnosc + " " + cysterna.komora2[2].pojemnosc + " " + cysterna.komora2[3].pojemnosc + " " +  cysterna.komora2[4].pojemnosc );
                    }
                    if (cysterna.komora2[0].nazwa_paliwa.equals("PB98")) {
                        System.out.println("PB98 "+ cysterna.komora2[0].pojemnosc + " " + cysterna.komora2[1].pojemnosc + " " + cysterna.komora2[2].pojemnosc + " " + cysterna.komora2[3].pojemnosc + " " +  cysterna.komora2[4].pojemnosc );
                    }
                    if (cysterna.komora2[0].nazwa_paliwa.equals("ON")) {
                        System.out.println("ON "+ cysterna.komora2[0].pojemnosc + " " + cysterna.komora2[1].pojemnosc + " " + cysterna.komora2[2].pojemnosc + " " + cysterna.komora2[3].pojemnosc + " " +  cysterna.komora2[4].pojemnosc );
                    }
                    if (cysterna.komora2[0].nazwa_paliwa.equals("ONs")) {
                        System.out.println("ONs "+ cysterna.komora2[0].pojemnosc + " " + cysterna.komora2[1].pojemnosc + " " + cysterna.komora2[2].pojemnosc + " " + cysterna.komora2[3].pojemnosc + " " +  cysterna.komora2[4].pojemnosc );
                    }
                    if (cysterna.komora2[0].nazwa_paliwa.equals("ONeko")) {
                        System.out.println("ONeko "+ cysterna.komora2[0].pojemnosc + " " + cysterna.komora2[1].pojemnosc + " " + cysterna.komora2[2].pojemnosc + " " + cysterna.komora2[3].pojemnosc + " " +  cysterna.komora2[4].pojemnosc );
                    }
                //}
                
               // System.out.println(stacje.getNazwa() + " " + dlugosc_trasy + " " + stacje.getZap_95() + " " + stacje.getZap_98() + " " + stacje.getZap_on() + " " + stacje.getZap_on_eko() + " " + stacje.getZap_on_s());
                break;
        }
    }
    
    public Cysterna getCysterna() {
        return cysterna;
    }
}
