package Vehiculos;

import java.util.ArrayList;

public class Coche extends Vehiculo{
		
	private boolean ABS;
	private ArrayList<Rueda> ruedas;

	public Coche (int numRuedas, boolean ABS) {
		super(numRuedas);
		this.ABS = ABS;
		ruedas = new ArrayList();
		for (int i = 0; i < numRuedas ; i++) {
			ruedas.add(new Rueda());
		}
	}
	
}
