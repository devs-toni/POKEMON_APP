package lordOfTheRings;

import javafx.scene.control.TextArea;
import lordOfTheRings.misUtiles.util;

import java.util.Random;

public final class bestia extends personaje //implements Cloneable {
{
        /**
     * Variable TIPO donde almacenaremos el tipo de bestia que vamos a crear
     * Variable DADO donde se almacenará el valor aleatorio para el ataque
     */


    private int dado = 0;
    //CONSTRUCTOR
    public bestia(String nombre, int vida, int armadura, tipo tipoBestia) {
        super(nombre, vida, armadura, tipoBestia);
    }
    //MÉTODO TOSTRING PARA EL CORRECTO MUESTREO DE LA BESTIA EN PANTALLA

    public String toString() {
        return getNombre() + " - " + getType() + " (" + util.valorAbsoluto(getVida()) + "," + getArmadura() + ")";
    }

    //MÉTODO EQUALS PARA LA CORRECTA COMPROBACIÓN DE OBJETOS IDÉNTICOS (TODOS LOS CAMPOS)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        bestia bestia = (bestia) o;

        return super.getNombre().equalsIgnoreCase(bestia.getNombre()) && getType() == bestia.getType();
    }

    /**
     * Esta clase implementa CLONEABLE del cual se implementa el método CLONE . . .
     * Se va a emplear para la copia profunda de la lista.
     *
     * @return Devolverá el objeto copia.
     */

//    @Override
//    protected Object clone() {
//        bestia b = null;
//        try {
//            b = (bestia) super.clone();
//        } catch (CloneNotSupportedException e) {
//            e.printStackTrace();
//        }
//        return b;
//    }

    /**
     * Método ATAQUE heredado de PERSONAJE, encargado de realizar el ataque
     * LA LOGICA DEL ATAQUE EMPLEADO EN UN SENCILLO JUEGO DE ESTAS CARACTERÍSTICAS ES -> Primero ataca Heroe | Segundo ataca Bestia
     * NO LE VEO SENTIDO A APLICAR UNA LÓGICA DE ATAQUE A LA PAR, AL NO HABER INTERFAZ DONDE VISUALIZAR EL CHOQUE DE ESPADAS. . .
     *
     * @param h Se le pasará el heroe al que se atacará, siendo posteriormente casteado
     */

    @Override
    public <T extends personaje> void ataque(T h, TextArea text) {
        int armadura = h.getArmadura();                                //SE GUARDA LA ARMADURA DEL HÉROE
        setPotencia_ataque(calculoAtaque(h) - h.getArmadura());        //SE ESTABLECE LA POTENCIA DEL ATAQUE  - AL NUMERO OBTENIDO MENOS LA ARMADURA DEL RIVAL -
        if (getPotencia_ataque() > 0) {
            h.setVida(h.getVida() - getPotencia_ataque());                 //SE LE RESTA LA VIDA AL HEROE RIVAL
        }                                                               //SE REGISTRA EL ATAQUE
        text.appendText(getNombre() + " saca " + dado + " y le quita " + util.valorAbsoluto(getPotencia_ataque()) + " de vida a " + h.getNombre() + "\n");
        if (h.getArmadura() < armadura) {                                //SI ES UN ATAQUE DE ORCO, COMO LA ARMADURA DEL HEROE HA BAJADO . . . ESTA SE RECUPERA
            h.setArmadura(armadura);
        }
    }

    /**
     * Método CALCULO heredado de PERSONAJE, encargado de realizar el calculo del valor de ofensiva
     *
     * @param h Se le pasará el personaje al que se atacará, siendo posteriormente casteado
     * @return Devolvera el valor de la ofensiva
     */

    @Override
    public <T extends personaje> int calculoAtaque(T h) {
        Random random = new Random();                                                                       //SE CREA LA VARIABLE RANDOM
        dado = random.nextInt(90);                                                                    //SE TIRA EL DADO
        //System.out.println("dado bestia - " + dado);

        if (getType().name().equals("Orco")) {                                                             //SI SE TRATA DE UN ORCO . . . SE BAJA LA ARMADURA DEL HEROE UN 10%
            double valor = h.getArmadura() * 0.10;
            //System.out.println("Orco - -10% armaduraAhora - " + h.getArmadura());
            h.setArmadura((int) (h.getArmadura() - valor));
            //System.out.println("Orco - -10% armaduraDespues - " + h.getArmadura());


        } else if (getType().name().equals("Trol") && h.getType().name().equals("Humano")) {          //SI SE TRATA DE UN TROL CONTRA UN HUMANO . . . ESTE SE ENFURECERA Y AUMENTARA EN UN NÚMERO ENTRE 0 Y 8
            int aleatorio = (int) (Math.random() * getType().getRabia());
            dado += aleatorio;
            //System.out.println("Trol vs Humano - (0 y 8) " + dado + " añadido un " + aleatorio);

        } else if (getType().name().equals("Nazgul")) {                                                    //SI SE TRATA DE UN NAZGUL . . . SU RABIA HARA UN DAÑO DE +15
            dado += getType().getRabia();
            //System.out.println("Nazgul - +10 " + dado);

        }
        return dado;                                                                                        //SE DEVUELVE EL DAÑO OCASIONADO PARA ATACAR
    }

}
