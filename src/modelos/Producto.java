package modelos;

import excepciones.IdentificadorNoValidoExcepcion;

import java.math.BigDecimal;

public abstract class Producto {
    /* El identificador es alfanumerico de longitud 5*/
    /* Podria usar directamente regex para restringirlo pero prefiero dejarlo asi de momento */
    private String identificador;
    private String descripcion;
    private int cantidad;
    private BigDecimal precioVenta;
    private BigDecimal precioCompra;
    private boolean estaDisponible;

    public Producto(String identificador, String descripcion, int cantidad, BigDecimal precioVenta, BigDecimal precioCompra, boolean estaDisponible) {
        this.identificador = identificador;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
        this.precioVenta = precioVenta;
        this.precioCompra = precioCompra;
        this.estaDisponible = estaDisponible;
    }


    public String getIdentificador() {
        return identificador;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getCantidad() {
        return cantidad;
    }

    public BigDecimal getPrecioVenta() {
        return precioVenta;
    }

    public BigDecimal getPrecioCompra() {
        return precioCompra;
    }

    public boolean isEstaDisponible() {
        return estaDisponible;
    }
}
