package myGame.pokemon;

import myGame.lucha.type;

import java.io.Serializable;
import java.util.Objects;

public abstract class pokemon implements Serializable {

    private static final long serialVersionUID = 4936544280314978050L;
    protected String nombre;
    protected int nivel;
    protected int vida;
    protected type tipo;
    protected int concentracion;
    protected boolean envenenado;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////VARIABLES

    //CONSTRUCTOR PARA EL POKEMON JUGADOR, CONSTRUIDO A PARTIR DE UNA EXPERIENCE ACUMULADA Y EN CONTINUO PROGRESO
    public pokemon(String nombre, int vida, type tipo) {
        this.nombre = nombre;
        this.vida = vida;
        this.tipo = tipo;
        envenenado = false;
    }

    //CONSTRUCTOR PARA EL POKEMON ENEMIGO, CONSTRUIDOS PUNTUALMENTE A PARTIR DE UN NIVEL EN CONCRETO
    public pokemon (String nombre, int vida, int nivel, type tipo){
        this.nombre = nombre;
        this.vida = vida;
        this.nivel = nivel;
        this.tipo = tipo;
        envenenado = false;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////CONSTRUCTORES

    public pokemon(){}

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public type getTipo() {
        return tipo;
    }

    public void setTipo(type tipo) {
        this.tipo = tipo;
    }

    public int getConcentracion() {
        return concentracion;
    }

    public void setConcentracion(int concentracion) {
        this.concentracion = concentracion;
    }

    public boolean getEnvenenado() {
        return envenenado;
    }

    public void setEnvenenado(boolean envenenado) {
        this.envenenado = envenenado;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////GETTER Y SETTER

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        pokemon pokemon = (pokemon) o;
        return Objects.equals(nombre, pokemon.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre);
    }
}
