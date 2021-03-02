package es.udc.paproject.backend.model.exceptions;

public class AlreadyInscribedException extends Exception {

    Long sportTestId;
    String userName;

    public AlreadyInscribedException(Long sportTestId, String userName) {
        super(userName + " already inscribed in sportTest " + sportTestId);
        this.sportTestId = sportTestId;
        this.userName = userName;
    }

    public Long getSportTestId() {
        return sportTestId;
    }

    public void setSportTestId(Long sportTestId) {
        this.sportTestId = sportTestId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
