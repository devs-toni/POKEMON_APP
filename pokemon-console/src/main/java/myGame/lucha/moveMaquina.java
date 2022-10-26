package myGame.lucha;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class moveMaquina extends movimiento  implements Serializable {

    private static final long serialVersionUID = -4883376858385785595L;

    public moveMaquina(String name, funcionMoves funcion, int dolor, int nivel) {
        super(name, funcion, dolor, nivel);
    }

    public moveMaquina(){}

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////CONSTRUCTORES

    public static List<moveMaquina> anadirMovesEnemigoNormal() {
        List<moveMaquina> listaNormal = new ArrayList<>();
        moveMaquina moveAtaque  = new moveMaquina("Embestida", funcionMoves.Ataque, 6, 0);
        moveMaquina moveDefensa  = new moveMaquina("Bloqueo", funcionMoves.Defensa,2, 0);
        moveMaquina moveAtaqueSpc = new moveMaquina("Puño de Hierro", funcionMoves.AtaqueEspecial, 10, 6);
        moveMaquina moveDefensaSpc  = new moveMaquina("Aliento Podrido", funcionMoves.DefensaEspecial, 4, 3);
        listaNormal.add(moveAtaque);
        listaNormal.add(moveDefensa);
        listaNormal.add(moveAtaqueSpc);
        listaNormal.add(moveDefensaSpc);
        return listaNormal;
    }

    public static List<moveMaquina> anadirMovesEnemigoAgua() {
        List<moveMaquina> listaAgua = new ArrayList<>();
        moveMaquina moveAtaque = new moveMaquina("Puño de Agua", funcionMoves.Ataque, 6 ,0);
        moveMaquina moveDefensa = new moveMaquina("Burbuja", funcionMoves.Defensa,2, 0);
        moveMaquina moveAtaqueSpc = new moveMaquina("Tsunami", funcionMoves.AtaqueEspecial, 10, 7);
        moveMaquina moveDefensaSpc = new moveMaquina("Lluvia Acida", funcionMoves.DefensaEspecial, 4,3);
        listaAgua.add(moveAtaque);
        listaAgua.add(moveDefensa);
        listaAgua.add(moveAtaqueSpc);
        listaAgua.add(moveDefensaSpc);
        return listaAgua;
    }

    public static List<moveMaquina> anadirMovesEnemigoElectrico() {
        List<moveMaquina> listaElectrico = new ArrayList<>();
        moveMaquina moveAtaque = new moveMaquina("Rayo", funcionMoves.Ataque, 6,0);
        moveMaquina moveDefensa = new moveMaquina("Chispas", funcionMoves.Defensa,2,0);
        moveMaquina moveAtaqueSpc = new moveMaquina("Tormenta Electrica", funcionMoves.AtaqueEspecial, 10, 7);
        moveMaquina moveDefensaSpc = new moveMaquina("Descarga Electrica", funcionMoves.DefensaEspecial, 4,3);
        listaElectrico.add(moveAtaque);
        listaElectrico.add(moveDefensa);
        listaElectrico.add(moveDefensaSpc);
        return listaElectrico;
    }

    public static List<moveMaquina> anadirMovesEnemigoFuego() {
        List<moveMaquina> listaFuego = new ArrayList<>();
        moveMaquina moveAtaque = new moveMaquina("Puño Fuego", funcionMoves.Ataque, 6,0);
        moveMaquina moveDefensa = new moveMaquina("Ascuas", funcionMoves.Defensa,2,0);
        moveMaquina moveAtaqueSpc = new moveMaquina("Llamarada", funcionMoves.AtaqueEspecial, 10, 7);
        moveMaquina moveDefensaSpc = new moveMaquina("Lava", funcionMoves.DefensaEspecial, 4,3);
        listaFuego.add(moveAtaque);
        listaFuego.add(moveDefensa);
        listaFuego.add(moveAtaqueSpc);
        listaFuego.add(moveDefensaSpc);
        return listaFuego;

    }

    public static List<moveMaquina> anadirMovesEnemigoHielo() {
        List<moveMaquina> listaHielo = new ArrayList<>();
        moveMaquina moveAtaque = new moveMaquina("Flecha Hielo", funcionMoves.Ataque, 6,0);
        moveMaquina moveDefensa = new moveMaquina("Ventisca", funcionMoves.Defensa,2, 0);
        moveMaquina moveAtaqueSpc = new moveMaquina("Rayo Helado", funcionMoves.AtaqueEspecial, 10, 7);
        moveMaquina moveDefensaSpc = new moveMaquina("Invierno Eterno", funcionMoves.DefensaEspecial, 4,3);
        listaHielo.add(moveAtaque);
        listaHielo.add(moveDefensa);
        listaHielo.add(moveAtaqueSpc);
        listaHielo.add(moveDefensaSpc);
        return listaHielo;
    }

    public static List<moveMaquina> anadirMovesEnemigoPlanta() {
        List<moveMaquina> listaPlanta = new ArrayList<>();
        moveMaquina moveAtaque = new moveMaquina("Latigo Cepa", funcionMoves.Ataque, 6,0);
        moveMaquina moveDefensa = new moveMaquina("Rayo Solar", funcionMoves.Defensa,2,0);
        moveMaquina moveAtaqueSpc = new moveMaquina("Tronco Aplastante", funcionMoves.AtaqueEspecial, 10, 7);
        moveMaquina moveDefensaSpc = new moveMaquina("Hiedra del Diablo", funcionMoves.DefensaEspecial, 4,3);
        listaPlanta.add(moveAtaque);
        listaPlanta.add(moveDefensa);
        listaPlanta.add(moveAtaqueSpc);
        listaPlanta.add(moveDefensaSpc);
        return listaPlanta;

    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////LISTA MOVIMIENTOS ENEMIGOS
}
