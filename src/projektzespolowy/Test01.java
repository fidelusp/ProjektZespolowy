/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projektzespolowy;

import java.io.IOException;
import java.util.Vector;
import java.util.Iterator;

/**
 *
 * @author Dawid
 */
public class Test01 {
    public static void test01(Vector<Wyniki> wyniki1,int[][] tab2 ){
        String[] tab = new String[]{"PB95", "PB98", "ON", "ONs", "ONeko"};
        Stacja[] stacje;
        stacje = new Stacja[OknoInicjalizujace.iloscStacji];
        //Vector<Wyniki> wyniki1 = new Vector<Wyniki>();
/*
        int tab2[][] = new int[][]{//zapotrzebowania
            {0, 0, 0, 0, 0},
            {20, 13, 0, 20, 17},
            {4, 50, 20, 5, 0},
            {0, 3, 21, 9, 13},
            {16, 35, 29, 70, 12},
            {32, 0, 2, 0, 12}
        };
*/
        /*
        int odleglosci[][] = new int[][]{//miedzy stacjami
            {0, 3, 0, 0, 7, 12},
            {3, 0, 4, 0, 6, 0},
            {0, 4, 0, 0, 5, 7},
            {0, 0, 0, 0, 9, 8},
            {7, 6, 5, 9, 0, 0},
            {12, 0, 7, 8, 0, 0}
        };
*/
        for (int i = 0; i < stacje.length; i++) {
            stacje[i] = new Stacja(OknoInicjalizujace.iloscStacji);
        }

        for (int i = 0; i < stacje.length; i++) {
            stacje[i].set_nazwa((char) ('A' + i));
            //stacje[i].set_odleglosci(odleglosci[i]);
            stacje[i].set_odleglosci(OknoInicjalizujace.odleglosci[i]);
            stacje[i].setZap_pb95(tab2[i][0]);
            stacje[i].setZap_98(tab2[i][1]);
            stacje[i].setZap_on(tab2[i][2]);
            stacje[i].setZap_on_s(tab2[i][3]);
            stacje[i].setZap_on_eko(tab2[i][4]);
            stacje[i].sumuj_zapotrzebowanie();
        }
        /////////////////////////zapotrzebowanie dla stacji
//        for (int i = 1; i < stacje.length; i++) {
//            System.out.print(stacje[i].getNazwa() + " ");
//            for (int j = 0; j < stacje.length - 1; j++) {
//                System.out.print(stacje[i].getPaliwo(j) + " ");
//
//            }
//            System.out.println("\n");
//            System.out.print("  " + stacje[i].getZap_95() + "    " + stacje[i].getZap_98() + "    " + stacje[i].getZap_on() + "    "
//                    + stacje[i].getZap_on_s() + "    " + stacje[i].getZap_on_eko());
//            ("\n");
//        }
//ALGORYTM 1
        Cysterna cys = new Cysterna();

        for (int i = 0; i < stacje.length; i++) {
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

                //("licznik :" + licznik);
            }
            suma = stacje[i].suma_zapotrzebowan;
            if (licznik % cys.MAX_KOMOR == 0) { //przeliczanie ilosci komor na ilosc przejazdow
                licznik = licznik / cys.MAX_KOMOR;
            } else {
                licznik = (licznik / cys.MAX_KOMOR) + 1;
            }
            
            Stacja obj = new Stacja(stacje[i]);
            for (int k = 0; k < licznik; k++) { 
                //wyzerowanie cysterny
                cys = new Cysterna();
                for (int l = 0; l < cys.MAX_KOMOR; l++) {
                    //pb95
                    if (cys.komora[l].pojemnosc == 0) {
                        if (obj.getZap_95() % cys.MAX_POJEMNOSC_KOMOR != 0 && obj.getZap_95() > 10) //jesli w zapotrzebowaniu nie ma
                        {//wielokrotnosci 10
                            obj.setZap_pb95(obj.getZap_95() - cys.MAX_POJEMNOSC_KOMOR);
                            cys.komora[l].pojemnosc = cys.MAX_POJEMNOSC_KOMOR;
                            cys.komora[l].nazwa_paliwa = "PB95";
                            cys.komora[l].nazwa_stacji = obj.getNazwa();
                        } else if (obj.getZap_95() % cys.MAX_POJEMNOSC_KOMOR == 0 && obj.getZap_95() != 0) {// komory 0-9
                            obj.setZap_pb95(obj.getZap_95() - cys.MAX_POJEMNOSC_KOMOR);
                            cys.komora[l].pojemnosc = cys.MAX_POJEMNOSC_KOMOR;
                            cys.komora[l].nazwa_paliwa = "PB95";
                            cys.komora[l].nazwa_stacji = obj.getNazwa();
                        } else {
                            cys.komora[l].pojemnosc = obj.getZap_95();
                            obj.setZap_pb95(0);
                            cys.komora[l].nazwa_paliwa = "PB95";
                            cys.komora[l].nazwa_stacji = obj.getNazwa();
                        }
                    }
                    //getZap_98
                    if (cys.komora[l].pojemnosc == 0) {
                        if (obj.getZap_98() % cys.MAX_POJEMNOSC_KOMOR != 0 && obj.getZap_98() > 10) //jesli w zapotrzebowaniu nie ma
                        {//wielokrotnosci 10
                            obj.setZap_98(obj.getZap_98() - cys.MAX_POJEMNOSC_KOMOR);
                            cys.komora[l].pojemnosc = cys.MAX_POJEMNOSC_KOMOR;
                            cys.komora[l].nazwa_paliwa = "PB98";
                            cys.komora[l].nazwa_stacji = obj.getNazwa();
                        } else if (obj.getZap_98() % cys.MAX_POJEMNOSC_KOMOR == 0 && obj.getZap_98() != 0) {// komory 0-9
                            obj.setZap_98(obj.getZap_98() - cys.MAX_POJEMNOSC_KOMOR);
                            cys.komora[l].pojemnosc = cys.MAX_POJEMNOSC_KOMOR;
                            cys.komora[l].nazwa_paliwa = "PB98";
                            cys.komora[l].nazwa_stacji = obj.getNazwa();
                        } else {
                            cys.komora[l].pojemnosc = obj.getZap_98();
                            obj.setZap_98(0);
                            cys.komora[l].nazwa_paliwa = "PB98";
                            cys.komora[l].nazwa_stacji = obj.getNazwa();
                        }
                    }
                    // on
                    if (cys.komora[l].pojemnosc == 0) {
                        if (obj.getZap_on() % cys.MAX_POJEMNOSC_KOMOR != 0 && obj.getZap_on() > 10) //jesli w zapotrzebowaniu nie ma
                        {//wielokrotnosci 10
                            obj.setZap_on(obj.getZap_on() - cys.MAX_POJEMNOSC_KOMOR);
                            cys.komora[l].pojemnosc = cys.MAX_POJEMNOSC_KOMOR;
                            cys.komora[l].nazwa_paliwa = "ON";
                            cys.komora[l].nazwa_stacji = obj.getNazwa();
                        } else if (obj.getZap_on() % cys.MAX_POJEMNOSC_KOMOR == 0 && obj.getZap_on() != 0) {// komory 0-9
                            obj.setZap_on(obj.getZap_on() - cys.MAX_POJEMNOSC_KOMOR);
                            cys.komora[l].pojemnosc = cys.MAX_POJEMNOSC_KOMOR;
                            cys.komora[l].nazwa_paliwa = "ON";
                            cys.komora[l].nazwa_stacji = obj.getNazwa();
                        } else {
                            cys.komora[l].pojemnosc = obj.getZap_on();
                            obj.setZap_on(0);
                            cys.komora[l].nazwa_paliwa = "ON";
                            cys.komora[l].nazwa_stacji = obj.getNazwa();
                        }
                    }
                    // on_eko
                    if (cys.komora[l].pojemnosc == 0) {
                        if (obj.getZap_on_eko() % cys.MAX_POJEMNOSC_KOMOR != 0 && obj.getZap_on_eko() > 10) //jesli w zapotrzebowaniu nie ma
                        {//wielokrotnosci 10
                            obj.setZap_on_eko(obj.getZap_on_eko() - cys.MAX_POJEMNOSC_KOMOR);
                            cys.komora[l].pojemnosc = cys.MAX_POJEMNOSC_KOMOR;
                            cys.komora[l].nazwa_paliwa = "ONeko";
                            cys.komora[l].nazwa_stacji = obj.getNazwa();
                        } else if (obj.getZap_on_eko() % cys.MAX_POJEMNOSC_KOMOR == 0 && obj.getZap_on_eko() != 0) {// komory 0-9
                            obj.setZap_on_eko(obj.getZap_on_eko() - cys.MAX_POJEMNOSC_KOMOR);
                            cys.komora[l].pojemnosc = cys.MAX_POJEMNOSC_KOMOR;
                            cys.komora[l].nazwa_paliwa = "ONeko";
                            cys.komora[l].nazwa_stacji = obj.getNazwa();
                        } else {
                            cys.komora[l].pojemnosc = obj.getZap_on_eko();
                            obj.setZap_on_eko(0);
                            cys.komora[l].nazwa_paliwa = "ONeko";
                            cys.komora[l].nazwa_stacji = obj.getNazwa();
                        }
                    }
                    //on_s
                    if (cys.komora[l].pojemnosc == 0) {
                        if (obj.getZap_on_s() % cys.MAX_POJEMNOSC_KOMOR != 0 && obj.getZap_on_s() > 10) //jesli w zapotrzebowaniu nie ma
                        {//wielokrotnosci 10
                            obj.setZap_on_s(obj.getZap_on_s() - cys.MAX_POJEMNOSC_KOMOR);
                            cys.komora[l].pojemnosc = cys.MAX_POJEMNOSC_KOMOR;
                            cys.komora[l].nazwa_paliwa = "ONs";
                            cys.komora[l].nazwa_stacji = obj.getNazwa();
                        } else if (obj.getZap_on_s() % cys.MAX_POJEMNOSC_KOMOR == 0 && obj.getZap_on_s() != 0) {// komory 0-9
                            obj.setZap_on_s(obj.getZap_on_s() - cys.MAX_POJEMNOSC_KOMOR);
                            cys.komora[l].pojemnosc = cys.MAX_POJEMNOSC_KOMOR;
                            cys.komora[l].nazwa_paliwa = "ONs";
                            cys.komora[l].nazwa_stacji = obj.getNazwa();
                        } else {
                            cys.komora[l].pojemnosc = obj.getZap_on_s();
                            obj.setZap_on_s(0);
                            cys.komora[l].nazwa_paliwa = "ONs";
                            cys.komora[l].nazwa_stacji = obj.getNazwa();
                        }
                    }
                }
               System.out.println(cys.komora[0].pojemnosc + " " + cys.komora[1].pojemnosc + " " + cys.komora[2].pojemnosc + " " + cys.komora[3].pojemnosc + " " + cys.komora[4].pojemnosc);
               ShortestPath t = new ShortestPath();
               t.dijkstra(OknoInicjalizujace.odleglosci,0);
               cys.dlugosc_trasy = t.dist[i+1];      
               wyniki1.addElement(new Wyniki(cys,1));               
              // wyniki1.lastElement().wyswietl_wynik(1);
            }
        }      
        //System.out.println("...");
        /*
        for(int i=0; i<wyniki1.size(); i++){
            wyniki1.get(i).wyswietl_wynik(1);
        }
        */
    }
}
