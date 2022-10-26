package RuedaVehiculos;

public interface Rueda {
	
	String rodar();
	void reventar();
	void inflar(int aire);
	void desinflar(int aire);
	void cambiar(Rueda rueda);

}
