package es.udc.paproject.backend.model.entities;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface InscriptionDao extends PagingAndSortingRepository<Inscription, Long> {

    Slice<Inscription> findByUserIdOrderByReservationDateDesc(Long userId, Pageable pageable);

}
