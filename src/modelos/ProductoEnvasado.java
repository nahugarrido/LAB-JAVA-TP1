package modelos;

import enums.TipoEnvase;
import excepciones.PorcentajeDescuentoNoValidoException;
import interfaces.ProductoComestible;
import interfaces.ProductoDescontable;
import utiles.GeneradorID;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ProductoEnvasado extends Producto implements ProductoDescontable, ProductoComestible {
    private static final double PORCENTAJE_DESCUENTO_MAXIMO = 0.20;
    private TipoEnvase tipoEnvase;
    private boolean esImportado;
    private double porcentajeDescuento;
    private LocalDate fechaVencimiento;
    private int calorias;

    public ProductoEnvasado(String descripcion, int cantidad, BigDecimal precioVenta, BigDecimal precioCompra, boolean estaDisponible) {
        super(GeneradorID.generarIDEnvasado(), descripcion, cantidad, precioVenta, precioCompra, estaDisponible);
        this.porcentajeDescuento = 0;
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

    @Override
    public void setFechaVencimiento(LocalDate fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    @Override
    public LocalDate getFechaVencimiento() {
        return this.fechaVencimiento;
    }

    @Override
    public void setCalorias(int calorias) {
        this.calorias = calorias;
    }

    @Override
    public int getCalorias() {
        return this.calorias;
    }

    public TipoEnvase getTipoEnvase() {
        return tipoEnvase;
    }

    public boolean isEsImportado() {
        return esImportado;
    }
}
