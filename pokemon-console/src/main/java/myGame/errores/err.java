package myGame.errores;

public enum err {
    NO_HAY_MOVIMIENTOS("No dispones de la energia necesaria para realizar este movimiento"),
    NO_HAY_ANTIDOTO("No te quedan antidotos"),
    NO_HAY_FRUTA("No te queda fruta curativa"),
    NO_HAY_POCIONES("No te quedan pociones"),
    NO_HAY_POKEBALL("No te quedan pokeballs"),
    NO_HAY_POKEMON_HERIDOS("No tienes ningun pokemon herido"),
    NO_HAY_SUFICIENTE_DINERO("No dispones del dinero suficiente para comprar"),
    NO_HAY_ESPACIO_PARA_POKEMON("Error: No tienes espacio para guardar más Pokémon"),

    POKEMON_SIN_VIDA("El pokemon elegido no tiene fuerzas para combatir"),
    POKEMON_AL_MAXIMO("El pokemon elegido ya tiene la vida al máximo"),
    POKEMON_YA_EXISTE("Ya tienes este pokemon"),
    POKEMON_NO_SALVAJE("El pokemon al que intentas capturar no es un pokemon Salvaje"),
    POKEMON_LLENO("Ya tienes todos los pokemon que se puede obtener"),
    POKEMON_VACIOS("No tienes pokemon"),

    ITEMS_LLENO("Tienes el inventario lleno"),
    ITEMS_VACIO ("Tienes el inventario vacío"),

    EXPERIENCIA_AL_MAXIMO ("Has alcanzado el máximo nivel de este pokemon"),

    DATO_INVALIDO("Introduce un valor válido");

    private String mensaje;

    err(String mensaje) {
        this.mensaje = mensaje;
    }

    public String toString() {
        return this.mensaje;
    }
}
