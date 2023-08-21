package modelos;

import enums.TipoAplicacion;

import java.math.BigDecimal;

public class ProductoLimpieza extends Producto{
    private TipoAplicacion tipoAplicacion;

    /* EL identificador de producto envasado debe ser de la forma AZXXX donde XXX son digitos */
    public ProductoLimpieza(String identificador, String descripcion, int cantidad, BigDecimal precioVenta, BigDecimal precioCompra, boolean estaDisponible, TipoAplicacion tipoAplicacion) {
        super(identificador, descripcion, cantidad, precioVenta, precioCompra, estaDisponible);
        this.tipoAplicacion = tipoAplicacion;
    }
}
