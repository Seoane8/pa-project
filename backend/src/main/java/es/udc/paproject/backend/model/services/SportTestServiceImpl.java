package es.udc.paproject.backend.model.services;


import es.udc.paproject.backend.model.entities.*;
import es.udc.paproject.backend.model.exceptions.InstanceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class SportTestServiceImpl implements SportTestService{

    @Autowired
    private ProvinceDao provinceDao;

    @Autowired
    private SportTestDao sportTestDao;


    @Override
    public List<Province> findAllProvinces() {
        Iterable<Province> provinces = provinceDao.findAll(Sort.by(Sort.Direction.ASC, "name"));
        List<Province> provincesAsList = new ArrayList<>();

        provinces.forEach(p -> provincesAsList.add(p));

        return provincesAsList;
    }

    @Override
    public List<SportTestType> findAllSportTestTypes() {
        return null;
    }

    @Override
    public SportTest findSportTestById(Long id) throws InstanceNotFoundException {
        return null;
    }

    @Override
    public Block<SportTest> findSportTests(Province province, SportTestType type, LocalDate startDate, LocalDate finishDate, int page, int size) {
        return null;
    }
}
