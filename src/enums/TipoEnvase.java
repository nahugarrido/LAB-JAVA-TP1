package enums;

public enum TipoEnvase {
    PLASTICO("Plástico"), VIDRIO("Vidrio"), LATA("Lata");

    private String mensaje;

    TipoEnvase(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getMensaje() {
        return mensaje;
    }
}
