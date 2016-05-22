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
public class Wyniki {
    private int dlugosc_trasy;  //suma dlugosci wszystkich tras przez ktore ma pzrejechac cysterna
    private Stacja stacje;  //stacje przez ktore musi przejechac cysterna
/*    
    private int wynik_pb95;
    private int wynik_98;
    private int wynik_on;
    private int wynik_on_s;
    private int wynik_on_eko;
  */  
    Wyniki(Cysterna cys){
        stacje = new Stacja();
        stacje.set_nazwa(cys.komora[0].nazwa_stacji);
        dlugosc_trasy = cys.dlugosc_trasy;
        //this.dlugosc_trasy = cys.dlugosc_trasy;
        for(int i=0;    i<cys.komora.length;    i++)
        {
            if(cys.komora[i].nazwa_paliwa.equals("PB95"))
            {
                stacje.setZap_pb95( stacje.getZap_95() + cys.komora[i].pojemnosc);
            }
            if(cys.komora[i].nazwa_paliwa.equals("PB98"))
            {
                stacje.setZap_98(stacje.getZap_98() + cys.komora[i].pojemnosc);
            }
            if(cys.komora[i].nazwa_paliwa.equals("ON"))
            {
                stacje.setZap_on(stacje.getZap_on()+ cys.komora[i].pojemnosc);
            }
            if(cys.komora[i].nazwa_paliwa.equals("ONs"))
            {
                stacje.setZap_on_s(stacje.getZap_on_s() + cys.komora[i].pojemnosc);
            }
            if(cys.komora[i].nazwa_paliwa.equals("ONeko"))
            {
                stacje.setZap_on_eko(stacje.getZap_on_eko()+ cys.komora[i].pojemnosc);
            }            
        }
    }
   public void wyswietl_wynik(){
       System.out.println(stacje.getNazwa()+" "+dlugosc_trasy+" "+stacje.getZap_95()+" "+stacje.getZap_98()+" "+stacje.getZap_on()+" "+stacje.getZap_on_eko()+" "+stacje.getZap_on_s());
   }
}
