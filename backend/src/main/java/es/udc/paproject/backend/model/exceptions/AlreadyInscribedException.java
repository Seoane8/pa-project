package es.udc.paproject.backend.model.exceptions;

public class AlreadyInscribedException extends Exception {

    Long sportTestId;
    Long inscriptionId;

    public AlreadyInscribedException(Long sportTestId, Long inscriptionId) {
        super("Already inscribed in sportTest " + sportTestId + " with id " + inscriptionId);
        this.sportTestId = sportTestId;
        this.inscriptionId = inscriptionId;
    }

    public Long getSportTestId() {
        return sportTestId;
    }

    public void setSportTestId(Long sportTestId) {
        this.sportTestId = sportTestId;
    }

    public Long getInscriptionId() {
        return inscriptionId;
    }

    public void setInscriptionId(Long inscriptionId) {
        this.inscriptionId = inscriptionId;
    }
}
