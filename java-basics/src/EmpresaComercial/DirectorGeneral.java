package EmpresaComercial;

public class DirectorGeneral {

	public void dirigir() {
	DirectorComercial directorComercial = new DirectorComercial();
	try {
		directorComercial.gestionComerciales();
	} catch (NosEstanQuitandoElPanException e) {
		e.printStackTrace();
	}
	
	}
}
