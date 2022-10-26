package myGame.personajes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import myGame.errores.datoInvalidoException;
import myGame.errores.err;
import myGame.errores.noHayEspacioException;
import myGame.errores.pokemonExisteException;
import myGame.errores.pokemonSinVidaException;
import myGame.items.bagJugador;
import myGame.items.smartphone;
import myGame.pokemon.pokemonJugador;
import myGame.utiles.util;

public class jugador extends personaje implements Serializable {

    private static final long serialVersionUID = -79098722653058143L;
    private bagJugador items;
    private pokemonJugador ultimoPokemon;
    private int cartera;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////VARIABLES

    public jugador(String nombre, bagJugador items) {
        super(nombre);
        this.items = items;
        cartera = 100;
    }

    public jugador() {
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////CONSTRUCTORES

    public bagJugador getItems() {
        return items;
    }

    public pokemonJugador getUltimoPokemon() {
        return ultimoPokemon;
    }

    public void setUltimoPokemon(pokemonJugador ultimoPokemon) {
        this.ultimoPokemon = ultimoPokemon;
    }

    public int getCartera() {
        return cartera;
    }

    public void setCartera(int cartera) {
        this.cartera = cartera;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////GETTER Y SETTER

    public static List<jugador> anadirPersonajes() {
        bagJugador mochilaAsh = new bagJugador(15, smartphone.anadirMovil(), pokemonJugador.anadirPokemonAshVacio());
        bagJugador mochilaMisty = new bagJugador(15, smartphone.anadirMovil(), pokemonJugador.anadirPokemonMistyVacio());
        bagJugador mochilaBrock = new bagJugador(15, smartphone.anadirMovil(), pokemonJugador.anadirPokemonBrockVacio());
        jugador ash = new jugador("Ash", mochilaAsh);
        jugador misty = new jugador("Misty", mochilaMisty);
        jugador brock = new jugador("Brock", mochilaBrock);
        List<jugador> personajesJuego = new ArrayList<>();
        personajesJuego.add(ash);
        personajesJuego.add(misty);
        personajesJuego.add(brock);
        return personajesJuego;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////PERSONAJES JUEGO

    /**
     * Función que permite comprobar la vida de los pokemon del jugador
     *
     * @return VERDADERO - si aun tiene pokemon con vida
     */

    public boolean comprobarVidaListaPokemon() {
        return this.items.getMisPokemon().stream().anyMatch(p -> p.getVida() > 0);
    }

    /**
     * Función que permite elegir un pokemon
     *
     * @param derrotado
     * @return Pokemon elegido
     */

    public pokemonJugador elegirPokemonJugador(boolean derrotado) {
        int eleccion = -1;
        boolean pokemonDisponible = false;
        List<pokemonJugador> misPokemon = this.items.getMisPokemon();

        do {
            try {
                if (!derrotado) {
                    System.out.println("0. Atrás");
                }
                misPokemon.forEach(pokemon -> {
                    int n = misPokemon.indexOf(pokemon) + 1;
                    System.out.println(n + ". " + pokemon.getNombre() + " tiene una vida de: " + pokemon.getVida());
                });

                eleccion = util.introducirNumeroObligatorio("Elige una opcion");
                if (!derrotado && eleccion == 0) {
                    break;
                }
                if (eleccion <= 0 || eleccion > misPokemon.size())
                    throw new datoInvalidoException(err.DATO_INVALIDO.toString());
                if (misPokemon.get(eleccion - 1).getVida() > 0) return misPokemon.get(eleccion - 1);
                else throw new pokemonSinVidaException(err.POKEMON_SIN_VIDA.toString());

            } catch (datoInvalidoException | pokemonSinVidaException e) {
                e.printStackTrace();
            }
        } while (!pokemonDisponible);
        return null;
    }

    /**
     * Funció que permite consultar los pokemon del jugador
     *
     * @return
     */

    public void consultarPokemon() {
        List<pokemonJugador> misPokemon = items.getMisPokemon();
        int opcionMenu = -1;
        boolean noSeleccionado = false;

        if (!misPokemon.isEmpty()) {
            do {
                System.out.println("0. Volver al menú.");
                misPokemon.forEach(pokemonJugador -> {
                    int n = misPokemon.indexOf(pokemonJugador) + 1;
                    System.out.print(n + ". " + pokemonJugador.getNombre());
                    if (ultimoPokemon == pokemonJugador) {
                        System.out.println(" - Seleccionado");
                    } else System.out.println();
                });

                try {
                    opcionMenu = util.introducirNumeroObligatorio("Elige un pokemon: ");

                    if (opcionMenu < 0 || opcionMenu > misPokemon.size()) {
                        throw new datoInvalidoException(err.DATO_INVALIDO.toString());
                    } else if (opcionMenu != 0) {

                        pokemonJugador aux = misPokemon.get(opcionMenu - 1);
                        System.out.println("Nombre: " + aux.getNombre() + "\nVida: " + aux.getVida() + "\nNivel: " + aux.getNivel() + "\nAtaques ->");
                        aux.getMovimientos().forEach(moveJugador -> {
                            System.out.println(moveJugador.getNombre() + "   -   " + moveJugador.getAtaquesRestantes() + "/" + moveJugador.getLimiteAtaques());
                        });
                        System.out.println();
                        System.out.println("0. Atrás");
                        if (ultimoPokemon != aux) {
                            noSeleccionado = true;
                            System.out.println("1. Establecer como pokemon principal");
                        }

                        while (true) {
                            try {
                                int option = util.introducirNumeroObligatorio("Elige una opción: ");
                                if (option < 0 || option > 1)
                                    throw new datoInvalidoException(err.DATO_INVALIDO.toString());
                                else if (option == 0) {
                                    break;
                                } else {
                                    if (noSeleccionado) {
                                        System.out.println("Tu pokemon principal ahora es " + aux.getNombre());
                                        ultimoPokemon = aux;
                                        break;
                                    } else throw new datoInvalidoException(err.DATO_INVALIDO.toString());
                                }
                            } catch (datoInvalidoException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                } catch (datoInvalidoException e) {
                    e.printStackTrace();
                }
            } while (opcionMenu != 0);
        }
    }

    public void anadirPokemon(pokemonJugador p) throws pokemonExisteException, noHayEspacioException {
        while (true) {
            if (items.getMisPokemon().size() < items.getCapacidad()) {
                if (!items.getMisPokemon().contains(p)) {
                    items.getMisPokemon().add(p);
                    break;
                } else {
                    throw new pokemonExisteException(err.POKEMON_YA_EXISTE.toString());
                }
            } else {
                throw new noHayEspacioException(err.NO_HAY_ESPACIO_PARA_POKEMON.toString());
            }
        }
    }
}

