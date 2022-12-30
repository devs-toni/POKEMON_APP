package myGame;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import myGame.errores.datoInvalidoException;
import myGame.errores.err;
import myGame.errores.noHayEspacioException;
import myGame.historia.historiaJuego;
import myGame.personajes.enemigo;
import myGame.personajes.jugador;
import myGame.pokemon.pokemonJugador;
import myGame.utiles.util;

public class hisoriaPrincipal implements historiaJuego, Serializable {

    private static final long serialVersionUID = 262445902653320498L;

    /**
     * Al principio del juego el doctor ofrece un pokemon para empezar a jugar
     *
     * @param user Jugador
     */

    public void pokemonRegaloDoctor(jugador user) throws noHayEspacioException {
        boolean datoValido = false;
        int eleccion = -1;

        System.out.println("\nHola " + user.getNombre() + " , soy el Dr.Pokemon! Te ofrezco la posibilidad de conseguir un compañero pokemon para continuar tu viaje! Escoge bien, tu compañero es muy importante.");

        while (!datoValido) {
            try {
                List<pokemonJugador> regaloDoctor = pokemonJugador.anadirPokemonRegaloDoctor();

                regaloDoctor.forEach(pokemonJugador -> {
                    int n = regaloDoctor.indexOf(pokemonJugador) + 1;
                    System.out.println(n + ". " + pokemonJugador.getNombre());
                });
                eleccion = util.introducirNumeroObligatorio("Elige un compañero para tu viaje!");
                List<pokemonJugador> misPokemon = user.getItems().getMisPokemon();

                if (eleccion < 1 || eleccion > regaloDoctor.size()) {
                    throw new datoInvalidoException(err.DATO_INVALIDO.toString());
                }
                if (misPokemon.size() == 10) {
                    throw new noHayEspacioException(err.NO_HAY_ESPACIO_PARA_POKEMON.toString());
                }
                System.out.println("Has elegido a: " + regaloDoctor.get(eleccion - 1).getNombre());
                misPokemon.add(regaloDoctor.get(eleccion - 1));
                user.setUltimoPokemon(misPokemon.get(0));
                System.out.println("Aquí tienes mi número por si necesitas cualquier cosa. Que tengas mucha suerte en tu misión!");
                user.getItems().getMovil().getContactos().add("Dr.Pokemon");
                datoValido = true;

            } catch (datoInvalidoException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Vamos moviendonos por terreno salvaje y hay un encuentro con un pokemon salvaje
     *
     * @param user Jugador
     * @param game Juego
     */

    public void encuentroPokemonSalvaje(jugador user, game game) {
        Random r = new Random();
        enemigo random = enemigo.salvajes().get(r.nextInt(enemigo.salvajes().size()));
        random.establecerNivel(user);
        game.setMaquina(random);
        user.setUltimoPokemon(game.luchar(random, user));
    }

    /**
     * Encuentro con los eneemigos Jessie y James del TeamRocket
     *
     * @param user Jugador
     * @param game Juego
     */

    public void encuentroTeamRocket(jugador user, game game) {
        enemigo team = enemigo.anadirTeamRocket();
        team.establecerNivel(user);
        game.setMaquina(team);
        System.out.println("\nESTOY ANDANDO POR EL TERRENO Y ME ENCUENTRO CON EL TEAM ROCKET");
        user.setUltimoPokemon(game.luchar(team, user));
        System.out.println();
    }

    /**
     * Torneo convencional con los mejores entrenadores de la ciudad
     *
     * @param list
     * @param game
     */

    public void torneo(List<enemigo> list, game game, jugador user) {
        System.out.println("\nVOY A UN TORNEO DONDE ME ENFRENTARE A LOS MEJORES ENTRENADORES DE LA CIUDAD");
        list.forEach(e -> e.establecerNivel(user));
        game.combateTorneo(list);
    }

    /**
     * Torneo con los entrenadores pokemon
     *
     * @param user Jugador
     * @param game Juego
     */

    public void torneoEntrenadores(jugador user, game game) {
        System.out.println("\nVOY A UN TORNEO DONDE DEBO PELEAR CON MIS COMPAÑEROS BROCK Y MISTY");
        List<enemigo> colleagues = new ArrayList<>();

        if (user.getNombre().equalsIgnoreCase("ash")) {
            colleagues.add(enemigo.anadirBrock());
            colleagues.add(enemigo.anadirMisty());
        } else if (user.getNombre().equalsIgnoreCase("misty")) {
            colleagues.add(enemigo.anadirAsh());
            colleagues.add(enemigo.anadirBrock());
        } else if (user.getNombre().equalsIgnoreCase("brock")) {
            colleagues.add(enemigo.anadirMisty());
            colleagues.add(enemigo.anadirAsh());
        }

        colleagues.forEach(e -> e.establecerNivel(user));
        game.combateTorneo(colleagues);
    }

    /**
     * Encuentro amistoso con un monje en un monasterio
     *
     * @param user
     * @param game
     */

    public void encuentroMonje(jugador user, game game) {
        enemigo monje = enemigo.anadirMonje();
        monje.establecerNivel(user);
        game.setMaquina(monje);
        System.out.println("\nVOY A UN MONASTERIO Y UN MONJE ME RETA A UN COMBATE POKEMON");
        System.out.println("A PELEAR-------");
        user.setUltimoPokemon(game.luchar(monje, user));
        System.out.println();
    }

    /**
     * Encuentro amistoso con amigo Oak
     *
     * @param user
     * @param game
     */

    public void encuentroAmistosoOak(jugador user, game game) {
        enemigo oak = enemigo.anadirOak();
        oak.establecerNivel(user);
        game.setMaquina(oak);
        System.out.println("\nMI GRAN AMIGO OAK ME ANIMA A TENER UNA BATALLA AMISTOSA PARA PONER A PRUEBA SUS POKEMON");
        System.out.println("A PELEAR-------");
        user.setUltimoPokemon(game.luchar(oak, user));
        System.out.println();
    }

    /**
     * Combate contra un ciudado creido
     *
     * @param user
     * @param game
     */

    public void combateCiudadano(jugador user, game game) {
        enemigo rival = enemigo.anadirRival();
        rival.establecerNivel(user);
        game.setMaquina(rival);
        System.out.println("\nUN CIUDADANO CRECIDO POR NO HAVER PERDIDO NINGUNA BATALLA ME RETA A UN DUELO");
        System.out.println("A PELEAR-------");
        user.setUltimoPokemon(game.luchar(rival, user));
        System.out.println();
    }
}
