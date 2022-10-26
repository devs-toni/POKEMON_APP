package CuentaBancaria;

public class CuentaBancaria {

	private int numeroCuenta;
	public String titular = "titular";

	public int getNumeroCuenta() {
		return numeroCuenta;
	}

	//Protección del Setter
	public void setNumeroCuenta(int numeroCuenta) {
		if (numeroCuenta > 0 && numeroCuenta < 100) {
			this.numeroCuenta = numeroCuenta;
		}
	}

	public String getTitular() {
		return titular;
	}
}
