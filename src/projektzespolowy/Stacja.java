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
    //private Zapotrzebowanie zapotrzebowanie;  //  5
    public int[] odleglosci;
    
    int zap_pb95;
    int zap_98;
    int zap_on;
    int zap_on_s;
    int zap_on_eko;
    
    public Stacja(int pb5,int pb8,int on, int onE, int onS){
    zap_pb95 = pb5;
    zap_98 = pb8;
    zap_on = on;
    zap_on_s = onS;
    zap_on_eko = onE; 
    }
    
    public Stacja(int x){
        paliwo=new String[]{"PB98","PB95","ON","ONs","ONeko"};
        nazwa = ' ';
        odleglosci = new int[x];
        zap_pb95 = 0;
        zap_98 = 0;
        zap_on = 0;
        zap_on_s = 0;
        zap_on_eko = 0;
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
     
    public void set_odleglosci(int[] odl){
        for(int i=0;    i<odleglosci.length;    i++){
            this.odleglosci[i]=odl[i];
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
