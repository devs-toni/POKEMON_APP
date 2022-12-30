package myGame.lucha;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class moveJugador extends movimiento  implements Serializable {

    private static final long serialVersionUID = -987624538455921398L;
    private int ataquesRestantes;
    private int limiteAtaques;

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////VARIABLES



    public moveJugador(String nombre, funcionMoves funcion, int dolor, int nivelAcceso, int ataquesRestantes, int limiteAtaques) {
        super(nombre, funcion, dolor, nivelAcceso);
        this.ataquesRestantes = ataquesRestantes;
        this.limiteAtaques = limiteAtaques;

    }

    public moveJugador(String nombre, funcionMoves funcion, int nivelAcceso, int ataquesRestantes, int limiteAtaques) {
        super(nombre, funcion, nivelAcceso);
        this.ataquesRestantes = ataquesRestantes;
        this.limiteAtaques = limiteAtaques;
    }

    public moveJugador() {
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////CONSTRUCTORES

    public int getAtaquesRestantes() {
        return ataquesRestantes;
    }

    public void setAtaquesRestantes(int ataquesRestantes) {
        this.ataquesRestantes = ataquesRestantes;
    }

    public int getLimiteAtaques() {
        return limiteAtaques;
    }

    public void setLimiteAtaques(int limiteAtaques) {
        this.limiteAtaques = limiteAtaques;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////GETTER Y SETTER

    public static List<moveJugador> anadirMovesJugadorAgua() {
        List<moveJugador> listaAgua = new ArrayList<>();
        moveJugador moveAtaque = new moveJugador("Puño de Agua", funcionMoves.Ataque,12, 0, 35, 35);
        moveJugador moveDefensa = new moveJugador("Burbuja", funcionMoves.Defensa, 4,0, 30, 30);
        moveJugador moveDefensaSpc = new moveJugador("Lluvia Acida", funcionMoves.DefensaEspecial, 8, 3, 20, 20);
        moveJugador moveAtaqueSpc = new moveJugador("Tsunami", funcionMoves.AtaqueEspecial, 20, 5, 10, 10);
        listaAgua.add(moveAtaque);
        listaAgua.add(moveDefensa);
        listaAgua.add(moveAtaqueSpc);
        listaAgua.add(moveDefensaSpc);
        return listaAgua;
    }

    public static List<moveJugador> anadirMovesJugadorElectrico() {
        List<moveJugador> listaElectrico = new ArrayList<>();
        moveJugador movimientoAtaque = new moveJugador("Rayo", funcionMoves.Ataque, 14,0, 35, 35);
        moveJugador movimientoDefensa = new moveJugador("Chispas", funcionMoves.Defensa,6,0, 30, 30);
        moveJugador movimientoDefensaSpc = new moveJugador("Descarga Electrica", funcionMoves.DefensaEspecial, 10, 3, 20, 20);
        moveJugador movimientoAtaqueSpc = new moveJugador("Tormenta Electrica", funcionMoves.AtaqueEspecial, 22, 5, 5, 5);
        listaElectrico.add(movimientoAtaque);
        listaElectrico.add(movimientoDefensa);
        listaElectrico.add(movimientoAtaqueSpc);
        listaElectrico.add(movimientoDefensaSpc);
        return listaElectrico;
    }

    public static List<moveJugador> anadirMovesJugadorFuego() {
        List<moveJugador> listaFuego = new ArrayList<>();
        moveJugador moveAtaque = new moveJugador("Puño Fuego", funcionMoves.Ataque, 13,0, 35, 35);
        moveJugador moveDefensa = new moveJugador("Ascuas", funcionMoves.Defensa,5,0, 30, 30);
        moveJugador moveDefensaSpc = new moveJugador("Lava", funcionMoves.DefensaEspecial, 9, 3, 20, 20);
        moveJugador moveAtaqueSpc = new moveJugador("Llamarada", funcionMoves.AtaqueEspecial, 21, 5, 5, 5);
        listaFuego.add(moveAtaque);
        listaFuego.add(moveDefensa);
        listaFuego.add(moveAtaqueSpc);
        listaFuego.add(moveDefensaSpc);
        return listaFuego;

    }

    public static List<moveJugador> anadirMovesJugadorHielo() {
        List<moveJugador> listaHielo = new ArrayList<>();
        moveJugador moveAtaque = new moveJugador("Flecha Hielo", funcionMoves.Ataque, 12,0, 35, 35);
        moveJugador moveDefensa = new moveJugador("Ventisca", funcionMoves.Defensa,4,0, 30, 30);
        moveJugador moveDefensaSpc = new moveJugador("Invierno", funcionMoves.DefensaEspecial, 8, 3, 20, 20);
        moveJugador moveAtaqueSpc = new moveJugador("Rayo Helado", funcionMoves.AtaqueEspecial, 20, 5, 5, 5);
        listaHielo.add(moveAtaque);
        listaHielo.add(moveDefensa);
        listaHielo.add(moveAtaqueSpc);
        listaHielo.add(moveDefensaSpc);
        return listaHielo;
    }

    public static List<moveJugador> anadirMovesJugadorPlanta() {
        List<moveJugador> listaPlanta = new ArrayList<>();
        moveJugador moveAtaque = new moveJugador("Latigo Cepa", funcionMoves.Ataque, 12, 0,35, 35);
        moveJugador moveDefensa = new moveJugador("Rayo Solar", funcionMoves.Defensa,4,0, 30, 30);
        moveJugador moveDefensaSpc = new moveJugador("Hiedra del Diablo", funcionMoves.DefensaEspecial, 8, 3, 20, 20);
        moveJugador moveAtaqueSpc = new moveJugador("Tronco Aplastante", funcionMoves.AtaqueEspecial, 20, 5, 5, 5);
        listaPlanta.add(moveAtaque);
        listaPlanta.add(moveDefensa);
        listaPlanta.add(moveAtaqueSpc);
        listaPlanta.add(moveDefensaSpc);
        return listaPlanta;
    }

    public static List<moveJugador> anadirMovesJugadorNormal() {
        List<moveJugador> listaNormal = new ArrayList<>();
        moveJugador moveAtaque = new moveJugador("Embestida", funcionMoves.Ataque, 12,0, 35, 35);
        moveJugador moveDefensa = new moveJugador("Bloqueo", funcionMoves.Defensa,4,0, 30, 30);
        moveJugador moveDefensaSpc = new moveJugador("Aliento", funcionMoves.DefensaEspecial, 8, 3, 20, 20);
        moveJugador moveAtaqueSpc = new moveJugador("Puño de Hierro", funcionMoves.AtaqueEspecial, 20, 5, 5, 5);
        listaNormal.add(moveAtaque);
        listaNormal.add(moveDefensa);
        listaNormal.add(moveAtaqueSpc);
        listaNormal.add(moveDefensaSpc);
        return listaNormal;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////LISTA MOVIMIENTOS BUENOS

    public String toString(){
        return super.getFuncion() + " - " + super.getNombre() + " - " + ataquesRestantes + "/" + limiteAtaques;
    }

    /**
     * Función para saber si un movimiento ya no puede ejecutarse
     * @return VERDADERO si está vacío
     */
    public boolean movimientosVacio() {
        return ataquesRestantes == 0;
    }
}
