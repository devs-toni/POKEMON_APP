package myGame;

import static myGame.items.bagJugador.anadirObjeto;

import java.io.Serializable;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

import myGame.errores.datoInvalidoException;
import myGame.errores.err;
import myGame.errores.experienciaAlMaximoException;
import myGame.errores.itemLlenoException;
import myGame.errores.noHayEspacioException;
import myGame.errores.noHayPocionesException;
import myGame.errores.pokemonExisteException;
import myGame.items.bagJugador;
import myGame.items.tiendaPokemon;
import myGame.lucha.ataqueSimple;
import myGame.lucha.moveJugador;
import myGame.personajes.enemigo;
import myGame.personajes.jugador;
import myGame.personajes.personaje;
import myGame.pokemon.pokemonJugador;
import myGame.pokemon.pokemonMaquina;
import myGame.utiles.util;

public class game implements Serializable {


    private static final long serialVersionUID = 4461088431040369786L;
    private jugador user;
    private enemigo maquina;
    private final List<jugador> listaJugadores;
    private tiendaPokemon tienda;
    private hisoriaPrincipal historia;

    ////////////////////////////////////////////////////////////////////////////////////////////VARIABLES

    public game(List<jugador> listaJugadores) {
        this.listaJugadores = listaJugadores;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////CONSTRUCTORES

    public jugador getUser() {
        return user;
    }

    public void setUser(jugador user) {
        this.user = user;
    }

    public void setMaquina(enemigo maquina) {
        this.maquina = maquina;
    }

    public List<jugador> getListaJugadores() {
        return listaJugadores;
    }

    public tiendaPokemon getTienda() {
        return tienda;
    }

    public void setTienda(tiendaPokemon tienda) {
        this.tienda = tienda;
    }

    public void setHistoria(hisoriaPrincipal historia) {
        this.historia = historia;
    }

    public hisoriaPrincipal getHistoria() {
        return historia;
    }
///////////////////////////////////////////////////////////////////////////////////////////GETTERS Y SETTERS

    /**
     * Se trata del primer método empleado en el juego, se debe escoger al personaje que se va a utilizar
     *
     * @return
     */

    public jugador elegirPersonaje() {
        boolean datoValido = false;
        jugador player = new jugador();

        while (!datoValido) {
            try {
                getListaJugadores().forEach(jugador -> {
                    int n = getListaJugadores().indexOf(jugador) + 1;
                    System.out.println(n + ". " + jugador.getNombre());
                });
                int eleccion = util.introducirNumeroObligatorio("Que entrenador pokemon quieres utilizar?");

                switch (eleccion) {
                    case 1:
                        datoValido = true;
                        player = jugador.anadirPersonajes().get(0);
                        System.out.println("Has elegido a Ash!");
                        break;
                    case 2:
                        datoValido = true;
                        player = jugador.anadirPersonajes().get(1);
                        System.out.println("Has elegido a Misty!");
                        break;
                    case 3:
                        datoValido = true;
                        player = jugador.anadirPersonajes().get(2);
                        System.out.println("Has elegido a Brock!");
                        break;
                }
                if (eleccion < 1 || eleccion > getListaJugadores().size()) {
                    throw new datoInvalidoException(err.DATO_INVALIDO.toString());
                }

            } catch (datoInvalidoException e) {
                e.printStackTrace();
            }
        }
        return player;
    }

    /**
     * Método empleado para realizar un torneo a partir de una lista de oponentes
     *
     * @param mpList
     */

    public void combateTorneo(List<enemigo> mpList) {
        int rounds = 0;
        int turn = 1;
        for (enemigo e : mpList) {
            setMaquina(e);
            System.out.println("Combate " + turn + "!\n" + user.getNombre() + " lucha contra: " + e.getNombre());
            user.setUltimoPokemon(luchar(e, user));
            if (hayGanador(e) == e) {
                System.out.println("Has perdido el torneo.");
                e.getPokeballs().getListaPokemonMalo().forEach(pokemonMaquina -> pokemonMaquina.setVida(100));
                break;
            }
            rounds++;
            turn++;
        }
        if (rounds == mpList.size()) {
            pokemonJugador pokemonPresent = regaloTorneo(pokemonJugador.anadirPokemonRegaloTorneo());

            try {
                anadirObjeto(user.getItems().getPocion(), 3, false);
            } catch (itemLlenoException e) {
                e.printStackTrace();
            }

            try {
                user.anadirPokemon(pokemonPresent);
            } catch (pokemonExisteException | noHayEspacioException e) {
                e.printStackTrace();
            }
            System.out.println("Has ganado el torneo! Enhorabuena!");
            System.out.println("Recibes 3 medicinas.");
            System.out.println("Has ganado el pokemon " + pokemonPresent.getNombre());
        }
        System.out.println();
    }

    /**
     * Método empleado para obtener un pokemon de regalo en el torneo escogido de entre un Set, de forma aleatoria
     *
     * @param list
     * @return
     */

    public pokemonJugador regaloTorneo(Set<pokemonJugador> list) {
        int intPresent = -1;
        int contador = 0;
        Random random = new Random();

        pokemonJugador pokemonPresent = new pokemonJugador();

        list = list.stream()
                .filter(pokemon -> user.getItems().getMisPokemon().stream()
                        .noneMatch(pok -> pok.getNombre().equalsIgnoreCase(pokemon.getNombre())))
                .collect(Collectors.toSet());

        intPresent = random.nextInt(list.size() - 1);
        for (pokemonJugador p : list) {
            if (contador == intPresent) {
                pokemonPresent = p;
            }
            contador++;
        }

        return pokemonPresent;
    }

////////////////////////////////////////////////////////////////////////////////////////////HISTORIA PRINCIPAL

    /**
     * Método de entrada por consola para la selección de una opcion en el menú de ataque
     *
     * @return el valor de la opcion elegida
     */

    public int menuAtaque() {
        System.out.println("1. Luchar\n2. Pokémon\n3. Items\n4. Correr");
        return util.introducirNumeroObligatorio("Que quieres hacer?");
    }

    /**
     * Principal método del juego que va a controlar la lucha entre un jugador y un enemigo
     *
     * @param enemigo
     * @param jugador
     * @return Ultimo pokemon empleado en batalla
     */

    public pokemonJugador luchar(enemigo enemigo, jugador jugador) {
        Random random = new Random();
        /**
         * Variable TURNO controla los turnos jugador-maquina
         * Variable MENUATAQUE controla la entrada por consola de la eleccion del jugador al menu principal de Ataque
         * Variable VERIFICARATAQUE controla la elección de ataque del jugador, si vuelve atrás será nulo, y si es un ataque venenoso se guardara en las variables posteriores
         * Variable HUIDA controla que user quiera huir del combate para así romper el bucle Ganador()
         * Variable CAPTURADO controla que el pokemon sea Capturado para así romper el bucle Ganador() ya que no lo hay
         * Variable MAQUINAENVENENADO controla que el tipo de ataque jugador sea de envenenamiento y así poder quitar un poco de vida a la maquina con cada turno
         * Variable JUGADORENVENENADO controla que el tipo de ataque maquina sea de envenenamiento y así poder quitar un poco de vida al jugador con cada turno
         * Variable ATRASMENU controla la salida del bucle del Menu de Ataque que se le ofrece al usuario
         * Variable ELEGIDO controla //TODO
         * Variable POKENEMY guarda el pokemon del enemigo
         */
        int turno = 1, menuAtaque = -1;
        String hayAtaque = "";
        boolean huida = false, capturado = false;
        boolean maquinaEnvenenado = false, jugadorEnvenenado = false;
        boolean atrasMenu = false, elegido = false;
        pokemonMaquina pokEnemy = enemigo.elegirPokemonMaquina();
        pokemonJugador pokUser = jugador.getUltimoPokemon();

        /**
         * Mientras no haya un ganador...
         * Si el pokemon que está combatiendo es derrotado, se notifica y se solicita escoger pokemon de nuevo
         *
         * La variable derrotado se va a emplear para la posibilidad de volver atras...
         * En caso de que el pokemon siga vivo se podrá volver atrás y seguir con el actual, pero si ha sido derrotado,
         * no se puede volver atrás, hay que elegir obligatoriamente uno para luchar
         *
         * Se recogen los datos del pokemon jugador y del pokemon enemigo para tener una mínima interfaz gráfica de como procede la batalla
         *
         * Si la concentración del pokemon maquina esta alterada, se restaura poco a poco...
         */

        while (hayGanador(enemigo) == null) {

            if (!pokUser.pokemonVivo()) {
                System.out.println(pokUser.getNombre() + " ha sido derrotado!");
                pokUser = user.elegirPokemonJugador(true);
            }

            if (pokEnemy.getConcentracion() < 100) {
                pokEnemy.setConcentracion(pokEnemy.getConcentracion() + ((pokEnemy.getConcentracion() * 20) / 100));
            }


            /**
             * CAMBIO DE TURNO ----------------------------------------------------------------------------------------------------------
             * Ataca el jugador.
             *
             * atrasMenu se lleva a false para mostrar el menú de ataque del usuario siempre que sea su turno
             *
             * La vida se recoge en valor absoluto, no interesan valores negativos.
             *
             * Mientras no se necesite salir del menúAtaque...se muestrea y
             * se recoge la respuesta del usuario.
             *
             * Si la respuesta es erronea se lanza excepcion de dato invalido.
             */

            System.out.println("\nTurno " + turno + " -> " + pokUser.getNombre());
            atrasMenu = false;
            while (!atrasMenu) {
                try {
                    menuAtaque = menuAtaque();

                    if (menuAtaque < 1 || menuAtaque > 4) {
                        throw new datoInvalidoException(err.DATO_INVALIDO.toString());
                    }

                    /**
                     * - Si la respuesta elegida es luchar, se controlara:
                     * 1- Si se ataca normal se rompera con el menu de ataque y se cambia de turno
                     * 2- Si no se elige ataque y se vuelve atras, entonces se continua en el menu ataque
                     * 3- Si el ataque enviado debe envenenar al contrario en MaquinaEnvenenado y cambiamos de turno saliendo del menu
                     *
                     * - Si la respuesta elegida es cambiar de Pokemon, la variable elegido nos ayuda a elegirlo
                     *
                     * Mientras no se haya elegido al pokemon, se pregunta al usuario.
                     * La variable derrotado se va a emplear para la posibilidad de volver atras, es decir, si el pokemon ha sido derrotado,
                     * no puedes volver atrás, ya que antes debes elegir pokemon, mientras que si no ha sido derrotado, puedes volver atrás para
                     * elegir otra opción.
                     *
                     * Si el pokemon elegido ya esta en el campo de batalla se le notifica al usuario y no se admite la eleccion,
                     * por lo que no se sale del bucle
                     * Si es nulo, significa que se ha vuelto atrás, por lo que se sale de la elección y se continua en el menú de ataque.
                     * Si se cambia de pokemon se asigna el pokemon Auxiliar, al pokemon del usuario dando por concluido el turno.
                     */

                    if (menuAtaque == 1) {
                        hayAtaque = pokUser.ataqueJugador(pokEnemy);
                        if (hayAtaque == null) {
                            break;
                        } else if (hayAtaque.equalsIgnoreCase("atras")) {
                            continue;
                        } else if (hayAtaque.equalsIgnoreCase("veneno")) {
                            maquinaEnvenenado = true;
                            break;
                        }

                    } else if (menuAtaque == 2) {
                        while (true) {
                            pokemonJugador cambio = user.elegirPokemonJugador(false);

                            try {
                                if (pokUser.equals(cambio)) {
                                    throw new pokemonExisteException(err.POKEMON_YA_EXISTE.toString());
                                } else if (cambio == null) { //Si vuelves atras, volvemos al menu principal
                                    break;

                                } else { //Si cambias de Pokemon, lo seleccionas
                                    pokUser = cambio;
                                    System.out.println("Luchas con " + cambio.getNombre());
                                    System.out.println("Has empleado tu turno");
                                    atrasMenu = true;
                                    break;
                                }
                            } catch (pokemonExisteException e) {
                                e.printStackTrace();
                            }
                        }

                        /**
                         * - La opcion elegida es acceder a la mochila de la lucha!
                         * No se debe confundir con la mochila del menú Start, su funcionamiento es diferente.
                         *
                         * De esta mochila se van a recoger diferentes estados...
                         * - CAPTURADO en el caso de que se use una pokeball al tratarse de un pokemon salvaje, si lo capturas se sale de la batalla
                         * - FUGADO si se escapa se pierde el turno
                         * - CURADO en el caso de que se emplee una pocion para curar a tu pokemon (Se pierde el turno)
                         * - si se recoge un nulo se ha seleccionodo volver atrás por lo que seguira en el menú de ataque
                         *
                         * - La opción elegida es Huir, por lo que se rompe el bucle y se guarda la variable huida para romper el bucle Ganador()
                         */

                    } else if (menuAtaque == 3) {
                        bagJugador.estado estadoPokemon = user.getItems().abrirMochilaLucha(user, pokEnemy);
                        if (estadoPokemon == null) {
                            continue;
                        } else if (estadoPokemon.equals(bagJugador.estado.CAPTURADO)) {
                            capturado = true;
                            break;
                        } else if (estadoPokemon.equals(bagJugador.estado.CURADO)) {
                            break;
                        } else if (estadoPokemon.equals(bagJugador.estado.CURAVENENO)) {
                            jugadorEnvenenado = false;
                            break;
                        } else if (estadoPokemon.equals(bagJugador.estado.FUGADO)) {
                            break;
                        }

                    } else {
                        huida = true;
                        break;
                    }

                    /**
                     * Se recoge la excepcion de dato invalido, volviendo así al menu Ataque
                     * hasta que se introduzca un dato válido
                     */
                } catch (datoInvalidoException e) {
                    e.printStackTrace();
                }
            }

            /**
             * MENU DEL JUGADOR TERMINADO ------------------------------------------------
             * opciones intermedias
             * 1- Si se ha huido, se sale de la batalla
             * 2- Si se ha capturado satisfactoriamente un pokemon salvaje, se sale de la batalla
             * 3- Si el jugador ha sido envenenado, se le quita algo vida en cada correspondiente turno
             * 4- Si la concentración del pokemon esta alterada por un ataque defensivo en el turno anterior, se sube con el paso de los turnos.
             */
            if (huida) break;
            if (capturado) break;

            if (pokUser.getConcentracion() < 100) {
                pokUser.setConcentracion(pokUser.getConcentracion() + ((pokUser.getConcentracion() * 40) / 100));
            }

            if (jugadorEnvenenado) {
                pokUser.setVida(pokUser.getVida() - 1);
            }


            /**
             * CAMBIO DE TURNO -----------------------------------------------------
             * Ataca la maquina
             *
             */

            turno++;
            System.out.println("\nTurno " + turno + " -> " + pokEnemy.getNombre());

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            /**
             * Se refresca la comprobación de la integridad de sus pokemon por si hubiera cambiado en el anterior ataque,
             * en tal caso, si no le quedara pokemon con vida, se abandona la batalla
             */
            if (!enemigo.comprobarVidaListaPokemon()) {
                System.out.println(enemigo.getNombre() + " derrotado!!! ----");
                break;
            }
            /**
             * Se analiza si el pokemon actual sigue con vida despues del ultimo ataque.
             *
             * En caso de que si se procede a elegir el ataque máquina aleatorio,
             * si el ataque es con veneno se guardara en la variable jugadorEnvenenado.
             *
             * En caso de que el pokemon haya caído,
             * se procedera a atacar con el siguiente pokemon que posea el enemigo
             */
            if (pokEnemy.pokemonlives()) {
                jugadorEnvenenado = pokEnemy.ataqueMaquina(pokUser);
            } else {
                pokEnemy = enemigo.elegirPokemonMaquina();
                jugadorEnvenenado = pokEnemy.ataqueMaquina(pokUser);
            }

            /**
             * Se cambia de turno para volver a atacar el jugador.
             * Si la maquina está envenenada, se le quita algo de vida
             */
            turno++;
            System.out.println();

            if (maquinaEnvenenado) {
                int probabilidad = random.nextInt(10);
                if (probabilidad > 5) {
                    maquinaEnvenenado = false;
                } else {
                    System.out.println("El veneno esta quitando vida a " + pokEnemy.getNombre() + " -2");
                    pokEnemy.setVida(pokUser.getVida() - 1);
                }
            }
        }


        /**
         * FIN DE BATALLA ------------------------------------------------------------------------------
         * Se comprueba el ganador...
         * Si gana el usuario el pokemon empleado adquiere experiencia
         */

        if (hayGanador(enemigo) == user) {
            if (pokUser.getExperiencia() < 1000) {
                try {
                    pokUser.aumentarExperiencia(enemigo);
                } catch (experienciaAlMaximoException e) {
                    e.printStackTrace();
                }
                System.out.println("Enhorabuna! Has ganado la batalla");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }


            /**
             * Si gana la maquina, se le notifica al usuario
             */
        } else if (hayGanador(enemigo) == enemigo) {
            System.out.println("Has perdido la batalla.");
        }

        /**
         * Al finalizar la batalla y comprobar el ganador, se restablece la vida de los pokemon máquina.
         */
        enemigo.getPokeballs()
                .getListaPokemonMalo()
                .forEach(pokemon -> pokemon.setVida(100));

        /**
         * IMPORTANTE-
         * El metodo luchar devolvera el ultimo pokemon empleado,
         * para así poder asignar al jugador el pokemon por defecto de batalla
         */

        return pokUser;
    }

    /**
     * Método que evalua tras cada doble turno si ya hay un ganador
     *
     * @param enemigo
     * @return el personaje ganador
     */

    public personaje hayGanador(enemigo enemigo) {

        if (!user.comprobarVidaListaPokemon()) {
            return enemigo;
        }
        if (!enemigo.comprobarVidaListaPokemon()) {
            return user;
        }
        return null;
    }

    /**
     * Funcion que permite añadir al pokemon salvaje ya capturado
     *
     * @param pokemon Pokemon Salvaje a Educar
     */

    public static void anadirPokemonSalvaje(jugador user, pokemonMaquina pokemon) {

        try {
            if (user.getItems().getMisPokemon().size() < 10) {
                pokemonJugador salvajeEducado = new pokemonJugador(pokemon.getNombre(), pokemon.getVida(), pokemon.getNivel() * 100, pokemon.getTipo(),
                        ataqueSimple.ataqueSimpleNormal(), moveJugador.anadirMovesJugadorNormal());
                user.getItems().getMisPokemon().add(salvajeEducado);

            } else if (user.getItems().getPocion().getCantidad() == 0) {
                throw new noHayPocionesException(err.NO_HAY_POCIONES.toString());
            } else if (user.getItems().getMisPokemon().size() == 10) {
                throw new noHayEspacioException(err.NO_HAY_ESPACIO_PARA_POKEMON.toString());
            }
        } catch (noHayEspacioException | noHayPocionesException e) {
            e.printStackTrace();
        }
    }


}

