package interfaces;

import java.math.BigDecimal;

public interface ProductoDescontable {
    void setPorcentajeDescuento(double porcentaje);
    double getPorcentajeDescuento();
    BigDecimal getPrecioVentaConDescuento();
}
