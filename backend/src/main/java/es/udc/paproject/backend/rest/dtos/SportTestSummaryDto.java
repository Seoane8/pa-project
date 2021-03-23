package es.udc.paproject.backend.rest.dtos;

import java.time.LocalDateTime;

public class SportTestSummaryDto {

    private Long id;
    private String name;
    private Long provinceId;
    private Long sportTestType;
    private long date;

    public SportTestSummaryDto () {}

    public SportTestSummaryDto(Long id, String name, Long provinceId,
                               Long sportTestType, long date) {
        this.id = id;
        this.name = name;
        this.provinceId = provinceId;
        this.sportTestType = sportTestType;
        this.date = date;
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

    public Long getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Long provinceId) {
        this.provinceId = provinceId;
    }

    public Long getSportTestType() {
        return sportTestType;
    }

    public void setSportTestType(Long sportTestType) {
        this.sportTestType = sportTestType;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }
}
