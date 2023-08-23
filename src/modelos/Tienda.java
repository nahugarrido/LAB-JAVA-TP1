package modelos;

import enums.TipoProducto;
import excepciones.SaldoInsuficienteCajaException;
import excepciones.StockMaximoException;
import excepciones.TipoProductoNoContempladoException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* Utilizo un String y no un Enum como key para que en el caso de que se agreguen nuevos tipos de productos,
 estos puedan ser agregados a la lista sin necesidad de modificar el enum y la implementacion de los metodos de la tienda */

public class Tienda {
    private String nombre;
    private int maxStock;
    private BigDecimal saldoCaja;
    private Map<String, List<Producto>> productos;

    public Tienda(String nombre, int maxStock, BigDecimal saldoCaja) {
        this.nombre = nombre;
        this.maxStock = maxStock;
        this.saldoCaja = saldoCaja;
        this.productos = new HashMap<>();
    }

    public void agregarProducto(Producto producto) {
        /// Obtener key basandome en el nombre de la clase
        String tipoProducto = producto.getClass().getSimpleName();

        /// Inicializar lista en caso de no estar inicializada
        if(!productos.containsKey(tipoProducto)) {
            productos.put(tipoProducto, new ArrayList<>());
        }

        /// Verificacion stock maximo de tienda
        if(this.maxStock <  this.maxStock + producto.getCantidad()) {
            throw new StockMaximoException(this.getMaxStock(), this.maxStock + producto.getCantidad());
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
        List<Producto> listaProducto = productos.get(tipoProducto);
        listaProducto.add(producto);
        productos.put(tipoProducto, listaProducto);

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
