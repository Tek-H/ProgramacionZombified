package Proyecto;

import java.util.Random;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class Proyecto {
    static Scanner sc = new Scanner(System.in);
    static Random rand = new Random();

    //Variables inputs
    static int opcion;

    //Variables colores
    static final String rojo = "\u001B[31m";
    static final String verde = "\u001B[32m";
    static final String morado = "\u001B[35m";
    static final String reset = "\u001B[0m";

    static int turno =0;

    //Variables inventario


    //Variables jugador
    static boolean saqueador = false;
    static int vida_jug = 100;
    static boolean tarde = true;
    static Object inventario [][] = {
            {"Mapa", "Balas"}, //objeto
            {false, true}, //¿tiene el objeto?
            {0, 10,} //cantidad
    };
    static Object armas[][] = {
            {"percha","barra de metal","martillo","pala","taladro"}, //arma
            {100, 110, 130, 120, 80}, //daño arma
            {false, false, false, false, false} //¿tiene el arma?
    };
    static int daño_arma = 0;

    //Variables enemigos
    static Object enemigos [][]= {
            {"Zombie", "Zombie blindado", "Zomblin", "persona", "encapuchado"}, //tipo enemigo
            {100, 150, 75, 125, 175}, //vida enemigo
            {20, 30, 10, 25, 30}, // daño base enemigo
            {700,800,500, 650, 500}, //tiempo de esquiva
    };
    static int indice = 0;
    static String tipo_e = "0";
    static int vida_e = 0;
    static int daño_e = 0;
    static int tiempo_esq = 0;

    static boolean puebloLlegar = false;




    public static void main(String[] args) {
        System.out.println("1. Quedarte en la ciudad");
        System.out.println("2. Irte al bosque");
        System.out.println("3. Buscar ayuda");
        opcion = scan(1,2,3);

            switch (opcion) {
                case 1:
                    ciudad();
                    break;
                case 2:
                    bosque();
                    break;
                case 3: {
                    ayuda();
                    break;
                }
        }
        System.exit(0);
    }


    //FUNCIONES ESCANEO

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


    static char scan_char(){
        while(true) {
            System.out.println("Introduce una letra");
            if (sc.hasNext()){
                char a = sc.next().charAt(0);
                if (Character.isLetter(a)) {
                    turno++;
                    return a;
                }
            }
        }
    }


    //FUNCIONES CINEMATICAS

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
        else if (a.equals("pueblo")) {
            a="\uD83C\uDFD8\uFE0F";
            limite=3;
        }

        StringBuilder recorrido = new StringBuilder(a+"/////////\uD83C\uDFC3");
        for(int i=0; i<=9;i++) {
            System.out.print("\r" + recorrido);
            recorrido = recorrido.deleteCharAt(limite);
            sleep();
        }
        System.out.println("");
    }



    //FUNCIONES HISTORIA PARTE1

    static void ciudad() {
        cinematica("ciudad");

        System.out.println("1. Tienda");
        System.out.println("2. Casa");
        System.out.println("3. Granero");
        System.out.println("4. Edificio");
        opcion = scan(1,2,3,4);
        if (opcion == 1) {
            tienda();
        } else if (opcion == 2) {
            cinematica("casa");
            System.out.println("Buscas una casa y te encuentras con gente");
            System.out.println("1. Continuar");
            opcion = scan(1);
            if (opcion == 1) {
                System.out.println(verde+"Deciden aceptarte y vives con ellos"+reset);
                armas[2][1]=true;
                pueblo();
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
                    armas[2][2] = true;
                    if((boolean)armas[2][2]==true) {
                        System.out.println(rojo + "mueres" + reset);
                    }
                }
                else if (opcion == 2) {
                    cinematica("pala");
                    armas[2][3] = true;
                    if((boolean)armas[2][3]==true) {
                        System.out.println(verde + "sobrevives" + reset);
                    }
                }
                else if (opcion == 3) {
                    cinematica("taladro");
                    armas[2][4] = true;
                    if((boolean)armas[2][4]==true) {
                        System.out.println(rojo + "mueres" + reset);
                    }
                }
        } else if (opcion == 4) {
            cinematica("edificio");
            System.out.println("Ves un edificio a lo lejos y subes hasta la azotea");
            System.out.println("1. Continuar");
            System.out.println("Te encuentras con un encapuchado");
            if(ahorcado()){
                System.out.println("Te propone ser amigos");
                System.out.println("1. Aceptar");
                System.out.println("2. Combatir");
                opcion = scan(1,2);
                if(opcion == 1){
                    System.out.println(verde+"Ahora sois amigos"+reset);
                    saqueador=true;
                    System.out.println(rojo+"");
                }
                else if(opcion == 2){
                    combate("encapuchado");
                }
            }else{
                System.out.println(rojo+"El encapuchado te mata"+reset);
            }
        }
    }

    static void bosque(){
        cinematica("bosque");

        System.out.println("1. Coche");
        System.out.println("2. Edificio");
        System.out.println("3. Militares");
        opcion = scan(1,2,3);
        if (opcion == 1) {
            cinematica("coche");
            System.out.println("Decides puentear el coche");
            combate("zombie");
            System.out.println("Te encuentras a un grupo de gente");
            opcion = scan(1);
            sc.nextLine();
            if (opcion == 1) {
                System.out.println(verde+"Deciden aceptarte y vives con ellos"+reset);
                    pueblo();
            }
        }
        else if (opcion == 2) {
            cinematica("edificio");
            System.out.println("Ves un edificio a lo lejos y subes hasta la azotea");
            System.out.println("1. Continuar");
            System.out.println("Te encuentras con un encapuchado");
            if(ahorcado()){
                System.out.println("Te propone ser amigos");
                System.out.println("1. Aceptar");
                System.out.println("2. Combatir");
                opcion = scan(1,2);
                if(opcion == 1){
                    System.out.println(verde+"Ahora sois amigos"+reset);
                    saqueador=true;
                    System.out.println(rojo+"");
                }
                else if(opcion == 2){
                    combate("zombie");
                }
            }else{
                System.out.println(rojo+"El encapuchado te mata"+reset);
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

    static void ayuda(){
        cinematica("bosque");
        System.out.println("Estas en el bosque y te encuentras un mapa, ¿lo coges?");
        System.out.println("1. Coger el mapa");
        System.out.println("2. Dejar el mapa");
        opcion = scan(1,2);
        if (opcion == 1) {
            inventario[1][0] = true;
            inventario[2][0] = 1;
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
            opcion = scan(1,2);
            if (opcion == 1) {
                cinematica("campamento");
                System.out.println(rojo+"Te acercas a los militares y te disparan"+reset);
            }
            if (opcion == 2) {
                System.out.println("No te fias y te alejas");
                System.out.println("Te encuentras con un encapuchado al andar un rato");
                System.out.println("Te encuentras dos caminos");
                System.out.println("1. Ciudad");
                System.out.println("2. bosque");
                opcion = scan(1,2);
                if (opcion == 1){
                    ciudad();
                }
                else if(opcion == 2){
                    bosque();
                }
            }
        }
    }


    //FUNCIONES HISTORIA PARTE2
    static void pueblo(){
        if(saqueador==false) {
            puebloLlegar = true;
            cinematica("pueblo");
            System.out.println("Llegais a un pueblo y decidis registrar las casas");
            System.out.println("1. Casa izquierda");
            System.out.println("2. Casa derecha");
            opcion = scan(1, 2);
            if (opcion == 1) {
                cinematica("casa");
            } else if (opcion == 2) {
                cinematica("casa");
                inventario[2][1] = (int) inventario[2][1] + 5;
                System.out.println("Un zombie mata a Bill");
                System.out.println("Te mandan a una tienda de la ciudad a por suministros");
                tienda();
            }
        }
    }

    //FUNCIONES HISTORIA SECUNDARIAS
    static boolean ahorcado() {
        String palabras[] = {"alfombra", "comida", "java", "edificios", "sandia","girafa"};
        Random rand =  new Random();
        int contador = 0;

        int eleccion = rand.nextInt(palabras.length);
        String solucion = palabras[eleccion];
        System.out.println(palabras[eleccion]);

        char averiguar[] = new char[solucion.length()];
        int longitud = solucion.length();

        for (int i = 0; i < longitud; i++) {
            averiguar[i] = '_';
        }

        while (true) {
            if(contador<10) {
                if (!String.valueOf(averiguar).equals(solucion)) {
                    System.out.println(averiguar);
                    char letra = scan_char();
                    contador++;
                    for (int i = 0; i < solucion.length(); i++) {
                        if (letra == solucion.charAt(i)) {
                            averiguar[i] = letra;
                        }
                    }
                }
                else{return true;}
            }
            else{
                return false;
            }
        }
    }

    static void tienda(){
        cinematica("tienda");
        System.out.println("Entras en la tienda, hay zombies ¿Percha, barra de metal?");
        System.out.println("1. Percha");
        System.out.println("2. Barra de metal");
        opcion = scan(1,2);
        if (opcion == 1) {
            cinematica("percha");
            armas[2][0] = true;
            if((boolean)armas[2][0]==true) {
                combate("zombie");
                if(puebloLlegar){

                }
            }
        }
        else if (opcion == 2) {
            cinematica("barra de metal");
            armas[2][1] = true;
            if((boolean)armas[2][1]==true) {
                System.out.println(rojo + "te matan los zombies" + reset);
            }
        }
    }



    //FUNCIONES COMBATE
    static void combate(String tipo){
        if(combatir(tipo)){
            System.out.println(verde+"Sobrevives, por ahora..."+reset);
        }
        else{
            System.out.println(rojo+"Has muerto"+reset);
            System.exit(0);
        }
    }

    static boolean combatir(String tipo) {

        stats_enem(tipo);
        stats_arma();

        while (true) {
            while(vida_e >0&&vida_jug>0) {
                System.out.println("");
                System.out.println(tipo_e+": "+vida_e);
                System.out.println("Vida: "+vida_jug);
                System.out.println("1. Combate cuerpo a cuerpo");
                System.out.println("2. Pistola");
                System.out.println("3. Inventario");

                int opcion = sc.nextInt();

                if (opcion == 1) {
                    vida_e -= 10+daño_arma;
                }
                if (opcion == 2) {
                    if ((int)inventario[2][1] > 0) {
                        inventario[2][1] = (int)inventario[2][1]-1;
                        vida_e -= 30;
                    } else {
                        System.out.println("No tienes balas");
                    }
                }
                if (opcion == 3) {
                    System.out.println("Balas: " + inventario[2][1]);
                    continue;
                }
                if(vida_e<0){
                    return true;
                }
                ia_enem(tipo);
                if(vida_jug<0){
                    return false;
                }
            }
        }
    }

    static void stats_arma(){
        for(int i=0;i<armas.length;i++){
            if((boolean)armas[2][i]==true){
                daño_arma=(int)armas[1][i]/10;
            }
        }
    }

    static void stats_enem(String tipo){
        if(tipo.equals("zombie")){
            indice = rand.nextInt(0, 3);
            tipo_e = (String) enemigos[0][indice];
            vida_e = (int) enemigos[1][indice];
            daño_e = (int) enemigos[2][indice];
            tiempo_esq = (int) enemigos[3][indice];
        }
        else if(tipo.equals("persona")){
            tipo_e = (String) enemigos[0][3];
            vida_e = (int) enemigos[1][3];
            daño_e = (int) enemigos[2][3];
            tiempo_esq = (int) enemigos[3][3];
        }
        else if(tipo.equals("encapuchado")){
            tipo_e = (String) enemigos[0][4];
            vida_e = (int) enemigos[1][4];
            daño_e = (int) enemigos[2][4];
            tiempo_esq = (int) enemigos[3][4];
        }
    }

    static void ia_enem(String tipo){
        int eleccion = rand.nextInt(1,3);
        if(tipo.equals("zombie")) {
            if (eleccion == 1) {
                System.out.println("Mordisco");
                esquivar();
                if (tarde == false) {
                    System.out.println("Esquivaste con exito");
                } else {
                    System.out.println("No esquivaste");
                    vida_jug -= daño_e + 10;
                }
            }
            if (eleccion == 2) {
                System.out.println("Arañazo");
                esquivar();
                if (tarde == false) {
                    System.out.println("Esquivaste con exito");
                } else {
                    System.out.println("No esquivaste");
                    vida_jug -= daño_e;
                }
            }
        }
        if(tipo.equals("persona")||tipo.equals("encapuchado")) {
            if (eleccion == 1) {
                    System.out.println("Disparo");
                    vida_jug -= daño_e + 10;
            }
            if (eleccion == 2) {
                System.out.println("Golpe");
                esquivar();
                if (tarde == false) {
                    System.out.println("Esquivaste con exito");
                } else {
                    System.out.println("No esquivaste");
                    vida_jug -= daño_e;
                }
            }
        }
    }

    static void esquivar(){
        tarde = true;
        boolean[] reaccionado = {false};
        Timer tim = new Timer();

        System.out.println("");
        System.out.println("ESQUIVA ( X )");
        TimerTask tarea = new TimerTask() {
            public void run() {
                if(!reaccionado[0]) {
                    tarde = true;
                    reaccionado[0] = true;
                    tim.cancel();
                }
            }
        };
        tim.schedule(tarea, tiempo_esq);
        sc.nextLine();
        String tecla = sc.nextLine();

        if(!reaccionado[0]) {
            if (tecla.equals("x")) {
                tarde = false;
            } else {
                tarde = true;
            }
            reaccionado[0] = true;
            tarea.cancel();
            tim.cancel();
        }
    }
}