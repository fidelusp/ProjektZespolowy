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
        Vector<Wyniki> wyniki = new Vector<Wyniki>();

        int tab2[][] = new int[][]{//zapotrzebowania
            {0, 0, 0, 0, 0},
            {20, 13, 0, 20, 17},
            {4, 50, 20, 5, 0},
            {0, 3, 21, 9, 13},
            {16, 35, 29, 70, 12},
            {32, 0, 2, 0, 12},
            {12, 0, 7, 10, 22}
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
            for (int j = 0; j < stacje.length - 1; j++) {
                System.out.print(stacje[i].getPaliwo(j) + " ");

            }
            System.out.println("\n");
            System.out.print("  " + stacje[i].getZap_95() + "    " + stacje[i].getZap_98() + "    " + stacje[i].getZap_on() + "    "
                    + stacje[i].getZap_on_s() + "    " + stacje[i].getZap_on_eko());
            System.out.println("\n");
        }
//ALGORYTM 2
        for (int i = 1, it = 0; i < stacje.length; i++) 
        {
            int suma_benzyny = 0;

            for (int j = 0; j < stacje.length; j++) 
            {    //zliczanie zapotrzebowania na dana benzyne
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
            while(suma_benzyny!=0)
            {
                for(int a=0;    a<ilosc_przejazdow; a++)
                {
                    System.out.print(tab[it]+" "+suma_benzyny+" ");
                    cys = new Cysterna();
                    for(int b=0;    b<cys.MAX_KOMOR;    b++)
                    {
                         if (suma_benzyny % cys.MAX_POJEMNOSC_KOMOR != 0 && suma_benzyny >= 10) { //19, 18 itd
                            suma_benzyny = suma_benzyny - cys.MAX_POJEMNOSC_KOMOR;
                            cys.komora2[b].pojemnosc = cys.MAX_POJEMNOSC_KOMOR;
                            cys.komora2[b].nazwa_paliwa = tab[it];
                         
                        }else if (suma_benzyny % cys.MAX_POJEMNOSC_KOMOR == 0 && suma_benzyny != 0) {// 10, 20 itd
                            suma_benzyny = suma_benzyny - cys.MAX_POJEMNOSC_KOMOR;
                            cys.komora2[b].pojemnosc = cys.MAX_POJEMNOSC_KOMOR;
                            cys.komora2[b].nazwa_paliwa = tab[it];
                            
                        } else if(suma_benzyny  != 0) {//1,2,3,4
                            cys.komora2[b].pojemnosc = suma_benzyny;
                            suma_benzyny=0;
                            cys.komora2[b].nazwa_paliwa = tab[it];
                         
                        }
                       System.out.print(cys.komora2[b].pojemnosc+" ");
                    }
                    //wyniki.add(new Wyniki(cys));
                    System.out.println();
                }
                
            }
            it++;
        Stacja obj = new Stacja(stacje[i]);
        for(int j1=0; j1<ilosc_przejazdow;    j1++)
        {
            for(int k=0;    k<cys.MAX_KOMOR;    k++)
            {
                while(cys.komora2[k].pojemnosc != cys.komora2[k].ilosc[i])
                {
                if(obj.getZap_95() >= cys.komora2[k].pojemnosc)
                {
                    cys.komora2[k].ilosc[0]=cys.MAX_POJEMNOSC_KOMOR;
                    cys.komora2[k].nazwa_stacji[0]=obj.getNazwa();
                }
                else
                {
                    cys.komora2[k].ilosc[0]=obj.getZap_95();
                    while(cys.komora2[k].nazwa_stacji[y] != ' ')
                    cys.komora2[k].nazwa_stacji[0]=obj.getNazwa();
                    //przejdz na nastepna stacje
                    
                }
                if(obj.getZap_95()==0)
                {
                    
                }
                }
            }
//            for(int k=0;    k<cys.MAX_KOMOR;    k++)
//            {
//                if( stacje[i].getZap_95() >= cys.komora2[k].pojemnosc && (stacje[i].getZap_95() != 0))
//                {
//                 stacje[i].setZap_pb95(stacje[i].getZap_95()-cys.MAX_POJEMNOSC_KOMOR); 
//                 cys.komora2[k].nazwa_stacji[0]=stacje[i].getNazwa();
//
//                }
//                else if(stacje[i].getZap_95() !=0 )
//                {
//                    cys.komora2[k].pojemnosc-=stacje[i].getZap_95();
//                    cys.komora2[k].nazwa_stacji[0]=stacje[i].getNazwa();
//                    for(int iter = i; i<stacje.length ;iter++){
//                        if( stacje[iter].getZap_95() != 0 ){
//                            cys.komora2[1].nazwa_stacji = stacje[iter].
//                        }
//                    }
//                    stacje[i].setZap_pb95(stacje[i].getZap_95());
//                }
//            }
        }
        }
        
//     System.out.println("...");
//        for(int i=0; i<wyniki.size(); i++){
//            wyniki.get(i).wyswietl_wynik();
//        }
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
