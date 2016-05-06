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
    
    private String nazwa_cysterny;
    private Komora[] komora;
    //dupa
    
    public class Komora{
        private int pojemnosc;
        String[] nazwa_paliwa;
    };
}
