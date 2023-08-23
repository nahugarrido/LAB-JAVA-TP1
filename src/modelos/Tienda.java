package modelos;

import enums.TipoProducto;
import excepciones.StockMaximoException;
import excepciones.TipoProductoNoContempladoException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Tienda {
    private String nombre;
    private int maxStock;
    private BigDecimal saldo;
    /* Utilizo un String y no un Enum como key para que en el caso de que se agreguen nuevos tipos de productos,
     estos puedan ser agregados a la lista sin necesidad de modificar el enum y la implementacion de los metodos de la tienda */
    private Map<String, List<Producto>> productos;

    public Tienda(String nombre, int maxStock, BigDecimal saldo) {
        this.nombre = nombre;
        this.maxStock = maxStock;
        this.saldo = saldo;
        this.productos = new HashMap<>();
    }

    public void agregarProducto(Producto producto) {
        /// Verificacion stock maximo de tienda
        if(this.maxStock <  this.maxStock + producto.getCantidad()) {
            throw new StockMaximoException(this.getMaxStock(), this.maxStock + producto.getCantidad());
        }

        /// Obtener key basandome en el nombre de la clase
        String tipoProducto = producto.getClass().getSimpleName();

        /// Inicializar lista en caso de no estar inicializada
        if(!productos.containsKey(tipoProducto)) {
            productos.put(tipoProducto, new ArrayList<>());
        }

        /// Agregar producto
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

    public String getNombre() {
        return nombre;
    }

    public int getMaxStock() {
        return maxStock;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }
}
