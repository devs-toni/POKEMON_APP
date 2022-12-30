package myGame.utiles;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Properties;
import java.util.Scanner;

import myGame.game;
import myGame.hisoriaPrincipal;
import myGame.errores.datoInvalidoException;
import myGame.errores.err;
import myGame.errores.noHayEspacioException;
import myGame.items.objetosTienda;
import myGame.items.tiendaPokemon;
import myGame.personajes.jugador;
import myGame.pokemon.pokemon;

public class util {
	public static final String FILE_PATH = System.getProperty("user.home") + File.separator + "savegames"
			+ File.separator;
	private static Scanner scan = new Scanner(System.in);

	/**
	 * Carga un objeto del disco
	 *
	 * @param path
	 * @return
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static Object loadFile(String path) throws IOException, ClassNotFoundException {
		ObjectInputStream load = new ObjectInputStream(new FileInputStream(path));
		Object loadObject = load.readObject();
		load.close();

		return loadObject;
	}

	/**
	 * Guarda un objeto a disco
	 *
	 * @param object
	 * @param path
	 * @throws IOException
	 */
	public static void saveData(Object object, String path) throws IOException {
		ObjectOutputStream save = new ObjectOutputStream(new FileOutputStream(path));
		save.writeObject(object);
		save.close();
	}

	public static int concentracionAbsoluta(pokemon p) {
		if (p.getConcentracion() > 100)
			return 100;
		else
			return p.getConcentracion();
	}

	public static void nuevaPartida(game game) throws noHayEspacioException {
		jugador j = game.elegirPersonaje();
		game.setUser(j);
		game.setHistoria(new hisoriaPrincipal());
		game.setTienda(new tiendaPokemon(objetosTienda.POCION, objetosTienda.FRUTA, objetosTienda.ANTIDOTO,
				objetosTienda.POKEBALL));
		game.getHistoria().pokemonRegaloDoctor(j);
	}

	public static void cargaPartida(game game, jugador j, boolean tienesPokemon) throws noHayEspacioException {
		game.setUser(j);
		game.setHistoria(new hisoriaPrincipal());
		game.setTienda(new tiendaPokemon(objetosTienda.POCION, objetosTienda.FRUTA, objetosTienda.ANTIDOTO,
				objetosTienda.POKEBALL));
		if (!tienesPokemon)
			game.getHistoria().pokemonRegaloDoctor(j);
	}

	public static boolean cargar(game myGame, Properties propiedad) {
		int menuCarga = -1;
		boolean tienesPokemon = false;

		try {
			propiedad.load(new FileInputStream(FILE_PATH + "pokemon.savegame"));
		} catch (IOException e) {
			try {
				propiedad.setProperty("pokemon", "");
				propiedad.store(new FileOutputStream(FILE_PATH + "pokemon.savegame"), "Guardado de partida");
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}

		do {
			try {
				if (!propiedad.getProperty("pokemon").isEmpty()) {
					System.out.println("1. Continuar");
					System.out.println("2. Nueva Partida");
					menuCarga = introducirNumeroObligatorio("");
					if (menuCarga < 0 || menuCarga > 2)
						throw new datoInvalidoException(err.DATO_INVALIDO.toString());
					else if (menuCarga == 1) {
						try {
							jugador j = (jugador) util.loadFile(FILE_PATH + "pokemon.player");
							tienesPokemon = (boolean) util.loadFile(FILE_PATH + "pokemon.doctor");
							util.cargaPartida(myGame, j, tienesPokemon);
							return tienesPokemon;
						} catch (IOException | ClassNotFoundException e) {
							e.printStackTrace();
						}
					} else if (menuCarga == 2) {
						util.nuevaPartida(myGame);
						return true;
					}

				} else {
					System.out.println("1. Nueva Partida");
					menuCarga = introducirNumeroObligatorio("Que quieres hacer?");
					if (menuCarga != 1)
						throw new datoInvalidoException(err.DATO_INVALIDO.toString());
					else {
						util.nuevaPartida(myGame);
						return true;
					}
				}
			} catch (datoInvalidoException | noHayEspacioException e) {
				e.printStackTrace();
			}
		} while (true);

	}

	public static int vidaAbsoluta(pokemon p) {
		if (p.getVida() >= 30) {
			return 30;
		} else if (p.getVida() <= 0) {
			return 0;
		} else
			return p.getVida();
	}

	public static int menuStart() {
		System.out.println("0. Atrás");
		System.out.println("1. Pokemon.");
		System.out.println("2. Items");
		System.out.println("3. Smartphone");
		return introducirNumeroObligatorio("Que quieres ver?");
	}

	public static int menuGuardar() {
		System.out.println("0. Volver a la partida");
		System.out.println("1. Salir sin guardar");
		System.out.println("2. Guardar");
		return introducirNumeroObligatorio("Que quieres hacer?");
	}

	public static int menuJuego(boolean inicio) {
		if (inicio)
			System.out.println("\nQue nueva aventura quieres emprender?");
		System.out.println("1. Encontrarte con un pokemon salvaje");
		System.out.println("2. Encontrarte con el Team Rocket en un duro encuentro");
		System.out.println("3. Competir en un torneo contra tus amigos");
		System.out.println("4. Competir en un torneo contra otros entrenadores");
		System.out.println("5. Eliminar Pokemon");
		System.out.println("6. Añadir medicina encontrada en el camino");
		System.out.println("7. Añadir fruta encontrada en un arbol");
		System.out.println("8. Añadir antidoto regalado por un doctor");
		System.out.println("9. Añadir pokeball regalada por un conocido");
		System.out.println("10. Entrar en la tienda");
		System.out.println("11. Acceder al menu SELECT");
		System.out.println("12. Salir del juego");

		return introducirNumeroObligatorio("Que quieres hacer?");
	}

	public static boolean valorCero(objetosTienda o, String s) {
		if (o.getCantidad() == 0) {
			System.out.println(s);
			return true;
		} else
			return false;

	}

	public static int introducirNumeroObligatorio(String message) {
		String text = null;
		do {
			text = inputText(message);
		} while (!(esNumero(text)));

		return Integer.parseInt(text);
	}

	public static String introducirTextoObligatorio(String message) {
		String text = null;
		do {
			System.out.println(message);
			text = scan.nextLine();

		} while (text.equals(""));

		return text;
	}

	private static boolean esNumero(String text) {
		return text.matches("[0-9][0-9]*");
	}

	public static String inputText(String message) {
		System.out.println(message);
		return scan.nextLine();
	}
}
