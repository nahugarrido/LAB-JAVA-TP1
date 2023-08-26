package configuracion;

public class Configuracion {
    private static Configuracion instancia = null;

    private int contadorBebida = 100;
    private int contadorEnvasado = 100;
    private int contadorLimpieza = 100;

    private Configuracion() {
    }

    public static Configuracion getInstance() {
        if (instancia == null) {
            instancia = new Configuracion();
        }
        return instancia;
    }

    public int getContadorBebida() {
        return contadorBebida;
    }

    public void setContadorBebida(int contadorBebida) {
        this.contadorBebida = contadorBebida;
    }

    public int getContadorEnvasado() {
        return contadorEnvasado;
    }

    public void setContadorEnvasado(int contadorEnvasado) {
        this.contadorEnvasado = contadorEnvasado;
    }

    public int getContadorLimpieza() {
        return contadorLimpieza;
    }

    public void setContadorLimpieza(int contadorLimpieza) {
        this.contadorLimpieza = contadorLimpieza;
    }
}