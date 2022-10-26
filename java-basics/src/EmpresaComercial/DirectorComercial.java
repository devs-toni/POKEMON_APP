package EmpresaComercial;

public class DirectorComercial {
	
	public void gestionComerciales() throws NosEstanQuitandoElPanException{
		Comercial comercial1 = new Comercial();
		comercial1.setSalud("MALA");
		
		
		try {
			comercial1.vender();
		}catch (VeoCompetidorException e) {
			throw new NosEstanQuitandoElPanException(e);
		}
	}

}
