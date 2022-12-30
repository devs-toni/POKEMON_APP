package myGame;

import myGame.errores.datoInvalidoException;
import myGame.errores.err;
import myGame.errores.itemLlenoException;
import myGame.errores.noHayPokemonException;
import myGame.items.smartphone;
import myGame.personajes.enemigo;
import myGame.personajes.jugador;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import static myGame.items.bagJugador.anadirObjeto;
import static myGame.utiles.util.*;

public class aplicacion {

	public static void main(String[] args) {
		int menuJuego = -1;
		boolean inicio = true;
		boolean salir = false;
		boolean cargado = false;
		boolean seleccionado = false;
		boolean tienesPokemon = false;
		Properties propiedad = new Properties();
		game myGame = new game(jugador.anadirPersonajes());

		tienesPokemon = cargar(myGame, propiedad);

		do {
			try {
				menuJuego = menuJuego(inicio);
				if (menuJuego < 1 || menuJuego > 12) {
					throw new datoInvalidoException(err.DATO_INVALIDO.toString());
				}
			} catch (datoInvalidoException e) {
				e.printStackTrace();
			}
			inicio = false;

			if (menuJuego < 1 || menuJuego > 12)
				System.out.println(err.DATO_INVALIDO.toString());

			switch (menuJuego) {

			case 1:
				myGame.getHistoria().encuentroPokemonSalvaje(myGame.getUser(), myGame);
				System.out.println("\nQue camino quieres emprender ahora?\n");
				break;
			case 2:
				myGame.getHistoria().encuentroTeamRocket(myGame.getUser(), myGame);
				System.out.println("\nQue camino quieres emprender ahora?\n");
				break;
			case 3:
				myGame.getHistoria().torneoEntrenadores(myGame.getUser(), myGame);
				System.out.println("\nQue camino quieres emprender ahora?\n");
				break;
			case 4:
				myGame.getHistoria().torneo(enemigo.anadirOponentesTorneo(), myGame, myGame.getUser());
				System.out.println("\nQue camino quieres emprender ahora?\n");
				break;
			case 5:
				try {
					myGame.getUser().getItems().eliminarPokemon();
				} catch (noHayPokemonException e) {
					e.printStackTrace();
				}
				break;
			case 6:
				try {
					anadirObjeto(myGame.getUser().getItems().getPocion(), 1, false);
				} catch (itemLlenoException e) {
					e.printStackTrace();
				}
				System.out.println("\nQue camino quieres emprender ahora?\n");
				break;
			case 7:
				try {
					anadirObjeto(myGame.getUser().getItems().getFruta(), 1, false);
				} catch (itemLlenoException e) {
					e.printStackTrace();
				}
				System.out.println("\nQue camino quieres emprender ahora?\n");
				break;
			case 8:
				try {
					anadirObjeto(myGame.getUser().getItems().getAntidoto(), 1, false);
				} catch (itemLlenoException e) {
					e.printStackTrace();
				}
				System.out.println("\nQue camino quieres emprender ahora?\n");
				break;
			case 9:
				try {
					anadirObjeto(myGame.getUser().getItems().getPokeball(), 1, false);
				} catch (itemLlenoException e) {
					e.printStackTrace();
				}
				System.out.println("\nQue camino quieres emprender ahora?\n");
				break;
			case 10:
				myGame.getTienda().atender(myGame.getUser());
				System.out.println("\nQue camino quieres emprender ahora?\n");
				break;

			case 11:
				String llamada = "";
				boolean llamando = false;
				int menuStart = -1;

				do {
					menuStart = menuStart();
					switch (menuStart) {
					case 1:
						myGame.getUser().consultarPokemon();
						break;
					case 2:
						myGame.getUser().getItems().abrirMochila();
						break;
					case 3:
						llamada = myGame.getUser().getItems().getMovil().consultMobile();
						if (llamada != null) {
							llamando = true;
						}
						break;
					}
					if (llamando)
						break;

				} while (menuStart != 0);

				smartphone.llamar(llamada);
				System.out.println("\nQue camino quieres emprender ahora?\n");
				break;

			case 12:
				try {
					int menuGuardar = -1;
					do {
						menuGuardar = menuGuardar();
						if (menuGuardar < 0 || menuGuardar > 2) {
							throw new datoInvalidoException(err.DATO_INVALIDO.toString());
						} else if (menuGuardar == 1) {
							salir = true;
							System.out.println("Saliendo del Juego . . .");
							break;
						} else if (menuGuardar == 2) {
							try {
								propiedad.setProperty("pokemon", "partida");
								propiedad.store(new FileOutputStream(FILE_PATH + "pokemon.savegame"), "Guardado");
								saveData(myGame.getUser(), FILE_PATH + "pokemon.player");
								saveData(tienesPokemon, FILE_PATH + "pokemon.doctor");
								salir = true;
								System.out.println("Guardando Partida . . .");
								System.out.println("Saliendo del Juego . . .");
								break;
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
					} while (menuGuardar != 0);
				} catch (datoInvalidoException e) {
					e.printStackTrace();
				}
				break;
			}
		} while (!salir);
	}
}