package myGame.lucha;

import java.io.Serializable;

public abstract class movimiento implements Serializable {

    private static final long serialVersionUID = 5522145569763319302L;
    private String nombre;
    private funcionMoves funcion;
    private int dolor;
    private int nivelAcceso;

    ////////////////////////////////////////////////////////////////////////////////////////VARIABLES

    public movimiento(String nombre, funcionMoves funcion, int dolor, int nivelAcceso) {
        this.nombre = nombre;
        this.funcion = funcion;
        this.dolor = dolor;
        this.nivelAcceso = nivelAcceso;
    }

    public movimiento(String nombre, funcionMoves funcion, int dolor) {
        this.nombre = nombre;
        this.funcion = funcion;
        this.dolor = dolor;
    }

    public movimiento(String nombre, funcionMoves funcion) {
        this.nombre= nombre;
        this.funcion = funcion;
    }

    public movimiento(){}

    ////////////////////////////////////////////////////////////////////////////////////////CONSTRUCTORES

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public funcionMoves getFuncion() {
        return funcion;
    }

    public void setFuncion(funcionMoves funcion) {
        this.funcion = funcion;
    }

    public int getDolor() {
        return dolor;
    }

    public void setDolor(int dolor) {
        this.dolor = dolor;
    }

    public int getNivelAcceso() {
        return nivelAcceso;
    }

    public void setNivelAcceso(int nivelAcceso) {
        this.nivelAcceso = nivelAcceso;
    }

    ////////////////////////////////////////////////////////////////////////////////////////GETTER Y SETTER

}
