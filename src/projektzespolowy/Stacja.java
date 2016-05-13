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
    
     String nazwa;
     int zap_pb95;
     int zap_pb98;
     int zap_on;
     int zap_on_s;
     int zap_on_eko;
    public Stacja(int pb5,int pb8,int on,int onE, int onS){
        this.zap_pb95=pb5;
     this.zap_pb98=pb8;
     this.zap_on=on;
     this.zap_on_s=onS;
     this.zap_on_eko=onE;
    }
    
     
}
