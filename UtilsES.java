/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eac5p3;

import java.util.Scanner;

/**
 *
 * @author Marta
 */
class UtilsES {

    public static int demanarEnter(String missatge, String missatgeError) {
        Scanner scan = new Scanner(System.in);
        boolean dadaCorrecta = false;
        int opcio = 0;
        do {
            System.out.println(missatge);
            dadaCorrecta = scan.hasNextInt();
            if (dadaCorrecta) {
                opcio = scan.nextInt();
            } else {
                scan.nextLine();
                System.out.println(missatgeError);
            }
        } while (!dadaCorrecta);
        return opcio;
    }

    public static String demanarString(String missatge, String missatgeError) {
        Scanner scanner = new Scanner(System.in);
        String ret;
        boolean correcte = false;
        do {
            System.out.print(missatge);
            ret = scanner.nextLine();
            correcte = !ret.isEmpty();
            if (!correcte) {
                System.out.println();
                System.out.println(missatgeError);
            }
        } while (!correcte);
        return ret;
    }
}
