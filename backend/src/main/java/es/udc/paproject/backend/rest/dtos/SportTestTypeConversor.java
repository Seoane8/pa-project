package es.udc.paproject.backend.rest.dtos;

import es.udc.paproject.backend.model.entities.SportTestType;

import java.util.List;
import java.util.stream.Collectors;

public class SportTestTypeConversor {
    private SportTestTypeConversor() {}

    public final static SportTestTypeDto toSportTestTypeDto(SportTestType type) {
        return new SportTestTypeDto(type.getId(), type.getName());
    }

    public final static List<SportTestTypeDto> toSportTestTypeDtos(List<SportTestType> types) {
        return types.stream().map(c -> toSportTestTypeDto(c)).collect(Collectors.toList());
    }
}
