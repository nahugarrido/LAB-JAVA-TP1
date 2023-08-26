package excepciones;

public class MaximaCantidadPorItemException extends RuntimeException {
    private int maximaCantidadPorItem;
    private int cantidadSolicitada;

    public MaximaCantidadPorItemException(int maximaCantidadPorItem, int cantidadSolicitada) {
        this.maximaCantidadPorItem = maximaCantidadPorItem;
        this.cantidadSolicitada = cantidadSolicitada;
    }

    @Override
    public String getMessage() {
        return "La cantidad de productos solicitados por item excede el maximo permitido, maximo permitido: " + this.getMaximaCantidadPorItem() + ", cantidad solicitada: " + this.getCantidadSolicitada();
    }

    public int getMaximaCantidadPorItem() {
        return maximaCantidadPorItem;
    }

    public int getCantidadSolicitada() {
        return cantidadSolicitada;
    }
}
