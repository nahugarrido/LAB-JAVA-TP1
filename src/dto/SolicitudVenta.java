package dto;

public class SolicitudVenta {
    private String identificador;
    private int cantidad;

    public SolicitudVenta(String identificador, int cantidad) {
        this.identificador = identificador;
        this.cantidad = cantidad;
    }

    public String getIdentificador() {
        return identificador;
    }

    public int getCantidad() {
        return cantidad;
    }
}
