package modelos;

import enums.TipoAplicacion;
import interfaces.ProductoDescontable;
import utiles.GeneradorID;

import java.math.BigDecimal;

public class ProductoLimpieza extends Producto implements ProductoDescontable {
    private TipoAplicacion tipoAplicacion;

    public ProductoLimpieza(String descripcion, int cantidad, BigDecimal precioCompra, BigDecimal precioVenta, boolean estaDisponible, TipoAplicacion tipoAplicacion) {
        super(GeneradorID.generarIDLimpieza(), descripcion, cantidad, precioCompra, precioVenta, estaDisponible);
        this.tipoAplicacion = tipoAplicacion;
    }

    @Override
    public String toString() {
        return super.toString() +
                " * Tipo de aplicacion: " + tipoAplicacion.toString() + "\n";
    }

    public TipoAplicacion getTipoAplicacion() {
        return tipoAplicacion;
    }
}
