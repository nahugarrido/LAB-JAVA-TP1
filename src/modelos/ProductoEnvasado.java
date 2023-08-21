package modelos;

import enums.TipoEnvase;

import java.math.BigDecimal;

public class ProductoEnvasado extends Producto{
    private TipoEnvase tipoEnvase;
    private boolean esImportado;

    /* EL identificador de producto envasado debe ser de la forma ABXXX donde XXX son digitos */
    public ProductoEnvasado(String identificador, String descripcion, int cantidad, BigDecimal precioVenta, BigDecimal precioCompra, boolean estaDisponible) {
        super(identificador, descripcion, cantidad, precioVenta, precioCompra, estaDisponible);
    }

    public TipoEnvase getTipoEnvase() {
        return tipoEnvase;
    }

    public boolean isEsImportado() {
        return esImportado;
    }
}
