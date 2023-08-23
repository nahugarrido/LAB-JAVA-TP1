package excepciones;

public class IdentificadorNoValidoException extends RuntimeException{
    private final String identificador;

    public IdentificadorNoValidoException(String identificador) {
        super();
        this.identificador = identificador;
    }

    @Override
    public String getMessage() {
        return "El identificador proporcionado no es un identificador valido, identificador: " + this.getIdentificador();
    }

    public String getIdentificador() {
        return identificador;
    }
}
