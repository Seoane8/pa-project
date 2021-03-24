package es.udc.paproject.backend.rest.dtos;

import es.udc.paproject.backend.model.entities.Province;
import es.udc.paproject.backend.model.entities.SportTestType;

import java.time.LocalDateTime;

public class SportTestDto {
    private Long id;
    private String name;
    private long date;
    private Long type;
    private Long province;
    private int media;


    public SportTestDto() {
    }

    public SportTestDto(Long id, String name, long date, Long type, Long province, int media) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.type = type;
        this.province = province;
        this.media = media;
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

    public int getMedia() {
        return media;
    }

    public void setMedia(int media) {
        this.media = media;
    }
}
