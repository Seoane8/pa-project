package es.udc.paproject.backend.model.exceptions;

public class NoMoreInscriptionsAllowedException extends Exception {

    Long sportTestId;

    public NoMoreInscriptionsAllowedException(Long sportTestId) {
        super("No more inscriptions allowed in race" + sportTestId);
        this.sportTestId = sportTestId;
    }

    public Long getSportTestId() {
        return sportTestId;
    }

    public void setSportTestId(Long raceId) {
        this.sportTestId = raceId;
    }
}
