package Encapsulamiento;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Principal {
	public static void main(String[] args) {
		InputStreamReader input = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(input);
		
		try {
			System.out.println("Introduce un texto");
			System.out.println("Podemos leer a lo bestia - " + br.readLine());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
