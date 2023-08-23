import enums.TipoAplicacion;
import modelos.Producto;
import modelos.ProductoLimpieza;
import modelos.Tienda;

import java.math.BigDecimal;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Tienda tienda = new Tienda("Tienda Bootcamp", 1000, BigDecimal.valueOf(50000));
        Scanner scan = new Scanner(System.in);
        boolean flag = true;
        String opcion = "";

        do {
            System.out.println("Navegacion - " + tienda.getNombre());
            System.out.println("Opcion 1 - Agregar Productos");
            System.out.println("Opcion X - XXX");
            System.out.println("Opcion X - Salir");

            opcion = scan.nextLine();

            switch (opcion) {
                case "1":
                    Producto productoLimpieza1 = new ProductoLimpieza("Muy buen desengrasante", 100, BigDecimal.valueOf(100), BigDecimal.valueOf(50), true, TipoAplicacion.COCINA);
                    tienda.agregarProducto(productoLimpieza1);
                    System.out.println("Productos agregado con exito.");
                    break;
                default:
                    System.out.println("Debes seleccionar una opcion valida.");
                    break;
            }

        } while(flag);
    }
}