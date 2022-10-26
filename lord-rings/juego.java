package lordOfTheRings;

import javafx.scene.control.TextArea;
import lordOfTheRings.misExcepciones.CharacterExistsException;
import lordOfTheRings.misUtiles.util;

import java.io.Serializable;
import java.util.LinkedList;

public class juego implements Serializable {

    /**
     * Lista de Heroes que forman el ejército
     * Lista de Bestias que forman el ejército
     * -
     * Lista de Backup de Heroes con el último ejército formado (PARA AHORRAR TIEMPO EN CREACIÓN DE FUTURAS BATALLAS).
     * Lista de Backup de Bestias con el último ejército formado (PARA AHORRAR TIEMPO EN CREACIÓN DE FUTURAS BATALLAS).
     */
    private LinkedList<heroe> listaHeroes = new LinkedList<>();
    private LinkedList<bestia> listaBestias = new LinkedList<>();
    /**
     * Variable TEXTO FINAL encargada de registrar todos los datos que se deben mostrar al usuario.
     * Variable LUCHA COMPLETADA que se encargará de activar la opción de 'VOLVER A LA MISMA BATALLA'
     * Variable EMPEZAR LUCHA que se encargará de activar la opción de 'EMPEZAR BATALLA'
     * Variable HEROE CAÍDO para registrar la muerte del heroe en cuestion
     * Variable BESTIA CAÍDA para registrar la muerte de la bestia en cuestion
     */

    private boolean empezarLucha = false;
    private boolean carga = false;
    private boolean botones_off = false;
    boolean heroesCaidos = false, bestiasCaidas = false;

    //GETTERS Y SETTERS NECESARIOS

    public LinkedList<heroe> getListaHeroes() {
        return listaHeroes;
    }

    public void setListaHeroes(LinkedList<heroe> listaHeroes) {
        this.listaHeroes = listaHeroes;
    }

    public LinkedList<bestia> getListaBestias() {
        return listaBestias;
    }

    public void setListaBestias(LinkedList<bestia> listaBestias) {
        this.listaBestias = listaBestias;
    }

    public boolean isEmpezarLucha() {
        return empezarLucha;
    }

    public void setEmpezarLucha(boolean empezarLucha) {
        this.empezarLucha = empezarLucha;
    }

    public boolean isCarga() {
        return carga;
    }

    public void setCarga(boolean carga) {
        this.carga = carga;
    }

    public boolean isBotones_off() {
        return botones_off;
    }

    public void setBotones_off(boolean botones_off) {
        this.botones_off = botones_off;
    }

    /**
     * Método ADD CHARACTER encargado de la adición de nuevos heroes y bestias a las listas
     *
     * @param name   Nombre del personaje
     * @param life   Vida del personaje
     * @param armour Armadura del personaje
     * @param type   Tipo de personaje
     * @throws CharacterExistsException Excepción informa de que el personaje puede existir en la lista
     */

    public void addCharacter(String name, int life, int armour, tipo type) throws CharacterExistsException {

        if (type.getTipoPersonaje().equalsIgnoreCase("heroe")) {
            heroe aux = new heroe(name, life, armour, type);

            if (listaHeroes.stream().filter(hero -> hero.equals(aux)).count() == 0) {
                listaHeroes.add(aux);
            } else throw new CharacterExistsException("Error: El personaje introducido ya existe");
        } else if (type.getTipoPersonaje().equalsIgnoreCase("bestia")) {
            bestia aux = new bestia(name, life, armour, type);
            if (listaBestias.stream().filter(bestia -> bestia.equals(aux)).count() == 0) {
                listaBestias.add(aux);
            } else throw new CharacterExistsException("Error: El personaje introducido ya existe");
        }
    }

    /**
     * Método HAY GANADOR para revisar en cada turno si algún ejército ha vencido al otro
     * Si a un ejercito no le quedan soldados con vida, este habrá sido vencido.
     *
     * @return Devuelve una cadena con el vencedor
     */

    public String hayGanador() {
        if (listaHeroes.stream().filter(heroe -> heroe.getVida() <= 0).count() == listaHeroes.size()) {
            return "bestias";
        } else if (listaBestias.stream().filter(bestia -> bestia.getVida() <= 0).count() == listaBestias.size()) {
            return "heroes";
        }
        return null;
    }

    /**
     * Método LUCHAR, el método que gobierna la lucha principal de ambos ejércitos
     *
     * @return Devuelve el texto acumulado durante la batalla para  mostrarlo en el controlador
     */

    public void lucha(TextArea text) {
        int turno = 0;                                            //VARIABLE QUE ALMACENA EL TURNO
        int contadorHeroes = listaHeroes.size();
        int contadorBestias = listaBestias.size();                //VARIABLES EMPLEADAS PARA MANTENER LA CUENTA DE PERSONAJES ACTUALIZADA AL IR SIENDO ELIMINADOS

        while (hayGanador() == null) {                            //MIENTRAS NO HAYA UN VENCEDOR . . .
            LinkedList<heroe> heroes = listaHeroes;                     //SE RECOGEN LAS LISTAS (CAPRICHO MÍO)
            LinkedList<bestia> bestias = listaBestias;
            turno++;                                              //SE AUMENTA EL TURNO Y SE IMPRIME
            text.appendText("Turno " + turno + "\n");
            if (heroesCaidos) {                                    //SI MUERE UN/OS HEROE/S EN EL TURNO ANTERIOR . . . MIENTRAS  HAYA HÉROES CAÍDOS . . .
                while (listaHeroes.stream().anyMatch(heroe -> heroe.getVida() <= 0)) {
                    heroe aux = dameHeroesCaídos();        //SE OBTIENE EL HEROE CAÍDO
                    heroes.remove(aux);                           //SE RETIRAN DE LA LISTA
                    contadorHeroes--;                             //DESCIENDE EL CONTADOR TAMAÑO DE LISTA
                }
                heroesCaidos = false;                                //CON EL EJERCITO SANEADO SE RESTAURA LA VARIABLE
            }
            if (bestiasCaidas) {                                   //SI MUERE UN/AS BESTIA/S EN EL TURNO ANTERIOR . . . MIENTRAS  HAYA BESTIAS CAÍDAS . . .
                while (listaBestias.stream().anyMatch(bestia -> bestia.getVida() <= 0)) {
                    bestia aux = dameBestiasCaidas();      //SE OBTIENE LA BESTIA CAÍDA
                    bestias.remove(aux);                          //SE RETIRAN DE LA LISTA
                    contadorBestias--;                            //DESCIENDE EL CONTADOR TAMAÑO DE LISTA
                }
                bestiasCaidas = false;                               //CON EL EJERCITO SANEADO SE RESTAURA LA VARIABLE
            }
            for (int i = 0; i < util.menor(contadorHeroes, contadorBestias); i++) {
                luchaIndividual(heroes.get(i), bestias.get(i), text);   //PARA EL MENOR VALOR DE LAS LISTAS, SE EJECUTAN BATALLAS POR PAREJAS, DESCARTANDO SOBRANTES
            }
        }

        if (hayGanador().equalsIgnoreCase("heroes")) {  //SI GANAN LOS HEROES . . . SE REGISTRA
            text.appendText("¡¡¡VICTORIA DE LOS HEROES!!!\n");
        } else {                                                   //SI GANAN LAS BESTIAS . . . SE REGISTRA
            text.appendText("¡¡¡VICTORIA DE LAS BESTIAS!!!\n");
        }
    }

    /**
     * Método LUCHA secundario, encargado de realizar la lucha individual entre un heroe y una bestia
     *
     * @param h Heroe
     * @param b Bestia
     */

    private void luchaIndividual(heroe h, bestia b, TextArea text) {
        text.appendText("Lucha entre " + h.getNombre() + " (Vida=" + h.getVida() + " Armadura=" + h.getArmadura() + ") y " + b.getNombre() + " (Vida=" + b.getVida() + " Armadura=" + b.getArmadura() + ")\n");
        h.ataque(b, text);                                               //HEROE ATACA A BESTIA
        if (b.getVida() > 0) {                                     //SI BESTIA VIVE . . . ATACA Y SE REGISTRA
            b.ataque(h, text);
        } else {                                                    //SI BETIA NO VIVE . . . SE REGISTRA Y SE GUARDA QUE HAY BESTIAS CAÍDAS
            text.appendText("¡Muere " + b.getNombre() + "!\n");
            bestiasCaidas = true;
        }

        if (h.getVida() <= 0) {                                     //SI HEROE MUERE EN EL ATAQUE . . . SE REGISTRA Y SE GUARDA QUE HAY HEROES CAIDOS
            text.appendText("¡Muere " + h.getNombre() + "!\n");
            heroesCaidos = true;
        }
    }

    /**
     * Método de COMPROBACIÓN encargado de identificar a los soldados caídos en los ejércitos correspondientes
     *
     * @return Devuelve los personajes que deben ser eliminados de los ejércitos
     */

    private heroe dameHeroesCaídos() {
        return listaHeroes.stream()
                .filter(heroe -> heroe.getVida() <= 0)
                .findFirst().get();
    }

    private bestia dameBestiasCaidas() {
        return listaBestias.stream()
                .filter(bestia -> bestia.getVida() <= 0)
                .findFirst().get();
    }

    /**
     * Método REINICIO aplicado en el reseteo de la BATALLA . . . Sea para cargar el ejército anterior, o empezar desde 0
     */

    void reiniciarListas() {
        listaHeroes.removeAll(listaHeroes);
        listaBestias.removeAll(listaBestias);
    }

    /**
     * Copia Profunda de los Ejércitos para así poder cargarlos en una partida con el ejército anteriormente formado
     *
     * @param list Se le pasará la lista que debe ser copiada
     * @return Devolverá la lista Copia
     */

}

