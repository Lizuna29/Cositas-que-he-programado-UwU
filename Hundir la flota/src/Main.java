import java.util.Scanner;

public class Main {
  static void printTitleScreen() {
    System.out.println("""
        ██╗  ██╗██╗   ██╗███╗   ██╗██████╗ ██╗██████╗     ██╗      █████╗     ███████╗██╗      ██████╗ ████████╗ █████╗\s
        ██║  ██║██║   ██║████╗  ██║██╔══██╗██║██╔══██╗    ██║     ██╔══██╗    ██╔════╝██║     ██╔═══██╗╚══██╔══╝██╔══██╗
        ███████║██║   ██║██╔██╗ ██║██║  ██║██║██████╔╝    ██║     ███████║    █████╗  ██║     ██║   ██║   ██║   ███████║
        ██╔══██║██║   ██║██║╚██╗██║██║  ██║██║██╔══██╗    ██║     ██╔══██║    ██╔══╝  ██║     ██║   ██║   ██║   ██╔══██║
        ██║  ██║╚██████╔╝██║ ╚████║██████╔╝██║██║  ██║    ███████╗██║  ██║    ██║     ███████╗╚██████╔╝   ██║   ██║  ██║
        ╚═╝  ╚═╝ ╚═════╝ ╚═╝  ╚═══╝╚═════╝ ╚═╝╚═╝  ╚═╝    ╚══════╝╚═╝  ╚═╝    ╚═╝     ╚══════╝ ╚═════╝    ╚═╝   ╚═╝  ╚═╝ 
        """);
  }

  static void printMainMenu() {
    System.out.println("""
        
        Elige que quieres hacer.
                
        1) Jugar.
        2) Ver las reglas.
        3) Ver estadisticas.
        4) Salir.
        """);
  }

  static char[][] createEmptyTabletop() {
    char[][] emptyTabletop = new char[10][10];

    for (int i = 0; i < emptyTabletop.length; i++) {
      for (int j = 0; j < emptyTabletop[0].length; j++) {
        emptyTabletop[i][j] = '-';
      }
    }
    return emptyTabletop;
  }

  static void printTabletop(char[][] tabletop) {
    String[] columnNumbers = {"◤  0","  1","  2","  3","  4","  5","  6","  7","  8","  9"};
    String[] rowLetters    = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};

    for (int j = 0; j < tabletop[0].length; j++) {
      System.out.print(columnNumbers[j]);
    }
    System.out.println();
    for (int i = 0; i < tabletop.length; i++) {
      System.out.print(rowLetters[i]);
      for (int j = 0; j < tabletop[0].length; j++) {
        System.out.printf("  %s", tabletop[i][j]);
      }
      System.out.println();
    }
    System.out.println();
  }

  static int[] generateRandomPosition(int type) {  //Genera posiciones aleatorias para cada tipo de barco.
    int[] position = {0,0};
    switch (type) {
      case 0 -> {
        position[0] = (int) (Math.random() * (9 + 1));
        position[1] = (int) (Math.random() * (9 + 1));
      }
      case 1 -> {
        position[0] = (int) (Math.random() * (9 + 1));
        position[1] = (int) (Math.random() * (7 + 1));
      }
      case 2 -> {
        position[1] = (int) (Math.random() * (5 + 1));
        position[0] = (int) (Math.random() * (9 + 1));
      }
      case 3 -> {
        position[1] = (int) (Math.random() * (9 + 1));
        position[0] = (int) (Math.random() * (5 + 1));
      }
    }

    return position;
  }

  static boolean isFree(char[][] tabletop, int[] position) {  //Comprueba si una casilla esta libre.
    boolean aux = false;

    switch (tabletop[position[0]][position[1]]) {
      case 'L', 'B', 'Z', 'P' -> { aux = false; }
      default -> { aux = true; }
    }
    return aux;
  }

  static char[][] placeBoat(char[][] tabletop, int [][] difficultyTable, int difficulty) { //Pone lanchas en el tablero.
    int[] position = new int [2];

    for (int i = 0; i < difficultyTable[difficulty][1];) {
     position = generateRandomPosition(0);
      if (tabletop[position[0]][position[1]] == '-') {
          tabletop[position[0]][position[1]] = 'L';
          i++;
      }
    }
    return tabletop;
  }

  static char[][] placeWarship(char[][] tabletop, int [][] difficultyTable, int difficulty) { //Pone buques en el tablero.
    int[] position = new int [2];

    for (int i = 0; i < difficultyTable[difficulty][2];) {
      position = generateRandomPosition(1);
      if (tabletop[position[0]][position[1]]   == '-' &&
          tabletop[position[0]][position[1]+1] == '-' &&
          tabletop[position[0]][position[1]+2] == '-') {

          tabletop[position[0]][position[1]]   = 'B';
          tabletop[position[0]][position[1]+1] = 'B';
          tabletop[position[0]][position[1]+2] = 'B';
          i++;
      }
    }
    return tabletop;
  }

  static char[][] placeBattleship(char[][] tabletop, int [][] difficultyTable, int difficulty) { //Pone acorazados en el tablero.
    int[] position = new int [2];

    for (int i = 0; i < difficultyTable[difficulty][3];) {
      position = generateRandomPosition(2);
      if (tabletop[position[0]][position[1]]   == '-' &&
          tabletop[position[0]][position[1]+1] == '-' &&
          tabletop[position[0]][position[1]+2] == '-' &&
          tabletop[position[0]][position[1]+3] == '-') {

          tabletop[position[0]][position[1]]   = 'Z';
          tabletop[position[0]][position[1]+1] = 'Z';
          tabletop[position[0]][position[1]+2] = 'Z';
          tabletop[position[0]][position[1]+3] = 'Z';
          i++;
      }
    }
    return tabletop;
  }

  static char[][] placeAircraftCarrier(char[][] tabletop, int [][] difficultyTable, int difficulty) { //Pone portaaviones en el tablero.
    int[] position = new int [2];

    for (int i = 0; i < difficultyTable[difficulty][4];) {
      position = generateRandomPosition(3);
      if (tabletop[position[0]][position[1]]   == '-' &&
          tabletop[position[0]+1][position[1]] == '-' &&
          tabletop[position[0]+2][position[1]] == '-' &&
          tabletop[position[0]+3][position[1]] == '-' &&
          tabletop[position[0]+4][position[1]] == '-') {

          tabletop[position[0]][position[1]]   = 'P';
          tabletop[position[0]+1][position[1]] = 'P';
          tabletop[position[0]+2][position[1]] = 'P';
          tabletop[position[0]+3][position[1]] = 'P';
          tabletop[position[0]+4][position[1]] = 'P';
          i++;
      }
    }
    return tabletop;
  }
  static int checkColumn (String a) {  //Se usa en la funcion getPositionFromPlayer
    int column = -1;

    switch (a) {
      case "0" -> { column = 0; }
      case "1" -> { column = 1; }
      case "2" -> { column = 2; }
      case "3" -> { column = 3; }
      case "4" -> { column = 4; }
      case "5" -> { column = 5; }
      case "6" -> { column = 6; }
      case "7" -> { column = 7; }
      case "8" -> { column = 8; }
      case "9" -> { column = 9; }
      default -> { System.out.println("Por favor, introduce un valor valido.\n"); }
    }

    return column;
  }
  static int checkRow(String a) { //Se usa en la funcion getPositionFromPlayer
    int row = -1;

      switch (a.toUpperCase()) {
        case "A" -> { row = 0; }
        case "B" -> { row = 1; }
        case "C" -> { row = 2; }
        case "D" -> { row = 3; }
        case "E" -> { row = 4; }
        case "F" -> { row = 5; }
        case "G" -> { row = 6; }
        case "H" -> { row = 7; }
        case "I" -> { row = 8; }
        case "J" -> { row = 9; }
        default -> { System.out.println("Por favor, introduce un valor valido.\n"); }
      }

    return row;
  }

  static int[] getPositionFromPlayer() {
    Scanner keyboard = new Scanner(System.in);
    int row = -1;
    int column = -1;

    while (row < 0) {
      System.out.println("¿A fila quieres disparar (letra de la A a la J)?");
      row = checkRow(keyboard.nextLine());
    }

    while (column < 0) {
      System.out.println("¿A que columna quieres disparar (número del 0 al 9)");
      column = checkColumn(keyboard.nextLine());
    }

    int[] position = {row,column};

    return position;
  }

  static int roundCounter (int[][] difficultyTable,int difficulty,  int rounds) {  //Se usa para contar el nuemro de rondas que han pasado
    int roundsLeft = 100;                                                          //o quedan en una partida.
    roundsLeft = ((difficultyTable[difficulty][0])-rounds);

    return roundsLeft;
  }
  static void showInstructions() {
    System.out.println("""
        Buenas, en el juego de hundir la flota tu objetivo es, lo has adivinado, hundir la flota de tu rival.
              
        Normalmente esto seria un juego de 2 jugadores pero en esta versión jugaras tu solo contra la máquina,
        ¿Entonces como se pierde?, hay un número limitado de rondas y mas o menos barcos a hundir segun la dificultad.
              
        Los barcos a destruir son asi:
        - Lancha (L): ocupa una casilla del tablero.
        - Buque (B): ocupa 3 casillas horizontales consecutivas del tablero.
        - Acorazado (Z): ocupa 4 casillas horizontales consecutivas del tablero.
        - Portaaviones (P): ocupa 5 casillas verticales consecutivas del tablero.
              
        Las dificultades serian las siguientes:
        - Muy fácil: El ordenador colocará 10 lanchas (no hay otro tipo de barcos)
        en el tablero y el jugador tendrá 50 intentos para hundirlos todos.
              
        - Fácil: El ordenador colocará 10 barcos (5 lanchas, 3 buques, 1 acorazado
        y 1 portaaviones) en el tablero y el jugador tendrá 50 intentos para
        hundirlos todos.
              
        - Medio: El ordenador colocará 5 barcos (2 lanchas, 1 buque, 1 acorazado
        y 1 portaaviones) en el tablero y el jugador tendrá 30 intentos para
        hundirlos todos.
              
        - Difícil: El ordenador colocará 2 barcos (1 lancha y 1 buque) en el tablero
        y el jugador tendrá 10 intentos para hundirlos todos.
              
        El tablero siempre sera del mismo tamaño independientemente de la dificultad, de 10x10 casillas, enumeradas
        en horizontal del 0 al 1 y en vertical de forma descendiente de la A hasta la J.
        
        !IMPORTANTE! UNA VEZ SE HAYA EMPEZADO UNA PARTIDA SE TIENE QUE ACABAR, NO HAY OTRA MANERA DE SALIR DEL JUEGO.
              
        Mucha suerte y que disfrutes.
        """);
  }

  public static void main(String[] args) {
    Scanner keyboard = new Scanner(System.in);

    boolean shutdown = false;
    int hits = 0;

    int gamesPlayed             = 0;
    int gamesPlayedVeryEasyMode = 0;
    int gamesPlayedEasyMode     = 0;
    int gamesPlayedMediumMode   = 0;
    int gamesPlayedHardMode     = 0;
    int gamesWon = 0;
    int gamesWonVeryEasyMode = 0;
    int gamesWonEasyMode = 0;
    int gamesWonMediumMode = 0;
    int gamesWonHardMode = 0;

    String selectionDifficulty = "";


    printTitleScreen();
    while (!shutdown) {

      printMainMenu();
      String selectionMainMenu = keyboard.nextLine();

      switch (selectionMainMenu) {
        case "1" -> {
          int[][] difficultyTable = {{50, 10,  0,  0,  0, 10},     //0.- Turnos.
                                     {50,  5,  3,  1,  1, 23},     //1.- Lanchas. (1 Casilla)
                                     {30,  2,  1,  1,  1, 14},     //2.- Buques. (3 Casillas Horizontales)
                                     {10,  1,  1,  0,  0,  4}};    //3.- Acorazados. (4 Casilla Horizontales)
                                                                   //4.- Portaaviones. (5 Casillas Verticales)
                                                                   //5- Total de casillas ocupadas.
          System.out.println("""
              
              ¿En que dificultad queires jugar?
              1) Muy facil.
              2) Facil.
              3) Medio.
              4) Dificil.
              5) No quiero jugar ahora mismo.
              """);

          selectionDifficulty = keyboard.nextLine();
          switch (selectionDifficulty) {
            case "1" -> {
              hits = 0;
              char[][] playerTabletop = createEmptyTabletop();
              char[][] computerTabletop = createEmptyTabletop();

              computerTabletop = placeBoat(computerTabletop, difficultyTable, 0);

              printTabletop(playerTabletop);
              for (int j = 0; j < difficultyTable[0][0]; j++) {
                System.out.printf("      Rondas restantes: %d\n\n", (roundCounter(difficultyTable, 0, j)));
                System.out.println("       ¿A donde querés disparar, Coronel?");
                int[] position = getPositionFromPlayer();
                if (!isFree(computerTabletop, position)) {
                  playerTabletop[position[0]][position[1]] = 'X';
                  hits++;
                } else {
                  playerTabletop[position[0]][position[1]] = 'A';
                }
                printTabletop(playerTabletop);

                if ((roundCounter(difficultyTable, 0, j)) == 0) {
                  System.out.println("Has perdido.\n");
                  gamesPlayed++;
                  gamesPlayedVeryEasyMode++;
                  break;
                }
                if (hits == (difficultyTable[0][5])) {
                  System.out.println("Has ganado, enhorabuena.\n");
                  gamesPlayed++;
                  gamesPlayedVeryEasyMode++;
                  gamesWon++;
                  gamesWonVeryEasyMode++;
                  break;
                }
              }
            }
            case "2" -> {
              hits = 0;
              char[][] playerTabletop = createEmptyTabletop();
              char[][] computerTabletop = createEmptyTabletop();

              computerTabletop = placeBoat(computerTabletop, difficultyTable, 1);
              computerTabletop = placeWarship(computerTabletop, difficultyTable, 1);
              computerTabletop = placeBattleship(computerTabletop, difficultyTable, 1);
              computerTabletop = placeAircraftCarrier(computerTabletop, difficultyTable, 1);

              printTabletop(playerTabletop);
              for (int j = 0; j < difficultyTable[1][0]; j++) {
                System.out.printf("      Rondas restantes: %d\n\n", (roundCounter(difficultyTable, 1, j)));
                System.out.println("       ¿A donde querés disparar, Coronel?");
                int[] position = getPositionFromPlayer();
                if (!isFree(computerTabletop, position)) {
                  playerTabletop[position[0]][position[1]] = 'X';
                  hits++;
                } else {
                  playerTabletop[position[0]][position[1]] = 'A';
                }
                printTabletop(playerTabletop);

                if ((roundCounter(difficultyTable, 1, j)) == 0) {
                  System.out.println("Has perdido.\n");
                  gamesPlayed++;
                  gamesPlayedEasyMode++;
                  break;
                }
                if (hits == (difficultyTable[1][5])) {
                  System.out.println("Has ganado, enhorabuena.\n");
                  gamesPlayed++;
                  gamesPlayedEasyMode++;
                  gamesWon++;
                  gamesWonEasyMode++;
                  break;
                }
              }
            }
            case "3" -> {
              hits = 0;
              char[][] playerTabletop = createEmptyTabletop();
              char[][] computerTabletop = createEmptyTabletop();

              computerTabletop = placeBoat(computerTabletop, difficultyTable, 2);
              computerTabletop = placeWarship(computerTabletop, difficultyTable, 2);
              computerTabletop = placeBattleship(computerTabletop, difficultyTable, 2);
              computerTabletop = placeAircraftCarrier(computerTabletop, difficultyTable, 2);

              printTabletop(playerTabletop);
              for (int j = 0; j < difficultyTable[2][0]; j++) {
                System.out.printf("      Rondas restantes: %d\n\n", (roundCounter(difficultyTable, 2, j)));
                System.out.println("       ¿A donde querés disparar, Coronel?");
                int[] position = getPositionFromPlayer();
                if (!isFree(computerTabletop, position)) {
                  playerTabletop[position[0]][position[1]] = 'X';
                  hits++;
                } else {
                  playerTabletop[position[0]][position[1]] = 'A';
                }
                printTabletop(playerTabletop);

                if ((roundCounter(difficultyTable, 2, j)) == 0) {
                  System.out.println("Has perdido.\n");
                  gamesPlayed++;
                  gamesPlayedMediumMode++;
                  break;
                }
                if (hits == (difficultyTable[2][5])) {
                  System.out.println("Has ganado, enhorabuena.\n");
                  gamesPlayed++;
                  gamesPlayedMediumMode++;
                  gamesWon++;
                  gamesWonMediumMode++;
                  break;
                }
              }
            }
            case "4" -> {
              hits = 0;
              char[][] playerTabletop = createEmptyTabletop();
              char[][] computerTabletop = createEmptyTabletop();

              computerTabletop = placeBoat(computerTabletop, difficultyTable, 3);
              computerTabletop = placeWarship(computerTabletop, difficultyTable, 3);

              printTabletop(playerTabletop);
              for (int j = 0; j < difficultyTable[3][0]; j++) {
                System.out.printf("      Rondas restantes: %d\n\n", (roundCounter(difficultyTable, 3, j)));
                System.out.println("       ¿A donde querés disparar, Coronel?");
                int[] position = getPositionFromPlayer();
                if (!isFree(computerTabletop, position)) {
                  playerTabletop[position[0]][position[1]] = 'X';
                  hits++;
                } else {
                  playerTabletop[position[0]][position[1]] = 'A';
                }
                printTabletop(playerTabletop);

                if ((roundCounter(difficultyTable, 3, j)) == 0) {
                  System.out.println("Has perdido.\n");
                  gamesPlayed++;
                  gamesPlayedHardMode++;
                  break;
                }
                if (hits == (difficultyTable[3][5])) {
                  System.out.println("Has ganado, enhorabuena.\n");
                  gamesPlayed++;
                  gamesPlayedHardMode++;
                  gamesWon++;
                  gamesWonHardMode++;
                  break;
                }
              }
            }
          }
        }
        case "2" -> {
          showInstructions();
        }

        case "3" -> {
          System.out.println("Número de partidas totales jugadas: "       + gamesPlayed);
          System.out.println("Número de partidas jugadas en muy fácil: "  + gamesPlayedVeryEasyMode);
          System.out.println("Número de partidas jugadas en fácil: "      + gamesPlayedEasyMode);
          System.out.println("Número de partidas jugadas en medio: "      + gamesPlayedMediumMode);
          System.out.println("Número de partidas jugadas en difícil: "    + gamesPlayedHardMode);
          System.out.println("Número de partidas totales ganadas: "       + gamesWon);
          System.out.println("Número de partidas ganadas en muy fácil: "  + gamesWonVeryEasyMode);
          System.out.println("Número de partidas ganadas en fácil: "      + gamesWonEasyMode);
          System.out.println("Número de partidas ganadas en medio: "      + gamesWonMediumMode);
          System.out.println("Número de partidas ganadas en difícil: "    + gamesWonHardMode);
        }
        case "4" -> {
          System.out.println("Adios");
          shutdown = true;
        }
        default -> { System.out.println("Por el amor de dios, pon una opcion valida."); }
      }
    }
  }
}