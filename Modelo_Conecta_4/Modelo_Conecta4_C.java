package Modelo_Conecta_4;

import java.util.Scanner;

public class Modelo_Conecta4_C {
	Scanner sc = new Scanner(System.in);
	
	private int[][] tablero;
	private int columnas;
	private int turno;
	private boolean juegoActivo;

	public Modelo_Conecta4_C(int filas, int columnas) {
		// TODO Auto-generated constructor stub
		this.columnas = columnas;
		tablero = new int[filas][columnas];
		turno = (int) (Math.random() * (2 - 1 + 1) + 1);
		juegoActivo = true;

	}
	
	public static void main(String[] args) {
		System.out.println("BIENVENIDO AL JUEGO CONECTA 4");
		int f = 0, c = 0;
		
		do {
			 f = pideNumero("Ingrese numero de filas del tablero (entre 3 y 10)", 3, 10);
			 c = pideNumero("Ingrese numero de columnas del tablero (entre 3 y 10)", 3, 10);
			
		}while(f >= c);
		
		Modelo_Conecta4_C juego = new Modelo_Conecta4_C(f, c);
		juego.iniciarJuego();
		System.out.println("Fin");
	}
	
	

	public void iniciarJuego() {
		int inserFichaColum = 0;
		
		do {
			ImprimirTablero();
			cambioTurno();
			insertFichaColum = pideNumero("Ponga la ficha en una columna: ", 1 ,columnas);
			
			pideNumero(String insertFichaColum, int min, int max));
			insertarFicha(int columnas, int turno);
			turnoGanador(int[][] matriz, int ff, int fc, int jugador);	

		} while (false);
	}

	public void ImprimirTablero() {
		System.out.print("\t");
		for(int i = 1; i <= tablero.length; i++) {
			System.out.print(i + " ");
		}
		System.out.print("\n");
		for (int i = 0; i < tablero.length; i++) {
			System.out.print((i+1) + "\t");
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
		turno =  turno == 1? 1:2;		
	}

	public int insertarFicha(int columnas, int turno) {
		
		int fila = -1;
		
		if(columnas > 0 && columnas < tablero[0].length) 
			if(tablero[0][columnas] == 0) {
				
			for(int i=tablero.length-1; i>=0; i--) {
				if(tablero[i][columnas]==0) {
					tablero[i][columnas]=turno;
					fila=i;
				}
			}
		}
		return fila;
	}

	public static boolean turnoGanador(int[][] matriz, int ff, int fc, int jugador) {
		// horizontalmente
		int f = ff;
		int c = fc;
		int contador = 1;
		
		/*--------------
		for (int c = fc + 1; c < matriz[0].length && matriz[f][c] == jugador; c++) {
			contador++;
		}
		for (int c = fc - 1; c >= 0 && matriz[f][c] == jugador; c-- ) {
			contador++;
		}
		--------------*/
		// a la derecha
		while(c < matriz[0].length - 1 && matriz[f][c + 1] == jugador) {
			c++;
			contador++;
		}
		c = fc;
		// a la izquierda
		while(c > 0 && matriz[f][c - 1] == jugador) {
			c--;
			contador++;
		}
		if (contador >= 4) {
			return true;
		}
		
		// verticalmente hacia abajo
		contador = 1;
		c = fc;
		
		while(f < matriz.length - 1 && matriz[f+1][c] == jugador) {
			contador++;
			f++;
		}
		if (contador >= 4) {
			return true;
		}
		
		// COMPROBACIÓN DE LA PRIMERA DIAGONAL
		contador = 1;
		f = ff;
		c = fc;
		
		//hacia arriba a la izquierda
		while (f > 0 && c > 0 && matriz[f-1][c-1] == jugador) {
			contador++;
			f--;
			c--;
		}
		//hacia abajo a la derecha
		f = ff;
		c = fc;
		while (f < matriz.length - 1 && c < matriz[0].length && matriz[f+1][c+1] == jugador) {
			contador++;
			f++;
			c++;
		}
		if (contador >= 4) {
			return true;
		}
		
		// COMPROBACIÓN DE LA SEGUNDA DIAGONAL
		contador = 1;
		f = ff;
		c = fc;
		// hacia arriba a la derecha
		while (f > 0 && c < matriz[0].length && matriz[f-1][c+1] == jugador) {
			contador++;
			f--;
			c++;
		}
		//hacia abajo a la izquierda
		f = ff;
		c = fc;
		while(f < matriz.length - 1 && c > 0 && matriz[f+1][c-1] == jugador) {
			contador++;
			f++;
			c--;
		}
		if (contador >= 4) {
			return true;
		}
		
		return false;
		
	}
	
	public static int pideNumero(String pregunta, int min, int max) {
		Scanner sc = new Scanner(System.in);
		System.out.println(pregunta);
		int valor = 0;
		boolean correcto = false;
		
		do {
			try {
				valor = Integer.parseInt(sc.nextLine());
				if (valor >= min && valor <= max) {
					correcto = true;
				} else {
					System.err.println("debe estar entre los valores descritos");
				}
			} catch(Exception e) {
				System.err.println("Error, vuelva a intentarlo.");
			}
			
		} while(correcto == false);
		return valor;
	}

}
