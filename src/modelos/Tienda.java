package modelos;

import dto.SolicitudVenta;
import enums.TipoAplicacion;
import excepciones.*;
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
    private List<Venta> ventas;
    private TreeMap<String, Producto> productos;

    public Tienda(String nombre, int maxStock, BigDecimal saldoCaja) {
        this.nombre = nombre;
        this.maxStock = maxStock;
        this.saldoCaja = saldoCaja;
        this.ventas = new ArrayList<>();
        this.productos = new TreeMap<>();
    }

    @Override
    public String toString() {
        return  "Nombre: " + nombre +
                " * Stock Maximo: " + maxStock +
                " * Saldo en caja: " + saldoCaja + "\n";

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

    public void establecerDescuentoProducto(String identificador, double porcentajeDescuento) {
        /// Obtener el producto a actualizar
        ProductoDescontable productoDescontable = (ProductoDescontable) productos.get(identificador);

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

    public void realizarVenta(List<SolicitudVenta> solicitudVentas) {
        int maximaCantidadProductos = 3;
        int maximaCantidadPorItem = 10;

        /// Mapear dtos a productos
        List<Producto> productosSolicitados = mapearItemsVenta(solicitudVentas);

        /// Verificaciones previas a venta y modificacion en cantidades
        productosSolicitados = verificacionesPreviasVenta(productosSolicitados, maximaCantidadProductos, maximaCantidadPorItem);

        /// Remover productos de la venta
        productosSolicitados = removerProductosVenta(productosSolicitados);

        /// Verificar porcentajes de ganancias
        verificarPorcentajeGanancias(productosSolicitados);

        /// Realizar venta
        /// monto antes de aplicar ningun descuento
        BigDecimal montoTotalVenta = new BigDecimal(0);
        /// monto despues de aplicar descuentos
        BigDecimal montoSubTotalVenta = new BigDecimal(0);
        /// impuestos en la venta
        BigDecimal impuestosVenta = new BigDecimal(0);
        /// monto abonado
        BigDecimal montoAbonadoTotal = new BigDecimal(0);

        for(Producto item : productosSolicitados) {
            BigDecimal subTotalItem;

            /// Verificacion de descuentos aplicables (Cuando se establecen los descuentos se verifica que respeten las restricciones)
            if(item.getPrecioVentaConDescuento().compareTo(item.getPrecioCompra()) < 0) {
                subTotalItem = item.getPrecioVenta().multiply(BigDecimal.valueOf(item.getCantidad()));
                System.out.println("El descuento registrado para el producto " + item.getIdentificador() + " no pudo ser aplicado");
            } else {
                subTotalItem = item.getPrecioVentaConDescuento().multiply(BigDecimal.valueOf(item.getCantidad()));
            }

            /// Verificar si el producto es importado y actualizar monto
            if(item instanceof ProductoBebida) {
                ProductoBebida aux = (ProductoBebida) item;
                if(aux.isEsImportado()) {
                    impuestosVenta = impuestosVenta.add(subTotalItem.multiply(BigDecimal.valueOf(0.1)));
                }
            }

            /// Verificar si el producto es importado y actualizar monto
            if(item instanceof ProductoEnvasado) {
                ProductoEnvasado aux = (ProductoEnvasado) item;
                if(aux.isEsImportado()) {
                    impuestosVenta = impuestosVenta.add(subTotalItem.multiply(BigDecimal.valueOf(0.1)));
                }
            }

            /// Actualizar monto total venta
            montoTotalVenta = montoTotalVenta.add(item.getPrecioVenta().multiply(BigDecimal.valueOf(item.getCantidad())));

            /// Actualizar monto subtotal venta
            montoSubTotalVenta = montoSubTotalVenta.add(subTotalItem);
        }

        /// modificar stock de productos en tienda
        for(Producto productoSolicitado : productosSolicitados) {
            String identificador = productoSolicitado.getIdentificador();

            Producto aux = productos.get(identificador);

            aux.setCantidad( aux.getCantidad() - productoSolicitado.getCantidad());

            if(aux.getCantidad() <= 0) {
                aux.setEstaDisponible(false);
            }

            productos.put(aux.getIdentificador(), aux);
        }

        /// actualizar saldo de tienda
        this.setSaldoCaja(this.getSaldo().add(montoSubTotalVenta));

        /// Generar venta
        montoAbonadoTotal = montoSubTotalVenta.add(impuestosVenta);
        Venta ventaNueva = new Venta(productosSolicitados, montoTotalVenta, montoSubTotalVenta, impuestosVenta, montoAbonadoTotal);

        /// Agregar venta a la tienda
        ventas.add(ventaNueva);

        /// imprimir venta
        System.out.println(ventaNueva.mostrarVenta());

    }

    public String mostrarVentas() {
        String texto = "";
        for(Venta item : ventas) {
            texto += item.mostrarVenta();
        }

        return texto;
    }



    private void verificarPorcentajeGanancias(List<Producto> productosSolicitados) {
        for(Producto item : productosSolicitados) {
            double porcentajeGanancia = calcularPorcentajeGanancia(item);

            if(item instanceof ProductoComestible) {
                if(porcentajeGanancia > 0.20) {
                    throw new RuntimeException("Porcentaje de ganancia no valido");
                }
            } else if(item instanceof ProductoLimpieza) {
                if((((ProductoLimpieza) item).getTipoAplicacion() == TipoAplicacion.ROPA) || (((ProductoLimpieza) item).getTipoAplicacion() == TipoAplicacion.MULTIUSO)) {
                    if(porcentajeGanancia > 0.25) {
                        throw new RuntimeException("Porcentaje de ganancia no valido");
                    }
                } else {
                    if(porcentajeGanancia > 0.25 || porcentajeGanancia < 0.10) {
                        throw new RuntimeException("Porcentaje de ganancia no valido");
                    }
                }
            }
        }
    }

    private double calcularPorcentajeGanancia(Producto producto) {
        BigDecimal precioCompra = producto.getPrecioCompra();
        BigDecimal precioVentaConDescuento = producto.getPrecioVentaConDescuento();
        BigDecimal ganancia = precioVentaConDescuento.subtract(precioCompra);

        return (ganancia.doubleValue() / precioCompra.doubleValue()) * 100;
    }

    private List<Producto> removerProductosVenta(List<Producto> productosSolicitados) {
        /// Remover productos que no se encuentran disponibles o estan vencidos
        Iterator<Producto> iterator = productosSolicitados.listIterator();
        while (iterator.hasNext()) {
            Producto item = iterator.next();

            /// Verificar si el producto esta vencido
            if(item instanceof ProductoComestible) {
                if(((ProductoComestible) item).getFechaVencimiento().isBefore(LocalDate.now())) {
                    /// Creo un aux para evitar errores de inconsistencia
                    Producto aux = productos.get(item.getIdentificador());
                    aux.setEstaDisponible(false);
                    productos.put(aux.getIdentificador(), aux);
                }
            }

            /// Verificar si el producto no esta disponible
            if (!item.isEstaDisponible()) {
                System.out.println("El producto: " + item.getIdentificador() + ", descripcion: " + item.getDescripcion() + ", no se encuentra disponible.");
                iterator.remove();
            }
        }

        return productosSolicitados;
    }

    private List<Producto> verificacionesPreviasVenta(List<Producto> productosSolicitados, int maximoCantidadProductos, int maximaCantidadPorItem) {
        boolean flagStock = false;

        /// Verificar cantidad de productos solicitados
        if(productosSolicitados.size() > maximoCantidadProductos) {
            throw new MaximaCantidadProductosException(maximoCantidadProductos, productosSolicitados.size());
        }

        /// Verificaciones antes de realizar venta
        for(Producto item : productosSolicitados) {
            Producto producto = productos.get(item.getIdentificador());

            /// Verificar cantidades no negativas
            if(item.getCantidad() < 0) {
                throw new RuntimeException("Cantidad no valida, valor negativo.");
            }

            /// Verificar cantidad por producto solicitado
            if(item.getCantidad() > maximaCantidadPorItem) {
                throw new MaximaCantidadPorItemException(maximaCantidadPorItem, item.getCantidad());
            }

            /// Actualizar valores de la venta a unidades disponibles
            if(item.getCantidad() > producto.getCantidad()) {
                item.setCantidad(producto.getCantidad());
                flagStock = true;
            }
        }

        /// Mostrar mensaje stock disponible menor al solicitado
        if(flagStock) {
            System.out.println("Hay productos con stock disponible menor al solicitado.");
        }

        return productosSolicitados;
    }

    /* Funcion auxiliar para modularizar codigo */
    private List<Producto> mapearItemsVenta(List<SolicitudVenta> solicitudVentas) {
        List<Producto> productosSolicitados = new ArrayList<>();

        for(SolicitudVenta item : solicitudVentas) {
            String identificador = item.getIdentificador();
            /// Verificar identificador
            if(identificador.length() > 6) {
                throw new IdentificadorNoValidoException(identificador);
            } else {
                Producto aux = productos.get(identificador);
                aux.setCantidad(item.getCantidad());
                productosSolicitados.add(aux);
            }
        }

        return productosSolicitados;
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
