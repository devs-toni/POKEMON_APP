package EmpresaComercial;

public class Comercial {
	
	private String salud = "BUENA"; 

	public String vender() throws VeoCompetidorException {
		if(salud.equalsIgnoreCase("MALA")) {
			throw new VeoCompetidorException();
		}
		return "He realizado una venta!!!";
	}

	public String getSalud() {
		return salud;
	}

	public void setSalud(String salud) {
		this.salud = salud;
	}
	
	
}
