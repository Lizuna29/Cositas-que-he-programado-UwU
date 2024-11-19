import java.util.Random;
import java.util.Scanner;

public class Main {
  public static void main(String[] args) throws InterruptedException {
    Scanner teclado = new Scanner(System.in);
    Random generador = new Random();

    boolean finPrograma = false;
    boolean finJuego = false;
    boolean juegoSimple = false;
    boolean juegoMultiple = false;

    int rondasGanadasJugador= 0;
    int rondasGanadasMaquina= 0;
    int opcionesMenu = 0;
    int opcionFinalizar = 0;
    int eleccionJugador = 0;
    int eleccionMaquina = 0;
    int modoDeJuego = 0;
    int numeroRondas = 0;
    int rondasGanadasJugadorTotales= 0;
    int rondasGanadasMaquinaTotales=0;
    double porcentajeVictoria = 0;

    System.out.println("✦✧✦PIEDRA PAPEL TIJERA LAGARTO SPOCK✦✧✦");
    System.out.print("""
      Bienvenido al juego de Piedra Papel Tijeras Lagarto Spock,
      es como el piedra papel o tijeras de toda la vida pero con reglas extra.
            
      !!! POR FAVOR, para elegir usa SOLO numeros !!!
      """);

    while (!finPrograma) {
      System.out.println("""
                
        ▼ Elige que quieres hacer ▼
        1) Comenzar juego
        2) Reglas
        3) Estadisticas
        4) Salir
        """);

      opcionesMenu = teclado.nextInt();

      switch (opcionesMenu) {
        case 1 -> {
          System.out.println("""
                        
            ¿Como quieres jugar?
            1) A una sola ronda
            2) Al mejor de 5
            """);

          juegoSimple = false;
          juegoMultiple = false;

          modoDeJuego = teclado.nextInt();
          switch (modoDeJuego) {
            case 1 -> {
              numeroRondas = 1;
              juegoSimple = true;
            }
            case 2 -> {
              numeroRondas = 5;
              juegoMultiple = true;
            }
          }

          finJuego = false;      //Vuelvo a declarar este booleano para que al acabar no salte el juego directamente

          while (!finJuego) {
            int rondasEmpate = 0;
            int buclesJuego = 0;
            int i = 1;



            for (buclesJuego = numeroRondas; i <= buclesJuego; i++) {

               if (modoDeJuego == 2 && i >= numeroRondas || modoDeJuego == 1) {
                rondasGanadasJugador = 0;
                rondasGanadasMaquina = 0;
              }

              System.out.println("\nElige que quieres sacar:\n1) Piedra \n2) Papel \n3) Tijeras \n4) Lagarto \n5) Spock\n");
              eleccionJugador = teclado.nextInt();
              eleccionMaquina = generador.nextInt(1, 6);

              switch (eleccionJugador) {
                case 1 -> {
                  switch (eleccionMaquina) {
                    case 1 -> {
                      System.out.println("\nHas sacado piedra y la maquina ha sacado piedra\n Habeis empatado.");
                      rondasEmpate++;
                    }
                    case 2 -> {
                      System.out.println("\nHas sacado piedra y la maquina ha sacado papel\n Has perdido");
                      switch (modoDeJuego) {
                        case 1 -> {
                          rondasGanadasMaquina++;
                        }
                        case 2 -> {
                          rondasGanadasMaquina++;
                          porcentajeVictoria = ((double) (rondasGanadasJugador/i))*100;
                          System.out.printf("%.2f",porcentajeVictoria);
                        }
                      }
                    }
                    case 3 -> {
                      System.out.println("\nHas sacado piedra y la maquina ha sacado tijeras\n Has ganado.");
                      switch (modoDeJuego) {
                        case 1 -> {
                          rondasGanadasJugador++;
                        }
                        case 2 -> {
                          rondasGanadasJugador++;
                          porcentajeVictoria = ((double) (rondasGanadasJugador/i))*100;
                          System.out.printf("%.2f",porcentajeVictoria);
                        }
                      }
                    }
                    case 4 -> {
                      System.out.println("\nHas sacado piedra y la maquina ha sacado lagarto\n Has ganado.");
                      switch (modoDeJuego) {
                        case 1 -> {
                          rondasGanadasJugador++;
                        }
                        case 2 -> {
                          rondasGanadasJugador++;
                          porcentajeVictoria = ((double) (rondasGanadasJugador/i)*100);
                          System.out.printf("%.2f",porcentajeVictoria);
                        }
                      }
                    }
                    case 5 -> {
                      System.out.println("\nHas sacado piedra y la maquina ha sacado spock\n Has perdido.");
                      switch (modoDeJuego) {
                        case 1 -> {
                          rondasGanadasMaquina++;
                        }
                        case 2 -> {
                          rondasGanadasMaquina++;
                          porcentajeVictoria = ((double) (rondasGanadasJugador/i))*100;
                          System.out.printf("%.2f",porcentajeVictoria);
                        }
                      }
                    }
                    default -> {
                      System.out.print("Error fatal");
                      finPrograma = true;
                    }
                  }
                }
                case 2 -> {
                  switch (eleccionMaquina) {
                    case 1 -> {
                      System.out.println("\nHas sacado papel y la maquina ha sacado piedra\n Has ganado.");
                      switch (modoDeJuego) {
                        case 1 -> {
                          rondasGanadasJugador++;
                        }
                        case 2 -> {
                          rondasGanadasJugador++;
                        }
                      }
                    }
                    case 2 -> {
                      System.out.println("\nHas sacado papel y la maquina ha sacado papel\n Habeis empatado");
                      rondasEmpate++;
                    }
                    case 3 -> {
                      System.out.println("\nHas sacado papel y la maquina ha sacado tijeras\n Has perdido.");
                      switch (modoDeJuego) {
                        case 1 -> {
                          rondasGanadasMaquina++;
                        }
                        case 2 -> {
                          rondasGanadasMaquina++;
                        }
                      }
                    }
                    case 4 -> {
                      System.out.println("\nHas sacado papel y la maquina ha sacado lagarto\n Has perdido.");
                      switch (modoDeJuego) {
                        case 1 -> {
                          rondasGanadasMaquina++;
                        }
                        case 2 -> {
                          rondasGanadasMaquina++;
                        }
                      }
                    }
                    case 5 -> {
                      System.out.println("\nHas sacado papel y la maquina ha sacado spock\n Has ganado.");
                      switch (modoDeJuego) {
                        case 1 -> {
                          rondasGanadasJugador++;
                        }
                        case 2 -> {
                          rondasGanadasJugador++;
                        }
                      }
                    }
                    default -> {
                      System.out.print("Error fatal");
                      finPrograma = true;
                    }
                  }
                }
                case 3 -> {
                  switch (eleccionMaquina) {
                    case 1 -> {
                      System.out.println("\nHas sacado tijeras y la maquina ha sacado piedra\n Has perdido.");
                      switch (modoDeJuego) {
                        case 1 -> {
                          rondasGanadasMaquina++;
                        }
                        case 2 -> {
                          rondasGanadasMaquina++;
                        }
                      }
                    }
                    case 2 -> {
                      System.out.println("\nHas sacado tijeras y la maquina ha sacado papel\n Has ganado");
                      switch (modoDeJuego) {
                        case 1 -> {
                          rondasGanadasJugador++;
                        }
                        case 2 -> {
                          rondasGanadasJugador++;
                        }
                      }
                    }
                    case 3 -> {
                      System.out.println("\nHas sacado tijeras y la maquina ha sacado tijeras\n Habeis empatado.");
                      rondasEmpate++;
                    }
                    case 4 -> {
                      System.out.println("\nHas sacado tijeras y la maquina ha sacado lagarto\n Has ganado.");
                      switch (modoDeJuego) {
                        case 1 -> {
                          rondasGanadasJugador++;
                        }
                        case 2 -> {
                          rondasGanadasJugador++;
                        }
                      }
                    }
                    case 5 -> {
                      System.out.println("\nHas sacado tijeras y la maquina ha sacado spock\n Has perdido.");
                      switch (modoDeJuego) {
                        case 1 -> {
                          rondasGanadasMaquina++;
                        }
                        case 2 -> {
                          rondasGanadasMaquina++;
                        }
                      }
                    }
                    default -> {
                      System.out.print("Error fatal");
                      finPrograma = true;
                    }
                  }
                }
                case 4 -> {
                  switch (eleccionMaquina) {
                    case 1 -> {
                      System.out.println("\nHas sacado lagarto y la maquina ha sacado piedra\n Habeis perdido.");
                      switch (modoDeJuego) {
                        case 1 -> {
                          rondasGanadasMaquina++;
                        }
                        case 2 -> {
                          rondasGanadasMaquina++;
                        }
                      }
                    }
                    case 2 -> {
                      System.out.println("\nHas sacado lagarto y la maquina ha sacado papel\n Has ganado");
                      switch (modoDeJuego) {
                        case 1 -> {
                          rondasGanadasJugador++;
                        }
                        case 2 -> {
                          rondasGanadasJugador++;
                        }
                      }
                    }
                    case 3 -> {
                      System.out.println("\nHas sacado lagarto y la maquina ha sacado tijeras\n Has perdido.");
                      switch (modoDeJuego) {
                        case 1 -> {
                          rondasGanadasMaquina++;
                        }
                        case 2 -> {
                          rondasGanadasMaquina++;
                        }
                      }
                    }
                    case 4 -> {
                      System.out.println("\nHas sacado lagarto y la maquina ha sacado lagarto\n Habeis empatado");
                      rondasEmpate++;
                    }
                    case 5 -> {
                      System.out.println("\nHas sacado lagarto y la maquina ha sacado spock\n Has ganado.");
                      switch (modoDeJuego) {
                        case 1 -> {
                          rondasGanadasJugador++;
                        }
                        case 2 -> {
                          rondasGanadasJugador++;
                        }
                      }
                    }
                    default -> {
                      System.out.print("Error fatal");
                      finPrograma = true;
                    }
                  }
                }
                case 5 -> {
                  switch (eleccionMaquina) {
                    case 1 -> {
                      System.out.println("\nHas sacado spock y la maquina ha sacado piedra\n Has ganado.");
                      switch (modoDeJuego) {
                        case 1 -> {
                          rondasGanadasJugador++;
                        }
                        case 2 -> {
                          rondasGanadasJugador++;
                        }
                      }
                    }
                    case 2 -> {
                      System.out.println("\nHas sacado spock y la maquina ha sacado papel\n Has perdido");
                      switch (modoDeJuego) {
                        case 1 -> {
                          rondasGanadasMaquina++;
                        }
                        case 2 -> {
                          rondasGanadasMaquina++;
                        }
                      }
                    }
                    case 3 -> {
                      System.out.println("\nHas sacado spock y la maquina ha sacado tijeras\n Has ganado.");
                      switch (modoDeJuego) {
                        case 1 -> {
                          rondasGanadasJugador++;
                        }
                        case 2 -> {
                          rondasGanadasJugador++;
                        }
                      }
                    }
                    case 4 -> {
                      System.out.println("\nHas sacado spock y la maquina ha sacado lagarto\n Has perdido.");
                      switch (modoDeJuego) {
                        case 1 -> {
                          rondasGanadasMaquina++;
                        }
                        case 2 -> {
                          rondasGanadasMaquina++;
                        }
                      }
                    }
                    case 5 -> {
                      System.out.println("\nHas sacado spock y la maquina ha sacado spock\n Habeis empatado.");
                      rondasEmpate++;
                    }
                    default -> {
                      System.out.print("Error fatal");
                      finPrograma = true;
                    }
                  }
                }
                default -> {
                  System.out.println("\n⚠ ⚠ ⚠ Introduce una opción correcta ⚠ ⚠ ⚠");
                }

              }
              rondasGanadasJugadorTotales= rondasGanadasJugadorTotales + rondasGanadasJugador;
              rondasGanadasMaquinaTotales= rondasGanadasMaquinaTotales + rondasGanadasMaquina;

              if (modoDeJuego == 2 && i >= numeroRondas || modoDeJuego == 1) {
                System.out.println("\n¿Quieres seguir jugando?\n1) Si\n2) No\n");
                opcionFinalizar = teclado.nextInt();

                switch (opcionFinalizar) {
                  case 1 -> {
                    eleccionJugador = 0;
                  }
                  case 2 -> {
                    finJuego = true;
                  }
                  default -> {
                    eleccionJugador = 0;
                  }
                }
              } else {
                continue;
              }
            }
          }
        }
        case 2 -> {
          System.out.println("\nEl Piedra Papel Tijeras Lagarto Spock es una variante del Piedra Papel Tijeras de" + " toda la vida pero con elementos añadidos.");
          System.out.print("""
                        
            Digamos que cada elemento interactua de la siguiente manera:
            Piedra aplasta Tijeras.
            Tijeras cortan Papel.
            Papel cubre Piedra.
            Piedra aplasta Lagarto.
            Lagarto envenena Spock.
            Spock destroza Tijeras.
            Tijeras decapitan Lagarto.
            Lagarto se come el Papel.
            Papel desautoriza a Spock.
            Spock vaporiza la Piedra.
                        
            """);
        }
        case 3 -> {
          System.out.println("WIP Aun no implementado.\n");
        }
        case 4 -> {
          System.out.print("""
               _____                                                                            ______              __              \s
              / ___/___  ___     __  ______  __  __   ( )___     _________  ____ _________     / ____/___ _      __/ /_  ____  __  __
              \\__ \\/ _ \\/ _ \\   / / / / __ \\/ / / /  / / __ \\   / ___/ __ \\/ __ `/ ___/ _ \\   / /   / __ \\ | /| / / __ \\/ __ \\/ / / /
             ___/ /  __/  __/  / /_/ / /_/ / /_/ /  / / / / /  (__  ) /_/ / /_/ / /__/  __/  / /___/ /_/ / |/ |/ / /_/ / /_/ / /_/ /\s
            /____/\\___/\\___/   \\__, /\\____/\\__,_/  /_/_/ /_/  /____/ .___/\\__,_/\\___/\\___/   \\____/\\____/|__/|__/_.___/\\____/\\__, / \s
                              /____/                              /_/                                                       /____/
            """);
          finPrograma = true;
        }
      }
    }
  }
}