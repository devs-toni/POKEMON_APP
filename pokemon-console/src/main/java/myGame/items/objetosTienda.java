package myGame.items;

public enum objetosTienda {
    POCION (4, 50, 10, 20),
    FRUTA (4, 50, 10, 10),
    ANTIDOTO(4, 50, 10),
    POKEBALL(4, 100, 10);

    private int cantidad;
    private int precio;
    private int maxmimaCantidad;
    private int curacion;

    objetosTienda(int cantidad, int precio, int maxmimaCantidad, int curacion){
        this.cantidad = cantidad;
        this.precio = precio;
        this.maxmimaCantidad = maxmimaCantidad;
        this.curacion = curacion;
    }

    objetosTienda(int cantidad, int precio, int maxmimaCantidad) {
        this.cantidad = cantidad;
        this.precio = precio;
        this.maxmimaCantidad = maxmimaCantidad;
    }

    objetosTienda (){}

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad (int cantidad){
        this.cantidad = cantidad;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public int getMaxmimaCantidad() {
        return maxmimaCantidad;
    }

    public void setMaxmimaCantidad(int maxmimaCantidad) {
        this.maxmimaCantidad = maxmimaCantidad;
    }

    public boolean objetoLleno () {
        return cantidad == maxmimaCantidad;
    }

    public boolean quedaObjetos() {
        return cantidad > 0;
    }

    public int getCuracion() {
        return curacion;
    }

    public void setCuracion(int curacion) {
        this.curacion = curacion;
    }
}
