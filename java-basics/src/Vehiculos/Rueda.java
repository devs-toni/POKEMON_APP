package Vehiculos;

public class Rueda {
	
	private int tamanio;
	private String modelo;


	public int getTamanio() {
		return tamanio;
	}


	public void setTamanio(int tamanio) {
		this.tamanio = tamanio;
	}


	public String getModelo() {
		return modelo;
	}


	public void setModelo(String modelo) {
		this.modelo = modelo;
	}


	@Override
	public String toString() {
		return "Rueda [tamanio=" + tamanio + ", modelo=" + modelo + "]";
	}
	
	
	
	
	

}
