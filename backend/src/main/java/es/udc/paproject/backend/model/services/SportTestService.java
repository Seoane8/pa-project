package es.udc.paproject.backend.model.services;

import es.udc.paproject.backend.model.entities.Province;
import es.udc.paproject.backend.model.entities.SportTest;
import es.udc.paproject.backend.model.entities.SportTestType;
import es.udc.paproject.backend.model.exceptions.InstanceNotFoundException;

import java.time.LocalDate;
import java.util.List;

public interface SportTestService {

    List<Province> findAllProvinces();

    List<SportTestType> findAllSportTestTypes();

    SportTest findSportTestById(Long id) throws InstanceNotFoundException;

    Block<SportTest> findSportTests(Long provinceId, Long typeId, LocalDate startDate, LocalDate finishDate, int page, int size);

}
