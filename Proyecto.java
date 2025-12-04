package Proyecto;

import java.util.Random;
import java.util.Scanner;

public class Proyecto {
    public static Scanner sc = new Scanner(System.in);

    //Variables inputs
    static int opcion;

    //Variables colores
    static final String rojo = "\u001B[31m";
    static final String verde = "\u001B[32m";
    static final String morado = "\u001B[35m";
    static final String reset = "\u001B[0m";

    static int turno =0;

    //Variables inventario
    static Object[][] inventario = {
            {"Mapa","Percha","Barra de metal","Martillo","Pala","Taladro"},
            {false, false, false, false, false, false},
    };

    public static void main(String[] args) {
        System.out.println("1. Quedarte en la ciudad");
        System.out.println("2. Irte al bosque");
        System.out.println("3. Buscar ayuda");
        opcion = scan(1,2,3);

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

    static int scan(int a){
            while(true) {
                System.out.println("Pulsa 1");
                if (sc.hasNextInt()) {
                    opcion = sc.nextInt();
                    if (opcion >= a && opcion <= a) {
                        return opcion;
                    }
                }
                else{
                    sc.next();
                }
            }
    }
    static int scan(int a, int b){
        while(true) {
            System.out.println("Pulsa 1-2");
            if (sc.hasNextInt()) {
                opcion = sc.nextInt();
                if (opcion >= a && opcion <= b) {
                    return opcion;
                }
            }
            else{
                sc.next();
            }
        }
    }
    static int scan(int a, int b, int c){
        while(true) {
            System.out.println("Pulsa 1-3");
            if (sc.hasNextInt()) {
                opcion = sc.nextInt();
                if (opcion >= a && opcion <= c) {
                    return opcion;
                }
            }
            else{
                sc.next();
            }
        }
    }
    static int scan(int a, int b, int c, int d){
        while(true) {
            System.out.println("Pulsa 1-4");
            if (sc.hasNextInt()) {
                opcion = sc.nextInt();
                if (opcion >= a && opcion <= d) {
                    return opcion;
                }
            }
            else{
                sc.next();
            }
        }
    }


    static String scan_char(String a){
        while(true) {
            System.out.println("Introduce una letra");
            if (sc.hasNext()){
                a = sc.next();
                if (a.length() == 1 && Character.isLetter(a.charAt(0))) {
                    turno++;
                    return a;
                }
            }
        }
    }

    static void sleep(){
        try {
            Thread.sleep(400);
        } catch (InterruptedException e) {}
    }

    static void cinematica(String a){
        int limite=0;
        if (a.equals("ciudad")){
            a = "\uD83C\uDFD9\uFE0F";
            limite=3;
        }
        else if(a.equals("bosque")){
            a = "\uD83C\uDF32";
            limite = 2;
        }
        else if(a.equals("campamento")){
            a = "\uD83C\uDFD5\uFE0F";
            limite = 3;
        }
        else if(a.equals("tienda")){
            a = "\uD83C\uDFEA";
            limite=2;
        }
        else if(a.equals("casa")){
            a = "\uD83C\uDFE0";
            limite=2;
        }
        else if(a.equals("granero")){
            a = "\uD83C\uDFDA\uFE0F";
            limite=3;
        }
        else if(a.equals("edificio")){
            a = "\uD83C\uDFEC";
            limite=2;
        }
        else if(a.equals("coche")){
            a = "\uD83D\uDE97";
            limite=2;
        }
        else if(a.equals("percha")){
            a = "\uD83E\uDE9D";
            limite=2;
        }
        else if(a.equals("barra de metal")){
            a = "\uD83D\uDD27";
            limite=2;
        }
        else if(a.equals("martillo")){
            a = "\uD83D\uDD28";
            limite=2;
        }
        else if (a.equals("pala")) {
            a = "\uD83E\uDE8F";
            limite=2;
        }
        else if(a.equals("taladro")){
            a = "\uD83E\uDE9B";
            limite=2;
        }

        StringBuilder recorrido = new StringBuilder(a+"/////////\uD83C\uDFC3");
        for(int i=0; i<=9;i++) {
            System.out.print("\r" + recorrido);
            recorrido = recorrido.deleteCharAt(limite);
            sleep();
        }
        System.out.println("");
    }

    static void A() {
        cinematica("ciudad");

        System.out.println("1. Tienda");
        System.out.println("2. Casa");
        System.out.println("3. Granero");
        System.out.println("4. Edificio");
        opcion = scan(1,2,3,4);
        if (opcion == 1) {
            cinematica("tienda");
            System.out.println("Entras en la tienda, hay zombies ¿Percha, barra de metal?");
            System.out.println("1. Percha");
            System.out.println("2. Barra de metal");
            opcion = scan(1,2);
                if (opcion == 1) {
                    cinematica("percha");
                    inventario[1][1] = true;
                    if((boolean)inventario[1][1]==true) {
                        System.out.println(verde + "sobrevives" + reset);
                    }
                }
                else if (opcion == 2) {
                    cinematica("barra de metal");
                    inventario[1][2] = true;
                    if((boolean)inventario[1][2]==true) {
                        System.out.println(rojo + "te matan los zombies" + reset);
                    }
                }
        } else if (opcion == 2) {
            cinematica("casa");
            System.out.println("Buscas una casa y te encuentras con gente");
            System.out.println("1. Continuar");
            opcion = scan(1);
            if (opcion == 1) {
                System.out.println(verde+"Deciden aceptarte y vives con ellos"+reset);
            }
        } else if (opcion == 3) {
            cinematica("granero");
            System.out.println("Vas al granero ");
            System.out.println("1. Martillo");
            System.out.println("2. Pala");
            System.out.println("3. Taladro");
            opcion = scan(1,2,3);
                if (opcion == 1) {
                    cinematica("martillo");
                    inventario[1][3] = true;
                    if((boolean)inventario[1][3]==true) {
                        System.out.println(rojo + "mueres" + reset);
                    }
                }
                else if (opcion == 2) {
                    cinematica("pala");
                    inventario[1][4] = true;
                    if((boolean)inventario[1][4]==true) {
                        System.out.println(verde + "sobrevives" + reset);
                    }
                }
                else if (opcion == 3) {
                    cinematica("taladro");
                    inventario[1][5] = true;
                    if((boolean)inventario[1][5]==true) {
                        System.out.println(rojo + "mueres" + reset);
                    }
                }
        } else if (opcion == 4) {
            cinematica("edificio");
            System.out.println("Te encuentras con un man");
            if(ahorcado()){
                System.out.println(verde+"Sois amigos"+reset);
            }
            else{
                System.out.println(rojo+"Man te mata"+reset);
            }
        }
    }

    static void B(){
        cinematica("bosque");

        System.out.println("1. Coche");
        System.out.println("2. Edificio");
        System.out.println("3. Militares");
        opcion = scan(1,2,3);
        if (opcion == 1) {
            cinematica("coche");
            System.out.println("Decides puentear el coche");
            System.out.println("1. Continuar");
            opcion = scan(1);
            if (opcion == 1) {
                System.out.println(rojo+"El coche esta roto, llegan zombies y mueres"+reset);
            }
        } else if (opcion == 2) {
            cinematica("edificio");
            System.out.println("Ves un edificio a lo lejos y subes hasta la azotea");
            System.out.println("1. Continuar");
            System.out.println("Te encuentras con un man");
            if(ahorcado()){
                System.out.println(verde+"Sois amigos"+reset);
            }else{
                System.out.println(rojo+"Man te mata"+reset);
            }
        } else if (opcion == 3) {
            cinematica("campamento");
            System.out.println("1. Continuar");
            opcion = scan(1);
            if (opcion == 1) {
                cinematica("campamento");
                System.out.println(rojo + "Te acercas a los militares y te disparan" + reset);
            }
        }
    }

    static void C(){
        cinematica("bosque");
        System.out.println("Estas en el bosque y te encuentras un mapa, ¿lo coges?");
        System.out.println("1. Coger el mapa");
        System.out.println("2. Dejar el mapa");
        opcion = scan(1,2);
        if (opcion == 1) {
            inventario[1][0] = true;
            if((boolean)inventario[1][0]==true) {
                System.out.println("Coges el mapa");
                System.out.println("Sigues el mapa y te encuentras un campamento militar");
                System.out.println("1. Continuar");
                opcion = scan(1);
                if (opcion == 1) {
                    cinematica("campamento");
                    System.out.println(rojo+"Te acercas a los militares y te disparan"+reset);
                }
            }
        } else if (opcion == 2) {
            System.out.println("Dejas el mapa");
            System.out.println("Sigues andando por el bosque y te encuentras un campamento militar, ¿te acercas?");
            System.out.println("1. Acercarte");
            System.out.println("2. Alejarte");
            opcion = scan(1);
            if (opcion == 1) {
                cinematica("campamento");
                System.out.println(rojo+"Te acercas a los militares y te disparan"+reset);
            }
            if (opcion == 2) {
                System.out.println("No te fias y te alejas");
            }
        }
    }

    static boolean ahorcado() {
        String palabras[] = {"alejandro", "carlos"};
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();

        int eleccion = rand.nextInt(palabras.length);
        String solucion = palabras[eleccion];
        System.out.println(palabras[eleccion]);

        char averiguar[] = new char[solucion.length()];
        int longitud = solucion.length();

        for (int i = 0; i < longitud; i++) {
            averiguar[i] = '_';
        }

        while (true) {
            if (!String.valueOf(averiguar).equals(solucion)) {
                System.out.println(averiguar);
                char letra = sc.next().charAt(0);
                for (int i = 0; i < solucion.length(); i++) {
                    if (letra == solucion.charAt(i)) {
                        averiguar[i] = letra;
                    }
                }
            } else {
                return (true);
            }
        }
    }
}