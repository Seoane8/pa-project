package es.udc.paproject.backend.model.entities;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Inscription {

    private Long id;
    private User user;
    private SportTest sportTest;
    private String cardNumber;
    private LocalDateTime reservationDate;
    private float price;
    private int dorsal;
    private boolean dorsalCollected;
    private int score;
    private int version;

    public Inscription() {}

    public Inscription(User user, SportTest sportTest, String cardNumber,
                       LocalDateTime reservationDate, float price, int dorsal) {
        this.user = user;
        this.sportTest = sportTest;
        this.cardNumber = cardNumber;
        this.reservationDate = reservationDate;
        this.price = price;
        this.dorsal = dorsal;
        this.dorsalCollected = false;
        this.score = -1;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ManyToOne(optional=false, fetch=FetchType.LAZY)
    @JoinColumn(name= "userId")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @ManyToOne(optional=false, fetch=FetchType.LAZY)
    @JoinColumn(name= "sportTestId")
    public SportTest getSportTest() {
        return sportTest;
    }

    public void setSportTest(SportTest sportTest) {
        this.sportTest = sportTest;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public LocalDateTime getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(LocalDateTime reservationDate) {
        this.reservationDate = reservationDate;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getDorsal() {
        return dorsal;
    }

    public void setDorsal(int dorsal) {
        this.dorsal = dorsal;
    }

    public boolean isDorsalCollected() { return dorsalCollected; }

    public void setDorsalCollected(boolean dorsalCollected) { this.dorsalCollected = dorsalCollected; }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Version
    public int getVersion() { return version; }

    public void setVersion(int version) { this.version = version; }

    public boolean ratingEnabled() {
        return this.score == -1 &&
                this.sportTest.getDate().isBefore(LocalDateTime.now()) &&
                this.getSportTest().getDate().plusDays(15).isAfter(LocalDateTime.now());
    }
}
