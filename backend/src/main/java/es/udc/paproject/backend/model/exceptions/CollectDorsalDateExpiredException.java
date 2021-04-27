package es.udc.paproject.backend.model.exceptions;

import java.time.LocalDateTime;

public class CollectDorsalDateExpiredException extends Throwable {

    private Long inscriptionId;
    private LocalDateTime date;

    public CollectDorsalDateExpiredException(Long inscriptionId, LocalDateTime date) {
        super("Collect dorsal is not allowed after the sportTest (date = " + date.toString() + ")");
        this.inscriptionId = inscriptionId;
        this.date = date;
    }

    public Long getInscriptionId() {
        return inscriptionId;
    }

    public void setInscriptionId(Long inscriptionId) {
        this.inscriptionId = inscriptionId;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
