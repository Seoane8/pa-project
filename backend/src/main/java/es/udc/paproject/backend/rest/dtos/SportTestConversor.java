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
                sporttest.getType().getId(), toMillis(sporttest.getDate()));
    }

    private final static long toMillis(LocalDateTime date) {
        return date.truncatedTo(ChronoUnit.MINUTES).atZone(ZoneOffset.systemDefault()).toInstant().toEpochMilli();
    }
}
