package es.udc.paproject.backend.rest.dtos;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class InscribeParamsDto {
    public Long sportTestId;
    public String cardNumber;

    @NotNull
    public Long getSportTestId() { return sportTestId; }

    public void setSportTestId(Long sportTestId) { this.sportTestId = sportTestId; }

    @NotNull
    @Size(min=14, max=19)
    public String getCardNumber() { return cardNumber; }

    public void setCardNumber(String cardNumber) { this.cardNumber = cardNumber; }
}
