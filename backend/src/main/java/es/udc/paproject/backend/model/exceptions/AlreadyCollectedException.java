package es.udc.paproject.backend.model.exceptions;

public class AlreadyCollectedException extends Exception {

    Long inscriptionId;
    String userName;

    public AlreadyCollectedException(Long inscriptionId, String userName) {
        super("The dorsal corresponding to the " +
                "inscription with id " + inscriptionId +
                " of the user " + userName +
                " has already been collected previously");
        this.inscriptionId = inscriptionId;
        this.userName = userName;
    }

    public Long getInscriptionId() {
        return inscriptionId;
    }

    public void setInscriptionId(Long inscriptionId) {
        this.inscriptionId = inscriptionId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
