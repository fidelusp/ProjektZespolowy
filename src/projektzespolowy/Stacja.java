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
    private int zap_pb95;
    private int zap_98;
    private int zap_on;
    private int zap_on_s;
    private int zap_on_eko;
    public int suma_zapotrzebowan;

    public Stacja(Stacja kopia){
    this.nazwa=kopia.nazwa;
    this.paliwo=kopia.paliwo;   
    this.odleglosci=kopia.odleglosci;
    this.zap_pb95=kopia.zap_pb95;
    this.zap_98=kopia.zap_98;
    this.zap_on=kopia.zap_on;
    this.zap_on_s=kopia.zap_on_s;
    this.zap_on_eko=kopia.zap_on_eko;
    this.suma_zapotrzebowan=kopia.suma_zapotrzebowan;
    }
    public Stacja(){
    nazwa = ' ';
    paliwo = new String[0];    //  5
    //private Zapotrzebowanie zapotrzebowanie;  //  5
    odleglosci = new int[0];
    zap_pb95 = 0;
    zap_98 = 0;
    zap_on = 0;
    zap_on_s = 0;
    zap_on_eko = 0;
    suma_zapotrzebowan = 0;
    }
    
    public void setZap_pb95(int zap_pb95) {
        this.zap_pb95 = zap_pb95;
    }

    public void setZap_98(int zap_98) {
        this.zap_98 = zap_98;
    }

    public void setZap_on(int zap_on) {
        this.zap_on = zap_on;
    }

    public void setZap_on_s(int zap_on_s) {
        this.zap_on_s = zap_on_s;
    }

    public void setZap_on_eko(int zap_on_eko) {
        this.zap_on_eko = zap_on_eko;
    }
    

    public char getNazwa() {
        return nazwa;
    }

    public String getPaliwo(int j) {
        if(j<paliwo.length)
        {
            return paliwo[j];
        }
        else return null;
    }

    public int[] getOdleglosci() {
        return odleglosci;
    }

    public int getZap_95() {
        return zap_pb95;
    }

    public int getZap_98() {
        return zap_98;
    }

    public int getZap_on() {
        return zap_on;
    }

    public int getZap_on_s() {
        return zap_on_s;
    }

    public int getZap_on_eko() {
        return zap_on_eko;
    }
    
    public void sumuj_zapotrzebowanie()
    {
        suma_zapotrzebowan=zap_pb95+zap_98+zap_on+zap_on_eko+zap_on_s;
    }
    
    public Stacja(int pb5,int pb8,int on, int onE, int onS){
    zap_pb95 = pb5;
    zap_98 = pb8;
    zap_on = on;
    zap_on_s = onS;
    zap_on_eko = onE; 
    }
    
    public Stacja(int x){
        paliwo=new String[]{"PB95","PB98","ON","ONs","ONeko"};
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
    
}
