package es.udc.paproject.backend.rest.dtos;

public class CollectDorsalResponseDto {

    int dorsal;

    public CollectDorsalResponseDto(int dorsal) {
        this.dorsal = dorsal;
    }

    public int getDorsal() { return dorsal; }

    public void setDorsal(int dorsal) { this.dorsal = dorsal; }
}
