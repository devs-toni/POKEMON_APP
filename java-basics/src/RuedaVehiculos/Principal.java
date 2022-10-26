package RuedaVehiculos;

public class Principal {

	
	public static void main(String[] args) {
		Dunlop d = new Dunlop();
		Hankook h = new Hankook();
		
		System.out.println(d.rodar());
		System.out.println(h.rodar());
	}
}
