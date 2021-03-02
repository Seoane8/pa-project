package es.udc.paproject.backend.model.exceptions;

public class SportTestNotStartedYetException extends Exception{

    private Long sportTestId;

    public SportTestNotStartedYetException(Long sportTestId) {
        super("La prueba deportiva con ID " + sportTestId + " aún no comenzó");
        this.sportTestId = sportTestId;
    }

    public Long getSportTestId() {
        return sportTestId;
    }
}
