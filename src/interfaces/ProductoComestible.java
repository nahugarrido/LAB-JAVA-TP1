package interfaces;

import java.time.LocalDate;

public interface ProductoComestible {

    void setFechaVencimiento(LocalDate fechaVencimiento);

    LocalDate getFechaVencimiento();

    void setCalorias(int calorias);

    int getCalorias();
}
