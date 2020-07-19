/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eac5p3;

import java.util.*;

public class GestioProduccio {

    static final double relQuiLitres = 0.7; //Relació entre quilograms i litres
    static final double capacitatAmpolla = 0.75;

    /*Mètode que calcular els litres màximes que es poden produir
      a partir dels % d'un producte i les quantitats acumulades al magatzem */
    public static int calculaLitres(int[] aCup, int[] mQuantitat) {
        double minDiv = 0;
        double div;
        double quilosAcum = 0;
        for (int i = 0; i < aCup.length; i++) {
            if (aCup[i] > 0) {
                div = mQuantitat[i] / aCup[i];
                if (div < minDiv || minDiv == 0) {
                    minDiv = div;
                }
            }
        }
        for (int j = 0; j < aCup.length; j++) {
            quilosAcum = quilosAcum + aCup[j] * minDiv;
        }

        return (int) Math.round(quilosAcum * relQuiLitres);
    }

    /*Mètode que ens retornarà els quilograms necessaris per elaborar els litres
      i % rebuts. */
    public static int[] calculaQuilogramsLitres(int litres, int[] aCup) {
        int[] mRes = {0, 0, 0, 0, 0};
        int quilos = (int) Math.round(litres * (1 / relQuiLitres));
        for (int i = 0; i < aCup.length; i++) {
            mRes[i] = (int) Math.round(quilos * (aCup[i]) / 100);
        }
        return mRes;
    }

    public static void mostraProduccio(String[] aProducte, int[] aProduccio) {
        System.out.println(" Producte             ampolles");
        System.out.println("--------------------------------");
        for (int i = 0; i < aProducte.length; i++) {
            System.out.println(String.format(" %-20s  %5d", aProducte[i], aProduccio[i]));
        }
    }

    /*Mètode posicioProducte  a desenvolupar.*/
    public static int posicioProducte(String prod, String[] aProd) {

        for (int i = 0; i < aProd.length; i++) {
            if (prod.equalsIgnoreCase(aProd[i])) {
                return i;
            }
        }
        return -1;

    }

    /*Mètode calculaAmpolles a desenvolupar.*/
    public static int calculaAmpolles(String producte, String[] aProd, int[][] mCup, int[] mQuant) {

        int posicio = posicioProducte(producte, aProd);
        int litres = 0;
        if (posicio > -1) {
            litres = calculaLitres(mCup[posicio], mQuant);
        }
        double calculo = litres / 0.75;
        int ampolles = (int) Math.round(calculo);
        return ampolles;

    }

    /*Mètode calculaQuilogramsAmpolles a desenvolupar.*/
    public static int[] calculaQuilogramsAmpolles(int ampolles, String producte, String[] aProd, int[][] mCup) {
        int litros = (int) Math.round(ampolles * 0.75);
        int[] kg = {0, 0, 0, 0, 0};
        int posicio = posicioProducte(producte, aProd);
        if (posicio > -1) {
            kg = calculaQuilogramsLitres(litros, mCup[posicio]);
        }
        return kg;

    }

}
