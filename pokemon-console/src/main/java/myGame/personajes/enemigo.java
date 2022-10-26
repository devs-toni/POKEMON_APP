package myGame.personajes;

import myGame.items.bagMaquina;
import myGame.pokemon.pokemonMaquina;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class enemigo extends personaje implements Serializable {

    private static final long serialVersionUID = -8828759973445783213L;
    private bagMaquina pokeballs;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////VARIABLES

    public enemigo(String nombre, bagMaquina pokeballs) {
        super(nombre);
        this.pokeballs = pokeballs;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////CONSTRUCTORES

    public bagMaquina getPokeballs() {
        return pokeballs;
    }

    public void setPokeballs(bagMaquina pokeballs) {
        this.pokeballs = pokeballs;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////GETTER Y SETTER

    /**
     * Enemigos Principales
     *
     * @return
     */

    public static enemigo anadirAsh() {
        return new enemigo("Ash", bagMaquina.anadirPokegearAsh());
    }

    public static enemigo anadirMisty() {
        return new enemigo("Misty", bagMaquina.anadirPokegearMisty());
    }

    public static enemigo anadirBrock() {
        return new enemigo("Brock", bagMaquina.anadirPokegearBrock());
    }

    public static enemigo anadirRival() {
        return new enemigo("Rival", bagMaquina.anadirPokeagearRival());
    }

    public static enemigo anadirMonje() {
        return new enemigo("Monje", bagMaquina.anadirPokegearMonje());
    }

    public static enemigo anadirSerena() {
        return new enemigo("Serena", bagMaquina.anadirPokegearSerena());
    }

    public static enemigo anadirDawn() {
        return new enemigo("Dawn", bagMaquina.anadirPokegearDawn());
    }

    public static enemigo anadirIris() {
        return new enemigo("Iris", bagMaquina.anadirPokegearIris());
    }

    public static enemigo anadirOak() {
        return new enemigo("Oak", bagMaquina.anadirPokegearOak());
    }

    public static enemigo anadirTeamRocket() {
        return new enemigo("Team Rocket", bagMaquina.anadirPokegearTeamRocket());
    }

    /**
     * Enemigos Casuales Salvajes
     *
     * @return
     */

    public static enemigo salvaje1() {
        return new enemigo("Pidgeot", bagMaquina.anadirPokegearSalvaje());
    }

    public static enemigo salvaje2() {
        return new enemigo("Rattata", bagMaquina.anadirPokegearSalvaje2());
    }

    public static enemigo salvaje3() {
        return new enemigo("Golem", bagMaquina.anadirPokegearSalvaje3());
    }

    public static enemigo salvaje4() {
        return new enemigo("Onix", bagMaquina.anadirPokegearSalvaje4());
    }

    public static enemigo salvaje5() {
        return new enemigo("Krabby", bagMaquina.anadirPokegearSalvaje5());
    }

    public static enemigo salvaje6() {
        return new enemigo("Voltorb", bagMaquina.anadirPokegearSalvaje6());
    }

    /**
     * List de enemigos salvajes
     *
     * @return
     */

    public static List<enemigo> salvajes() {
        List<enemigo> list = new ArrayList<>();
        list.add(salvaje1());
        list.add(salvaje2());
        list.add(salvaje3());
        list.add(salvaje4());
        list.add(salvaje5());
        list.add(salvaje6());
        return list;
    }

    public static List<enemigo> anadirOponentesTorneo() {
        List<enemigo> oponentes = new ArrayList<>();
        oponentes.add(anadirSerena());
        oponentes.add(anadirIris());
        oponentes.add(anadirDawn());
        return oponentes;
    }

    /**
     * Función que permite elegir pokemon a la máquina
     *
     * @return pokemon malvado que la maquina ha elegido de forma aleatoria
     */

    public pokemonMaquina elegirPokemonMaquina() {
        List<pokemonMaquina> listPokemonMachine = this.pokeballs.getListaPokemonMalo();

        if (comprobarVidaListaPokemon()) {
            pokemonMaquina pok = listPokemonMachine.stream().filter(pokemonMaquina -> pokemonMaquina.getVida() > 0).findFirst().get();
            System.out.println("LUCHAS CONTRA - " + pok.getNombre().toUpperCase() + " - Nivel " + pok.getNivel());
            return pok;
        }
        return null;
    }

    /**
     * Función que permite comprobar la vida de los pokemon enemigos
     *
     * @return VERDADERO - indica que tiene pokemon con vida
     */

    public boolean comprobarVidaListaPokemon() {

        return this.pokeballs.getListaPokemonMalo().stream().anyMatch(p -> p.getVida() > 0);
    }

    public void establecerNivel(jugador user) {
        Random r = new Random();
        if (user.getUltimoPokemon().getNivel() < 2 && user.getUltimoPokemon().getNivel() > 0)
            pokeballs.getListaPokemonMalo().forEach(p -> {
                int nivel = r.nextInt(user.getUltimoPokemon().getNivel());
                p.setNivel(nivel);
                p.getMovimientos().forEach(m -> {
                    m.setDolor(m.getDolor());
                });
            });

        else if (user.getUltimoPokemon().getNivel() < 4  && user.getUltimoPokemon().getNivel() > 0)
            pokeballs.getListaPokemonMalo().forEach(p -> {
                int nivel = r.nextInt(user.getUltimoPokemon().getNivel());
                p.setNivel(nivel);
                p.getMovimientos().forEach(m -> {
                    if (p.getNivel() < 2)
                        m.setDolor(m.getDolor() + 1);
                    else m.setDolor(m.getDolor() + 2);
                });
            });

        else if (user.getUltimoPokemon().getNivel() < 6  && user.getUltimoPokemon().getNivel() > 0)
            pokeballs.getListaPokemonMalo().forEach(p -> {
                int nivel = r.nextInt(user.getUltimoPokemon().getNivel() - 2) + 2;
                p.setNivel(nivel);
                p.getMovimientos().forEach(m -> {
                    if (p.getNivel() < 4)
                        m.setDolor(m.getDolor() + 2);
                    else m.setDolor(m.getDolor() + 3);
                });
            });

        else if (user.getUltimoPokemon().getNivel() < 8  && user.getUltimoPokemon().getNivel() > 0)
            pokeballs.getListaPokemonMalo().forEach(p -> {
                int nivel = r.nextInt(user.getUltimoPokemon().getNivel() - 4) + 4;
                p.setNivel(nivel);
                p.getMovimientos().forEach(m -> {
                    if (p.getNivel() < 6)
                        m.setDolor(m.getDolor() + 3);
                    else m.setDolor(m.getDolor() + 4);
                });
            });

        else if (user.getUltimoPokemon().getNivel() < 10  && user.getUltimoPokemon().getNivel() > 0)
            pokeballs.getListaPokemonMalo().forEach(p -> {
                int nivel = r.nextInt(user.getUltimoPokemon().getNivel() - 6) + 6;
                p.setNivel(nivel);
                p.getMovimientos().forEach(m -> {
                    if (p.getNivel() < 8)
                        m.setDolor(m.getDolor() + 4);
                    else m.setDolor(m.getDolor() + 5);
                });
            });

        else if (user.getUltimoPokemon().getNivel() > 0)
            pokeballs.getListaPokemonMalo().forEach(p -> {
                int nivel = r.nextInt(user.getUltimoPokemon().getNivel() - 7) + 7;
                p.setNivel(nivel);
                p.getMovimientos().forEach(m -> {
                    if (p.getNivel() < 9)
                        m.setDolor(m.getDolor() + 5);
                    else m.setDolor(m.getDolor() + 6);
                });
            });
    }


}



