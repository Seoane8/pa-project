package es.udc.paproject.backend.rest.dtos;

public class InscriptionDto {

    private Long id;
    private Long sportTestId;
    private String sportTestName;
    private String cardNumber;
    private long date;
    private int dorsal;
    private boolean dorsalCollected;
    private int score;

    public InscriptionDto() {}

    public InscriptionDto(Long id, Long sportTestId, String sportTestName, String cardNumber,
                          long date, int dorsal, boolean dorsalCollected, int score) {
        this.id = id;
        this.sportTestId = sportTestId;
        this.sportTestName = sportTestName;
        this.cardNumber = cardNumber;
        this.date = date;
        this.dorsal = dorsal;
        this.dorsalCollected = dorsalCollected;
        this.score = score;
    }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public Long getSportTestId() { return sportTestId; }

    public void setSportTestId(Long sportTestId) { this.sportTestId = sportTestId; }

    public String getSportTestName() { return sportTestName; }

    public void setSportTestName(String sportTestName) { this.sportTestName = sportTestName; }

    public String getCardNumber() { return cardNumber; }

    public void setCardNumber(String cardNumber) { this.cardNumber = cardNumber; }

    public long getDate() { return date; }

    public void setDate(long date) { this.date = date; }

    public int getDorsal() { return dorsal; }

    public void setDorsal(int dorsal) { this.dorsal = dorsal; }

    public boolean isDorsalCollected() { return dorsalCollected; }

    public void setDorsalCollected(boolean dorsalCollected) { this.dorsalCollected = dorsalCollected; }

    public int getScore() { return score; }

    public void setScore(int score) { this.score = score; }
}
