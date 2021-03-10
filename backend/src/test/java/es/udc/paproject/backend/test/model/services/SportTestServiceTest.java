package es.udc.paproject.backend.test.model.services;


import es.udc.paproject.backend.model.entities.*;
import es.udc.paproject.backend.model.services.Block;
import es.udc.paproject.backend.model.services.SportTestService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class SportTestServiceTest {

    private final float PRICE = (float) 9.95;
    private final int MAX_PARTICIPANTS = 20;
    private final String DESCRIPTION = "description";
    private final String LOCATION = "city";

    @Autowired
    private SportTestDao sportTestDao;

    @Autowired
    private ProvinceDao provinceDao;

    @Autowired
    private SportTestTypeDao sportTestTypeDao;

    @Autowired
    private SportTestService sportTestService;

    private SportTestType addSportTestType(String typeName) {

        return sportTestTypeDao.save(new SportTestType(typeName));
    }

    private Province addProvince(String provinceName) {

        return provinceDao.save(new Province(provinceName));
    }

    private SportTest addSportTest(String name, LocalDateTime date, SportTestType type, Province province) {

        SportTest sportTest = new SportTest(name, DESCRIPTION, date, PRICE, MAX_PARTICIPANTS, 0, type, LOCATION, province);
        return sportTestDao.save(sportTest);
    }

    @Test
    public void testFindSportTestsByProvince() {

        Province province1 = addProvince("A Coruña");
        Province province2 = addProvince("Pontevedra");

        SportTestType sportTestType1 = addSportTestType("Race");

        SportTest sportTest1 = addSportTest("Race 1", LocalDateTime.now().plusMonths(2), sportTestType1, province1);
        SportTest sportTest2 = addSportTest("Race 2", LocalDateTime.now().plusMonths(2), sportTestType1, province2);
        SportTest sportTest3 = addSportTest("Race 3", LocalDateTime.now().plusMonths(2), sportTestType1, province1);

        Block<SportTest> expectedBlock = new Block<>(Arrays.asList(sportTest1, sportTest3), false);

        assertEquals(expectedBlock, sportTestService.findSportTests(province1.getId(), null, null, null, 0, 3));
    }

    @Test
    public void testFindSportTestsBySportTestType() {

        Province province1 = addProvince("A Coruña");

        SportTestType sportTestType1 = addSportTestType("Race");
        SportTestType sportTestType2 = addSportTestType("Another");

        SportTest sportTest1 = addSportTest("Race 1", LocalDateTime.now().plusMonths(2), sportTestType1, province1);
        SportTest sportTest2 = addSportTest("Race 2", LocalDateTime.now().plusMonths(2), sportTestType2, province1);
        SportTest sportTest3 = addSportTest("Race 3", LocalDateTime.now().plusMonths(2), sportTestType2, province1);

        Block<SportTest> expectedBlock = new Block<>(Arrays.asList(sportTest1), false);

        assertEquals(expectedBlock, sportTestService.findSportTests(null, sportTestType1.getId(), null, null, 0, 3));
    }

    @Test
    public void testFindSportTestsByDate() {

        Province province1 = addProvince("A Coruña");

        SportTestType sportTestType1 = addSportTestType("Race");

        SportTest sportTest1 = addSportTest("Race 1", LocalDateTime.now().plusMonths(1), sportTestType1, province1);
        SportTest sportTest2 = addSportTest("Race 2", LocalDateTime.now().plusMonths(1), sportTestType1, province1);
        SportTest sportTest3 = addSportTest("Race 3", LocalDateTime.now().plusMonths(2), sportTestType1, province1);

        Block<SportTest> expectedBlock = new Block<>(Arrays.asList(sportTest3), false);

        assertEquals(expectedBlock, sportTestService.findSportTests(null, null, LocalDate.now().plusDays(45), LocalDate.now().plusMonths(3), 0, 3));
    }

    @Test
    public void testFindSportTestsByAllCriteria() {

        Province province1 = addProvince("A Coruña");
        Province province2 = addProvince("Pontevedra");

        SportTestType sportTestType1 = addSportTestType("Race");
        SportTestType sportTestType2 = addSportTestType("Another");

        SportTest sportTest1 = addSportTest("Race 1", LocalDateTime.now().plusMonths(2), sportTestType1, province1);
        SportTest sportTest2 = addSportTest("Race 2", LocalDateTime.now().plusMonths(2), sportTestType1, province2);
        SportTest sportTest3 = addSportTest("Race 3", LocalDateTime.now().plusMonths(3), sportTestType2, province1);

        Block<SportTest> expectedBlock = new Block<>(Arrays.asList(sportTest1), false);

        assertEquals(expectedBlock, sportTestService.findSportTests(province1.getId(), sportTestType1.getId(), LocalDate.now().plusMonths(1), LocalDate.now().plusMonths(3), 0, 3));
    }

    @Test
    public void testFindAllSportTests() {

        Province province1 = addProvince("A Coruña");
        Province province2 = addProvince("Pontevedra");
        Province province3 = addProvince("Lugo");

        SportTestType sportTestType1 = addSportTestType("Race");
        SportTestType sportTestType2 = addSportTestType("Match");
        SportTestType sportTestType3 = addSportTestType("Another");

        SportTest sportTest1 = addSportTest("Race 1", LocalDateTime.now().plusMonths(1), sportTestType1, province1);
        SportTest sportTest2 = addSportTest("Race 2", LocalDateTime.now().plusMonths(2), sportTestType2, province2);
        SportTest sportTest3 = addSportTest("Race 3", LocalDateTime.now().plusMonths(3), sportTestType3, province3);

        Block<SportTest> expectedBlock = new Block<>(Arrays.asList(sportTest1, sportTest2, sportTest3), false);

        assertEquals(expectedBlock, sportTestService.findSportTests(null, null, null, null, 0, 3));
    }

    @Test
    public void testFindSportTestsByPages() {

        Province province1 = addProvince("A Coruña");

        SportTestType sportTestType1 = addSportTestType("Race");

        SportTest sportTest1 = addSportTest("Race 1", LocalDateTime.now().plusMonths(2), sportTestType1, province1);
        SportTest sportTest2 = addSportTest("Race 2", LocalDateTime.now().plusMonths(2), sportTestType1, province1);
        SportTest sportTest3 = addSportTest("Race 3", LocalDateTime.now().plusMonths(2), sportTestType1, province1);

        Block<SportTest> expectedBlock = new Block<>(Arrays.asList(sportTest1, sportTest2), true);

        assertEquals(expectedBlock, sportTestService.findSportTests(null, null, null, null, 0, 2));

        expectedBlock = new Block<>(Arrays.asList(sportTest3), false);

        assertEquals(expectedBlock, sportTestService.findSportTests(null, null, null, null, 1, 2));

        expectedBlock = new Block<>(Arrays.asList(), false);

        assertEquals(expectedBlock, sportTestService.findSportTests(null, null, null, null, 2, 2));
    }

}
