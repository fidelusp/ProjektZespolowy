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
   //private String nazwa_cysterny;
    public Komora[] komora = new Komora[MAX_KOMOR];
    
    Cysterna()
    {
        for(int i=0 ; i<komora.length ;i++){
            komora[i] = new Komora();
        }
    }
    
    public class Komora{
        public int pojemnosc;
        String nazwa_paliwa = new String("");
        char nazwa_stacji;
        
        public Komora()
        {
            this.pojemnosc=0;
            this.nazwa_paliwa=new String("");
            this.nazwa_stacji=' ';
        }
        
        
    };
}
