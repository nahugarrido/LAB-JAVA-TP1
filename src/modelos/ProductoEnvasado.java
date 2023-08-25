package modelos;

import enums.TipoEnvase;
import excepciones.PorcentajeDescuentoNoValidoException;
import interfaces.ProductoComestible;
import interfaces.ProductoDescontable;
import utiles.GeneradorID;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ProductoEnvasado extends Producto implements ProductoDescontable, ProductoComestible {
    private TipoEnvase tipoEnvase;
    private boolean esImportado;
    private double porcentajeDescuento;
    private LocalDate fechaVencimiento;
    private int calorias;

    public ProductoEnvasado(String descripcion, int cantidad, BigDecimal precioCompra, BigDecimal precioVenta, boolean estaDisponible, TipoEnvase tipoEnvase, boolean esImportado) {
        super(GeneradorID.generarIDEnvasado(), descripcion, cantidad, precioCompra, precioVenta, estaDisponible);
        this.esImportado = esImportado;
        this.tipoEnvase = tipoEnvase;
        this.porcentajeDescuento = 0;
        this.fechaVencimiento = null;
        this.calorias = 0;
    }

    @Override
    public String toString() {
        return super.toString() +
                " * ¿Es Importado?: " + esImportado +
                " * Tipo de envase: " + tipoEnvase.getMensaje() +
                " * Porcentaje de descuento: " + porcentajeDescuento + "%" +
                " * Calorias: " + calorias +
                " * Fecha de vencimiento: " + fechaVencimiento + "\n";
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
