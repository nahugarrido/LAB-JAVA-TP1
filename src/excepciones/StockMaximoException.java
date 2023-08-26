package excepciones;

public class StockMaximoException extends RuntimeException {
    private int stockMax;
    private int stockNoValido;

    public StockMaximoException(int stockMax, int stockNoValido) {
        this.stockMax = stockMax;
        this.stockNoValido = stockNoValido;
    }

    @Override
    public String getMessage() {
        return "Stock maximo de la tienda sobrepasado, stock maximo: " + this.getStockMax() + ", stock no valido: " + this.getStockNoValido();
    }

    public int getStockMax() {
        return stockMax;
    }

    public int getStockNoValido() {
        return stockNoValido;
    }
}
