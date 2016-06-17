package projektzespolowy;

import java.io.IOException;
import java.util.Vector;

public class Test02 {

    public static void test02(Vector<Wyniki> wyniki1,int[][] tab2 ) throws IOException {
        String[] tab = new String[]{"PB95", "PB98", "ON", "ONeko", "ONs"};
        Stacja[] stacje;
        stacje = new Stacja[7];

/*
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
*/
        for (int i = 0; i < stacje.length; i++) {
            stacje[i] = new Stacja(OknoInicjalizujace.iloscStacji);
        }

        for (int i = 0; i < stacje.length; i++) {
            stacje[i].set_nazwa((char) ('A' + i - 1));
           // stacje[i].set_odleglosci(odleglosci[i]);
            stacje[i].setZap_pb95(tab2[i][0]);
            stacje[i].setZap_98(tab2[i][1]);
            stacje[i].setZap_on(tab2[i][2]);
            stacje[i].setZap_on_s(tab2[i][3]);
            stacje[i].setZap_on_eko(tab2[i][4]);
            stacje[i].sumuj_zapotrzebowanie();
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

                    }
                    wyniki1.add(new Wyniki(cys, 2));
                    System.out.println();
                }

            }
            it++;
            Stacja obj = new Stacja(stacje[i]);
        }

        System.out.println("\n");
        for (int i = 0; i < wyniki1.size(); i++) {
            wyniki1.get(i).wyswietl_wynik(2);
        }

        int stanKomory, l,numerCysterny = 0;
                                                  /// paliwo PB95

        for (int i = 2 ; i < stacje.length; i++, numerCysterny++) {   //dla każdej stacji
            i--;
            for (int j = 0; j < wyniki1.get(numerCysterny).getCysterna().komora2.length; j++) {        // dla kazdej komory

                if (wyniki1.get(numerCysterny).getCysterna().komora2[0].nazwa_paliwa.equals("PB95") && stacje[i].getZap_95() >= 10) { //jezeli paliwa >= 10
                    stacje[i].setZap_pb95(stacje[i].getZap_95() - 10);

                    l = 0;
                    while (l < wyniki1.get(numerCysterny).getCysterna().komora2[j].nazwa_stacji.length && wyniki1.get(numerCysterny).getCysterna().komora2[j].nazwa_stacji[l] != ' ') {
                        l++;
                    }
                    wyniki1.get(numerCysterny).getCysterna().komora2[j].nazwa_stacji[l] = stacje[i].getNazwa();

                } else if (wyniki1.get(numerCysterny).getCysterna().komora2[0].nazwa_paliwa.equals("PB95") && stacje[i].getZap_95() < 10 && stacje[i].getZap_95() != 0) { // jeżeli paliwa od 1 do 9
                    stanKomory = stacje[i].getZap_95();
                    stacje[i].setZap_pb95(0);
                    l = 0;
                    while (l < wyniki1.get(numerCysterny).getCysterna().komora2[j].nazwa_stacji.length && wyniki1.get(numerCysterny).getCysterna().komora2[j].nazwa_stacji[l] != ' ') {
                        l++;
                    }

                    wyniki1.get(numerCysterny).getCysterna().komora2[j].nazwa_stacji[l] = stacje[i].getNazwa();

                    while (stanKomory != wyniki1.get(numerCysterny).getCysterna().komora2[j].pojemnosc) {       //jezeli komora nie jest skonczona
                        i++;
                        if (stacje[i].getZap_95() > (10 - stanKomory)) {          // gdy dana stacja ma wieksze zapotrzebowanie niz wolnego miejsca w komorze
                            stacje[i].setZap_pb95(stacje[i].getZap_95() - (10 - stanKomory));
                            stanKomory = 10;
                            l = 0;
                            while (l < wyniki1.get(numerCysterny).getCysterna().komora2[j].nazwa_stacji.length && wyniki1.get(numerCysterny).getCysterna().komora2[j].nazwa_stacji[l] != ' ') {
                                l++;
                            }
                            wyniki1.get(numerCysterny).getCysterna().komora2[j].nazwa_stacji[l] = stacje[i].getNazwa();
                        } else if (stacje[i].getZap_95() != 0) {         // gdy zmiesci sie wszystko do komory
                            stanKomory += stacje[i].getZap_95();
                            stacje[i].setZap_pb95(0);
                            l = 0;
                            while (l < wyniki1.get(numerCysterny).getCysterna().komora2[j].nazwa_stacji.length && wyniki1.get(numerCysterny).getCysterna().komora2[j].nazwa_stacji[l] != ' ') {
                                l++;
                            }
                            wyniki1.get(numerCysterny).getCysterna().komora2[j].nazwa_stacji[l] = stacje[i].getNazwa();
                        }
                    }

                } else if (i < stacje.length - 1) {  // gdy dana stacja jest pusta i musi przejść do następnej
                    i++;
                    j--;
                } else {
                    break;
                }
            }
        }

                                             // paliwo PB98
        for (int i = 2; i < stacje.length; i++, numerCysterny++) {   //dla każdej stacji
            i--;
            for (int j = 0; j < wyniki1.get(numerCysterny).getCysterna().komora2.length; j++) {        // dla kazdej komory

                if (wyniki1.get(numerCysterny).getCysterna().komora2[0].nazwa_paliwa.equals("PB98") && stacje[i].getZap_98() >= 10) { //jezeli paliwa >= 10
                    stacje[i].setZap_98(stacje[i].getZap_98() - 10);

                    l = 0;
                    while (l < wyniki1.get(numerCysterny).getCysterna().komora2[j].nazwa_stacji.length && wyniki1.get(numerCysterny).getCysterna().komora2[j].nazwa_stacji[l] != ' ') {
                        l++;
                    }
                    wyniki1.get(numerCysterny).getCysterna().komora2[j].nazwa_stacji[l] = stacje[i].getNazwa();

                } else if (wyniki1.get(numerCysterny).getCysterna().komora2[0].nazwa_paliwa.equals("PB98") && stacje[i].getZap_98() < 10 && stacje[i].getZap_98() != 0) { // jeżeli paliwa od 1 do 9
                    stanKomory = stacje[i].getZap_98();
                    stacje[i].setZap_98(0);
                    l = 0;
                    while (l < wyniki1.get(numerCysterny).getCysterna().komora2[j].nazwa_stacji.length && wyniki1.get(numerCysterny).getCysterna().komora2[j].nazwa_stacji[l] != ' ') {
                        l++;
                    }

                    wyniki1.get(numerCysterny).getCysterna().komora2[j].nazwa_stacji[l] = stacje[i].getNazwa();

                    while (stanKomory != wyniki1.get(numerCysterny).getCysterna().komora2[j].pojemnosc) {       //jezeli komora nie jest skonczona
                        i++;
                        if (stacje[i].getZap_98() > (10 - stanKomory)) {          // gdy dana stacja ma wieksze zapotrzebowanie niz wolnego miejsca w komorze
                            stacje[i].setZap_98(stacje[i].getZap_98() - (10 - stanKomory));
                            stanKomory = 10;
                            l = 0;
                            while (l < wyniki1.get(numerCysterny).getCysterna().komora2[j].nazwa_stacji.length && wyniki1.get(numerCysterny).getCysterna().komora2[j].nazwa_stacji[l] != ' ') {
                                l++;
                            }
                            wyniki1.get(numerCysterny).getCysterna().komora2[j].nazwa_stacji[l] = stacje[i].getNazwa();
                        } else if (stacje[i].getZap_98() != 0) {         // gdy zmiesci sie wszystko do komory
                            stanKomory += stacje[i].getZap_98();
                            stacje[i].setZap_98(0);
                            l = 0;
                            while (l < wyniki1.get(numerCysterny).getCysterna().komora2[j].nazwa_stacji.length && wyniki1.get(numerCysterny).getCysterna().komora2[j].nazwa_stacji[l] != ' ') {
                                l++;
                            }
                            wyniki1.get(numerCysterny).getCysterna().komora2[j].nazwa_stacji[l] = stacje[i].getNazwa();
                        }
                    }

                } else if (i < stacje.length - 1) {  // gdy dana stacja jest pusta i musi przejść do następnej
                    i++;
                    j--;
                } else {
                    break;
                }
            }
        }
        // Palwio ON
        for (int i = 2 ; i < stacje.length; i++, numerCysterny++) {   //dla każdej stacji
            i--;
            for (int j = 0; j < wyniki1.get(numerCysterny).getCysterna().komora2.length; j++) {        // dla kazdej komory

                if (wyniki1.get(numerCysterny).getCysterna().komora2[0].nazwa_paliwa.equals("ON") && stacje[i].getZap_on() >= 10) { //jezeli paliwa >= 10
                    stacje[i].setZap_on(stacje[i].getZap_on() - 10);

                    l = 0;
                    while (l < wyniki1.get(numerCysterny).getCysterna().komora2[j].nazwa_stacji.length && wyniki1.get(numerCysterny).getCysterna().komora2[j].nazwa_stacji[l] != ' ') {
                        l++;
                    }
                    wyniki1.get(numerCysterny).getCysterna().komora2[j].nazwa_stacji[l] = stacje[i].getNazwa();

                } else if (wyniki1.get(numerCysterny).getCysterna().komora2[0].nazwa_paliwa.equals("ON") && stacje[i].getZap_on() < 10 && stacje[i].getZap_on() != 0) { // jeżeli paliwa od 1 do 9
                    stanKomory = stacje[i].getZap_on();
                    stacje[i].setZap_on(0);
                    l = 0;
                    while (l < wyniki1.get(numerCysterny).getCysterna().komora2[j].nazwa_stacji.length && wyniki1.get(numerCysterny).getCysterna().komora2[j].nazwa_stacji[l] != ' ') {
                        l++;
                    }

                    wyniki1.get(numerCysterny).getCysterna().komora2[j].nazwa_stacji[l] = stacje[i].getNazwa();

                    while (stanKomory != wyniki1.get(numerCysterny).getCysterna().komora2[j].pojemnosc) {       //jezeli komora nie jest skonczona
                        i++;
                        if (stacje[i].getZap_on() > (10 - stanKomory)) {          // gdy dana stacja ma wieksze zapotrzebowanie niz wolnego miejsca w komorze
                            stacje[i].setZap_on(stacje[i].getZap_on() - (10 - stanKomory));
                            stanKomory = 10;
                            l = 0;
                            while (l < wyniki1.get(numerCysterny).getCysterna().komora2[j].nazwa_stacji.length && wyniki1.get(numerCysterny).getCysterna().komora2[j].nazwa_stacji[l] != ' ') {
                                l++;
                            }
                            wyniki1.get(numerCysterny).getCysterna().komora2[j].nazwa_stacji[l] = stacje[i].getNazwa();
                        } else if (stacje[i].getZap_on() != 0) {         // gdy zmiesci sie wszystko do komory
                            stanKomory += stacje[i].getZap_on();
                            stacje[i].setZap_on(0);
                            l = 0;
                            while (l < wyniki1.get(numerCysterny).getCysterna().komora2[j].nazwa_stacji.length && wyniki1.get(numerCysterny).getCysterna().komora2[j].nazwa_stacji[l] != ' ') {
                                l++;
                            }
                            wyniki1.get(numerCysterny).getCysterna().komora2[j].nazwa_stacji[l] = stacje[i].getNazwa();
                        }
                    }

                } else if (i < stacje.length - 1) {  // gdy dana stacja jest pusta i musi przejść do następnej
                    i++;
                    j--;
                } else {
                    break;
                }
            }
        }
        
                // Palwio ON_eko
        for (int i = 2 ; i < stacje.length; i++, numerCysterny++) {   //dla każdej stacji
            i--;
            for (int j = 0; j < wyniki1.get(numerCysterny).getCysterna().komora2.length; j++) {        // dla kazdej komory

                if (wyniki1.get(numerCysterny).getCysterna().komora2[0].nazwa_paliwa.equals("ONeko") && stacje[i].getZap_on_eko()>= 10) { //jezeli paliwa >= 10
                    stacje[i].setZap_on_eko(stacje[i].getZap_on_eko() - 10);

                    l = 0;
                    while (l < wyniki1.get(numerCysterny).getCysterna().komora2[j].nazwa_stacji.length && wyniki1.get(numerCysterny).getCysterna().komora2[j].nazwa_stacji[l] != ' ') {
                        l++;
                    }
                    wyniki1.get(numerCysterny).getCysterna().komora2[j].nazwa_stacji[l] = stacje[i].getNazwa();

                } else if (wyniki1.get(numerCysterny).getCysterna().komora2[0].nazwa_paliwa.equals("ONeko") && stacje[i].getZap_on_eko() < 10 && stacje[i].getZap_on_eko() != 0) { // jeżeli paliwa od 1 do 9
                    stanKomory = stacje[i].getZap_on_eko();
                    stacje[i].setZap_on_eko(0);
                    l = 0;
                    while (l < wyniki1.get(numerCysterny).getCysterna().komora2[j].nazwa_stacji.length && wyniki1.get(numerCysterny).getCysterna().komora2[j].nazwa_stacji[l] != ' ') {
                        l++;
                    }

                    wyniki1.get(numerCysterny).getCysterna().komora2[j].nazwa_stacji[l] = stacje[i].getNazwa();

                    while (stanKomory != wyniki1.get(numerCysterny).getCysterna().komora2[j].pojemnosc) {       //jezeli komora nie jest skonczona
                        i++;
                        if (stacje[i].getZap_on_eko() > (10 - stanKomory)) {          // gdy dana stacja ma wieksze zapotrzebowanie niz wolnego miejsca w komorze
                            stacje[i].setZap_on_eko(stacje[i].getZap_on_eko() - (10 - stanKomory));
                            stanKomory = 10;
                            l = 0;
                            while (l < wyniki1.get(numerCysterny).getCysterna().komora2[j].nazwa_stacji.length && wyniki1.get(numerCysterny).getCysterna().komora2[j].nazwa_stacji[l] != ' ') {
                                l++;
                            }
                            wyniki1.get(numerCysterny).getCysterna().komora2[j].nazwa_stacji[l] = stacje[i].getNazwa();
                        } else if (stacje[i].getZap_on_eko() != 0) {         // gdy zmiesci sie wszystko do komory
                            stanKomory += stacje[i].getZap_on_eko();
                            stacje[i].setZap_on_eko(0);
                            l = 0;
                            while (l < wyniki1.get(numerCysterny).getCysterna().komora2[j].nazwa_stacji.length && wyniki1.get(numerCysterny).getCysterna().komora2[j].nazwa_stacji[l] != ' ') {
                                l++;
                            }
                            wyniki1.get(numerCysterny).getCysterna().komora2[j].nazwa_stacji[l] = stacje[i].getNazwa();
                        }
                    }

                } else if (i < stacje.length - 1) {  // gdy dana stacja jest pusta i musi przejść do następnej
                    i++;
                    j--;
                } else {
                    break;
                }
            }
        }
        
        
                        // Palwio ON_s
        for (int i = 2 ; i < stacje.length; i++, numerCysterny++) {   //dla każdej stacji
            i--;
            for (int j = 0; j < wyniki1.get(numerCysterny).getCysterna().komora2.length; j++) {        // dla kazdej komory

                if (wyniki1.get(numerCysterny).getCysterna().komora2[0].nazwa_paliwa.equals("ONs") && stacje[i].getZap_on_s()>= 10) { //jezeli paliwa >= 10
                    stacje[i].setZap_on_s(stacje[i].getZap_on_s() - 10);

                    l = 0;
                    while (l < wyniki1.get(numerCysterny).getCysterna().komora2[j].nazwa_stacji.length && wyniki1.get(numerCysterny).getCysterna().komora2[j].nazwa_stacji[l] != ' ') {
                        l++;
                    }
                    wyniki1.get(numerCysterny).getCysterna().komora2[j].nazwa_stacji[l] = stacje[i].getNazwa();

                } else if (wyniki1.get(numerCysterny).getCysterna().komora2[0].nazwa_paliwa.equals("ONs") && stacje[i].getZap_on_s() < 10 && stacje[i].getZap_on_s() != 0) { // jeżeli paliwa od 1 do 9
                    stanKomory = stacje[i].getZap_on_s();
                    stacje[i].setZap_on_s(0);
                    l = 0;
                    while (l < wyniki1.get(numerCysterny).getCysterna().komora2[j].nazwa_stacji.length && wyniki1.get(numerCysterny).getCysterna().komora2[j].nazwa_stacji[l] != ' ') {
                        l++;
                    }

                    wyniki1.get(numerCysterny).getCysterna().komora2[j].nazwa_stacji[l] = stacje[i].getNazwa();

                    while (stanKomory != wyniki1.get(numerCysterny).getCysterna().komora2[j].pojemnosc) {       //jezeli komora nie jest skonczona
                        i++;
                        if (stacje[i].getZap_on_s() > (10 - stanKomory)) {          // gdy dana stacja ma wieksze zapotrzebowanie niz wolnego miejsca w komorze
                            stacje[i].setZap_on_s(stacje[i].getZap_on_s() - (10 - stanKomory));
                            stanKomory = 10;
                            l = 0;
                            while (l < wyniki1.get(numerCysterny).getCysterna().komora2[j].nazwa_stacji.length && wyniki1.get(numerCysterny).getCysterna().komora2[j].nazwa_stacji[l] != ' ') {
                                l++;
                            }
                            wyniki1.get(numerCysterny).getCysterna().komora2[j].nazwa_stacji[l] = stacje[i].getNazwa();
                        } else if (stacje[i].getZap_on_s() != 0) {         // gdy zmiesci sie wszystko do komory
                            stanKomory += stacje[i].getZap_on_s();
                            stacje[i].setZap_on_s(0);
                            l = 0;
                            while (l < wyniki1.get(numerCysterny).getCysterna().komora2[j].nazwa_stacji.length && wyniki1.get(numerCysterny).getCysterna().komora2[j].nazwa_stacji[l] != ' ') {
                                l++;
                            }
                            wyniki1.get(numerCysterny).getCysterna().komora2[j].nazwa_stacji[l] = stacje[i].getNazwa();
                        }
                    }

                } else if (i < stacje.length - 1) {  // gdy dana stacja jest pusta i musi przejść do następnej
                    i++;
                    j--;
                } else {
                    break;
                }
            }
        }

        //wypisanie
        for (int i = 0; i < wyniki1.size(); i++) { //dla kazdej cysterny
            System.out.println("cysterna z " + wyniki1.get(i).getCysterna().komora2[0].nazwa_paliwa + " :");
            for (int j = 0; j < wyniki1.get(i).getCysterna().komora2.length; j++) {   //dla kazdej komory
                System.out.print("komora " + (j + 1) + " z " + i + " cysterny ");
                for (int k = 0; k < wyniki1.get(i).getCysterna().komora2[j].nazwa_stacji.length && wyniki1.get(i).getCysterna().komora2[j].nazwa_stacji[k] != ' '; k++) {
                    System.out.print(wyniki1.get(i).getCysterna().komora2[j].nazwa_stacji[k] + " ");
                }
                System.out.println("");
            }

        }
    }

}
