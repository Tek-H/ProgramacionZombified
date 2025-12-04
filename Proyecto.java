package Proyecto;

import java.util.Arrays;
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
    static String rojo = "\u001B[31m";
    static String verde = "\u001B[32m";
    static String morado = "\u001B[35m";
    static String marron = "\u001B[33m";
    static String reset = "\u001B[0m";

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
    static boolean enterrado = true;
    static boolean ciudadLlegar = false;
    static boolean militaresLlegar = false;
    static boolean puebloLlegar = false;
    static boolean tiendaLlegar = false;
    static boolean edificioLlegar = false;
    static boolean graneroLlegar = false;
    static boolean encapuchadoVisto = false;



    public static void main(String[] args) {
        imprimir("texto", "Después de varias semanas de viaje por carretera tu coche se ha averiado definitivamente. Armado con \nuna pistola y con poco más que unas balas y vendas decides seguir una de las tres carreteras que tienes delante.");
        sleep(500);
        imprimir("texto","1. Seguir hasta la ciudad");
        imprimir("texto","2. Seguir en dirección al bosque");
        imprimir("texto","3. Ir a buscar ayuda");
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
                imprimir("funcion","Pulsa 1");
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
            imprimir("funcion","Pulsa 1-2");
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
            imprimir("funcion","Pulsa 1-3");
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
            imprimir("funcion","Pulsa 1-4");
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
            imprimir("funcion","Introduce una letra");
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

    static void sleep(int tiempo){
        try {
            Thread.sleep(tiempo);
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
            sleep(400);
        }
        System.out.println("");
    }



    //FUNCIONES HISTORIA PARTE1

    static void ciudad() {
        cinematica("ciudad");
        if(ciudadLlegar==false) {
            ciudadLlegar=true;
            imprimir("texto", "Tras varias horas de viaje porfín entras en la ciudad sin \nningún percance. En cuanto llegas al centro se te presentan varios sitios a donde ir");
        }
        else if (ciudadLlegar==true){
            imprimir("texto","Vuelves a la ciudad, ¿por donde quieres ir ahora?");
        }
        imprimir("texto","1. Distrito comercial");
        imprimir("texto","2. Distrito residencial");
        imprimir("texto","3. Afueras de la ciudad");
        imprimir("texto","4. Distrito financiero");
        opcion = scan(1,2,3,4);
        if (opcion == 1) {
            tienda();
        } else if (opcion == 2) {
            imprimir("texto","Te diriges camino al distrito residencial");
            opcion = scan(1);
            cinematica("pueblo");
            imprimir("texto","En cuanto llegas al distrito residencial ves a lo lejos un grupo de gente en una casa del fondo");
            imprimir("texto", "- Tú el del fondo o te acercas o te metemos un tiro entre ceja y ceja.");
            cinematica("casa");
            if (opcion == 1) {
                encuentro_gente();
            }
        } else if (opcion == 3) {
            if(graneroLlegar == false) {
                graneroLlegar = true;
                imprimir("texto","Andas por el extrarradio de la ciudad y ves un cartel que indica la ubicación de un granero. No está \nmuy lejos de donde estás por lo que te encaminas en esa dirección");
                cinematica("granero");
                imprimir("texto","Entras al granero, parece que los animales se han escapado.");
                scan(1);
                imprimir("texto","Ves un zombie al fondo, lo unico que tienes cerca es un martillo, una pala \ny un taladro eléctrico, aunque podría no tener batería");
                sleep(1000);
                imprimir("texto","1. Martillo");
                imprimir("texto","2. Pala");
                imprimir("texto","3. Taladro");
                opcion = scan(1, 2, 3);
                if (opcion == 1) {
                    cinematica("martillo");
                    equipar("martillo");
                    armasMesa[1][0] = false;
                    if ((boolean) armas[2][2] == true) {
                        combate("zombie");
                        imprimir("texto", "Registras todo el granero pero no hay nada. Habiendo hecho esto, lo unico que queda por hacer es seguir el camino");
                        sleep(1000);
                        bosque();
                        return;
                    }
                } else if (opcion == 2) {
                    cinematica("pala");
                    equipar("pala");
                    armasMesa[1][1] = false;
                    if ((boolean) armas[2][3] == true) {
                        combate("zombie");
                        imprimir("texto", "Registras todo el granero pero no hay nada. Habiendo hecho esto, lo unico que queda por hacer es seguir el camino");
                        sleep(1000);
                        bosque();
                        return;
                    }
                } else if (opcion == 3) {
                    cinematica("taladro");
                    equipar("taladro");
                    armasMesa[1][2] = false;
                    if ((boolean) armas[2][4] == true) {
                        sleep(1000);
                        imprimir("texto",rojo + "Atacas con el taladro pero al intentar taladrarle la cabeza te das cuenta de que no funciona. Despues de un forcejeo mueres");
                        System.exit(0);
                    }
                }
            }
            if(graneroLlegar == true) {
                imprimir("texto","Vuelves al granero de antes. Todavía están las herramientas que dejaste");
                for(int i=0; i<armasMesa[0].length;i++){
                    if((boolean)armasMesa[1][i]==true){
                        imprimir("texto",i+1+". "+armasMesa[0][i]);
                    }
                }
                opcion = scan(1, 2, 3);
                if (opcion == 1&&(boolean)armasMesa[1][0]==true){
                    equipar("martillo");
                }
                if (opcion == 1&&(boolean)armasMesa[1][0]==false){
                    imprimir("texto","Decides continuar con el martillo.");
                }
                if (opcion == 2&&(boolean)armasMesa[1][1]==true){
                    equipar("pala");
                }
                if (opcion == 1&&(boolean)armasMesa[1][1]==false){
                    imprimir("texto","Decides continuar con la pala.");
                }
                if(opcion == 3&&(boolean)armasMesa[1][2]==true){
                    imprimir("texto","Te das cuenta de que el taladro no tenia batería por lo que tras buscar un rato encuentras una y se la pones a la herramienta.");
                    equipar("taladro");
                }
            }
        }
        else if (opcion == 4) {
            edificio();
        }
    }

    static void bosque(){
        cinematica("bosque");
        imprimir("texto","Llegas al bosque. A unos metros hay un coche abandonado y en la distancia se distingue la silueta de un edificio \nmuy alto. También recuerdas que había una base militar en medio del bosque. ¿A donde decides ir?");
        imprimir("texto","1. Coche");
        imprimir("texto","2. Edificio");
        imprimir("texto","3. Militares");
        opcion = scan(1,2,3);
        if (opcion == 1) {
            cinematica("coche");
            imprimir("texto","Le hechas un vistazo al coche, pero en cuanto arrancas lo arrancas el sonido del \n motor se extingue. Intentas usar tus escasos conocimientos de mecánica para arreglarlo.");
            sleep(200);
            imprimir("texto","Escuchas unos gemidos a tu espalda, parece que el ruido del motor ha llamado la atención de un zombie");
            combate("zombie");
            imprimir("texto","Ves un grupo de gente al fondo");
            sleep(1000);
            imprimir("texto", "- Tú el del fondo o te acercas o te metemos un tiro entre ceja y ceja.");
            cinematica("bosque");
            encuentro_gente();
        }
        else if (opcion == 2) {
            if(ciudadLlegar==false) {
                imprimir("texto", "Te alejas en dirección al edificio y antes de que te des cuenta llegas a una ciudad");
            }
            else{
                imprimir("texto","Vas hacia el edificio más alto de la ciudad");
            }
            edificio();
        }
        else if (opcion == 3) {
            cinematica("campamento");
            if((boolean)armas[2][3]==true){
                if(enterrado==true) {
                    enterrado=false;
                    imprimir("texto","Encuentras una caja medio enterrada. Usas tu pala para desenterrarla pero al intentar abrirla no puedes.");
                    if(desbloquear()){
                        obtener("balas", 5);
                        obtener("vendas", 3);
                    }
                }
                else{
                    System.out.println("Vuelves a donde desenterraste esa caja de suministros. Está vacía");
                }
            }
            else{
                imprimir("texto","Ves algo medio enterrado pero no tienes una pala.");
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
        imprimir("texto","Llevas andando un rato cuando te encuentras el cadaver de un militar junto a un mapa ¿que haces?");
        imprimir("texto","1. Coger el mapa");
        imprimir("texto","2. Dejar el mapa");
        opcion = scan(1,2);
        if (opcion == 1) {
            obtener("mapa",1);
            if((boolean)inventario[1][0]==true) {
                imprimir("texto","Te agachas a coger el mapa, y al revisarlo aparece marcado un campamento militar. ");
                imprimir("texto","Después de unas horas siguiendo el mapa ves a lo lejos humo. Debe ser el campamento");
                opcion = scan(1);
                if (opcion == 1) {
                    cinematica("campamento");
                    imprimir("texto",rojo+"Vas hacia el campamento, pero antes de que te des cuenta te disparan en el pecho. Mueres"+reset);
                    return;
                }
            }
        } else if (opcion == 2) {
            imprimir("texto","Dejas el mapa, estaría feo robar las cosas de un muerto.");
            militares();
        }
    }


    //FUNCIONES HISTORIA PARTE2
    static void pueblo(){
        if(saqueador==false) {
            cinematica("pueblo");
            if(puebloLlegar==false) {
                puebloLlegar = true;
                imprimir("texto","Después de mucho tiempo de viaje llegais a un pueblo. Bill empieza a hablar al grupo.");
                imprimir("texto", "- Vale, porfin hemos llegado. Tenemos que ver si queda algo de provecho o si hay zombies ¿Por donde empezamos?");
                imprimir("texto","1. Casa de la izquierda");
                imprimir("texto","2. Casa de la derecha");
                opcion = scan(1, 2);
                if (opcion == 1) {
                    cinematica("casa");
                    imprimir("texto", "Decides registrar la casa de la izquierda. No hay provisiones.");
                    sleep(750);
                    imprimir("texto", "Antes de salir encuchas un ruido a tu espalda.");
                    combate("zombie");
                    imprimir("texto", "Bill se acerca preocupado.");
                    imprimir("texto", "- ¿Estás bien, te ha mordido? Menos mal que te has dado cuenta antes de que fuese demasiado tarde.");
                    sleep(1000);
                    imprimir("texto", "Os reunis en la plaza del pueblo. Y Bill dice:");
                    imprimir("texto","Tenemos que ir a la ciudad a buscar provisiones. Iremos el nuevo y yo");
                    compañero("bill");
                    tienda();
                } else if (opcion == 2) {
                    cinematica("casa");
                    imprimir("texto", "Decides ir a la casa de la derecha");
                    obtener("balas",5);
                    sleep(1000);
                    imprimir("texto", "Escuchas un grito. Sales a la calle y ves a bill con un mordisco en el brazo");
                    imprimir("texto", "Un miembro del grupo dice:");
                    imprimir("texto", "- Novato odio tener que pedirte esto. Pero necesitamos que vayas a la ciudad a por medicinas. Mientras nos encargaremos de que Bill no muera. ");
                    tienda();
                }
            }
            if(puebloLlegar==true){
                imprimir("texto","LLegas al pueblo y dejas los suministros");
                imprimir("texto","Escuchas disparos y gritos, hay saqueadores");
                combate("persona");
                imprimir("texto","¿A donde vas?");
                imprimir("texto","1. Plaza del pueblo");
                imprimir("texto","2. Entrada del pueblo");
                opcion = scan(1,2);
                if(opcion == 1){
                    plaza();
                }
                else if(opcion == 2){
                    combate("persona");
                    imprimir("texto","Conseguis eliminar a los saqueadores de la entrada, pero se escuchan gritos en la plaza");
                    plaza();
                }
            }
        } else if (saqueador==true){
            imprimir("texto","Los saqueadores y tú observais el pueblo desde lejos. El encapuchado se te acerca");
            imprimir("texto","- Nos ponemos en marcha novato. Son bastantes así que puede que den problemas, no te separes de mi");
            compañero("encapuchado");
            imprimir("texto", "Reventais la puerta principal y os abris paso pegando tiros. Se te acerca uno de los residentes.");
            combate("persona");
            sleep(1000);
            imprimir("texto",rojo+"");
            imprimir("texto","Hay una bifurcación, por donde vas");
            imprimir("texto","1. Calle principal");
            imprimir("texto","2. Calle secundaria");
            opcion = scan(1,2);
            if (opcion == 1){
                imprimir("texto", "Decides ir por la calle principal");
                combate("persona");
                plaza();
            }
            else if(opcion == 2){
                imprimir("texto", "Decides ir por la calle secundaria. Te encuentras con una mochila");
                obtener("balas",5);
                obtener("vendas",1);
                imprimir("texto", "Una bala pasa rozando tu cabeza");
                combate("persona");
                plaza();
            }

        }
    }

    static void plaza(){
        if(saqueador==false) {
            combate("encapuchado");
            imprimir("texto", "Despues de un largo combate, consigues matar al encapuchado. Los saqueadores huyen despavoridos.");
            sleep(1000);
            imprimir("texto", "Bill se acerca a tí");
            imprimir("texto", "- Menos mal de que te has encargado de ese capullo. Parecía peligroso");
            sleep(2000);
            imprimir("texto", verde + "Gracias a todo lo que has hecho por el pueblo, los supervivientes te nombran su lider");
            System.exit(0);
        }
        if(saqueador==true){
            imprimir("texto", "Llegas a la plaza. Hay otra persona intentando matarte");
            combate("persona");
            sleep(1000);
            imprimir("texto", "Conseguis matar al último residente. El encapuchado se te acerca");
            imprimir("texto", "- No lo has hecho mal novato");
            sleep(2000);
            imprimir("texto", "Continuas con los saqueadores. Eventualmente una coalición de distintos \nasentimientos consiguen mataros a todos.");
        }
    }

    //FUNCIONES HISTORIA SECUNDARIAS

    static void imprimir(String opcion ,String texto){
        if(saqueador==true){
            reset = rojo;
        }
        else{
            reset = "\u001B[0m";
        }

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

        String[] texto_arr = texto.split(" ");

        if(opcion.equals("texto") && saqueador==true){
            texto = rojo+texto;
            System.out.println(texto);
            return;
        }

        if(opcion.equals("texto")){
                for (int i = 0; i < texto.length(); i++) {
                    if (texto.charAt(i) == ' ') {
                        System.out.print(texto.charAt(i));
                        sleep(100); // 200
                    } else {
                        System.out.print(texto.charAt(i));
                        sleep(50); //100
                    }
                }
                System.out.println("");
        }
        if(opcion.equals("funcion")){
            System.out.println(texto);
        }
    }



    static void tienda(){
        if(tiendaLlegar==false) {
            imprimir("texto", "Te pones en marcha dirección al distrito comercial, esperas encontrar algunas provisiones");
            cinematica("tienda");
            tiendaLlegar=true;
            imprimir("texto","Llegas al distrito comercial. En cuanto pones un pie dentro de la primera tienda escuchas dos zombies en la habitación de al lado.");
            sleep(500);
            imprimir("texto","Ves dos objetos en una mesa. Parecen una percha y una barra de metal bastante oxidada");
            imprimir("texto","¿Que eliges?");
            imprimir("texto","1. Percha");
            imprimir("texto","2. Barra de metal");
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
                sleep(500);
                if ((boolean) armas[2][1] == true) {
                    imprimir("texto",rojo + "Cuando intentas pegar el primer golpe con la barra de metal, se parte en dos. Intentas alcanzar algo \n que defenderte pero eres devorado por los zombies" + reset);
                    System.exit(0);
                }
            }
        }
        else if(tiendaLlegar==true&&puebloLlegar==true){
            cinematica("tienda");
            imprimir("texto","Ves los cadaveres de los zombies que mataste antes, te limitas a coger los suministros e irte.");
            pueblo();
        }
        else if(tiendaLlegar==true){
            cinematica("tienda");
            imprimir("texto","Ves los cadaveres de los zombies que mataste antes, decides salir a la calle.");
            ciudad();
        }
    }

    static void edificio(){
        cinematica("edificio");
        if(edificioLlegar==false) {
            edificioLlegar=true;
            if (encapuchadoVisto == false) {
                imprimir("texto","Estás a las puertas del edificio más alto de la ciudad, las vistas serán buenas desde la azotea.");
                scan(1);
                imprimir("texto","Te encuentras con un encapuchado mirandote fijamente");
                imprimir("texto","Necesito a gente avispada para un trabajito. Si adivinas en lo que estoy pensado te dejaré unirte a mi equipo");
            } else {
                imprimir("texto","El edificio del que te habló aquel encapuchado. Decides subir y hablar con él");
                scan(1);
                imprimir("texto","Te encuentras al encapuchado de antes mirandote fijamente");
                imprimir("texto","- Qué, te ha picado la curiosidad. Mira, estoy reuniendo a un equipo. Si adivinas lo que estoy pensando dejaré que te unas.");
            }
            if (ahorcado()) {
                imprimir("texto","- Ja, bien hecho.");
                imprimir("texto","- ¿Aceptas mi propuesta?");
                imprimir("texto","1. Aceptar");
                imprimir("texto","2. Combatir");
                opcion = scan(1, 2);
                if (opcion == 1) {
                    imprimir ("texto","Decides unirte a su grupo de saqueadores" + reset);
                    saqueador = true;
                    imprimir("texto",rojo + "");
                    pueblo();
                } else if (opcion == 2) {
                    imprimir("texto","- Tenías potencial, una lástima que tenga que matarte");
                    combate("encapuchado");
                    imprimir("texto","Consigues acabar con el encapuchado y vas hacia la salida. Peor al girarte para ver el cadaver una ultima vez, \n ves que ha desaparecido. Tienes el presentimiento de que no será la última vez que le veas");
                    if(ciudadLlegar==false) {
                        imprimir("texto","Decides salir a la calle y explorar el resto de la ciudad");
                    }
                    else{
                        imprimir("texto","Decides salir a la calle");
                    }
                    ciudad();
                }
            } else {
                imprimir("texto","- Solo acepto a los mejores, y parece que tú no eres uno de ellos.");
                imprimir("texto",rojo + "El encapuchado te mata a sangre fría" + reset);
            }
        }
        else if(edificioLlegar==true){
            imprimir("texto","Decides subir de nuevo para otear el horizonte.");
            imprimir("texto","Pasan un par de horas por lo que decides marcharte antes de que vengan zombies");
            ciudad();
        }
    }

    static void militares(){
            if(militaresLlegar==false) {
                imprimir("texto","Sigues andando por el bosque y te encuentras un campamento militar, ¿te acercas?");
            }
            else{
                imprimir("texto","Vuelves al campamento militar ¿te acercas?");
            }
            imprimir("texto","1. Acercarte");
            imprimir("texto","2. Alejarte");
            opcion = scan(1, 2);
            if (opcion == 1) {
                cinematica("campamento");
                imprimir("texto",rojo + "Te acercas a los militares y antes de que te puedas explicar recibes un disparo" + reset);
            }
            if (opcion == 2) {
                if(militaresLlegar==false) {
                    imprimir("texto","No te fias y te alejas");
                }
                else{
                    imprimir("texto","Te alejas sin ser visto");
                }
                if(encapuchadoVisto==false) {
                    imprimir("texto","Escuchas un ruido detras tuya y al girarte ves a un encapuchado.");
                    imprimir("texto","- Si estas cansado de andar por ahí, reunete conmigo en la azotea del edificio más alto de la ciudad.");
                    encapuchadoVisto=true;
                }
                else{
                    imprimir("texto","Ves a alguien a lo lejos, podría ser un zombie, o el encapuchado de antes. Decides apurar el paso");
                }
                if(militaresLlegar==false) {
                    imprimir("texto","Tras una larga caminata te encuentras con una bifurcación, un cartel indica que el camino \n izquierdo lleva a la ciudad mientras que el otro vuelve al inicio del bosque ");
                    militaresLlegar=true;
                }
                else{
                    imprimir("texto","Te vuelves a encontrar con la bifurcación de antes, ¿por donde continuas?");
                }
                imprimir("texto","1. Ciudad");
                imprimir("texto","2. bosque");
                opcion = scan(1, 2);
                if (opcion == 1) {
                    ciudad();
                } else if (opcion == 2) {
                    bosque();
                }
            }
    }

    static void encuentro_gente(){
        imprimir("texto", "Te acercas y dos de ellos te apuntan con pistolas. El más alto de todos habla: ");
        imprimir("texto", "- No pareces mál tipo, vente con nosotros. Tenemos refugio y comida");
        sleep(1000);
        imprimir("texto", "Indica que bajen las armas");
        sleep(1000);
        imprimir("texto", "- Por cierto soy Bill, toma esto");
        equipar("barra de metal");
        sleep(1000);
        imprimir("texto",verde+"Deciden aceptarte y vives con ellos"+reset);
        imprimir("texto","Te curan las heridas");
        curar(1000);
        pueblo();
    }



    //FUNCIONES INVENTARIO

    static void equipar(String nombre) {
        nombre = nombre.toLowerCase();
        for (int i = 0; i < armas[0].length; i++) {
            if (((String) armas[0][i]).equals(nombre)) {
                armas[2][i] = true;
                imprimir("funcion","Has equipado: " + nombre);
                return;
            }
        }
        imprimir("funcion","Arma no encontrada: " + nombre);
    }

    static void obtener(String nombre, int cantidad) {
        for (int i = 0; i < inventario[0].length; i++) {
            if (((String) inventario[0][i]).equalsIgnoreCase(nombre)) {
                inventario[1][i] = true;
                inventario[2][i] = (int)inventario[2][i]+cantidad;
                imprimir("funcion","Obtienes: " + cantidad +" "+nombre);
                return;
            }
        }
        imprimir("funcion","Objeto no encontrada: " + nombre);
    }



    //FUNCIONES MINIJUEGOS
    static boolean ahorcado() {
        String palabras[] = {"alfombra", "comida", "java", "edificios", "sandia","girafa"};
        Random rand =  new Random();
        int contador = 0;

        int eleccion = rand.nextInt(palabras.length);
        String solucion = palabras[eleccion];
        imprimir("funcion",palabras[eleccion]);

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

    static boolean desbloquear(){
        imprimir("funcion","-----DESBLOQUEAR-----");
        int largo = 5;

        int[] solucion = new int[largo];

        for(int i=0;i<largo;i++){
            int numero = rand.nextInt(1,6);
            solucion[i] = numero;
        }

        int[] jugador = new int[largo];
        int contador = 0;

        while(true) {
            imprimir("funcion",Arrays.toString(jugador));
            int caracter = sc.nextInt();
            for (int i = 0; i < largo; i++){
                if (caracter == solucion[i]){
                    jugador[i] = caracter;
                    contador++;
                }
                if (caracter > solucion[i]){
                    if (jugador[i] == 0) {
                        imprimir("funcion","La posición "+i+" es menor");
                    }
                }
                if (caracter < solucion[i]){
                    if (jugador[i] == 0) {
                        imprimir("funcion","La posición "+i+" es mayor");
                    }
                }
            }
            if (jugador == solucion){
                imprimir("funcion",Arrays.toString(jugador));
                return true;
            }
        }
    }


    //FUNCIONES COMBATE

    static void combate(String tipo){
        if(combatir(tipo)){
            imprimir("texto",verde+"Sobrevives, por ahora..."+reset);
        }
        else{
            imprimir("texto",rojo+"Has muerto"+reset);
            System.exit(0);
        }
    }

    static boolean combatir(String tipo) {
        stats_enem(tipo);
        stats_arma();

        while (true) {
            while(vida_e >0&&vida_jug>0) {
                imprimir("funcion","");
                imprimir("funcion",tipo_e+": "+vida_e);
                imprimir("funcion","Vida: "+vida_jug);
                imprimir("funcion","1. Combate cuerpo a cuerpo");
                imprimir("funcion","2. Pistola");
                imprimir("funcion","3. Inventario");

                int opcion = scan(1,2,3);

                if (opcion == 1) {
                    vida_e -= 10+daño_arma;
                }
                if (opcion == 2) {
                    if ((int)inventario[2][1] > 0) {
                        inventario[2][1] = (int)inventario[2][1]-1;
                        vida_e -= 30;
                    } else {
                        imprimir("funcion","No tienes balas");
                    }
                }
                if (opcion == 3) {
                    imprimir("funcion","Balas: " + inventario[2][1]);
                    imprimir("funcion","Vendas: " +  inventario[2][2]);
                    imprimir("funcion","1. Usar vendas");
                    imprimir("funcion","2. Volver");
                    opcion = scan(1,2);
                    if(opcion == 1) {
                        if ((int) inventario[2][2] > 0){
                            inventario[2][2] = (int) inventario[2][2] - 1;
                            curar(30);
                        }
                        else{
                            imprimir("funcion","No tienes vendas");
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
                dialogosCombate(tipo);
                ia_enem(tipo);
                if(vida_jug<=0){
                    return false;
                }
                combate_compañero(tipo);
                if(vida_e<=0){
                    return true;
                }
            }
        }
    }

    static void curar(int cantidad){
        vida_jug += cantidad;
        if(vida_jug>100){
            vida_jug = 100;
        }
    }

    static void compañero(String nombre){
        for(int i = 0; i < compañeros[0].length; i++) {
            String comprobador = (String) compañeros[0][i];
            if(comprobador.equalsIgnoreCase(nombre)&&(boolean)compañeros[1][i]==false){
                compañeros[1][i] = true;
                imprimir( "texto",comprobador + " decide ir contigo");
            }
        }
    }

    static void combate_compañero(String tipo){
        for(int j = 0; j<compañeros[0].length; j++){
            if((boolean)compañeros[1][j]==true){
                String nombre = (String)compañeros[0][j];
                int eleccion = rand.nextInt(1, 3);

                if (eleccion == 1) {
                    if (vida_jug < 50) {
                        curar(10);
                        imprimir("texto",nombre + " te cura 10 de vida");
                    } else {
                        vida_e -= 5;
                        imprimir("texto",nombre + " hace 5 de daño a " + tipo);
                    }
                } else {
                    vida_e -= 10;
                    imprimir("texto",nombre + " hace 10 de daño a " + tipo);
                }
            }
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
                imprimir("funcion","Mordisco");
                esquivar();
                if (tarde == false) {
                    imprimir("funcion","Esquivaste con exito");
                } else {
                    imprimir("funcion","No esquivaste");
                    vida_jug -= daño_e + 10;
                }
            }
            if (eleccion == 2) {
                imprimir("funcion","Arañazo");
                esquivar();
                if (tarde == false) {
                    imprimir("funcion","Esquivaste con exito");
                } else {
                    imprimir("funcion","No esquivaste");
                    vida_jug -= daño_e;
                }
            }
        }
        if(tipo.equals("persona")||tipo.equals("encapuchado")) {
            if (eleccion == 1) {
                    imprimir("funcion","Disparo");
                    vida_jug -= 20;
            }
            if (eleccion == 2) {
                imprimir("funcion","Golpe");
                esquivar();
                if (tarde == false) {
                    imprimir("funcion","Esquivaste con exito");
                } else {
                    imprimir("funcion","No esquivaste");
                    vida_jug -= daño_e+10;
                }
            }
        }
    }

    static void dialogosCombate(String tipo){
        if(tipo.equals("zombie")){
            if(vida_e<40){
                imprimir("funcion",morado+"Cerebrooooos"+reset);
            }
        }
        if(tipo.equals("encapuchado")){
            if(vida_e<70){
                imprimir("funcion",rojo+"..."+reset);
            }
        }
    };

    static void esquivar(){
        tarde = true;
        boolean[] resultadoDecidido = {false};
        Timer tim = new Timer();

        imprimir("funcion","ESQUIVA ( X )");
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