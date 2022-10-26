package myGame.items;

import java.io.Serializable;
import java.util.HashSet;

import myGame.errores.datoInvalidoException;
import myGame.errores.err;
import myGame.utiles.util;

public class smartphone implements Serializable {

    private static final long serialVersionUID = 6177564128279513986L;
    public HashSet<String> contactos;

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////VARIABLES

    public smartphone(HashSet<String> contactos) {
        this.contactos = contactos;
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////CONSTRUCTORES

    public HashSet<String> getContactos() {
        return contactos;
    }

    public void setContactos(HashSet<String> contactos) {
        this.contactos = contactos;
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////GETTER Y SETTER

    /**
     * Función que permite consultar el móvil y seleccionar contactos
     */
    public String consultMobile() {
        String opcion = "";
        String llamada = "";
        int i = 1;

        do {
            contactos.forEach(System.out::println);
            System.out.println();

            try {
                System.out.println("Selecciona un contacto para llamar.");
                opcion = util.introducirTextoObligatorio("0. Atras");
                if (opcion.equals(String.valueOf(0))) return null;

                for (String s : contactos) {
                    if (s.equalsIgnoreCase(opcion)) {
                        return s;
                    } else if (i == contactos.size()) throw new datoInvalidoException(err.DATO_INVALIDO.toString());
                    i++;
                }

            } catch (datoInvalidoException e) {
                e.printStackTrace();
            }
        } while (true);
    }

    /**
     * Método que devuelve un movil ya construido
     *
     * @return Movil
     */
    public static smartphone anadirMovil() {
        HashSet<String> contactos = new HashSet<>();
        return new smartphone(contactos);
    }

    /**
     * Método empleado para realizar llamadas y tener conversaciones con personajes
     *
     * @param nombre
     */
    public static void llamar(String nombre) {
        System.out.println("Hola " + nombre + ", como està hoy");
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////FUNCIONES UTILES

}
