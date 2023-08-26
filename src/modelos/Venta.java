package modelos;

import java.math.BigDecimal;
import java.util.List;

public class Venta {
    private final List<Producto> productos;
    /// monto antes de aplicar ningun descuento
    private final BigDecimal montoTotalVenta;
    /// monto despues de aplicar descuentos
    private final BigDecimal montoSubTotalVenta;
    /// impuestos en la venta
    private final BigDecimal impuestosVenta;
    /// monto abonado
    private final BigDecimal montoAbonadoTotal;

    public Venta(List<Producto> productos, BigDecimal montoTotalVenta, BigDecimal montoSubTotalVenta, BigDecimal impuestosVenta, BigDecimal montoAbonadoTotal) {
        this.productos = productos;
        this.montoTotalVenta = montoTotalVenta;
        this.montoSubTotalVenta = montoSubTotalVenta;
        this.impuestosVenta = impuestosVenta;
        this.montoAbonadoTotal = montoAbonadoTotal;
    }

    public BigDecimal getMontoTotalVenta() {
        return montoTotalVenta;
    }

    public BigDecimal getMontoSubTotalVenta() {
        return montoSubTotalVenta;
    }

    public BigDecimal getImpuestosVenta() {
        return impuestosVenta;
    }

    public BigDecimal getMontoAbonadoTotal() {
        return montoAbonadoTotal;
    }

    public String mostrarVenta() {
        StringBuilder texto = new StringBuilder();
        texto.append("****************** VENTA ******************");
        texto.append("SUBTOTAL SIN DESCUENTOS: " + this.getMontoTotalVenta() + "\n");
        texto.append("SUBTOTAL APLICANDO DESCUENTOS: " + this.getMontoSubTotalVenta() + "\n");
        texto.append("IMPUESTOS: " + this.getImpuestosVenta() + "\n");
        texto.append("MONTO TOTAL ABONADO: " + this.getMontoAbonadoTotal() + "\n");
        texto.append("****************** ITEMS ******************");
        for(Producto item : productos) {
            texto.append(item.getIdentificador() + " " + item.getDescripcion() + " ");
            texto.append(item.getCantidad() + " x " + item.getPrecioVenta() + "\n");
        }

        texto.append("******************************************");

        return texto.toString();
    }
}
