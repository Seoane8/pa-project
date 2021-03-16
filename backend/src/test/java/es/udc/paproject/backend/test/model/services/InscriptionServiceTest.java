package es.udc.paproject.backend.test.model.services;

import es.udc.paproject.backend.model.entities.*;
import es.udc.paproject.backend.model.exceptions.*;
import es.udc.paproject.backend.model.services.InscriptionService;
import es.udc.paproject.backend.model.services.UserService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class InscriptionServiceTest {

    private final Long NON_EXISTENT_ID = Long.valueOf(-1);
    private final String CARD_NUMBER = "1234567890123456";
    private final LocalDateTime DATE = LocalDateTime.now().plusMonths(2);
    private final float PRICE = (float) 9.95;
    private final int MAX_PARTICIPANTS = 20;
    private final String DESCRIPTION = "description";
    private final String LOCATION = "A Coruña";


    @Autowired
    private UserService userService;

    @Autowired
    private ProvinceDao provinceDao;

    @Autowired
    private SportTestTypeDao sportTestTypeDao;

    @Autowired
    private SportTestDao sportTestDao;

    @Autowired
    private InscriptionService inscriptionService;

    @Autowired
    private InscriptionDao inscriptionDao;

    private User singUpUser(String userName) {

        User user = new User(userName, "password", "firstName",
                "lastName", userName+"@udc.es");

        try {
            userService.signUp(user);
        } catch (DuplicateInstanceException e) {
            throw new RuntimeException(e);
        }

        return user;

    }

    private Province addProvince () {
        return provinceDao.save(new Province("A Coruña"));
    }

    private SportTestType addSportTestType () {
        return sportTestTypeDao.save(new SportTestType("race"));
    }

    private SportTest addSportTest(String name, LocalDateTime date, int maxParticipants) {
        SportTest sportTest = new SportTest(name, DESCRIPTION, date, PRICE, maxParticipants,
                0, addSportTestType(), LOCATION, addProvince());
        return sportTestDao.save(sportTest);
    }
    private SportTest addSportTest(String name, LocalDateTime date) {
        return addSportTest(name, date, MAX_PARTICIPANTS);
    }
    private SportTest addSportTest(String name, int maxParticipants) {
        return addSportTest(name, DATE, maxParticipants);
    }
    private SportTest addSportTest(String name) {
        return addSportTest(name, DATE, MAX_PARTICIPANTS);
    }

    @Test
    public void testInscribeInNonExistingSportTest() {
        User user = singUpUser("user");

        assertThrows(InstanceNotFoundException.class, () ->
                inscriptionService.inscribe(NON_EXISTENT_ID, user.getId(), CARD_NUMBER));
    }

    @Test
    public void testAlreadyInscribed() throws PermissionException, AlreadyInscribedException,
            InscriptionDateExpiredException, NoMoreInscriptionsAllowedException, InstanceNotFoundException {
        User user = singUpUser("user");
        SportTest sportTest = addSportTest("Solidary Race 2021");

        inscriptionService.inscribe(sportTest.getId(), user.getId(), CARD_NUMBER);

        assertThrows(AlreadyInscribedException.class, () ->
                inscriptionService.inscribe(sportTest.getId(), user.getId(), CARD_NUMBER));
    }

    @Test
    public void testInscribeInFullSportTest() {
        User user = singUpUser("user");
        SportTest sportTest = addSportTest("Solidary Race 2021", 0);

        assertThrows(NoMoreInscriptionsAllowedException.class, () ->
                inscriptionService.inscribe(sportTest.getId(), user.getId(), CARD_NUMBER));
    }

    @Test
    public void testInscribeLate() {
        User user = singUpUser("user");
        SportTest sportTest = addSportTest("Solidary Race 2021", LocalDateTime.now().plusHours(20));

        assertThrows(InscriptionDateExpiredException.class, () ->
                inscriptionService.inscribe(sportTest.getId(), user.getId(), CARD_NUMBER));
    }

    @Test
    public void testInscribe() throws PermissionException, AlreadyInscribedException,
            InscriptionDateExpiredException, NoMoreInscriptionsAllowedException, InstanceNotFoundException {
        User user = singUpUser("user");
        SportTest sportTest = addSportTest("Solidary Race 2021");

        Inscription inscription = inscriptionService.inscribe(sportTest.getId(), user.getId(), CARD_NUMBER);
        Optional<Inscription> foundInscription = inscriptionDao.findById(inscription.getId());

        assertTrue(foundInscription.isPresent());
        assertEquals(inscription, foundInscription.get());
        assertEquals(inscription.getCardNumber(), foundInscription.get().getCardNumber());
        assertEquals(inscription.getDorsal(), foundInscription.get().getDorsal());
        assertEquals(inscription.getSportTest(), foundInscription.get().getSportTest());
        assertEquals(inscription.getPrice(), foundInscription.get().getPrice());
        assertEquals(inscription.getReservationDate(), foundInscription.get().getReservationDate());
        assertEquals(inscription.getUser(), foundInscription.get().getUser());

    }

    @Test
    public void testCollectDorsalOfNonExistingSportTest() throws PermissionException, AlreadyInscribedException,
            InscriptionDateExpiredException, NoMoreInscriptionsAllowedException, InstanceNotFoundException {
        User user = singUpUser("user");
        SportTest sportTest = addSportTest("Urban Race 2021");
        Inscription inscription = inscriptionService.inscribe(sportTest.getId(), user.getId(), CARD_NUMBER);
        sportTest.setDate(LocalDateTime.now().plusHours(10));

        assertThrows(InstanceNotFoundException.class, () ->
                inscriptionService.collectDorsal(NON_EXISTENT_ID, inscription.getId(), CARD_NUMBER));
    }

    @Test
    public void testCollectDorsalOfNonExistingInscription() {
        SportTest sportTest = addSportTest("Urban Race 2021");

        assertThrows(InstanceNotFoundException.class, () ->
                inscriptionService.collectDorsal(sportTest.getId(), NON_EXISTENT_ID, CARD_NUMBER));
    }

    @Test
    public void testCollectDorsalOfNotAssociatedInscription() throws PermissionException, AlreadyInscribedException,
            InscriptionDateExpiredException, NoMoreInscriptionsAllowedException, InstanceNotFoundException {
        User user = singUpUser("user");
        SportTest sportTestValid = addSportTest("Urban Race 2021");
        SportTest sportTestInvalid = addSportTest("Solidary Race 2021");
        Inscription inscription = inscriptionService.inscribe(sportTestValid.getId(), user.getId(), CARD_NUMBER);
        sportTestValid.setDate(LocalDateTime.now().plusHours(10));

        assertThrows(InscriptionNotAssociatedException.class, () ->
                inscriptionService.collectDorsal(sportTestInvalid.getId(), inscription.getId(), CARD_NUMBER));
    }

    @Test
    public void testCollectDorsalSoon() throws PermissionException, AlreadyInscribedException,
            InscriptionDateExpiredException, NoMoreInscriptionsAllowedException, InstanceNotFoundException {
        User user = singUpUser("user");
        SportTest sportTest = addSportTest("Urban Race 2021");
        Inscription inscription = inscriptionService.inscribe(sportTest.getId(), user.getId(), CARD_NUMBER);
        sportTest.setDate(LocalDateTime.now().plusHours(14));

        assertThrows(NotAllowedYetException.class, () ->
                inscriptionService.collectDorsal(sportTest.getId(), inscription.getId(), CARD_NUMBER));
    }

    @Test
    public void testCollectAlreadyCollectedInscription() throws PermissionException, AlreadyInscribedException,
            InscriptionDateExpiredException, NoMoreInscriptionsAllowedException, InstanceNotFoundException,
            NotAllowedYetException, AlreadyCollectedException, IncorrectCardNumberException,
            InscriptionNotAssociatedException {
        User user = singUpUser("user");
        SportTest sportTest = addSportTest("Urban Race 2021");
        Inscription inscription = inscriptionService.inscribe(sportTest.getId(), user.getId(), CARD_NUMBER);
        sportTest.setDate(LocalDateTime.now().plusHours(10));

        inscriptionService.collectDorsal(sportTest.getId(), inscription.getId(), CARD_NUMBER);

        assertThrows(AlreadyCollectedException.class, () ->
                inscriptionService.collectDorsal(sportTest.getId(), inscription.getId(), CARD_NUMBER));
    }

    @Test
    public void testCollectDorsalWithIncorrectCard () throws PermissionException, AlreadyInscribedException,
            InscriptionDateExpiredException, NoMoreInscriptionsAllowedException, InstanceNotFoundException {
        User user = singUpUser("user");
        SportTest sportTest = addSportTest("Urban Race 2021");
        Inscription inscription = inscriptionService.inscribe(sportTest.getId(), user.getId(), CARD_NUMBER);
        sportTest.setDate(LocalDateTime.now().plusHours(10));

        assertThrows(IncorrectCardNumberException.class, () ->
                inscriptionService.collectDorsal(sportTest.getId(), inscription.getId(), "1234567890000000"));
    }

    @Test
    public void testCollectDorsal () throws PermissionException, AlreadyInscribedException,
            InscriptionDateExpiredException, NoMoreInscriptionsAllowedException, InstanceNotFoundException,
            NotAllowedYetException, AlreadyCollectedException, IncorrectCardNumberException,
            InscriptionNotAssociatedException {
        User user = singUpUser("user");
        SportTest sportTest = addSportTest("Urban Race 2021");
        Inscription inscription = inscriptionService.inscribe(sportTest.getId(), user.getId(), CARD_NUMBER);
        sportTest.setDate(LocalDateTime.now().plusHours(10));

        int dorsal = inscriptionService.collectDorsal(sportTest.getId(), inscription.getId(), CARD_NUMBER);

        Optional<Inscription> foundInscription = inscriptionDao.findById(inscription.getId());

        assertTrue(foundInscription.get().isDorsalCollected());
        assertEquals(foundInscription.get().getDorsal(), dorsal);
    }

}
