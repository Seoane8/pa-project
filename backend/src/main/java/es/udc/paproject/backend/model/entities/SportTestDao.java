package es.udc.paproject.backend.model.entities;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.time.LocalDate;

public interface SportTestDao extends PagingAndSortingRepository<SportTest, Long> {
    @Query("SELECT st FROM SportTest st WHERE " +
            "(?1 IS NULL OR st.province.id = ?1)" +
            " AND " +
            "(?2 IS NULL OR st.type.id = ?2)" +
            " AND " +
            "(?3 IS NULL OR st.date >= ?3)" +
            " AND " +
            "(?4 IS NULL OR st.date <= ?4)" +
            "ORDER BY st.date DESC")
    Slice<SportTest> findRaces(Long provinceId, Long typeId, LocalDate startDate, LocalDate finishDate, Pageable pageable);
}
