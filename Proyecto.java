package Proyecto;

import java.util.Random;
import java.util.Scanner;

public class Proyecto {
    public static Scanner sc = new Scanner(System.in);

    //Variables inputs
    static int opcion;
    static int posibilidades[] = {1,2,3,4};

    //Variables colores
    static final String rojo = "\u001B[31m";
    static final String verde = "\u001B[32m";
    static final String morado = "\u001B[35m";
    static final String reset = "\u001B[0m";

    //Variables ahorcado
    static Random rand = new Random();
    static String palabras[]={"palabra","java","libro","sandia","edificio","broma","comida","alfombra"};
    static String elegida = palabras[rand.nextInt(palabras.length)];
    static String jugador[]= new String[elegida.length()];
    static String letra;
    static int turno=0;

    //Variables inventario
    static Object[][] inventario = {
            {"Mapa","Percha","Barra de metal","Martillo","Pala","Taladro"},
            {false, false, false, false, false, false},
    };

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
            Thread.sleep(1000);
        } catch (InterruptedException e) {}
    }


    static void A() {
        System.out.println("1. Tienda");
        System.out.println("2. Casa");
        System.out.println("3. Granero");
        System.out.println("4. Edificio");
        opcion = scan(posibilidades[3]);
        if (opcion == 1) {
            System.out.println("Entras en la tienda, hay zombies ¿Percha, barra de metal?");
            System.out.println("1. Percha");
            System.out.println("2. Barra de metal");
            opcion = scan(posibilidades[1]);
                if (opcion == 1) {
                    inventario[1][1] = true;
                    if((boolean)inventario[1][1]==true) {
                        System.out.println(verde + "sobrevives" + reset);
                    }
                }
                else if (opcion == 2) {
                    inventario[1][2] = true;
                    if((boolean)inventario[1][1]==true) {
                        System.out.println(rojo + "te matan los zombies" + reset);
                    }
                }
        } else if (opcion == 2) {
            System.out.println("Buscas una casa y te encuentras con gente");
            System.out.println("1. Continuar");
            opcion = scan(posibilidades[0]);
            if (opcion == 1) {
                System.out.println(verde+"Deciden aceptarte y vives con ellos"+reset);
            }
        } else if (opcion == 3) {
            System.out.println("Vas al granero ");
            System.out.println("1. Martillo");
            System.out.println("2. Pala");
            System.out.println("3. Taladro");
            opcion = scan(posibilidades[2]);
                if (opcion == 1) {
                    inventario[1][2] = true;
                    if((boolean)inventario[1][3]==true) {
                        System.out.println(rojo + "mueres" + reset);
                    }
                }
                else if (opcion == 2) {
                    inventario[1][3] = true;
                    if((boolean)inventario[1][4]==true) {
                        System.out.println(verde + "sobrevives" + reset);
                    }
                }
                else if (opcion == 3) {
                    inventario[1][4] = true;
                    if((boolean)inventario[1][5]==true) {
                        System.out.println(rojo + "mueres" + reset);
                    }
                }
        } else if (opcion == 4) {
            System.out.println("Te encuentras con un man");
            ahorcado(letra);
            if(ahorcado(letra)){
                System.out.println(verde+"Sois amigos"+reset);
            }
            else{
                System.out.println(rojo+"Man te mata"+reset);
            }
        }
    }

    static void B(){
        System.out.println("1. Coche");
        System.out.println("2. Edificio");
        System.out.println("3. Militares");
        opcion = scan(posibilidades[2]);
        if (opcion == 1) {
            System.out.println("Decides puentear el coche");
            System.out.println("1. Continuar");
            opcion = scan(posibilidades[0]);
            if (opcion == 1) {
                System.out.println(rojo+"El coche esta roto, llegan zombies y mueres"+reset);
            }
        } else if (opcion == 2) {
            System.out.println("Ves un edificio a lo lejos y subes hasta la azotea");
            System.out.println("1. Continuar");
            System.out.println("Te encuentras con un man");
            ahorcado(letra);
            if(ahorcado(letra)){
                System.out.println(verde+"Sois amigos"+reset);
            }
            else{
                System.out.println(rojo+"Man te mata"+reset);
            }
        } else if (opcion == 3) {
            System.out.println("1. Continuar");
            opcion = scan(posibilidades[0]);
            if (opcion == 1) {
                System.out.println(rojo + "Te acercas a los militares y te disparan" + reset);
            }
        }
    }

    static void C(){
        System.out.println("Estas en el bosque y te encuentras un mapa, ¿lo coges?");
        System.out.println("1. Coger el mapa");
        System.out.println("2. Dejar el mapa");
        opcion = scan(posibilidades[1]);
        if (opcion == 1) {
            inventario[1][0] = true;
            if((boolean)inventario[1][0]==true) {
                System.out.println("Cojes el mapa");
                System.out.println("Sigues el mapa y te encuentras un campamento militar");
                System.out.println("1. Continuar");
                opcion = scan(posibilidades[0]);
                if (opcion == 1) {
                    System.out.println(rojo+"Te acercas a los militares y te disparan"+reset);
                }
            }
        } else if (opcion == 2) {
            System.out.println("Dejas el mapa");
            System.out.println("Sigues andando por el bosque y te encuentras un campamento militar, ¿te acercas?");
            System.out.println("1. Acercarte");
            System.out.println("2. Alejarte");
            opcion = scan(posibilidades[1]);
            if (opcion == 1) {
                System.out.println(rojo+"Te acercas a los militares y te disparan"+reset);
            }
            if (opcion == 2) {
                System.out.println("No te fias y te alejas");
            }
        }
    }

    static boolean ahorcado(String a){
        while(turno<10) {
            if (turno == 0) {
                for (int i = 0; i < elegida.length(); i++) {
                    System.out.print("_ ");
                }
                System.out.println();
            }

            String jugador_string = String.join("", jugador);
            if(elegida.equals(jugador_string)){
                return true;
            }

            a = scan_char(a);

            char opcion = a.charAt(0);
            for (int i = 0; i < elegida.length(); i++) {
                if (elegida.charAt(i) == '_' || elegida.charAt(i) == ' ') {
                    jugador[i] = "_";
                } else {
                    if (jugador[i] != null && jugador[i] == String.valueOf(elegida.charAt(i))) {
                    } else if (elegida.charAt(i) == opcion) {
                        jugador[i] = String.valueOf(elegida.charAt(i));
                    } else if (jugador[i] == null) {
                        jugador[i] = "_";
                    }
                }
                System.out.print(jugador[i] + " ");
            }
            System.out.println(" ");
        }
        return false;
    }
}