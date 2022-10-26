package Vehiculos;

public class Principal {

	public static void main(String[] args) {
		Avion avion1 = new Avion(4, 6000);
		Avion avion2 = new Avion(5, 10000);
		avion1.setRef(1);
		avion2.setRef(2);
		avion2.setModelo("Lockheed SR-71");
		System.out.println(avion1.getRef() + " - " + avion1.getModelo() + " - " + avion1.volar() + " - Mi vuelo es el numero - " + avion1.getVuelo());
		System.out.println(avion2.getRef() + " - " + avion2.getModelo() + " - " + avion2.volar() + " - Mi vuelo es el numero - " + avion2.getVuelo());
		
		Avion avion3 = new Avion(3,150000);
		
		//El vuelo del avión 3 no será 0 como viene predeterminado, porque se trata de un valor estático, 
		//mientras que la referencia si que será el valor por defecto
		System.out.println("Numero de vuelo del avión" + avion3.getVuelo());
		System.out.println("Referencia del avión" + avion3.getRef());
		
		System.out.println("Avion 1 - " + avion1.getNroRuedas());
		System.out.println("Avion 2 - " + avion2.getNroRuedas());
		System.out.println("Avion 3 - " + avion3.getNroRuedas());
	}
}
