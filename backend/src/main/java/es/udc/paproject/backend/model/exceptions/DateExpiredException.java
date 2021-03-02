package es.udc.paproject.backend.model.exceptions;

public class DateExpiredException extends Exception{

    private Long sportTestId;

    public DateExpiredException(Long sportTestId) {
        super("Ya han pasado más de 15 días desde la celebración de la prueba con ID " + sportTestId);
        this.sportTestId = sportTestId;
    }

    public Long getSportTestId() {
        return sportTestId;
    }
}
