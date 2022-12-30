package myGame.historia;

import myGame.errores.noHayEspacioException;
import myGame.game;
import myGame.personajes.enemigo;
import myGame.personajes.jugador;

import java.io.Serializable;
import java.util.List;

public interface historiaJuego extends Serializable {

    void pokemonRegaloDoctor(jugador user) throws noHayEspacioException;

    void encuentroPokemonSalvaje (jugador user, game game);

    void encuentroTeamRocket(jugador user, game game);

    void torneo (List<enemigo> list, game game, jugador jugador);

    void torneoEntrenadores(jugador user, game game);

    void encuentroMonje (jugador user, game game);

    void encuentroAmistosoOak (jugador user, game game);

    void combateCiudadano (jugador user, game game);







}
