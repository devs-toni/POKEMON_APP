package CuentaBancaria;

public class MainBanco {
	public static void main(String[] args) {
		CuentaBancaria miCuenta = new CuentaBancaria();
		
		miCuenta.setNumeroCuenta(-5);
		//Titular - al ser una variable publica, cualquiera puede modificarla sin antes pasar el control que el programador anterior considere oportuno (SETTER)
		miCuenta.titular = "34667654547";
		System.out.println(miCuenta.getNumeroCuenta() + " - " + miCuenta.getTitular());
	}

}
