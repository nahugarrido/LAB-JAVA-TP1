package excepciones;

public class PorcentajeDescuentoNoValidoException extends RuntimeException {
    private double porcentajeMaximo;
    private double porcentajeNoValido;

    public PorcentajeDescuentoNoValidoException(double porcentajeMaximo, double porcentajeNoValido) {
        this.porcentajeMaximo = porcentajeMaximo;
        this.porcentajeNoValido = porcentajeNoValido;
    }

    @Override
    public String getMessage() {
        return "El maximo porcentaje de descuento es de: " + this.getPorcentajeMaximo() + ", porcentaje no valido: " + this.porcentajeNoValido;
    }

    public double getPorcentajeMaximo() {
        return porcentajeMaximo;
    }

    public double getPorcentajeNoValido() {
        return porcentajeNoValido;
    }
}
