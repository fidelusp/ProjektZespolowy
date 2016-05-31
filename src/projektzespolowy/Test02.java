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
        stacje = new Stacja[7];
        Vector<Wyniki> wyniki2 = new Vector<Wyniki>();

        int tab2[][] = new int[][]{//zapotrzebowania
            {0, 0, 0, 0, 0},
            {20, 13, 0, 20, 17},
            {4, 50, 20, 5, 0},
            {0, 3, 21, 9, 13},
            {16, 35, 29, 70, 12},
            {32, 0, 0, 0, 12},
            {12, 0, 0, 10, 22}
        };

        int odleglosci[][] = new int[][]{//miedzy stacjami
            {0, 3, 0, 0, 6, 12, 0},
            {3, 0, 4, 0, 6, 0, 0},
            {0, 4, 0, 0, 5, 7, 5},
            {0, 0, 0, 0, 9, 8, 0},
            {7, 6, 5, 9, 0, 0, 0},
            {12, 0, 7, 8, 0, 0, 3},
            {0, 0, 5, 0, 0, 3, 0}
        };

        for (int i = 0; i < stacje.length; i++) {
            stacje[i] = new Stacja(7);
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
            for (int j = 0; j < tab.length ; j++) {
                System.out.print(stacje[i].getPaliwo(j) + " ");

            }
            System.out.println("\n");
            System.out.print("  " + stacje[i].getZap_95() + "    " + stacje[i].getZap_98() + "    " + stacje[i].getZap_on() + "    "
                    + stacje[i].getZap_on_s() + "    " + stacje[i].getZap_on_eko());
            System.out.println("\n");
        }
//ALGORYTM 2
        for (int i = 1, it = 0; i < stacje.length; i++) {
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
            Cysterna cys = new Cysterna();
            int ilosc_przejazdow = 0;
            if (suma_benzyny % cys.MAX_KOMOR == 0) { //przeliczanie ilosci zapotrzebowania na ilosc przejazdow
                ilosc_przejazdow = suma_benzyny / (cys.MAX_KOMOR * cys.MAX_POJEMNOSC_KOMOR);
            } else {
                ilosc_przejazdow = (suma_benzyny / (cys.MAX_KOMOR * cys.MAX_POJEMNOSC_KOMOR)) + 1;
            }

            //System.out.println(ilosc_przejazdow);
            while (suma_benzyny != 0) //rozdzielanie do cysterny
            {
                for (int a = 0; a < ilosc_przejazdow; a++) {
                    System.out.print(tab[it] + " " + suma_benzyny + " ");
                    cys = new Cysterna();
                    for (int b = 0; b < cys.MAX_KOMOR; b++) {
                        if (suma_benzyny % cys.MAX_POJEMNOSC_KOMOR != 0 && suma_benzyny >= 10) { //19, 18 itd
                            suma_benzyny = suma_benzyny - cys.MAX_POJEMNOSC_KOMOR;
                            cys.komora2[b].pojemnosc = cys.MAX_POJEMNOSC_KOMOR;
                            cys.komora2[b].nazwa_paliwa = tab[it];

                        } else if (suma_benzyny % cys.MAX_POJEMNOSC_KOMOR == 0 && suma_benzyny != 0) {// 10, 20 itd
                            suma_benzyny = suma_benzyny - cys.MAX_POJEMNOSC_KOMOR;
                            cys.komora2[b].pojemnosc = cys.MAX_POJEMNOSC_KOMOR;
                            cys.komora2[b].nazwa_paliwa = tab[it];

                        } else if (suma_benzyny != 0) {//1,2,3,4
                            cys.komora2[b].pojemnosc = suma_benzyny;
                            suma_benzyny = 0;
                            cys.komora2[b].nazwa_paliwa = tab[it];

                        }
                        System.out.print(cys.komora2[b].pojemnosc + " ");
                    }
                    wyniki2.add(new Wyniki(cys, 2));
                    System.out.println();
                }

            }
            it++;
            Stacja obj = new Stacja(stacje[i]);
        }

        System.out.println("\n");
        for (int i = 0; i < wyniki2.size(); i++) {
            wyniki2.get(i).wyswietl_wynik(2);
        }
        /*
         1.dla kazdej cysterny
         2.dla kazdej stacji    
         3.dla kazdej komory
         if( stacja.zap != 0 )
         komora = stacja;
         stacja-komora;
         */
        /*
for(int i=0;i<stacje.length;i++)
    System.out.println(stacje[i].getZap_95()+ " " +stacje[i].getZap_98()+" "+stacje[i].getZap_on()+" "+stacje[i].getZap_on_eko()+" "+stacje[i].getZap_on_s());
        */
        
        for (int i = 0; i < wyniki2.size(); i++) { //dla kazdej cysterny
            for (int j = 0; j < wyniki2.get(i).getCysterna().komora2.length; j++) { //dla kazdej komory
                for (int k = 1; k < stacje.length; k++) { //dla kazdej stacji
                    if (wyniki2.get(i).getCysterna().komora2[0].nazwa_paliwa.equals("PB95") && wyniki2.get(i).getCysterna().komora2[j].pojemnosc > 0) {  // jezeli cala cysterna dla pb95 i jezeli w komoze jest cokolwiek
                        if ( stacje[k].getZap_95() >= 10 ) {
                                stacje[k].setZap_pb95(stacje[k].getZap_95() - 10);
                                int l = 0;
                                while (l < wyniki2.get(i).getCysterna().komora2[j].nazwa_stacji.length && wyniki2.get(i).getCysterna().komora2[j].nazwa_stacji[l] != ' ') {
                                    l++;
                                }
                                wyniki2.get(i).getCysterna().komora2[j].nazwa_stacji[l] = stacje[k].getNazwa();
                                
                                break;
                            } else if (stacje[k].getZap_95() > 0) {
                                int l = k + 1;
                                while ( l < stacje.length && stacje[l].getZap_95() == 0 ) {   //wyszukanie nastepnej stacji z zapotrzebowaniem
                                    l++;
                                }
                                if( l == stacje.length ) l--;
                                    System.out.println(l+" "+k);
                                    stacje[l].setZap_pb95(stacje[l].getZap_95() - (stacje[k].getZap_95() - stacje[l].getZap_95()) );
                                    stacje[k].setZap_pb95(0);

                                l = 0;
                                while (l + 1 < wyniki2.get(i).getCysterna().komora2[j].nazwa_stacji.length && wyniki2.get(i).getCysterna().komora2[j].nazwa_stacji[l] != ' ') {
                                    l++;
                                }
                                if( l == stacje.length ){
                                    wyniki2.get(i).getCysterna().komora2[j].nazwa_stacji[l] = stacje[k].getNazwa();
                                }
                                wyniki2.get(i).getCysterna().komora2[j].nazwa_stacji[l] = stacje[k].getNazwa();
                            }
                    }
                    if (wyniki2.get(i).getCysterna().komora2[0].nazwa_paliwa.equals("PB98") && wyniki2.get(i).getCysterna().komora2[j].pojemnosc > 0) {  // jezeli cala cysterna dla pb95 i jezeli w komoze jest cokolwiek
                        if ( stacje[k].getZap_98() >= 10 ) {
                                stacje[k].setZap_98(stacje[k].getZap_98() - 10);
                                int l = 0;
                                while (l < wyniki2.get(i).getCysterna().komora2[j].nazwa_stacji.length && wyniki2.get(i).getCysterna().komora2[j].nazwa_stacji[l] != ' ') {
                                    l++;
                                }
                                wyniki2.get(i).getCysterna().komora2[j].nazwa_stacji[l] = stacje[k].getNazwa();
                                break;
                            } else if (stacje[k].getZap_98() > 0) {
                                int l = k + 1;
                                while ( l < stacje.length && stacje[l].getZap_98() == 0 ) {   //wyszukanie nastepnej stacji z zapotrzebowaniem
                                    l++;
                                }
                                if( l == stacje.length ) l--;
                                    System.out.println(l+" "+k);
                                    stacje[l].setZap_98(stacje[l].getZap_98() - (stacje[k].getZap_98() - stacje[l].getZap_98()) );
                                    stacje[k].setZap_98(0);

                                l = 0;
                                while (l + 1 < wyniki2.get(i).getCysterna().komora2[j].nazwa_stacji.length && wyniki2.get(i).getCysterna().komora2[j].nazwa_stacji[l] != ' ') {
                                    l++;
                                }
                                if( l == stacje.length ){
                                    wyniki2.get(i).getCysterna().komora2[j].nazwa_stacji[l] = stacje[k].getNazwa();
                                }
                                wyniki2.get(i).getCysterna().komora2[j].nazwa_stacji[l] = stacje[k].getNazwa();
                            }
                    }
                    if (wyniki2.get(i).getCysterna().komora2[0].nazwa_paliwa.equals("ON") && wyniki2.get(i).getCysterna().komora2[j].pojemnosc > 0) {  // jezeli cala cysterna dla pb95 i jezeli w komoze jest cokolwiek
                        if ( stacje[k].getZap_on() >= 10 ) {
                                stacje[k].setZap_on(stacje[k].getZap_on()- 10);
                                int l = 0;
                                while (l < wyniki2.get(i).getCysterna().komora2[j].nazwa_stacji.length && wyniki2.get(i).getCysterna().komora2[j].nazwa_stacji[l] != ' ') {
                                    l++;
                                }
                                wyniki2.get(i).getCysterna().komora2[j].nazwa_stacji[l] = stacje[k].getNazwa();
                                break;
                            } else if (stacje[k].getZap_on() > 0) {
                                int l = k + 1;
                                while ( l < stacje.length && stacje[l].getZap_on() == 0 ) {   //wyszukanie nastepnej stacji z zapotrzebowaniem
                                    l++;
                                }
                                if( l == stacje.length ) l--;
                                    System.out.println(l+" "+k);
                                    stacje[l].setZap_on(stacje[l].getZap_on() - (stacje[k].getZap_on() - stacje[l].getZap_on()) );
                                    stacje[k].setZap_on(0);

                                l = 0;
                                while (l + 1 < wyniki2.get(i).getCysterna().komora2[j].nazwa_stacji.length && wyniki2.get(i).getCysterna().komora2[j].nazwa_stacji[l] != ' ') {
                                    l++;
                                }
                                if( l == stacje.length ){
                                    wyniki2.get(i).getCysterna().komora2[j].nazwa_stacji[l] = stacje[k].getNazwa();
                                }
                                wyniki2.get(i).getCysterna().komora2[j].nazwa_stacji[l] = stacje[k].getNazwa();
                            }
                    }
                    if (wyniki2.get(i).getCysterna().komora2[0].nazwa_paliwa.equals("ONs") && wyniki2.get(i).getCysterna().komora2[j].pojemnosc > 0) {  // jezeli cala cysterna dla pb95 i jezeli w komoze jest cokolwiek
                        if ( stacje[k].getZap_on_s()>= 10 ) {
                                stacje[k].setZap_on_s(stacje[k].getZap_on_s()- 10);
                                int l = 0;
                                while (l < wyniki2.get(i).getCysterna().komora2[j].nazwa_stacji.length && wyniki2.get(i).getCysterna().komora2[j].nazwa_stacji[l] != ' ') {
                                    l++;
                                }
                                wyniki2.get(i).getCysterna().komora2[j].nazwa_stacji[l] = stacje[k].getNazwa();
                                break;
                            } else if (stacje[k].getZap_on_s() > 0) {
                                int l = k + 1;
                                while ( l < stacje.length && stacje[l].getZap_on_s() == 0 ) {   //wyszukanie nastepnej stacji z zapotrzebowaniem
                                    l++;
                                }
                                if( l == stacje.length ) l--;
                                    System.out.println(l+" "+k);
                                    stacje[l].setZap_on_s(stacje[l].getZap_on_s() - (stacje[k].getZap_on_s() - stacje[l].getZap_on_s()) );
                                    stacje[k].setZap_on_s(0);

                                l = 0;
                                while (l + 1 < wyniki2.get(i).getCysterna().komora2[j].nazwa_stacji.length && wyniki2.get(i).getCysterna().komora2[j].nazwa_stacji[l] != ' ') {
                                    l++;
                                }
                                if( l == stacje.length ){
                                    wyniki2.get(i).getCysterna().komora2[j].nazwa_stacji[l] = stacje[k].getNazwa();
                                }
                                wyniki2.get(i).getCysterna().komora2[j].nazwa_stacji[l] = stacje[k].getNazwa();
                            }
                    }
                     if (wyniki2.get(i).getCysterna().komora2[0].nazwa_paliwa.equals("ONeko") && wyniki2.get(i).getCysterna().komora2[j].pojemnosc > 0) {  // jezeli cala cysterna dla pb95 i jezeli w komoze jest cokolwiek
                        if ( stacje[k].getZap_on_eko()>= 10 ) {
                                stacje[k].setZap_on_eko(stacje[k].getZap_on_eko()- 10);
                                int l = 0;
                                while (l < wyniki2.get(i).getCysterna().komora2[j].nazwa_stacji.length && wyniki2.get(i).getCysterna().komora2[j].nazwa_stacji[l] != ' ') {
                                    l++;
                                }
                                wyniki2.get(i).getCysterna().komora2[j].nazwa_stacji[l] = stacje[k].getNazwa();
                                break;
                            } else if (stacje[k].getZap_on_eko() > 0) {
                                int l = k + 1;
                                while ( l < stacje.length && stacje[l].getZap_on_eko() == 0 ) {   //wyszukanie nastepnej stacji z zapotrzebowaniem
                                    l++;
                                }
                                if( l == stacje.length ) l--;
                                    System.out.println(l+" "+k);
                                    stacje[l].setZap_on_eko(stacje[l].getZap_on_eko() - (stacje[k].getZap_on_eko() - stacje[l].getZap_on_eko()) );
                                    stacje[k].setZap_on_eko(0);

                                l = 0;
                                while (l + 1 < wyniki2.get(i).getCysterna().komora2[j].nazwa_stacji.length && wyniki2.get(i).getCysterna().komora2[j].nazwa_stacji[l] != ' ') {
                                    l++;
                                }
                                if( l == stacje.length ){
                                    wyniki2.get(i).getCysterna().komora2[j].nazwa_stacji[l] = stacje[k].getNazwa();
                                }
                                wyniki2.get(i).getCysterna().komora2[j].nazwa_stacji[l] = stacje[k].getNazwa();
                            }
                    }
                }
            }
        }
        
        //wypisanie
        for (int i = 0; i < wyniki2.size(); i++) { //dla kazdej cysterny
            System.out.println("cysterna z " + wyniki2.get(i).getCysterna().komora2[0].nazwa_paliwa + " :");
            for (int j = 0; j < wyniki2.get(i).getCysterna().komora2.length; j++) {   //dla kazdej komory
                System.out.print("komora " + (j + 1) + " z " + i + " cysterny ");
                for (int l = 0; l < wyniki2.get(i).getCysterna().komora2[j].nazwa_stacji.length && wyniki2.get(i).getCysterna().komora2[j].nazwa_stacji[l] != ' '; l++) {
                    System.out.print(wyniki2.get(i).getCysterna().komora2[j].nazwa_stacji[l] + " ");
                }
                System.out.println("");
            }

        }

    }
}

/*
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
       
 Stacja obj = new Stacja(stacje[b]);
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
 } else if(obj.getZap_95() != 0) {
 cys.komora2[l].pojemnosc = obj.getZap_95();
 obj.setZap_pb95(0);
 cys.komora2[l].nazwa_paliwa = "PB95";
 cys.komora2[l].nazwa_stacji[n] = obj.getNazwa();
 n++;
 }
 else if(obj.getZap_95()==0  && b<=stacje.length)
 {
 b++;   
 }
                        
 }
 System.out.print(cys.komora2[l].pojemnosc+" ");
 }
 System.out.println("");
 }
 }
 */
