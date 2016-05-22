/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projektzespolowy;

import java.io.IOException;
import java.util.Vector;

/**
 *
 * @author PasterzM
 */
public class Test02 {

    public static void main(String[] args) throws IOException {
        String[] tab = new String[]{"PB95", "PB98", "ON", "ONs", "ONeko"};
        Stacja[] stacje;
        stacje = new Stacja[6];
        Vector<Wyniki> wyniki = new Vector<Wyniki>();

        int tab2[][] = new int[][]{//zapotrzebowania
            {0, 0, 0, 0, 0},
            {20, 13, 0, 20, 17},
            {4, 50, 20, 5, 0},
            {0, 3, 21, 9, 13},
            {16, 35, 29, 70, 12},
            {32, 0, 2, 0, 12}
        };

        int odleglosci[][] = new int[][]{//miedzy stacjami
            {0, 3, 0, 0, 6, 12},
            {3, 0, 4, 0, 6, 0},
            {0, 4, 0, 0, 5, 7},
            {0, 0, 0, 0, 9, 8},
            {7, 6, 5, 9, 0, 0},
            {12, 0, 7, 8, 0, 0}
        };

        for (int i = 0; i < stacje.length; i++) {
            stacje[i] = new Stacja(6);
        }

        for (int i = 0; i < stacje.length; i++) {
            stacje[i].set_nazwa((char) ('A' + i - 1));
            stacje[i].set_odleglosci(odleglosci[i]);
            stacje[i].setZap_pb95(tab2[i][0]);
            stacje[i].setZap_98(tab2[i][1]);
            stacje[i].setZap_on(tab2[i][2]);
            stacje[i].setZap_on_s(tab2[i][3]);
            stacje[i].setZap_on_eko(tab2[i][4]);
            stacje[i].sumuj_zapotrzebowanie();
        }
        /////////////////////////zapotrzebowanie dla stacji
        for (int i = 1; i < stacje.length; i++) {
            System.out.print(stacje[i].getNazwa() + " ");
            for (int j = 0; j < stacje.length - 1; j++) {
                System.out.print(stacje[i].getPaliwo(j) + " ");

            }
            System.out.println("\n");
            System.out.print("  " + stacje[i].getZap_95() + "    " + stacje[i].getZap_98() + "    " + stacje[i].getZap_on() + "    "
                    + stacje[i].getZap_on_s() + "    " + stacje[i].getZap_on_eko());
            System.out.println("\n");
        }
//ALGORYTM 2
        Cysterna cys = new Cysterna();
        for (int i = 1, it = 0; i < tab.length; i++, it++) {
            int suma_benzyny = 0;

            for (int j = 0; j < stacje.length; j++) {    //zliczanie zapotrzebowania na dana benzyne
                if (it == 0) {
                    suma_benzyny += stacje[j].getZap_95();
                }
                if (it == 1) {
                    suma_benzyny += stacje[j].getZap_98();
                }
                if (it == 2) {
                    suma_benzyny += stacje[j].getZap_on();
                }
                if (it == 3) {
                    suma_benzyny += stacje[j].getZap_on_eko();
                }
                if (it == 4) {
                    suma_benzyny += stacje[j].getZap_on_s();
                }
            }
            //System.out.print(suma_benzyny+" ");
            int ilosc_przejazdow = 0;
            if (suma_benzyny % cys.MAX_KOMOR == 0) { //przeliczanie ilosci zapotrzebowania na ilosc przejazdow
                ilosc_przejazdow = suma_benzyny / (cys.MAX_KOMOR * cys.MAX_POJEMNOSC_KOMOR);
            } else {
                ilosc_przejazdow = (suma_benzyny / (cys.MAX_KOMOR * cys.MAX_POJEMNOSC_KOMOR)) + 1;
            }
            //System.out.println(ilosc_przejazdow);
            Stacja obj = new Stacja(stacje[i]);
            for (int j = 0; j < ilosc_przejazdow; j++) {
                cys = new Cysterna();
                for (int l = 0, n = 0; l < cys.MAX_KOMOR; l++) {   //ladowanie cysterny
                    if (cys.komora2[l].pojemnosc < cys.MAX_POJEMNOSC_KOMOR) {
                        if (obj.getZap_95() % cys.MAX_POJEMNOSC_KOMOR != 0 && obj.getZap_95() >= 10) { //jesli w zapotrzebowaniu nie ma
                            //wielokrotnosci 10
                            obj.setZap_pb95(obj.getZap_95() - cys.MAX_POJEMNOSC_KOMOR);
                            cys.komora2[l].pojemnosc = cys.MAX_POJEMNOSC_KOMOR;
                            cys.komora2[l].nazwa_paliwa = "PB95";
                            cys.komora2[l].nazwa_stacji[n] = obj.getNazwa();
                        }else if (obj.getZap_95() % cys.MAX_POJEMNOSC_KOMOR == 0 && obj.getZap_95() != 0) {// komory 0-9
                            obj.setZap_pb95(obj.getZap_95() - cys.MAX_POJEMNOSC_KOMOR);
                            cys.komora2[l].pojemnosc = cys.MAX_POJEMNOSC_KOMOR;
                            cys.komora2[l].nazwa_paliwa = "PB95";
                            cys.komora2[l].nazwa_stacji[n] = obj.getNazwa();
                        } else {
                            cys.komora2[l].pojemnosc = obj.getZap_95();
                            obj.setZap_pb95(0);
                            cys.komora2[l].nazwa_paliwa = "PB95";
                            cys.komora2[l].nazwa_stacji[n] = obj.getNazwa();
                            n++;
                        }
                    }
                    System.out.print(cys.komora2[l].pojemnosc+" ");
                }
                System.out.println("");
            }
        }
    }
}
