package es.udc.paproject.backend.rest.dtos;

public class InscriptionSummaryDto {

    public Long id;
    public int dorsal;

    public InscriptionSummaryDto() {}

    public InscriptionSummaryDto(Long id, int dorsal) {
        this.id = id;
        this.dorsal = dorsal;
    }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public int getDorsal() { return dorsal; }

    public void setDorsal(int dorsal) { this.dorsal = dorsal; }
}
