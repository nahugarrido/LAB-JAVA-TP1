import enums.TipoAplicacion;
import enums.TipoEnvase;
import excepciones.SaldoInsuficienteCajaException;
import modelos.*;

import java.math.BigDecimal;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Tienda tienda = new Tienda("Tienda Bootcamp", 1000, BigDecimal.valueOf(50000));
        Scanner scan = new Scanner(System.in);
        boolean flag = true;
        String opcion = "";

        do {
            System.out.println("******************************************************");
            System.out.println("Navegación - " + tienda.getNombre());
            System.out.println("******************************************************");
            System.out.println("Opción 1 - Mostrar Productos (Testear resultado de operaciones)");
            System.out.println("Opción 2 - Mostrar Tienda (Testear resultado de operaciones)");
            System.out.println("Opción 3 - Agregar Productos (La tienda no cuenta con productos inicialmente)");
            System.out.println("Opción 4 - Agregar Productos (Exceder saldo de la tienda)");
            System.out.println("Opción 5 - Agregar Productos (Exceder stock maximo de la tienda)");
            System.out.println("Opción 6 - Agregar Productos (No cumple con condicion precio de venta - Productos Comestibles) *** PENDIENTE ***");
            System.out.println("Opción 7 - Agregar Productos (No cumple con condicion precio de venta - Productos de Limpieza (1)) *** PENDIENTE ***");
            System.out.println("Opción 8 - Agregar Productos (No cumple con condicion precio de venta - Productos de Limpieza (2)) *** PENDIENTE ***");
            System.out.println("Opción 9 - Añadir fechas de caducidad y calorias (Inicialmente se les asignan valores por defecto null y 0) *** PENDIENTE ***");
            System.out.println("Opción 10 - Añadir descuentos a productos *** PENDIENTE ***");
            System.out.println("Opción 11 - Añadir descuentos a productos (no cumple con condicion BEBIDAS) *** PENDIENTE ***");
            System.out.println("Opción 12 - Añadir descuentos a productos (no cumple con condicion ENVASADOS) *** PENDIENTE ***");
            System.out.println("Opción 13 - Añadir descuentos a productos (no cumple con condicion LIMPIEZA) *** PENDIENTE ***");
            System.out.println("Opción 14 - Añadir descuentos a productos (no cumple con condicion GENERA PERDIDAS) *** PENDIENTE ***");
            System.out.println("Opción 15 - Realizar venta exitosamente *** PENDIENTE ***");
            System.out.println("Opción 16 - Realizar venta con casos especiales *** PENDIENTE ***");
            System.out.println("Opción 17 - Obtener comestibles con descuento menor a 10% con API STREAMS *** PENDIENTE ***");
            System.out.println("Opción 18 - Obtener productos generando ganancias menores a 10%  con API STREAMS *** PENDIENTE ***");

            System.out.println("Opción X - Salir");
            System.out.println("******************************************************");
            System.out.println("Selecciona una opción: ");

            opcion = scan.nextLine();

            try {
                switch (opcion) {
                    case "1":
                        System.out.println(tienda.mostrarProductos());
                        break;
                    case "2":
                        System.out.println(tienda.toString());
                        break;
                    case "3":
                        Producto productoLimpieza1 = new ProductoLimpieza("Detergente Ala", 100, BigDecimal.valueOf(100), BigDecimal.valueOf(50), true, TipoAplicacion.COCINA);
                        Producto productoLimpieza2 = new ProductoLimpieza("Desengrasante Vick", 50, BigDecimal.valueOf(80), BigDecimal.valueOf(100), true, TipoAplicacion.COCINA);
                        Producto productoLimpieza3 = new ProductoLimpieza("Cera para pisos", 50, BigDecimal.valueOf(60), BigDecimal.valueOf(90), true, TipoAplicacion.PISOS);
                        Producto productoBebida1 = new ProductoBebida("Gaseosa Coca Cola", 50, BigDecimal.valueOf(30), BigDecimal.valueOf(40), true, false, false);
                        Producto productoBebida2 = new ProductoBebida("Whisky Blue Label", 10, BigDecimal.valueOf(2500), BigDecimal.valueOf(3000), true, false, true,0.32);
                        Producto productoEnvasado1 = new ProductoEnvasado("Queso La Vaca Feliz", 50, BigDecimal.valueOf(30), BigDecimal.valueOf(40), true, TipoEnvase.PLASTICO, false);
                        tienda.agregarProducto(productoLimpieza1);
                        tienda.agregarProducto(productoLimpieza2);
                        tienda.agregarProducto(productoLimpieza3);
                        tienda.agregarProducto(productoBebida1);
                        tienda.agregarProducto(productoBebida2);
                        tienda.agregarProducto(productoEnvasado1);
                        System.out.println("Productos agregado con exito.");
                        break;
                    case "4":
                        Producto productoBebida4 = new ProductoBebida("Whisky Red Label", 50, BigDecimal.valueOf(2500), BigDecimal.valueOf(3000), true, false, true,0.32);
                        tienda.agregarProducto(productoBebida4);
                        break;
                    case "5":
                        Producto productoEnvasado2 = new ProductoEnvasado("Caramelo", 1001, BigDecimal.valueOf(1), BigDecimal.valueOf(10), true, TipoEnvase.PLASTICO, false);
                        tienda.agregarProducto(productoEnvasado2);
                        break;
                    default:
                        System.out.println("Debes seleccionar una opcion valida.");
                        break;
                }
            } catch(RuntimeException e) {
                System.out.println( "MENSAJE DE ERROR: " + e.getMessage());
            }

        } while(flag);
    }
}