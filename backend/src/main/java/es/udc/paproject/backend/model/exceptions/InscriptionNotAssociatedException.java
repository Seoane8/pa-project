package es.udc.paproject.backend.model.exceptions;

public class InscriptionNotAssociatedException extends Exception {

    Long inscriptionId;
    Long sportTestId;

    public InscriptionNotAssociatedException(Long inscriptionId, Long sportTestId) {
        super("Inscription with id " + inscriptionId + "is not associated with sport test with id " + sportTestId);
        this.inscriptionId = inscriptionId;
        this.sportTestId = sportTestId;
    }

    public Long getInscriptionId() {
        return inscriptionId;
    }

    public void setInscriptionId(Long inscriptionId) {
        this.inscriptionId = inscriptionId;
    }

    public Long getSportTestId() {
        return sportTestId;
    }

    public void setSportTestId(Long sportTestId) {
        this.sportTestId = sportTestId;
    }
}
