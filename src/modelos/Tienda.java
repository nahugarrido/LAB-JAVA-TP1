package modelos;

import enums.TipoProducto;
import excepciones.PorcentajeDescuentoNoValidoException;
import excepciones.SaldoInsuficienteCajaException;
import excepciones.StockMaximoException;
import excepciones.TipoProductoNoContempladoException;
import interfaces.ProductoComestible;
import interfaces.ProductoDescontable;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

public class Tienda {
    private static final double PORCENTAJE_GANANCIA_COMESTIBLE = 0.20;
    private static final double PORCENTAJE_MIN_GANANCIA_LIMPIEZA = 0.10;
    private static final double PORCENTAJE_MAX_GANANCIA_LIMPIEZA = 0.25;
    private static final double PORCENTAJE_MAX_DESCUENTO_BEBIDAS = 0.15;
    private static final double PORCENTAJE_MAX_DESCUENTO_ENVASADOS = 0.20;
    private static final double PORCENTAJE_MAX_DESCUENTO_LIMPIEZA = 0.25;

    private String nombre;
    private int maxStock;
    private BigDecimal saldoCaja;
    private TreeMap<String, Producto> productos;

    public Tienda(String nombre, int maxStock, BigDecimal saldoCaja) {
        this.nombre = nombre;
        this.maxStock = maxStock;
        this.saldoCaja = saldoCaja;
        this.productos = new TreeMap<>();
    }

    @Override
    public String toString() {
        return  "Nombre: " + nombre +
                " * Stock Maximo: " + maxStock +
                " * Saldo en caja: " + saldoCaja + "\n";

    }

    public void agregarProducto(Producto producto) {
        /// Verificacion stock maximo de tienda
        if(this.maxStock <  this.obtenerStockActual() + producto.getCantidad()) {
            throw new StockMaximoException(this.getMaxStock(), producto.getCantidad());
        }

        /// Verificar saldo suficiente en tienda
        BigDecimal montoCompra = producto.getPrecioCompra().multiply(BigDecimal.valueOf(producto.getCantidad()));

        if(this.getSaldo().compareTo(montoCompra) < 0) {
            throw new SaldoInsuficienteCajaException(this.getSaldo(), montoCompra);
        }

        /// Actualizar Stock
        this.setMaxStock(this.getMaxStock() + producto.getCantidad());
        /// Actualizar Saldo
        this.setSaldoCaja(this.getSaldo().subtract(montoCompra));

        /// Agregar el producto a la tienda
        /// No necesito validar si el identificador existe ya que este es generado por el sistema
        productos.put(producto.getIdentificador(), producto);
    }

    public void establecerFechaVencimientoProducto(String identificador, LocalDate date) {
       ProductoComestible producto = (ProductoComestible) productos.get(identificador);
       producto.setFechaVencimiento(date);
       productos.put(identificador, (Producto) producto);
    }

    public void establecerCaloriasProducto(String identificador, int calorias) {
        ProductoComestible productoComestible = (ProductoComestible) productos.get(identificador);
        productoComestible.setCalorias(calorias);
        productos.put(identificador, (Producto) productoComestible);
    }

    public void establecerDescuentoProducto(String identificador, double porcentajeDescuento) {

        /// Obtener el producto a actualizar
        ProductoDescontable productoDescontable = (ProductoDescontable) productos.get(identificador);

        System.out.println(productoDescontable);

        /// Actualizar el descuento del producto
        if(productoDescontable instanceof ProductoBebida) {
            productoDescontable = actualizarDescuento(productoDescontable, porcentajeDescuento, PORCENTAJE_MAX_DESCUENTO_BEBIDAS);
        } else if(productoDescontable instanceof ProductoLimpieza) {
            productoDescontable = actualizarDescuento(productoDescontable, porcentajeDescuento, PORCENTAJE_MAX_DESCUENTO_LIMPIEZA);

        } else if(productoDescontable instanceof ProductoEnvasado) {
            productoDescontable = actualizarDescuento(productoDescontable, porcentajeDescuento, PORCENTAJE_MAX_DESCUENTO_ENVASADOS);
        } else {
            throw new TipoProductoNoContempladoException(productoDescontable.getClass().getSimpleName());
        }

        System.out.println("TEST");
        /// Persistir el producto
        productos.put(identificador, (Producto) productoDescontable);
    }

    /* Funcion auxiliar para evitar bloque de codigo repetido */
    private ProductoDescontable actualizarDescuento(ProductoDescontable productoDescontable, double porcentajeDescuento, double porcentajeMaximoDescuento) {
        if(porcentajeDescuento <= porcentajeMaximoDescuento) {
            productoDescontable.setPorcentajeDescuento(porcentajeDescuento);
            return productoDescontable;
        } else {
            throw new PorcentajeDescuentoNoValidoException(porcentajeMaximoDescuento,porcentajeDescuento);
        }
    }

    public void realizarVenta(List<Producto> productosSolicitados) {

    }

    public String mostrarProductos() {
        StringBuilder texto = new StringBuilder();

        for(Map.Entry<String, Producto> entry : productos.entrySet()) {
            texto.append(entry.getValue().toString());
        }

        if(texto.toString().length() == 0) {
            throw new RuntimeException("No hay productos cargados en la tienda.");
        }

        return texto.toString();
    }

    private int obtenerStockActual() {
        int contador = 0;

        for (Map.Entry<String, Producto> entry : productos.entrySet()) {
            Producto valor = entry.getValue();
            contador += valor.getCantidad();
        }

        return contador;
    }

    private void setMaxStock(int maxStock) {
        this.maxStock = maxStock;
    }

    private void setSaldoCaja(BigDecimal saldoCaja) {
        this.saldoCaja = saldoCaja;
    }

    public String getNombre() {
        return nombre;
    }

    public int getMaxStock() {
        return maxStock;
    }

    public BigDecimal getSaldo() {
        return saldoCaja;
    }
}
