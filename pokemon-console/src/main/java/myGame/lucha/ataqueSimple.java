package myGame.lucha;

import java.io.Serializable;

public class ataqueSimple  implements Serializable {

    private static final long serialVersionUID = 4365345708991047003L;
    private String nombre;
    private type tipoAtaque;
    private int dano;

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////VARIABLES

    public ataqueSimple(String nombre, type tipoAtaque, int dano) {
        this.nombre = nombre;
        this.tipoAtaque = tipoAtaque;
        this.dano = dano;
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////CONSTRUCTORES

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public type getTipoAtaque() {
        return tipoAtaque;
    }

    public void setTipoAtaque(type tipoAtaque) {
        this.tipoAtaque = tipoAtaque;
    }

    public int getDano() {
        return dano;
    }

    public void setDano(int dano) {
        this.dano = dano;
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////GETTER Y SETTER

    public static ataqueSimple ataqueSimpleAgua() {
        return new ataqueSimple("Cascada", type.AGUA, 35);
    }

    public static ataqueSimple ataqueSimpleElectrico() {
        return new ataqueSimple("Impactrueno", type.ELECTRICO, 35);
    }

    public static ataqueSimple ataqueSimpleFuego() {
        return new ataqueSimple("Girofuego", type.FUEGO, 35);
    }

    public static ataqueSimple ataqueSimpleHielo() {
        return new ataqueSimple("Helada", type.HIELO, 30);
    }

    public static ataqueSimple ataqueSimplePlanta() {
        return new ataqueSimple("Latigazo", type.PLANTA, 40);
    }

    public static ataqueSimple ataqueSimpleNormal(){
        return new ataqueSimple("Golpe Cuerpo" , type.NORMAL, 35);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////ATAQUES SIMPLES POKEMON JUGADOR

}
