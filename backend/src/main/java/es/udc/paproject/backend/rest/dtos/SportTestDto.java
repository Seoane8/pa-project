package es.udc.paproject.backend.rest.dtos;

import es.udc.paproject.backend.model.entities.Province;
import es.udc.paproject.backend.model.entities.SportTestType;

import java.time.LocalDateTime;

public class SportTestDto {
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

    public SportTestDto() {
    }

    public SportTestDto(Long id, String name, String description, LocalDateTime date,
                        float price, int maxParticipants, int numParticipants, SportTestType type, String location,
                        Province province, int rating, int numRatings, int version) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.date = date;
        this.price = price;
        this.maxParticipants = maxParticipants;
        this.numParticipants = numParticipants;
        this.type = type;
        this.location = location;
        this.province = province;
        this.rating = rating;
        this.numRatings = numRatings;
        this.version = version;
    }

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

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}
