package modelos;

import enums.TipoAplicacion;
import excepciones.PorcentajeDescuentoNoValidoException;
import interfaces.ProductoDescontable;
import utiles.GeneradorID;

import java.math.BigDecimal;

public class ProductoLimpieza extends Producto implements ProductoDescontable {
    private static final double PORCENTAJE_DESCUENTO_MAXIMO = 0.25;
    private TipoAplicacion tipoAplicacion;
    private double porcentajeDescuento;

    public ProductoLimpieza(String descripcion, int cantidad, BigDecimal precioCompra, BigDecimal precioVenta, boolean estaDisponible, TipoAplicacion tipoAplicacion) {
        super(GeneradorID.generarIDLimpieza(), descripcion, cantidad, precioCompra, precioVenta, estaDisponible);
        this.tipoAplicacion = tipoAplicacion;
        this.porcentajeDescuento = 0;
    }

    @Override
    public String toString() {
        return super.toString() +
                " * Tipo de aplicacion: " + tipoAplicacion.toString() +
                " * Porcentaje de descuento: " + porcentajeDescuento + "%" + "\n";
    }

    @Override
    public void setPorcentajeDescuento(double porcentaje) {
        if(porcentaje > PORCENTAJE_DESCUENTO_MAXIMO) {
            throw new PorcentajeDescuentoNoValidoException(PORCENTAJE_DESCUENTO_MAXIMO, porcentaje);
        }
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

    public TipoAplicacion getTipoAplicacion() {
        return tipoAplicacion;
    }
}
