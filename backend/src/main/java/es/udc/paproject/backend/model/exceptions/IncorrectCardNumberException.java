package es.udc.paproject.backend.model.exceptions;

public class IncorrectCardNumberException extends Exception {

    Long inscriptionId;
    String userName;

    public IncorrectCardNumberException(Long inscriptionId, String userName) {
        super("Inscription with id " + inscriptionId +
                " of the user " + userName +
                " was not done with given credit card");
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
