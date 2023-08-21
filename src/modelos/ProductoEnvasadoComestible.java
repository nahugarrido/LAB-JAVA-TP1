package modelos;

import interfaces.ProductoComestible;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ProductoEnvasadoComestible extends ProductoEnvasado implements ProductoComestible {
    private LocalDate fechaVencimiento;
    private int calorias;

    public ProductoEnvasadoComestible(String identificador, String descripcion, int cantidad, BigDecimal precioVenta, BigDecimal precioCompra, boolean estaDisponible) {
        super(identificador, descripcion, cantidad, precioVenta, precioCompra, estaDisponible);
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
}
