package modelos;

import enums.TipoEnvase;
import interfaces.ProductoComestible;
import interfaces.ProductoDescontable;
import utiles.GeneradorID;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ProductoEnvasado extends Producto implements ProductoDescontable, ProductoComestible {
    private TipoEnvase tipoEnvase;
    private boolean esImportado;
    private LocalDate fechaVencimiento;
    private int calorias;

    public ProductoEnvasado(String descripcion, int cantidad, BigDecimal precioCompra, BigDecimal precioVenta, boolean estaDisponible, TipoEnvase tipoEnvase, boolean esImportado) {
        super(GeneradorID.generarIDEnvasado(), descripcion, cantidad, precioCompra, precioVenta, estaDisponible);
        this.esImportado = esImportado;
        this.tipoEnvase = tipoEnvase;
        this.fechaVencimiento = null;
        this.calorias = 0;
    }

    @Override
    public String toString() {
        return super.toString() +
                " * ¿Es Importado?: " + esImportado +
                " * Tipo de envase: " + tipoEnvase.getMensaje() +
                " * Calorias: " + calorias +
                " * Fecha de vencimiento: " + fechaVencimiento + "\n";
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
