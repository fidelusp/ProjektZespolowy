/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projektzespolowy;

/**
 *
 * @author Dawid
 */
public class Test01 {
    
    public static void main (String[] args)
    {
    String[] tab=new String[]{"PB98","PB95","ON","ONs","ONeko"};
    Stacja[] stacje;
    stacje = new Stacja[6];
    Zapotrzebowanie[] zap = new Zapotrzebowanie[stacje.length];
    
    int tab2[][] = new int[][]{
    {0, 0, 0, 0, 0},
    {20, 13, 0, 6, 17},
    {4, 50, 20, 5, 0},
    {0, 3, 21, 9, 13},
    {16, 35, 29, 70, 12},
    {32, 0, 2, 0, 12}
    };
    
     int odleglosci[][] = new int[][]{//miedzy stacjami
           {0, 3, 0, 0, 6, 12},
           {3, 0, 4, 0, 6, 0},
           {0, 4, 0, 0, 5, 7},
           {0, 0, 0, 0, 9, 8},
           {7, 6, 5, 9, 0, 0},
           {12, 0, 7, 8, 0, 0}
     };
    
    for(int i=0; i<stacje.length;   i++)
    {
        stacje[i] = new Stacja(6);
    }
    
    
    for(int i=0;    i<stacje.length;    i++)
    {
            stacje[i].set_nazwa((char) ('A'+i));
            stacje[i].set_odleglosci(odleglosci[i]);
            stacje[i].set_zapotrzebowanie(tab2[i]);
    }
    /////////////////////////zapotrzebowanie dla stacji
    for(int i=0;     i<stacje.length;   i++)
    {
        for(int j=0;    j<stacje.length-1;    j++)
        {
        System.out.print(stacje[i].get_nazwa()+" ");
        System.out.print(stacje[i].get_paliwo(j)+" ");
        System.out.print(stacje[i].get_zapotrzebowanie_i(j)+" ");
        }
        System.out.println("\n");
    }
    ////////////////////odleglosci
    for(int i=0;    i<stacje.length;    i++)
    {
        for(int j=0;    j<stacje[i].odleglosci.length ; j++)
        {
        System.out.print(stacje[i].odleglosci[j]+" "); 
        }
        System.out.println();
    }
     
   ShortestPath t = new ShortestPath();
   t.dijkstra(odleglosci, 0); 
    
   }
    
    
 }
    
 

