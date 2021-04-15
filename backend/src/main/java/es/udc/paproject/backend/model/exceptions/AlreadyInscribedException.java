package es.udc.paproject.backend.model.exceptions;

public class AlreadyInscribedException extends Exception {

    Long sportTestId;
    Long userId;

    public AlreadyInscribedException(Long sportTestId, Long userId) {
        super("Already inscribed in sportTest " + sportTestId + " with id " + userId);
        this.sportTestId = sportTestId;
        this.userId = userId;
    }

    public Long getSportTestId() {
        return sportTestId;
    }

    public void setSportTestId(Long sportTestId) {
        this.sportTestId = sportTestId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserName(Long userId) {
        this.userId = userId;
    }
}
