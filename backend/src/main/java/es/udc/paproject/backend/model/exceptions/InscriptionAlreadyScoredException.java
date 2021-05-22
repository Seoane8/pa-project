package es.udc.paproject.backend.model.exceptions;

public class InscriptionAlreadyScoredException extends Exception{

    private Long inscriptionId;

    public InscriptionAlreadyScoredException(Long inscriptionId) {
        super("Inscription with ID " + inscriptionId + " has already been previously scored");
        this.inscriptionId = inscriptionId;
    }

    public Long getInscriptionId() {
        return inscriptionId;
    }
}
