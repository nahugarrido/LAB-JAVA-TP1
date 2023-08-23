package excepciones;

import java.math.BigDecimal;

public class SaldoInsuficienteCajaException extends RuntimeException {
    private BigDecimal saldoCaja;
    private BigDecimal montoOperacion;

    public SaldoInsuficienteCajaException(BigDecimal saldoCaja, BigDecimal montoOperacion) {
        this.saldoCaja = saldoCaja;
        this.montoOperacion = montoOperacion;
    }

    @Override
    public String getMessage() {
        return "No tienes fondos suficientes para realizar la operación, tus fondos: " +
                "($" + this.getSaldoCaja() + ")" +
                ", monto de operación: " +
                "($" + this.getMontoOperacion() + ")";
    }

    public BigDecimal getSaldoCaja() {
        return saldoCaja;
    }

    public BigDecimal getMontoOperacion() {
        return montoOperacion;
    }
}
