package Proyecto;

import java.util.Scanner;

public class Proyecto {
    public static Scanner sc = new Scanner(System.in);

    static int opcion;
    static String rojo = "\u001B[31m";
    static String verde = "\u001B[32m";
    static String reset = "\u001B[0m";
    static int posibilidades[] = {1,2,3,4};

    public static void main(String[] args) {
        System.out.println("1. Quedarte en la ciudad");
        System.out.println("2. Irte al bosque");
        System.out.println("3. Buscar ayuda");
        opcion = scan(posibilidades[2]);

        switch (opcion){
            case 1:
                A();
                break;
            case 2:
                B();
                break;
            case 3: {
                C();
                break;
            }
        }
    }

    static int scan(int max){
            while(true) {
                System.out.println("Introduce un número del 1-"+max);
                if (sc.hasNextInt()) {
                    opcion = sc.nextInt();
                    if (opcion >= 1 && opcion <= max) {
                        return opcion;
                    }
                }
                else{
                    sc.next();
                }
            }
    }

    static void A() {
        System.out.println("1. A1");
        System.out.println("2. A2");
        System.out.println("3. A3");
        System.out.println("4. A4");
        opcion = scan(posibilidades[3]);
        if (opcion == 1) {
            System.out.println("1. Continuar");
            opcion = scan(posibilidades[0]);
            if (opcion == 1) {
                System.out.println(rojo+"FINAL FAM"+reset);
            }
        } else if (opcion == 2) {
            System.out.println("1. Continuar");
            opcion = scan(posibilidades[0]);
            if (opcion == 1) {
                System.out.println(verde+"FINAL FAB"+reset);
            }
        } else if (opcion == 3) {
            System.out.println("1. Continuar");
            opcion = scan(posibilidades[0]);
            if (opcion == 1) {
                System.out.println("FINAL FABN");
            }
        } else if (opcion == 4) {
            System.out.println("1. Continuar");
            opcion = scan(posibilidades[0]);
            if (opcion == 1) {
                System.out.println("FINAL FABN");
            }
        }
    }

    static void B(){
        System.out.println("1. B1");
        System.out.println("2. B2");
        System.out.println("3. B3");
        opcion = scan(posibilidades[2]);
        if (opcion == 1) {
            System.out.println("1. Continuar");
            opcion = scan(posibilidades[0]);
            if (opcion == 1) {
                System.out.println("FINAL FABN");
            }
        } else if (opcion == 2) {
            System.out.println("1. Continuar");
            opcion = scan(posibilidades[0]);
            if (opcion == 1) {
                System.out.println(rojo+"FINAL FBM"+reset);
            }
        } else if (opcion == 3) {
            System.out.println("1. Continuar");
            opcion = scan(posibilidades[0]);
            if (opcion == 1) {
                System.out.println(verde + "FINAL FBB" + reset);
            }
        }
    }

    static void C(){
        System.out.println("1. C1");
        System.out.println("2. C2");
        opcion = scan(posibilidades[1]);
        if (opcion == 1) {
            System.out.println("1. Continuar");
            opcion = scan(posibilidades[0]);
            if (opcion == 1) {
                System.out.println("FINAL FABN");
            }
        } else if (opcion == 2) {
            System.out.println("1. Continuar");
            opcion = scan(posibilidades[0]);
            if (opcion == 1) {
                System.out.println(rojo + "FINAL FBM" + reset);
            }
        }
    }
}