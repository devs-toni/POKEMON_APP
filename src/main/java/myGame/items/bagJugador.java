package myGame.items;

import static myGame.utiles.util.valorCero;
import static myGame.utiles.util.vidaAbsoluta;

import java.io.Serializable;
import java.util.List;
import java.util.Random;

import myGame.game;
import myGame.errores.datoInvalidoException;
import myGame.errores.err;
import myGame.errores.itemLlenoException;
import myGame.errores.noEsSalvajeException;
import myGame.errores.noHayPokeballsException;
import myGame.errores.noHayPokemonException;
import myGame.errores.noHayPokemonHeridoException;
import myGame.errores.pokemonExisteException;
import myGame.errores.vidaAlMaximoException;
import myGame.personajes.jugador;
import myGame.pokemon.pokemonJugador;
import myGame.pokemon.pokemonMaquina;
import myGame.utiles.util;

public class bagJugador implements Serializable {

    public enum estado {
        CURADO, CAPTURADO, FUGADO, CURAVENENO
    }

    private static final long serialVersionUID = 3734420490969536024L;
    private int capacidad;
    private objetosTienda pocion;
    private objetosTienda fruta;
    private objetosTienda antidoto;
    private objetosTienda pokeball;
    private smartphone movil;
    private List<pokemonJugador> misPokemon;


    //////////////////////////////////////////////////////////////////////////////////////////////////////////////VARIABLES

    //CONSTRUCTOR MOCHILA POR DEFECTO
    public bagJugador(int capacidad, smartphone movil, List<pokemonJugador> misPokemon) {
        this.capacidad = capacidad;
        this.movil = movil;
        this.misPokemon = misPokemon;
        pocion = objetosTienda.POCION;
        fruta = objetosTienda.FRUTA;
        antidoto = objetosTienda.ANTIDOTO;
        pokeball = objetosTienda.POKEBALL;
    }

    public bagJugador() {
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////CONSTRUCTORES

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public objetosTienda getPocion() {
        return pocion;
    }

    public void setPocion(objetosTienda pocion) {
        this.pocion = pocion;
    }

    public objetosTienda getFruta() {
        return fruta;
    }

    public void setFruta(objetosTienda fruta) {
        this.fruta = fruta;
    }

    public objetosTienda getAntidoto() {
        return antidoto;
    }

    public void setAntidoto(objetosTienda antidoto) {
        this.antidoto = antidoto;
    }

    public objetosTienda getPokeball() {
        return pokeball;
    }

    public void setPokeball(objetosTienda pokeball) {
        this.pokeball = pokeball;
    }

    public smartphone getMovil() {
        return movil;
    }

    public void setMovil(smartphone movil) {
        this.movil = movil;
    }

    public List<pokemonJugador> getMisPokemon() {
        return misPokemon;
    }

    public void setMisPokemon(List<pokemonJugador> misPokemon) {
        this.misPokemon = misPokemon;
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////GETTER Y SETTER

    /**
     * Función que permite abrir la mochila desde el menú de juego
     */

    public void abrirMochila() {
        int eleccion = -1;
        do {
            try {
                eleccion = menuItems();
                switch (eleccion) {
                    case 1:
                        if (valorCero(pocion, "No tienes ninguna pocion para usar\n")) break;
                        curaPokemon(pocion);
                        break;
                    case 2:
                        if (valorCero(fruta, "No tienes ninguna fruta curativa para usar\n")) break;
                        curaPokemon(fruta);
                        break;
                    case 3:
                        if (valorCero(antidoto, "No tienes ningun antidoto para usar\n")) break;
                        curaPokemon(antidoto);
                        break;
                    case 4:
                        System.out.println("Las pokeball se pueden emplear cuando te encuentras con un pokemon salvaje,\nde este modo puedes intentar capturarlo");
                        break;
                }
                if (eleccion < 0 || eleccion > 4) {
                    throw new datoInvalidoException(err.DATO_INVALIDO.toString());
                }
            } catch (datoInvalidoException e) {
                e.printStackTrace();
            }
        } while (eleccion != 0);
    }

    /**
     * Función que permite abrir la mochila desde el combate
     *
     * @param user
     * @param salvaje Pokemon salvaje disponible para ser capturado en caso de
     *                que el contrincante sea un pokemon salvaje
     * @return La variable ATRAS
     */

    public estado abrirMochilaLucha(jugador user, pokemonMaquina salvaje) {
        int eleccion = -1;

        do {
            try {
                eleccion = menuItems();
                switch (eleccion) {
                    case 1:
                        if (valorCero(pocion, err.NO_HAY_POCIONES.toString())) break;
                        if (sanarPokemonLucha(pocion))
                            return estado.CURADO;
                        break;
                    case 2:
                        if (valorCero(fruta, err.NO_HAY_FRUTA.toString())) break;
                        if (sanarPokemonLucha(fruta))
                            return estado.CURADO;
                        break;
                    case 3:
                        if (valorCero(antidoto, err.NO_HAY_ANTIDOTO.toString())) break;
                        return estado.CURAVENENO;
                    case 4:
                        if (valorCero(pokeball, err.NO_HAY_POKEBALL.toString())) break;

                        Random aleatorio = new Random();
                        int probabilidad = aleatorio.nextInt(90) + 10;
                        try {
                            if (misPokemon
                                    .stream()
                                    .anyMatch(pokemonJugador -> pokemonJugador.getNombre().equalsIgnoreCase(salvaje.getNombre()))) {
                                throw new pokemonExisteException(err.POKEMON_YA_EXISTE.toString());
                            }
                            if (salvaje.getEsSalvaje() && pokeball.getCantidad() > 0) {
                                System.out.println("Intentando atrapar al pokemon!");
                                System.out.println(". . . ");

                                if (salvaje.getVida() > 25) {
                                    if (probabilidad > 80) {
                                        game.anadirPokemonSalvaje(user, salvaje);
                                        pokeball.setCantidad(pokeball.getCantidad() - 1);
                                        salvaje.setEsSalvaje(false);
                                        System.out.println(salvaje.getNombre() + " ha  sido capturado!");
                                        System.out.println("Se ha incluido en tu colección de pokemon");
                                        return estado.CAPTURADO;
                                    } else {
                                        pokeball.setCantidad(pokeball.getCantidad() - 1);
                                        System.out.println(salvaje.getNombre() + " se ha escapado de la pokeball");
                                        return estado.FUGADO;
                                    }

                                } else if (salvaje.getVida() > 20) {
                                    if (probabilidad > 50) {
                                        game.anadirPokemonSalvaje(user, salvaje);
                                        pokeball.setCantidad(pokeball.getCantidad() - 1);
                                        salvaje.setEsSalvaje(false);
                                        System.out.println(salvaje.getNombre() + " ha  sido capturado!");
                                        System.out.println("Se ha incluido en tu colección de pokemon");
                                        return estado.CAPTURADO;
                                    } else {
                                        pokeball.setCantidad(pokeball.getCantidad() - 1);
                                        System.out.println(salvaje.getNombre() + " se ha escapado de la pokeball");
                                        return estado.FUGADO;
                                    }

                                } else if (salvaje.getVida() > 15) {
                                    if (probabilidad > 35) {
                                        game.anadirPokemonSalvaje(user, salvaje);
                                        pokeball.setCantidad(pokeball.getCantidad() - 1);
                                        salvaje.setEsSalvaje(false);
                                        System.out.println(salvaje.getNombre() + " ha  sido capturado!");
                                        System.out.println("Se ha incluido en tu colección de pokemon");
                                        return estado.CAPTURADO;
                                    } else {
                                        pokeball.setCantidad(pokeball.getCantidad() - 1);
                                        System.out.println(salvaje.getNombre() + " se ha escapado de la pokeball");
                                        return estado.FUGADO;
                                    }

                                } else if (salvaje.getVida() > 10) {
                                    if (probabilidad > 20) {
                                        game.anadirPokemonSalvaje(user, salvaje);
                                        pokeball.setCantidad(pokeball.getCantidad() - 1);
                                        salvaje.setEsSalvaje(false);
                                        System.out.println(salvaje.getNombre() + " ha  sido capturado!");
                                        System.out.println("Se ha incluido en tu colección de pokemon");
                                        return estado.CAPTURADO;
                                    } else {
                                        pokeball.setCantidad(pokeball.getCantidad() - 1);
                                        System.out.println(salvaje.getNombre() + " se ha escapado de la pokeball");
                                        return estado.FUGADO;
                                    }

                                } else if (salvaje.getVida() <= 10) {
                                    if (probabilidad > 10) {
                                        game.anadirPokemonSalvaje(user, salvaje);
                                        pokeball.setCantidad(pokeball.getCantidad() - 1);
                                        salvaje.setEsSalvaje(false);
                                        System.out.println(salvaje.getNombre() + " ha  sido capturado!");
                                        System.out.println("Se ha incluido en tu colección de pokemon");
                                        return estado.CAPTURADO;
                                    } else {
                                        pokeball.setCantidad(pokeball.getCantidad() - 1);
                                        System.out.println(salvaje.getNombre() + " se ha escapado de la pokeball");
                                        return estado.FUGADO;
                                    }
                                }
                            } else if (!salvaje.getEsSalvaje()) {
                                throw new noEsSalvajeException(err.POKEMON_NO_SALVAJE.toString());
                            } else if (pokeball.getCantidad() == 0) {
                                throw new noHayPokeballsException(err.NO_HAY_POKEBALL.toString());
                            }
                        } catch (pokemonExisteException | noEsSalvajeException | noHayPokeballsException e) {
                            e.printStackTrace();
                        }
                        break;
                }
                if (eleccion < 0 || eleccion > 4) {
                    throw new datoInvalidoException(err.DATO_INVALIDO.toString());
                }
            } catch (datoInvalidoException e) {
                e.printStackTrace();
            }
        } while (eleccion != 0);

        return null;
    }

    /**
     * Función para curar el veneno del pokemon
     */

    private void curaPokemon(objetosTienda objeto) {
        boolean curaCompletada = false;
        int cura = -1;

        do {
            try {
                if (misPokemon.stream().noneMatch(pokemon -> pokemon.getVida() < 30)) {
                    curaCompletada = true;
                    throw new noHayPokemonHeridoException(err.NO_HAY_POKEMON_HERIDOS.toString());
                }
                cura = menuCuras(objeto);
                if (cura < 0 || cura > misPokemon.size()) {
                    throw new datoInvalidoException(err.DATO_INVALIDO.toString());
                } else if (cura > 0) {
                    if (misPokemon.get(cura - 1).getVida() < 30) {
                        pokemonJugador aux = misPokemon.get(cura - 1);
                        if (objeto.equals(objetosTienda.POCION)) {
                            aux.setVida(30);
                            misPokemon.set(cura - 1, aux);
                            objeto.setCantidad(objeto.getCantidad() - 1);
                        } else if (objeto.equals(objetosTienda.FRUTA)) {
                            aux.setVida(aux.getVida() + 10);
                            misPokemon.set(cura - 1, aux);
                            objeto.setCantidad(objeto.getCantidad() - 1);
                        } else if (objeto.equals(objetosTienda.ANTIDOTO)) {
                            aux.setEnvenenado(false);
                            misPokemon.set(cura - 1, aux);
                            objeto.setCantidad(objeto.getCantidad() - 1);
                        }

                    } else throw new vidaAlMaximoException(err.POKEMON_AL_MAXIMO.toString());

                    if (misPokemon.stream().noneMatch(pokemon -> pokemon.getVida() < 30)) {
                        break;
                    }
                }
            } catch (datoInvalidoException | vidaAlMaximoException | noHayPokemonHeridoException e) {
                e.printStackTrace();
            }
        } while (cura != 0 && !curaCompletada && objeto.getCantidad() > 0);
    }

    /**
     * Función para curar la vida al máximo desde la mochila de combate
     *
     * @return VERDADERO si 1 solo pokemon ha sido curado, y en consecuencia se pierde turno
     */

    public boolean sanarPokemonLucha(objetosTienda objeto) {
        boolean completado = false;
        int valorPokemon = -1;

        do {
            try {
                if (misPokemon.stream().noneMatch(pokemon -> pokemon.getVida() < 30)) {
                    completado = true;
                    throw new noHayPokemonHeridoException(err.NO_HAY_POKEMON_HERIDOS.toString());
                }
                valorPokemon = menuCuras(objeto);
                if (valorPokemon < 0 || valorPokemon > misPokemon.size())
                    throw new datoInvalidoException(err.DATO_INVALIDO.toString());
                if (valorPokemon == 0) {
                    return false; //SE HA IDO ATRAS
                }
                if (misPokemon.get(valorPokemon - 1).getVida() >= 30)
                    throw new vidaAlMaximoException(err.POKEMON_AL_MAXIMO.toString());
                misPokemon.get(valorPokemon - 1).setVida(misPokemon.get(valorPokemon-1).getVida() + objeto.getCuracion());
                if (misPokemon.get(valorPokemon-1).getVida() >= 30) misPokemon.get(valorPokemon -1).setVida(30);
                objeto.setCantidad(objeto.getCantidad() - 1);
                System.out.println("La vida de tu pokemon se ha restaurado: +" + objeto.getCuracion() + "\nHas empleado tu turno.");
                return true; //SE HA CURADO EL POKEMON DESEADO

            } catch (datoInvalidoException | vidaAlMaximoException | noHayPokemonHeridoException e) {
                e.printStackTrace();
            }
        } while (!completado);

        return false;
    }

    /**
     * Muestreo de los items
     */

    private int menuItems() {
        System.out.println("0. Atrás\n1. Pociones   x" + pocion.getCantidad() + "\n2. Fruta Curativa   x" + fruta.getCantidad() +
                "\n3. Antidoto   x" + antidoto.getCantidad() + "\n4. Pokeball   x" + pokeball.getCantidad());
        return util.introducirNumeroObligatorio("Que quieres hacer");
    }

    /**
     * Métodos empleados para el borrado de pokémon
     */

    public void eliminarPokemon() throws noHayPokemonException {
        int menu = -1;

        do {
            if (misPokemon.size() == 0) {
                throw new noHayPokemonException(err.POKEMON_VACIOS.toString());
            }
            try {
                menu = menuBorrarPokemon();
                if (menu < 0 || menu > misPokemon.size())
                    throw new datoInvalidoException(err.DATO_INVALIDO.toString());
                misPokemon.remove(menu - 1);
            } catch (datoInvalidoException e) {
                e.printStackTrace();
            }
        } while (menu != 0);
    }

    public int menuBorrarPokemon() {
        System.out.println("0. Atrás");
        misPokemon.forEach(pokemon -> {
            int n = misPokemon.indexOf(pokemon) + 1;
            System.out.println(n + ". " + pokemon.getNombre());
        });
        return util.introducirNumeroObligatorio("Que pokemon quieres eliminar?");
    }

    /**
     * Método de muestreo de los pokemon y seleccion de cura
     *
     * @param objeto Objeto a seleccionar: pocion, cura o antidoto
     * @return el valor de la eleccion
     */

    public int menuCuras(objetosTienda objeto) {
        System.out.println("0. Atrás");
        misPokemon.forEach(pokemonJugador -> {
            int n = misPokemon.indexOf(pokemonJugador) + 1;
            System.out.println(n + "." + pokemonJugador.getNombre() + " - PH: " + vidaAbsoluta(pokemonJugador));
        });
        System.out.println("x" + objeto.getCantidad());
        return util.introducirNumeroObligatorio("Que pokemon deseas curar?");
    }

    /**
     * Método utilizado para añadir objetos de cualquier tipo de los 4 existentes
     *
     * @param objeto   Tipo de objeto a añadir
     * @param n        Cantidad de objetos a añadir
     * @param esCompra En caso de tratarse de una compra se muestra el resultado de forma diferente
     */

    public static void anadirObjeto(objetosTienda objeto, int n, boolean esCompra) throws itemLlenoException {
        if (objeto.objetoLleno()) {
            throw new itemLlenoException(err.ITEMS_LLENO.toString());
        }
        if ((objeto.getCantidad() + n) < objeto.getMaxmimaCantidad()) {
            objeto.setCantidad(objeto.getCantidad() + n);
            if (!esCompra)
                System.out.println("Se han añadido " + n + " " + objeto.name() + " a tu mochila");
        } else if (objeto.getCantidad() + n >= objeto.getMaxmimaCantidad()) {
            if (!esCompra) {
                System.out.println("Se han añadido " + (objeto.getMaxmimaCantidad() - objeto.getCantidad()) + " " + objeto.name() + " a tu mochila");
                System.out.println("Tienes las " + objeto.name() + " al máximo");
                objeto.setCantidad(objeto.getMaxmimaCantidad());
            }

        }

    }

    /**
     * Métodos empleados para verificar si el inventario está lleno o está vacío
     */

    public boolean inventarioVacío() {
        return !pokeball.quedaObjetos() && !fruta.quedaObjetos() && !antidoto.quedaObjetos() && !pocion.quedaObjetos();
    }

    public boolean inventarioLleno() {
        return pocion.objetoLleno() && fruta.objetoLleno() && antidoto.objetoLleno() && pokeball.objetoLleno();
    }
}
