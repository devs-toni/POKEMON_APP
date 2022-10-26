package lordOfTheRings;

import java.io.Serializable;

public enum tipo implements Serializable {

    /**
     * Creación de los distintos tipos de heroes y bestias que contiene el juego
     */

    //HEROES

    Mago("Heroe", 20),
    Elfo("Heroe", 10),
    Enano("Heroe", 5),
    Humano("Heroe", 0),
    Hobbit("Heroe", -5),

    //BESTIAS
    Nazgul("Bestia", 15),
    Orco("Bestia", 0),
    Trol("Bestia", 8),
    Trasgo("Bestia", 0);

    /**
     * Con la variable TIPO PERSONAJE identificaremos si se trata de un héroe o de una bestia al rellenar el ComboBox
     * Con la variable RABIA se configurará la subida en la ofensiva contra los enemigos según el tipo de personaje
     */

    private String tipoPersonaje;
    private int rabia;

    //CONSTRUCTOR

    tipo(String tipoPersonaje, int rabia) {
        this.tipoPersonaje = tipoPersonaje;
        this.rabia = rabia;

    }

    //GETTERS Y SETTERS NECESARIOS

    public String getTipoPersonaje() {
        return tipoPersonaje;
    }

    public int getRabia() {
        return rabia;
    }

}
