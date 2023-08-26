package modelos;

import interfaces.ProductoDescontable;

import java.math.BigDecimal;

public abstract class Producto implements ProductoDescontable {
    private String identificador;
    private String descripcion;
    private int cantidad;
    private BigDecimal precioCompra;
    private BigDecimal precioVenta;
    private boolean estaDisponible;
    private double porcentajeDescuento;

    public Producto(String identificador, String descripcion, int cantidad, BigDecimal precioCompra, BigDecimal precioVenta, boolean estaDisponible) {
        this.identificador = identificador;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
        this.precioCompra = precioCompra;
        this.precioVenta = precioVenta;
        this.estaDisponible = estaDisponible;
        this.porcentajeDescuento = 0;
    }

    @Override
    public String toString() {
        return "Identificador: " + identificador +
                " * Descripcion: " + descripcion +
                " * Cantidad disponible: " + cantidad +
                " * Valor de compra: " + precioCompra +
                " * Valor de venta:" + precioVenta +
                " * Porcentaje de descuento: " + porcentajeDescuento + "%" +
                " * ¿Está Disponible?: " + estaDisponible;
    }

    @Override
    public void setPorcentajeDescuento(double porcentaje) {
        this.porcentajeDescuento = porcentaje;
    }

    @Override
    public double getPorcentajeDescuento() {
        return this.porcentajeDescuento;
    }

    @Override
    public BigDecimal getPrecioVentaConDescuento() {
        BigDecimal precioVentaConDescuento = this.getPrecioVenta().multiply(BigDecimal.valueOf(1 - this.porcentajeDescuento));
        return precioVentaConDescuento;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public void setEstaDisponible(boolean estaDisponible) {
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
