package AdivinaNumero;

import java.util.Random;
import java.util.Scanner;

public class AdivinarNumero {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		Random r = new Random();
		boolean correcto = false;

		while (!correcto) {
			System.out.println("Introduce un numero entre 1 y 10, aver si lo adivinas!!!");
			int valor = r.nextInt(9)+1;
			System.out.println(valor);
			if (in.nextInt() == valor) {
				System.out.println("Enhorabuena!!!");
				correcto = true;
			} else {
				System.out.println("No has acertado, vuelve a intentarlo...");
			}
		}
	}
}
