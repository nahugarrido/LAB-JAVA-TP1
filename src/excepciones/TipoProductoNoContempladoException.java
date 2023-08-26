package excepciones;

import enums.TipoProducto;

public class TipoProductoNoContempladoException extends RuntimeException {
    private String tipoProducto;

    public TipoProductoNoContempladoException(String tipoProducto) {
        this.tipoProducto = tipoProducto;
    }

    @Override
    public String getMessage() {
        return "Tipo de producto no contemplado: " + this.getTipoProducto();
    }


    public String getTipoProducto() {
        return tipoProducto;
    }
}
