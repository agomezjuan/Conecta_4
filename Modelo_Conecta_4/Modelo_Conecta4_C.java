package Modelo_Conecta_4;

import java.util.Scanner;

public class Modelo_Conecta4_C {
	private int[][] tablero;
	private int columnas;
	private int turno;
	private boolean juegoActivo;
	private Scanner sc;

	public Modelo_Conecta4_C(int filas, int columnas) {
		this.columnas = columnas;
		tablero = new int[filas][columnas];
		turno = (int) (Math.random() * 2 + 1);
		juegoActivo = true;
		sc = new Scanner(System.in);
	}

	public static void main(String[] args) {
		System.out.println("BIENVENIDO AL JUEGO CONECTA 4");
		int f = 0, c = 0;

		do {
			f = pideNumero("Ingrese numero de filas del tablero (entre 3 y 10)", 3, 10);
			c = pideNumero("Ingrese numero de columnas del tablero (entre 3 y 10)", 3, 10);
		} while (f >= c);

		Modelo_Conecta4_C juego = new Modelo_Conecta4_C(f, c);
		juego.iniciarJuego();
		System.out.println("Fin");
	}

	public void iniciarJuego() {
		do {
			ImprimirTablero();
			cambioTurno();
			int insertFichaColum = pideNumero("Ponga la ficha en una columna: ", 1, columnas) - 1;
			insertarFicha(insertFichaColum, turno);
			juegoActivo = turnoGanador(insertFichaColum, turno);
		} while (juegoActivo);
	}

	public void ImprimirTablero() {
		System.out.print("\t");
		for (int i = 1; i <= columnas; i++) {
			System.out.print(i + " ");
		}
		System.out.print("\n");
		for (int i = 0; i < tablero.length; i++) {
			System.out.print((i + 1) + "\t");
			for (int j = 0; j < tablero[i].length; j++) {
				if (tablero[i][j] == 0) {
					System.out.print("_ ");
				} else if (tablero[i][j] == 1) {
					System.out.print("X ");
				} else {
					System.out.print("O ");
				}
			}
			System.out.println();
		}
	}

	public void cambioTurno() {
		turno = turno == 1 ? 2 : 1;
	}

	public int insertarFicha(int columna, int turno) {
		int fila = -1;

		if (columna >= 0 && columna < tablero[0].length) {
			if (tablero[0][columna] == 0) {
				for (int i = tablero.length - 1; i >= 0; i--) {
					if (tablero[i][columna] == 0) {
						tablero[i][columna] = turno;
						fila = i;
						break;
					}
				}
			}
		}

		return fila;
	}

	public boolean turnoGanador(int ff, int jugador) {
		int f = ff;
		int c = 0;
		int contador = 0;

		// Horizontalmente
		for (c = 0; c < columnas; c++) {
			if (tablero[f][c] == jugador) {
				contador++;
			} else {
				contador = 0;
			}

			if (contador >= 4) {
				return true;
			}
		}

		// Verticalmente
		contador = 0;
		for (c = 0; c < columnas; c++) {
			if (tablero[c][f] == jugador) {
				contador++;
			} else {
				contador = 0;
			}

			if (contador >= 4) {
				return true;
			}
		}

		// Diagonal hacia abajo a la derecha
		contador = 0;
		for (c = 0; c < columnas; c++) {
			if (f + c < tablero.length && tablero[f + c][c] == jugador) {
				contador++;
			} else {
				contador = 0;
			}

			if (contador >= 4) {
				return true;
			}
		}

		// Diagonal hacia arriba a la derecha
		contador = 0;
		for (c = 0; c < columnas; c++) {
			if (f - c >= 0 && tablero[f - c][c] == jugador) {
				contador++;
			} else {
				contador = 0;
			}

			if (contador >= 4) {
				return true;
			}
		}

		return false;
	}

	public static int pideNumero(String pregunta, int min, int max) {
		Scanner sc = new Scanner(System.in);
		int valor = 0;
		boolean correcto = false;

		do {
			System.out.println(pregunta);
			try {
				valor = Integer.parseInt(sc.nextLine());
				if (valor >= min && valor <= max) {
					correcto = true;
				} else {
					System.err.println("Debe estar entre los valores descritos.");
				}
			} catch (Exception e) {
				System.err.println("Error, vuelva a intentarlo.");
			}
		} while (!correcto);

		return valor;
	}
}