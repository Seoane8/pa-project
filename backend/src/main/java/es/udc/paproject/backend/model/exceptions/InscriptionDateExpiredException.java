package es.udc.paproject.backend.model.exceptions;

import java.time.LocalDateTime;

public class InscriptionDateExpiredException extends Exception {

    Long sportTestId;
    LocalDateTime date;

    public InscriptionDateExpiredException(Long sportTestId, LocalDateTime date) {
        super("Inscriptions are not allowed withing 24 hours of the sport test");
        this.sportTestId = sportTestId;
        this.date = date;
    }

    public Long getSportTestId() {
        return sportTestId;
    }

    public void setSportTestId(Long sportTestId) {
        this.sportTestId = sportTestId;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
