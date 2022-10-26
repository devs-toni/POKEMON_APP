package lordOfTheRings.misUtiles;

import java.io.File;

public class util {

    public static final String pathDir = System.getProperty("user.home") + File.separator + "savegames";
    public static final String pathFile = File.separator + "savegame.sg";

    /**
     * Método empleado para devolver el valor mayor
     *
     * @param a Número1
     * @param b Número2
     * @return Devolverá el valor mayor
     */

    public static int mayor(int a, int b) {
        if (a > b) return a;
        else if (a < b) return b;
        else return a;
    }

    /**
     * Método empleado para devolver el valor menor
     *
     * @param a Número1
     * @param b Número2
     * @return Devolverá el valor menor
     */

    public static int menor(int a, int b) {
        if (a > b) return b;
        else if (a < b) return a;
        else return a;
    }

    /**
     * Método empleado para evitar el muestreo de números negativos y mostrar 0
     *
     * @param valor Se le pasará DAÑO o VIDA.
     * @return Devolverá 0 o positivo
     */

    public static int valorAbsoluto(int valor) {
        if (valor <= 0) return 0;
        else return valor;
    }


}
