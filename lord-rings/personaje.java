package lordOfTheRings;

import javafx.scene.control.TextArea;

import java.io.Serializable;

/**
 * Creación del personaje base . . .
 * Contrucción con nombre, vida y armadura
 * -
 * Variable POTENCIA_ATAQUE compartida entre heroes y bestias -> Calculo del daño a realizar en el ataque
 * Variable IMPRIMIR compartida entre heroes y bestias -> Registra los sucesos para mostrarlos en pantalla en el controlador
 */

public abstract class personaje implements Serializable {


    private String nombre;
    private int vida;
    private int armadura;
    private tipo type;

    private int potencia_ataque;

    public personaje(String nombre, int vida, int armadura, tipo type) {
        this.nombre = nombre;
        this.vida = vida;
        this.armadura = armadura;
        this.type = type;
        potencia_ataque = 0;
    }

    /**
     * Getters y Setters requeridos
     */

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public int getArmadura() {
        return armadura;
    }

    public void setArmadura(int armadura) {
        this.armadura = armadura;
    }

    public tipo getType() {
        return type;
    }

    public void setType(tipo type) {
        this.type = type;
    }

    public int getPotencia_ataque() {
        return potencia_ataque;
    }

    public void setPotencia_ataque(int potencia_ataque) {
        this.potencia_ataque = potencia_ataque;
    }

    /**
     * Método de ATAQUE donde se  realiza la acción de atacar.
     * @param personaje Se le pasará un heroe o una bestia
     */

    public abstract <T extends personaje> void ataque (T personaje, TextArea text);

    /**
     * Método de CALCULO donde se calcula el valor de la ofensiva
     * @param personaje Se le pasará un heroe o una bestia
     * @return Devolverá un entero con el valor de la ofensiva aleatorio
     */

    public abstract <T extends personaje> int calculoAtaque(T personaje);

}

