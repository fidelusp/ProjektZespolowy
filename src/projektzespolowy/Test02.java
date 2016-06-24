package projektzespolowy;

import java.io.IOException;
import java.util.Vector;

public class Test02 {

    public static void main(String[] args/*test02(Vector<Wyniki> wyniki1, int[][] tab2) throws IOException */) {
        String[] tab = new String[]{"PB95", "PB98", "ON", "ONeko", "ONs"};
        Stacja[] stacje;
        stacje = new Stacja[7];
        Vector<Wyniki> wyniki1 = new Vector<Wyniki>();

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

        int stanKomory, l, numerCysterny = 0;
        /// paliwo PB95

        for (int i = 2; i < stacje.length; i++, numerCysterny++) {   //dla każdej stacji
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
        for (int i = 2; i < stacje.length; i++, numerCysterny++) {   //dla każdej stacji
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
        for (int i = 2; i < stacje.length; i++, numerCysterny++) {   //dla każdej stacji
            i--;
            for (int j = 0; j < wyniki1.get(numerCysterny).getCysterna().komora2.length; j++) {        // dla kazdej komory

                if (wyniki1.get(numerCysterny).getCysterna().komora2[0].nazwa_paliwa.equals("ONeko") && stacje[i].getZap_on_eko() >= 10) { //jezeli paliwa >= 10
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
        for (int i = 2; i < stacje.length; i++, numerCysterny++) {   //dla każdej stacji
            i--;
            for (int j = 0; j < wyniki1.get(numerCysterny).getCysterna().komora2.length; j++) {        // dla kazdej komory

                if (wyniki1.get(numerCysterny).getCysterna().komora2[0].nazwa_paliwa.equals("ONs") && stacje[i].getZap_on_s() >= 10) { //jezeli paliwa >= 10
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

        for (int i = 0; i < wyniki1.size(); i++) { //dla każdego wyniku
            //char[] nazwyStacji = new char[OknoInicjalizujace.iloscStacji];
            char[] nazwyStacji = new char[5];
            for (int k : nazwyStacji) { // inicjalizacja tablicy pom spacjami
                k = 'x';
            }
            int s = 0;

            for (int j = 0; j < wyniki1.get(i).getCysterna().MAX_KOMOR; j++) {   //dla kazdej komory

                for (int x = 0; (x < wyniki1.get(i).getCysterna().komora2[j].nazwa_stacji.length) //sprawdza do ilu stacji jedzie
                        || (wyniki1.get(i).getCysterna().komora2[j].nazwa_stacji[x] == ' '); x++) {
                    boolean dalej = false;
                    for (int z = 0; z < nazwyStacji.length; z++) {  //sprawdza tablice pom czy dana stacja juz jest
                        if (nazwyStacji[z] == wyniki1.get(i).getCysterna().komora2[j].nazwa_stacji[x]
                                || wyniki1.get(i).getCysterna().komora2[j].nazwa_stacji[x] == ' ') {
                            dalej = true;
                        }
                    }
                    if (dalej == true) {
                        break;
                    } else {
                       
                             nazwyStacji[s] = wyniki1.get(i).getCysterna().komora2[j].nazwa_stacji[x];
                             s++;
                        
                       
                    }
                }
            }
            System.out.println(nazwyStacji[0] + " " + nazwyStacji[1] + " " + nazwyStacji[2] + " " + nazwyStacji[3] + " " + nazwyStacji[4]);
            //int[] odleglosci = new int[nazwyStacji.length];

            int od = 0;
            int min = 0;
            int das = 0;

            ShortestPath t = new ShortestPath();
            for (int z = 0; z < nazwyStacji.length; z++) {    //  dla kazdej cysterny

                t.dijkstra(odleglosci, od);
                //t.printSolution(t.dist, od);
                min = 1000;
                for (int v = 0; v < nazwyStacji.length; v++) {    //szukanie minimalnej odleglosci pomiedzy od a "nazwyStacji[0] - ('A' - 1)" 
                    // System.out.println((int)nazwyStacji[0]);

                    if(  nazwyStacji[v] ==' '){
                        continue;
                    }
                    
                    if (nazwyStacji[v] ==' ' || v==nazwyStacji.length-1) {
                        if(min==1000) min=0;
                        wyniki1.get(i).getCysterna().dlugosc_trasy += min;
                        od = das;
                        nazwyStacji[das-1] = ' ';
                        break;
                    } else if (min > t.dist[(int) (nazwyStacji[v] - ('A' - 1))] && t.dist[(int) (nazwyStacji[v] - ('A' - 1))]!=0) {
                        min = t.dist[(int) (nazwyStacji[v] - ('A' - 1))];
                        das = (int) (nazwyStacji[v] - ('A' - 1));       // zle przeskakuje z np A B "w tym miejscu" D
                    }

                }
                //wyniki1.get(i).getCysterna().dlugosc_trasy += min;
               // od = das;
               // nazwyStacji[das] = ' ';
            }
            // "od" do "bazy"
            t.dijkstra(tab2, od);
            wyniki1.get(i).getCysterna().dlugosc_trasy += t.dist[0];
            
            System.out.println(wyniki1.get(i).getCysterna().dlugosc_trasy+'\n');
                    }
    }

}

/*
t.dijkstra(tab2, (int) nazwyStacji[v] - ('A' - 1) );
                odleglosci[v] = t.dist[(int) nazwyStacji[v] - ('A' - 1)];
 */
