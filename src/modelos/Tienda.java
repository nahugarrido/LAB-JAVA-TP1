package modelos;

import enums.TipoProducto;
import excepciones.SaldoInsuficienteCajaException;
import excepciones.StockMaximoException;
import excepciones.TipoProductoNoContempladoException;

import java.math.BigDecimal;
import java.util.*;

/* Utilizo un String y no un Enum como key para que en el caso de que se agreguen nuevos tipos de productos,
 estos puedan ser agregados a la lista sin necesidad de modificar el enum y la implementacion de los metodos de la tienda */

public class Tienda {
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

    private TipoProducto obtenerTipoProducto(Producto producto) {
        if(producto instanceof ProductoBebida) {
            return TipoProducto.BEBIDA;
        } else if(producto instanceof ProductoEnvasado) {
            return TipoProducto.ENVASADO;
        } else if(producto instanceof ProductoLimpieza) {
            return TipoProducto.LIMPIEZA;
        } else {
            throw new TipoProductoNoContempladoException(producto.getClass().getSimpleName());
        }
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
