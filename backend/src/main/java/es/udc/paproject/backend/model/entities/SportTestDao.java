package es.udc.paproject.backend.model.entities;

import org.springframework.data.domain.Slice;

import java.time.LocalDate;

public interface SportTestDao {

    Slice<SportTest> findRaces(Province province, SportTestType type, LocalDate startDate, LocalDate finishDate);

}