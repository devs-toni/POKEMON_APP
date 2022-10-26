package lordOfTheRings;

import lordOfTheRings.misUtiles.util;
import javafx.scene.control.TextArea;

import java.util.Random;

public final class heroe extends personaje //implements Cloneable {
{

    /**
     * Variable TIPO donde almacenaremos el tipo de heroe que vamos a crear
     * Variable DADO1 y DADO2 donde se almacenaran los valores aleatorios para el ataque
     */

    int dado1 = 0, dado2 = 0;

    //CONSTRUCTOR

    public heroe(String nombre, int vida, int armadura, tipo tipoHeroe) {
        super(nombre, vida, armadura, tipoHeroe);
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

        heroe heroe = (heroe) o;

        return super.getNombre().equalsIgnoreCase(heroe.getNombre()) && getType() == heroe.getType() && super.getVida() == heroe.getVida() && super.getArmadura() == heroe.getArmadura();
    }

    /**
     /**
     * Esta clase implementa CLONEABLE del cual se implementa el método CLONE . . .
     * Se va a emplear para la copia profunda de la lista.
     *
     * @return Devolverá el objeto copia.
     */

//    @Override
//    protected Object clone() {
//        heroe h = null;
//        try {
//            h = (heroe) super.clone();
//       } catch (CloneNotSupportedException e) {
//            e.printStackTrace();
//        }
//        return h;
//    }

    /**
     * Método ATAQUE heredado de PERSONAJE, encargado de realizar el ataque
     * LA LOGICA DEL ATAQUE EMPLEADO EN UN SENCILLO JUEGO DE ESTAS CARACTERÍSTICAS ES -> Primero ataca Heroe | Segundo ataca Bestia
     * NO LE VEO SENTIDO A APLICAR UNA LÓGICA DE ATAQUE A LA PAR, AL NO HABER INTERFAZ DONDE VISUALIZAR EL CHOQUE DE ESPADAS. . .
     *
     * @return Devolvera un booleano verificando la caída de la bestia en combate
     */

    /**
     * Método ATAQUE heredado de PERSONAJE, encargado de realizar el ataque
     * LA LOGICA DEL ATAQUE EMPLEADO EN UN SENCILLO JUEGO DE ESTAS CARACTERÍSTICAS ES -> Primero ataca Heroe | Segundo ataca Bestia
     * NO LE VEO SENTIDO A APLICAR UNA LÓGICA DE ATAQUE A LA PAR, AL NO HABER INTERFAZ DONDE VISUALIZAR EL CHOQUE DE ESPADAS. . .
     *
     * @param b Se le pasará la bestia a la que se atacará, siendo posteriormente casteado
     * @return Devolvera un booleano verificando la caída de la bestia en combate
     */

    @Override
    public <T extends personaje> void ataque(T b, TextArea text) {
        int ofensiva = calculoAtaque(b);                              //SE RECOJE EL VALOR ALEATORIO 'OFENSIVA'
        int danyo = ofensiva - b.getArmadura();                       //SE LE RESTA LA PROTECCIÓN DE LA ARMADURA
        if (danyo > 0) {
            b.setVida(b.getVida() - danyo);                               //SE LE RESTA LA VIDA A LA BESTIA
        }
        //SE REGISTRA EL ATAQUE
        text.appendText(getNombre() + " saca " + ofensiva + " y le quita " + util.valorAbsoluto(danyo) + " de vida a " + b.getNombre() + "\n");
    }

    /**
     * Método CALCULO heredado de PERSONAJE, encargado de realizar el calculo del valor de ofensiva
     *
     * @param b Se le pasará la personaje a la que se atacará, siendo posteriormente casteado
     * @return Devolvera el valor de la ofensiva
     */

    @Override
    public <T extends personaje> int calculoAtaque(T b) {
        Random random = new Random();                                                               //SE CREA LA VARIABLE ALEATORIO
        dado1 = random.nextInt(100);                                                          //SE LANZAN 2 DADOS Y SE ESCOGE EL MAYOR
        dado2 = random.nextInt(100);
        setPotencia_ataque(util.mayor(dado1, dado2));
        //System.out.println("dado1 - " + dado1);
        //System.out.println("dado2 - " + dado2);
        //System.out.println("dado final - " + getPotencia_ataque());

        if (getType().name().equals("Elfo") && b.getType().name().equals("Orco")) {           //SI SE TRATA DE ELFO CONTRA ORCO . . . +10
            setPotencia_ataque(getPotencia_ataque() + getType().getRabia());
            //System.out.println("Elfo vs Orco -  +10 - " + getPotencia_ataque());


            //SI SE TRATA DE HOBBIT CONTRA TRASGO O CONTRA NASGUL . . . -5
        } else if (getType().name().equals("Hobbit") && (b.getType().name().equals("Trasgo") || b.getType().name().equals("Nazgul"))) {
            setPotencia_ataque(getPotencia_ataque() + getType().getRabia());
            //System.out.println("Hobbit vs Trasgo o nasgul - -5 - " + getPotencia_ataque());

            //SI SE TRATA DE ENANO CONTRA TRASGO . . . +5
        } else if (getType().name().equals("Enano") && b.getType().name().equals("Trasgo")) {
            setPotencia_ataque(getPotencia_ataque() + getType().getRabia());
            //System.out.println("Enano vs Trasgo -  +5 - " + getPotencia_ataque());

            //SI SE TRATA DE UN MAGO . . . +20
        } else if (getType().name().equals("Mago")) {
            setPotencia_ataque(getPotencia_ataque() + getType().getRabia());
            //System.out.println("Mago - +20 - " + getPotencia_ataque());
        }
        return getPotencia_ataque();                                                                //SE DEVUELVE EL ATAQUE
    }
}
