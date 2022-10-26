package Vehiculos;

import java.util.ArrayList;

public class Avion extends Vehiculo{

	private int ref = 0;
	private String modelo = "concorde";
	private static int vuelo = 0;
	private double autonomia;

	public Avion(int ruedas, double autonomia) {
		super (ruedas);
		this.autonomia = autonomia;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public int getRef() {
		return ref;
	}

	public void setRef(int ref) {
		this.ref = ref;
	}

	public static int getVuelo() {
		return vuelo;
	}

	public static void setVuelo(int vuelo) {
		Avion.vuelo = vuelo;
	}

	public String volar() {
		vuelo++;
		return "Estoy volando!!!";
	}

}
