/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eac5p3;

import java.util.Scanner;

public class AppEac5P3 {

    public static final int MAXKG = 25000;

    String mVarietat[]
            = {"Ull de llebre", "Garnatxa", "Xarelo", "Macabeu", "Parellada"};
    int mQuantitat[] = {0, 0, 0, 0, 0};
    int quantitatTotalRaim = 0;
    DadesProduccio dades = new DadesProduccio();

    public static void main(String[] args) {
        AppEac5P3 prg = new AppEac5P3();
        prg.inici();
    }

    private void inici() {

        menu();

    }

    //metodos nivel 1
    private void menu() {
        boolean exit = false;
        boolean dadaCorrecta = false;

        Scanner scan = new Scanner(System.in);
        do {
            System.out.println("");
            System.out.println("Escoge opción:");
            System.out.println("(1) Entrada de raim");
            System.out.println("(2) Sortida de raim");
            System.out.println("(3) Mostrar quantitats");
            System.out.println("(4) Estimació producció");
            System.out.println("(5) Produir i embotellar");
            System.out.println("(6) Mostrar producció");
            System.out.println("(7) Sortir");

            dadaCorrecta = scan.hasNextInt();
            if (dadaCorrecta) {
                int opcio = scan.nextInt();

                switch (opcio) {
                    case 1:
                        entradaRaim(posicioVarietat(), quantitat());
                        break;
                    case 2:
                        sortidaRaim(posicioVarietat());
                        break;
                    case 3:
                        mostrarRaims();
                        break;
                    case 4:
                        estimarQuantitat();
                        break;
                    case 5:
                        produir();
                        break;
                    case 6:
                        GestioProduccio.mostraProduccio(dades.aProducte, dades.aProduccio);
                        break;
                    case 7:
                        exit = true;
                        break;
                    default:
                        System.out.println("Aquesta opció no existeix.");

                }
            } else {
                scan.nextLine();
                System.out.println("Opció incorrecta");
            }
        } while (!exit);
    }

//metodos nivel 2
    private void entradaRaim(int posicio, int quantitat) {

        if ((quantitat + mQuantitat[posicio]) > MAXKG) {
            System.out.println("No cap al magatzem.");
        } else {
            if (posicio >= 0) {

                mQuantitat[posicio] += quantitat;
            } else {
                System.out.println("Varietat incorrecta");
            }
        }
    }

    private void sortidaRaim(int posicio) {
        if (posicio >= 0) {
            System.out.println(" Varietat: " + mVarietat[posicio] + ". Total emmagatzemat:" + mQuantitat[posicio] + " KG.");
            int kg = 0;

            boolean correct = false;
            do {
                kg = quantitat();
                if ((mQuantitat[posicio] - kg) < 0) {
                    System.out.println("Quantitat erronea. Torni a introduir.");
                } else {
                    mQuantitat[posicio] = mQuantitat[posicio] - kg;
                    correct = true;
                }
            } while (!correct);
        } else {
            System.out.println("Varietat incorrecta");
        }

    }

    private void mostrarRaims() {
        int totalkgs = 0;
        for (int i = 0; i < mVarietat.length; i++) {
            System.out.println(" Varietat: " + mVarietat[i] + ". Total emmagatzemat:" + mQuantitat[i] + " KG.");
            totalkgs += mQuantitat[i];
        }
        System.out.println("Total en magatzem: " + totalkgs + " Kg.");
    }

    private void estimarQuantitat() {

        String producte = UtilsES.demanarString("Introdueix el producte a simular: Cava Castells, Blanc Castells, Negre Castells ", "No valido");

        System.out.println(" Se podrien produir fins " + GestioProduccio.calculaAmpolles(producte, dades.aProducte, dades.mCupatge, mQuantitat) + " ampolles.");

    }

    private void produir() {
        String producte = UtilsES.demanarString("Que vols embotellar?: Cava Castells, Blanc Castells, Negre Castells ", "No valido");

        int quantitat = UtilsES.demanarEnter("Quina quantitat de ampolles  vols fer?", producte);

        int maxampolles = GestioProduccio.calculaAmpolles(producte, dades.aProducte, dades.mCupatge, mQuantitat);

        if (quantitat > maxampolles) {
            System.out.println("No hi ha quantitat suficient per produirles");
        } else {
            int[] kgUsados = GestioProduccio.calculaQuilogramsAmpolles(quantitat, producte, mVarietat, dades.mCupatge);
            for (int i = 0; i < kgUsados.length; i++) {
                mQuantitat[i] = mQuantitat[i] - kgUsados[i];
            }

            int posicio = GestioProduccio.posicioProducte(producte, dades.aProducte);

            dades.aProduccio[posicio] += quantitat;
        }

    }

//Metodos nivel 3
    private int posicioVarietat() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Quina varietat vols? : Ull de llebre, Garnatxa, Xarelo, Macabeu o Parellada");

        String varietat = scan.nextLine();

        for (int i = 0; i < mVarietat.length; i++) {
            if (varietat.equalsIgnoreCase(mVarietat[i])) {
                return i;
            }
        }
        return -1;

    }

    private int quantitat() {
        Scanner scan = new Scanner(System.in);
        boolean dadaCorrecta = false;
        int kg = 0;
        do {
            System.out.println("Introdueix la quantitat entre 0 i 1000 kg");
            dadaCorrecta = scan.hasNextInt();
            if (dadaCorrecta) {
                kg = scan.nextInt();
                scan.nextLine();
                if ((kg < 0) || (kg > 1000)) {
                    System.out.println("Quantitat incorrecta");
                    dadaCorrecta = false;
                }
            } else {
                System.out.println("Dada incorrecta.");
                scan.nextLine();
            }
        } while (!dadaCorrecta);
        return kg;
    }

}
