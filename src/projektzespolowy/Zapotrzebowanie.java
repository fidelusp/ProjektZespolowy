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
public class Zapotrzebowanie {
    private int zap_pb95;
    private int zap_pb98;
    private int zap_on;
    private int zap_on_s;
    private int zap_on_eko;
    
    public int get_pb95()
    {
     return zap_pb95;
    }
    
    public int get_pb98()
    {
     return zap_pb98;
    }
    
    public int get_on()
    {
     return zap_on;
    }
    
    public int get_on_s()
    {
     return zap_on_s;
    }
    
    public int get_on_eko()
    {
     return zap_on_eko;
    }
    
    public void set_on(int a)
    {
    this.zap_on=a;
    }
    
    public void set_on_eko(int a)
    {
    this.zap_on_eko=a;
    }
    
    public void set_on_s(int a)
    {
    this.zap_on_s=a;
    }
    
    public void set_pb95(int a)
    {
    this.zap_pb95=a;
    }
    
    public void set_pb98(int a)
    {
    this.zap_pb98=a;
    }
    
    public Zapotrzebowanie()
    {
    zap_pb95=0;
    zap_pb98=0;
    zap_on=0;
    zap_on_s=0;
    zap_on_eko=0;
    }
    
    public Zapotrzebowanie(int a, int b, int c, int d, int e)
    {
    zap_pb95=a;
    zap_pb98=b;
    zap_on=c;
    zap_on_s=d;
    zap_on_eko=e;
    }
    
    public Zapotrzebowanie(int[] tab)
    {
    zap_pb95=tab[0];
    zap_pb98=tab[1];
    zap_on=tab[2];
    zap_on_s=tab[3];
    zap_on_eko=tab[4];
    }
    
    public Zapotrzebowanie(Zapotrzebowanie tab)
    {
    zap_pb95=tab.zap_pb95;
    zap_pb98=tab.zap_pb98;
    zap_on=tab.zap_on;
    zap_on_s=tab.zap_on_s;
    zap_on_eko=tab.zap_on_eko;
    }
    
}
