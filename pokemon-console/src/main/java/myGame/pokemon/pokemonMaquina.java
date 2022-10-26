package myGame.pokemon;

import myGame.lucha.*;
import myGame.utiles.util;

import java.io.Serializable;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static myGame.utiles.util.vidaAbsoluta;

public class pokemonMaquina extends pokemon implements Serializable {

    private static final long serialVersionUID = 8624657250353342278L;
    private List<moveMaquina> movimientos;
    private boolean esSalvaje;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////VARIABLES

    //CONSTRUCTOR POKEMON ENEMIGO A PARTIR DE UN NIVEL PREDEFINIDO SEGUN LAS CIRCUNSTANCIAS DEL JUGADOR
    public pokemonMaquina(String nombre, int vida, int nivel, type tipo, List<moveMaquina> movimientos, boolean esSalvaje) {
        super(nombre, vida, nivel, tipo);
        this.movimientos = movimientos;
        this.esSalvaje = esSalvaje;
        super.setConcentracion(100);
    }

    //CONSTRUCTOR POKEMON ENEMIGO VACIO
    public pokemonMaquina() {
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////CONSTRUCTORES


    public List<moveMaquina> getMovimientos() {
        return movimientos;
    }

    public void setMovimientos(List<moveMaquina> movimientos) {
        this.movimientos = movimientos;
    }

    public boolean getEsSalvaje() {
        return esSalvaje;
    }

    public void setEsSalvaje(boolean esSalvaje) {
        this.esSalvaje = esSalvaje;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////GETTER Y SETTE

    /**
     * POKEMON ENEMIGOS DE DISTINTONS NIVELS 0-10
     *
     * @return
     */

    public static pokemonMaquina anadirWeezing() {
        return new pokemonMaquina("Weezing", 30, 0, type.NORMAL, moveMaquina.anadirMovesEnemigoNormal(), false);
    }

    public static pokemonMaquina anadirEevee() {
        return new pokemonMaquina("Eevee", 30, 0, type.NORMAL, moveMaquina.anadirMovesEnemigoNormal(), false);
    }

    public static pokemonMaquina anadirMeowth() {
        return new pokemonMaquina("Meowth", 30, 0, type.NORMAL, moveMaquina.anadirMovesEnemigoNormal(), false);
    }

    public static pokemonMaquina anadirSeadra() {
        return new pokemonMaquina("Seadra", 30, 0, type.AGUA, moveMaquina.anadirMovesEnemigoAgua(), false);
    }

    public static pokemonMaquina anadirArbok() {
        return new pokemonMaquina("Arbok", 30, 0, type.NORMAL, moveMaquina.anadirMovesEnemigoNormal(), false);
    }

    public static pokemonMaquina anadirGlaceon() {
        return new pokemonMaquina("Glaceon", 30, 0, type.HIELO, moveMaquina.anadirMovesEnemigoHielo(), false);
    }

    public static pokemonMaquina anadirBellsprout() {
        return new pokemonMaquina("Bellsprout", 30, 0, type.PLANTA, moveMaquina.anadirMovesEnemigoPlanta(), false);
    }

    public static pokemonMaquina anadirBlastoise() {
        return new pokemonMaquina("Blastoise", 30, 0, type.AGUA, moveMaquina.anadirMovesEnemigoAgua(), false);
    }

    public static pokemonMaquina anadirSquirtle() {
        return new pokemonMaquina("Squirtle", 30, 0, type.AGUA, moveMaquina.anadirMovesEnemigoAgua(), false);
    }

    public static pokemonMaquina anadirBulbasur() {
        return new pokemonMaquina("Bulbasur", 30, 0, type.PLANTA, moveMaquina.anadirMovesEnemigoPlanta(), false);
    }

    public static pokemonMaquina anadirTotodile() {
        return new pokemonMaquina("Totodile", 30, 0, type.AGUA, moveMaquina.anadirMovesEnemigoAgua(), false);
    }

    public static pokemonMaquina anadirCharmander() {
        return new pokemonMaquina("Charmander", 30, 0, type.FUEGO, moveMaquina.anadirMovesEnemigoFuego(), false);
    }

    public static pokemonMaquina anadirCharizard() {
        return new pokemonMaquina("Charizard", 30, 0, type.FUEGO, moveMaquina.anadirMovesEnemigoFuego(), false);
    }

    public static pokemonMaquina anadirSnorlax() {
        return new pokemonMaquina("Snorlax", 30, 0, type.NORMAL, moveMaquina.anadirMovesEnemigoNormal(), false);
    }

    public static pokemonMaquina anadirRaichu() {
        return new pokemonMaquina("Raichu", 30, 0, type.ELECTRICO, moveMaquina.anadirMovesEnemigoElectrico(), false);
    }

    public static pokemonMaquina anadirMagneton() {
        return new pokemonMaquina("Magneton", 30, 0, type.ELECTRICO, moveMaquina.anadirMovesEnemigoElectrico(), false);
    }

    public static pokemonMaquina anadirMoltres() {
        return new pokemonMaquina("Moltres", 30, 0, type.FUEGO, moveMaquina.anadirMovesEnemigoFuego(), false);
    }

    public static pokemonMaquina anadirArticuno() {
        return new pokemonMaquina("Articuno", 30, 0, type.HIELO, moveMaquina.anadirMovesEnemigoHielo(), false);
    }

    public static pokemonMaquina anadirMagmar() {
        return new pokemonMaquina("Magmar", 30, 0, type.FUEGO, moveMaquina.anadirMovesEnemigoFuego(), false);
    }

    public static pokemonMaquina anadirJynx() {
        return new pokemonMaquina("Jynx", 30, 0, type.HIELO, moveMaquina.anadirMovesEnemigoHielo(), false);
    }

    public static pokemonMaquina anadirExeggutor() {
        return new pokemonMaquina("Exeggutor", 30, 0, type.PLANTA, moveMaquina.anadirMovesEnemigoPlanta(), false);
    }

    public static pokemonMaquina anadirPikachu() {
        return new pokemonMaquina("Pikachu", 30, 0, type.ELECTRICO, moveMaquina.anadirMovesEnemigoElectrico(), false);
    }

    /**
     * POKEMON SALVAJES
     *
     * @return
     */

    public static pokemonMaquina anadirPidgeot() {
        return new pokemonMaquina("Pidgeot", 30, 0, type.NORMAL, moveMaquina.anadirMovesEnemigoNormal(), true);
    }

    public static pokemonMaquina anadirRattata() {
        return new pokemonMaquina("Rattata", 30, 0, type.NORMAL, moveMaquina.anadirMovesEnemigoNormal(), true);
    }

    public static pokemonMaquina anadirGolem() {
        return new pokemonMaquina("Golem", 30, 0, type.NORMAL, moveMaquina.anadirMovesEnemigoNormal(), true);
    }

    public static pokemonMaquina anadirOnix() {
        return new pokemonMaquina("Onix", 30, 0, type.NORMAL, moveMaquina.anadirMovesEnemigoNormal(), true);
    }

    public static pokemonMaquina anadirVoltorb() {
        return new pokemonMaquina("Voltorb", 30, 0, type.ELECTRICO, moveMaquina.anadirMovesEnemigoElectrico(), true);
    }

    public static pokemonMaquina anadirKrabby() {
        return new pokemonMaquina("Krabby", 30, 0, type.NORMAL, moveMaquina.anadirMovesEnemigoAgua(), true);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////POKEMON MALOS

    /**
     * Función que permite evaluar la vida del pokemon malvado en cuestión
     *
     * @return
     */

    public boolean pokemonlives() {
        return this.vida > 0;
    }

    /**
     * Método que elije el movimiento de la máquina de forma aleatoria
     *
     * @return Movimiento de la maquina
     */

    public moveMaquina elegirMovimientoMaquina() {
        Random random = new Random();
        moveMaquina moveElegido = null;
        List<moveMaquina> copiaMovimientos = movimientos;
        Stream<moveMaquina> listaMovimientosMaquinaNivel;

        if (nivel < 3) {
            listaMovimientosMaquinaNivel = movimientos
                    .stream()
                    .filter(attack -> attack.getNivelAcceso() == 0);

            movimientos = listaMovimientosMaquinaNivel.collect(Collectors.toList());
            moveElegido = movimientos.get(random.nextInt(movimientos.size()));
            movimientos = copiaMovimientos;

        } else if (nivel < 7) {
            listaMovimientosMaquinaNivel = movimientos
                    .stream()
                    .filter(attack -> attack.getNivelAcceso() < 7);

            movimientos = listaMovimientosMaquinaNivel.collect(Collectors.toList());
            moveElegido = movimientos.get(random.nextInt(movimientos.size()));
            movimientos = copiaMovimientos;

        } else {
            moveElegido = movimientos.get(random.nextInt(movimientos.size()));
        }

        return moveElegido;
    }

    /**
     * Método de ataque del pokemon enemigo
     *
     * @param p Pokemon jugador
     * @return Variable ENVENENADO
     */

    public boolean ataqueMaquina(pokemonJugador p) {

        boolean veneno = false;
        Random aleatorio = new Random();
        moveMaquina movimientoMaquina = elegirMovimientoMaquina();
        int dano = movimientoMaquina.getDolor();
        int danoFinal = 0;

        if (movimientoMaquina.getFuncion() == funcionMoves.Ataque) {
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
            } else {
                danoFinal = aleatorio.nextInt(dano - ((dano * 40) / 100)) + ((dano * 60) / 100);
            }
            p.setVida(p.getVida() - danoFinal);
            System.out.println(p.getNombre() + "  -" + danoFinal + " -> PH: " + vidaAbsoluta(p));

        } else if (movimientoMaquina.getFuncion() == funcionMoves.Defensa) {
            if (p.getConcentracion() > 0) {
                p.setConcentracion(p.getConcentracion() - 30);
                if (p.getConcentracion() < 0) p.setConcentracion(0);
            }
            p.setVida(p.getVida() - movimientoMaquina.getDolor());
            System.out.println(p.getNombre() + "  -" + movimientoMaquina.getDolor() + " -> PH: " + vidaAbsoluta(p));
            System.out.println("Concentrado -> " + util.concentracionAbsoluta(p));

        } else if (movimientoMaquina.getFuncion() == funcionMoves.DefensaEspecial) {
            if (p.getConcentracion() > 0) {
                p.setConcentracion(p.getConcentracion() - 50);
                if (p.getConcentracion() <= 0) p.setConcentracion(0);
            }
            p.setVida(p.getVida() - movimientoMaquina.getDolor());
            System.out.println(p.getNombre() + "  -" + movimientoMaquina.getDolor() + " -> PH: " + vidaAbsoluta(p));
            System.out.println("Concentrado -> " + util.concentracionAbsoluta(p));
            veneno = true;

        } else if (movimientoMaquina.getFuncion() == funcionMoves.AtaqueEspecial) {
            p.setVida(p.getVida() - movimientoMaquina.getDolor());
            System.out.println(p.getNombre() + "  -" + movimientoMaquina.getDolor() + " -> PH: " + vidaAbsoluta(p));
            System.out.println("Concentrado -> " + util.concentracionAbsoluta(p));
        }
        return veneno;
    }
}
