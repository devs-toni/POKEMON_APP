package lordOfTheRings.misExcepciones;

public class CharacterExistsException extends Exception {

    /**
     * Excepción empleada para notificar que ya existe un personaje idéntico en la correspondiente lista
     * @param message Se le pasará el mensaje con la notificación de error
     */

    public CharacterExistsException(String message) {
        super(message);
    }

}
