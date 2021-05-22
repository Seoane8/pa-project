package es.udc.paproject.backend.model.services;


import es.udc.paproject.backend.model.entities.*;
import es.udc.paproject.backend.model.exceptions.InstanceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class SportTestServiceImpl implements SportTestService{

    @Autowired
    private ProvinceDao provinceDao;

    @Autowired
    private SportTestTypeDao sportTestTypeDao;

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
        Iterable<SportTestType> sportTestType = sportTestTypeDao.findAll(Sort.by(Sort.Direction.ASC, "name"));
        List<SportTestType> sportTestTypeAsList = new ArrayList<>();

        sportTestType.forEach(s -> sportTestTypeAsList.add(s));

        return sportTestTypeAsList;
    }

    @Override
    public SportTest findSportTestById(Long id) throws InstanceNotFoundException {
        Optional<SportTest> test = sportTestDao.findById(id);

        if (test.isEmpty()) {
            throw new InstanceNotFoundException("entities.sportTest", id);
        }

        return test.get();
    }

    @Override
    public Block<SportTest> findSportTests(Long provinceId, Long typeId, LocalDate startDate, LocalDate finishDate, int page, int size) {

        Slice<SportTest> slice = sportTestDao.findRaces(provinceId, typeId, startDate, finishDate, PageRequest.of(page, size));

        return new Block<>(slice.getContent(), slice.hasNext());
    }
}
