package es.udc.paproject.backend.model.exceptions;

import java.time.LocalDateTime;

public class NotAllowedYetException extends Exception {

    Float inscriptionId;
    LocalDateTime date;

    public NotAllowedYetException(Float inscriptionId, LocalDateTime date) {
        super("Can only be collected 12 hours before the sport test (date = " + date.toString() + ")");
        this.inscriptionId = inscriptionId;
        this.date = date;
    }

    public Float getInscriptionId() {
        return inscriptionId;
    }

    public void setInscriptionId(Float inscriptionId) {
        this.inscriptionId = inscriptionId;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
