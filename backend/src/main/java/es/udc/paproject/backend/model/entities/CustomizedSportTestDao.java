package es.udc.paproject.backend.model.entities;

import org.springframework.data.domain.Slice;

import java.time.LocalDate;

public interface CustomizedSportTestDao {

    Slice<SportTest> findRaces(Province province, SportTestType type, LocalDate startDate, LocalDate finishDate, int page, int size);

}
