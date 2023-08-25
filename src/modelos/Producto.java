package modelos;

import interfaces.ProductoDescontable;

import java.math.BigDecimal;

public abstract class Producto {
    private String identificador;
    private String descripcion;
    private int cantidad;
    private BigDecimal precioCompra;
    private BigDecimal precioVenta;
    private boolean estaDisponible;

    public Producto(String identificador, String descripcion, int cantidad, BigDecimal precioCompra, BigDecimal precioVenta, boolean estaDisponible) {
        this.identificador = identificador;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
        this.precioCompra = precioCompra;
        this.precioVenta = precioVenta;
        this.estaDisponible = estaDisponible;
    }

    @Override
    public String toString() {
        return "Identificador: " + identificador +
                " * Descripcion: " + descripcion +
                " * Cantidad disponible: " + cantidad +
                " * Valor de compra: " + precioCompra +
                " * Valor de venta:" + precioVenta +
                " * ¿Está Disponible?: " + estaDisponible;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
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
