package modelos;

import java.math.BigDecimal;

/* En esta clase la consigna plantea que si una bebida es alcoholica debe contar con
* la propiedad graduacion alcoholica, en vez de crear 2 sub clases de bebida,
* prefiero crear una unica clase y gestionar el mostrar o no el atributo "graduacionAlcoholica" desde las otras funciones,
* en caso de que la bebida no sea alcoholica se le asignara un valor por "0" al atributo.
*  */
public class ProductoBebida extends Producto {
    private boolean esImportado;
    private double graduacionAlcoholica;
    private boolean esAlcoholica;

    /* Constructor auxiliar para bebidas no alcoholicas (no recibe "graduacionAlcoholica" por parametro) */
    public ProductoBebida(String identificador, String descripcion, int cantidad, BigDecimal precioVenta, BigDecimal precioCompra, boolean estaDisponible, boolean esImportado, boolean esAlcoholica) {
        super(identificador, descripcion, cantidad, precioVenta, precioCompra, estaDisponible);
        this.esImportado = esImportado;
        this.esAlcoholica = esAlcoholica;
        this.graduacionAlcoholica = 0;
    }

    /* EL identificador de producto bebida debe ser de la forma ACXXX donde XXX son digitos */
    public ProductoBebida(String identificador, String descripcion, int cantidad, BigDecimal precioVenta, BigDecimal precioCompra, boolean estaDisponible, boolean esImportado, boolean esAlcoholica, double graduacionAlcoholica) {
        super(identificador, descripcion, cantidad, precioVenta, precioCompra, estaDisponible);
        this.esImportado = esImportado;
        this.esAlcoholica = esAlcoholica;
        if(esAlcoholica) {
            this.graduacionAlcoholica = graduacionAlcoholica;
        } else {
            this.graduacionAlcoholica = 0;
        }
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
