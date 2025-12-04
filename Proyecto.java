package Proyecto;

import java.util.*;
import java.util.Arrays;

public class Proyecto {
    //VARIBALES GLOBALES
    //Mantienen estados a lo largo de la partida

    //Variables java
    //-------------------------------------------------
    static Scanner sc = new Scanner(System.in);
    static Random rand = new Random();

    //Variable input
    //-------------------------------------------------
    static int opcion;

    //Variables colores
    //-------------------------------------------------
    static String rojo = "\u001B[31m";
    static String verde = "\u001B[32m";
    static String morado = "\u001B[35m";
    static String marron = "\u001B[33m";
    static String reset = "\u001B[0m";

    //Variables inventarios
    //-------------------------------------------------
    static Object inventario [][] = {
            {"Mapa", "Balas", "Vendas"}, //objeto
            {false, true, true}, //¿tiene el objeto?
            {0, 10, 5} //cantidad
    };
    static Object armas[][] = {
            {"percha","barra de metal","martillo","pala","taladro"}, //arma
            {11, 13, 15, 14, 10}, //daño arma
            {false, false, false, false, false}, //¿tiene el arma?
            {false, false, false, false, false} //¿está equipada?
    };
    static Object armasMesa[][] = {
            {"Martillo", "Pala", "Taladro"},
            {true, true, true},
    };
    static Object compañeros[][] = {
            {"Encapuchado","Bill"},
            {false, false},
    };

    //Variables jugador
    //-------------------------------------------------
    static boolean encapuchadoVisto = false;
    static boolean saqueador = false;
    static int vida_jug = 100;
    static boolean tarde = true;
    static int daño_arma = 0;

    //Variables enemigos
    //-------------------------------------------------
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

    //Variables ubicaciones
    //-------------------------------------------------
    static boolean ciudadLlegar = false;
    static boolean militaresLlegar = false;
    static boolean puebloLlegar = false;
    static boolean tiendaLlegar = false;
    static boolean edificioLlegar = false;
    static boolean graneroLlegar = false;

    //Otras variables
    //-------------------------------------------------
    static boolean enterrado = true;
    static int turno =0;



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



    //-------------------------------------------------
    //FUNCIONES DE UTILIDADES
    //-------------------------------------------------

    //Pausa el codigo un el tiempo indicado
    static void sleep(int tiempo){
        try {
            Thread.sleep(tiempo);
        } catch (InterruptedException e) {}
    }

    //Imprime el texto con una animacion como de escritura y ciertas palabras con algunos colores
    static void imprimir(String opcion ,String texto){

        //Si el personaje es saqueador hace que reset sea rojo para que al imprimir una palabra de otro color el siguiente texto vuelva a ser rojo. Si no, es blanco
        if(saqueador==true){
            reset = rojo;
        }
        else{
            reset = "\u001B[0m";
        }

        //Busca por cada palabra del array ignorando mayusculas y si la palabra en la que está coincide con una del array, usa la palabra original y le aplica el color correspondiente.

        //Expresiones regulares usadas:
        // ?i para que no ignore mayusculas y minusculas
        // \\b para que use la palabra completa y no una subcadena
        //$1 usa la palabra original con mayusculas y todo

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

        //Si el personaje es saqueador hace que el texto que vaya a imprimir sea rojo y termina la funcion.
        if(opcion.equals("texto") && saqueador==true){
            texto = rojo+texto;
            System.out.println(texto);
            return;
        }

        //Animación para imprimir texto. Imprime por caracteres con un tiempo de 50 ms y los espacion con 100ms
        if(opcion.equals("texto")){
            for (int i = 0; i < texto.length(); i++) {
                //Imprimir espacios
                if (texto.charAt(i) == ' ') {
                    System.out.print(texto.charAt(i));
                    sleep(100);
                }
                //Imprimir caracteres
                else {
                    System.out.print(texto.charAt(i));
                    sleep(50);
                }
            }
            //Salto de linea
            System.out.println(""); //Imprime el texto directamente

        }
        if(opcion.equals("funcion")){
            System.out.println(texto);
        }


    }



    //-------------------------------------------------
    //FUNCIONES DE ESCANEO
    //-------------------------------------------------

    //ESCANEO DE UN NUMERO

    //Funcion que comprueba si el numero introducido al que está entre los parentesis
    static int scan(int a){
        while(true) {
            //Comprueba si el input de dentro suya es de tipo int. Si no lo es, se ejecuta el else.
            imprimir("funcion","Pulsa 1");
            if (sc.hasNextInt()) {
                opcion = sc.nextInt();
                //Si es de tipo int y es igual a -a- devuelve el numero. Si no ejecuta el else del if padre.
                if (opcion == a) {
                    return opcion;
                }
            }
            //Limpia el anterior input y ejecuta el bucle de nuevo
            else{
                sc.next();
            }
        }
    }

    //ESCANEO DE VARIOS NUMEROS
    //Funcion que  comprueba si el numero introducido es uno de los que están entre parentesis
    static int scan(int a, int b){
        while(true) {
            imprimir("funcion","Pulsa 1-2");
            //Comprueba si el input introducido es de tipo int. Si no lo es, se ejecuta el else.
            if (sc.hasNextInt()) {
                opcion = sc.nextInt();
                //Si es de tipo int y es igual a -a- o a -b- devuelve el numero. Si no ejecuta el else del if padre.
                if (opcion == a || opcion == b) {
                    return opcion;
                }
            }
            else{
                //Limpia el anterior input y ejecuta el bucle de nuevo
                sc.next();
            }
        }
    }

    //Funcion que comprueba si el numero introducido es uno de los que están entre parentesis
    static int scan(int a, int b, int c){
        while(true) {
            imprimir("funcion","Pulsa 1-3");
            //Comprueba si el input introducido es de tipo int. Si no lo es, se ejecuta el else.
            if (sc.hasNextInt()) {
                opcion = sc.nextInt();
                //Si es de tipo int y es mayor o igual que a y menor o igual que c devuelve el numero. Si no ejecuta el else del if padre.
                if (opcion == a || opcion == b || opcion == c) {
                    return opcion;
                }
            }
            else{
                //Limpia el anterior input y ejecuta el bucle de nuevo
                sc.next();
            }
        }
    }

    //Funcion que comprueba si el numero introducido es uno de los que están entre parentesis
    static int scan(int a, int b, int c, int d){
        while(true) {
            imprimir("funcion","Pulsa 1-4");
            //Comprueba si el input introducido es de tipo int. Si no lo es, se ejecuta el else.
            if (sc.hasNextInt()) {
                opcion = sc.nextInt();
                //Si es de tipo int y es mayor o igual que a y menor o igual que d devuelve el numero. Si no ejecuta el else del if padre.
                if (opcion == a || opcion == b || opcion == c || opcion == d) {
                    return opcion;
                }
            }
            else{
                //Limpia el anterior input y ejecuta el bucle de nuevo
                sc.next();
            }
        }
    }

    //ESCANEO DE CARACTERES

    //Funcion que comprueba si has introducido un caracter
    static char scan_char(){
        while(true) {
            imprimir("funcion","Introduce una letra");
            //Comprueba que el input introducido es de tipo char. Si no lo es se ejecuta el else
            if (sc.hasNext()){
                //Usa el primer caracter de lo que hayas introducido
                char a = sc.next().charAt(0);
                //Valida que el input introducido es una letra. Si no lo es ejecuta de nuevo el bucle
                if (Character.isLetter(a)) {
                    //Si el input es una letra suma un turno y devuelve el caracter
                    turno++;
                    return a;
                }
            }
        }
    }


    //ESCANEO DE ARRAYS

    //Funcion que comprueba si la opción introducida es disponible en un array
    static int  scan_arr(int[] array){
        while(true) {
            imprimir("funcion","Pulsa una opción");
            //Comprueba si el input introducido es de tipo int. Si no lo es, se ejecuta el else.
            if (sc.hasNextInt()) {
                opcion = sc.nextInt();
                //Si es de tipo int y es mayor o igual que a y menor o igual que d devuelve el numero. Si no ejecuta el else del if padre.
                for (int validas:array){
                    if (validas == opcion) {
                        return validas;
                    }
                }
                System.out.println("No disponible");
            }
            else{
                //Limpia el anterior input y ejecuta el bucle de nuevo
                sc.next();
            }
        }
    }



    //-------------------------------------------------
    //FUNCIONES CINEMATICAS
    //-------------------------------------------------

    //Funcion que crea una "cinematica" imprimendo texto. Al poner la función hay que indicar que que emoticono vas a usar
    static void cinematica(String emoji){
        int limite=0;

        //Comprueba si el emoji pedido coincide con el texto.
        if (emoji.equals("ciudad")){
            //Asigna a la variable emoji el codigo del mismo
            emoji = "\uD83C\uDFD9\uFE0F";
            //Asigna el limite (numero de espacios que ocupa)
            limite=3;
        }
        //(Esto ocurre en todos)
        else if(emoji.equals("bosque")){
            emoji = "\uD83C\uDF32";
            limite = 2;
        }
        else if(emoji.equals("campamento")){
            emoji = "\uD83C\uDFD5\uFE0F";
            limite = 3;
        }
        else if(emoji.equals("tienda")){
            emoji = "\uD83C\uDFEA";
            limite=2;
        }
        else if(emoji.equals("casa")){
            emoji = "\uD83C\uDFE0";
            limite=2;
        }
        else if(emoji.equals("granero")){
            emoji = "\uD83C\uDFDA\uFE0F";
            limite=3;
        }
        else if(emoji.equals("edificio")){
            emoji = "\uD83C\uDFEC";
            limite=2;
        }
        else if(emoji.equals("coche")){
            emoji = "\uD83D\uDE97";
            limite=2;
        }
        else if(emoji.equals("percha")){
            emoji = "\uD83E\uDE9D";
            limite=2;
        }
        else if(emoji.equals("barra de metal")){
            emoji = "\uD83D\uDD27";
            limite=2;
        }
        else if(emoji.equals("martillo")){
            emoji = "\uD83D\uDD28";
            limite=2;
        }
        else if (emoji.equals("pala")) {
            emoji = "\uD83E\uDE8F";
            limite=2;
        }
        else if(emoji.equals("taladro")){
            emoji = "\uD83E\uDE9B";
            limite=2;
        }
        else if (emoji.equals("pueblo")) {
            emoji="\uD83C\uDFD8\uFE0F";
            limite=3;
        }

        //Crea uns string que representa el recorrido
        StringBuilder recorrido = new StringBuilder(emoji+"/////////\uD83C\uDFC3");

        //Por cada vuelta del bucle elimina el caracter que esté en limite
        for(int i=0; i<=9;i++) {
            //Esto imprime en la misma linea
            System.out.print("\r" + recorrido);
            //Esto elimina
            recorrido = recorrido.deleteCharAt(limite);
            //Espera 400ms entre cada vuelta del bucle
            sleep(400);
        }
        //Cuando termina el bucle, hace un salto de linea
        System.out.println("");
    }



    //-------------------------------------------------
    //FUNCIONES INVENTARIO
    //-------------------------------------------------

    //Equipa el arma que se indique entre los parentesis
    static void equipar(String nombre) {
        //Pasa el nombre del arma a minusculas
        nombre = nombre.toLowerCase();
        //Pasa por cada columna del array armas
        for (int i = 0; i < armas[0].length; i++) {
            //Si la columna en la que este corresponde con el nombre introducido la obtiene
            if (((String)armas[0][i]).equals(nombre)) {
                //Obtiene el arma en la columna correspondiente
                armas[2][i] = true;
                //Pregunta si se quiere equipar
                imprimir("funcion","¿Quieres equiparla?");
                imprimir("funcion","1. Si");
                imprimir("funcion","2. No");
                scan(1,2);
                //Si quiere equipar
                if(opcion==1){
                    //Recorre la fila de si está equipada
                    for(int j=0; j<armas[3].length;j++){
                        //Si el arma está equipada la desequipa
                        if((boolean)armas[3][j]==true){
                            armas[3][j]=false;
                        }
                    }
                    //Equipa el arma
                    armas[3][i] = true;
                    imprimir("funcion","Has equipado: " + nombre);
                    return;
                }
                else if(opcion==2){
                    imprimir("funcion","Has obtenido: " + nombre);
                }
                //Muestra un mensaje con el arma correspondiente
            }
        }
        //Si no encuentra el arma sale un mensaje de error
        imprimir("funcion","Arma no encontrada: " + nombre);
    }

    //Obtiene el objeto indicado añadiendo la cantidad indicada
    static void obtener(String nombre, int cantidad) {
        //Recorre cada columna del array inventario
        for (int i = 0; i < inventario[0].length; i++) {
            //Si el nombre introducido coincide con un objeto del array la equipa y le suma cierta cantidad
            if (((String) inventario[0][i]).equalsIgnoreCase(nombre)) {
                //Obtiene el objeto
                inventario[1][i] = true;
                //Suma cierta cantidad a la cantidad que ya tienes
                inventario[2][i] = (int)inventario[2][i]+cantidad;
                //Sale un mensaje de aclaración
                imprimir("funcion","Obtienes: " + cantidad +" "+nombre);
                return;
            }
        }
        //Si no se encuentra el objeto sale un mensaje de error
        imprimir("funcion","Objeto no encontrada: " + nombre);
    }

    //Permite coger las armas que no has cogido antes de la mesa
    static void mesa(){
        //Hace un array con la longitud de armasMesa
        int[] opciones = new int[armasMesa[0].length];

        //Imprime las armas de mesa que no se han cogidp
        for(int i=0; i<armasMesa[0].length;i++){
            if((boolean)armasMesa[1][i]==true){
                imprimir("texto", (i+1) + ". " + armasMesa[0][i]);
            }
        }

        //Se crea indice para más adelante
        int indice = 0;
        //Añade a opciones las posiciones/indices de las armas disponibles
        for(int i=0; i<armasMesa[1].length;i++){
            if((boolean)armasMesa[1][i]==true){
                opciones[indice] = i+1;
                indice++;
            }
        }

        //crea un array con la longitud de indice (indice es el numero de armas que se han dejado en la mesa)
        int[] validas = new int[indice];

        //rellena ese indice con las posiciones de las armas disponibles
        for(int i=0; i<indice;i++){
            validas[i] = opciones[i];
        }

        //Pide la opcion para buscar
        opcion = scan_arr(validas);

        //Si la opcion es uno y el arma está en la mesa se recoge
        if(opcion == 1 && (boolean)armasMesa[1][0]==true){
            equipar("martillo");
        }

        //Si la opcion es dos y el arma está en la mesa se recoge
        if(opcion == 2 && (boolean)armasMesa[1][1]==true){
            equipar("pala");
        }

        //Si la opcion es tres y el arma está en la mesa se recoge
        if(opcion == 3 && (boolean)armasMesa[1][2]==true){
            imprimir("texto","Te das cuenta de que el taladro no tenia batería, encuentras una y se la pones.");
            equipar("taladro");
        }
    }



    //-------------------------------------------------
    //FUNCIONES PRUEBAS
    //-------------------------------------------------

    //Funcion que crea un array con numero aleatorios del uno al cinco
    static int[] aleatorio() {
        int[] adivinar = new int[5];
        Random rand = new Random();

        //Por cada posición del array...
        for (int i=0; i< adivinar.length; i++){
            //Elige el número entre uno y cinco y lo mete en el array
            adivinar[i] = rand.nextInt(4)+1;
        }
        //retorna el array
        return adivinar;
    }

    //Función que crea un minijuego de desbloquear una combinación
    static boolean juego(int[] aleatorio){
        Scanner sc = new Scanner(System.in);
        String introducido = "";

        //Array del jugador
        String[] comprobacion = new String [aleatorio.length];

        //Rellena el array de guiones bajos
        for (int i=0; i<aleatorio.length; i++){
            comprobacion[i] = "-";
        }

        //Mientras que los arrays no sean iguales...
        while (!Arrays.toString(comprobacion).equals(Arrays.toString(aleatorio))){
            //Comprueba que se introduzcan cinco caracteres
            do {
                System.out.println("Introduce una combinación de 5 números: ");
                introducido = sc.nextLine();
            }
            while (introducido.length()!=5 );

            //Por cada posición del array...
            for (int i=0; i<aleatorio.length; i++){
                // Compruebas que el número introducido en esa posición es similar al del array solución y si lo es lo pones en el array comprobación
                if (aleatorio[i] == Character.getNumericValue(introducido.toCharArray()[i])){
                    comprobacion[i] = Character.toString(introducido.toCharArray()[i]);
                }
                // Si el numero introducido en una posición es mayor que el número en esa posición del array solución y cambia el caracter de esa posición en el array comprobación por menos
                else if (aleatorio[i] < Character.getNumericValue(introducido.toCharArray()[i])) {
                    comprobacion[i] = "-";
                }
                // Si el numero introducido en una posición es menor que el número en esa posición del array solución y cambia el caracter de esa posición en el array comprobación por menos
                else if (aleatorio[i] > Character.getNumericValue(introducido.toCharArray()[i])) {
                    comprobacion[i] = "+";
                }
            }
            //Imprime el array
            System.out.println(Arrays.toString(comprobacion));
        }
        //Si los arrays son iguales devuelve true
        return true;
    }

    //Función que crea un minijuego del ahorcado
    static boolean ahorcado() {
        //Array con las palabras disponibles
        String palabras[] = {"virus", "comida", "java", "edificios", "superviviente","zombified"};

        //Elige una palabra del array
        Random rand =  new Random();
        int eleccion = rand.nextInt(palabras.length);
        String solucion = palabras[eleccion];

        int contador = 0;
        //Crea el array en el que el jugador pone los caracteres
        char averiguar[] = new char[solucion.length()];
        int longitud = solucion.length();

        //Llena el array del jugador de guiones bajos
        for (int i = 0; i < longitud; i++) {
            averiguar[i] = '_';
        }

        //Empieza el juego del ahorcado
        while (true) {
            //Siempre que haya menos de 10 turnos
            if(contador<10) {
                //Si el array del jugador no es igual al de la solucion...
                if (!Arrays.equals(averiguar, solucion.toCharArray())){
                    //Muestra el array del jugador
                    System.out.println(averiguar);
                    //Pide una letra
                    char letra = scan_char();
                    //Suma un turno
                    contador++;
                    //Comprueba que la letra coincide con un caracter de la solución
                    for (int i = 0; i < solucion.length(); i++) {
                        if (letra == solucion.charAt(i)) {
                            //Sustituye el guion bajo por la letra correspondiente
                            averiguar[i] = letra;
                        }
                    }
                }
                else{
                    //Si son iguales y no supera el limite devuelve true
                    return true;
                }
            }
            else{
                //Si supera el limite devuelve false
                return false;
            }
        }
    }



    //-------------------------------------------------
    //FUNCIONES COMBATE
    //-------------------------------------------------

    //Funcion que obtiene las estadisticas del arma
    static void stats_arma(){
        //Busca en la matriz de armas la ultima arma obtenida
        for(int i=0;i<armas[0].length;i++){
            if((boolean)armas[2][i]==true){
                //Busca el arma equipada
                if((boolean) armas[3][i]==true) {
                    //Coge el daño del arma
                    daño_arma = (int) armas[1][i];
                }
            }
        }
    }

    //Funcion que obtiene las estadisticas de los enemigos dependiendo del tipo del mismo cuando combate
    static void stats_enem(String tipo){
        //Si el enemigo es tipo zombie coge sus estadisticas
        if(tipo.equals("zombie")){
            //Elige un enemigo de este tipo
            indice = rand.nextInt(0, 3);

            //Coge las estadisticas del enemigo seleccionado
            tipo_e = (String) enemigos[0][indice];
            vida_e = (int) enemigos[1][indice];
            daño_e = (int) enemigos[2][indice];
            tiempo_esq = (int) enemigos[3][indice];
        }
        //Si el enemigo es tipo persona coge sus estadisticas
        else if(tipo.equals("persona")){
            tipo_e = (String) enemigos[0][3];
            vida_e = (int) enemigos[1][3];

            daño_e = (int) enemigos[2][3];
            tiempo_esq = (int) enemigos[3][3];
        }
        //Si el enemigo es tipo encapuchado coge sus estadisticas
        else if(tipo.equals("encapuchado")){
            tipo_e = (String) enemigos[0][4];
            vida_e = (int) enemigos[1][4];
            daño_e = (int) enemigos[2][4];
            tiempo_esq = (int) enemigos[3][4];
        }

        //Nota: al haber solo un tipo de enemigo de clase persona y de clase encapuchado no usa indice si no que usa directamente su posición
    }

    //Funcion que cura al jugador sin superar su vida maxima
    static void curar(int cantidad){
        //suma a la vida actual del jugador
        vida_jug += cantidad;
        //Si la vida que tiene tras curar es mayor a 100, la convierte en 100
        if(vida_jug>100){
            vida_jug = 100;
        }
    }

    //Funcion que añade a un compañero
    static void compañero(String nombre){
        //Bucle que recorre la tabla compañeros
        for(int i = 0; i < compañeros[0].length; i++) {
            //Comprobador es la posicion de la primera fila en la que está el bucle
            String comprobador = (String) compañeros[0][i];
            //Comprueba que comprobador coincida con el nombre introduvido y que sea false
            if(comprobador.equalsIgnoreCase(nombre)&&(boolean)compañeros[1][i]==false){
                //Lo vuelve tu compañero
                compañeros[1][i] = true;
                imprimir( "texto",comprobador + " decide ir contigo");
            }
        }
    }


    //Funcion para entrar y terminar el combate
    static void combate(String tipo){
        //Entra en combate y dependiendo de si mueres o no muestra un mensaje distinto
        if(combatir(tipo)){
            imprimir("texto",verde+"Sobrevives, por ahora..."+reset);
        }
        else{
            imprimir("texto",rojo+"Has muerto"+reset);
            System.exit(0);
        }
    }

    //Funcion que ejecuta el combate
    static boolean combatir(String tipo) {
        //Coge las estadisticas del enemigo y del arma
        stats_enem(tipo);
        stats_arma();

            //Ejecuta el bucle siempre que el jugador o el enemigo esten vivos
            while(vida_e >0&&vida_jug>0) {
                //Muestra el menú del combate
                imprimir("funcion","");
                imprimir("funcion",tipo_e+": "+vida_e);
                imprimir("funcion","Vida: "+vida_jug);
                imprimir("funcion","1. Combate cuerpo a cuerpo");
                imprimir("funcion","2. Pistola");
                imprimir("funcion","3. Inventario");

                //Pide la opción
                int opcion = scan(1,2,3);

                //Hace un ataque cuerpo a cuerpo
                if (opcion == 1) {
                    //El daño total es daño base más el 10% del daño del arma
                    vida_e -= 10+daño_arma;
                }
                //Hace un ataque a distancia
                if (opcion == 2) {
                    //Si hay balas ...
                    if ((int)inventario[2][1] > 0) {
                        //Gasta una y hace 30 de daño al enemigo
                        inventario[2][1] = (int)inventario[2][1]-1;
                        vida_e -= 30;
                    }
                    //Si no hay muestra un mensaje y pierdes el turno
                    else {
                        imprimir("funcion","No tienes balas");
                    }
                }
                //Muestra los objetos de combate del inventario
                if (opcion == 3) {
                    //Muestra las balas
                    imprimir("funcion","Balas: " + inventario[2][1]);
                    //Muestra las vendas
                    imprimir("funcion","Vendas: " +  inventario[2][2]);
                    //Pregunta si quieres usar vendas
                    imprimir("funcion","1. Usar vendas");
                    imprimir("funcion","2. Volver");
                    opcion = scan(1,2);
                    //Usas una venda
                    if(opcion == 1) {
                        //Si hay más de una venda...
                        if ((int) inventario[2][2] > 0){
                            //Gasta una y cura 30 de vida
                            inventario[2][2] = (int) inventario[2][2] - 1;
                            curar(30);
                        }
                        //Si no hay...
                        else{
                            //Muestra un mensaje y vuelves al menú de combate pero no pierdes el turno
                            imprimir("funcion","No tienes vendas");
                            continue;
                        }
                    }
                    //Vuelves al menú de combate
                    if(opcion == 2){
                        continue;
                    }
                }
                //Si matas al enemigo devuelve true
                if(vida_e<=0){
                    return true;
                }
                //Muestra los dialogos de combate
                dialogosCombate(tipo);
                //Turno del enemigo
                ia_enem(tipo);
                //Si el enemigo mata al jugador devuelve false
                if(vida_jug<=0){
                    return false;
                }
                //Turno del compañero
                combate_compañero(tipo);
                //Si el compañero mata al enemigo devuelve true
                if(vida_e<=0){
                    return true;
                }
            }
            return false;
    }

    //Funcion que decide y ejecuta el ataque del enemigo según el tipo que tenga el mismo
    static void ia_enem(String tipo){
        //Elige un numero 1-2
        int eleccion = rand.nextInt(1,3);
        //Si el enemigo es zombie...
        if(tipo.equals("zombie")) {
            //Si el numero es 1 hace un mordisco
            if (eleccion == 1) {
                imprimir("funcion","Mordisco");
                //El jugador puede esquivar el combate
                esquivar();
                //Si no responde tarde no sufre daño
                if (tarde == false) {
                    imprimir("funcion","Esquivaste con exito");
                }
                //Si responde tarde sufre 10 de daño más el del enemigo
                else {
                    imprimir("funcion","No esquivaste");
                    vida_jug -= daño_e + 10;
                }
            }
            //Si el numero es 2 hace un arañazo
            if (eleccion == 2) {
                imprimir("funcion","Arañazo");
                //El jugador puede esquivar el ataque
                esquivar();
                //Si no responde tarde no sufre daño
                if (tarde == false) {
                    imprimir("funcion","Esquivaste con exito");
                }
                //Si responde tarde sufre el daño del enemigo
                else {
                    imprimir("funcion","No esquivaste");
                    vida_jug -= daño_e;
                }
            }
        }
        //Si el enemigo es persona o encapuchado
        if(tipo.equals("persona")||tipo.equals("encapuchado")) {
            //Si el numero es 1 hace un disparo pero no puede esquivar
            if (eleccion == 1) {
                imprimir("funcion","Disparo");
                //El jugador pierde 20 de vida
                vida_jug -= 20;
            }
            //Si el numero es 2 hace un golpe
            if (eleccion == 2) {
                imprimir("funcion","Golpe");
                //El jugador puede esquivar el ataque
                esquivar();
                //Si no responde tarde no sufre daño
                if (tarde == false) {
                    imprimir("funcion","Esquivaste con exito");
                }
                //Si responde tarde sufre el daño del enemigo + 10
                else {
                    imprimir("funcion","No esquivaste");
                    vida_jug -= daño_e+10;
                }
            }
        }
    }

    //Funcion que imprime un mensaje dependiendo de la vida del enemigo y su tipo
    static void dialogosCombate(String tipo){
        //Si el tipo del enemigo es zombie...
        if(tipo.equals("zombie")){
            //Si el enemigo tiene menos de 40 de vida imprime un mensaje
            if(vida_e<40){
                imprimir("funcion",morado+"Cerebrooooos"+reset);
            }
        }
        //Si el enemigo es de tipo encapuchado
        if(tipo.equals("encapuchado")){
            //Si el enemigo tiene menos de 70 de vida imprime un mensaje
            if(vida_e<70){
                imprimir("funcion",rojo+"..."+reset);
            }
        }
    };

    //Funcion que ejecuta las acciones del compañero
    static void combate_compañero(String tipo){
        //Recorre la matriz compañeros
        for(int j = 0; j<compañeros[0].length; j++){
            //Si el compañero en el que está es true...
            if((boolean)compañeros[1][j]==true){
                String nombre = (String)compañeros[0][j];
                //Elige un ataque entre 2
                int eleccion = rand.nextInt(1, 3);

                if (eleccion == 1) {
                    //Si la vida del jugador es menor a 50 cura 10 de vida al jugador
                    if (vida_jug < 50) {
                        curar(10);
                        imprimir("texto",nombre + " te cura 10 de vida");
                    }
                    //Si es mayor a 50 hace 5 de daño al enemigo
                    else {
                        vida_e -= 5;
                        imprimir("texto",nombre + " hace 5 de daño a " + tipo);
                    }
                }
                //Hace 5 de daño al enemigo
                else {
                    vida_e -= 10;
                    imprimir("texto",nombre + " hace 10 de daño a " + tipo);
                }
            }
        }
    }

    //Funcion para esquivar
    static void esquivar(){
        //Se asume que el jugador va a llegar tarde
        tarde = true;
        //De momento no se ha decidido resultado
        boolean[] resultadoDecidido = {false};
        //Se hace un timer
        Timer tim = new Timer();

        imprimir("funcion","ESQUIVA ( X )");

        //ESTO SE EJECUTA AL TERMINAR EL TIMER
        //----------------------------------------------------------------------------------------
        //Se inicia la tarea que se cumple al terminar el temporizador
        TimerTask tarea = new TimerTask() {
            public void run() {
                //Si no se ha decidido resultado
                if(!resultadoDecidido[0]) {
                    //Cuenta como responder tarde
                    tarde = true;
                    //Se decide resultado
                    resultadoDecidido[0] = true;
                    //Se cancela el resultado
                    tim.cancel();
                }
            }
        };
        //----------------------------------------------------------------------------------------

        //Se inicia el temporizador con un tiempo maximo igual al tiempo de esquiva del enemigo
        tim.schedule(tarea, tiempo_esq);

        //SE EJECUTA ANTES DE QUE TERMINE EL TIMER
        sc.nextLine();
        //Pide un caracter
        String tecla = sc.nextLine();

        //Si no se ha decidido resultado
        if(!resultadoDecidido[0]) {
            //Si pulsa x
            if (tecla.equals("x")) {
                //No cuenta como pulsar tarde
                tarde = false;
            } else {
                //Cuenta como pulsar tarde
                tarde = true;
            }
            //Se decide resultado
            resultadoDecidido[0] = true;
            //Cancela la tarea del timer
            tarea.cancel();
            //Cancela el temporizador
            tim.cancel();
        }
    }



    //-------------------------------------------------
    //FUNCIONES UBICACIONES
    //-------------------------------------------------
    static void ciudad() {
        cinematica("ciudad");
        //Si es la primera vez que llega...
        if(ciudadLlegar==false) {
            //Activa la flag de llegar a la ciudad e imprime texto
            ciudadLlegar=true;
            imprimir("texto","Tras varias horas de viaje porfín entras en la ciudad sin \nningún percance. En cuanto llegas al centro se te presentan varios sitios a donde ir");
        }
        //Si ya ha llegado a la ciudad
        else if (ciudadLlegar==true){
            //Imprime el texto
            imprimir("texto","Vuelves a la ciudad, ¿por donde quieres ir ahora?");
        }
        //Imprime las distintas opciones y pide un camino por donde ir
        imprimir("texto","1. Distrito comercial");
        imprimir("texto","2. Distrito residencial");
        imprimir("texto","3. Afueras de la ciudad");
        imprimir("texto","4. Distrito financiero");
        opcion = scan(1,2,3,4);

        //Si eliges la opcion1 llama a la funcion tienda
        if (opcion == 1) {
            tienda();
        }
        //Si la opcion elegida es 2 el jugador va al distrito residencial y llama a la funcion encuentro gente
        else if (opcion == 2) {
            imprimir("texto","Te diriges camino al distrito residencial");
            opcion = scan(1);
            cinematica("pueblo");
            imprimir("texto","En cuanto llegas al distrito residencial ves a lo lejos un grupo de gente en una casa del fondo");
            imprimir("texto", "- Tú el del fondo o te acercas o te metemos un tiro entre ceja y ceja.");
            cinematica("casa");
            if (opcion == 1) {
                encuentro_gente();
            }
        }
        //Si la opcion es 3...
        else if (opcion == 3) {
            //Si es la primera vez que llega...
            if(graneroLlegar == false) {
                //Activa la flag para representar que ya ha llegado
                graneroLlegar = true;
                imprimir("texto","Andas por el extrarradio de la ciudad y ves un cartel que indica la ubicación de un granero. No está \nmuy lejos de donde estás por lo que te encaminas en esa dirección");
                cinematica("granero");
                imprimir("texto","Entras al granero, parece que los animales se han escapado.");
                scan(1);
                imprimir("texto","Ves un zombie al fondo, lo unico que tienes cerca es un martillo, una pala \ny un taladro eléctrico, aunque podría no tener batería");
                sleep(1000);
                //Imprime las opciones y pide una de ellas
                imprimir("texto","1. Martillo");
                imprimir("texto","2. Pala");
                imprimir("texto","3. Taladro");
                opcion = scan(1, 2, 3);

                //Si eliges el martillo...
                if (opcion == 1) {
                    cinematica("martillo");
                    //Lo equipas
                    equipar("martillo");
                    //Quita el arma de la mesa
                    armasMesa[1][0] = false;
                    //Si recoges el martillo...
                    if ((boolean) armas[2][2] == true) {
                        combate("zombie");
                        imprimir("texto", "Registras todo el granero pero no hay nada. Habiendo hecho esto, lo unico que queda por hacer es seguir el camino");
                        sleep(1000);
                        bosque();
                        return;
                    }
                }
                //Si eliges la pala
                else if (opcion == 2) {
                    cinematica("pala");
                    //La equipas
                    equipar("pala");
                    //Quita el arma de la mesa
                    armasMesa[1][1] = false;
                    //Si coges la pala...
                    if ((boolean) armas[2][3] == true) {
                        combate("zombie");
                        imprimir("texto", "Registras todo el granero pero no hay nada. Habiendo hecho esto, lo unico que queda por hacer es seguir el camino");
                        sleep(1000);
                        bosque();
                        return;
                    }
                }
                //Si eliges el taladro
                else if (opcion == 3) {
                    cinematica("taladro");
                    //Lo equipas
                    equipar("taladro");
                    //Quita el arma de la mesa
                    armasMesa[1][2] = false;
                    //Si coges el taladro...
                    if ((boolean) armas[2][4] == true) {
                        sleep(1000);
                        //Mueres
                        imprimir("texto",rojo + "Atacas con el taladro pero al intentar taladrarle la cabeza te das cuenta de que no funciona. Despues de un forcejeo mueres");
                        System.exit(0);
                    }
                }
            }
            //Si ya has llegado, imprime un texto y permite de la mesa un arma de las que sobraron antes (funcion mesa)
            if(graneroLlegar == true) {
                imprimir("texto","Vuelves al granero de antes. Todavía están las herramientas que dejaste");
                mesa();
            }
        }
        //El jugador va al edificio
        else if (opcion == 4) {
            edificio();
        }
    }

    static void bosque(){
        cinematica("bosque");
        imprimir("texto","Llegas al bosque. A unos metros hay un coche abandonado y en la distancia se distingue la silueta de un edificio \nmuy alto. También recuerdas que había una base militar en medio del bosque. ¿A donde decides ir?");
        //Imprime las opciones y pide una
        imprimir("texto","1. Coche");
        imprimir("texto","2. Edificio");
        imprimir("texto","3. Militares");
        opcion = scan(1,2,3);
        //Si decide ir al coche...
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
        //Si decide ir al edificio
        else if (opcion == 2) {
            //Si no ha ido a la ciudad muestra este texto
            if(ciudadLlegar==false) {
                imprimir("texto", "Te alejas en dirección al edificio y antes de que te des cuenta llegas a una ciudad");
            }
            //Si ha ido a la ciudad muestra este
            else{
                imprimir("texto","Vas hacia el edificio más alto de la ciudad");
            }
            //El jugador va al edificio
            edificio();
        }
        else if (opcion == 3) {
            cinematica("campamento");
            //Si tiene pala...
            if((boolean)armas[2][3]==true){
                //Si sigue enterrado..
                if(enterrado==true) {
                    //Lo desentierra
                    enterrado=false;
                    imprimir("texto","Encuentras una caja medio enterrada. Usas tu pala para desenterrarla pero al intentar abrirla no puedes.");
                    //Hace un minijuego de desbloquear una combinacion
                    if(juego(aleatorio())){
                        //Obtiene estos objetos
                        obtener("balas", 5);
                        obtener("vendas", 3);
                    }
                }
                //Si ya lo has desenterrado
                else{
                    System.out.println("Vuelves a donde desenterraste esa caja de suministros. Está vacía");
                }
            }
            //Si no tienes pala
            else{
                imprimir("texto","Ves algo medio enterrado pero no tienes una pala.");
            }
            //Te lleva al campamento de los militares
            opcion = scan(1);
            if (opcion == 1) {
                cinematica("campamento");
                militares();
            }
        }
    }

    static void ayuda(){
        cinematica("bosque");
        //Imprime el texto y muestra una opcin
        imprimir("texto","Llevas andando un rato cuando te encuentras el cadaver de un militar junto a un mapa ¿que haces?");
        imprimir("texto","1. Coger el mapa");
        imprimir("texto","2. Dejar el mapa");
        opcion = scan(1,2);
        //Si coge el mapa...
        if (opcion == 1) {
            //Obtiene el mapa
            obtener("mapa",1);
            imprimir("texto","Te agachas a coger el mapa, y al revisarlo aparece marcado un campamento militar. ");
            imprimir("texto","Después de unas horas siguiendo el mapa ves a lo lejos humo. Debe ser el campamento");
            opcion = scan(1);
            //Llega al campamento y muere
            if (opcion == 1) {
                cinematica("campamento");
                imprimir("texto",rojo+"Vas hacia el campamento, pero antes de que te des cuenta te disparan en el pecho. Mueres"+reset);
                return;
            }
        }
        //Si no lo coge
        else if (opcion == 2) {
            imprimir("texto","Dejas el mapa, estaría feo robar las cosas de un muerto.");
            //Va al campamento de los militares
            militares();
        }
    }

    //Función tienda
    static void tienda(){
        //Si nunca has llegado ...
        if(tiendaLlegar==false) {
            //Imprime el texto y muestra una cinematica
            imprimir("texto", "Te pones en marcha dirección al distrito comercial, esperas encontrar algunas provisiones");
            cinematica("tienda");
            //Activa la flag para representar que ya ha llegado una vez
            tiendaLlegar=true;
            imprimir("texto","Llegas al distrito comercial. En cuanto pones un pie dentro de la primera tienda escuchas dos zombies en la habitación de al lado.");
            sleep(500);
            imprimir("texto","Ves dos objetos en una mesa. Parecen una percha y una barra de metal bastante oxidada");
            //Muestra las opciones y pide la opción
            imprimir("texto","¿Que eliges?");
            imprimir("texto","1. Percha");
            imprimir("texto","2. Barra de metal");
            opcion = scan(1, 2);
            //Si coge la percha ...
            if (opcion == 1) {
                //La equipa y entra en combate
                cinematica("percha");
                equipar("percha");
                combate("zombie");
                //Si viene del pueblo vuelve a él
                if (puebloLlegar){
                    pueblo();
                }
                //Si viene de la ciudad vuelve a ella
                else{
                    ciudad();
                }
            }
            //Si coge la barra de metal...
            else if (opcion == 2) {
                //La equipa
                cinematica("barra de metal");
                equipar("barra de metal");
                sleep(500);
                //El jugador muere
                imprimir("texto",rojo + "Cuando intentas pegar el primer golpe con la barra de metal, se parte en dos. Intentas alcanzar algo \n que defenderte pero eres devorado por los zombies" + reset);
                System.exit(0);
            }
        }
        //Si ya ha venido y viene del pueblo...
        else if(tiendaLlegar==true&&puebloLlegar==true){
            //Muestra este texto y vuelve al pueblo
            cinematica("tienda");
            imprimir("texto","Ves los cadaveres de los zombies que mataste antes, te limitas a coger los suministros e irte.");
            pueblo();
        }
        //Si ya ha venido
        else if(tiendaLlegar==true){
            //Muestra el texto y vuelve a la ciudad
            cinematica("tienda");
            imprimir("texto","Ves los cadaveres de los zombies que mataste antes, decides salir a la calle.");
            ciudad();
        }
    }

    static void edificio(){
        cinematica("edificio");
        //Si es la primera vez que llega...
        if(edificioLlegar==false) {
            //Activa el flag para representar que ya ha llegado
            edificioLlegar=true;
            //Si no ha visto al encapuchado muestra este texto
            if (encapuchadoVisto == false) {
                imprimir("texto","Estás a las puertas del edificio más alto de la ciudad, las vistas serán buenas desde la azotea.");
                scan(1);
                imprimir("texto","Te encuentras con un encapuchado mirandote fijamente");
                imprimir("texto","Necesito a gente avispada para un trabajito. Si adivinas en lo que estoy pensado te dejaré unirte a mi equipo");
            }
            //Si ya le ha visto muestra este texto
            else {
                imprimir("texto","El edificio del que te habló aquel encapuchado. Decides subir y hablar con él");
                scan(1);
                imprimir("texto","Te encuentras al encapuchado de antes mirandote fijamente");
                imprimir("texto","- Qué, te ha picado la curiosidad. Mira, estoy reuniendo a un equipo. Si adivinas lo que estoy pensando dejaré que te unas.");
            }

            //Juega al ahorcado y si gana...
            if (ahorcado()) {
                imprimir("texto","- Ja, bien hecho.");
                imprimir("texto","- ¿Aceptas mi propuesta?");
                //Salen estas opciones y pide una de ellas
                imprimir("texto","1. Aceptar");
                imprimir("texto","2. Combatir");
                opcion = scan(1, 2);
                //Si te unes a su grupo...
                if (opcion == 1) {
                    //Se activa la flag saqueador y el jugador va al pueblo
                    imprimir ("texto","Decides unirte a su grupo de saqueadores" + reset);
                    saqueador = true;
                    imprimir("texto",rojo + "");
                    pueblo();
                }
                //Si lo rechazas
                else if (opcion == 2) {
                    //Entras en combate con el encapuchado
                    imprimir("texto","- Tenías potencial, una lástima que tenga que matarte");
                    combate("encapuchado");
                    imprimir("texto","Consigues acabar con el encapuchado y vas hacia la salida. Peor al girarte para ver el cadaver una ultima vez, \n ves que ha desaparecido. Tienes el presentimiento de que no será la última vez que le veas");
                    //Si sobrevives al combate y no vienes de la ciudad imprime esto
                    if(ciudadLlegar==false) {
                        imprimir("texto","Decides salir a la calle y explorar el resto de la ciudad");
                    }
                    //Si vienes de la ciudad imprime esto
                    else{
                        imprimir("texto","Decides salir a la calle");
                    }
                    //Luego vas a la ciudad
                    ciudad();
                }
            }
            //Si falla...
            else {
                //El jugador muere
                imprimir("texto","- Solo acepto a los mejores, y parece que tú no eres uno de ellos.");
                imprimir("texto",rojo + "El encapuchado te mata a sangre fría" + reset);
            }
        }
        //Si ya has llegado...
        else if(edificioLlegar==true){
            //Imprime esto
            imprimir("texto","Decides subir de nuevo para otear el horizonte.");
            imprimir("texto","Pasan un par de horas por lo que decides marcharte antes de que vengan zombies");
            //Vuelve a la ciudad
            ciudad();
        }
    }

    static void militares(){
        //Si no ha ido donde los militares...
        if(militaresLlegar==false) {
            //Muestra este texto
            imprimir("texto","Sigues andando por el bosque y te encuentras un campamento militar, ¿te acercas?");
        }
        //Si ya ha llegado...
        else{
            //Muestra este texto
            imprimir("texto","Vuelves al campamento militar ¿te acercas?");
        }
        //Muestra estas opciones y pide una de ellas
        imprimir("texto","1. Acercarte");
        imprimir("texto","2. Alejarte");
        opcion = scan(1, 2);
        //Si el jugador se acerca...
        if (opcion == 1) {
            //El jugador mueres
            cinematica("campamento");
            imprimir("texto",rojo + "Te acercas a los militares y antes de que te puedas explicar recibes un disparo" + reset);
        }
        //Si el jugador se aleja...
        if (opcion == 2) {

            //Si es la primera vez que llega donde los militares
            if(militaresLlegar==false) {
                imprimir("texto","No te fias y te alejas");
            }
            //Si ya ha llegado donde los militares
            else{
                imprimir("texto","Te alejas sin ser visto");
            }
            //Si no ha visto al encapuchado
            if(encapuchadoVisto==false) {
                imprimir("texto","Escuchas un ruido detras tuya y al girarte ves a un encapuchado.");
                imprimir("texto","- Si estas cansado de andar por ahí, reunete conmigo en la azotea del edificio más alto de la ciudad.");
                encapuchadoVisto=true;
            }
            //Si ha visto al encapuchado
            else{
                imprimir("texto","Ves a alguien a lo lejos, podría ser un zombie, o el encapuchado de antes. Decides apurar el paso");
            }
            //Si no ha llegado donde los militares
            if(militaresLlegar==false) {
                imprimir("texto","Tras una larga caminata te encuentras con una bifurcación, un cartel indica que el camino \n izquierdo lleva a la ciudad mientras que el otro vuelve al inicio del bosque ");
                militaresLlegar=true;
            }
            //Si ya ha llegado donde los militares
            else{
                imprimir("texto","Te vuelves a encontrar con la bifurcación de antes, ¿por donde continuas?");
            }
            //Imprime dos opciones y pide una
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

    static void pueblo(){
        //Si no es saqueador...
        if(saqueador==false) {
            cinematica("pueblo");
            //Si es la primera vez que llega al pueblo...
            if(puebloLlegar==false) {
                puebloLlegar = true;
                imprimir("texto","Después de mucho tiempo de viaje llegais a un pueblo. Bill empieza a hablar al grupo.");
                imprimir("texto", "- Vale, porfin hemos llegado. Tenemos que ver si queda algo de provecho o si hay zombies ¿Por donde empezamos?");
                //Elige una opción
                imprimir("texto","1. Casa de la izquierda");
                imprimir("texto","2. Casa de la derecha");
                opcion = scan(1, 2);
                //No consigue nada, hay un combate y consigue un compañero
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
                }
                //Consigue balas pero pierde al compañero
                else if (opcion == 2) {
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
            //Cuando vuelve de la tienda
            if(puebloLlegar==true){
                imprimir("texto","LLegas al pueblo y dejas los suministros");
                //Imprime dos opciones y pide una
                imprimir("texto","Escuchas disparos y gritos, hay saqueadores");
                combate("persona");
                imprimir("texto","¿A donde vas?");
                imprimir("texto","1. Plaza del pueblo");
                imprimir("texto","2. Entrada del pueblo");
                opcion = scan(1,2);
                //Si elige primero la plaza va directamente a esa parte
                if(opcion == 1){
                    plaza();
                }
                //Si elige la entrada hay un combate y luego va al pueblo
                else if(opcion == 2){
                    combate("persona");
                    imprimir("texto","Conseguis eliminar a los saqueadores de la entrada, pero se escuchan gritos en la plaza");
                    plaza();
                }
            }
        }
        //Si es saqueador...
        else if (saqueador==true){
            imprimir("texto","Los saqueadores y tú observais el pueblo desde lejos. El encapuchado se te acerca");
            imprimir("texto","- Nos ponemos en marcha novato. Son bastantes así que puede que den problemas, no te separes de mi");
            compañero("encapuchado");
            imprimir("texto", "Reventais la puerta principal y os abris paso pegando tiros. Se te acerca uno de los residentes.");
            combate("persona");
            sleep(1000);
            imprimir("texto",rojo+"");
            //Imprime dos opciones y pide una de ellas
            imprimir("texto","Hay una bifurcación, por donde vas");
            imprimir("texto","1. Calle principal");
            imprimir("texto","2. Calle secundaria");
            opcion = scan(1,2);
            //Si elige la calle principal tiene un combate y va a la plaza
            if (opcion == 1){
                imprimir("texto", "Decides ir por la calle principal");
                combate("persona");
                plaza();
            }
            //Si elige la calle secundaria obtiene objetos, tiene un combate
            else if(opcion == 2){
                imprimir("texto", "Decides ir por la calle secundaria. Mientras vas corriendo entre callejones te encuentras una \ncaja abierta con cosas dentro");
                obtener("balas",5);
                obtener("vendas",1);
                imprimir("texto", "Una bala pasa rozando tu cabeza");
                combate("persona");
                plaza();
            }

        }
    }

    static void plaza(){
        //Si no es saqueador tiene un combate contra el encapuchado
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
        //Si es un saqueador tiene un combate contra una persona
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
}
