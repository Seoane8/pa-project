package es.udc.paproject.backend.model.exceptions;

public class DateExpiredException extends Exception{

    private Long sportTestId;

    public DateExpiredException(Long sportTestId) {
        super("More than 15 days have passed since the sportTest with ID " + sportTestId);
        this.sportTestId = sportTestId;
    }

    public Long getSportTestId() {
        return sportTestId;
    }
}
