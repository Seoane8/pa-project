package es.udc.paproject.backend.rest.dtos;

import es.udc.paproject.backend.model.entities.Inscription;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

public class InscriptionConversor {

    private InscriptionConversor() {}

    public final static InscriptionDto toInscriptionDto(Inscription inscription) {
        return new InscriptionDto(
                        inscription.getId(),
                        inscription.getSportTest().getId(),
                        inscription.getSportTest().getName(),
                        inscription.getCardNumber().substring(inscription.getCardNumber().length() - 4),
                        toMillis(inscription.getReservationDate()),
                        inscription.getDorsal(),
                        inscription.isDorsalCollected(),
                        inscription.getScore(),
                        inscription.ratingEnabled()
                    );
    }

    public final static InscriptionSummaryDto toInscriptionSummaryDto(Inscription inscription) {
        return new InscriptionSummaryDto(inscription.getId(), inscription.getDorsal());
    }

    public final static List<InscriptionDto> toInscriptionDtos(List<Inscription> inscriptions) {
        return inscriptions.stream().map(insc -> toInscriptionDto(insc)).collect(Collectors.toList());
    }

    private final static long toMillis(LocalDateTime date) {
        return date.truncatedTo(ChronoUnit.MINUTES).atZone(ZoneOffset.systemDefault()).toInstant().toEpochMilli();
    }

}
