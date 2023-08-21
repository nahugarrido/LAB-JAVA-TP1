package modelos;

import enums.TipoAplicacion;
import interfaces.ProductoDescontable;

import java.math.BigDecimal;

public class ProductoLimpieza extends Producto implements ProductoDescontable {
    private TipoAplicacion tipoAplicacion;
    private double porcentajeDescuento;

    /* EL identificador de producto envasado debe ser de la forma AZXXX donde XXX son digitos */
    public ProductoLimpieza(String identificador, String descripcion, int cantidad, BigDecimal precioVenta, BigDecimal precioCompra, boolean estaDisponible, TipoAplicacion tipoAplicacion) {
        super(identificador, descripcion, cantidad, precioVenta, precioCompra, estaDisponible);
        this.tipoAplicacion = tipoAplicacion;
    }

    public TipoAplicacion getTipoAplicacion() {
        return tipoAplicacion;
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
        BigDecimal precioVentaConDescuento = super.getPrecioVenta().multiply(BigDecimal.valueOf(1 - this.porcentajeDescuento));
        return precioVentaConDescuento;
    }
}
