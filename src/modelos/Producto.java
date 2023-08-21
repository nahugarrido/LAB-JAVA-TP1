package modelos;

import excepciones.IdentificadorNoValidoExcepcion;

import java.math.BigDecimal;

public abstract class Producto {
    /* El identificador es alfanumerico de longitud 5*/
    /* Podria usar directamente regex para restringirlo pero prefiero dejarlo asi de momento */
    protected String identificador;
    protected String descripcion;
    protected int cantidad;
    protected BigDecimal precioVenta;
    protected BigDecimal precioCompra;
    protected boolean estaDisponible;

    public Producto(String identificador, String descripcion, int cantidad, BigDecimal precioVenta, BigDecimal precioCompra, boolean estaDisponible) {
        this.identificador = identificador;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
        this.precioVenta = precioVenta;
        this.precioCompra = precioCompra;
        this.estaDisponible = estaDisponible;
    }
}
