package utiles;

import configuracion.Configuracion;

public class GeneradorID {
    private static Configuracion configuracion = Configuracion.getInstance();

    /* EL identificador de producto envasado debe ser de la forma ABXXX donde XXX son digitos */
    public static String generarIDEnvasado() {
        int nuevoId = configuracion.getContadorEnvasado() + 1;
        configuracion.setContadorEnvasado(nuevoId);
        return "AB" + nuevoId;
    }

    /* EL identificador de producto bebida debe ser de la forma ACXXX donde XXX son digitos */
    public static String generarIDBebida() {
        int nuevoId = configuracion.getContadorBebida() + 1;
        configuracion.setContadorBebida(nuevoId);
        return "AC" + nuevoId;
    }

    /* EL identificador de producto envasado debe ser de la forma AZXXX donde XXX son digitos */
    public static String generarIDLimpieza() {
        int nuevoId = configuracion.getContadorLimpieza() + 1;
        configuracion.setContadorLimpieza(nuevoId);
        return "AZ" + nuevoId;
    }

}
