package Modelo_Conecta_4;

import java.util.Scanner;

public class Modelo_Conecta4_C {
	private int[][] tablero; // Matriz que representa el tablero del juego
	private int columnas; // Número de columnas del tablero
	private int turno; // Número del jugador que tiene el turno actualmente
	private boolean juegoActivo; // Bandera para indicar si el juego está activo o no
	private static Scanner sc; // Objeto Scanner para leer la entrada del usuario
	private int jugadorGanador; // Número del jugador que ha ganado el juego

	public Modelo_Conecta4_C(int filas, int columnas) {
		this.columnas = columnas;
		tablero = new int[filas][columnas]; // Inicialización del tablero con el tamaño especificado
		turno = (int) (Math.random() * 2 + 1); // Elección aleatoria del jugador que comienza
		juegoActivo = true; // El juego se inicia como activo
		sc = new Scanner(System.in); // Inicialización del objeto Scanner para la entrada del usuario
	}

	public static void main(String[] args) {
		System.out.println("BIENVENIDO AL JUEGO CONECTA 4");
		int f = 0, c = 0;

		do {
			f = pideNumero("Ingrese numero de filas del tablero (entre 3 y 10):", 3, 10);
			c = pideNumero("Ingrese numero de columnas del tablero (entre 3 y 10):", 3, 10);

			if (f >= c) {
				System.out.println("El numero de filas debe ser mayor o igual al numero de columnas.");
			}
		} while (f >= c);

		Modelo_Conecta4_C juego = new Modelo_Conecta4_C(f, c);
		juego.iniciarJuego();
		System.out.println("Fin");

		sc.close();
	}

	public void iniciarJuego() {

		do {
			ImprimirTablero();
			cambioTurno();
			int insertFichaColum = pideNumero("Turno del Jugador " + turno + ". Ponga la ficha en una columna:", 1,
					columnas) - 1;
			int fila = insertarFicha(insertFichaColum, turno);
			juegoActivo = !turnoGanador(fila, insertFichaColum, turno);
		} while (juegoActivo);

		ImprimirTablero();

		System.out.println("¡Jugador " + jugadorGanador + " ha ganado!");

	}

	public void ImprimirTablero() {
		System.out.println();
		System.out.print("\t");
		for (int i = 1; i <= columnas; i++) {
			System.out.print(i + " ");
		}
		System.out.print("\n");
		for (int i = 0; i < tablero.length; i++) {
			System.out.print((i + 1) + "\t");
			for (int j = 0; j < tablero[i].length; j++) {
				if (tablero[i][j] == 0) {
					System.out.print("_ "); // Imprime '_' para casillas vacías
				} else if (tablero[i][j] == 1) {
					System.out.print("X "); // Imprime 'X' para fichas del jugador 1
				} else {
					System.out.print("O "); // Imprime 'O' para fichas del jugador 2
				}
			}
			System.out.println();
		}
		System.out.println();
	}

	public void cambioTurno() {
		turno = turno == 1 ? 2 : 1; // Cambia el turno del jugador
	}

	public int insertarFicha(int columna, int turno) {
		int fila = -1;

		if (columna >= 0 && columna < tablero[0].length) {
			for (int i = tablero.length - 1; i >= 0; i--) {
				if (tablero[i][columna] == 0) {
					tablero[i][columna] = turno; // Inserta la ficha en la columna seleccionada
					fila = i;
					break;
				}
			}
		}

		return fila; // Retorna la fila en la que se insertó la ficha
	}

	public boolean turnoGanador(int fila, int columna, int jugador) {
		// Comprueba si el jugador actual ha ganado el juego

		// Horizontalmente
		int contador = 0;
		for (int c = 0; c < columnas; c++) {
			if (tablero[fila][c] == jugador) {
				contador++;
			} else {
				contador = 0;
			}

			if (contador >= 4) {
				jugadorGanador = jugador;
				return true;
			}
		}

		// Verticalmente
		contador = 0;
		for (int f = 0; f < tablero.length; f++) {
			if (tablero[f][columna] == jugador) {
				contador++;
			} else {
				contador = 0;
			}

			if (contador >= 4) {
				jugadorGanador = jugador;
				return true;
			}
		}

		// Diagonal hacia abajo a la derecha
		contador = 0;
		int c = columna - Math.min(fila, columna);
		int f = fila - Math.min(fila, columna);
		while (f < tablero.length && c < columnas) {
			if (tablero[f][c] == jugador) {
				contador++;
			} else {
				contador = 0;
			}

			if (contador >= 4) {
				return true;
			}

			f++;
			c++;
		}

		// Diagonal hacia arriba a la derecha
		contador = 0;
		c = columna - Math.min(tablero.length - 1 - fila, columna);
		f = fila + Math.min(tablero.length - 1 - fila, columna);
		while (f >= 0 && c < columnas) {
			if (tablero[f][c] == jugador) {
				contador++;
			} else {
				contador = 0;
			}

			if (contador >= 4) {
				return true;
			}

			f--;
			c++;
		}

		return false; // Si no hay ganador, retorna false
	}

	public static int pideNumero(String pregunta, int min, int max) {
		Scanner sc = new Scanner(System.in);
		int valor = 0;
		boolean correcto = false;

		do {
			System.out.print(pregunta + " ");
			try {
				valor = Integer.parseInt(sc.nextLine()); // Lee un número entero de la entrada del usuario
				if (valor >= min && valor <= max) {
					correcto = true;
				} else {
					System.err.println("Debe estar entre los valores descritos.");
				}
			} catch (Exception e) {
				System.err.println("Error, vuelva a intentarlo.");
			}
		} while (!correcto);

		sc.close();
		return valor; // Retorna el valor válido ingresado por el usuario
	}
}