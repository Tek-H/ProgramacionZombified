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
    static final String marron = "\u001B[33m";
    static final String cian = "\u001B[36m";
    static final String reset = "\u001B[97m";

    static int turno =0;


    //Variables jugador
    static boolean saqueador = false;
    static int vida_jug = 100;
    static boolean tarde = true;
    static Object inventario [][] = {
            {"Mapa", "Balas", "Vendas"}, //objeto
            {false, true, true}, //¿tiene el objeto?
            {0, 10, 5} //cantidad
    };
    static Object armas[][] = {
            {"percha","barra de metal","martillo","pala","taladro"}, //arma
            {100, 110, 130, 120, 80}, //daño arma
            {false, false, false, false, false} //¿tiene el arma?
    };
    static int daño_arma = 0;
    static Object compañeros[][] = {
            {"Encapuchado","Bill"},
            {false, false},
    };
    //Variables enemigos
    static Object enemigos [][]= {
            {"Zombie", "Zombie blindado", "Zomblin", "persona", "encapuchado"}, //tipo enemigo
            {100, 150, 75, 125, 175}, //vida enemigo
            {20, 30, 10, 25, 30}, // daño base enemigo
            {900,1000,700, 850, 700}, //tiempo de esquiva
    };
    static int indice = 0;
    static String tipo_e = "0";
    static int vida_e = 0;
    static int daño_e = 0;
    static int tiempo_esq = 0;

    //Variables escenario
    static Object armasMesa[][] = {
            {"Martillo", "Pala", "Taladro"},
            {true, true, true},
    };

    static boolean militaresLlegar = false;
    static boolean puebloLlegar = false;
    static  boolean tiendaLlegar = false;
    static boolean edificioLlegar = false;
    static boolean graneroLlegar = false;
    static boolean encapuchadoVisto = false;



    public static void main(String[] args) {
        imprimir("1. Quedarte en la ciudad");
        imprimir("2. Irte al bosque");
        imprimir("3. Buscar ayuda");
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
                imprimir("Pulsa 1");
                if (sc.hasNextInt()) {
                    opcion = sc.nextInt();
                    if (opcion == a) {
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
            imprimir("Pulsa 1-2");
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
            imprimir("Pulsa 1-3");
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
            imprimir("Pulsa 1-4");
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
            imprimir("Introduce una letra");
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

        imprimir("1. Tienda");
        imprimir("2. Casa");
        imprimir("3. Granero");
        imprimir("4. Edificio");
        opcion = scan(1,2,3,4);
        if (opcion == 1) {
            tienda();
        } else if (opcion == 2) {
            cinematica("casa");
            imprimir("Buscas una casa y te encuentras con gente");
            opcion = scan(1);
            if (opcion == 1) {
                imprimir(verde+"Deciden aceptarte y vives con ellos"+reset);
                armas[2][1]=true;
                pueblo();
            }
        } else if (opcion == 3) {
            if(graneroLlegar == false) {
                graneroLlegar = true;
                cinematica("granero");
                imprimir("Vas al granero ");
                imprimir("1. Martillo");
                imprimir("2. Pala");
                imprimir("3. Taladro");
                opcion = scan(1, 2, 3);
                if (opcion == 1) {
                    cinematica("martillo");
                    equipar("martillo");
                    armasMesa[1][0] = false;
                    if ((boolean) armas[2][2] == true) {
                        combate("zombie");
                        bosque();
                        return;
                    }
                } else if (opcion == 2) {
                    cinematica("pala");
                    equipar("pala");
                    armasMesa[1][1] = false;
                    if ((boolean) armas[2][3] == true) {
                        combate("zombie");
                        bosque();
                        return;
                    }
                } else if (opcion == 3) {
                    cinematica("taladro");
                    equipar("taladro");
                    armasMesa[1][2] = false;
                    if ((boolean) armas[2][4] == true) {
                        imprimir(rojo + "Intentas atacar con el taladro pero al intentar taladrarle la cabeza te das cuenta de que no funciona. Despues de un forcejeo mueres");
                        System.exit(0);
                    }
                }
            }
            if(graneroLlegar == true) {
                imprimir("Vuelves al granero de antes. Todavía están las herramientas");
                for(int i=0; i<armasMesa[0].length;i++){
                    if((boolean)armasMesa[1][i]==true){
                        imprimir(i+1+". "+armasMesa[0][i]);
                    }
                }
                opcion = scan(1, 2, 3);
                if (opcion == 1&&(boolean)armasMesa[1][0]==true){
                    equipar("martillo");
                }
                if (opcion == 1&&(boolean)armasMesa[1][0]==false){
                    imprimir("Decides continuar con el martillo.");
                }
                if (opcion == 2&&(boolean)armasMesa[1][1]==true){
                    equipar("pala");
                }
                if (opcion == 1&&(boolean)armasMesa[1][1]==false){
                    imprimir("Decides continuar con la pala.");
                }
                if(opcion == 3&&(boolean)armasMesa[1][2]==true){
                    imprimir("Te das cuenta de que el taladro no tenia batería por lo que tras buscar un rato encuentras una y se la pones a la herramienta.");
                    equipar("taladro");
                }
            }
        } else if (opcion == 4) {
            edificio();
        }
    }

    static void bosque(){
        cinematica("bosque");

        imprimir("1. Coche");
        imprimir("2. Edificio");
        imprimir("3. Militares");
        opcion = scan(1,2,3);
        if (opcion == 1) {
            cinematica("coche");
            imprimir("Decides puentear el coche");
            combate("zombie");
            imprimir("Te encuentras a un grupo de gente");
            opcion = scan(1);
            sc.nextLine();
            if (opcion == 1) {
                imprimir(verde+"Deciden aceptarte y vives con ellos"+reset);
                    pueblo();
            }
        }
        else if (opcion == 2) {
            edificio();
        } else if (opcion == 3) {
            cinematica("campamento");
            if((boolean)armas[2][3]==true){
                imprimir("Encuentras una caja medio enterrada. Usas tu pala");
                obtener("balas",5);
                obtener("vendas",3);
            }
            else{
                imprimir("Ves algo medio enterrado pero no tienes una pala.");
            }
            opcion = scan(1);
            if (opcion == 1) {
                cinematica("campamento");
                militares();
            }
        }
    }

    static void ayuda(){
        cinematica("bosque");
        imprimir("Llevas andando un rato cuando te encuentras el cadaver de un militar junto a un mapa ¿que haces?");
        imprimir("1. Coger el mapa");
        imprimir("2. Dejar el mapa");
        opcion = scan(1,2);
        if (opcion == 1) {
            obtener("mapa",1);
            if((boolean)inventario[1][0]==true) {
                imprimir("Te agachas a coger el mapa, y al revisarlo aparece marcado un campamento militar. ");
                imprimir("Después de unas horas siguiendo el mapa ves a lo lejos humo. Debe ser el campamento");
                opcion = scan(1);
                if (opcion == 1) {
                    cinematica("campamento");
                    imprimir(rojo+"Vas hacia el campamento, pero antes de que te des cuenta te disparan en el pecho. Mueres"+reset);
                    return;
                }
            }
        } else if (opcion == 2) {
            imprimir("Dejas el mapa, estaría feo robar las cosas de un muerto.");
            militares();
        }
    }


    //FUNCIONES HISTORIA PARTE2
    static void pueblo(){
        cinematica("pueblo");
        if(saqueador==false) {
            if(puebloLlegar==false) {
                puebloLlegar = true;
                imprimir("Llegais a un pueblo y decidis registrar las casas");
                imprimir("1. Casa izquierda");
                imprimir("2. Casa derecha");
                opcion = scan(1, 2);
                if (opcion == 1) {
                    cinematica("casa");
                    imprimir("Te mandan a una tienda de la ciudad a por suministros");
                    compañero("bill");
                    tienda();
                } else if (opcion == 2) {
                    cinematica("casa");
                    obtener("balas",5);
                    imprimir("Un zombie mata a Bill");
                    imprimir("Te mandan a una tienda de la ciudad a por suministros");
                    tienda();
                }
            }
            if(puebloLlegar==true){
                imprimir("LLegas al pueblo y dejas los suministros");
                imprimir("Escuchas disparos, hay saqueadores");
                combate("persona");
                imprimir("¿A donde vas?");
                imprimir("1. Plaza del pueblo");
                imprimir("2. Entrada del pueblo");
                opcion = scan(1,2);
                if(opcion == 1){
                    combate("encapuchado");
                    imprimir(verde+"Despues de un largo combate, consigues matar al encapuchado. Los saqueadores huyen despavoridos."+reset);
                    System.exit(0);
                }
                else if(opcion == 2){
                    combate("persona");
                    imprimir("Conseguis eliminar a los saqueadores de la entrada, pero se escuchan gritos en la plaza");
                    combate("encapuchado");
                    imprimir(verde+"Despues de un largo combate, consigues matar al encapuchado. Los saqueadores huyen despavoridos."+reset);
                    System.exit(0);
                }
            }
        } else if (saqueador==true){
            imprimir("Llegais al pueblo de noche y entrais por la fuerza");
            compañero("encapuchado");
            combate("persona");
            imprimir(rojo+"");
            imprimir("Hay una bifurcación, por donde vas");
            imprimir("1. Calle principal");
            imprimir("2. Calle secundaria");
            opcion = scan(1,2);
            if (opcion == 1){
                combate("persona");
            }
            else if(opcion == 2){
                obtener("balas",5);
                combate("persona");
            }
        }
    }

    //FUNCIONES HISTORIA SECUNDARIAS
    static void imprimir(String texto){
        String[] palabras_moradas = {"zombies","zombie","zomblin", "blindado"};
        for(String palabra : palabras_moradas){
            texto = texto.replaceAll("(?i)(\\b"+palabra+"\\b)", morado + "$1" + reset);
        }
        String[] palabras_rojas = {"saqueadores", "encapuchado", "cadaveres", "cadaver", "muerto"};
        for(String palabra : palabras_rojas){
            texto = texto.replaceAll("(?i)(\\b"+palabra+"\\b)", rojo + "$1" + reset);
        }
        String[] palabras_militares = {"militar", "militares", "oxidada", "campamento", "mapa"};
        for(String palabra : palabras_militares){
            texto = texto.replaceAll("(?i)(\\b"+palabra+"\\b)", marron + "$1" + reset);
        }

        System.out.println(texto);
    }

    static boolean ahorcado() {
        String palabras[] = {"alfombra", "comida", "java", "edificios", "sandia","girafa"};
        Random rand =  new Random();
        int contador = 0;

        int eleccion = rand.nextInt(palabras.length);
        String solucion = palabras[eleccion];
        imprimir(palabras[eleccion]);

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
        if(tiendaLlegar==false) {
            tiendaLlegar=true;
            imprimir("En cuanto pones un pie dentro de la tienda escuchas dos zombies en la habitación de al lado.");
            imprimir("Ves dos objetos en una mesa. Parecen una percha y una barra de metal bastante oxidada");
            imprimir("¿Que eliges?");
            imprimir("1. Percha");
            imprimir("2. Barra de metal");
            opcion = scan(1, 2);
            if (opcion == 1) {
                cinematica("percha");
                equipar("percha");
                if ((boolean) armas[2][0] == true) {
                    combate("zombie");
                    if (puebloLlegar){
                        pueblo();
                    }
                    else{
                        ciudad();
                    }
                }
            } else if (opcion == 2) {
                cinematica("barra de metal");
                equipar("barra de metal");
                if ((boolean) armas[2][1] == true) {
                    imprimir(rojo + "Cuando intentas pegar el primer golpe con la barra de metal, se parte en dos. Intentas alcanzar algo para que defenderte pero eres devorado por los zombies" + reset);
                }
            }
        }
        else if(tiendaLlegar==true&&puebloLlegar==true){
            imprimir("Ves los cadaveres de zombies que mataste antes, te limitas a coger los suministros e irte.");
            pueblo();
        }
        else if(tiendaLlegar==true){
            imprimir("Ves los cadaveres de zombies que mataste antes, decides salir a la calle.");
            ciudad();
        }
    }

    static void edificio(){
        cinematica("edificio");
        if(edificioLlegar==false) {
            edificioLlegar=true;
            if (encapuchadoVisto == false) {
                imprimir("Estás a las puertas del edificio más alto de la ciudad, las vistas serán buenas desde la azotea.");
                scan(1);
                imprimir("Te encuentras con un encapuchado mirandote fijamente");
                imprimir("Necesito a gente avispada para un trabajito. Si adivinas en lo que estoy pensado te dejaré unirte a mi equipo");
            } else {
                imprimir("El edificio del que te habló aquel encapuchado. Decides subir y hablar con él");
                scan(1);
                imprimir("Te encuentras al encapuchado de antes mirandote fijamente");
                imprimir("- Qué, te ha picado la curiosidad. Mira, estoy reuniendo a un equipo. Si adivinas lo que estoy pensando dejaré que te unas.");
            }
            if (ahorcado()) {
                imprimir("- Ja, bien hecho.");
                imprimir("- Qué, ¿aceptas mi propuesta?");
                imprimir("1. Aceptar");
                imprimir("2. Combatir");
                opcion = scan(1, 2);
                if (opcion == 1) {
                    imprimir ("Decides unirte a su grupo de saqueadores" + reset);
                    saqueador = true;
                    imprimir(rojo + "");
                    pueblo();
                } else if (opcion == 2) {
                    imprimir("- Tenías potencial, una lástima que tenga que matarte");
                    combate("encapuchado");
                    imprimir("Consigues acabar con el encapuchado y vas hacia la salida. Peor al girarte para ver el cadaver una ultima vez, \n ves que ha desaparecido. Tienes el presentimiento de que no será la última vez que le veas");
                    imprimir("Decides salir a la calle");
                    ciudad();
                }
            } else {
                imprimir("- Solo acepto a los mejores, y parece que tú no eres uno de ellos.");
                imprimir(rojo + "El encapuchado te mata a sangre fría" + reset);
            }
        }
        else if(edificioLlegar==true){
            imprimir("Decides subir de nuevo para otear el horizonte.");
            imprimir("Pasan un par de horas por lo que decides marcharte antes de que vengan zombies");
            ciudad();
        }
    }

    static void militares(){
            if(militaresLlegar==false) {
                imprimir("Sigues andando por el bosque y te encuentras un campamento militar, ¿te acercas?");
            }
            else{
                imprimir("Vuelves al campamento militar ¿te acercas?");
            }
            imprimir("1. Acercarte");
            imprimir("2. Alejarte");
            opcion = scan(1, 2);
            if (opcion == 1) {
                cinematica("campamento");
                imprimir(rojo + "Te acercas a los militares y antes de que te puedas explicar recibes un disparo" + reset);
            }
            if (opcion == 2) {
                if(militaresLlegar==false) {
                    imprimir("No te fias y te alejas");
                }
                else{
                    imprimir("Te alejas sin ser visto");
                }
                if(encapuchadoVisto==false) {
                    imprimir("Escuchas un ruido detras tuya y al girarte ves a un encapuchado.");
                    imprimir("- Si estas cansado de andar por ahí, reunete conmigo en la azotea del edificio más alto de la ciudad.");
                    encapuchadoVisto=true;
                }
                else{
                    imprimir("Ves a alguien a lo lejos, podría ser un zombie, o el encapuchado de antes. Decides apurar el paso");
                }
                if(militaresLlegar==false) {
                    imprimir("Tras una larga caminata te encuentras con una bifurcación, un cartel indica que el camino \n izquierdo lleva a la ciudad mientras que el otro vuelve al inicio del bosque ");
                    militaresLlegar=true;
                }
                else{
                    imprimir("Te vuelves a encontrar con la bifurcación de antes, ¿por donde continuas?");
                }
                imprimir("1. Ciudad");
                imprimir("2. bosque");
                opcion = scan(1, 2);
                if (opcion == 1) {
                    ciudad();
                } else if (opcion == 2) {
                    bosque();
                }
            }
    }



    //FUNCIONES INVENTARIO

    static void equipar(String nombre) {
        nombre = nombre.toLowerCase();
        for (int i = 0; i < armas[0].length; i++) {
            if (((String) armas[0][i]).equals(nombre)) {
                armas[2][i] = true;
                imprimir("Has equipado: " + nombre);
                return;
            }
        }
        imprimir("Arma no encontrada: " + nombre);
    }

    static void obtener(String nombre, int cantidad) {
        for (int i = 0; i < inventario[0].length; i++) {
            if (((String) inventario[0][i]).equalsIgnoreCase(nombre)) {
                inventario[1][i] = true;
                inventario[2][i] = (int)inventario[2][i]+cantidad;
                imprimir("Obtienes: " + cantidad + nombre);
                return;
            }
        }
        imprimir("Objeto no encontrada: " + nombre);
    }



    //FUNCIONES COMBATE

    static void combate(String tipo){
        if(combatir(tipo)){
            imprimir(verde+"Sobrevives, por ahora..."+reset);
        }
        else{
            imprimir(rojo+"Has muerto"+reset);
            System.exit(0);
        }
    }

    static boolean combatir(String tipo) {

        stats_enem(tipo);
        stats_arma();

        while (true) {
            while(vida_e >0&&vida_jug>0) {
                imprimir("");
                imprimir(tipo_e+": "+vida_e);
                imprimir("Vida: "+vida_jug);
                imprimir("1. Combate cuerpo a cuerpo");
                imprimir("2. Pistola");
                imprimir("3. Inventario");

                int opcion = scan(1,2,3);

                if (opcion == 1) {
                    vida_e -= 10+daño_arma;
                }
                if (opcion == 2) {
                    if ((int)inventario[2][1] > 0) {
                        inventario[2][1] = (int)inventario[2][1]-1;
                        vida_e -= 30;
                    } else {
                        imprimir("No tienes balas");
                    }
                }
                if (opcion == 3) {
                    imprimir("Balas: " + inventario[2][1]);
                    imprimir("Vendas: " +  inventario[2][2]);
                    imprimir("1. Usar vendas");
                    imprimir("2. Volver");
                    opcion = scan(1,2);
                    if(opcion == 1) {
                        if ((int) inventario[2][2] > 0){
                            inventario[2][2] = (int) inventario[2][2] - 1;
                            vida_jug += 30;
                        }
                        else{
                            imprimir("No tienes vendas");
                            continue;
                        }
                    }
                    if(opcion == 2){
                        continue;
                    }
                }
                if(vida_e<=0){
                    return true;
                }
                combate_compañero(tipo);
                dialogosCombate(tipo);
                ia_enem(tipo);
                if(vida_jug<=0){
                    return false;
                }
            }
        }
    }

    static void compañero(String nombre){
        for(int i = 0; i < compañeros[0].length; i++) {
            String comprobador = (String) compañeros[0][i];
            if(comprobador.equalsIgnoreCase(nombre)&&(boolean)compañeros[1][i]==false){
                compañeros[1][i] = true;
                imprimir( comprobador + " decide ir contigo");
            }
        }
    }

    static void combate_compañero(String tipo){
        String nombre = "";
        for(int i = 0; i<compañeros[1].length; i++){
            if((boolean)compañeros[1][i]==true){
                nombre=(String)compañeros[0][i];
            }
        }
        int eleccion = rand.nextInt(1,3);
        if(eleccion==1){
            vida_e -= 10;
            imprimir(nombre+" hace 10 de daño a "+tipo);
        }
        if(eleccion==2){
            imprimir(nombre+" te cura 10 de vida");
            vida_jug += 10;
        }
    }

    static void stats_arma(){
        for(int i=0;i<armas[0].length;i++){
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
                imprimir("Mordisco");
                esquivar();
                if (tarde == false) {
                    imprimir("Esquivaste con exito");
                } else {
                    imprimir("No esquivaste");
                    vida_jug -= daño_e + 10;
                }
            }
            if (eleccion == 2) {
                imprimir("Arañazo");
                esquivar();
                if (tarde == false) {
                    imprimir("Esquivaste con exito");
                } else {
                    imprimir("No esquivaste");
                    vida_jug -= daño_e;
                }
            }
        }
        if(tipo.equals("persona")||tipo.equals("encapuchado")) {
            if (eleccion == 1) {
                    imprimir("Disparo");
                    vida_jug -= 20;
            }
            if (eleccion == 2) {
                imprimir("Golpe");
                esquivar();
                if (tarde == false) {
                    imprimir("Esquivaste con exito");
                } else {
                    imprimir("No esquivaste");
                    vida_jug -= daño_e+10;
                }
            }
        }
    }

    static void dialogosCombate(String tipo){
        if(tipo.equals("zombie")){
            if(vida_e<40){
                imprimir(morado+"Cerebrooooos"+reset);
            }
        }
        if(tipo.equals("encapuchado")){
            if(vida_e<70){
                imprimir(rojo+"..."+reset);
            }
        }
    };

    static void esquivar(){
        tarde = true;
        boolean[] resultadoDecidido = {false};
        Timer tim = new Timer();

        imprimir("");
        imprimir("ESQUIVA ( X )");
        TimerTask tarea = new TimerTask() {
            public void run() {
                if(!resultadoDecidido[0]) {
                    tarde = true;
                    resultadoDecidido[0] = true;
                    tim.cancel();
                }
            }
        };
        tim.schedule(tarea, tiempo_esq);
        sc.nextLine();
        String tecla = sc.nextLine();

        if(!resultadoDecidido[0]) {
            if (tecla.equals("x")) {
                tarde = false;
            } else {
                tarde = true;
            }
            resultadoDecidido[0] = true;
            tarea.cancel();
            tim.cancel();
        }
    }
}