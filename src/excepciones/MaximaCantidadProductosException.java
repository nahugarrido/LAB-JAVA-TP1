package excepciones;

public class MaximaCantidadProductosException extends RuntimeException {
    private int maximaCantidadProductos;
    private int cantidadProductos;

    public MaximaCantidadProductosException(int maximaCantidadProductos, int cantidadProductos) {
        this.maximaCantidadProductos = maximaCantidadProductos;
        this.cantidadProductos = cantidadProductos;
    }

    @Override
    public String getMessage() {
        return "La cantidad de productos excede el maximo permitido, maximo permitido: " + this.getMaximaCantidadProductos() + ", cantidad actual: " + this.getCantidadProductos();
    }

    public int getMaximaCantidadProductos() {
        return maximaCantidadProductos;
    }

    public int getCantidadProductos() {
        return cantidadProductos;
    }
}
