/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projektzespolowy;

/**
 *
 * @author PasterzM
 */
public class Cysterna {

    public final int MAX_KOMOR = 5;
    public final int MAX_POJEMNOSC_KOMOR = 10;
    public int dlugosc_trasy;
    public Komora[] komora = new Komora[MAX_KOMOR];
    public Komora2[] komora2 = new Komora2[MAX_KOMOR];

    public Cysterna() {
        for (int i = 0; i < komora.length; i++) {
            komora[i] = new Komora();
        }
        for (int i = 0; i < komora.length; i++) {
            komora2[i] = new Komora2();
        }
    }

    Cysterna(Cysterna cys) {
      
        for (int i = 0; i < komora.length; i++) {
            komora[i] = new Komora();
        }
        for (int i = 0; i < komora.length; i++) {
            komora2[i] = new Komora2();
        }
       komora2 = cys.komora2;
       dlugosc_trasy = cys.dlugosc_trasy;
    }

    public class Komora {

        public int pojemnosc;
        String nazwa_paliwa = new String("");
        char nazwa_stacji;

        public Komora() {
            this.pojemnosc = 0;
            this.nazwa_paliwa = new String("");
            this.nazwa_stacji = ' ';
        }

    };

    public class Komora2 {

        public int pojemnosc;
        public String nazwa_paliwa = new String("");
        public char[] nazwa_stacji;
        public int[] ilosc ;
        
        public Komora2() {
            this.pojemnosc = 0;
            this.nazwa_paliwa = new String("");
            this.nazwa_stacji = new char[6]; //dle testow potem zamienic
            for(int i=0; i<nazwa_stacji.length ;i++)
                nazwa_stacji[i] = ' ';
            ilosc = new int[nazwa_stacji.length];
        }
    };
}
