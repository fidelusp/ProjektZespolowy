package projektzespolowy;

import java.io.IOException;
import java.util.Vector;

public class Test02 {

    public static void main(String[] args) throws IOException {
        String[] tab = new String[]{"PB95", "PB98", "ON", "ONeko", "ONs"};
        Stacja[] stacje;
        stacje = new Stacja[7];
        Vector<Wyniki> wyniki2 = new Vector<Wyniki>();
        int[] sumaPaliw = new int[5];

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
            {0, 3, 0, 0, 7, 12, 0},
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
            for (int j = 0; j < tab.length; j++) {
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
            if(it<5){
                sumaPaliw[it] = suma_benzyny;
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

        for (int i = 1; i < stacje.length; i++) {
            System.out.println(stacje[i].getNazwa() + " " + stacje[i].getZap_95() + " " + " " + stacje[i].getZap_98() + " " + stacje[i].getZap_on() + " " + stacje[i].getZap_on_s() + " " + stacje[i].getZap_on_eko());
        }

        
        int rodzajPaliwa = 0, licznik = 0,l;

       // while (sumaPaliw[rodzajPaliwa] != licznik) {
            for (int i =2,numerCysterny=0; i < stacje.length; i++,numerCysterny++) {   
                i--;
                for (int j = 0; j < wyniki2.get(numerCysterny).getCysterna().komora2.length; j++) {
                    if (wyniki2.get(numerCysterny).getCysterna().komora2[0].nazwa_paliwa.equals("PB95") && stacje[i].getZap_95() >= 10) {
                        stacje[i].setZap_pb95(stacje[i].getZap_95() - 10);
                        licznik+=10;
                        l = 0;
                        while (l < wyniki2.get(numerCysterny).getCysterna().komora2[j].nazwa_stacji.length && wyniki2.get(numerCysterny).getCysterna().komora2[j].nazwa_stacji[l] != ' ') {
                            l++;
                        }
                        wyniki2.get(numerCysterny).getCysterna().komora2[j].nazwa_stacji[l] = stacje[i].getNazwa();  
                        
                       
                    }else if(wyniki2.get(numerCysterny).getCysterna().komora2[0].nazwa_paliwa.equals("PB95") && stacje[i].getZap_95() < 10 && stacje[i].getZap_95() != 0){
                        int stanKomory=stacje[i].getZap_95();
                        stacje[i].setZap_pb95(0);
                        l = 0;
                        while (l < wyniki2.get(numerCysterny).getCysterna().komora2[j].nazwa_stacji.length && wyniki2.get(numerCysterny).getCysterna().komora2[j].nazwa_stacji[l] != ' ') {
                            l++;
                        }
                        
                        wyniki2.get(numerCysterny).getCysterna().komora2[j].nazwa_stacji[l] = stacje[i].getNazwa();
                        
                        while(stanKomory!=wyniki2.get(numerCysterny).getCysterna().komora2[j].pojemnosc){
                            i++;
                            if(stacje[i].getZap_95()>(10-stanKomory)){
                                stacje[i].setZap_pb95(stacje[i].getZap_95()-(10-stanKomory));
                                stanKomory=10;
                                l = 0;
                                while (l < wyniki2.get(numerCysterny).getCysterna().komora2[j].nazwa_stacji.length && wyniki2.get(numerCysterny).getCysterna().komora2[j].nazwa_stacji[l] != ' ') {
                                    l++;
                                }
                                wyniki2.get(numerCysterny).getCysterna().komora2[j].nazwa_stacji[l] = stacje[i].getNazwa();
                            }else if(stacje[i].getZap_95()!=0){         
                                stanKomory+=stacje[i].getZap_95();
                                stacje[i].setZap_pb95(0);
                                l = 0;
                                while (l < wyniki2.get(numerCysterny).getCysterna().komora2[j].nazwa_stacji.length && wyniki2.get(numerCysterny).getCysterna().komora2[j].nazwa_stacji[l] != ' ') {
                                    l++;
                                }
                                wyniki2.get(numerCysterny).getCysterna().komora2[j].nazwa_stacji[l] = stacje[i].getNazwa();
                            }
                        }
                        
                        
                    } else{
                        if(i<stacje.length-1)
                        {
                        i++;
                        j--;
                        }
                        else break;
                    }
                }
            }

       // }
        
        //wypisanie
        for (int i = 0; i < wyniki2.size(); i++) { //dla kazdej cysterny
            System.out.println("cysterna z " + wyniki2.get(i).getCysterna().komora2[0].nazwa_paliwa + " :");
            for (int j = 0; j < wyniki2.get(i).getCysterna().komora2.length; j++) {   //dla kazdej komory
                System.out.print("komora " + (j + 1) + " z " + i + " cysterny ");
                for (int k = 0; k < wyniki2.get(i).getCysterna().komora2[j].nazwa_stacji.length && wyniki2.get(i).getCysterna().komora2[j].nazwa_stacji[k] != ' '; k++) {
                    System.out.print(wyniki2.get(i).getCysterna().komora2[j].nazwa_stacji[k] + " ");
                }
                System.out.println("");
            }
        
        }
    }

}


/*
for (int i = 0; i < wyniki2.size(); i++) { //dla kazdej cysterny
            for (int j = 0; j < wyniki2.get(i).getCysterna().komora2.length; j++) { //dla kazdej komory
                for (int k = 1; k < stacje.length; k++) { //dla kazdej stacji
                    // jezeli cala cysterna dla pb95 i jezeli w komoze jest cokolwiek
                    if (wyniki2.get(i).getCysterna().komora2[0].nazwa_paliwa.equals("PB95") && wyniki2.get(i).getCysterna().komora2[j].pojemnosc > 0) {
                        if ( stacje[k].getZap_95() >= 10 ) {
                                stacje[k].setZap_pb95(stacje[k].getZap_95() - 10);
                                int l = 0;
                                while (l < wyniki2.get(i).getCysterna().komora2[j].nazwa_stacji.length && wyniki2.get(i).getCysterna().komora2[j].nazwa_stacji[l] != ' ') {
                                    l++;
                                }
                                wyniki2.get(i).getCysterna().komora2[j].nazwa_stacji[l] = stacje[k].getNazwa();
                                break;
                            } else if ( stacje[k].getZap_95() > 0 && stacje[k].getZap_95() < 10 ) {
                                int l = k + 1;
                                while ( l < stacje.length && stacje[l].getZap_95() == 0 ) {   //wyszukanie nastepnej stacji z zapotrzebowaniem
                                    l++;
                                }
                                if( l == stacje.length ) l--;
                                    System.out.println(l+" "+k);
                                    stacje[l].setZap_pb95(stacje[l].getZap_95() - (10 - stacje[k].getZap_95()) );
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
 */
