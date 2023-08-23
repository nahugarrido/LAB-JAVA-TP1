package modelos;

import excepciones.PorcentajeDescuentoNoValidoException;
import interfaces.ProductoComestible;
import interfaces.ProductoDescontable;
import utiles.GeneradorID;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ProductoBebida extends Producto implements ProductoDescontable, ProductoComestible {
    private static final double PORCENTAJE_DESCUENTO_MAXIMO = 0.15;
    private boolean esImportado;
    private double graduacionAlcoholica;
    private boolean esAlcoholica;
    private double porcentajeDescuento;
    private LocalDate fechaVencimiento;
    private int calorias;

    /* Constructor auxiliar para bebidas no alcoholicas (no recibe "graduacionAlcoholica" por parametro) */
    public ProductoBebida(String descripcion, int cantidad, BigDecimal precioVenta, BigDecimal precioCompra, boolean estaDisponible, boolean esImportado, boolean esAlcoholica) {
        super(GeneradorID.generarIDBebida(), descripcion, cantidad, precioVenta, precioCompra, estaDisponible);
        this.esImportado = esImportado;
        this.esAlcoholica = esAlcoholica;
        this.porcentajeDescuento = 0;
        this.graduacionAlcoholica = 0;
    }

    public ProductoBebida(String descripcion, int cantidad, BigDecimal precioVenta, BigDecimal precioCompra, boolean estaDisponible, boolean esImportado, boolean esAlcoholica, double graduacionAlcoholica) {
        super(GeneradorID.generarIDBebida(), descripcion, cantidad, precioVenta, precioCompra, estaDisponible);
        this.esImportado = esImportado;
        this.esAlcoholica = esAlcoholica;
        this.porcentajeDescuento = 0;
        if(esAlcoholica) {
            this.graduacionAlcoholica = graduacionAlcoholica;
        } else {
            this.graduacionAlcoholica = 0;
        }
    }

    public class Builder {
        private boolean esImportado;
        private double graduacionAlcoholica;
        private boolean esAlcoholica;
        private double porcentajeDescuento = 0.0;
        private LocalDate fechaVencimiento;
        private int calorias;
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
        return this.getPrecioVenta().multiply(BigDecimal.valueOf(1 - this.porcentajeDescuento));
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

    public boolean isEsImportado() {
        return esImportado;
    }

    public double getGraduacionAlcoholica() {
        return graduacionAlcoholica;
    }

    public boolean isEsAlcoholica() {
        return esAlcoholica;
    }
}
