/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projektzespolowy;

import java.io.IOException;

/**
 *
 * @author Dawid
 */
public class Test01 {

    public static void main(String[] args) throws IOException {
        String[] tab = new String[]{"PB95", "PB98", "ON", "ONs", "ONeko"};
        Stacja[] stacje;
        stacje = new Stacja[6];

        int tab2[][] = new int[][]{//zapotrzebowania
            {0, 0, 0, 0, 0},
            {20, 13, 0, 6, 17},
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
            stacje[i].set_nazwa((char) ('A' + i));
            stacje[i].set_odleglosci(odleglosci[i]);
            stacje[i].setZap_pb95(tab2[i][0]);
            stacje[i].setZap_98(tab2[i][1]);
            stacje[i].setZap_on(tab2[i][2]);
            stacje[i].setZap_on_eko(tab2[i][4]);
            stacje[i].setZap_on_s(tab2[i][3]);
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

        Cysterna cys = new Cysterna();

        for (int i = 1; i < stacje.length; i++) {
            int licznik = 0;//ilosc komor przeznaczonych na daną benzynę
            int suma = stacje[i].suma_zapotrzebowan;//pomocnicza suma zapotrzebowan    
            while (suma > 0) {

                if (stacje[i].getZap_95() % cys.MAX_POJEMNOSC_KOMOR != 0 && stacje[i].getZap_95() > 0) //jesli w zapotrzebowaniu nie ma
                {//wielokrotnosci 
                    licznik += (stacje[i].getZap_95() / cys.MAX_POJEMNOSC_KOMOR) + 1;
                    int dana_benzyna = stacje[i].getZap_95();
                    suma -= dana_benzyna;
                } else {
                    licznik += (stacje[i].getZap_95() / cys.MAX_POJEMNOSC_KOMOR);
                    int dana_benzyna = stacje[i].getZap_95();
                    suma -= dana_benzyna;

                }
                if (stacje[i].getZap_98() % cys.MAX_POJEMNOSC_KOMOR != 0 && stacje[i].getZap_98() > 0) //jesli w zapotrzebowaniu nie ma
                {//wielokrotnosci 
                    licznik += (stacje[i].getZap_98() / cys.MAX_POJEMNOSC_KOMOR) + 1;
                    int dana_benzyna = stacje[i].getZap_98();
                    suma -= dana_benzyna;
                } else {
                    licznik += (stacje[i].getZap_98() / cys.MAX_POJEMNOSC_KOMOR);
                    int dana_benzyna = stacje[i].getZap_98();
                    suma -= dana_benzyna;

                }
                if (stacje[i].getZap_on() % cys.MAX_POJEMNOSC_KOMOR != 0 && stacje[i].getZap_on() > 0) //jesli w zapotrzebowaniu nie ma
                {//wielokrotnosci 
                    licznik += (stacje[i].getZap_on() / cys.MAX_POJEMNOSC_KOMOR) + 1;
                    int dana_benzyna = stacje[i].getZap_on();
                    suma -= dana_benzyna;
                } else {
                    licznik += (stacje[i].getZap_on() / cys.MAX_POJEMNOSC_KOMOR);
                    int dana_benzyna = stacje[i].getZap_on();
                    suma -= dana_benzyna;
                }
                if (stacje[i].getZap_on_eko() % cys.MAX_POJEMNOSC_KOMOR != 0 && stacje[i].getZap_on_eko() > 0) //jesli w zapotrzebowaniu nie ma
                {//wielokrotnosci 
                    licznik += (stacje[i].getZap_on_eko() / cys.MAX_POJEMNOSC_KOMOR) + 1;
                    int dana_benzyna = stacje[i].getZap_on_eko();
                    suma -= dana_benzyna;
                } else {
                    licznik += (stacje[i].getZap_on_eko() / cys.MAX_POJEMNOSC_KOMOR);
                    int dana_benzyna = stacje[i].getZap_on_eko();
                    suma -= dana_benzyna;

                }
                if (stacje[i].getZap_on_s() % cys.MAX_POJEMNOSC_KOMOR != 0 && stacje[i].getZap_on_s() > 0) //jesli w zapotrzebowaniu nie ma
                {//wielokrotnosci 
                    licznik += (stacje[i].getZap_on_s() / cys.MAX_POJEMNOSC_KOMOR) + 1;
                    int dana_benzyna = stacje[i].getZap_on_s();
                    suma -= dana_benzyna;
                } else {
                    licznik += (stacje[i].getZap_on_s() / cys.MAX_POJEMNOSC_KOMOR);
                    int dana_benzyna = stacje[i].getZap_on_s();
                    suma -= dana_benzyna;

                }

                System.out.println("licznik :" + licznik);
            }
            suma = stacje[i].suma_zapotrzebowan;
            if (licznik % cys.MAX_KOMOR == 0) {
                licznik = licznik / cys.MAX_KOMOR;
            } else {
                licznik = (licznik / cys.MAX_KOMOR) + 1;
            }

            for (int k = 0; k < licznik; k++) {
                Stacja obj = new Stacja(stacje[i]);
                for (int l = 0; l < cys.MAX_KOMOR; l++) {
                    if (obj.getZap_on_s() % cys.MAX_POJEMNOSC_KOMOR != 0 && obj.getZap_on_s() > 10) //jesli w zapotrzebowaniu nie ma
                    {//wielokrotnosci 
                        obj.setZap_on_s(obj.getZap_on_s() - cys.MAX_POJEMNOSC_KOMOR);
                        cys.komora[l].pojemnosc = cys.MAX_POJEMNOSC_KOMOR;
                        cys.komora[l].nazwa_paliwa="ONs";
                        cys.komora[l].nazwa_stacji=obj.getNazwa();
                    } else// komory 0-9
                    {
                        cys.komora[l].pojemnosc = obj.getZap_on_s();
                        obj.setZap_on_s(0);
                        cys.komora[l].nazwa_paliwa="ONs";
                        cys.komora[l].nazwa_stacji=obj.getNazwa();
                    }
                }
            }

        }
        /*
    ////////////////////odleglosci
    for(int i=0;    i<stacje.length;    i++)
    {
        for(int j=0;    j<stacje[i].odleglosci.length ; j++)
        {
        System.out.print(stacje[i].odleglosci[j]+" "); 
        }
        System.out.println();
    }*/

        //ShortestPath t = new ShortestPath();
        //t.dijkstra(odleglosci, 0); 
    }

}
