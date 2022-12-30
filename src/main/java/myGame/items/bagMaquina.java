package myGame.items;

import myGame.pokemon.pokemonMaquina;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class bagMaquina implements Serializable {

    private static final long serialVersionUID = -44374163084554132L;
    private List<pokemonMaquina> listaPokemonMaquina;

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////VARIABLES

    public bagMaquina(List<pokemonMaquina> listaPokemonMaquina) {
        if (listaPokemonMaquina.size() <= 10) {
            this.listaPokemonMaquina = listaPokemonMaquina;
        }
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////CONSTRUCTORES

    public List<pokemonMaquina> getListaPokemonMalo() {
        return listaPokemonMaquina;
    }

    public void setListaPokemonMalo(List<pokemonMaquina> listaPokemonMaquina) {
        this.listaPokemonMaquina = listaPokemonMaquina;
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////GETTER Y SETTER

    public static bagMaquina anadirPokegearTeamRocket() {
        List<pokemonMaquina> lista = new ArrayList<>();
        lista.add(pokemonMaquina.anadirArbok());
        lista.add(pokemonMaquina.anadirMeowth());
        lista.add(pokemonMaquina.anadirWeezing());
        return new bagMaquina(lista);
    }

    public static bagMaquina anadirPokegearSalvaje() {
        List<pokemonMaquina> lista = new ArrayList<>();
        lista.add(pokemonMaquina.anadirPidgeot());
        return new bagMaquina(lista);
    }

    public static bagMaquina anadirPokegearSalvaje2() {
        List<pokemonMaquina> lista = new ArrayList<>();
        lista.add(pokemonMaquina.anadirRattata());
        return new bagMaquina(lista);
    }

    public static bagMaquina anadirPokegearSalvaje3() {
        List<pokemonMaquina> lista = new ArrayList<>();
        lista.add(pokemonMaquina.anadirGolem());
        return new bagMaquina(lista);
    }

    public static bagMaquina anadirPokegearSalvaje4() {
        List<pokemonMaquina> lista = new ArrayList<>();
        lista.add(pokemonMaquina.anadirOnix());
        return new bagMaquina(lista);
    }

    public static bagMaquina anadirPokegearSalvaje5() {
        List<pokemonMaquina> lista = new ArrayList<>();
        lista.add(pokemonMaquina.anadirKrabby());
        return new bagMaquina(lista);
    }

    public static bagMaquina anadirPokegearSalvaje6() {
        List<pokemonMaquina> lista = new ArrayList<>();
        lista.add(pokemonMaquina.anadirVoltorb());
        return new bagMaquina(lista);
    }

    public static bagMaquina anadirPokegearAsh() {
        List<pokemonMaquina> lista = new ArrayList<>();
        lista.add(pokemonMaquina.anadirPikachu());
        lista.add(pokemonMaquina.anadirCharmander());
        lista.add(pokemonMaquina.anadirSquirtle());
        return new bagMaquina(lista);
    }

    public static bagMaquina anadirPokegearMisty() {
        List<pokemonMaquina> lista = new ArrayList<>();
        lista.add(pokemonMaquina.anadirEevee());
        lista.add(pokemonMaquina.anadirSeadra());
        lista.add(pokemonMaquina.anadirBlastoise());
        return new bagMaquina(lista);
    }

    public static bagMaquina anadirPokegearBrock() {
        List<pokemonMaquina> lista = new ArrayList<>();
        lista.add(pokemonMaquina.anadirBulbasur());
        lista.add(pokemonMaquina.anadirVoltorb());
        lista.add(pokemonMaquina.anadirTotodile());
        return new bagMaquina(lista);
    }

    public static bagMaquina anadirPokegearDawn() {
        List<pokemonMaquina> lista = new ArrayList<>();
        lista.add(pokemonMaquina.anadirSnorlax());
        lista.add(pokemonMaquina.anadirJynx());
        return new bagMaquina(lista);
    }

    public static bagMaquina anadirPokegearIris() {
        List<pokemonMaquina> lista = new ArrayList<>();
        lista.add(pokemonMaquina.anadirRattata());
        lista.add(pokemonMaquina.anadirExeggutor());
        return new bagMaquina(lista);
    }

    public static bagMaquina anadirPokegearSerena() {
        List<pokemonMaquina> lista = new ArrayList<>();
        lista.add(pokemonMaquina.anadirMagmar());
        lista.add(pokemonMaquina.anadirMagneton());
        return new bagMaquina(lista);
    }

    public static bagMaquina anadirPokegearMonje() {
        List<pokemonMaquina> lista = new ArrayList<>();
        lista.add(pokemonMaquina.anadirRaichu());
        lista.add(pokemonMaquina.anadirArticuno());
        return new bagMaquina(lista);
    }

    public static bagMaquina anadirPokegearOak() {
        List<pokemonMaquina> lista = new ArrayList<>();
        lista.add(pokemonMaquina.anadirMoltres());
        lista.add(pokemonMaquina.anadirCharizard());
        return new bagMaquina(lista);
    }

    public static bagMaquina anadirPokeagearRival(){
        List<pokemonMaquina> lista = new ArrayList<>();
        lista.add(pokemonMaquina.anadirBellsprout());
        lista.add(pokemonMaquina.anadirGlaceon());
        return new bagMaquina(lista);
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////POKEBALLS EEMIGOS


}
