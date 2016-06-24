/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projektzespolowy;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Dawid
 */
public class czytajplik {

    public static void WczytajOdleglosci(int[][] arg) throws FileNotFoundException {

        FileReader fr = null;
        String linia = "";
  // int ilosc_stacji=6;//1 baza + 5 stacji

        // OTWIERANIE PLIKU:
        try {
            fr = new FileReader("C:\\Users\\Mateusz\\Desktop\\ProjektZespolowy\\src\\projektzespolowy\\macierz.txt");
        } catch (FileNotFoundException e) {
            System.out.println("BŁĄD PRZY OTWIERANIU PLIKU!");
            System.exit(1);
        }

        BufferedReader bfr = new BufferedReader(fr);
        // ODCZYT KOLEJNYCH LINII Z PLIKU:
        Scanner in = new Scanner(bfr);
        String enter = "";
        boolean wyjscie = false;
        // try {
        for (int i = 0; i < arg.length; i++) {
            for (int j = 0; j < arg[i].length; j++) {
                arg[i][j] = in.nextInt();
                System.out.print(arg[i][j] + " ");
            }
            System.out.println("");
            if ((enter = in.nextLine()) == null) {
                wyjscie = true;
            }
            if (wyjscie) {
                break;
            }
        }
        /*
         } catch (IOException e) {
         System.out.println("BŁĄD ODCZYTU Z PLIKU!");
         System.exit(2);
         }
         */
        // ZAMYKANIE PLIKU
        try {
            fr.close();
        } catch (IOException e) {
            System.out.println("BŁĄD PRZY ZAMYKANIU PLIKU!");
            System.exit(3);
        }
    }

}
