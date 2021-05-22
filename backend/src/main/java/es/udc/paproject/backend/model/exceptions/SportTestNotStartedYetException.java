package es.udc.paproject.backend.model.exceptions;

public class SportTestNotStartedYetException extends Exception{

    private Long sportTestId;

    public SportTestNotStartedYetException(Long sportTestId) {
        super("SportTest with ID " + sportTestId + "not started yet");
        this.sportTestId = sportTestId;
    }

    public Long getSportTestId() {
        return sportTestId;
    }
}
