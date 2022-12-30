package myGame.pokemon;

import static myGame.utiles.util.vidaAbsoluta;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import myGame.errores.datoInvalidoException;
import myGame.errores.err;
import myGame.errores.experienciaAlMaximoException;
import myGame.errores.noQuedanMovimientosException;
import myGame.lucha.ataqueSimple;
import myGame.lucha.funcionMoves;
import myGame.lucha.moveJugador;
import myGame.lucha.type;
import myGame.personajes.enemigo;
import myGame.utiles.util;

public class pokemonJugador extends pokemon implements Serializable {

	private static final long serialVersionUID = 3654355783654528018L;
	private int experiencia;
	private ataqueSimple ataque;
	private List<moveJugador> movimientos;

	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////// VARIABLES

	// CONSTRUCTOR POKEMON
	public pokemonJugador(String nombre, int vida, int experiencia, type tipo, ataqueSimple ataque,
			List<moveJugador> movimientos) {
		super(nombre, vida, tipo);
		this.experiencia = experiencia;
		this.ataque = ataque;
		this.movimientos = movimientos;
		super.setConcentracion(100);
		switch (experiencia) {
		case 0:
			super.setNivel(0);
			break;
		case 100:
			super.setNivel(1);
			break;
		case 200:
			super.setNivel(2);
			break;
		case 300:
			super.setNivel(3);
			break;
		case 400:
			super.setNivel(4);
			break;
		case 500:
			super.setNivel(5);
			break;
		case 600:
			super.setNivel(6);
			break;
		case 700:
			super.setNivel(7);
			break;
		case 800:
			super.setNivel(8);
			break;
		case 900:
			super.setNivel(9);
			break;
		case 1000:
			super.setNivel(10);
			break;
		}
	}

	// CONSTRUCTOR VACIO
	public pokemonJugador() {
	}

	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////// CONSTRUCTORES

	public int getExperiencia() {
		return experiencia;
	}

	public void setExperiencia(int experiencia) {
		this.experiencia = experiencia;
	}

	public ataqueSimple getAtaque() {
		return ataque;
	}

	public void setAtaque(ataqueSimple ataque) {
		this.ataque = ataque;
	}

	public List<moveJugador> getMovimientos() {
		return movimientos;
	}

	public void setMovimientos(List<moveJugador> movimientos) {
		this.movimientos = movimientos;
	}

	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////// GETTER
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////// Y
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////// SETTER

	public static pokemonJugador anadirPikachu() {
		return new pokemonJugador("Pikachu", 30, 100, type.ELECTRICO, ataqueSimple.ataqueSimpleElectrico(),
				moveJugador.anadirMovesJugadorElectrico());
	}

	public static pokemonJugador anadirSquirtle() {
		return new pokemonJugador("Squirtle", 30, 100, type.AGUA, ataqueSimple.ataqueSimpleAgua(),
				moveJugador.anadirMovesJugadorAgua());
	}

	public static pokemonJugador anadirBulbasur() {
		return new pokemonJugador("Bulbasur", 30, 100, type.PLANTA, ataqueSimple.ataqueSimplePlanta(),
				moveJugador.anadirMovesJugadorPlanta());
	}

	public static pokemonJugador anadirCharizard() {
		return new pokemonJugador("Charizard", 30, 100, type.FUEGO, ataqueSimple.ataqueSimpleFuego(),
				moveJugador.anadirMovesJugadorFuego());
	}

	public static pokemonJugador anadirTotodile() {
		return new pokemonJugador("Totodile", 30, 100, type.AGUA, ataqueSimple.ataqueSimpleAgua(),
				moveJugador.anadirMovesJugadorAgua());
	}

	public static pokemonJugador anadirCharmander() {
		return new pokemonJugador("Charmander", 30, 100, type.FUEGO, ataqueSimple.ataqueSimpleFuego(),
				moveJugador.anadirMovesJugadorFuego());
	}

	public static pokemonJugador anadirGlaceon() {
		return new pokemonJugador("Glaceon", 30, 100, type.HIELO, ataqueSimple.ataqueSimpleHielo(),
				moveJugador.anadirMovesJugadorHielo());
	}

	public static pokemonJugador anadirSnorlax() {
		return new pokemonJugador("Snorlax", 30, 100, type.NORMAL, ataqueSimple.ataqueSimpleNormal(),
				moveJugador.anadirMovesJugadorNormal());
	}

	public static pokemonJugador anadirEevee() {
		return new pokemonJugador("Eevee", 30, 100, type.NORMAL, ataqueSimple.ataqueSimpleNormal(),
				moveJugador.anadirMovesJugadorNormal());
	}

	public static pokemonJugador anadirRaichu() {
		return new pokemonJugador("Raichu", 30, 100, type.ELECTRICO, ataqueSimple.ataqueSimpleElectrico(),
				moveJugador.anadirMovesJugadorElectrico());
	}

	public static pokemonJugador anadirBlastoise() {
		return new pokemonJugador("Blastoise", 30, 100, type.AGUA, ataqueSimple.ataqueSimpleAgua(),
				moveJugador.anadirMovesJugadorAgua());
	}

	public static pokemonJugador anadirMagneton() {
		return new pokemonJugador("Magneton", 30, 100, type.ELECTRICO, ataqueSimple.ataqueSimpleElectrico(),
				moveJugador.anadirMovesJugadorElectrico());
	}

	public static pokemonJugador anadirSeadra() {
		return new pokemonJugador("Seadra", 30, 100, type.AGUA, ataqueSimple.ataqueSimpleAgua(),
				moveJugador.anadirMovesJugadorAgua());
	}

	public static pokemonJugador anadirMoltres() {
		return new pokemonJugador("Moltres", 30, 100, type.FUEGO, ataqueSimple.ataqueSimpleFuego(),
				moveJugador.anadirMovesJugadorFuego());
	}

	public static pokemonJugador anadirArticuno() {
		return new pokemonJugador("Articuno", 30, 100, type.HIELO, ataqueSimple.ataqueSimpleHielo(),
				moveJugador.anadirMovesJugadorHielo());
	}

	public static pokemonJugador anadirMagmar() {
		return new pokemonJugador("Magmar", 30, 100, type.FUEGO, ataqueSimple.ataqueSimpleFuego(),
				moveJugador.anadirMovesJugadorFuego());
	}

	public static pokemonJugador anadirJynx() {
		return new pokemonJugador("Jynx", 30, 100, type.HIELO, ataqueSimple.ataqueSimpleHielo(),
				moveJugador.anadirMovesJugadorHielo());
	}

	public static pokemonJugador anadirBellsprout() {
		return new pokemonJugador("Bellsprout", 30, 100, type.PLANTA, ataqueSimple.ataqueSimplePlanta(),
				moveJugador.anadirMovesJugadorPlanta());
	}

	public static pokemonJugador anadirExeggutor() {
		return new pokemonJugador("Exeggutor", 30, 100, type.PLANTA, ataqueSimple.ataqueSimplePlanta(),
				moveJugador.anadirMovesJugadorPlanta());
	}

	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////// POKEMON
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////// BUENOS

	public static List<pokemonJugador> anadirPokemonRegaloDoctor() {
		List<pokemonJugador> lista = new ArrayList<>();
		lista.add(anadirPikachu());
		lista.add(anadirSquirtle());
		lista.add(anadirBulbasur());
		return lista;
	}

	public static List<pokemonJugador> anadirPokemonAshVacio() {
		List<pokemonJugador> lista = new ArrayList<>();
		return lista;
	}

	public static List<pokemonJugador> anadirPokemonMistyVacio() {
		List<pokemonJugador> lista = new ArrayList<>();
		return lista;
	}

	public static List<pokemonJugador> anadirPokemonBrockVacio() {
		List<pokemonJugador> lista = new ArrayList<>();
		return lista;
	}

	public static Set<pokemonJugador> anadirPokemonRegaloTorneo() {
		Set<pokemonJugador> lista = new HashSet<>();
		lista.add(anadirArticuno());
		lista.add(anadirBlastoise());
		lista.add(anadirBellsprout());
		lista.add(anadirCharizard());
		lista.add(anadirCharmander());
		lista.add(anadirEevee());
		lista.add(anadirExeggutor());
		lista.add(anadirGlaceon());
		lista.add(anadirJynx());
		lista.add(anadirMagmar());
		lista.add(anadirMagneton());
		lista.add(anadirMoltres());
		lista.add(anadirPikachu());
		lista.add(anadirRaichu());
		lista.add(anadirSeadra());
		lista.add(anadirSquirtle());
		lista.add(anadirSnorlax());
		lista.add(anadirTotodile());
		return lista;
	}

	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////// LISTAS
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////// DE
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////// POKEMON

	/**
	 * Función que evalua la vida del pokemon en cuestion
	 *
	 * @return VERDADERO si el pokemon actual tiene vida
	 */

	public boolean pokemonVivo() {
		return this.vida > 0;
	}

	/**
	 * Método para verificar que no quedan movimientos estratégicos disponibles
	 */

	public boolean sinMovimientos() {
		long filtrado = movimientos.stream().filter(movimientoPlayer -> movimientoPlayer.getAtaquesRestantes() == 0)
				.count();
		return filtrado == movimientos.size();
	}

	/**
	 * Funcion encargada de aumentar la experiencia del pokemon que ha realizado la
	 * batalla, en función de los pokemons que se han derrotado
	 *
	 * @param PokemonEnemigo
	 */

	public void aumentarExperiencia(enemigo PokemonEnemigo) throws experienciaAlMaximoException {
		int exp = 20 * PokemonEnemigo.getPokeballs().getListaPokemonMalo().size();
		int totalExp = experiencia + exp;
		if (totalExp >= 1000) {
			setExperiencia(1000);
			setNivel(10);
			throw new experienciaAlMaximoException(err.EXPERIENCIA_AL_MAXIMO.toString());
		} else
			experiencia = totalExp;

		System.out.println(nombre + " ha ganado " + exp + " puntos de experiencia  -> TOTAL - " + experiencia);
		for (int i = 100; i <= 1000; i += 100) {
			if (experiencia == i) {
				System.out.println(nombre + " ha alcanzado el nivel " + (i / 100));
				nivel = i / 100;
				System.out.println("Ataque  x" + nivel + " / Defensa  x" + nivel + " / AtaqueEspecial  x" + nivel
						+ " / DefensaEspecial  x" + nivel);

				for (moveJugador m : movimientos) {
					if (m.getFuncion() == funcionMoves.Ataque) {
						m.setDolor(m.getDolor() + 2);
					} else if (m.getFuncion() == funcionMoves.Defensa) {
						m.setDolor(m.getDolor() + 3);
					} else if (m.getFuncion() == funcionMoves.AtaqueEspecial) {
						m.setDolor(m.getDolor() + 3);
					} else if (m.getFuncion() == funcionMoves.DefensaEspecial) {
						m.setDolor(m.getDolor() + 2);
					}
				}
			}
		}
	}

	/**
	 * Método empleado para elegir el movimiento de ataque del jugador, dependiendo
	 * del nivel de acceso de cada ataque
	 *
	 * @return - Movimiento elegido - Nulo en caso de volver al menu aterior
	 * @throws noQuedanMovimientosException
	 */

	public moveJugador elegirMovimientoJugador() throws noQuedanMovimientosException {
		int ataque = -1;
		moveJugador movimiento = new moveJugador();
		List<moveJugador> copiaMovimientos = movimientos;
		List<moveJugador> listaMovimientos = movimientos;

		Stream<moveJugador> listaMovimientosNivel;
		if (nivel < 3) {
			listaMovimientosNivel = listaMovimientos.stream().filter(moveJugador -> moveJugador.getNivelAcceso() == 0);
			listaMovimientos = listaMovimientosNivel.collect(Collectors.toList());

		} else if (nivel < 5) {
			listaMovimientosNivel = listaMovimientos.stream().filter(moveJugador -> moveJugador.getNivelAcceso() < 4);
			listaMovimientos = listaMovimientosNivel.collect(Collectors.toList());
		}
		movimientos = listaMovimientos;

		if (!sinMovimientos()) {
			do {
				try {
					System.out.println("0. Atrás.");
					movimientos.forEach(moveJugador -> {
						int n = movimientos.indexOf(moveJugador) + 1;
						System.out.println(n + ". " + moveJugador.getNombre() + " - " + moveJugador.getFuncion().name()
								+ "  " + moveJugador.getAtaquesRestantes() + "/" + moveJugador.getLimiteAtaques());
					});

					ataque = util.introducirNumeroObligatorio("Elige un ataque");

					if (ataque < 0 || ataque > movimientos.size())
						throw new datoInvalidoException(err.DATO_INVALIDO.toString());
					if (ataque == 0)
						break;
					if (movimientos.get(ataque - 1).movimientosVacio())
						throw new noQuedanMovimientosException(err.NO_HAY_MOVIMIENTOS.toString());
					else {
						moveJugador aux = movimientos.get(ataque - 1);
						movimientos.get(ataque - 1)
								.setAtaquesRestantes(movimientos.get(ataque - 1).getAtaquesRestantes() - 1);
						movimientos = copiaMovimientos;
						return aux;
					}
				} catch (noQuedanMovimientosException | datoInvalidoException e) {
					e.printStackTrace();
				}
			} while (true);
		} else {
			throw new noQuedanMovimientosException(err.NO_HAY_MOVIMIENTOS.toString());
		}
		return null;
	}

	/**
	 * Método encargado de atacar dependiento del tipo de ataque empleado, aqui es
	 * donde se ecuentra la lógica de ataque del juego
	 *
	 * @param enemigo
	 * @return - VENENO en caso de un ataque con veneno - ATRAS en caso de volver al
	 *         menu de ataque - NULO en caso de ataque normal
	 */

	public String ataqueJugador(pokemonMaquina enemigo) {
		Random aleatorio = new Random();
		moveJugador move = new moveJugador();

		try {
			move = elegirMovimientoJugador();
		} catch (noQuedanMovimientosException e) {
			e.printStackTrace();
		}

		int danoFinal = 0;
		if (move == null) {
			return "atras";
		}

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		int dano = move.getDolor();

		if (move.getFuncion() == funcionMoves.Ataque) {
			if (concentracion < 90 && concentracion >= 70) {
				dano = (dano * 80) / 100;
				danoFinal = aleatorio.nextInt(dano - ((dano * 40) / 100)) + ((dano * 60) / 100);
			} else if (concentracion < 70 && concentracion >= 50) {
				dano = (dano * 60) / 100;
				danoFinal = aleatorio.nextInt(dano - ((dano * 40) / 100)) + ((dano * 60) / 100);
			} else if (concentracion < 50 && concentracion >= 20) {
				dano = (dano * 40) / 100;
				danoFinal = aleatorio.nextInt(dano - ((dano * 40) / 100)) + ((dano * 60) / 100);
			} else if (concentracion < 20) {
				dano = (dano * 20) / 100;
				danoFinal = aleatorio.nextInt(dano - ((dano * 40) / 100)) + ((dano * 60) / 100);
			} else if (concentracion >= 90) {
				danoFinal = aleatorio.nextInt(dano - ((dano * 40) / 100)) + ((dano * 60) / 100);
			}
			enemigo.setVida(enemigo.getVida() - danoFinal);
			System.out.println(enemigo.getNombre() + "  -" + danoFinal + " -> PH: " + vidaAbsoluta(enemigo));

		} else if (move.getFuncion() == funcionMoves.Defensa) {
			if (enemigo.getConcentracion() > 0) {
				enemigo.setConcentracion(enemigo.getConcentracion() - 40);
				if (enemigo.getConcentracion() < 0)
					enemigo.setConcentracion(0);
			}
			enemigo.setVida(enemigo.getVida() - move.getDolor());
			System.out.println(enemigo.getNombre() + "  -" + move.getDolor() + " -> PH: " + vidaAbsoluta(enemigo));
			System.out.println("Concentrado -> " + util.concentracionAbsoluta(enemigo));

		} else if (move.getFuncion() == funcionMoves.DefensaEspecial) {
			if (enemigo.getConcentracion() > 0) {
				enemigo.setConcentracion(enemigo.getConcentracion() - 60);
				if (enemigo.getConcentracion() <= 0)
					enemigo.setConcentracion(0);
			}
			enemigo.setVida(enemigo.getVida() - move.getDolor());
			System.out.println(enemigo.getNombre() + "  -" + move.getDolor() + " -> PH: " + vidaAbsoluta(enemigo));
			System.out.println("Concentrado -> " + util.concentracionAbsoluta(enemigo));
			return "veneno";

		} else if (move.getFuncion() == funcionMoves.AtaqueEspecial) {
			enemigo.setVida(enemigo.getVida() - move.getDolor());
			System.out.println(enemigo.getNombre() + "  -" + move.getDolor() + " -> PH: " + vidaAbsoluta(enemigo));
		}
		return null;
	}

}
