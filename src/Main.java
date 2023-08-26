import enums.TipoAplicacion;
import enums.TipoEnvase;
import excepciones.SaldoInsuficienteCajaException;
import modelos.*;

import java.math.BigDecimal;
import java.time.LocalDate;
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
            System.out.println("Opción 6 - Añadir fechas de caducidad y calorias (Inicialmente se les asignan valores por defecto null y 0)");
            System.out.println("Opción 7 - Actualizar descuentos de productos (Inicialmente el descuento de todos los productos es 0)");
            System.out.println("Opción 8 - Actualizar descuentos de productos (No cumple con condición descuento maximo)");
            System.out.println("Opción X - Realizar venta exitosamente *** PENDIENTE ***");
            System.out.println("Opción X - Realizar venta con casos especiales *** PENDIENTE ***");
            System.out.println("Opción X - Obtener comestibles con descuento menor a 10% con API STREAMS *** PENDIENTE ***");
            System.out.println("Opción X - Obtener productos generando ganancias menores a 10%  con API STREAMS *** PENDIENTE ***");

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
                    case "6":
                        tienda.establecerFechaVencimientoProducto("AB101",LocalDate.of(2024,6,27));
                        tienda.establecerCaloriasProducto("AB101", 60);
                        tienda.establecerFechaVencimientoProducto("AC101",LocalDate.of(2024,2,2));
                        tienda.establecerCaloriasProducto("AC101", 120);
                        tienda.establecerFechaVencimientoProducto("AC102",LocalDate.of(2021,1,1)); /// VENCIDO
                        tienda.establecerCaloriasProducto("AC102", 160);
                        System.out.println("Fechas de vencimiento y calorias actualizadas con exito.");
                        break;
                    case "7":
                        tienda.establecerDescuentoProducto("AB101", 0.05);
                        tienda.establecerDescuentoProducto("AC101", 0.1);
                        tienda.establecerDescuentoProducto("AC102", 0.15);
                        tienda.establecerDescuentoProducto("AZ101", 0.20);
                        tienda.establecerDescuentoProducto("AZ102", 0.21);
                        tienda.establecerDescuentoProducto("AZ103", 0.22);
                        System.out.println("Descuentos actualizados con exito.");
                        break;
                    case "8":
                        tienda.establecerDescuentoProducto("AB101", 0.5);
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