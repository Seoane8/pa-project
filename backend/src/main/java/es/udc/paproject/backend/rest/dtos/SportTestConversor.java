package es.udc.paproject.backend.rest.dtos;

import es.udc.paproject.backend.model.entities.SportTest;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

public class SportTestConversor {

    private SportTestConversor() {}


    public final static List<SportTestSummaryDto> toSportTestSummaryDtos(List<SportTest> sporttests) {
        return sporttests.stream().map(s -> toSportTestSummaryDto(s)).collect(Collectors.toList());
    }

    private final static SportTestSummaryDto toSportTestSummaryDto(SportTest sporttest) {
        return new SportTestSummaryDto(sporttest.getId(), sporttest.getName(), sporttest.getProvince().getId(),
                sporttest.getType().getId(), toMillis(sporttest.getDate()), sporttest.getRating());
    }

    private final static long toMillis(LocalDateTime date) {
        return date.truncatedTo(ChronoUnit.MINUTES).atZone(ZoneOffset.systemDefault()).toInstant().toEpochMilli();
    }


    public final static SportTestDto toSportTestDto(SportTest sporttest) {
        return new SportTestDto(sporttest.getId(), sporttest.getName(), toMillis(sporttest.getDate()), sporttest.getType().getId(), sporttest.getProvince().getId(),
                sporttest.getRating(), sporttest.getLocation(), sporttest.getPrice(), sporttest.getMaxParticipants(), sporttest.getNumParticipants(), sporttest.getDescription(),
                sporttest.registrationEnabled(), sporttest.dorsalDeliveryEnabled());
    }

    public final static List<SportTestDto> toSportTestDtos(List<SportTest> sporttests) {
        return sporttests.stream().map(s -> toSportTestDto(s)).collect(Collectors.toList());
    }
}
