package myGame.items;

import java.io.Serializable;

import myGame.errores.carteraVaciaException;
import myGame.errores.datoInvalidoException;
import myGame.errores.err;
import myGame.errores.inventarioLlenoException;
import myGame.errores.inventarioVacioException;
import myGame.errores.itemLlenoException;
import myGame.errores.itemVacioException;
import myGame.personajes.jugador;
import myGame.utiles.util;

public class tiendaPokemon implements Serializable {

    private static final long serialVersionUID = 7001610035037850033L;
    private objetosTienda pocion;
    private objetosTienda fruta;
    private objetosTienda antidoto;
    private objetosTienda pokeball;

    public tiendaPokemon(objetosTienda pocion, objetosTienda fruta, objetosTienda antidoto, objetosTienda pokeball) {
        this.pocion = pocion;
        this.fruta = fruta;
        this.antidoto = antidoto;
        this.pokeball = pokeball;
    }

    public objetosTienda getPocion() {
        return pocion;
    }

    public void setPocion(objetosTienda pocion) {
        this.pocion = pocion;
    }

    public objetosTienda getFruta() {
        return fruta;
    }

    public void setFruta(objetosTienda fruta) {
        this.fruta = fruta;
    }

    public objetosTienda getAntidoto() {
        return antidoto;
    }

    public void setAntidoto(objetosTienda antidoto) {
        this.antidoto = antidoto;
    }

    public objetosTienda getPokeball() {
        return pokeball;
    }

    public void setPokeball(objetosTienda pokeball) {
        this.pokeball = pokeball;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////GETTER Y SETTER


    /**
     * Método de muestro del menú principal de la tienda
     *
     * @return
     */

    public int menuTienda() {
        System.out.println("0. Terminar compra\n1. Comprar\n2. Vender");
        return util.introducirNumeroObligatorio("Buenos dias, que quieres hacer?");
    }

    /**
     * Función empleada para el acceso a la tienda de artículos
     *
     * @param user
     */

    public void atender(jugador user) {
        int menu = -1;

        do {
            try {
                menu = menuTienda();
                switch (menu) {
                    case 1:
                        compra(user);
                        break;
                    case 2:
                        venta(user);
                        break;
                }
                if (menu < 0 || menu > 2) throw new datoInvalidoException(err.DATO_INVALIDO.toString());
            } catch (datoInvalidoException | carteraVaciaException | inventarioLlenoException | inventarioVacioException e) {
                e.printStackTrace();
            }
        } while (menu != 0);
    }

    private void venta(jugador user) throws inventarioVacioException {
        int menu = -1;
        boolean vacio = false;

        if (user.getItems().inventarioVacío()) {
            throw new inventarioVacioException(err.ITEMS_VACIO.toString());
        }

        do {
            try {
                menu = menuVender(user);
                if (menu < 0 || menu > 4) throw new datoInvalidoException(err.DATO_INVALIDO.toString());

                switch (menu) {
                    case 1:
                        if (user.getItems().getPocion().quedaObjetos())
                            vender(user, objetosTienda.POCION);
                        else
                            throw new itemVacioException(err.ITEMS_VACIO.toString());
                        break;
                    case 2:
                        if (user.getItems().getFruta().quedaObjetos())
                            vender(user, objetosTienda.FRUTA);
                        else
                            throw new itemVacioException(err.ITEMS_VACIO.toString());
                        break;
                    case 3:
                        if (user.getItems().getAntidoto().quedaObjetos())
                            vender(user, objetosTienda.ANTIDOTO);
                        else
                            throw new itemVacioException(err.ITEMS_VACIO.toString());
                        break;
                    case 4:
                        if (user.getItems().getPokeball().quedaObjetos())
                            vender(user, objetosTienda.POKEBALL);
                        else
                            throw new itemVacioException(err.ITEMS_VACIO.toString());
                        break;
                }
                if (user.getItems().inventarioVacío()) break;
            } catch (datoInvalidoException | itemVacioException e) {
                e.printStackTrace();
            }
        } while (menu != 0);
    }

    public void compra(jugador user) throws carteraVaciaException, inventarioLlenoException {
        int menu = -1;
        boolean comprar = true;
        boolean lleno = false;

        if (user.getItems().inventarioLleno()) {
            throw new inventarioLlenoException(err.ITEMS_LLENO.toString());
        }

        if (user.getCartera() == 0) {
            throw new carteraVaciaException(err.NO_HAY_SUFICIENTE_DINERO.toString());
        }

        do {
            try {
                menu = menuComprar(user);
                if (menu < 0 || menu > 4) throw new datoInvalidoException(err.DATO_INVALIDO.toString());

                switch (menu) {
                    case 1:
                        if (!user.getItems().getPocion().objetoLleno())
                            comprar(user, objetosTienda.POCION);
                        else
                            throw new itemLlenoException(err.ITEMS_LLENO.toString());
                        break;
                    case 2:
                        if (!user.getItems().getFruta().objetoLleno())
                            comprar(user, objetosTienda.FRUTA);
                        else
                            throw new itemLlenoException(err.ITEMS_LLENO.toString());
                        break;
                    case 3:
                        if (!user.getItems().getAntidoto().objetoLleno())
                            comprar(user, objetosTienda.ANTIDOTO);
                        else
                            throw new itemLlenoException(err.ITEMS_LLENO.toString());
                        break;
                    case 4:
                        if (!user.getItems().getPokeball().objetoLleno())
                            comprar(user, objetosTienda.POKEBALL);
                        else
                            throw new itemLlenoException(err.ITEMS_LLENO.toString());
                        break;
                }
                if (user.getCartera() == 0) break;
                if (user.getItems().inventarioLleno()) break;
            } catch (itemLlenoException | datoInvalidoException e) {
                e.printStackTrace();
            }
        } while (menu != 0);
    }

    /**
     * Métodos de muestro de los menús secundarios de la tienda
     * comprar y vender
     *
     * @return
     */

    public int menuComprar(jugador user) {
        System.out.println("0. Atrás");
        System.out.println("1. Pociones  x" + user.getItems().getPocion().getCantidad());
        System.out.println("2. Frutas curativas  x" + user.getItems().getFruta().getCantidad());
        System.out.println("3. Antidotos  x" + user.getItems().getAntidoto().getCantidad());
        System.out.println("4. Pokeballs  x" + user.getItems().getPokeball().getCantidad());
        System.out.println("\n" + user.getCartera() + " dollars");
        return util.introducirNumeroObligatorio("Que quieres comprar?");
    }

    public int menuVender(jugador user) {
        System.out.println("0. Atrás");
        System.out.println("1. Pociones  x" + user.getItems().getPocion().getCantidad());
        System.out.println("2. Frutas curativas  x" + user.getItems().getFruta().getCantidad());
        System.out.println("3. Antidotos  x" + user.getItems().getAntidoto().getCantidad());
        System.out.println("4. Pokeballs  x" + user.getItems().getPokeball().getCantidad());
        System.out.println("\n" + user.getCartera() + " dollars");
        return util.introducirNumeroObligatorio("Selecciona el articulo que desea vender");
    }

    /**
     * Funciones varias para las elecciones de los correspondientes
     * menús de compraventa en tienda
     *
     * @return Opcion elegida
     */

    public void comprar(jugador user, objetosTienda tipoObjeto) {
        int compra = -1;
        objetosTienda objeto = null;
        do {
            try {
                if (tipoObjeto == objetosTienda.POCION) {
                    objeto = user.getItems().getPocion();
                } else if (tipoObjeto == objetosTienda.FRUTA) {
                    objeto = user.getItems().getFruta();
                } else if (tipoObjeto == objetosTienda.ANTIDOTO) {
                    objeto = user.getItems().getAntidoto();
                } else if (tipoObjeto == objetosTienda.POKEBALL) {
                    objeto = user.getItems().getPokeball();
                }
                compra = compraObjeto(user, objeto);

                if (compra < 0 || compra > objeto.getMaxmimaCantidad() || ((compra + objeto.getCantidad()) > objeto.getMaxmimaCantidad()))
                    throw new datoInvalidoException(err.DATO_INVALIDO.toString());
                else if (compra > 0) {
                    if ((objeto.getCantidad() + compra) <= objeto.getMaxmimaCantidad()) {
                        int cantidadDollars = compra * objeto.getPrecio();
                        if (user.getCartera() >= cantidadDollars) {
                            user.setCartera(user.getCartera() - cantidadDollars);
                            compraRealizada(cantidadDollars);
                            objeto.setCantidad(objeto.getCantidad() + compra);
                            if (tipoObjeto == objetosTienda.POCION) {
                                user.getItems().setPocion(objeto);
                            } else if (tipoObjeto == objetosTienda.FRUTA) {
                                user.getItems().setFruta(objeto);
                            } else if (tipoObjeto == objetosTienda.ANTIDOTO) {
                                user.getItems().setAntidoto(objeto);
                            } else if (tipoObjeto == objetosTienda.POKEBALL) {
                                user.getItems().setPokeball(objeto);
                            }
                            break;
                        } else {
                            throw new carteraVaciaException(err.NO_HAY_SUFICIENTE_DINERO.toString());
                        }
                    } else if ((objeto.getCantidad() + compra) > objeto.getMaxmimaCantidad()) {
                        int compraReal = objeto.getMaxmimaCantidad() - objeto.getCantidad();
                        int cantidadDollars = compraReal * objeto.getPrecio();

                        if (user.getCartera() >= cantidadDollars) {
                            user.setCartera(user.getCartera() - cantidadDollars);
                            compraRealizada(cantidadDollars);
                            objeto.setCantidad(objeto.getCantidad() + compraReal);
                            if (tipoObjeto == objetosTienda.POCION) {
                                user.getItems().setPocion(objeto);
                            } else if (tipoObjeto == objetosTienda.FRUTA) {
                                user.getItems().setFruta(objeto);
                            } else if (tipoObjeto == objetosTienda.ANTIDOTO) {
                                user.getItems().setAntidoto(objeto);
                            } else if (tipoObjeto == objetosTienda.POKEBALL) {
                                user.getItems().setPokeball(objeto);
                            }
                            break;
                        }
                    }
                }
            } catch (datoInvalidoException | carteraVaciaException e) {
                e.printStackTrace();
            }
        } while (compra != 0 && user.getCartera() > 0);
    }

    public void vender(jugador user, objetosTienda tipoObjeto) {
        int venta = -1;
        objetosTienda objeto = null;

        do {
            try {
                if (tipoObjeto == objetosTienda.POCION) {
                    objeto = user.getItems().getPocion();
                } else if (tipoObjeto == objetosTienda.FRUTA) {
                    objeto = user.getItems().getFruta();
                } else if (tipoObjeto == objetosTienda.ANTIDOTO) {
                    objeto = user.getItems().getAntidoto();
                } else if (tipoObjeto == objetosTienda.POKEBALL) {
                    objeto = user.getItems().getPokeball();
                }
                if (objeto.quedaObjetos()) {
                    venta = ventaObjeto(user, objeto);
                    if (venta < 0 || venta > objeto.getCantidad())
                        throw new datoInvalidoException(err.DATO_INVALIDO.toString());
                    else if (venta > 0) {
                        int cantidadDollars = venta * objeto.getPrecio();
                        user.setCartera(user.getCartera() + cantidadDollars);
                        objeto.setCantidad(objeto.getCantidad() - venta);
                        if (tipoObjeto == objetosTienda.POCION) {
                            user.getItems().setPocion(objeto);
                        } else if (tipoObjeto == objetosTienda.FRUTA) {
                            user.getItems().setFruta(objeto);
                        } else if (tipoObjeto == objetosTienda.ANTIDOTO) {
                            user.getItems().setAntidoto(objeto);
                        } else if (tipoObjeto == objetosTienda.POKEBALL) {
                            user.getItems().setPokeball(objeto);
                        }
                        ventaRealizada(cantidadDollars);
                        break;
                    }
                } else {
                    throw new itemVacioException(err.ITEMS_VACIO.toString());
                }
            } catch (datoInvalidoException e) {
                e.printStackTrace();
            } catch (itemVacioException e) {
                e.printStackTrace();
                break;
            }
        } while (venta != 0);
    }

    public int compraObjeto(jugador user, objetosTienda objeto) {
        int cantidad = objeto.getMaxmimaCantidad() - objeto.getCantidad();
        if (cantidad > 10) {
            cantidad = 10;
        }
        System.out.println("Que cantidad quieres comprar? El máximo disponible son " + cantidad);
        System.out.println("El precio de cada pokeball es de " + objeto.getPrecio());
        System.out.println();
        System.out.println(user.getCartera() + " dollars");
        return util.introducirNumeroObligatorio("0. Atrás");
    }

    public int ventaObjeto(jugador user, objetosTienda objeto) {
        System.out.println("Que cantidad quieres vender? El máximo disponible son " + objeto.getCantidad());
        System.out.println();
        System.out.println(user.getCartera() + " dollars");
        return util.introducirNumeroObligatorio("0. Atrás");
    }

    /**
     * Funciones empleadas para la finalización de transacciones en tienda
     *
     * @param dollars
     */

    public void compraRealizada(int dollars) {
        System.out.println("\nHas gastado " + dollars + " pokemon dollars");
        System.out.println("Los articulos comprados se han guardado en tu mochila\n");
    }

    public void ventaRealizada(int dollars) {
        System.out.println("\nArticulos vendidos, has ganado " + dollars + " pokemon dollars\n");
    }


}
