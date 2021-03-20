package es.udc.paproject.backend.model.entities;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class SportTest {

    private Long id;
    private String name;
    private String description;
    private LocalDateTime date;
    private float price;
    private int maxParticipants;
    private int numParticipants;
    private SportTestType type;
    private String location;
    private Province province;
    private int rating;
    private int numRatings;
    private int version;

    public SportTest() {}

    public SportTest(String name, String description, LocalDateTime date, float price,
                     int maxParticipants, int numParticipants, SportTestType type, String location,
                     Province province) {
        this.name = name;
        this.description = description;
        this.date = date;
        this.price = price;
        this.maxParticipants = maxParticipants;
        this.numParticipants = numParticipants;
        this.type = type;
        this.location = location;
        this.province = province;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getMaxParticipants() {
        return maxParticipants;
    }

    public void setMaxParticipants(int maxParticipants) {
        this.maxParticipants = maxParticipants;
    }

    public int getNumParticipants() {
        return numParticipants;
    }

    public void setNumParticipants(int numParticipants) {
        this.numParticipants = numParticipants;
    }

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "sportTestTypeId")
    public SportTestType getType() {
        return type;
    }

    public void setType(SportTestType type) {
        this.type = type;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "provinceId")
    public Province getProvince() {
        return province;
    }

    public void setProvince(Province province) {
        this.province = province;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getNumRatings() {
        return numRatings;
    }

    public void setNumRatings(int numRatings) {
        this.numRatings = numRatings;
    }

    @Version
    public int getVersion() { return version; }

    public void setVersion(int version) { this.version = version; }

    public int addParticipant() {
        return this.numParticipants == this.maxParticipants ? -1 : ++this.numParticipants;
    }

    public void actualizeScore(int score) {
        this.rating = (rating * numRatings + score) / (numRatings + 1);
        this.numRatings = numRatings + 1;
    }
}
