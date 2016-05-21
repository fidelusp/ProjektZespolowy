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
public class Stacja {
    private char nazwa;
    private String[] paliwo;    //  5
    private Zapotrzebowanie zapotrzebowanie;  //  5
    public int[] odleglosci;
    
    public Stacja(int x)
    {
        paliwo=new String[]{"PB98","PB95","ON","ONs","ONeko"};
        nazwa = ' ';
        zapotrzebowanie = new Zapotrzebowanie();
        odleglosci = new int[x];
    }
    
    public void set_nazwa(char nazwa)
    {
        this.nazwa=nazwa;
    }
    
     public void set_paliwo(String[] paliwo)
    {
        for(int i=0;    i<paliwo.length;    i++)
            
        this.paliwo[i]=paliwo[i];
    } 
     
    public void set_zapotrzebowanie(Zapotrzebowanie zapotrzebowanie)
    {   
          this.zapotrzebowanie.set_on(zapotrzebowanie.get_on());
          this.zapotrzebowanie.set_on_eko(zapotrzebowanie.get_on_eko());
          this.zapotrzebowanie.set_on_s(zapotrzebowanie.get_on_s());
          this.zapotrzebowanie.set_pb95(zapotrzebowanie.get_pb95());
          this.zapotrzebowanie.set_pb98(zapotrzebowanie.get_pb98());
    }
    
    public void set_zapotrzebowanie(int[] zapotrzebowanie)
    {
          this.zapotrzebowanie.set_pb95(zapotrzebowanie[0]);
          this.zapotrzebowanie.set_pb98(zapotrzebowanie[1]);
          this.zapotrzebowanie.set_on(zapotrzebowanie[2]);
          this.zapotrzebowanie.set_on_s(zapotrzebowanie[3]);
          this.zapotrzebowanie.set_on_eko(zapotrzebowanie[4]);
    }
    
    public void set_odleglosci(int[] odl)
    {
    for(int i=0;    i<odleglosci.length;    i++)
    {
        this.odleglosci[i]=odl[i];
    }
    }
    
    public int get_zapotrzebowanie_i(int j)
    {
      switch(j){
          case 0:
              return zapotrzebowanie.get_pb95();
          case 1:
              return zapotrzebowanie.get_pb98();
          case 2:
              return zapotrzebowanie.get_on();
          case 3:
              return zapotrzebowanie.get_on_s();
          case 4:
              return zapotrzebowanie.get_on_eko();
          default:
              return 0;
      }
    }
     
    public char get_nazwa()
    {
        return nazwa;
    }
    
     public String get_paliwo(int j)
    {
       if(j<paliwo.length)
       {
       return paliwo[j];
       }
       else return null;
    } 
    
}
