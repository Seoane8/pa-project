package es.udc.paproject.backend.rest.dtos;

public class SportTestDto {
    private Long id;
    private String name;
    private long date;
    private Long type;
    private Long province;
    private int rating;
    private String location;
    private float price;
    private int maxParticipants;
    private int numParticipants;
    private String description;


    public SportTestDto() {
    }

    public SportTestDto(Long id, String name, long date, Long type, Long province, int rating, String location,
                        float price, int maxParticipants, int numParticipants, String description) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.type = type;
        this.province = province;
        this.rating = rating;
        this.location = location;
        this.price = price;
        this.maxParticipants = maxParticipants;
        this.numParticipants = numParticipants;
        this.description = description;
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

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public Long getType() {
        return type;
    }

    public void setType(Long type) {
        this.type = type;
    }

    public Long getProvince() {
        return province;
    }

    public void setProvince(Long province) {
        this.province = province;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
