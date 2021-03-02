package es.udc.paproject.backend.model.exceptions;

public class InscriptionAlreadyScoredException extends Exception{

    private Long inscriptionId;

    public InscriptionAlreadyScoredException(Long inscriptionId) {
        super("La inscripción con ID " + inscriptionId + " ya ha sido puntuada previamente");
        this.inscriptionId = inscriptionId;
    }

    public Long getInscriptionId() {
        return inscriptionId;
    }
}
